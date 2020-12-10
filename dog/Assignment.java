
// Jesper Eriksson jeer6905

package dog;
import java.util.*;
public class Assignment {

	/*
	 * Allt eftersom du skriver dina metoder fyller du i deras namn i konstanterna
	 * nedan. Testprogrammet använder dessa konstanter för att hitta dina metoder,
	 * så det är viktigt att namnen stämmer.
	 */
	public static final String REGISTER_NEW_DOG_METHOD = "registerNewDog"; // U7.1
	public static final String LIST_DOGS_METHOD = "listDogs"; // U7.2 och U8.4
	public static final String FIND_DOG_METHOD = ""; // U7.3 - hjälpmetod tänkt att användas i de följande stegen
	public static final String INCREASE_AGE_METHOD = ""; // U7.4
	public static final String REMOVE_DOG_METHOD = ""; // U7.5, U8.6 och U9.6
	public static final String SORT_DOGS_METHOD = ""; // U7.6
	public static final String REGISTER_NEW_OWNER_METHOD = ""; // U8.1
	public static final String FIND_OWNER_METHOD = ""; // U8.2 - hjälpmetod tänkt att användas i de följande stegen
	public static final String GIVE_DOG_METHOD = ""; // U8.3 och framåt
	public static final String LIST_OWNERS_METHOD = ""; // U8.4
	public static final String OWNER_OF_DOG_METHOD = ""; // U8.5, obs! metoden ska ligga i Owner-klassen
	public static final String REMOVE_OWNER_METHOD = ""; // U8.7 och U9.6
	public static final String START_AUCTION_METHOD = ""; // U9.1 och framåt
	public static final String FIND_AUCTION_METHOD = ""; // U9.2 - hjälpmetod tänkt att användas i de följande stegen
	public static final String MAKE_BID_METHOD = ""; // U9.3 och framåt
	public static final String LIST_BIDS_METHOD = ""; // U9.4 och framåt
	public static final String LIST_AUCTIONS_METHOD = ""; // U9.5 och framåt
	public static final String CLOSE_AUCTION_METHOD = ""; // U9.6

	/********************************************************************************
	 * Här nedanför skriver du dina metoder. Du kommer att kunna lämna in samma
	 * fil(er) i samtliga inlämningar, så du behöver inte börja om för varje ny
	 * metod.
	 ********************************************************************************/
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	Scanner registrationScanner = new Scanner(System.in);
	public void registerNewDog(){
		Input input = new Input();
		String name = input.prompt("\nName", registrationScanner);
		String breed = input.prompt("Breed", registrationScanner);
		int age = input.convertToInt(input.prompt("Age", registrationScanner));
		int weight = input.convertToInt(input.prompt("Weight", registrationScanner));
		System.out.println(String.format("%s added to the register",name));
		Dog dog = new Dog(name, breed, age, weight);
		System.out.println(dog);
		addDog(dog);
	}
	public void registerNewDog(Dog dog){
		addDog(dog);
	}
	public void listDogs(){
		Input input = new Input();
		boolean atLeastOne = false;
		if (dogs.size()>0){
			double minTailLength = input.convertToDouble(
					input.prompt(
						"Smallest tail length to display",
						registrationScanner));
			System.out.println("The following dogs has such a large tail:");
			for (int i = 0; i<dogs.size(); i++) {
				if (dogs.get(i).getTailLength() >= minTailLength) {
					atLeastOne = true;
					System.out.print(String.format("*%s ",dogs.get(i).getName()));
					System.out.print(String.format("(%s, ",dogs.get(i).getBreed()));
					System.out.print(String.format("%d years, ",dogs.get(i).getAge()));
					System.out.print(String.format("%d kilo, ",dogs.get(i).getWeight()));
					System.out.print(String.format("%.2f cm tail)\n",dogs.get(i).getTailLength()));
				} 
			}
			if (!atLeastOne) {
				System.out.println(
						String.format(
							"Error: No dogs with a tail of at least %.1f cm",
							minTailLength));
			}
		} else {
			System.out.println("Error: no dog in register");
		}
	}
	/*
	private int convertToInt(String ageString){
		return Integer.parseInt(ageString);
	}

	private String prompt(String question, Scanner registrationScanner){
		System.out.print(String.format("%s?> ",question));
		return registrationScanner.nextLine(); 
	}
	*/
	/*
	 * Metoderna nedan är till för att testprogrammet ska sätta upp och kontrollera
	 * olika saker. De är INTE tänkta att användas i din egen kod. Du måste fylla i
	 * den saknade koden i metoderna allteftersom de behövs av testprogrammet.
	 */

