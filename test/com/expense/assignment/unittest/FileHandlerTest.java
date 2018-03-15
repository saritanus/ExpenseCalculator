package com.expense.assignment.unittest;

import com.expense.bll.FileHandler;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileHandlerTest {

	FileHandler handler = null;

	@Before
	public void initialize() {
		handler = new FileHandler();
	}

	@Test
	public void readFromFile() throws FileNotFoundException, IOException {
		List<String> uniqueList = handler.readFile("D:\\Mavericks\\people.txt");
		assertNotEquals(uniqueList.size(), 0);
	}

	@Test(expected = FileNotFoundException.class)
	public void readException() throws FileNotFoundException, IOException {
		handler.readFile("D:\\Mavericks\\people2.txt");
	}

	@After
	public void tearDown() {
		handler = null;
	}
}
