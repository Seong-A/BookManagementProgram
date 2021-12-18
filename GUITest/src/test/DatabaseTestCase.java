package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import code.*;

class DatabaseTestCase {

	@Test
	void checkNameTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkName("qwer");
		assertEquals(true,ret);
	}
	
	@Test
	void checkNameInvalidTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkName("abcx");
		assertEquals(false,ret);
	}
	@Test
	void checkNameAndPwdTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkNameAndPwd("qwer","qwer");
		assertEquals(true,ret);
	}
	@Test
	void checkNameAndPwdInvalidTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkNameAndPwd("abcx","abcx");
		assertEquals(false,ret);
	}
	@Test
	void insertMemberDataTest() {
		Database db = Database.getInstance();
		boolean ret = db.insertMemberData("qwer","qwer");
		assertEquals(true,ret);
	}
	@Test
	void checkExistTableTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkExistTable("member");
		assertEquals(true,ret);
	}
	@Test
	void bookSearchTest() {
		Database db = Database.getInstance();
		boolean ret = db.bookSearch("손석희","장면들");
		assertEquals(true,ret);
	}
	@Test
	void bookSearchAuthorTest() {
		Database db = Database.getInstance();
		boolean ret = db.bookSearchAuthor("오은영");
		assertEquals(true,ret);
	}
	@Test
	void bookSearchTitleTest() {
		Database db = Database.getInstance();
		boolean ret = db.bookSearchTitle("오은영의 화해");
		assertEquals(true,ret);
	}

	
}

