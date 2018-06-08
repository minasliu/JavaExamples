//CIS35B Lab3
//Written by Mina Liu
//on 2/13/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for Automobile Class
 * |------------------------------------------------------------------------------------------------------------------------------|
 * |        Automobile Class                                                                                                           |
 * |==============================================================================================================================|
 * |      - String makeName                                                                                                       |
 * |      - String modelName                                                                                                      |
 * |      - float basePrice                                                                                                       |
 * |      - ArrayList<OptionSet> optSet                                                                                           |
 * |        -------------------                                                                                                   |
 * |==============================================================================================================================|
 * |      + Automobile() <<default Constructor>>                                                                                       |
 * |      + Automobile(makeName: String)                                                                                               |
 * |      + Automobile(makeName: String, modelName: String)                                                                            |
 * |      + Automobile(makeName: String, modelName: String, p: float)                                                                  |
 * |      + Automobile(makeName: String, modelName: String, p: float, count: int)                                                      |
 * |      + Automobile(makeName: String, modelName: String, p: float, osNames: String[], oCounts: int[])                               |
 * |      + Automobile(makeName: String, modelName: String, p: float, osNames: String[], oCounts: int[], oNames[][], oPrices[][])      |
 * |      + Automobile(makeName: String, modelName: String, p: float, optSet: OptionSet[])                                             |
 * |                                                                                                                              |
 * |      + getMakeName(): String                                                                                                 |
 * |      + getModelName(): String                                                                                                |
 * |      + getBasePrice(): float                                                                                                 |
 * |      + getOptionSetName(i: int): String                                                                                      |
 * |      + getOptionSetsCount(): int                                                                                             |
 * |      + getOptionSets(): OptionSet[]                                                                                          |
 * |      + getOptionSet(osIndex: int): OptionSet                                                                                 |
 * |      + getOptionsCount(osIndex: int): int                                                                                    |
 * |      + getOptionsCount(): int[]                                                                                              |
 * |      + getOptionName(osIndex: int, oIndex: int): String                                                                      |
 * |                                                                                                                              |
 * |      + findOptionSet(osName: String): OptionSet                                                                              |
 * |      + findOptionSetIndex(osName: String): int                                                                               |
 * |      + findOptionsCount(osName: String): int                                                                                 |
 * |      + findOptionIndex(osName: String, oName: String): int                                                                   |
 * |      + findOptionPrice(osName: String, oName: String): float                                                                 |
 * |                                                                                                                              |
 * |      + setMakeName(n: String)                                                                                                |
 * |      + setModelName(n: String)                                                                                               |
 * |      + setBasePrice(p: float)                                                                                                |
 * |      + setOptionSets(os: OptionSet[])                                                                                        |
 * |      + setOptionSet(os: OptionSet, index: int)                                                                               |
 * |      + setOptionSetName(n: String, index: int)                                                                               |
 * |      + setOption(osIndex: int, oName: String, oPrice: float, oIndex: int)                                                    |
 * |      + setOptName(osIndex: int, oName: String, oIndex: int)                                                                  |
 * |      + setOptPrice(osIndex: int, oPrice: float, oIndex: int)                                                                 |
 * |                                                                                                                              |
 * |      + clearOptionSet (osIndex: int)                                                                                         |
 * |      + clearOptions (osIndex: int)                                                                                           |
 * |      + clearOption (osIndex: int, oIndex: int)                                                                               |
 * |      + print(): String                                                                                                       |
 * |------------------------------------------------------------------------------------------------------------------------------|
 *
 */
package auto;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import util.CustomException;
import auto.OptionSet.Option;

public class Automobile implements Serializable{
	private String makeName;
	private String modelName;
	private float basePrice;
	private ArrayList<OptionSet> optSet;

	//Constructors
	public Automobile(){
		makeName=new String("Not Set");
		modelName=new String("Not Set");
		basePrice=0;
		optSet=new ArrayList<OptionSet>(0);
	}

	public Automobile(String makeName){
		this(makeName,"Not Set",0.0F,0);
	}

	public Automobile(String makeName, String modelName){
		this(makeName,modelName,0.0F,0);
	}

	public Automobile(String makeName, String modelName, float p){
		this(makeName,modelName,p,0);
	}

	public Automobile(String makeName, String modelName, float p, int count){
		this.modelName=modelName;
		this.makeName=makeName;
		basePrice=p;
		optSet=new ArrayList<OptionSet>(count);
		for(int i=0;i<optSet.size();i++) { 
			optSet.set(i,new OptionSet()); 
		}
	}

