
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
	public static final String REGISTER_NEW_OWNER_METHOD = "registerNewOwner"; // U8.1
	public static final String FIND_OWNER_METHOD = "findOwner"; // U8.2 - hjälpmetod tänkt att användas i de följande stegen
	public static final String GIVE_DOG_METHOD = "giveDog"; // U8.3 och framåt
	public static final String LIST_OWNERS_METHOD = "listOwners"; // U8.4
	public static final String OWNER_OF_DOG_METHOD = "ownerOfDog"; // U8.5, obs! metoden ska ligga i Owner-klassen
	public static final String REMOVE_OWNER_METHOD = "removeOwner"; // U8.7 och U9.6
	public static final String START_AUCTION_METHOD = "startAuction"; // U9.1 och framåt
	public static final String FIND_AUCTION_METHOD = "findAuction"; // U9.2 - hjälpmetod tänkt att användas i de följande stegen
	public static final String MAKE_BID_METHOD = "makeBid"; // U9.3 och framåt
	public static final String LIST_BIDS_METHOD = "listBids"; // U9.4 och framåt
	public static final String LIST_AUCTIONS_METHOD = "listAuctions"; // U9.5 och framåt
	public static final String CLOSE_AUCTION_METHOD = "closeAuction"; // U9.6

	/********************************************************************************
	 * Här nedanför skriver du dina metoder. Du kommer att kunna lämna in samma
	 * fil(er) i samtliga inlämningar, så du behöver inte börja om för varje ny
	 * metod.
	 ********************************************************************************/
	private Scanner registrationScanner = new Scanner(System.in);
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private ArrayList<Owner> owners = new ArrayList<Owner>();
	private ArrayList<Auction> auctions = new ArrayList<Auction>();
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
	public void registerNewDog(Dog dog){ // För att slippa inmatning under testning
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
				Dog dog = dogs.get(i);
				if (dog.getTailLength() >= minTailLength) {
					atLeastOne = true;
					System.out.print(String.format("*%s ",dog.getName()));
					System.out.print(String.format("(%s, ",dog.getBreed()));
					System.out.print(String.format("%d years, ",dog.getAge()));
					System.out.print(String.format("%d kilo, ",dog.getWeight()));
					System.out.print(String.format("%.2f cm tail, ",dog.getTailLength()));
					if (dog.getHasOwner()){
						System.out.print(String.format("owned by %s)\n",dog.getOwner()));
					} else {
						System.out.print("no owner)\n");
					}
				} 
			}
			if (!atLeastOne) {
				System.out.println(
						String.format(
							"Error: No dogs with a tail of at least %.1f cm",
							minTailLength));
			}
		} else {
			System.out.println("Error: No dogs in list");
		}
	}
	private Dog findDog() { //För manuell inmatning
		String name = input.prompt("Enter the name of the dog",registrationScanner);
		for (int i = 0; i<dogs.size();i++){
			if (dogs.get(i).getName().equalsIgnoreCase(name)){
				return dogs.get(i);
			}
		}
		noSuchDog(name); // Ger användare en chans till	
		return null;
	}
	public Dog findDog(String name){ // För automatisk inmatning
		for (int i = 0; i<dogs.size();i++){
			if (dogs.get(i).getName().equalsIgnoreCase(name)){
				return dogs.get(i);
			}
		}
		noSuchDog(name); // Ger användare en chans till	
		return null;
	}
	private void noSuchDog(String name){
		System.out.println(String.format("Error: no dog named %s",name));
	}
	private void noSuchOwner(String name){
		System.out.println(String.format("Error: no owner named %s",name));
	}
	private void noDogs(){
		System.out.println("Error: No dogs in list");
	}
	public void increaseAge() {
		if (dogs.size()>0) {
			Dog dog = findDog();
			dog.increaseAge();
			System.out.println(String.format("%s is now one year older",dog.getName()));
		} else {
			noDogs();
		}
	}
	public void removeDog(){
		String name = input.prompt("Enter the name of the dog to remove",registrationScanner);
		removeDogTwo(name);
	}
	public void removeDog(String name){
		removeDogTwo(name);
	}
	private void removeDogTwo(String name){
		for (int i = 0; i<dogs.size(); i++){
			Dog dog = dogs.get(i);
			if (dog.getName().equals(name)){
				System.out.println("here");
				System.out.println(dog);
				if (dog.getHasOwner()){
					Owner owner = dog.getOwner();
					owner.removeDogFromOwner(dog);
					System.out.println(String.format("%s removed from %s",name,owner.getName()));
				} else if (checkIfDogInAuction(dog)){
					closeAuction(dog);	
				} else {
					System.out.println(String.format("%s removed",name));	
				}
				dogs.remove(i);
				return;
			}
		}
		System.out.println(String.format("Error: no dog named %s",name));
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
	private boolean compareAlphabetically(String stringOne, String stringTwo){
		if (stringOne.compareTo(stringTwo)>0){ // Ger positivt om s2 ligger före s1 alfabetiskt
			return true; // dvs, byt plats på dem 
		} else { // Annars, 
			return false; // Gör ingenting
		}
	}

	public void registerNewOwner(){
		String name = input.prompt("Name",registrationScanner);
		Owner owner = new Owner(name);
		System.out.println(String.format("%s added to the register",name));
		addOwner(owner);
	}
	public void registerNewOwner(Owner owner){ // För att slippa inmatning under test
		addOwner(owner);
	}

	public Owner findOwner() {// Manuell inmatning
		String name = input.prompt("Enter the name of the owner",registrationScanner);
		return findOwnerTwo(name);
	}
	public Owner findOwner(String name){//Automatisk inmatning
		return findOwnerTwo(name);
	}
	private Owner findOwnerTwo(String name){
		for (int i = 0; i<owners.size();i++){
			if (owners.get(i).getName().equalsIgnoreCase(name)){
				return owners.get(i);
			}
		} 
		System.out.println("Error: no such owner");
		return null;
	}
	private void alreadyHasOwner(){
		System.out.println("Error: the dog already has an owner");
	}
	public void giveDog(){
		Dog dog = findDog();
		if (dog.getHasOwner()){
			alreadyHasOwner();
		} else {
			Owner owner = findOwner();
			giveDogTwo(dog,owner);
		}
	}
	public void giveDog(Dog dog, Owner owner){	
		giveDogTwo(dog,owner);
	}
	private void giveDogTwo(Dog dog, Owner owner){
		dog.addOwner(owner);	
		//owner.addDog(dog);
		removeAuction(dog);
		System.out.println(String.format("%s now owns %s",owner.getName(),dog.getName()));
	}
	public void listOwners(){
		if (owners.size()>0){
			for (int i = 0; i<owners.size();i++){
				System.out.print(String.format("%s owns ",owners.get(i)));
				int nDogs = owners.get(i).getOwnedDogs().length;
				for (int j = 0; j<nDogs;j++){
					System.out.print(String.format("%s",owners.get(i).getOwnedDogs()[j].getName()));
					if (j<nDogs-2){
						System.out.print(", ");
					} else if (j==nDogs-2 && nDogs>1){
						System.out.print(" and ");
					}  
				}
				if (nDogs==0){
					System.out.print("no dogs");
				}
				System.out.print("\n");
			}
		} else {
			noOwners();
		}
	}
	private void noOwners(){
		System.out.print("Error: no owners registered");
	}
	public void removeOwner(){
		String name = input.prompt("Enter the name of the owner",registrationScanner);
		removeOwnerTwo(name);
	}
	public void removeOwner(String name){
		removeOwnerTwo(name);
	} 
	private void removeOwnerTwo(String name){
		Owner owner = findOwner(name);
		for (int i = 0;i<owners.size();i++){
			if (owner == owners.get(i)){
				removeDogsOfOwner(owner);
				removeBidsOfOwner(owner);
				owners.remove(i);
				System.out.println(String.format("%s removed",owner.getName()));
				return;
			}
		}	
		noSuchOwner(name);
	}
	private void removeDogsOfOwner(Owner owner){
		for (int i = dogs.size()-1;i>=0;i--){
			if (dogs.get(i).getOwner() == owner){
				removeDog(dogs.get(i).getName());
			}
		} 
	}
	private void removeBidsOfOwner(Owner owner){
		for (int i = 0; i<auctions.size();i++){
			Auction auction = auctions.get(i);
			Bid[] bidHistory = auction.getBidHistory();
			for (int j = bidHistory.length-1;j>=0;j--){
				if (owner == bidHistory[j].getBidder()){
					auction.removeBidFromAuction(bidHistory[j]);
				}
			}
		}
	}
	public void startAuction(){
		Dog dog = findDog();
		if (checkIfDogInAuction(dog)){
			System.out.println(String.format("Error: %s is already up for auction",dog.getName()));
		} else if (dog.getHasOwner()){
			System.out.println(String.format("Error: %s already has an owner",dog.getName()));
		} else {
			auctions.add(new Auction(dog));
			System.out.println(String.format(
					"%s has been put up for auction in auction #%d",
					dog.getName(),auctions.get(auctions.size()-1).getSerialNumber()));
		}
	}
	public void startAuction(Dog dog){
		if (checkIfDogInAuction(dog)){
			System.out.println(String.format("Error: %s is already up for auction",dog.getName()));
		} else if (dog.getHasOwner()){
			System.out.println(String.format("Error: %s already has an owner",dog.getName()));
		} else {
			auctions.add(new Auction(dog));
			System.out.println(String.format(
					"%s has been put up for auction in auction #%d",
					dog.getName(),auctions.get(auctions.size()-1).getSerialNumber()));
		}
	}
	private boolean checkIfDogInAuction(Dog dog){
		for (int i = 0;i<auctions.size();i++){
			if (dog == auctions.get(i).getDogForSale()){
				return true;
			}
		}
		return false;
	}
	public Auction findAuction(Dog dog){
		for (int i = 0;i<auctions.size();i++){
			if (auctions.get(i).getDogForSale()==dog){
				return auctions.get(i);
			}
		}	
		System.out.println(String.format("Error: Auction for %s doesn't exist",dog.getName()));
		return null;
	}
	public void makeBid(){
		int allowedAttempts = 2;
		Owner user = findOwner();
		if (user==null){
			throw new RuntimeException("");
		}
		Dog dog = findDog();
		if (dog.getHasOwner()){
			System.out.println(String.format("Error: %s is not up for sale",dog.getName()));	
		} else {
			Auction auction = findAuction(dog);
			/*if (user == auction.getHighestBid().getBidder()){
				System.out.println(String.format("Error: %s already has the highest bid",user.getName()));
				throw new RuntimeException();
			}*/
			int attempts = 0;
			while (attempts<allowedAttempts){
				int bidAmount = input.promptInt(String.format(
						"Amount to bid (min %d kr)",auction.getHighestBid().getBid()),
						registrationScanner); 
				if (auction.raiseBid(new Bid(bidAmount, user))){
					break;
				} else {
					attempts++;
				}
			}
		}
	}
	public void makeBid(Dog dog, Owner user, int bidAmount){
		int allowedAttempts = 2;
		if (user==null){
			throw new RuntimeException("");
		}
		if (dog.getHasOwner()){
			System.out.println(String.format("Error: %s is not up for sale",dog.getName()));	
		} else {
			Auction auction = findAuction(dog);
			/*if (user == auction.getHighestBid().getBidder()){
				System.out.println(String.format("Error: %s already has the highest bid",user.getName()));
				throw new RuntimeException();
			}*/
			int attempts = 0;
			while (attempts<allowedAttempts){
				if (auction.raiseBid(new Bid(bidAmount, user))){
					break;
				} else {
					attempts++;
				}
			}
		}
	}
	public void listBids(){
		Dog dog = findDog();
		Auction auction = findAuction(dog);
		listBidsTwo(auction);
	}
	public void listBids(Dog dog){
		Auction auction = findAuction(dog);
		listBidsTwo(auction);
	}
	private void listBidsTwo(Auction auction){
		ArrayList<Owner> alreadyDisplayed = new ArrayList<Owner>(0);
		System.out.println(String.format("Highest bids for the auction for %s",
					auction.getDogForSale().getName()));
		for (int i = auction.getBidHistory().length-1;i>0 && i>auction.getBidHistory().length-4;i--){
			if (!checkIfArrayListContains(auction.getBidHistory()[i].getBidder(),alreadyDisplayed)){
				System.out.println(String.format(
							"%s %d kr",
							auction.getBidHistory()[i].getBidder().getName(),
							auction.getBidHistory()[i].getBid()));	
				alreadyDisplayed.add(auction.getBidHistory()[i].getBidder());
			}
		}
	
	}
	private boolean checkIfArrayListContains(Owner owner,ArrayList<Owner> list){
		for (int i = list.size()-1;i>=0;i--){
			if (list.get(i)==owner){
				return true;
			}
		}
		return false;
	}
	public void listAuctions(){
		if (auctions.size()>0){
			for (int i = 0; i<auctions.size(); i++){
				System.out.println(String.format("Auction #%d: ",auctions.get(i).getSerialNumber()));
				listBids(auctions.get(i).getDogForSale());
			}
		} else {
			System.out.println("Error: no auctions in progress");
		}
	}
	public void closeAuction(){
		Dog dog = findDog();
		closeAuctionTwo(dog);
	}
	public void closeAuction(Dog dog){
		closeAuctionTwo(dog);
	}
	private void closeAuctionTwo(Dog dog){
		if (checkIfDogInAuction(dog)){
			Auction auction = findAuction(dog);
			System.out.print(String.format("Auction #%d, for %s is closed. ",
					auction.getSerialNumber(),auction.getDogForSale().getName()));
			if (auction.getBidHistory().length == 1){
				dogIsNotSold(dog);
			} else {
				dogIsSold(dog);
			}
			removeAuction(dog);
		} else {
			System.out.println("Error: this dog is not up for auction");
		}	
	}
	private void removeAuction(Dog dog){
		for (int i = 0; i<auctions.size(); i++){
			if (dog == auctions.get(i).getDogForSale()){
				auctions.remove(i);
				return;
			}
		}
	}
	private void dogIsSold(Dog dog){
		Auction auction = findAuction(dog);
		System.out.println(String.format(
			" The winning bid was %dkr and was made by %s",
			auction.getHighestBid().getBid(),
			auction.getHighestBid().getBidder().getName()));
		giveDog(dog,auction.getHighestBid().getBidder());
	}
	private void dogIsNotSold(Dog dog){
		System.out.println(String.format("No bids were made for ",dog.getName()));
	
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
	public void addOwner(Owner o) {
		// Ersätt raden nedan med NAMNPÅLISTAN.add(o); eller motsvarande anrop
		owners.add(o);
	}

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
	public Collection<Owner> getOwners() {
		// Ersätt raden nedan med return NAMNPÅSAMLINGEN; eller motsvarande anrop
		return owners;
	}

	/*
	 * Om du använder en array för att spara ägarna kan nedanstående variant
	 * användas istället
	 */
//	public Collection<Owner> getOwners() {
//		return Arrays.asList(NAMNET_PÅ_ARRAYEN);
//	}

}
