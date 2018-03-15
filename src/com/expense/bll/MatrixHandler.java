package com.expense.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**This class store individual amount spent in a matrix and then settles the amount.
 *
 * @author Sarita
 *  */
public class MatrixHandler {
 
    private final int vertices;
    private double[][] expenseMatrix;
 
    public MatrixHandler(int size) 
    {
        vertices = size;
        expenseMatrix = new double[vertices][vertices];
    }

   
 
    public  double[][]  setAllEdgeValues(List<String> persons,HashMap<String,Double> transactions, double avgAmount) 
            throws ArrayIndexOutOfBoundsException,NullPointerException
    {
          int size = persons.size(); 
           for(int i=0;i<size;i++)
           {
               for(int j=0;j<persons.size();j++)
               {
                   if(i==j)
                       if (transactions.get(persons.get(i))!= null) expenseMatrix[i][j] 
                               = transactions.get(persons.get(i))-avgAmount; else expenseMatrix[i][j]=0.0d-avgAmount;
                   else
                      expenseMatrix[i][j]=0.0d;
               }
           }
              
        return expenseMatrix;
    }
 
    public double getEdgeValue(int to, int from) 
    {
        try 
        {
            return expenseMatrix[to][from];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
        return 0;
    }
    
    public int getMaxValueIndexDiagonally( ArrayList<Double> diagonalValues)
    {
        return diagonalValues.indexOf(Collections.max(diagonalValues,null));
    }
    
     public int getMinValueIndexDiagonally( ArrayList<Double> diagonalValues)
    {
       
        return diagonalValues.indexOf(Collections.min(diagonalValues));
    }
     
    public double[][] setEdgeValue(int maxIndex, int minIndex, double [][] matrix) 
    { 
       
        try 
        {
            double maxAmount = matrix[maxIndex][maxIndex];
            double minAmount = matrix[minIndex][minIndex];
            ArrayList<Double> individualRemainingAmount = new ArrayList<>();
          
            while(!checkDiagonalValues(matrix))
            {
                //Checks if absolute value of minimum amout is same or lesse than the amount of max, 
                //then the below logic will be applied
                if(Math.abs(maxAmount)<=Math.abs(minAmount))
                {
                    matrix[minIndex][maxIndex] = maxAmount;
                    matrix[maxIndex][maxIndex]=0.0d;
                    if(minAmount<0) matrix[minIndex][minIndex]=maxAmount + minAmount; 
                    else matrix[minIndex][minIndex] = maxAmount - minAmount;
                }
                 //Checks if absolute value of minimum amout is more than the amount of max, 
                //then the below logic will be applied
                else
                {
                    matrix[minIndex][maxIndex] = Math.abs(minAmount);
                    if(minAmount<0) matrix[maxIndex][maxIndex]=maxAmount + minAmount; 
                    else matrix[maxIndex][maxIndex] = maxAmount - minAmount;
                    matrix[minIndex][minIndex]=0.0d;
                }
                
                individualRemainingAmount = getDiagonalValuesofMatrx(matrix);
                maxIndex = getMaxValueIndexDiagonally(individualRemainingAmount);
                minIndex  =getMinValueIndexDiagonally(individualRemainingAmount);
                
                maxAmount = matrix[maxIndex][maxIndex];
                minAmount = matrix[minIndex][minIndex];
                setEdgeValue(maxIndex,minIndex,matrix);
         
            }
          
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
        
        return matrix;
    }
    
    public ArrayList<Double> getDiagonalValuesofMatrx(double[][] matrix)
    {
         ArrayList<Double> diagonalValues = new ArrayList<>();
        try{
            for(int i=0; i<expenseMatrix.length;i++)
            {
                diagonalValues.add(expenseMatrix[i][i]);
              
            }
        } catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
        return diagonalValues;
    }
    
    private boolean checkDiagonalValues(double[][] expenses)
    {
        boolean check = false;
        if(expenses==null) throw  new NullPointerException();
        
        ArrayList<Double> list = getDiagonalValuesofMatrx(expenses);
        for(double expense:list)
        {
            if(expense==0.0d)
              check=true;
            else 
            {
                check=false;
                return check;
            }
            
        }
        
        return check;
    }
    
    
 }

