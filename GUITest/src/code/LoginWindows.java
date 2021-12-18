package code;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private LoginButtonListener buttonListener = null;
	
	/* name 텍스트 필드 생성*/
	private JTextField nameField = null;
	/* password 텍스트 필드 생성*/
	private JPasswordField passField = null;
	/* name과 password getter 생성*/
	public JTextField getNameField() {
		return nameField;
	}
	public JPasswordField getPassField() {
		return passField;
	}
	
	public LoginWindows() {
		getContentPane().setBackground(new Color(60, 179, 113));
		buttonListener = new LoginButtonListener(this);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(new JLabel("name        "));
		/* 수정 */
		nameField = new JTextField(20); 
		getContentPane().add(nameField);
		getContentPane().add(new JLabel("password"));
		/* 수정 */
		passField = new JPasswordField(20);
		getContentPane().add(passField);
		
		JButton okButton = new JButton(Const.OK);
		okButton.addActionListener(buttonListener);
		getContentPane().add(okButton);
		JButton cancelButton = new JButton(Const.CANCEL);
		cancelButton.addActionListener(buttonListener);
		getContentPane().add(cancelButton);
		JButton joinButton = new JButton(Const.JOIN);
		joinButton.addActionListener(buttonListener);
		getContentPane().add(joinButton);
		
		setLocationRelativeTo(null);
		setSize(300, 150);
		setVisible(true);
	}
}

