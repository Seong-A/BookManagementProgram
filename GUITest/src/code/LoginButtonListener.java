package code;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginButtonListener implements ActionListener{
	
	
	private LoginWindows window = null;
	public LoginButtonListener(LoginWindows window) {
		this.window = window;
	}
	private void closeWindow() {
		window.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)){
			System.out.println("ok");
			/*
			 * 1. name, password�� ���� �ԷµǾ����� Ȯ���մϴ�.
			 * 1-1. ���� ���ٸ� ��� �޽����� ����մϴ�.
			 * 2. �����ͺ��̽����� member ���̺��� ��ȸ�Ͽ� �Էµ�
			 * name�� password�� ���� ���� �ִ� �� Ȯ���մϴ�.
			 * 2-1. ���� ���� �ִٸ� MainFrame ȭ���� ���ϴ�.
			 * 2-2. ���� ���� ���ٸ� ��� �޽����� ����մϴ�.
			 * */
			/* 1. name, password�� ���� �ԷµǾ����� Ȯ���մϴ�. */
			if(window.getNameField().getText().length() == 0) {
				/* 1-1. ���� ���ٸ� ��� �޽����� ����մϴ�. */
				JOptionPane.showMessageDialog(null, "The name is empty");
				window.getNameField().requestFocus();
			}
			else if(window.getPassField().getText().length() == 0) {
				/* 1-1. ���� ���ٸ� ��� �޽����� ����մϴ�. */
				JOptionPane.showMessageDialog(null, "The password is empty");
				window.getPassField().requestFocus();
			}
			else
			{
				/* 2. �����ͺ��̽����� member ���̺��� ��ȸ�Ͽ� �Էµ�
				 * name�� password�� ���� ���� �ִ� �� Ȯ���մϴ�. */
				if(Database.getInstance().checkNameAndPwd(
						window.getNameField().getText(), 
						window.getPassField().getText()) ) {
				
					/*2-1. ���� ���� �ִٸ� MainFrame ȭ���� ���ϴ�.*/
					/* ��� ������ �����ϸ� �Ʒ��� �����մϴ�.*/
					new MainFrame();
					/* �α��� ������â�� �ݽ��ϴ�. */
					closeWindow();
				}
				else {
					/* 2-2. ���� ���� ���ٸ� ��� �޽����� ����մϴ� */
					JOptionPane.showMessageDialog(null, "The name or password is invalid");
					window.getNameField().requestFocus();
				}
			}
		}
		else if(e.getActionCommand().equals(Const.JOIN)) {
			MemberJoinDialog dialog = new MemberJoinDialog();
			dialog.show();
			System.out.println("join");
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			int dialogResult = JOptionPane.showConfirmDialog (null, 
					"Would you like to cancel?",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				closeWindow();
				System.out.println("cancel");
			}
		}
	}

}
