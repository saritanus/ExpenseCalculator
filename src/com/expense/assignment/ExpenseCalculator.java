package com.expense.assignment;

import com.expense.bll.ExpenseHandler;
import com.expense.bll.FileHandler;
import com.expense.bll.MatrixHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseCalculator {  

    static Double totalSpentAmount=0.0d;
    static Double avgSpentAmount=0.0d;    
    
    public static void main(String[] args) throws IOException {
        
        try{
        //Get the input files from the user     
         Scanner sc = new Scanner(System.in);      
         System.out.println("Please enter person file name and location, e.g. D:\\Maverickassignment\\persons.txt:");
         String personFileName = sc.nextLine();
                 
         System.out.println("Please enter expense transaction file name and location: e.g. D:\\Maverickassignment\\expenses.txt");
         String expenseFileName=sc.nextLine();
         
    
         //Read both the files
         List<String> listofPerson=FileHandler.readFile(personFileName);
         List<String> listofTranscations =FileHandler.readFile(expenseFileName);
         
         //Calculate individual expenses
         HashMap<String,Double> transactions = ExpenseHandler.calculateIndividualExpenses(listofPerson,listofTranscations);
         
         //Calculate total amount spent in the whole month
         for(Map.Entry<String, Double> entry : transactions.entrySet()) {
             totalSpentAmount = totalSpentAmount+entry.getValue();
         }
         
         //Calculate average amount contirbution of each individual for the month.
         avgSpentAmount = totalSpentAmount/listofPerson.size();
         
         //Create a adjacency matrix so that we can store values of each individual and 
         //calculate  the amount to settle among themselves
         MatrixHandler handler = new MatrixHandler(listofPerson.size());
         double matrix[][] =handler.setAllEdgeValues(listofPerson, transactions, avgSpentAmount);
         
         
       
            ArrayList<Double> diagonalValues = handler.getDiagonalValuesofMatrx(matrix);            
            matrix = handler.setEdgeValue(handler.getMaxValueIndexDiagonally(diagonalValues), handler.getMinValueIndexDiagonally(diagonalValues), matrix);
            for (int i = 0; i < listofPerson.size(); i++) 
            {
                for (int j = 0; j < listofPerson.size(); j++) 
                {
                    if(matrix[i][j]!=0.0d)
                    {   
                        System.out.println(String.format("%s pays $%s to %s", 
                        listofPerson.get(i),String.format("%.02f",matrix[i][j]),listofPerson.get(j)));
                    }
                }
               
            }
    }
        catch(Exception e)
        {
            System.out.println("Please re run the program, enter a valid file name");
        }
    } 
   
}
    
