package dog;
public class DogTest {
	public static void main(String[] args){
		Dog fido = new Dog("Fido", "tax", 3, 3);	
		System.out.println(fido);
		fido.increaseAge();
		System.out.println(fido);
		
		Dog buster = new Dog("Buster","Labrador",5,43);
		System.out.println(buster);
		buster.increaseAge();
		System.out.println(buster);

		Dog fido2 = new Dog("Fido2", "mäyräkoira",4,4);
		System.out.println(fido2);
		fido2.increaseAge();
		System.out.println(fido2);


		Dog fido3 = new Dog("Fido3", "TAX",4,4);
		System.out.println(fido3);
		fido3.increaseAge();
		System.out.println(fido3);

		Assignment assignment = new Assignment();
		assignment.registerNewDog();
		//System.out.print(assignment.getDogs());
		assignment.registerNewDog();

		//Assignment assignment = new Assignment();
		assignment.registerNewDog(new Dog("fido1","schäfer",2,40));

		assignment.registerNewDog(new Dog("fido2","pudel",5,13));
		assignment.registerNewDog(new Dog("fido3","tax",6,5));
		assignment.registerNewDog(new Dog("fido4","shi tzu",11,4));
		assignment.listDogs();

		Assignment fail = new Assignment();
		fail.listDogs();
	}
}
