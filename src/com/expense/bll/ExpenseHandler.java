package com.expense.bll;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sarita
 */
public class ExpenseHandler {
    
    public static HashMap<String,Double> calculateIndividualExpenses(List<String> persons,
                                        List<String> transactions) throws NumberFormatException,NullPointerException
    {
        HashMap<String,Double> transactionMap = new HashMap<>(); 
            if(transactions.size()>0)
             {
                     for (String transaction : transactions) {
                     //split the transaction assuming pattern is never going to change
                     String[] arr = transaction.split(" ");
                     //get the name and expenses of each individual

                         if(persons.contains(arr[0]))
                         {
                            if(!transactionMap.containsKey(arr[0]))
                            {
                                transactionMap.put(arr[0], Double.parseDouble(arr[2].substring(1)));
                            }
                            else
                            {
                                 Double amountSpent = transactionMap.get(arr[0])+Double.parseDouble(arr[2].substring(1));
                                 transactionMap.replace(arr[0], amountSpent);
                            }
                         }                         
                     }

                  }
       return transactionMap;       
    }   
   
    
}
