//CIS35B Lab2
//Written by Mina Liu
//on 2/3/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for CustomException Class
 * |------------------------------------------------------------------------------------------|
 * |        CustomeException Class   extends Exception                                        |
 * |==========================================================================================|
 * |      - int errorCode                                                                     |
 * |      - String errorMsg                                                                   |
 * |==========================================================================================|
 * |      + CustomException() <<default constructor>>                                         |
 * |      + CustomException(int newCode)                                                      |
 * |      + CustomException(String newMsg)                                                    |
 * |      + CustomException(int newCode, String newMsg)                                       |
 * |      + getErrorCode(): int                                                               |
 * |      + String getErrorMsg(): String                                                      |
 * |      + setErrorCode(int newCode):                                                        |
 * |      + setErrorMsg(String newMsg):                                                       |
 * |      + print():                                                                          |
 * |      + fixException():                                                                   |
 * |------------------------------------------------------------------------------------------|
 *
 */
 
package util;

import java.util.Scanner;

public class CustomException extends Exception{
	private int errorCode=0;
	private String errorMsg;

	public CustomException(){
		super();
		errorCode=0;
		errorMsg=new String("Not Error Message Set");
	}
	public CustomException(int newCode){
		super();
		errorCode=newCode;
	}
	
	public CustomException(String newMsg){
		super();
		errorCode=0;
		errorMsg=new String(newMsg);
	}

	public CustomException(int newCode, String newMsg){
		super();
		errorCode=newCode;
		errorMsg=new String(newMsg);
	}
	
	public int getErrorCode(){
		return errorCode;
	}

	public String getErrorMsg(){
		return errorMsg;
	}
	
	public void setErrorCode(int newCode){
		errorCode=newCode;
	}
	
	public void setErrorMsg(String newMsg){
		errorMsg=newMsg;
	}
	
	public void print() {
		System.out.println("CustomException [errorCode" + errorCode + ", errorMsg=" + errorMsg); 
	}

	public void fixException(){
		switch(errorCode){
		case(0):
			print();
			System.out.println("Unknown error encountered.\n");
			break;
			
		case (1)://Headers missing.
			setErrorMsg("Headers missing from input file.");
			print();
			System.out.println(" Creating unpopulated Automobile.\n");
			break;
			
		case (2)://Invalid Count: i.e. a string or a negative number
			setErrorMsg("Invalid number of OptionSets encountered.");
			print();
			System.out.println(" Setting value to 0.\n");
			break;

		case (3)://Invalid Price: i.e. a string, negative prices allowed
			setErrorMsg("Invalid Price encountered.");
			print();
			System.out.println(" Setting value to 0.\n");
			break;

		case (4): //Too many OptionSets vs. OptionSet count
			setErrorMsg("Too much OptionSet data provided.");
			print();
			System.out.println(" Returning unpopulated Automobile.  Please check file for incorrect OptionSet count.\n");
			break;

		case (5): //Early Termination-missing data;
			setErrorMsg("Expected more data.");
			print();
			System.out.println(" Insufficient data to complete Automobile.  Please check file missing data or incorrect OptionSet count.\n");
			break;
			
		case (6): //CarKey not found in LHM;
			setErrorMsg("was not found in the autoDB.");
			print();
			break;

		case (7): //OptionNames[].length!=OptionPrices[].length;
			setErrorMsg("Number of Option Names does not equal the number of Options Prices.");
			print();
			System.out.println("The number of Options will be set to the smaller of the two numbers.");
			break;

		case (8): //Trying to set an object to an index that is out of bounds, fixes it by adding to end
			setErrorMsg("Attempting to set object to an out-of-bounds index.");
			print();
			System.out.println("Adding object to the end of the list.");
			break;

		case (9): //Trying to set an value to an index that is out of bounds, does not try to fix
		setErrorMsg("Attempting to set value to an out-of-bounds index.");
		print();
		System.out.println("Your value has not been added.  Please check your index.");
		break;

		case (10): //String not found in search
		setErrorMsg("Unable to find value in search of list.");
		print();
		System.out.println("returning an index of -1.");
		break;

	}
	}
	
	public int checkIndex(int index, int min, int max, String objName){
		try{
			if((index<min)||(index>max))
				throw new CustomException(8);
		}catch(CustomException ce){
			return ce.fixExceptionIndex(min,max, objName);
		}
		return index;

	}
	public int fixExceptionIndex(int min, int max, String objName){
		System.out.printf("You have selected an out of range index for %s.\n", objName);
		System.out.printf("Please enter in a new index between %d and %d: ",min, max);
		Scanner input = new Scanner (System.in);
		if(input.hasNextInt()){
			return input.nextInt();
		}else {
			System.out.printf("You have entered in an invalid index.  Index set to %d.\n",min);
			return min;
		}
	}
}

