
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
	public static final String FIND_DOG_METHOD = "findDog"; // U7.3 - hjälpmetod tänkt att användas i de följande stegen
	public static final String INCREASE_AGE_METHOD = "increaseAge"; // U7.4
	public static final String REMOVE_DOG_METHOD = "removeDog"; // U7.5, U8.6 och U9.6
	public static final String SORT_DOGS_METHOD = "sortDogs"; // U7.6
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
	private Scanner registrationScanner = new Scanner(System.in);
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private Input input = new Input();
	public void registerNewDog(){
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
	public Dog findDog(String name) {
		if (dogs.size()>0){
			for (int i = 0; i<dogs.size();i++){
				if (dogs.get(i).getName().equalsIgnoreCase(name)){
					return dogs.get(i);
				}
			}	
		} 
		return null;
	}
	public void increaseAge() {
		String name = input.prompt("Enter the name of the dog",registrationScanner);
		Dog dog = findDog(name);
		if (dog == null){
			System.out.println(String.format("Error: no dog named %s",name));
		} else {	
			dog.increaseAge();
			System.out.println(String.format("%s is now one year older",name));
		}
	}
	public int findDogIndex(String name){
		if (dogs.size()>0){
			for (int i = 0; i<dogs.size();i++){
				if (dogs.get(i).getName().equalsIgnoreCase(name)){
					return i;
				}
			}	
		} 
		return dogs.size()+1;
	}
	public void removeDog() {
		String name = input.prompt("Enter the name of the dog to remove",registrationScanner);
		int index = findDogIndex(name);
		if (index>dogs.size()) {
			System.out.println(String.format("Error: no dog named %s",name));
		} else {
			dogs.remove(index);
			System.out.println(String.format("%s is removed from the register",name));
		}
	}
	public void sortDogsPrint(){ // För debug
		for (int i = 1; i<dogs.size(); i++) {
			// Välj den hund som ska flyttas
			Dog dog = dogs.get(i);
			double tailLength = dog.getTailLength();
			// Börja räkna ifrån den hundens position
			int j  = i - 1;
			// Om listan inte är slut och hunden bakom har längre svans
			while (j >= 0 && dogs.get(j).getTailLength() > tailLength){
				// Flytta bak hunden med större svans
				dogs.set(j+1, dogs.get(j));
				// Gå ett steg år höger
				j = j - 1;
			}
			// Placera hunden som skulle flyttas på rätt plats
			dogs.set(j+1,dog);
		}
		for (int i = 0; i<dogs.size();i++){
			System.out.println(dogs.get(i));
		}
		
	}
	// InsertionSort, informationen hittade jag här: https://www.geeksforgeeks.org/insertion-sort/
	public ArrayList<Dog> sortDogs(){
		ArrayList<Dog> sortedDogs = new ArrayList<>(dogs);
		for (int i = 1; i<sortedDogs.size(); i++) {
			// Välj den hund som ska flyttas
			Dog dog = sortedDogs.get(i);
			// Börja räkna ifrån den hundens position
			int j  = i - 1;
			// Om listan inte är slut och hunden bakom har längre svans
			while (j >= 0 && sortedDogs.get(j).getTailLength() > dog.getTailLength()){
				// Flytta bak hunden med större svans
				sortedDogs.set(j+1, sortedDogs.get(j));
				// Gå ett steg år höger
				j--;
			}
			// Placera hunden som skulle flyttas på rätt plats
			sortedDogs.set(j+1,dog);
		}
		ArrayList<Dog> doubleSortedDogs = sortDogsByName(sortedDogs);
		return doubleSortedDogs;
	}
	private ArrayList<Dog> sortDogsByName(ArrayList<Dog> list){
	
		// Sortera hundar med samma svanslängd på namn
		for (int i = 1; i<list.size();i++){
			// Åtminstone två hundar har samma svanslängd x
			if (list.get(i-1).getTailLength()== list.get(i).getTailLength()) { 
				int j = 1;
				while (list.get(i).getTailLength() == list.get(i+j).getTailLength()){
					j++; // j är antalet hunder med svanslängd x
				}
				for (int k = i;k<i+j;k++){
					// Välj hund som ska flyttas
					Dog dog = list.get(k);
					int l = k-1;
					// Samma procedur som innan
					while (l>=0 && compareAlphabetically(list.get(l).getName(),dog.getName())){
						list.set(l+1, list.get(l));
						l--;			
					}
					list.set(l+1,dog);	
				}
				i = i + j; // Hoppa över redan sorterade hundar
			}
		}
		return list;
	}
	private boolean compareAlphabetically(String s1, String s2){
		if (s1.compareTo(s2)>0){ // Ger positivt om s2 ligger före s1 alfabetiskt
			return true; // dvs, byt plats på dem 
		} else { // Annars, 
			return false; // Gör ingenting
		}
	}
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
	public String waitForUserInput() {
		// Ersätt raden nedan med NAMNPÅSCANNER.nextLine() eller motsvarande anrop på din egen klass
		return input.waitForEnter(registrationScanner);
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
