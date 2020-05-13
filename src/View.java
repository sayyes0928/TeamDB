
public class View {
	void start() {
		System.out.println("[1]회원가입");
		System.out.println("[2]로그인");
		System.out.println("[3]종료");
	}
	void view(int i, String rs) { // 로그인시 출력되는 회원정보
		String list[] = { "Null", "ID", "PassWord", "Name", "Birth", "Age", "Mail", "Tel", "Address", "Genfer" };
		System.out.println(list[i] + " : " + rs);

	}
	void viewSelectUpdate() {
		System.out.println("=======================");
		System.out.println();
		System.out.println("수정이나 삭제 선택해주세요");
		System.out.println("[1]수정");
		System.out.println("[2]삭제");
		System.out.println("[3]초기 화면으로");
	}

	void viewUpdate() {
		System.out.println("수정하고 싶은신 내용의 번호를 입력해주세요");

		System.out.println("[1]pw");
		System.out.println("[2]name");
		System.out.println("[3]birth");
		System.out.println("[4]age");
		System.out.println("[5]mail");
		System.out.println("[6]tel");
		System.out.println("[7]addr");
		System.out.println("[8]gender");
		System.out.println("[0]종료");
	}
	void deleteSelect() {
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("[1]예");
		System.out.println("[2]아니오");
	}
}
