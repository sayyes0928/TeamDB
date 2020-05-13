import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {
	UserDTO userDTO = new UserDTO();
	Scanner sc = new Scanner(System.in);
	int updateNum;
	String change;
	String list[];

	InputData() {// 생성자

	}

	String IdCheck() {
		System.out.print("ID  :  ");
		String id = sc.next();
		userDTO.setId(id);
		return id;
	}

	void insertInfo() { // 회원가입 선택시 사용자에게 값을 입력받을 메소드, DAO의 join으로 값을 매개변수로 넘겨준다

		System.out.print("비밀번호  : ");
		String pw = sc.next();
		System.out.print("이름  :  ");
		String name = sc.next();
		System.out.println("생년월일 ");
		System.out.print("Ex) 1991-09-28   :  ");
		String birth = sc.next();
		int age = 0;
		while (true) { // 나이에 문자를 입력했을 때의 예외처리
			try {
				System.out.print("나이  :  ");
				age = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("숫자만 입력해주세요");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print("메일  :  ");
		String mail = sc.next();
		System.out.print("전화번호  :  ");
		String tel = sc.next();
		System.out.print("주소  :  ");
		String addr = sc.next();
		System.out.println("성별에 해당하는 번호를 입력하세요");
		System.out.println("[1] 남성  [2] 여성");
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
				System.out.println("지정된 번호만 입력해주세요");
				System.out.println("===================");
				System.out.println("성별에 해당하는 번호를 입력하세요\"");
				System.out.println("[1] 남성  [2] 여성");
				genNum = sc.next();
			}
		}

		// DTO의 객체에 입력받은 값을 넘겨준다. SET
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
		System.out.println("로그인을 선택하셨습니다");
		System.out.println("=======================");
		System.out.println("아이디를 입력해주세요:");
		String user_id = sc.next();
		userDTO.setId(user_id);
		System.out.println("비밀번호를 입력해주세요");
		String user_pw = sc.next();
		userDTO.setPw(user_pw);
	}

	void updateInfo() {
		String[] list = { "null", "use_pW", "name", "birth", "age", "mail", "tel", "addr", "gender" };

		// 사용자에게 수정값 입력받기
		updateNum = (sc.nextInt()); // 수정할 컨텐츠 번호 입력 받기
		if (updateNum > 0 && updateNum < 10) {
			System.out.println(list[updateNum] + "를 선택하셨습니다.");//
			if (updateNum == 3) {
				System.out.println("생년월일을 보기와 같이 입력하세요");
				System.out.print("Ex) 1991-09-28   :  ");
			}
			System.out.println("수정하실 내용을 입력해 주세요");

			change = sc.next(); // 변경할 값을 입력받을 변수
			System.out.println("정상적으로 수정되었습니다.");

		}
	}

	void deleteSelect() {
		int del = sc.nextInt();
		if (del != 1) {
			System.out.println("아니오를 선택하여 프로그램을 종료하니다.");
			System.exit(0);
		}
	}

}
