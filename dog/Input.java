// Jesper Eriksson jeer6905
package dog;
import java.util.*;
public class Input{

        public int convertToInt(String stringInt){
                return Integer.parseInt(stringInt);
        }
        public String prompt(String question, Scanner scan){
                System.out.print(String.format("%s?> ",question));
                return scan.nextLine(); 
        }
	public int promptInt(String question, Scanner scan){
                System.out.print(String.format("%s?> ",question));
		return convertToInt(scan.nextLine());	
	}
	public double convertToDouble(String stringDouble){
		stringDouble = stringDouble.replaceAll(",",".");
		return Double.parseDouble(stringDouble);
	}
	public String waitForEnter(Scanner scanner) {
		return scanner.nextLine();
	}


}
