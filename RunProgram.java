// Jesper Eriksson jeer6905


import java.util.*;
public class RunProgram {
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private ArrayList<Owner> owners = new ArrayList<Owner>();
	private ArrayList<Auction> auctions = new ArrayList<Auction>();
	private Input input = new Input();
	private boolean endProgram;
	private String command;
	
	public void programLoop(){
	    printCommands();
        while (!endProgram) {
            command = input.prompt("command ",scanner);
            switch(command){
                case "0":
                case "exit":
                    endProgram = true;
                    break;
                case "1":
                case "register new dog":
                    registerNewDog();
                    break;
                case "2":
                case "list dogs":
                    listDogs();
                    break;
                case "3":
                case "increase age":
                    increaseAge();
                    break;
                case "4":
                case "remove dog":
                    removeDog();
                    break;
                case "5":
                case "register new owner":
                    registerNewOwner();
                    break;
                case "6":
                case "give dog":
                    giveDog();
                    break;
                case "7":
                case "list owners":
                    listOwners();
                    break;
                case "8":
                case "remove owner":
                    removeOwner();
                    break;
                case "9":
                case "start auction":
                    startAuction();
                    break;
                case "10":
                case "make bid":
                    makeBid();
                    break;
                case "11":
                case "list bids":
                    listBids();
                    break;
                case "12":
                case "list auctions":
                    listAuctions();
                    break;
                case "13":
                case "close auction":
                    closeAuction();
                    break;
                default:
                    System.out.println("Error: command not recognized");
                    printCommands();
            }
        }
	}
    private void printCommands(){	
	    System.out.println("Here are the available commands: ");
	    System.out.println("[1] register new dog\n[2] list dogs\n[3] increase age\n[4] remove dog");
	    System.out.println("[5] register new owner\n[6] give dog\n[7] list owners\n[8] remove owner");
	    System.out.println("[9] start auction\n[10] make bid\n[11] list bids\n[12] list auctions");
	    System.out.println("[13] close auction\n[0] exit");
	}
	//############# REGISTRATION METHODS ##########
	public void registerNewDog(){
		String name = input.promptName("\nName", scanner);
		String breed = input.prompt("Breed", scanner);
		int age = input.convertToInt(input.prompt("Age", scanner));
		int weight = input.convertToInt(input.prompt("Weight", scanner));
		System.out.println(String.format("%s added to the register",name));
		Dog dog = new Dog(name, breed, age, weight);
		System.out.println(dog);
		addDog(dog);
		}
	public void addDog(Dog d) {
		dogs.add(d);
	}
	public void registerNewOwner(){
		String name = input.promptName("Name",scanner);
		Owner owner = new Owner(name);
		System.out.println(String.format("%s added to the register",name));
		addOwner(owner);
	}
	public void addOwner(Owner o) {
		owners.add(o);
	}
	public void giveDog(){
		Dog dog = findDog();
		if (dog.getHasOwner()){
			alreadyHasOwner();
		} else {
			Owner owner = findOwner();
			if (owner != null){
			giveDogTwo(dog,owner);
			} else {
			    System.out.print(" ");
			}
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
	public void startAuction(){
		Dog dog = findDog();
		if (dog != null){
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
		} else {
		    System.out.print(" ");
		}
	}
	public void makeBid(){
		int allowedAttempts = 10;
		Owner user = findOwner();
		if (user==null){
			throw new RuntimeException("");
		}
		Dog dog = findDog();
		if (dog.getHasOwner()){
			System.out.println(String.format("Error: %s is not up for sale",dog.getName()));	
		} else {
			Auction auction = findAuction(dog);
			int attempts = 0;
			while (attempts<allowedAttempts){
				int bidAmount = input.promptInt(String.format(
						"Amount to bid (min %d kr)",auction.getHighestBid().getBid())+1,
						scanner); 
				if (auction.raiseBid(new Bid(bidAmount, user))){
					break;
				} else {
					attempts++;
				}
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
	public String waitForUserInput() {
		return input.waitForEnter(scanner);
	}
	public void increaseAge() {
		Dog dog = findDog();
		if (dog != null){
    		dog.increaseAge();
	    	System.out.println(String.format("%s is now one year older",dog.getName()));
        } 
	}
	// ############ LIST METHODS ##########	
	public void listDogs(){
	    sortDogs();
	    sortDogs();
		boolean atLeastOne = false;
		if (dogs.size()>0){
			double minTailLength = input.convertToFloat(
					input.prompt(
						"Smallest tail length to display",
						scanner));
			System.out.println("The following dogs has such a large tail:");
			for (int i = 0; i<dogs.size(); i++) {
				Dog dog = dogs.get(i);
				if (dog.getTailLength() >= minTailLength) {
					atLeastOne = true;
                    presentDog(dog);
				} 
			}
			if (!atLeastOne) {
				System.out.println(String.format("Error: No dogs with a tail of at least %.1f cm",minTailLength));
			}
		} else {
			System.out.println("Error: No dogs in list");
		}
	}
	public void listOwners(){
		if (owners.size()>0){
			for (int i = 0; i<owners.size();i++){
				System.out.print(String.format("%s owns ",owners.get(i)));
				int numDogs = owners.get(i).getNumberOfOwnedDogs();
				for (int j = 0; j<numDogs;j++){
					System.out.print(String.format("%s",owners.get(i).getOwnedDogs()));
					if (j<numDogs-2){
						System.out.print(", ");
					} else if (j==numDogs-2 && numDogs>1){
						System.out.print(" and ");
					}  
				}
				if (numDogs==0){
					System.out.print("no dogs");
				}
				System.out.print("\n");
			}
		} else {
			noOwners();
		}
	}
	private void presentDog(Dog dog){
	    System.out.print(String.format("*%s ",dog.getName()));
	    System.out.print(String.format("(%s, ",dog.getBreed()));
	    System.out.print(String.format("%d years, ",dog.getAge()));
	    System.out.print(String.format("%d kilo, ",dog.getWeight()));
	    System.out.print(String.format("%.1f cm tail, ",dog.getTailLength()));
	    if (dog.getHasOwner()){
		    System.out.print(String.format("owned by %s)\n",dog.getOwner()));
	    } else {
		    System.out.print("no owner)\n");
	    }
	}
	public void listBids(){// Anropas från user, alla bud
		Dog dog = findDog();
		Auction auction = findAuction(dog);
		ArrayList<Owner> alreadyDisplayed = new ArrayList<Owner>(0);
		System.out.println(String.format("Highest bids for the auction for %s",
					auction.getDogForSale().getName()));
		for (int i = auction.getBidHistory().length-1;i>0;i--){
			if (!alreadyDisplayed.contains(auction.getBidHistory()[i].getBidder())){
				System.out.println(String.format(
							"%s %d kr",
							auction.getBidHistory()[i].getBidder().getName(),
							auction.getBidHistory()[i].getBid()));	
				alreadyDisplayed.add(auction.getBidHistory()[i].getBidder());
			}
		}
	}
	public void listBids(Dog dog){ // Anropas från listAuction, 3 bud
	    int numBids = 3;
		Auction auction = findAuction(dog);
		ArrayList<Owner> alreadyDisplayed = new ArrayList<Owner>(0);
		System.out.println(String.format("Highest bids for the auction for %s",
					auction.getDogForSale().getName()));
		int i = auction.getBidHistory().length-1;
		int j = 0;
		while (i>0 && j<numBids){
			if (!alreadyDisplayed.contains(auction.getBidHistory()[i].getBidder())){
				System.out.println(String.format(
							"%s %d kr",
							auction.getBidHistory()[i].getBidder().getName(),
							auction.getBidHistory()[i].getBid()));	
				alreadyDisplayed.add(auction.getBidHistory()[i].getBidder());
				j++;
			}
			i--;
		}
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
	// ############### FIND/GET METHODS ###########
	private Dog findDog() { //För manuell inmatning
		String name = input.promptName("Enter the name of the dog",scanner);
		for (int i = 0; i<dogs.size();i++){
			if (dogs.get(i).getName().equalsIgnoreCase(name)){
				return dogs.get(i);
			}
		}
		noSuchDog(name); // Ger användare en chans till	
		return null;
	}
	public Owner findOwner() {// Manuell inmatning
		String name = input.promptName("Enter the name of the owner",scanner);
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
	//public Collection<Owner> getOwners() {
	//	return owners;
	//}
	// ########## REMOVE METHODS ############
	public void removeDog(){
		String name = input.promptName("Enter the name of the dog to remove",scanner);
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
		noSuchDog(name);
	}
	public void removeOwner(){
		String name = input.promptName("Enter the name of the owner",scanner);
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
	// ########## SORTING METHODS ###########
	// InsertionSort, informationen hittade jag här: https://www.geeksforgeeks.org/insertion-sort/
	public void sortDogs(){
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
		sortDogsByName(sortedDogs);
	}
	private void sortDogsByName(ArrayList<Dog> list){
	
		// Sortera hundar med samma svanslängd på namn
		int i = 0;
		while (i+1 <list.size()){
		//for (int i = 1; i<list.size();i++){
			// Åtminstone två hundar har samma svanslängd x
			if (list.get(i).getTailLength()== list.get(i+1).getTailLength()) { 
				int j = 1;
				if (i+2<list.size()){ // Så att man inte går utanför
					while (list.get(i).getTailLength() == list.get(i+j+1).getTailLength() && j+i<list.size()-2){ // Ifall alla hundar har lika lång svans
						j++; // j är antalet hunder med svanslängd x
					}
				}
				for (int k = i+1;k<i+j+1;k++){
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
				//i = i + j; // Hoppa över redan sorterade hundar
			}
			i++;
		}
		dogs = list;
	}
	private boolean compareAlphabetically(String stringOne, String stringTwo){
	    int compare = stringOne.compareTo(stringTwo);
		if (compare>0){ // Ger positivt om s2 ligger före s1 alfabetiskt
			return true; // dvs, byt plats på dem 
		}
		return false; // Gör ingenting
	}
	//########### ERROR MESSAGES ############
	private void noSuchDog(String name){
		System.out.println(String.format("Error: no dog named %s",name));
	}
	private void noSuchOwner(String name){
		System.out.println(String.format("Error: no owner named %s",name));
	}
	private void noDogs(){
		System.out.println("Error: No dogs in list");
	}
	private void alreadyHasOwner(){
		System.out.println("Error: the dog already has an owner");
	}
	private void noOwners(){
		System.out.print("Error: no owners registered");
	}
}
