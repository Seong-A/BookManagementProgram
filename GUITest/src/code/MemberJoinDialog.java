package code;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

public class MemberJoinDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameTextField = null;
	private JPasswordField pwdTextField = null;
	public MemberJoinDialog() {
		setBackground(Color.WHITE);
		getContentPane().setBackground(new Color(60, 179, 113));
		setTitle("Join Member");
		setModal(true);
		setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(60, 179, 113));
        panel1.setLayout(new BorderLayout());
        getContentPane().add(panel1);
        
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(new Color(60, 179, 113));
        panel1.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());
        panelCenter.add(new JLabel("name        "));
        nameTextField = new JTextField(20);
        panelCenter.add(nameTextField);
        panelCenter.add(new JLabel("password"));
        pwdTextField = new JPasswordField(20);
        panelCenter.add(pwdTextField);
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(60, 179, 113));
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(300, 200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); //다이얼로그 제거
            }
        });
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)) {
			/* 1. name과 password에 값이 있는지 체크합니다. */
			if(nameTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The name is empty");
				nameTextField.requestFocus();
			}
			else if(pwdTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The password is empty");
				pwdTextField.requestFocus();
			}
			else
			{
				/* 데이터베이스에 같은 name이 있는지 체크합니다. */
				if( !Database.getInstance().checkName(nameTextField.getText()) ) {
					/* 데이터베이스에 값을 입력합니다. */
					Database.getInstance().insertMemberData(
							nameTextField.getText(),
							pwdTextField.getText());
					
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The name exists");
					//만약 가입되어 있다면 메시지를 출력하고 name과 password 필드를 비워줍니다.
					nameTextField.setText("");
					pwdTextField.setText("");
					//name에 focus를 표시합니다.
					nameTextField.requestFocus();
				}
			}
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			dispose();
		}
	}

}
