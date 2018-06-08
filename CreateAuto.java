//CIS35B Lab2
//Written by Mina Liu
//on 2/3/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for CreateAuto Interface
 * |-----------------------------------------------------------------------------------|
 * |        CreateAuto Interface                                                       |
 * |===================================================================================|
 * |                                                                                   |
 * |===================================================================================|
 * |      + buildAuto(fileName: String)                                                |
 * |      + printAuto():                                                               |
 * |-----------------------------------------------------------------------------------|
 *
 */

package adapter;

public interface CreateAuto {

	public void buildAuto(String fileName);
	
	public void print();

	public void printAuto(String aKey);

}