	/*
	 * Byt ut koden i nedanstående metod så att den väntar på att användaren trycker
	 * på return. Du gör detta genom att anropa nextLine-metoden på din scanner.
	 * 
	 * Om du inte du gjort övningen till F6 där man ska skriva en egen klass för att
	 * hantera inmatning så gör den. Den är ett bra exempel på en klass med
	 * funktionalitet, och kommer att göra inlämningsuppgifterna enklare eftersom du
	 * inte kommer att drabbas av några vanliga fel.
	 * 
	 * Behövs från U7.5, eventuellt tidigare
	 */
	public void waitForUserInput(Scanner scanner) {
		// Ersätt raden nedan med NAMNPÅSCANNER.nextLine() eller motsvarande anrop på din egen klass
		scanner.nextLine(); // Används ej
	}

	/*
	 * Byt ut koden i nedanstående metod så att hunden läggs in i listan av hundar.
	 * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
	 * okej att använda andra klasser ur Javas Collection-api om du känner till dem.
	 * 
	 * Behövs från U7.2
	 */
	public void addDog(Dog d) {
		// Ersätt raden nedan med NAMNPÅLISTAN.add(d); eller motsvarande anrop
		dogs.add(d);
	}

	/*
	 * Byt ut koden i nedanstående metod så att listan på hundar returneras.
	 * 
	 * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
	 * okej att använda andra list-klasser ur Javas Collection-api om du känner till
	 * det, och föredrar en annan klass därifrån. Returtypen List gör att det går
	 * att skicka tillbaka vilken listtyp som helst.
	 * 
	 * Denna metod är ENBART till för testprogrammet i steg U7.1 till U9.7. Den ska
	 * nästan säkert INTE finnas i det slutgiltiga fullständiga programmet, så
	 * använd den inte i din egen kod.
	 * 
	 * Behövs från U7.1
	 */
	public List<Dog> getDogs() {
		// Ersätt raden nedan med return NAMNPÅLISTAN; eller motsvarande anrop
		return dogs;
	}

	/*
	 * Byt ut koden i nedanstående metod så att ägaren läggs in i listan av ägare.
	 * Uppgiften har inget specifikt krav på vilken typ av samling du ska använda
	 * för detta, utan det får du bestämma själv. Det kan vara en array, en
	 * ArrayList, en annan av Javas samlingsklasser, eller något du skrivit själv.
	 * 
	 * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
	 * okej att använda andra klasser ur Javas Collection-api om du känner till det,
	 * 
	 * Behövs från U8.2
	 */
//	public void addOwner(Owner o) {
//		// Ersätt raden nedan med NAMNPÅLISTAN.add(o); eller motsvarande anrop
//		throw new RuntimeException("Assignment.addOwner(Owner) är inte implementerad");
//	}

	/*
	 * Byt ut koden i nedanstående metod så att ägaren läggs in i listan av ägare.
	 * Uppgiften har inget specifikt krav på vilken typ av samling du ska använda
	 * för detta, utan det får du bestämma själv. Det kan vara en array, en
	 * ArrayList, en annan av Javas samlingsklasser, eller något du skrivit själv.
	 * 
	 * Kravet i uppgiften är formulerat så att en ArrayList ska användas, men det är
	 * okej att använda andra klasser ur Javas Collection-api om du känner till dem.
	 * 
	 * Behövs från U8.1
	 */
//	public Collection<Owner> getOwners() {
//		// Ersätt raden nedan med return NAMNPÅSAMLINGEN; eller motsvarande anrop
//		throw new RuntimeException("Assignment.getOwners är inte implementerad");
//	}

	/*
	 * Om du använder en array för att spara ägarna kan nedanstående variant
	 * användas istället
	 */
//	public Collection<Owner> getOwners() {
//		return Arrays.asList(NAMNET_PÅ_ARRAYEN);
//	}

}
