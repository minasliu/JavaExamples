//CIS35B Lab3
//Written by Mina Liu
//on 2/17/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for FileIO Class
 * |------------------------------------------------------------------------------------------------------------------------------|
 * |        FileIO Class                                                                                                          |
 * |==============================================================================================================================|
 * |      - int MAX_OPTIONS                                                                                                       |
 * |      - String OUTFILE_NAME                                                                                                   |
 * |==============================================================================================================================|
 * |      + readFile(String fileName): Automotive                                                                                      |
 * |      - readCount(String line): int throws CustomException                                                                    |
 * |      -readPrice(String line): float throws CustomException                                                                   |
 * |      + serializeAuto(Automotive auto)                                                                                             |
 * |      + deserializeAuto(String inFile): Automotive                                                                                 |
 * |------------------------------------------------------------------------------------------------------------------------------|
 *
 */
package util;
import auto.Automobile;
import java.io.*;

public class FileIO {
	private final String OUTFILE_NAME="auto.ser";

	//I've set up the input file to be programmer-friendly with the expectation that a user-friendly GUI would eventually 
	//created to generate the input file.
	public Automobile readFile(String fileName){
		Automobile newCar=new Automobile();
		int osIndex=-1;
		FileReader file;
		BufferedReader buff;

		try{
			file = new FileReader(fileName);
			buff=new BufferedReader(file);
			boolean eof=false;
			boolean makeFlagRead=false;
			boolean modelFlagRead=false;
			boolean OptionSetFlagRead=false;

			while(!eof){
				String line = buff.readLine();

				if (line==null){
					eof=true;
					file.close();
					buff.close();
				}else{
					//Break line into 3 tokens
					String[] tokens=new String[3];
					tokens=line.split(",",3);
					String header=tokens[0];
					String name=tokens[1];
					float price=readPrice(tokens[2]);

					switch(header){
					case "Make": 
						makeFlagRead=true;
						newCar.setMakeName(name);
						break;

					case "Model":
						if(!makeFlagRead)
							throw new CustomException(1);
						modelFlagRead=true;
						newCar.setModelName(name);
						newCar.setBasePrice(price);
						break;

					case "OptionSet":
						if(!modelFlagRead)
							throw new CustomException(1);
						OptionSetFlagRead=true;
						newCar.addOptionSet(name);
						osIndex++;
						break;

					case "Option":
						if(!OptionSetFlagRead)
							throw new CustomException(1);
						newCar.addOption(osIndex,name, price);
						break;

					}
				}
			}
		}catch (IOException e){
			System.out.println("Error"+e.toString());
		}catch(CustomException ce){
			ce.fixException();
			return newCar;

		}
		return newCar;
	}

	private String readName(String name){
		if (name==null){
			return new String("Not Set");
		}else{
			return name;
		}
	}

/*Commenting out since no longer needed in the new version of readFile.  But keeping just in case.
	private int readCount(String line) throws CustomException{
		int count=0;
		try{
			count=Integer.parseInt(line.trim());
		}catch(NullPointerException npe){
			return 0;
		}
		catch(NumberFormatException nfe){
			throw new CustomException(2);
		}
		if(count<0){
			throw new CustomException(2);
		}
		return count;
	}
*/
	
	private float readPrice(String line) throws CustomException{
		float amount=0;
		if (line.compareTo("")==0){
			return 0;
		}
		try{//Need to return 0 if null
			amount=Float.parseFloat(line);
		}catch(NumberFormatException nfe){
			throw new CustomException(3);
		}
		return amount;
	}



	public void serializeAuto(Automobile auto){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(OUTFILE_NAME));
			out.writeObject(auto);
			out.close();
		}catch(Exception e){ 
			System.out.print("Error: " + e);
			System.exit(1);
		}
	}

	public Automobile deserializeAuto(String inFile){
		Automobile auto=new Automobile();
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(inFile));
			auto = (Automobile) in.readObject();
			in.close();
		}catch(Exception e){ 
			System.out.print("Error: " + e);
			System.exit(1);
		}

		return auto;
	}
}
