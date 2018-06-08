//CIS35B Lab3
//Written by Mina Liu
//on 2/13/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

/*
 *  
 * UML Diagram for OptionSet Class
 * |----------------------------------------------------------------------------|
 * |        OptionSet                                                           |
 * |============================================================================|
 * |      - String name                                                         |
 * |      - ArrayList<Option> opt                                               |
 * |============================================================================|
 * |      # OptionSet() <<default Constructor>>                                 |
 * |      # OptionSet(n: String)                                                |
 * |      # OptionSet(n: String, opt: Option[])                                 |
 * |      # OptionSet(n: String, size: int)                                     |
 * |                                                                            |
 * |      # getOptSetName(): String                                             |
 * |      # getOptions(): Option[]                                              |
 * |      # getOptionsCount(): int                                              |
 * |      # getOption(i: int): Option                                           |
 * |      # getOptName(i: int): String                                          |
 * |      # getOptPrice(i: int): float                                          |
 * |                                                                            |
 * |      # setOptionSet(osName: String,  options: Option[])                    |
 * |      # setOptionSet(osName: String,  oNames: String[], oPrices: float[])   |
 * |      # setOptSetName(osName: String)                                       |
 * |      # setOptions(options: Option[])                                       |
 * |      # setOptions(String[] oNames, float[] oPrices)                        |
 * |      # setOption(option: Option, index: int)                               |
 * |      # setOption(oName: String, oPrice: float, index: int)                 |
 * |      # setOptName(String oName, index: int)                                |
 * |      # setOptPrice(oPrice: float, index: int)                              |
 * |                                                                            |
 * |      # findOptionIndex(String oName): int                                  |
 * |      # findOptionPrice(String oName): float                                |
 * |                                                                            |
 * |      # createOption(): Option                                              |
 * |      # createOption(String n): Option                                      |
 * |      # createOption(String n, float p): Option                             |
 * |      # createOptions(int oCount)                                           |
 * |      # createOptions(String[] oNames, float[] oPrices)                     |
 * |                                                                            |
 * |      # clearOptionSet()                                                    |
 * |      # clearOptions()                                                      |
 * |      # clearOption(int i)                                                  |
 * |                                                                            |
 * |      # print()                                              |
 * |                                                                            |
 * |============================================================================|
 * |      Inner Class: Option Class                                             |
 * |============================================================================|
 * |      - String name;                                                        |
 * |      - float price;                                                        |
 * |============================================================================|
 * |      # Option() <<default Constructor>>                                    |
 * |      # Option(n: String)                                                   |
 * |      # Option(n: String, p: float)                                         |
 * |                                                                            |
 * |      # setOptName(n: String)                                               |
 * |      # setOptPrice (p: String)                                             |
 * |      # setOption(n: String, p: float)                                      |
 * |                                                                            |
 * |      # getOptName(): String                                                |
 * |      # float getOptPrice(): float                                          |
 * |      # getOption(): Option                                                 |
 * |                                                                            |
 * |      # print()                                                    |
 * |----------------------------------------------------------------------------|
 *
 */
package auto;
import util.CustomException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.lang.Math;//for min method

public class OptionSet implements Serializable{
	private String name;
	private ArrayList<Option> opt;
	private Option choice;


	//Constructors
	protected OptionSet(){
		this("Not Set",0);
	}

	protected OptionSet(String n){
		this(n,0);
	}

	protected OptionSet(String n, ArrayList<Option> opt){
		name=n;
		this.opt=opt;
		choice = new Option();
	}

	protected OptionSet(String n, int size) { 
		name = n;
		opt = new ArrayList<Option>(size); 
		for(int i=0;i<opt.size();i++) { 
			opt.set(i,new Option());
		}
		choice= new Option();
	}

	//Getters
	protected String getOptSetName(){
		return name;
	}

	protected ArrayList<Option> getOptions(){
		return opt;
	}

	protected Option getOptChoice(){
		return choice;
	}
	
	protected String getOptChoiceName(){
		if(choice==null){
			return new String("");
		}else{
		return choice.getOptName();
		}
	}
	
