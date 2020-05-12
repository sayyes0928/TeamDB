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

	UserDAO() { // SQL ����
		try {
			Class.forName(JD);
			conn = DriverManager.getConnection(DBURL, "root", "root");
			System.out.println("���Ἲ��");

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("ERROR : " + e);
		}
	}

	void login() { // �α��� �޼ҵ�
		Scanner sc = new Scanner(System.in);
		System.out.println("�α����� �����ϼ̽��ϴ�");
		System.out.println("=======================");
		System.out.println("���̵� �Է����ּ���:");
		String user_id = sc.next();
		System.out.println("��й�ȣ�� �Է����ּ���");
		String user_pw = sc.next();

		try {
			stmt = conn.createStatement();
			String sql2;
			sql2 = "select user_pw from user where user_id = '" + user_id + "'"; // userid������ �Է����ִ� pw�� ����

			ResultSet rs2 = stmt.executeQuery(sql2); // rs2���� ���� ���� user_id�� �´� ���̺��� pw�� ����ִ�.
			if (rs2.next()) { // ���࿡ rs2.next pw������ ù��°���϶�

				if (rs2.getString(1).equals(user_pw)) { // rs2�� ���̺��� ���� �Է��� pw�� ���� ������
					System.out.println("����");
					String sql;

					sql = "select * from user where user_id = '" + user_id + "'"; // ���� �Է�����id ���̺��� ��ü ���̺��� ���
					ResultSet rs = stmt.executeQuery(sql); // ������������ ��������� ��� ��ü rs�� ����
					if (rs.next()) {
						System.out.println("�α��� �Ǿ����ϴ� .ȸ������ �����Դϴ�!");
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

						System.out.println("�����̳� ���� �������ּ���");
						System.out.println("[1]����");
						System.out.println("[2]����");
						System.out.println("[3]�ʱ� ȭ������");
						int sel = sc.nextInt();

						if (sel == 1) {
							int i = sc.nextInt();
							if (i == 0) {
								System.out.println("�ý����� ����Ǿ����ϴ�");
								System.exit(0);
							}

							update(rs.getString(i)); // ??
						} else if (sel == 2) {
							delete(rs.getString(1));
						} else {

						}
					} else {
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�!");
					}
				}
			}
		}

		catch (

		Exception e) {
			System.out.println("ȣ�����");
		}

	}

	void delete(String id) {
		Scanner sc = new Scanner(System.in);
		try {
			String sql2;
			System.out.println("������ �����Ͻðڽ��ϱ�?");
			int del = 0;
			System.out.println("[1]��");
			System.out.println("[2]�ƴϿ�");
			del = sc.nextInt();

			if (del == 1) {
				System.out.println("���������� �����Ǿ� ���α׷��� �����մϴ�.");
				sql2 = "delete from user where user_id ='" + id + "'";
				pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate(); // database �� valuse �� update! //
				pstmt.close();
			} else if (del == 2) {
				System.out.println("�ƴϿ��� �����Ͽ� ���α׷��� �����ϴϴ�.");
				System.exit(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void join(Model model) {

		try {
			System.out.println("ȸ������ ȭ���Դϴ�");
			String sql2;

			System.out.println("ID�� �Է����ּ���");
			String id = sc.next();
			System.out.println("��й�ȣ�� �Է����ּ���");
			String pw = sc.next();
			System.out.println("�̸��� �Է����ּ���");
			String name = sc.next();
			System.out.println("������ �Է����ּ���");
			String birth = sc.next();
			System.out.println("���̸� �Է����ּ���");
			int age = sc.nextInt();
			System.out.println("mail�� �Է����ּ���");
			String mail = sc.next();
			System.out.println("��ȭ��ȣ�� �Է����ּ���");
			String tel = sc.next();
			System.out.println("�ּҸ� �Է����ּ���");
			String addr = sc.next();
			System.out.println("������ �Է����ּ���");
			System.out.println("[1] ����  [2] ����");
			String gender = null;
			int genNum = sc.nextInt();
			if (genNum == 1) {
				gender = "mail";
			} else if (genNum == 2) {
				gender = "female";
			} else {
				System.out.println("������ ��ȣ�� �Է����ּ���");
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
				pstmt.executeUpdate(); // database �� valuse �� update! //
				pstmt.close();
				System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	void update(String i) {
		String[] list = { "use_pW", "name", "birth", "age", "mail", "tel", "addr", "gender" };
		System.out.println("�����ϰ� ������ ������ ��ȣ�� �Է����ּ���");

		System.out.println("[1]�α��� ȭ������ ���ư���");
		System.out.println("[2]pw");
		System.out.println("[3]name");
		System.out.println("[4]birth");
		System.out.println("[5]age");
		System.out.println("[6]mail");
		System.out.println("[7]tel");
		System.out.println("[8]addr");
		System.out.println("[9]gender");
		System.out.println("[0]����");

		int updateNum = (sc.nextInt() - 2); // ������ ������ ��ȣ �Է� �ޱ�

		System.out.println(list[updateNum] + "�� �����ϼ̽��ϴ�.");//
		System.out.println("�����Ͻ� ������ �Է��� �ּ���");

		String change = sc.next(); // ������ ���� �Է¹��� ����
		try {
			String sql2;
			sql2 = "update user set " + list[updateNum] + " = '" + change + "' where " + list[updateNum] + "= '" + i
					+ "'";
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate(); // database �� valuse �� update! //
			pstmt.close();

		} catch (Exception e) {
			System.out.println("��������. �Է��Ͻ� ������ Ȯ���� �ּ���");
		}

	}
}
