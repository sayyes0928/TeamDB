import java.util.Scanner;

public class Controller {
	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserDAO(); // ������ �Ű������� Model�� ��ü�� �־��شٴ��ǹ�
	View view = new View();

	Controller() {
		int count = 0;
		while (count == 0) {
			view.start();
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("ȸ������ ȭ���Դϴ�");
				int idOK = userDAO.idCheck();
				while (true) {
					if (idOK != -1) {
						userDAO.join();
						break;
					} else {
						System.out.println("�ߺ��� ID�� �ֽ��ϴ�.");
						idOK = userDAO.idCheck();
					}
				}
				break;
			case 2:
				userDAO.login();
				if (userDAO.login == true) {
					view.viewSelectUpdate(); // ���� ���� ���ù� ���
					int select = sc.nextInt();
					if (select == 1) {
						view.viewUpdate();
						userDAO.update();
					} else if (select == 2) {
						userDAO.delete();
					}
				} else {
					System.out.println("�α��ο� �����߽��ϴ�.");
				}
				break;
			case 3:
				System.out.println("�ý����� ����Ǿ����ϴ�");
				System.exit(0);
			}

		}

	}

}
