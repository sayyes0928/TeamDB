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
	System.out.println("ID�� �Է����ּ���");
	id = sc.next();
	System.out.println("��й�ȣ�� �Է����ּ���");
	pw = sc.next();
	System.out.println("�̸��� �Է����ּ���");
	name = sc.next();
	System.out.println("������ �Է����ּ���");
	birth = sc.next();
	System.out.println("���̸� �Է����ּ���");
	age = sc.nextInt();
	System.out.println("mail�� �Է����ּ���");
	mail = sc.next();
	System.out.println("��ȭ��ȣ�� �Է����ּ���");
	tel = sc.next();
	System.out.println("�ּҸ� �Է����ּ���");
	addr = sc.next();
	System.out.println("������ �Է����ּ���");
	gender = sc.next();
}
}