	protected float getOptChoicePrice(){
		if(choice==null){
			return 0;
		}else{
		return choice.getOptPrice();
		}
	}
	
	protected int getOptionsCount(){
		return opt.size();
	}


	protected Option getOption(int index){
		return opt.get(checkOIndex(index));		
	}

	protected String getOptName(int index){
		return opt.get(index).getOptName();
	}

	protected float getOptPrice(int index){
		return opt.get(index).getOptPrice();
	}

	protected Iterator<Option> getOptNamesIterator(){
		return opt.iterator();
	}
	
	//Setters
	protected void setOptionSet(String osName, ArrayList<Option> options){
		name=osName;
		opt=options;
	}

	protected void setOptionSet(String osName, String[] oNames, float[] oPrices){//replaces existing data
		name=osName;
		setOptions(oNames,oPrices);	
	}

	protected void setOptSetName(String osName){
		name=osName;
	}

	protected void setOptions(ArrayList<Option> options){
		opt=options;
	}

	protected void setOptions(String[] oNames, float[] oPrices){
		int newOptionCount=oNames.length;

		try{
			if(oNames.length!=oPrices.length){
				throw new CustomException(7);
			}
		}catch(CustomException ce){
			ce.fixException();
			newOptionCount= Math.min(oNames.length,oPrices.length);//Should move this code to CustomException
		}
		opt=new ArrayList<Option>(newOptionCount);
		for(int i=0;i<newOptionCount;i++){
			opt.set(i,new Option(oNames[i], oPrices[i]));
		}
	}

	protected void setOptionChoice(int index){
		choice=getOption(index);
	}
	
	protected void setOptionChoice(String choiceName){
		choice=findOption(choiceName);//Need to throw exception if name not found
	}

	protected void setOption(int index, Option option){
		opt.set(checkOIndex(index),option);//Should eventually check that name isn't already there
	}


	protected void setOption(int index, String oName, float oPrice){
		setOption(checkOIndex(index),new Option(oName,oPrice));
	}

	protected void setOptName(int index,String oName){
		getOption(checkOIndex(index)).setOptName(oName);
	}
	
	protected void setOptName(String oOldName,String oNewName){
		findOption(oOldName).setOptName(oNewName);
	}

	protected void setOptPrice(int index,float oPrice){
		getOption(checkOIndex(index)).setOptPrice(oPrice);
	}
	
	protected void setOptPrice(String oName,float oPrice){
		findOption(oName).setOptPrice(oPrice);
	}

	//Finders

	protected Option findOption(String oName){
		return opt.get(findOptionIndex(oName));
	}
	
	protected int findOptionIndex(String oName){//Finds first instance of the name
		try{
			Iterator<Option> it=getOptNamesIterator();
			int index=0;
			while(it.hasNext()){
				if(it.next().getOptName().compareTo(oName)==0)
					return index;
				index++;
			}
			throw new CustomException(10);//if name not found
		}catch (CustomException ce){
			ce.fixException();//Need to do proper exception handling eventually
		}
		return -1;//To show option not found
	}
//Need findOption that returns null if not found
	protected float findOptionPrice(String oName){//Finds first instance of the name
			int index=findOptionIndex(oName);
			if(index==-1){
				return 0;
			}else{
				return opt.get(index).getOptPrice();
			}
	}

	//Modifiers
	protected Option createOption(){
		return new Option(); 
	}

	protected Option createOption(String n){
		return new Option(n);
	}

	protected Option createOption(String n, float p){
		return new Option(n, p);
	}

	protected void createOptions(int oCount){
		opt=new ArrayList<Option>(oCount);
	}

	protected void addOption(Option o){
		opt.add(o);
	}
	
	protected void addOption(String oName){
		opt.add(new Option(oName));
	}
	
	protected void addOption(String oName, float oPrice){
		opt.add(new Option(oName,oPrice));
	}
	//Clears info, but leaves the structure and size in place
	protected void clearOptionSet(){
		name="Not Set";
		clearOptions();
	}

