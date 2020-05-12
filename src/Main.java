import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO userDAO = new UserDAO(); // 유저의 매개변수에 Model의 객체를 넣어준다는의미

		int count = 0;
		while (count == 0) {
			System.out.println("[1]회원가입");
			System.out.println("[2]로그인");
			System.out.println("[3]종료");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				userDAO.join(new Model());
				break;
			case 2:
				userDAO.login();
				break;
			case 3:
				System.out.println("시스템이 종료되었습니다");
				count++;
			}
		}
		//

	}

}
