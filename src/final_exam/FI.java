package final_exam;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//�̳�MouseListener�ӿڣ�����ʵ�����еĳ��󷽷�
public class FI extends JFrame implements MouseListener{
	int width = Toolkit.getDefaultToolkit().getScreenSize().width; 
	int height = Toolkit.getDefaultToolkit().getScreenSize().height; //���ÿ�Ⱥ͸߶ȣ�������Ļ�ֱ���
	private Chess[][] allChess = new Chess[9][9]; //���б�����������
	private Chess currentChess; //ѡ�е�����
	private int step = 1; //���ӵĲ���������Ϊ�����ߣ�˫��Ϊ�����ߣ���������
	private boolean first = true; //�ж��Ƿ����ִ�е�һ����ѡ�����ӣ�
	private boolean second = false; //�ж��Ƿ����ִ�еڶ������������ӣ�
	private String message1; //message1��֪��Ҹ�˭��
	private String message2 = ""; //message2��֪�����Ϸ״̬����������
	
	//���캯��
	public FI(){
		this.initChess();
		this.setTitle("�۹��ŵ�����"); //���ñ���
		this.setSize(700,700); //���ô��ڴ�С
		this.setLocation((width - 500) / 2 , (height - 500) / 2 ); //���ô���λ�ã��ô��ھ�����ʾ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ùرհ�ť
		this.setResizable(false); //���ô��ڲ��ɸı䣬�̶����ڴ�С
		this.setVisible(true); //���ÿ��ӻ�	
		this.repaint(); //java��repaint()���ػ�component�ķ���������ֱ�ӵ�������ķ����Խ���������л滭			
		this.addMouseListener(this); //ʵ���������ķ���
	}
	
