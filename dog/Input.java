// Jesper Eriksson jeer6905
package dog;
import java.util.*;
public class Input{

        public int convertToInt(String ageString){
                return Integer.parseInt(ageString);
        }

        public String prompt(String question, Scanner registrationScanner){
                System.out.print(String.format("%s?> ",question));
                return registrationScanner.nextLine(); 
        }


}
