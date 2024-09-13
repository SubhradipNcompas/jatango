package demo.pageObject;


import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import java.util.Random;

public class JobTemplete {
	
	
	
	
//	public static void main(String[] args) throws FilloException {
   

	public static void jobIdWrite(String jobtitle,String columnName) throws FilloException {
		
		try {
			
			System.out.println("Welcome Create JobID Inside block In Side");
			
			String dirPath = System.getProperty("user.dir");
			
			String ExclePath=dirPath+"/autoIt/JobTitle.xlsx";
			
			System.out.println("ExclePath: "+ExclePath);

			
		    Fillo fillo = new Fillo();

		    Connection connection = fillo.getConnection(ExclePath);
		    
            //String jobtitle="TFSE-4671k";
		    
		    String newString = '\''+ jobtitle + '\'';
		    
		    System.out.println("newString: "+newString);

		    System.out.println("jobtitle: "+jobtitle);

			String  strUpdateQuerry = "Update SimplifyVMS Set "+columnName+" ="+newString+" where Id=1" ;

		     
		    System.out.println(strUpdateQuerry);
		    connection.executeUpdate(strUpdateQuerry);
			
		}catch(Exception e) {
			
			System.out.println("Exception : "+e);
			
		}
		

     
		
}

// Generate Random Email
public static String getNewEmailId() {
	System.out.println("Enter Email Id Create: ");
	String emailId=null;
	String emailIdSatic="subhradip.sinha+";

	try {
		Random rn = new Random();
		int answer = rn.nextInt(85) +11;
		int second = rn.nextInt(85);
		int Finaltext=answer+second;
		emailId=emailIdSatic+Finaltext;
		System.out.println("Final emailId Generate: "+emailId);


	}
	catch(Exception e) {
		e.printStackTrace();
	}

	return emailId+"@simplifyvms.com";
}



}