	protected void clearOptions(){
		for(int i=0;i<opt.size();i++){
			clearOption(i);
		}
	}

	protected void clearOption(int i){
		opt.get(checkOIndex(i)).setOption("Not Set", 0);
	}

//OtherMethods
	protected boolean isConfigured(){
		return (!((getOptChoiceName().compareTo("Not Set"))==0));		
	}
	
	protected String print(){
		StringBuilder s=new StringBuilder();
		s.append("OptionSet: ");
		s.append(name);
		for(int i=0;i<opt.size();i++){
			s.append("\n\t");
			s.append(opt.get(i).print());
		}
		return s.toString();
	}

	protected String printChoice(){
		StringBuilder s=new StringBuilder();
		s.append("OptionSet: ");
		s.append(name);
		s.append("\n\tSelected Option: ");
		s.append(choice.getOptName());
		s.append(":\t$");
		s.append(String.format("%.2f", choice.getOptPrice()));
		return s.toString();
	}

	/* Future Methods
 	//return subset of options
	//change order of options, i.e. alphabetize, decreasing price
	//alphabetize options
//add options to existing optionSet
 //add a single  option with name, price
	 * 	protected void addOption(Option o){
		Option newOptions [];
		newOptions=new Option[opt.length+1];
		for(int i=0;i<opt.length;i++){
			newOptions[i]=opt[i];
		}
		newOptions[opt.length]=o;
		opt=newOptions;
	}
	 */

	class Option implements Serializable{
		private String name;
		private float price;

		//Constructors
		protected Option(){
			name=new String("Not Set");
			price=0;  
		}

		protected Option(String n){
			name=n;
			price=0;
		}

		protected Option(String n, float p){
			name=n;
			price=p;
		}

		//Setters
		protected void setOptName(String n){
			name=n;
		}

		protected void setOptPrice (float p){
			price=p;
		}

		protected void setOption(String n, float p){
			name=n;
			price=p;
		}

		//Getters
		protected String getOptName(){
			return name;
		}

		protected float getOptPrice(){
			return price;
		}

		protected Option getOption(){
			return this;
		}

		//Printers	  
		protected String print(){
			StringBuilder s=new StringBuilder();
			s.append("\t\tOption: ");
			s.append(name);
			s.append(":\t$");
			s.append(String.format("%.2f", price));

			return s.toString();
		}

	}
	
	//Helper method
	private int checkOIndex(int index){
		CustomException ce8=new CustomException(8);//To handle out-of-bounds index
		return ce8.checkIndex(index,0,opt.size()-1, "Option");
	}


	/*Possible methods for future labs
	protected void resizeOptions(int newSize){
		Option[] newOpt = new Option[newSize];//Should put a warning if options will be lost
		for(int i=0;i<newOpt.length;i++){
			newOpt[i]=new Option();
			while (opt.length>=newOpt.length){
				newOpt[i]=opt[i];
			}
		}	
		opt=newOpt;
	}

	protected void switchOptions (int firstIndex, int secondIndex){
		Option tempOption=getOption(firstIndex);
		this.setOption(getOption(secondIndex), firstIndex);
		this.setOption(tempOption, secondIndex);
	}

		//Inserts an option in a particular spot in the Option array.  Shifts all subsequent options.
	protected void addOption(Option o, int optIndex){
		Option newOptions [];
		newOptions=new Option[opt.length+1];
		for(int i=0;i<opt.length;i++){
			if (i<optIndex){
				newOptions[i]=opt[i];
			}else{
				if(i==optIndex){
					newOptions[i]=o;
				}else{
					if(i>optIndex){
						newOptions[i+1]=opt[i];
					}
				}
			}
		}
		opt=newOptions;
	}


	//Removes Option from list, shifting other options up and shortening list by 1
	protected void removeOption(int optIndex){
		for(int i=0;i<opt.length;i++){
			if(i>optIndex){
				opt[i-1]=opt[i];
			}
		}
		this.resizeOptions(opt.length-1);
	}

	 */
}