	//��������
	public void paint(Graphics g) {
		//BufferedImage����Imageʵ���࣬��һ����������ͼ���࣬��Ҫ�����ǽ�һ��ͼƬ���ص��ڴ���
		BufferedImage buf = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB); //����ͼƬ��С
		Graphics g1 =  buf.createGraphics();
		g1.setColor(Color.WHITE); 
		g1.fillRect(0, 0, 700, 700); //������ɫ���
		Font f = new Font("΢���ź�",Font.BOLD,30); 
		g1.setFont(f); //��������������
		g1.setColor(Color.WHITE); //�������̱�����ɫ
		g1.fill3DRect(50, 60, 450, 450, true); //�������̴�С��500,500��
		for(int i=0;i<=9;i++){
			g1.setColor(Color.BLACK); //�����ߵ���ɫ
			g1.drawLine(50, 60+i*50, 450+50, 60+i*50); //�����ߣ�ÿ������֮��ľ���Ϊ50
			g1.drawLine(50+i*50, 60, 50+i*50, 450+60); //������
		}
		g1.setFont(new Font("΢���ź�",Font.BOLD,20));
		g1.drawRect(550,100,100,40);
	    g1.drawString("���¿�ʼ",560,125); //���¿�ʼ��ť
		g1.drawRect(550,250,100,40);
	    g1.drawString("��Ϸ����",560,275); //��Ϸ����ť
	    if(step%2==0) {message1 = "����";}
	    else {message1 = "����";}
	    g1.drawString("�ڷ����У����ڸ�"+message1, 50, 550);
	    g1.drawString("��Ϸ״̬��"+message2, 50, 600);
	    
	    
		//��������
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(allChess[i][j] instanceof Black_Chess) { //instanceof��һ���ؼ��֣������ж��Ƿ�����һ����
					//�����������ڵ�λ��
					int tempX = i*50+50; 
					int tempY = j*50+60; 
					g1.setColor(Color.BLACK);
					g1.fillOval(tempX, tempY, 50, 50); //����Բ�����ӣ�50��ʾԲ�Ĵ�СΪ50*50�ľ��ε��ڽ�Բ
					g1.setColor(Color.BLACK);
					g1.drawOval(tempX, tempY, 50, 50);
				}
				else if(allChess[i][j] instanceof White_Chess) {
					int tempX = i*50+50;
					int tempY = j*50+60;
					g1.setColor(Color.WHITE);
					g1.fillOval(tempX, tempY, 50, 50);
					g1.setColor(Color.BLACK);
					g1.drawOval(tempX, tempY, 50, 50);
					if(allChess[i][j] instanceof King) {//���ƹ�������
						tempX = i*50+50;
						tempY = j*50+60;
						g1.setColor(Color.WHITE);
						g1.fillOval(tempX, tempY, 50, 50);
						g1.setColor(Color.BLACK);
						g1.drawOval(tempX, tempY, 50, 50);
						g1.drawOval(tempX+10, tempY+10, 30, 30);
					}
				}
			}
		}
		g.drawImage(buf, 0, 0,this); //��ͼƬ��ʾ����
	}
	
	//��ʼ������
	private void initChess() {
		//��ʼ������
		allChess[0][3] = new Black_Chess();
		allChess[0][4] = new Black_Chess();
		allChess[0][5] = new Black_Chess();
		allChess[1][4] = new Black_Chess();
		allChess[3][8] = new Black_Chess();
		allChess[4][8] = new Black_Chess();
		allChess[5][8] = new Black_Chess();
		allChess[4][7] = new Black_Chess();
		allChess[3][0] = new Black_Chess();
		allChess[4][0] = new Black_Chess();
		allChess[5][0] = new Black_Chess();
		allChess[4][1] = new Black_Chess();
		allChess[8][3] = new Black_Chess();
		allChess[8][4] = new Black_Chess();
		allChess[8][5] = new Black_Chess();
		allChess[7][4] = new Black_Chess();
		//��ʼ������
		allChess[2][4] = new White_Chess();
		allChess[3][4] = new White_Chess();
		allChess[5][4] = new White_Chess();
		allChess[6][4] = new White_Chess();
		allChess[4][2] = new White_Chess();
		allChess[4][3] = new White_Chess();
		allChess[4][5] = new White_Chess();
		allChess[4][6] = new White_Chess();
		//��ʼ������
		allChess[4][4] = new King();
	}
	
	public int x1,y1,x2,y2;//(x1,y1)��ʾѡ�������λ�õ�����(x2,y2)��ʾ�ƶ��Ŀո��λ������
	public int[] max = new int[4]; //max�б��ʾ�����ƶ�����Զ���� 0-left��1-right��2-up��3-down��
	//mouseClicked��������ѡ�����Ӳ����ƶ����ӣ��ı��б���ڲ����죩
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int i = (x-50)/50;
		int j = (y-60)/50;
		if(i>=0 && i<=8 && j<=8 && j>=0) { //�ж�����Ƿ����������ڣ���Ȼֱ���˳�
			//�������ӵ�ѡ��
			if(first) {
				x1 = i;
				y1 = j;
				if(allChess[i][j]!=null) {
					//����Ͱ���ĳ���˳��
					if((allChess[i][j].Color == "black" && step%2!=0) || (allChess[i][j].Color == "white" && step%2==0)) {
						currentChess = allChess[i][j]; //ѡ��Ҫ�ƶ�������
						//ѡ�е����ӵ��ƶ���Զ������б�
						for(int n=1;n<=x1;n++) { //left
							if(allChess[x1-n][y1]!=null) {max[0] = n-1;break;}
							else{max[0] = x1;}
						}
						for(int n=1;n<=8-x1;n++) { //right
							if(allChess[x1+n][y1]!=null) {max[1] = n-1;break;} 
							else{max[1] = 8-x1;}
						}
						for(int n=1;n<=y1;n++) { //up
							if(allChess[x1][y1-n]!=null) {max[2] = n-1;break;} 
							else{max[2] = y1;}
						}
						for(int n=1;n<=8-y1;n++) { //down
							if(allChess[x1][y1+n]!=null) {max[3] = n-1;break;} 
							else{max[3] = 8-y1;}
						}
						allChess[i][j] = null; //ѡ�е����ӱ�������ƶ�
						second = true; //ѡ����ϣ�����ִ�еڶ���
						first = false; //�����ٽ��е�һ��
						//System.out.println("left��"+max[0]+"right"+max[1]+"up"+max[2]+"down"+max[3]);
					}
					else {
						JOptionPane.showMessageDialog(null,"��ѡ���Լ�������");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"��ѡ������");
				}
			}
			else {
				x2 = i;
				y2 = j;
				if(second) {
					//���������ƶ�λ��,�����㷨������ֻ���ƶ������������ĸ�����С�����ֵ�ľ���
					if(x1==x2 && y1==y2) {
						JOptionPane.showMessageDialog(null,"���ƶ�����");
					}
					else {
						boolean go = go(x1,x2,y1,y2,max); //����go���ж�
						if(go) {
							allChess[i][j] = currentChess; //�����ӷ���յ��б���
							eatChess(currentChess); //���г��Ӳ���
							this.repaint(); //�����ػ�
							second = false; //����ִ�еڶ���
							first = true; //����ִ�е�һ��
							step+=1; //����+=1
							message2 = "�ƶ���һ������";
							int w = isWin(currentChess); //�ж���Ӯ
							if(w == 1) {
								JOptionPane.showMessageDialog(null,"����Ӯ");
							}
							else if(w == 2) {
								JOptionPane.showMessageDialog(null,"����Ӯ");
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"�����ƶ�������");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"��ѡ���Լ�������");
				}
			}
		}
		//���¿�ʼ
		else if(x<=600 && x>=500 && y>=80 && y<=120) {
			restart();
		}
		//��Ϸ����
		else if(x<=600 && x>=500 && y>=230 && y<=270) {
			//��������
			JOptionPane.showMessageDialog(null,"����һ���ŵ�������Ϸ����Ϊ�������������Ӫ�����к������ߡ�\r\n"
					+ "�淨�������߷��൱�������еġ�������ֻҪû�ϰ�һ�ο��Ժ��������߶������ҷ����������ӽ��Է������Ӽ����м䣬�Է������Ӿͱ����ˡ�\r\n" + 
					"ʤ���������������м����й���������Ե����������ʤ���������ܵ������������ʤ��(����ƨ�ɵ����Ǹ�λ��Ϊ�ϰ�����ͨ��)");
		}
		else {
			JOptionPane.showMessageDialog(null,"���������ڵ��");
		}
	}
	
	//isWin()�ж��Ƿ�Ӯ��ʤ���ĺ���,1�������Ӯ,2�������Ӯ,0�������
	public int isWin(Chess c) {
		int win = 0;
		boolean black = true;
		for(int i=0;i<=8;i++) {
			for(int j=0;j<=8;j++) {
				if(allChess[i][j] instanceof King) {
					int[] ls = getP(c);
					System.out.println(ls[0]);
					if(c instanceof King) {
						if(ls[0]==0||ls[1]==0||ls[0]==8||ls[1]==8) {
							win = 1;
						}
					}
					black = false;
				}
			}
		}
		if(black) {
			win = 2;
		}
		return win;
	}
	
	//����һ�����ӵ�λ��
	public int[] getP(Chess c) {
		int[] ls = new int[2];
		for(int i=0;i<=8;i++) {
			for(int j=0;j<=8;j++) {
				if(allChess[i][j] == c) {
					ls[0]=i;
					ls[1]=j;
				}
			}
		}
		return ls;
	}
	
	//go���������ƶ����Ӳ������������ƶ�����
	public boolean go(int x1,int x2,int y1,int y2,int[] max) {
		boolean go = false;
		if(y1 == y2) {
			if(x1-x2>=0 && x1-x2<=max[0]) {
				go = true;
			}
			else if(x1-x2<0 && x2-x1<=max[1]) {
				go = true;
			}
		}
		else if(x1 == x2) {
			if(y1-y2>=0 && y1-y2<=max[2]) {
				go = true;
			}
			else if(y1-y2<0 && y2-y1<=max[3]) {
				go = true;
			}
		}
		return go;
	}
	
	//eatChess()���ڳ���,�����㷨ΪcurrentChess��Χ�Ƿ���ڱ��Ե�����
	public void eatChess(Chess c) {
		int[] ls = getP(c); //�洢����λ�ã�ls[0]=i;ls[1]=j
		int i = ls[0];
		int j = ls[1];
		String cc = c.Color;
		//�ж��������Ƿ��������
		if(i-1>=0) {
			if(allChess[i-1][j]!=null) {
				if(i-1==0 && cc.equals(allChess[i-1][j].Color)==false) {
					allChess[i-1][j] = null;
				}
				else if(i-1>0 && allChess[i-2][j]!=null) {
					if(cc.equals(allChess[i-1][j].Color)==false && cc.equals(allChess[i-2][j].Color)) {
						allChess[i-1][j] = null;
					}
				}
			} 
		}
		if(i+1<=8) {
			if(allChess[i+1][j]!=null) {
				if(i+1==8 && cc.equals(allChess[i+1][j].Color)==false) {
					allChess[i+1][j] = null;
				}
				else if(i+1<8 && allChess[i+2][j]!=null) {
					if(cc.equals(allChess[i+1][j].Color)==false && cc.equals(allChess[i+2][j].Color)) {
						allChess[i+1][j] = null;
					}
				}
			}
		}
		if(j-1>=0) {
			if(allChess[i][j-1]!=null){
				if(j-1==0 && cc.equals(allChess[i][j-1].Color)==false) {
					allChess[i][j-1] = null;
				}
				else if(j-1>0 && allChess[i][j-2]!=null) {
					if(cc.equals(allChess[i][j-1].Color)==false && cc.equals(allChess[i][j-2].Color)) {
						allChess[i][j-1] = null;
					}
				}
			} 
		}
		if(j+1<=8) {
			if(allChess[i][j+1]!=null){
				if(j-1==0 && cc.equals(allChess[i][j+1].Color)==false) {
					allChess[i][j+1] = null;
				}
				else if(j+1<8 && allChess[i][j+2]!=null) {
					if(cc.equals(allChess[i][j+1].Color)==false && cc.equals(allChess[i][j+2].Color)) {
						allChess[i][j+1] = null;
					}
				}
			} 
		}
	}
	
	//restart()�����������¿�ʼ��Ϸ
	public void restart() {
		for(int i=0;i<=8;i++) {
			for(int j=0;j<=8;j++) {
				allChess[i][j] = null;
			}
		}
		initChess();
		repaint();
		message2 = "";
		step = 1;
	}
	
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		FI f1 = new FI();
	}

	//����ʵ�ֵĳ��󷽷������Բ������
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