	public Automobile(String makeName, String modelName, float p, String[] osNames, int[] oCounts){//Need to throw exception that 2 arrays have some length
		this.modelName=modelName;
		this.makeName=makeName;
		basePrice=p;
		int newOSCount=osNames.length;

		try{
			if(osNames.length!=oCounts.length){
				throw new CustomException(7);
			}
		}catch(CustomException ce){
			ce.fixException();
			newOSCount= Math.min(osNames.length,oCounts.length);//Should move this code to CustomException
		}
		optSet=new ArrayList<OptionSet>(newOSCount);
		for(int i=0;i<newOSCount;i++) { 
			optSet.set(i, new OptionSet(osNames[i], oCounts[i])); 
		}
	}
/*  Try to get rid of this constructor
	public Automobile(String makeName, String modelName, float p, String[] osNames, int[] oCounts, String[][] oNames, float[][] oPrices){
		//Need to throw exception if arrays are not the appropriate dimensions
		this(makeName,modelName,p,osNames,oCounts);
		try{
			if(oNames.length!=oPrices.length){
				throw new CustomException(7);
			}
		}catch(CustomException ce){
			ce.fixException();
			newOSCount= Math.min(osNames.length,oCounts.length);//Should move this code to CustomException
		}
		
		for(int osIndex=0;osIndex<optSet.length;osIndex++) { 
			optSet[osIndex] = new OptionSet(osNames[osIndex], oCounts[osIndex]); 
			for(int oIndex=0;oIndex<oCounts[osIndex];oIndex++){
				optSet[osIndex].setOption(oNames[osIndex][oIndex], oPrices[osIndex][oIndex],oIndex);
			}
		}
	}
*/
	public Automobile(String makeName, String modelName, float p, ArrayList<OptionSet> optSet){
		this(makeName,modelName,p);
		this.optSet=optSet;
	}


	//Getters
	public String getMakeName (){
		return makeName;
	}

	public String getModelName (){
		return modelName;
	}

	public float getBasePrice(){
		return basePrice;
	}

	public String getOptionSetName(int index){
			return getOptionSet(index).getOptSetName();		
	}

	public int getOptionSetsCount(){
		return optSet.size();
	}

	public ArrayList<OptionSet> getOptionSets(){
		return optSet;
	}

	public OptionSet getOptionSet(int osIndex){
		return optSet.get(checkOSIndex(osIndex));
	}
	

	public int getOptionsCount(int osIndex){
		return getOptionSet(osIndex).getOptionsCount();
	}

	public int[] getOptionsCount(){
		int[] oCounts=new int[optSet.size()];
		for (int i=0;i<oCounts.length;i++){
			oCounts[i]=getOptionsCount(i);
		}
		return oCounts;
	}

	public String getOptionName (int osIndex, int oIndex){
		return optSet.get(osIndex).getOption(oIndex).getOptName();
	}

	public float getOptionPrice (int osIndex, int oIndex){
			return optSet.get(osIndex).getOption(oIndex).getOptPrice();
		}

	public String getOptionChoiceName(String osName){
		return findOptionSet(osName).getOptChoiceName();
	}

	public String getOptionChoiceName(int index){
		return getOptionSet(index).getOptChoiceName();
	}

	public float getOptionChoicePrice(String osName){
		return findOptionSet(osName).getOptChoicePrice();
	}
	
	public float getOptionChoicePrice(int index){
		return getOptionSet(index).getOptChoicePrice();
	}
	
	public Iterator<OptionSet> getOptionSetIterator(){
		return optSet.iterator();
	}
	
	public float getTotalPrice(){
		float totalPrice=0;
		totalPrice+=getBasePrice();
		for(int i=0;i<getOptionSetsCount();i++){
			totalPrice+=getOptionChoicePrice(i);
		}
		return totalPrice;
	}
	
	//Finders
	public OptionSet findOptionSet(String osName){
		try{
			Iterator<OptionSet> it=getOptionSetIterator();
			int index=0;
			while(it.hasNext()){
				if(it.next().getOptSetName().compareTo(osName)==0)
					return getOptionSet(index);
				index++;
			}
			throw new CustomException(10);
		}catch(CustomException ce){
			ce.fixException();
		}
			return new OptionSet();//Should do proper error catching
	}

	public int findOptionSetIndex(String osName){
		try{
			Iterator<OptionSet> it=getOptionSetIterator();
			int index=0;
			while(it.hasNext()){
				if(it.next().getOptSetName().compareTo(osName)==0)
					return index;
				index++;
			}
			throw new CustomException(10);
		}catch(CustomException ce){
			ce.fixException();
		}
		return -1;//returns negative # to show not found//Should do proper error catching
	}

	public int findOptionsCount(String osName){
		try{
			Iterator<OptionSet> it=getOptionSetIterator();
			int index=0;
			while(it.hasNext()){
				if(it.next().getOptSetName().compareTo(osName)==0)
					return getOptionSet(index).getOptionsCount();
				index++;
			}
			throw new CustomException(10);
		}catch(CustomException ce){
			ce.fixException();
		}
		return 0;
	}

	public int findOptionIndex(String osName, String oName){
		return findOptionSet(osName).findOptionIndex(oName);
	}

	public float findOptionPrice(String osName, String oName){
		return findOptionSet(osName).findOptionPrice(oName);
	}


	//Setters
	public void setMakeName(String n){
		makeName=n;
	}

