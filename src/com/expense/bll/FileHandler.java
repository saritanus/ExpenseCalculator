package com.expense.bll;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    
     public static List<String> readFile(String fileName) throws FileNotFoundException, IOException
    {
        List<String> list = new ArrayList<>();       
        
        if(fileName == null) throw new NullPointerException();
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
            
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
        //Get unique names from the file
            if(!list.contains(currentLine))
            {
                list.add(currentLine.trim());
            }
        }
        	
        return list;
    }   
    
}
