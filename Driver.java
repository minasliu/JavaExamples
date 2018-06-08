//CIS35B Lab3
//Written by Mina Liu
//on 2/17/14
//using JRE 1.7 with Java Compiler in Eclipse IDE
//on Windows 7 Home Laptop

package driver;
import adapter.*;

public class Driver {
	public static void main(String [] args){
		CreateAuto creator=new BuildAuto();
		UpdateAuto updater=new BuildAuto();
		//BuildAuto builder=new BuildAuto();
		
		System.out.println("Building Auto from CreateAuto object");
		creator.buildAuto("FordZTW.txt");
		creator.print();
		
		System.out.println("Testing Updater-Changing OptionSet Name & Option\n");
		updater.updateOptionSetName("FordFocus Wagon ZTW","Color", "Colour");
		updater.updateOptionName("FordFocus Wagon ZTW","Colour", "French Blue Clearcoat Metallic", "Pretty Blue");
		updater.updateOptionPrice("FordFocus Wagon ZTW","Colour", "Pretty Blue", 500);
		updater.printAuto("FordFocus Wagon ZTW");
		
		System.out.println("Testing BuildAuto-Adding 3new cars.\n");
		BuildAuto builder=new BuildAuto();
		builder.buildAuto("HyundaiSantaFe.txt");
		builder.buildAuto("FordC-MaxEnergi.txt");
		System.out.println("Testing to see what's in builder after adding 3 more cars.");
		builder.print();
		
		System.out.println("Testing Configuring Car.\n");
		builder.setOptionChoice("FordC-Max Energi", "Color", "Ruby Red");
		builder.setOptionChoice("FordC-Max Energi", "Transmission", "2.0L I-4 HEV");
		builder.setOptionChoice("FordC-Max Energi", "Package", "302A");
		builder.printConfiguredAuto("FordC-Max Energi");
		builder.printConfiguredAuto("HyundaiSanta Fe");
		
		System.out.println("Testing Exception Handling if bad Key passed in.");
		builder.printConfiguredAuto("BadCarName");
		
		
		/*
		//Testing Exception Classes
		CreateAuto badAuto=new BuildAuto();
		
		System.out.println("Testing Exception if 'Make' flag missing");
		badAuto.buildAuto("FordZTW-NoMakeFlag.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if 'Model' flag missing");
		badAuto.buildAuto("FordZTW-NoModelFlag.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if OptionSet count is negative");
		badAuto.buildAuto("FordZTW-NegOptionSetCount.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if OptionSet count is a string");
		badAuto.buildAuto("FordZTW-StringOptionSetCount.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if basePrice is a string");
		badAuto.buildAuto("FordZTW-StringBasePrice.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if optionPrice is a string");
		badAuto.buildAuto("FordZTW-StringOptionPrice.txt");
		badAuto.printAuto();
		
		System.out.println("Testing Exception if OptionSet count less than number of OptionSets provided");
		badAuto.buildAuto("FordZTW-TooManyOS.txt");
		badAuto.printAuto();

		System.out.println("Testing Exception if OptionSet count is more than number of OptionSets provided");
		badAuto.buildAuto("FordZTW-TooFewOS.txt");
		badAuto.printAuto();*/
	
	}
}

