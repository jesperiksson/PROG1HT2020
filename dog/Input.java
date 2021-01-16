// Jesper Eriksson jeer6905

import java.util.*;
public class Input{

	public int convertToInt(String stringInt){
	    return Integer.parseInt(stringInt);
	}
	public String prompt(String question, Scanner scan){
	    System.out.print(String.format("%s?> ",question));
	    return scan.nextLine(); 
	}
	public String promptName(String question, Scanner scan){
	    System.out.print(String.format("%s?> ",question));
	    return properCapitalizing(scan.nextLine()); 
	}
	public int promptInt(String question, Scanner scan){
                System.out.print(String.format("%s?> ",question));
		return convertToInt(scan.nextLine());	
	}
	public double convertToFloat(String stringFloat){
		stringFloat = stringFloat.replaceAll(",",".");
		return Float.parseFloat(stringFloat);
	}
	public String waitForEnter(Scanner scanner) {
		return scanner.nextLine();
	}
	private String properCapitalizing(String string){
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}


}
