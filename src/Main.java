import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO userDAO = new UserDAO(); // ������ �Ű������� Model�� ��ü�� �־��شٴ��ǹ�

		int count = 0;
		while (count == 0) {
			System.out.println("[1]ȸ������");
			System.out.println("[2]�α���");
			System.out.println("[3]����");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				userDAO.join(new Model());
				break;
			case 2:
				userDAO.login();
				break;
			case 3:
				System.out.println("�ý����� ����Ǿ����ϴ�");
				count++;
			}
		}
		//

	}

}
