package final_exam;

public class Chess {
	private boolean alive=true;
	public String Color;
	public Chess() {} //���幹�췽��
	public void eat() {alive=false;} //�������ӱ���
	public boolean isAlive() {return alive;} //���庯���ж������Ƿ���
}
