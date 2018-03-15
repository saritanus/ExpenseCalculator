package com.expense.assignment.unittest;

import com.expense.bll.MatrixHandler;
import java.util.ArrayList;
import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sarita
 */
public class MatrixHandlerTest {
    
      MatrixHandler handler = null;
      ArrayList<String>  persons= new ArrayList<>();
       HashMap<String,Double> transactions = new HashMap<>();
      

	@Before
	public void initialize() {
             persons.add("Alice");
             persons.add("Bob");
             transactions.put("Alice", 100.00d);
             transactions.put("Bob",200.00);
             handler = new MatrixHandler(persons.size());
	}
        @Test
        public void setAllEdgeValuesTest1()
        {
          
          
           
           Double avgAmount = 50.00d;
          
           double[][] expectedMatrix = new double[2][2];
           expectedMatrix[0][0]=50.00d;
           expectedMatrix[0][1]=0.0d;
           expectedMatrix[1][0]=0.0d;
           expectedMatrix[1][1]=150.0d;
           
           double[][] actual  =  handler.setAllEdgeValues(persons, transactions, avgAmount);
           assertArrayEquals(expectedMatrix, actual); 
         
           
        }
        

        
        //Check if transaction list is null
         @Test(expected = java.lang.NullPointerException.class)
        public void testNullPointerException() {
           handler.setAllEdgeValues(null, transactions, 50);
         
        }
        @Test
        public void getMaxValueIndexDiagonallyTest()
        {
            ArrayList<Double> values = new ArrayList<>();
            values.add(100.05);
            values.add(200.0d);
            int expected = 1;
            int actual =handler.getMaxValueIndexDiagonally(values);            
            assertEquals(expected,actual);
            
        }
        
         @Test
        public void getMinValueIndexDiagonallyTest()
        {
            ArrayList<Double> values = new ArrayList<>();
            values.add(100.05);
            values.add(-200.0d);
            int expected = 1;
            int actual =handler.getMinValueIndexDiagonally(values);            
            assertEquals(expected,actual);
            
        }
        @After
	public void tearDown() {
		handler = null;
	}
    
}
