import java.util.Scanner;

public class InsertInfo {
	Scanner sc = new Scanner(System.in);
	
	String id;
	String pw;
	String name;
	String birth;
	int age;
	String mail;
	String tel;
	String addr;
	String gender;
	
	
	InsertInfo(){
	System.out.println("ID를 입력해주세요");
	id = sc.next();
	System.out.println("비밀번호를 입력해주세요");
	pw = sc.next();
	System.out.println("이름을 입력해주세요");
	name = sc.next();
	System.out.println("생일을 입력해주세요");
	birth = sc.next();
	System.out.println("나이를 입력해주세요");
	age = sc.nextInt();
	System.out.println("mail을 입력해주세요");
	mail = sc.next();
	System.out.println("전화번호를 입력해주세요");
	tel = sc.next();
	System.out.println("주소를 입력해주세요");
	addr = sc.next();
	System.out.println("성별을 입력해주세요");
	gender = sc.next();
}
}
