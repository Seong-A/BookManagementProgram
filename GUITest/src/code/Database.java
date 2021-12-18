package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Database {
	private volatile static Database instance = null;
	private Connection connection = null;
	private Database() {
		initDatabase();
	}
	public static Database getInstance() {
		if(instance == null) {
			synchronized (Database.class) {
				if(instance == null)
					instance = new Database();
			}
		}
		return instance;
	}
	//NAME을 조회합니다.
	public boolean checkName(String name) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM member WHERE name='"+name+"'");
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	//NAME과 Password 확인
	public boolean checkNameAndPwd(String name, String password) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT COUNT(*) FROM member "
					+ "WHERE name='"+name+"' and pwd='"+password+"'");
			
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	public boolean insertMemberData(String name, String password) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        int r = statement.executeUpdate(
	        		"INSERT INTO member (name, pwd) "
	        		+ "values('"+name+"', '"+password+"')");
	        retValue = true;
	        
	        if(r>0) {
	        	 System.out.println("가입 성공");   
	        	 retValue=true;
	            }else{
	                System.out.println("가입 실패");
	            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retValue = false;
		}     
		return retValue;
	}
	
	public void insertBookData(String isbn, String number,String authors, String title, String publisher, String book_date, int status) {
		try {
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
			Date time = new Date();
			String time1 = format.format(time);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(

	        		"INSERT INTO Book (isbn, number,authors, title, publisher, book_date, status,regist_date) "
	        		+ "values('"+isbn+"', '" +number+"', '"+authors+"', '"+title+"', '"+publisher+"','"+book_date+"','"+status+"', '"+time1+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	private void initDatabase() {
		// create a database connection
        try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			if( !checkExistTable("member") ) {
				statement.executeUpdate(
						"CREATE TABLE member "
						+ "(id INTEGER NOT NULL, "
						+ "name STRING NOT NULL, "
						+ "pwd STRING NOT NULL, "
						+ "PRIMARY KEY(ID AUTOINCREMENT))");
			}
			if( !checkExistTable("Book") ) {
				statement.executeUpdate(
						"CREATE TABLE Book "
						+ "(id INTEGER NOT NULL, "
						+ "isbn STRING NOT NULL, "
						+ "number STRING NOT NULL, "
						+ "authors STRING NOT NULL, "
						+ "title STRING NOT NULL, "
						+ "publisher STRING NOT NULL, "
						+ "book_date STRING NOT NULL, "
						+ "status INTEGER NOT NULL, "
						+ "regist_date STRING NOT NULL, "
						+ "PRIMARY KEY(ID AUTOINCREMENT))");
			}
			if( !checkExistTable("MyBook") ) {
				statement.executeUpdate(
						"CREATE TABLE MyBook "
						+ "(isbn STRING NOT NULL, "
						+ "authors STRING NOT NULL, "
						+ "title STRING NOT NULL, "
						+ "publisher STRING NOT NULL)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkExistTable(String tableName) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE name='"+tableName+"'");
			if(rs.getInt(1) == 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	public void insertJTable(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM member");
			while(rs.next())
			{
				String []record = new String[3];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("name");
				record[2] = rs.getString("pwd");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertJTable2(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Book");
			while(rs.next())
			{
				String []record = new String[9];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("isbn");
				record[2] = rs.getString("number");
				record[3] = rs.getString("authors");
				record[4] = rs.getString("title");
				record[5] = rs.getString("publisher");
				record[6] = rs.getString("book_date");
				if (Integer.parseInt(rs.getString("status")) == 0) {
					record[7] = "대출가능";
					
				}
				else {
					record[7] = "대출중";

				}
				record[8] = rs.getString("regist_date");

				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertJTable3(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM myBook");
			while(rs.next())
			{
				String []record = new String[4];
				record[0] = rs.getString("isbn");
				record[1] = rs.getString("authors");
				record[2] = rs.getString("title");
				record[3] = rs.getString("publisher");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public boolean bookSearch(String authors, String title) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Book where authors like '%"+authors+"%' and title like '%"+title+"%'");
			while(rs.next())
			{
				String []record = new String[9];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("isbn");
				record[2] = rs.getString("number");
				record[3] = rs.getString("authors");
				record[4] = rs.getString("title");
				record[5] = rs.getString("publisher");
				record[6] = rs.getString("book_date");
				if (Integer.parseInt(rs.getString("status")) == 0) {
					record[7] = "대출가능";
					
				}
				else {
					record[7] = "대출중";

				}
				record[8] = rs.getString("regist_date");

				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}

	public boolean bookSearchAuthor(String authors) {
	
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Book WHERE authors='"+authors+"'");
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	public boolean bookSearchTitle(String title) {
		
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Book WHERE title='"+title+"'");
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	public boolean insertBookData(String authors, String title) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(
	        		"INSERT INTO Book (authors, title) "
	        		+ "values('"+authors+"', '"+title+"')");
	        retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retValue = false;
		}     
		return retValue;
	}
	public void BorrowBook(String title) {
		try {
			Statement statement = connection.createStatement();
			String authors = "", publisher = "", status = "", isbn= "" ;
			ResultSet rs = statement.executeQuery("SELECT * FROM Book WHERE title = '"+title+"'");
			while(rs.next())
			{
				isbn = rs.getString("isbn");
				authors = rs.getString("authors");
				title = rs.getString("title");
				publisher = rs.getString("publisher");
				status = rs.getString("status");


							}
			if (status.equals("1")) {
				JOptionPane.showMessageDialog(null, "대출 불가");
				return;
			}
	        statement.executeUpdate("INSERT INTO myBook (isbn, authors, title, publisher) "
	        		+ "values('"+isbn+"','"+authors+"', '"+title+"', '"+publisher+"')");
	        statement.executeUpdate("UPDATE Book SET status = 1 WHERE title = '"+title+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	public void ReturnBook(String title) {
		try {
			Statement statement = connection.createStatement();
			
	        statement.executeUpdate("DELETE FROM myBook WHERE title = '"+title+"'");
	        statement.executeUpdate("UPDATE Book SET status = 0 WHERE title = '"+title+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	public void DeleteBook(String title) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  
	        statement.executeUpdate("DELETE FROM Book WHERE title = '"+title+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}

	
	
}
