package brickBreaker;

import java.io.*; 
import com.opencsv.CSVWriter;

public class Data {
	public int score=0;
	public String x="";
	
	
	public void call() 
	{ 
		
		File file = new File("D:\\db.csv"); 
		try { 
			
			FileWriter outputfile = new FileWriter(file,true); 

			
			CSVWriter writer = new CSVWriter(outputfile);  			
			
			// add data to csv 
			
			String score1 = score+"";
			//System.out.println(x+"hey"+x.length());
			if(x!=null)
			{	if(x.length()==0)
					x="Anonymous";
				String[] data1 = { x, score1 }; 
				writer.writeNext(data1);  
			}

			
			writer.close(); 
		} 
		catch (IOException e) { 
			
		} 
	} 


}
