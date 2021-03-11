package client;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import test.LoginForm.MyPanel;

public class ChatFrame extends JFrame implements ActionListener {
	String imgPath = "src\\image\\";
	ImageIcon ig = new ImageIcon(imgPath + "BONOBONO.jpg");
	Font font = new Font("휴먼굴림체", Font.BOLD, 17);
	JButton jbtn_login = new JButton("로그인");
	JButton jbtn_join = new JButton("회원가입");
	JButton jbtn_exit = new JButton("나가기");

	LoginDialog ld = null;
	JoinDialog jd = null;

	//////////////////////////////////// [[JPanel]]////////////////////////////////////////////

	JPanel displayPanel = new JPanel();
	JPanel displayPanel2 = new JPanel();
	JPanel inputPanel = new JPanel();

	JTextArea display;
	JTextArea display2;
	JTextField input;

	//////////////////////////////////// [[JPanel]]////////////////////////////////////////////
	ChatFrame() {
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	}

	public void initDisplay() {
		jbtn_login.addActionListener(this);
		jbtn_join.addActionListener(this);
		jbtn_exit.addActionListener(this);

		//////////////////////////////////////////////////////////
		displayPanel.setLayout(new FlowLayout());
		display = new JTextArea(19, 40);
		Font displayFont = new Font("Serif", Font.BOLD, 20);
		display.setFont(displayFont);
		display.setEditable(false);
		displayPanel.setVisible(true);
		this.add(displayPanel);
		//////////////////////////////////////////////////////////
		this.setContentPane(new MyPanel());
		this.setLayout(null);// 디폴트 - BorderLayout
//      jlb_id.setBounds(45, 200, 80, 40);//id문자열
//      jlb_id.setFont(font);
//      jtf_id.setBounds(110, 200, 185, 40);   //id입력란
//      this.add(jlb_id);
//      this.add(jtf_id);
//      jlb_pw.setBounds(45, 240, 80, 40);//pw 문자열
//      jlb_pw.setFont(font);
//      jtf_pw.setBounds(110, 240, 185, 40);   //pw 입력란
//      this.add(jlb_pw);
//      this.add(jtf_pw);
		jbtn_login.setBounds(600, 360, 150, 50);
		this.add(jbtn_login);
		jbtn_join.setBounds(600, 300, 150, 50);
		this.add(jbtn_join);
		jbtn_exit.setBounds(600, 480, 150, 50);
		this.add(jbtn_exit);
		this.setTitle("채팅");
		this.setLocation(500, 100);
		this.setSize(800, 600);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		ChatFrame tst = new ChatFrame();
		tst.initDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtn_login) {
			ld = new LoginDialog();

		} // end of login if
		if (obj == jbtn_join) {
			jd = new JoinDialog();
		}
		if (obj == jbtn_exit) {
			System.exit(0);
		}
	}
}