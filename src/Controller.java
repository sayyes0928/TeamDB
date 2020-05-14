import java.util.Scanner;

public class Controller {
	Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserDAO(); // 유저의 매개변수에 Model의 객체를 넣어준다는의미
	View view = new View();

	Controller() {
		int count = 0;
		while (count == 0) {
			view.start();
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("회원가입 화면입니다");
				int idOK = userDAO.idCheck();
				while (true) {
					if (idOK != -1) {
						userDAO.join();
						break;
					} else {
						System.out.println("중복된 ID가 있습니다.");
						idOK = userDAO.idCheck();
					}
				}
				break;
			case 2:
				userDAO.login();
				if (userDAO.login == true) {
					view.viewSelectUpdate(); // 수정 삭제 선택문 출력
					int select = sc.nextInt();
					if (select == 1) {
						view.viewUpdate();
						userDAO.update();
					} else if (select == 2) {
						userDAO.delete();
					}
				} else {
					System.out.println("로그인에 실패했습니다.");
				}
				break;
			case 3:
				System.out.println("시스템이 종료되었습니다");
				System.exit(0);
			}

		}

	}

}
