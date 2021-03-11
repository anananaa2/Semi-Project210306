package client;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinDialog extends JDialog implements ActionListener {
	String imgPath = "src\\";
	ImageIcon ig = new ImageIcon(imgPath + "main.png");
	JLabel jlb_id = new JLabel("아이디");
	JTextField jtf_id = new JTextField("");
	JLabel jlb_nickname = new JLabel("닉네임");
	JPasswordField jtf_nickname = new JPasswordField("");
	JLabel jlb_pw = new JLabel("비밀번호");
	JPasswordField jtf_pw = new JPasswordField("");
	Font font = new Font("휴먼매직체", Font.BOLD, 17);
	JButton jbtn_join = new JButton("회원가입");
	JButton jbtn_exit = new JButton("닫기");
//	TalkDao tDao = new TalkDao();
	String nickName = null;// 닉네임 등록

	JoinDialog() {
		initDisplay();
	}

	// 내부 클래스로 배경 이미지 처리
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	}

	public void initDisplay() {
		jbtn_join.addActionListener(this);
		jbtn_exit.addActionListener(this);
		this.setContentPane(new MyPanel());
		this.setLayout(null);// 디폴트 - BorderLayout
		
		//아이디 입력 레이블, 텍스트박스
		jlb_id.setBounds(45, 200, 80, 40);
		jlb_id.setFont(font);
		jtf_id.setBounds(110, 200, 185, 40);
		this.add(jlb_id);
		this.add(jtf_id);
		
		//닉네임 입력 레이블, 텍스트박스
		jlb_nickname.setBounds(45, 240, 80, 40);
		jlb_nickname.setFont(font);
		jtf_nickname.setBounds(110, 240, 185, 40);
		this.add(jlb_nickname);
		this.add(jtf_nickname);
		
//		/비번 입력 레이블, 텍스트박스
		jlb_pw.setBounds(45, 280, 80, 40);
		jlb_pw.setFont(font);
		jtf_pw.setBounds(110, 280, 185, 40);
		this.add(jlb_pw);
		this.add(jtf_pw);
		
		
		jbtn_join.setBounds(45, 350, 120, 40);
		this.add(jbtn_join);
		jbtn_exit.setBounds(175, 350, 120, 40);
		this.add(jbtn_exit);
		this.setTitle("Join Page");
		this.setLocation(500, 100);
		this.setSize(350, 600);
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		LoginDialog login = new LoginDialog();
//		login.initDisplay();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (jbtn_join == obj) {
		}
		else {
			this.dispose();
		}

	}
}