	public void setModelName(String n){
		modelName=n;
	}

	public void setBasePrice (float p){
		basePrice=p;
	}

	public void setOptionSets(ArrayList<OptionSet> os){
		optSet=os;
	}

	public void setOptionSet(int index, OptionSet os){
		optSet.set(checkOSIndex(index),os);
	}

	public void setOptionSetName(int osIndex, String oName){
		getOptionSet(osIndex).setOptSetName(oName);
	}

	public void setOptionSetName(String osName, String oName){
		getOptionSet(findOptionSetIndex(osName)).setOptSetName(oName);
	}
	
	public void setOptionChoice(String osName, String choiceOName){
		findOptionSet(osName).setOptionChoice(choiceOName);
	}
	
	public void setOption(int osIndex, String oName, float oPrice, int oIndex){
		getOptionSet(osIndex).setOption(oIndex,oName,oPrice);
	}

	public void setOptName(int osIndex, String oName, int oIndex){
		getOptionSet(osIndex).setOptName(oIndex,oName);
	}

	public void setOptName(String osName, String oName, int oIndex){
		getOptionSet(findOptionSetIndex(osName)).setOptName(oIndex,oName);
	}
	
	public void setOptName(String osName, String oOldName, String oNewName){
		getOptionSet(findOptionSetIndex(osName)).setOptName(oOldName, oNewName);
	}

	public void setOptPrice(int osIndex, int oIndex, float oPrice){
		getOptionSet(osIndex).setOptPrice(oIndex,oPrice);
	}

	public void setOptPrice(String osName, int oIndex, float oPrice){
		getOptionSet(findOptionSetIndex(osName)).setOptPrice(oIndex,oPrice);
	}

	public void setOptPrice(String osName, String oName, float oPrice){
		getOptionSet(findOptionSetIndex(osName)).setOptPrice(oName,oPrice);
	}

	//Modifiers
	public void addOptionSet(OptionSet os){
		optSet.add(os);
	}

	public void addOptionSet(String oName){
		optSet.add(new OptionSet(oName));
	}

	public void addOption(int osIndex, Option o){
		getOptionSet(osIndex).addOption(o);
	}
	
	public void addOption(int osIndex, String oName){
		getOptionSet(osIndex).addOption(oName);
	}
	public void addOption(int osIndex, String oName, float oPrice){
		getOptionSet(osIndex).addOption(oName, oPrice);
	}

	public void addOption(String osName, Option o){
		findOptionSet(osName).addOption(o);
	}

	public void addOption(String osName, String oName){
		findOptionSet(osName).addOption(oName);
	}

	public void addOption(String osName, String oName, float oPrice){
		findOptionSet(osName).addOption(oName, oPrice);
	}

	public void clearOptionSet (int osIndex){
			getOptionSet(osIndex).clearOptionSet();
	}

	public void clearOptions (int osIndex){
		getOptionSet(osIndex).clearOptions();
	}

	public void clearOption(int osIndex, int oIndex){
		getOptionSet(osIndex).clearOption(oIndex);
	}

	//Other Methods
	public boolean isFullyConfigured(){
		Iterator<OptionSet> it=getOptionSetIterator();
		while(it.hasNext()){
			if(!it.next().isConfigured())
				return false;
		}
		return true;
	}
	
	public String print(){
		StringBuilder s=new StringBuilder();
		s.append("Printing an unconfigured Model with all Options listed.\n");
		s.append("Make:");
		s.append(makeName);
		s.append("\nModel:");
		s.append(modelName);
		s.append(String.format("\nBase Price: $%.2f", basePrice));
		for(int i=0;i<optSet.size();i++){
			s.append("\n\t");
			s.append(getOptionSet(i).print());
		}
		s.append("\n");
		return s.toString();
	}

	public String printConfiguredModel(){
		StringBuilder s=new StringBuilder();
		s.append("Printing an Configured Model with only Option choices listed.\n");
		s.append("Make: ");
		s.append(makeName);
		s.append("\nModel: ");
		s.append(modelName);
		s.append(String.format("\nBase Price: $%.2f", basePrice));
		for(int i=0;i<getOptionSetsCount();i++){
			s.append("\n\t");
			s.append(getOptionSet(i).printChoice());
		}
		s.append("\n");
		return s.toString();
		
	}
	//Helper method
	private int checkOSIndex(int index){
		CustomException ce8=new CustomException(8);//To handle out-of-bounds index
		return ce8.checkIndex(index,0,optSet.size()-1, "OptionSet");
	}
	/* Save these methods for later labs per Prof. Singh's instructions
	public void removeOptionSet(int index){	}
	public void resizeOptionSet(int newSize){	}
	public void addOptionSet(OptionSet newOS){	}
	public void addOptionSet(OptionSet newOS, int i ){	}
	public void deleteOptionSet(){	}
	public void switchOptionSets(int firstIndex, int secondIndex){	}
	calculateConfiguredPrice
	compareTo
	 */

}
