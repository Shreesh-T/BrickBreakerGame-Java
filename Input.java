package brickBreaker;


import javax.swing.*;

public class Input  
{
	String name;
	JTextField fieldName;
	public void button() 
    {
		
		JFrame frame = new JFrame("InputDialog Example #1");

    	 name = JOptionPane.showInputDialog(frame, "Enter Name: ");
    	 frame.dispose();
	}
    		
		
    }

	
	
	
    
