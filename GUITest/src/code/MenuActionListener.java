package code;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MenuActionListener implements ActionListener {
	

	private MainFrame mainWindow = null;
	private JTable table = null;
	private JTable table2 = null;
	private JScrollPane scroll = null;
	private JScrollPane scroll2 = null;
	JPanel myPanel = new JPanel();
	JPanel myPanel1 = new JPanel();
	JPanel myPanel2 = new JPanel();
	JPanel myPanel3 = new JPanel();

	private JTextField borrow_input = new JTextField(10);
	private JTextField book_isbn = new JTextField(10);
	private JTextField book_number = new JTextField(10);
	private JTextField book_authors = new JTextField(10);
	private JTextField book_title = new JTextField(10);
	private JTextField book_publisher = new JTextField(10);
	private JTextField book_date = new JTextField(10);
	private JTextField book_regist_date = new JTextField(10);
	private JTextField member_name = new JTextField(10);
	private Connection connection;

	
	public MenuActionListener(MainFrame mainWindow) {
		this.mainWindow = mainWindow;
		createTable();
	}
	
	
	public int checkDateField(String text) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(text);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Members ...")) {
			if(table != null) {
				mainWindow.remove(scroll);
			}
			createTable();
			System.out.println("members... ok");
			
			DefaultTableModel model= (DefaultTableModel)table.getModel();
			model.setRowCount(0);
			Database.getInstance().insertJTable(model);
			mainWindow.revalidate();
			
		}
		if(e.getActionCommand().equals("Search Members")) {
			System.out.println("Search Members");
			memberCheck();
			
			if(member_name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The name is empty");
				member_name.requestFocus();
			}else
			{
				
				/* 데이터베이스에 같은 book이 있는지 체크합니다. */
				if( Database.getInstance().checkName(
					/* 데이터베이스에 값을 입력합니다. */
						member_name.getText())) {
					JOptionPane.showMessageDialog(null, "The member exists");
					
				}
				else
				{
			
				/* 같은 값이 없다면 경고 메시지를 출력합니다 */
				JOptionPane.showMessageDialog(null, "The member is invalid");
				member_name.requestFocus();
			}
		}
		}

			
		
		if(e.getActionCommand().equals("Book List")) {
			System.out.println("Book List... ok");
            if (table != null) {
                mainWindow.remove(scroll);
            }
			createTable2();
			DefaultTableModel model= (DefaultTableModel)table2.getModel();
			model.setRowCount(0);
			Database.getInstance().insertJTable2(model);
			mainWindow.revalidate();
			mainWindow.repaint();
			
		}
		if(e.getActionCommand().equals("Add Book")) {
			System.out.println("Add Book ...");
			createInput();
			dispose();
	
			}




	
		if(e.getActionCommand().equals("Search Book")) {
			System.out.println("Search Book");
			createSearch();
		
			if(book_authors.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "The authors is empty");
					book_authors.requestFocus();
				}
				else if(book_title.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "The title is empty");
					book_title.requestFocus();
				}
				else
				{
					
					/* 데이터베이스에 같은 book이 있는지 체크합니다. */
					if( Database.getInstance().bookSearch(
							/* 데이터베이스에 값을 입력합니다. */
								book_authors.getText(),
								book_title.getText())) {
						JOptionPane.showMessageDialog(null, "The book exists");
						
					}
					else
					{
				
					/* 같은 값이 없다면 경고 메시지를 출력합니다 */
					JOptionPane.showMessageDialog(null, "The book is invalid");
					book_authors.requestFocus();
				}
			}
			}
		
		
		if(e.getActionCommand().equals("Borrow Book")) {
			System.out.println("Borrow Book");
			processBook();
			if(book_title.getText().equals("")) {
				JOptionPane.showMessageDialog(null,myPanel, "제목을 입력해주세요",JOptionPane.OK_CANCEL_OPTION);
				JOptionPane.showMessageDialog(null, "The title is empty");
				return;
			}

			
	
			
		}
		
	
		if(e.getActionCommand().equals("Return Book")) {
			ReturnBook();
			System.out.println("Return Book");
			createTable3();
			DefaultTableModel model= (DefaultTableModel)table2.getModel();
			Database.getInstance().insertJTable3(model);
			mainWindow.revalidate();
			mainWindow.repaint();
		}
		if(e.getActionCommand().equals("Delete Book")) {
			DeleteBook();
			System.out.println("Delete Book");
			createTable2();
			DefaultTableModel model= (DefaultTableModel)table2.getModel();
			Database.getInstance().insertJTable2(model);
			mainWindow.revalidate();
			mainWindow.repaint();
		}
		
		if(e.getActionCommand().equals("Log-in ...")) {
			mainWindow.dispose();
			new LoginWindows();
		}
		if(e.getActionCommand().equals("Log-out ...")) {
			int dialogResult = JOptionPane.showConfirmDialog (null,
					"Would you like to Logout?",
					"Warning",
					JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
					 // code here
						mainWindow.dispose();
						new LoginWindows();
					}
		}
		if(e.getActionCommand().equals("My Book")) {
			createTable3();
			DefaultTableModel model= (DefaultTableModel)table2.getModel();
			Database.getInstance().insertJTable3(model);
			mainWindow.revalidate();
			mainWindow.repaint();
		}
	}
	
	



	private void createTable3() {
		String []header = {"ISBN","책의 저자", "책의 제목","출판사"};
		DefaultTableModel model=new DefaultTableModel(header,0);
		table2 = new JTable(model);
		if(scroll2 != null) {
			mainWindow.remove(scroll2);
		}
		if(scroll != null) {
			mainWindow.remove(scroll);
		}
		scroll2 = new JScrollPane(table2);
		mainWindow.add(scroll2);     
		}
        
	
	
	private void createTable() {
		String []header = {"id", "name", "password"};
		DefaultTableModel model=new DefaultTableModel(header,0);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		mainWindow.add(scroll);
	}
	
	private void memberCheck() {
		myPanel1.add(new JLabel("name : "));
		myPanel1.add(member_name);
		myPanel1.add(Box.createHorizontalStrut(15)); 
		
		int result =JOptionPane.showConfirmDialog(null, myPanel1,"검색할 회원 이름을 입력해주세요 ", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {

			dispose();
		}
		
	}
	private void createTable2() {
		String []header = {"id", "ISBN", "등록번호","책의 저자","책의 제목","출판사","출판일","대출 상태","등록일"};
		DefaultTableModel model=new DefaultTableModel(header,0);
		table2 = new JTable(model);
		scroll2 = new JScrollPane(table2);
		
		
		mainWindow.remove(scroll);
		mainWindow.add(scroll2);
		mainWindow.repaint();
		model.setRowCount(0);
        
	}
	public void createInput() {
		
			myPanel.add(new JLabel("ISBN: "));
			myPanel.add(book_isbn);
			myPanel.add(Box.createHorizontalStrut(15)); 
			myPanel.add(new JLabel("등록번호: "));
			myPanel.add(book_number);
			myPanel.add(Box.createHorizontalStrut(15)); 
			myPanel.add(new JLabel("책의 저자: "));
			myPanel.add(book_authors);
			myPanel.add(Box.createHorizontalStrut(15)); 
			myPanel.add(new JLabel("책의 제목: "));
			myPanel.add(book_title);
			myPanel.add(Box.createHorizontalStrut(15));
			myPanel.add(new JLabel("출판사: "));
			myPanel.add(book_publisher);
			myPanel.add(Box.createHorizontalStrut(15)); 
			myPanel.add(new JLabel("출판일: "));
			myPanel.add(book_date);
			
			int result =JOptionPane.showConfirmDialog(null, myPanel,"책 정보를 입력해주세요",
					JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION) {
	        	Database.getInstance().insertBookData(
	        			book_isbn.getText(), 
	        			book_number.getText(), 
	        			book_authors.getText(), 
	        			book_title.getText(), 
	        			book_publisher.getText(),
	        			book_date.getText(), 
	        			0);
	        	dispose();
			}
	

	        			
	        
			}
	

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}



	public void createSearch() {
		myPanel2.add(new JLabel("책의 저자: "));
		myPanel2.add(book_authors);
		myPanel2.add(Box.createHorizontalStrut(15)); 
		myPanel2.add(new JLabel("책의 제목: "));
		myPanel2.add(book_title);
		myPanel2.add(Box.createHorizontalStrut(15));
		int result =JOptionPane.showConfirmDialog(null, myPanel2,"검색할 책을 입력해주세요 ",JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			dispose();
		}
	
	}
	
	public void processBook() {
			myPanel3.add(new JLabel("책의 제목: "));
			myPanel3.add(book_title);
			myPanel3.add(Box.createHorizontalStrut(15));
			

	int result =JOptionPane.showConfirmDialog(null, myPanel3,"검색할 책", JOptionPane.OK_CANCEL_OPTION);
	
	if(result == JOptionPane.OK_OPTION) {
		Database.getInstance().BorrowBook(book_title.getText());
				}
			else
			{
		
			/* 같은 값이 없다면 경고 메시지를 출력합니다 */
			JOptionPane.showMessageDialog(null, "The book is invalid");
			book_title.requestFocus();
		}
		dispose();
	}
	public void ReturnBook() {
		myPanel3.add(new JLabel("책의 제목: "));
		myPanel3.add(book_title);
		myPanel3.add(Box.createHorizontalStrut(15));
		

	int result =JOptionPane.showConfirmDialog(null, myPanel3,"검색할 책", JOptionPane.OK_CANCEL_OPTION);
	
	if(result == JOptionPane.OK_OPTION) {
		Database.getInstance().ReturnBook(book_title.getText());
				}
			else
			{
		
			/* 같은 값이 없다면 경고 메시지를 출력합니다 */
			JOptionPane.showMessageDialog(null, "The book is invalid");
			book_title.requestFocus();
		}
		dispose();
	}
	private void DeleteBook() {
		myPanel3.add(new JLabel("책의 제목: "));
		myPanel3.add(book_title);
		myPanel3.add(Box.createHorizontalStrut(15));
		
	int result =JOptionPane.showConfirmDialog(null, myPanel3,"검색할 책", JOptionPane.OK_CANCEL_OPTION);
	
	if(result == JOptionPane.OK_OPTION) {
		Database.getInstance().DeleteBook(book_title.getText());
				}
			else
			{
		
			/* 같은 값이 없다면 경고 메시지를 출력합니다 */
			JOptionPane.showMessageDialog(null, "The book is invalid");
			book_title.requestFocus();
		}
		dispose();
	}

	}
	
	

