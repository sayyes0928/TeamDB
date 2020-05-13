import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {
	UserDTO userDTO = new UserDTO();
	Scanner sc = new Scanner(System.in);
	int updateNum;
	String change;
	String list[];

	InputData() {// ������

	}

	String IdCheck() {
		System.out.print("ID  :  ");
		String id = sc.next();
		userDTO.setId(id);
		return id;
	}

	void insertInfo() { // ȸ������ ���ý� ����ڿ��� ���� �Է¹��� �޼ҵ�, DAO�� join���� ���� �Ű������� �Ѱ��ش�

		System.out.print("��й�ȣ  : ");
		String pw = sc.next();
		System.out.print("�̸�  :  ");
		String name = sc.next();
		System.out.println("������� ");
		System.out.print("Ex) 1991-09-28   :  ");
		String birth = sc.next();
		int age = 0;
		while (true) { // ���̿� ���ڸ� �Է����� ���� ����ó��
			try {
				System.out.print("����  :  ");
				age = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("���ڸ� �Է����ּ���");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print("����  :  ");
		String mail = sc.next();
		System.out.print("��ȭ��ȣ  :  ");
		String tel = sc.next();
		System.out.print("�ּ�  :  ");
		String addr = sc.next();
		System.out.println("������ �ش��ϴ� ��ȣ�� �Է��ϼ���");
		System.out.println("[1] ����  [2] ����");
		String gender = null;
		int cntId = 0;
		String genNum = sc.next();
		while (cntId == 0) {
			if (genNum.equals("1")) {
				gender = "male";
				cntId++;
			} else if (genNum.equals("2")) {
				gender = "female";
				cntId++;
			} else {
				System.out.println("������ ��ȣ�� �Է����ּ���");
				System.out.println("===================");
				System.out.println("������ �ش��ϴ� ��ȣ�� �Է��ϼ���\"");
				System.out.println("[1] ����  [2] ����");
				genNum = sc.next();
			}
		}

		// DTO�� ��ü�� �Է¹��� ���� �Ѱ��ش�. SET
		userDTO.setPw(pw);
		userDTO.setName(name);
		userDTO.setbirth(birth);
		userDTO.setAge(age);
		userDTO.setMail(mail);
		userDTO.setTel(tel);
		userDTO.setAddr(addr);
		userDTO.setGender(gender);

	}

	void loginInput() {
		System.out.println("�α����� �����ϼ̽��ϴ�");
		System.out.println("=======================");
		System.out.println("���̵� �Է����ּ���:");
		String user_id = sc.next();
		userDTO.setId(user_id);
		System.out.println("��й�ȣ�� �Է����ּ���");
		String user_pw = sc.next();
		userDTO.setPw(user_pw);
	}

	void updateInfo() {
		String[] list = { "null", "use_pW", "name", "birth", "age", "mail", "tel", "addr", "gender" };

		// ����ڿ��� ������ �Է¹ޱ�
		updateNum = (sc.nextInt()); // ������ ������ ��ȣ �Է� �ޱ�
		if (updateNum > 0 && updateNum < 10) {
			System.out.println(list[updateNum] + "�� �����ϼ̽��ϴ�.");//
			if (updateNum == 3) {
				System.out.println("��������� ����� ���� �Է��ϼ���");
				System.out.print("Ex) 1991-09-28   :  ");
			}
			System.out.println("�����Ͻ� ������ �Է��� �ּ���");

			change = sc.next(); // ������ ���� �Է¹��� ����
			System.out.println("���������� �����Ǿ����ϴ�.");

		}
	}

	void deleteSelect() {
		int del = sc.nextInt();
		if (del != 1) {
			System.out.println("�ƴϿ��� �����Ͽ� ���α׷��� �����ϴϴ�.");
			System.exit(0);
		}
	}

}
