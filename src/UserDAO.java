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

	Boolean login = false; // �α����� �����Ǿ����� true�� ��ȯ�� ���� �ܰ�� �Ѿ �� �ְ� �����

	static final String JD = "com.mysql.jdbc.Driver";
	static final String DBURL = "jdbc:mysql://localhost/mydb";
	static final String dbID = "root";
	static final String dbPW = "root";

	UserDAO() { // SQL ����, �������̱� ������ ��ü ������ ȣ��
		try {
			Class.forName(JD);
			System.out.println("���Ἲ��");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����̹� �ε� ����");
		}
	}

	public void connect() { // DB�� �����ϴ� �޼ҵ�
		try {
			conn = DriverManager.getConnection(DBURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() { // ����� DB�� �ݾ��ִ� �޼ҵ�
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

	int idCheck() { // InputData Ŭ�������� ȸ�����Խ� ID�ߺ�Ȯ���� ���� ȣ��Ǿ� �����
		connect();
		String idcheck = input.IdCheck(); // id�Է¹ޱ�
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sqlIdcheck;
			sqlIdcheck = "select user_id from user where user_id = '" + input.userDTO.getId() + "'"; // user_id �ʵ忡 �ߺ�
																										// ���� �ִ��� Ȯ��
			ResultSet rs = stmt.executeQuery(sqlIdcheck); //
			if (rs.next()) {
				if (idcheck.equals(rs.getString(1))) {
					result = -1; // �ߺ����� ���� �� -1�� ��ȯ���� ��� ������ ������ �˷��ش�
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	void join() { // ȸ������
		// DB����
		connect();
		InputData inputDB = new InputData(); // InputData Class���� �Է¹��� ������ �����ϱ����� ���ο� ��ü ����
		inputDB.insertInfo(); // �� �޼ҵ忡���� �����

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
			pstmt.executeUpdate(); // database �� valuse �� update! //
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
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
			sql2 = "select user_pw from user where user_id = '" + input.userDTO.getId() + "'"; // userid������ �Է����ִ� pw�� ����
			ResultSet rs2 = stmt.executeQuery(sql2); // rs2���� ���� ���� user_id�� �´� ���̺��� pw�� ����ִ�.
			if (rs2.next()) {
				pass = rs2.getString(1);

			}
		} catch (Exception e) {
			System.out.println("ID�� Ʋ�Ƚ��ϴ�");
		} finally {
			disconnect();
		}
		return pass;
	}

	void login() { // �α��� �޼ҵ�
		connect();
		input.loginInput();
		try {
			stmt = conn.createStatement();
			String pass = idexist(input.userDTO.getId());
			if (pass.equals(input.userDTO.getPw())) { // rs2�� ���̺��� ���� �Է��� pw�� ���� ������
				String sql;
				sql = "select * from user where user_id = '" + input.userDTO.getId() + "'"; // ���� �Է�����id ���̺��� ��ü��
				ResultSet rs = stmt.executeQuery(sql); // ������������ ��������� ��� ��ü rs�� ����
				if (rs.next()) {
					System.out.println("�α��� �Ǿ����ϴ� .ȸ������ �����Դϴ�!");
					System.out.println("=======================");
					for (int i = 1; i < 10; i++) { // �����׺��̽� ���� ����ϱ����� �޼ҵ�
						view.view(i, rs.getString(i));
					}
					// �α��� �� ������ ��ü�� ��� SET ���� ��Ʈ���� ���
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
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȣ�����");
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
			pstmt.executeUpdate(); // database �� valuse �� update! //
			pstmt.close();
			System.out.println("���������� �����Ǿ����ϴ�.");
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
			pstmt.executeUpdate(); // database �� valuse �� update! //
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������. �Է��Ͻ� ������ Ȯ���� �ּ���");
		} finally {
			disconnect();
		}
	}
}
