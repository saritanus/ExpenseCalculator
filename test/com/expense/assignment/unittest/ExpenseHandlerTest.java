package com.expense.assignment.unittest;

import com.expense.bll.ExpenseHandler;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sarita
 */
public class ExpenseHandlerTest {
    ExpenseHandler handler = null;

	@Before
	public void initialize() {
		handler = new ExpenseHandler();
	}
       
	@Test
         //check if it returns expected value if trhere is no transaction for one person
	public void calculateIndividualExpenses1() {
	   ArrayList<String> persons = new ArrayList<>();
           persons.add("Alice");
           persons.add("Bob");
           persons.add("Claire");
           persons.add("David");
           
           ArrayList<String> transactions = new ArrayList<>();
           transactions.add("Claire paid $100.00 for phone bill.");
           transactions.add("Bob paid $200.00 for petrol.");
           transactions.add("David paid $300.00 for groceries.");
           transactions.add("David paid $400.00 for breakfast.");
           
           HashMap<String,Double> expectedExpenses = new HashMap<>(); 
           expectedExpenses.put("Bob", 200.0d);
           expectedExpenses.put("Claire", 100.0d);
           expectedExpenses.put("David", 700.0d);

           //check if it returns expected value
           HashMap<String,Double> individualExpenses =  handler.calculateIndividualExpenses(persons, transactions);
           if(expectedExpenses.size()==individualExpenses.size())
               assertTrue(expectedExpenses.equals(individualExpenses));
           
           //check if an extra name is given on expense file
           //its a name which is not in person file name. It should be ignored
           individualExpenses =  handler.calculateIndividualExpenses(persons, transactions);
           assertTrue(expectedExpenses.equals(individualExpenses));
           }
        
        @Test
         //check if an extra name is given on expense file
         //its a name which is not in person file name. It should be ignored
          
	public void calculateIndividualExpenses2() {
	   ArrayList<String> persons = new ArrayList<>();
           persons.add("Alice");
           persons.add("Bob");
           persons.add("Claire");
           persons.add("David");
           
           ArrayList<String> transactions = new ArrayList<>();
           transactions.add("Claire paid $100.00 for phone bill.");
           transactions.add("Bob paid $200.00 for petrol.");
           transactions.add("David paid $300.00 for groceries.");
           transactions.add("David paid $400.00 for breakfast.");
           transactions.add("Edward paid $400.00 for breakfast.");         
           
           HashMap<String,Double> expectedExpenses = new HashMap<>(); 
           expectedExpenses.put("Bob", 200.0d);
           expectedExpenses.put("Claire", 100.0d);
           expectedExpenses.put("David", 700.0d);

        
            HashMap<String,Double> individualExpenses  =  handler.calculateIndividualExpenses(persons, transactions);
            assertTrue(expectedExpenses.equals(individualExpenses));
           }

        
        @Test
        //check for empty values 
	public void calculateIndividualExpenses3() {
	   ArrayList<String> persons = new ArrayList<>();
          
           
           ArrayList<String> transactions = new ArrayList<>();
           transactions.add("Claire paid $100.00 for phone bill.");
           transactions.add("Bob paid $200.00 for petrol.");
           transactions.add("David paid $300.00 for groceries.");
           transactions.add("David paid $400.00 for breakfast.");
           transactions.add("Edward paid $400.00 for breakfast.");         
           
          
            HashMap<String,Double> individualExpenses  =  handler.calculateIndividualExpenses(persons, transactions);
            assertEquals(individualExpenses.size(),0);
          }
        //check if number format is wrong
        @Test(expected = java.lang.NumberFormatException.class)
        public void testNumberFormatException () {
          ArrayList<String> persons = new ArrayList<>();
           persons.add("Alice");        
           
           ArrayList<String> transactions = new ArrayList<>();
           transactions.add("Alice paid $3y0.00 for groceries.");
            handler.calculateIndividualExpenses(persons, transactions);
         
        }
        //Check if persons list is null
         @Test(expected = java.lang.NullPointerException.class)
        public void testNullPointerException1() {
         
           ArrayList<String> transactions = new ArrayList<>();
           transactions.add("Alice paid $3y0.00 for groceries.");
            handler.calculateIndividualExpenses(null, transactions);
         
        }
        
          //Check if transaction list is null
         @Test(expected = java.lang.NullPointerException.class)
        public void testNullPointerException2() {
              ArrayList<String> persons = new ArrayList<>();
           persons.add("Alice");       
           handler.calculateIndividualExpenses(null, null);
         
        }
        
        
	@After
	public void tearDown() {
		handler = null;
	}
}
