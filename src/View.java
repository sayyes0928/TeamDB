
public class View {
	void start() {
		System.out.println("[1]ȸ������");
		System.out.println("[2]�α���");
		System.out.println("[3]����");
	}
	void view(int i, String rs) { // �α��ν� ��µǴ� ȸ������
		String list[] = { "Null", "ID", "PassWord", "Name", "Birth", "Age", "Mail", "Tel", "Address", "Genfer" };
		System.out.println(list[i] + " : " + rs);

	}
	void viewSelectUpdate() {
		System.out.println("=======================");
		System.out.println();
		System.out.println("�����̳� ���� �������ּ���");
		System.out.println("[1]����");
		System.out.println("[2]����");
		System.out.println("[3]�ʱ� ȭ������");
	}

	void viewUpdate() {
		System.out.println("�����ϰ� ������ ������ ��ȣ�� �Է����ּ���");

		System.out.println("[1]pw");
		System.out.println("[2]name");
		System.out.println("[3]birth");
		System.out.println("[4]age");
		System.out.println("[5]mail");
		System.out.println("[6]tel");
		System.out.println("[7]addr");
		System.out.println("[8]gender");
		System.out.println("[0]����");
	}
	void deleteSelect() {
		System.out.println("������ �����Ͻðڽ��ϱ�?");
		System.out.println("[1]��");
		System.out.println("[2]�ƴϿ�");
	}
}
