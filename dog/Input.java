// Jesper Eriksson jeer6905
package dog;
import java.util.*;
public class Input{

        public int convertToInt(String stringInt){
                return Integer.parseInt(stringInt);
        }

        public String prompt(String question, Scanner registrationScanner){
                System.out.print(String.format("%s?> ",question));
                return registrationScanner.nextLine(); 
        }
	public double convertToDouble(String stringDouble){
		stringDouble = stringDouble.replaceAll(",",".");
		return Double.parseDouble(stringDouble);
	}


}