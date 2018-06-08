//CIS35B Lab2
//Written by Mina Liu
//on 2/3/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for ProxyAuto (Abstract) Class
 * |---------------------------------------------------------------------------------------|
 * |        ProxyAuto Class                                                                |
 * |=======================================================================================|
 * |      - Automotive a1                                                                       |
 * |      - FileIO fileIO                                                                  |
 * |=======================================================================================|
 * |      + updateOptionSetName(osName: String, newOSName: String):                        |
 * |      + updateOptionName(osName: String, oName: String, newOName: String):             |
 * |      + updateOptionPrice(osName: String, oName: String, newPrice: float):             |
 * |      + buildAuto(fileName: String):                                                   |
 * |      + printAuto():                                                                   |
 * |---------------------------------------------------------------------------------------|
 *
 */

package adapter;
import auto.Automobile;
import auto.OptionSet;
import util.FileIO;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import util.CustomException;

public abstract class ProxyAuto {
	private static LinkedHashMap<String, Automobile> autoDB;
	private FileIO fileIO;

	public ProxyAuto(){
		if(autoDB==null)
			autoDB=new LinkedHashMap<String, Automobile>(0);
		fileIO=new FileIO();
	}
	public ProxyAuto(Automobile a1){
		if(getAutoCount()==0){
			autoDB=new LinkedHashMap<String, Automobile>(1);
		}else{
			addAuto(a1);
		}
		fileIO=new FileIO();
		autoDB.put(createKey(a1),a1);
	}

	public Automobile getAuto (String aKey) throws CustomException{
		if(autoDB.containsKey(aKey)){
			return autoDB.get(aKey);
		}else{
			throw new CustomException(6);
		}
	}

	public String getOptionChoiceName(String aKey,String osName){
		try{
			return getAuto(aKey).getOptionChoiceName(osName);
		}catch (CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			return new String("Not Found");
		}
	}

	public float getOptionChoicePrice(String aKey,String osName){
		try{
			return getAuto(aKey).getOptionChoicePrice(osName);
		}catch (CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			return 0;
		}
	}

	public float getTotalPrice(String aKey){
		try{
			return getAuto(aKey).getTotalPrice();
		}catch (CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			return 0;
		}
	}

	public int getAutoCount(){
		return autoDB.size();
	}

	//setters
	public void setOptionChoice(String aKey,String osName, String oChoiceName){
		try{
			getAuto(aKey).setOptionChoice(osName,oChoiceName);
		}catch (CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.println("Unable to set Option Choice.");
		}
	}

	//Modifiers
	public void addAuto(Automobile a1){
		autoDB.put(createKey(a1),a1);	
	}

	public void removeAuto(String aKey){
		try{
			if(autoDB.containsKey(aKey)){
				autoDB.remove(aKey);
			}else{
				throw new CustomException(6);
			}
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("%s Auto not removed.\n", aKey);
		}
	}

	public boolean containsAKey(String aKey){
		return autoDB.containsKey(aKey);
	}

	public boolean containsAuto(Automobile a1){
		return autoDB.containsValue(a1);
	}

	public void updateOptionSetName(String aKey, String osName, String newOSName){
		try{
			getAuto(aKey).setOptionSetName(osName,newOSName);
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("%s Auto not updated.\n", aKey);

		}
	}

	public void updateOptionName(String aKey, String osName, String oOldName, String oNewName){
		try{
			getAuto(aKey).setOptName(osName, oOldName, oNewName);
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("OptionName in %s Auto not updated.\n", aKey);
		}
	}

	public void updateOptionPrice(String aKey, String osName, String oName, float newPrice){
		try{
			getAuto(aKey).setOptPrice(osName, oName, newPrice);
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("OptionName in %s Auto not updated.\n", aKey);
		}
	}

	public void addOptionSet(String aKey, String osName){
		try{
			getAuto(aKey).addOptionSet(osName);
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("OptionSet in %s Auto not added.\n", aKey);
		}
	}

	public void addOption(String aKey, String osName, String oName, float oPrice){
		try{
			getAuto(aKey).addOption(osName,oName,oPrice);
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("Option in %s Auto not added.\n", aKey);
		}
	}

	public void buildAuto(String fileName){
		Automobile a1=fileIO.readFile(fileName);
		autoDB.put(createKey(a1),a1);

	}

	public void print(){
		StringBuilder s=new StringBuilder();
		Set<String> st = autoDB.keySet();
		Iterator<String> itr = st.iterator();
		while (itr.hasNext()){
			try{
				s.append(getAuto(itr.next()).print());
			}catch(CustomException ce){
			}
		}
		System.out.println(s.toString());}

	public void printAuto(String aKey){
		try{
			System.out.println(getAuto(aKey).print());
		}catch (CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("Unable to print %s.\n", aKey);
		}
	}

	public void printConfiguredAuto(String aKey){
		try{
			System.out.println(getAuto(aKey).printConfiguredModel());
		}catch(CustomException ce){
			System.out.print(aKey);
			ce.fixException();
			System.out.printf("Unable to print %s.\n", aKey);
		}
	}

	//Helper methods
	private String createKey(Automobile a1){
		StringBuilder s=new StringBuilder(a1.getMakeName());
		s.append(a1.getModelName());
		return s.toString();
	}
}