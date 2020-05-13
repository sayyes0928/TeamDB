import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class UserDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	Scanner sc = new Scanner(System.in);
	UserDTO userDTO = new UserDTO();
	InputData input = new InputData();
	View view = new View();

	Boolean login = false; // 로그인이 성공되었으면 true를 반환해 다음 단계로 넘어갈 수 있게 만든다

	static final String JD = "com.mysql.jdbc.Driver";
	static final String DBURL = "jdbc:mysql://localhost/mydb";
	static final String dbID = "root";
	static final String dbPW = "root";

	UserDAO() { // SQL 연결, 생성자이기 때문에 객체 생성시 호출
		try {
			Class.forName(JD);
			System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}
	}

	public void connect() { // DB와 연결하는 메소드
		try {
			conn = DriverManager.getConnection(DBURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() { // 연결된 DB를 닫아주는 메소드
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	int idCheck() { // InputData 클래스에서 회원가입시 ID중복확인을 위해 호출되어 사용함
		connect();
		String idcheck = input.IdCheck(); // id입력받기
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sqlIdcheck;
			sqlIdcheck = "select user_id from user where user_id = '" + input.userDTO.getId() + "'"; // user_id 필드에 중복
																										// 값이 있는지 확인
			ResultSet rs = stmt.executeQuery(sqlIdcheck); //
			if (rs.next()) {
				if (idcheck.equals(rs.getString(1))) {
					result = -1; // 중복값이 있을 때 -1을 반환값에 담아 오류가 있음을 알려준다
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	void join() { // 회원가입
		// DB연결
		connect();
		InputData inputDB = new InputData(); // InputData Class에서 입력받은 값을들 저장하기위해 새로운 객체 생성
		inputDB.insertInfo(); // 이 메소드에서만 사용함

		try {
			String sqlJoin;
			sqlJoin = "insert into user values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sqlJoin);
			pstmt.setString(1, input.userDTO.getId());
			pstmt.setString(2, inputDB.userDTO.getPw());
			pstmt.setString(3, inputDB.userDTO.getName());
			pstmt.setString(4, inputDB.userDTO.getbirth());
			pstmt.setInt(5, inputDB.userDTO.getAge());
			pstmt.setString(6, inputDB.userDTO.getMail());
			pstmt.setString(7, inputDB.userDTO.getTel());
			pstmt.setString(8, inputDB.userDTO.getAddr());
			pstmt.setString(9, inputDB.userDTO.getGender());
			pstmt.executeUpdate(); // database 에 valuse 를 update! //
			System.out.println("회원가입이 완료되었습니다.");
			System.out.println("=======================");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disconnect();
		}
	}

	String idexist(String id) {
		connect();
		String pass = null;
		try {
			stmt = conn.createStatement();
			String sql2;
			sql2 = "select user_pw from user where user_id = '" + input.userDTO.getId() + "'"; // userid적으면 입력해있는 pw가 나옴
			ResultSet rs2 = stmt.executeQuery(sql2); // rs2에는 내가 쳐준 user_id에 맞는 테이블의 pw가 들어있다.
			if (rs2.next()) {
				pass = rs2.getString(1);

			}
		} catch (Exception e) {
			System.out.println("ID가 틀렸습니다");
		} finally {
			disconnect();
		}
		return pass;
	}

	void login() { // 로그인 메소드
		connect();
		input.loginInput();
		try {
			stmt = conn.createStatement();
			String pass = idexist(input.userDTO.getId());
			if (pass.equals(input.userDTO.getPw())) { // rs2의 테이블값과 내가 입력한 pw의 값이 같으면
				String sql;
				sql = "select * from user where user_id = '" + input.userDTO.getId() + "'"; // 내가 입력해준id 테이블값의 전체의
				ResultSet rs = stmt.executeQuery(sql); // 값을저장해준 모든정보를 결과 객체 rs에 담음
				if (rs.next()) {
					System.out.println("로그인 되었습니다 .회원님의 정보입니다!");
					System.out.println("=======================");
					for (int i = 1; i < 10; i++) { // 데이테베이스 값을 출력하기위한 메소드
						view.view(i, rs.getString(i));
					}
					// 로그인 된 정보를 객체에 담아 SET 수정 파트에서 사용
					userDTO.setId(rs.getString(1));
					userDTO.setPw(rs.getString(2));
					userDTO.setName(rs.getString(3));
					userDTO.setbirth(rs.getString(4));
					userDTO.setAge(rs.getInt(5));
					userDTO.setMail(rs.getString(6));
					userDTO.setTel(rs.getString(7));
					userDTO.setAddr(rs.getString(8));
					userDTO.setGender(rs.getString(9));
					login = true;
				}
			} else {
				System.out.println("비밀번호가 틀렸습니다!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("호출실패");
		} finally {
			disconnect();
		}
	}

	void delete() {
		connect();
		try {

			view.deleteSelect();
			input.deleteSelect();

			String sqlDel;
			sqlDel = "delete from user where user_id ='" + userDTO.getId() + "'";
			pstmt = conn.prepareStatement(sqlDel);
			pstmt.executeUpdate(); // database 에 valuse 를 update! //
			pstmt.close();
			System.out.println("정상적으로 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	void update() {
		connect();
		input.updateInfo();
		String[] list = { "null", "user_pW", "user_name", "user_birth", "user_age", "user_mail", "user_tel",
				"user_addr", "user_gender" };

		try {
			String sql2;
			sql2 = "update user set " + list[input.updateNum] + " = '" + input.change + "' where user_id= '"
					+ userDTO.getId() + "'";
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate(); // database 에 valuse 를 update! //
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정실패. 입력하신 내용을 확인해 주세요");
		} finally {
			disconnect();
		}
	}
}
