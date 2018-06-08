//CIS35B Lab2
//Written by Mina Liu
//on 2/3/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for UpdateAuto Interface
 * |------------------------------------------------------------------------------------------|
 * |        UpdateAuto Interface                                                              |
 * |==========================================================================================|
 * |                                                                                          |
 * |==========================================================================================|
 * |      + updateOptionSetName(osName: String, newOSName: String):                           |
 * |      + updateOptionName(osName: String, oName: String, newOName: String):                |
 * |      + updateOptionPrice(osName: String, oName: String, newPrice: float):                |
 * |------------------------------------------------------------------------------------------|
 *
 */

package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String aKey, String osName, String newOSName);

	public void updateOptionName(String aKey, String osName, String oName, String newOName);
	
	public void updateOptionPrice(String aKey, String osName, String oName, float newPrice);
	
	public void print();

	public void printAuto(String aKey);

}

