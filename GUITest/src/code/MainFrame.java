package code;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		getContentPane().setBackground(new Color(60, 179, 113));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GUI Test");
		setSize(800, 500);
		
		createMenu();
		
		setVisible(true);
		//화면 가운데 생성
		setLocationRelativeTo(null);
	}
	
	private void createMenu() {
		/* Menu를 만듭니다. */
		JMenuBar mb = new JMenuBar();
		JMenu personMenu = new JMenu("Person");
		JMenu BookMenu = new JMenu("Book");
		JMenuItem myBookMenuItem = new JMenuItem("My Book");
		JMenuItem loginMenuItem = new JMenuItem("Log-in ...");
		JMenuItem logoutMenuItem = new JMenuItem("Log-out ...");
		JMenuItem AddBookMenuItem = new JMenuItem("Add Book");
		JMenuItem BookListMenuItem = new JMenuItem("Book List");
		JMenuItem BookSearchMenuItem = new JMenuItem("Search Book");
		JMenuItem BookBorrowMenuItem = new JMenuItem("Borrow Book");
		JMenuItem BookReturnMenuItem = new JMenuItem("Return Book");
		JMenuItem BookDeleteMenuItem = new JMenuItem("Delete Book");
		personMenu.add(myBookMenuItem);
		personMenu.add(loginMenuItem);
		personMenu.add(logoutMenuItem);
		BookMenu.add(AddBookMenuItem);
		BookMenu.add(BookListMenuItem);
		BookMenu.add(BookSearchMenuItem);
		BookMenu.add(BookBorrowMenuItem);
		BookMenu.add(BookReturnMenuItem);
		BookMenu.add(BookDeleteMenuItem);
		
		JMenu memberMenu = new JMenu("Members");
		JMenuItem membersMenuItem = new JMenuItem("Members ...");
		JMenuItem SearchmembersMenuItem = new JMenuItem("Search Members");
		memberMenu.add(membersMenuItem);
		memberMenu.add(SearchmembersMenuItem);
		
		mb.add(personMenu);
		mb.add(memberMenu);
		mb.add(BookMenu);
		
		
		
		
		MenuActionListener menuListener = new MenuActionListener(this);
		membersMenuItem.addActionListener(menuListener);
		SearchmembersMenuItem.addActionListener(menuListener);
		AddBookMenuItem.addActionListener(menuListener);
		BookListMenuItem.addActionListener(menuListener);
		BookSearchMenuItem.addActionListener(menuListener);
		BookBorrowMenuItem.addActionListener(menuListener);
		BookReturnMenuItem.addActionListener(menuListener);
		BookDeleteMenuItem.addActionListener(menuListener);
		myBookMenuItem.addActionListener(menuListener);
		loginMenuItem.addActionListener(menuListener);
		logoutMenuItem.addActionListener(menuListener);
		setJMenuBar(mb);
	}
}
