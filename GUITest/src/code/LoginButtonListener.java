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
			 * 1. name, password에 값이 입력되었는지 확인합니다.
			 * 1-1. 만약 없다면 경고 메시지를 출력합니다.
			 * 2. 데이터베이스에서 member 테이블을 조회하여 입력된
			 * name과 password가 같은 같이 있는 지 확인합니다.
			 * 2-1. 같은 값이 있다면 MainFrame 화면을 띄웁니다.
			 * 2-2. 같은 값이 없다면 경고 메시지를 출력합니다.
			 * */
			/* 1. name, password에 값이 입력되었는지 확인합니다. */
			if(window.getNameField().getText().length() == 0) {
				/* 1-1. 만약 없다면 경고 메시지를 출력합니다. */
				JOptionPane.showMessageDialog(null, "The name is empty");
				window.getNameField().requestFocus();
			}
			else if(window.getPassField().getText().length() == 0) {
				/* 1-1. 만약 없다면 경고 메시지를 출력합니다. */
				JOptionPane.showMessageDialog(null, "The password is empty");
				window.getPassField().requestFocus();
			}
			else
			{
				/* 2. 데이터베이스에서 member 테이블을 조회하여 입력된
				 * name과 password가 같은 같이 있는 지 확인합니다. */
				if(Database.getInstance().checkNameAndPwd(
						window.getNameField().getText(), 
						window.getPassField().getText()) ) {
				
					/*2-1. 같은 값이 있다면 MainFrame 화면을 띄웁니다.*/
					/* 모든 조건이 성립하면 아래를 실행합니다.*/
					new MainFrame();
					/* 로그인 윈도우창은 닫습니다. */
					closeWindow();
				}
				else {
					/* 2-2. 같은 값이 없다면 경고 메시지를 출력합니다 */
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
