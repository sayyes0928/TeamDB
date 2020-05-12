import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class UserDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	boolean result;
	Scanner sc = new Scanner(System.in);

	static final String JD = "com.mysql.jdbc.Driver";
	static final String DBURL = "jdbc:mysql://localhost/mydb";

	UserDAO() { // SQL 연결
		try {
			Class.forName(JD);
			conn = DriverManager.getConnection(DBURL, "root", "root");
			System.out.println("연결성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("ERROR : " + e);
		}
	}

	void login() { // 로그인 메소드
		Scanner sc = new Scanner(System.in);
		System.out.println("로그인을 선택하셨습니다");
		System.out.println("=======================");
		System.out.println("아이디를 입력해주세요:");
		String user_id = sc.next();
		System.out.println("비밀번호를 입력해주세요");
		String user_pw = sc.next();

		try {
			stmt = conn.createStatement();
			String sql2;
			sql2 = "select user_pw from user where user_id = '" + user_id + "'"; // userid적으면 입력해있는 pw가 나옴

			ResultSet rs2 = stmt.executeQuery(sql2); // rs2에는 내가 쳐준 user_id에 맞는 테이블의 pw가 들어있다.
			if (rs2.next()) { // 만약에 rs2.next pw라인의 첫번째값일때

				if (rs2.getString(1).equals(user_pw)) { // rs2의 테이블값과 내가 입력한 pw의 값이 같으면
					System.out.println("성공");
					String sql;

					sql = "select * from user where user_id = '" + user_id + "'"; // 내가 입력해준id 테이블값의 전체 테이블값들 출력
					ResultSet rs = stmt.executeQuery(sql); // 값을저장해준 모든정보를 결과 객체 rs에 담음
					if (rs.next()) {
						System.out.println("로그인 되었습니다 .회원님의 정보입니다!");
						System.out.println("=======================");
						System.out.println("ID :" + rs.getString(1));
						System.out.println("PSWD :" + rs.getString(2));
						System.out.println("**********************");
						System.out.println("Name :" + rs.getString(3));
						System.out.println("birth : " + rs.getDate(4));
						System.out.println("Age :" + rs.getInt(5));
						System.out.println("Mail :" + rs.getString(6));
						System.out.println("Tel : " + rs.getString(7));
						System.out.println("Addr :" + rs.getString(8));
						System.out.println("Gender :" + rs.getString(9));
						System.out.println("=======================");

						System.out.println("수정이나 삭제 선택해주세요");
						System.out.println("[1]수정");
						System.out.println("[2]삭제");
						System.out.println("[3]초기 화면으로");
						int sel = sc.nextInt();

						if (sel == 1) {
							int i = sc.nextInt();
							if (i == 0) {
								System.out.println("시스템이 종료되었습니다");
								System.exit(0);
							}

							update(rs.getString(i)); // ??
						} else if (sel == 2) {
							delete(rs.getString(1));
						} else {

						}
					} else {
						System.out.println("비밀번호가 틀렸습니다!");
					}
				}
			}
		}

		catch (

		Exception e) {
			System.out.println("호출실패");
		}

	}

	void delete(String id) {
		Scanner sc = new Scanner(System.in);
		try {
			String sql2;
			System.out.println("정말로 삭제하시겠습니까?");
			int del = 0;
			System.out.println("[1]예");
			System.out.println("[2]아니오");
			del = sc.nextInt();

			if (del == 1) {
				System.out.println("정상적으로 삭제되어 프로그램을 종료합니다.");
				sql2 = "delete from user where user_id ='" + id + "'";
				pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate(); // database 에 valuse 를 update! //
				pstmt.close();
			} else if (del == 2) {
				System.out.println("아니오를 선택하여 프로그램을 종료하니다.");
				System.exit(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void join(Model model) {

		try {
			System.out.println("회원가입 화면입니다");
			String sql2;

			System.out.println("ID를 입력해주세요");
			String id = sc.next();
			System.out.println("비밀번호를 입력해주세요");
			String pw = sc.next();
			System.out.println("이름을 입력해주세요");
			String name = sc.next();
			System.out.println("생일을 입력해주세요");
			String birth = sc.next();
			System.out.println("나이를 입력해주세요");
			int age = sc.nextInt();
			System.out.println("mail을 입력해주세요");
			String mail = sc.next();
			System.out.println("전화번호를 입력해주세요");
			String tel = sc.next();
			System.out.println("주소를 입력해주세요");
			String addr = sc.next();
			System.out.println("성별을 입력해주세요");
			System.out.println("[1] 남성  [2] 여성");
			String gender = null;
			int genNum = sc.nextInt();
			if (genNum == 1) {
				gender = "mail";
			} else if (genNum == 2) {
				gender = "female";
			} else {
				System.out.println("지정된 번호만 입력해주세요");
			}

			if (!id.equals(null) || !pw.equals(null) || !name.equals(null) || !birth.equals(null) || !mail.equals(null)
					|| !tel.equals(null) || !addr.equals(null) || !gender.equals(null)) {
				sql2 = "insert into user values(?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, birth);
				pstmt.setInt(5, age);
				pstmt.setString(6, mail);
				pstmt.setString(7, tel);
				pstmt.setString(8, addr);
				pstmt.setString(9, gender);
				pstmt.executeUpdate(); // database 에 valuse 를 update! //
				pstmt.close();
				System.out.println("회원가입이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	void update(String i) {
		String[] list = { "use_pW", "name", "birth", "age", "mail", "tel", "addr", "gender" };
		System.out.println("수정하고 싶은신 내용의 번호를 입력해주세요");

		System.out.println("[1]로그인 화면으로 돌아가기");
		System.out.println("[2]pw");
		System.out.println("[3]name");
		System.out.println("[4]birth");
		System.out.println("[5]age");
		System.out.println("[6]mail");
		System.out.println("[7]tel");
		System.out.println("[8]addr");
		System.out.println("[9]gender");
		System.out.println("[0]종료");

		int updateNum = (sc.nextInt() - 2); // 수정할 컨텐츠 번호 입력 받기

		System.out.println(list[updateNum] + "를 선택하셨습니다.");//
		System.out.println("수정하실 내용을 입력해 주세요");

		String change = sc.next(); // 변경할 값을 입력받을 변수
		try {
			String sql2;
			sql2 = "update user set " + list[updateNum] + " = '" + change + "' where " + list[updateNum] + "= '" + i
					+ "'";
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate(); // database 에 valuse 를 update! //
			pstmt.close();

		} catch (Exception e) {
			System.out.println("수정실패. 입력하신 내용을 확인해 주세요");
		}

	}
}
