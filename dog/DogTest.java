package dog;
import java.util.ArrayList;
import java.util.Arrays;
public class DogTest {
	public static void main(String[] args){
		/*
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
		*/

		Assignment assignment = new Assignment();
		//assignment.registerNewDog();
		//System.out.print(assignment.getDogs());
		//assignment.registerNewDog();

		//Assignment assignment = new Assignment();
		assignment.registerNewDog(new Dog("fido6","shi tzu",17,73));
		assignment.registerNewDog(new Dog("f","shi tzu",13,5));
		assignment.registerNewDog(new Dog("fidod","pudel",5,13));
		assignment.registerNewDog(new Dog("fidoc","tax",6,5));
		assignment.registerNewDog(new Dog("fidob","tax",6,5));
		assignment.registerNewDog(new Dog("fidoa","tax",6,5));
		assignment.registerNewDog(new Dog("fidoe","shi tzu",13,5));
		assignment.registerNewDog(new Dog("fido1","schäfer",2,40));
		assignment.registerNewDog(new Dog("fido4","shi tzu",11,4));
		//assignment.listDogs();

		//System.out.println(assignment.findDog("fido2"));
		/*
		Assignment fail = new Assignment();
		//fail.listDogs();
		System.out.println(fail.findDog("q"));
		fail.registerNewDog(new Dog("fido10","rottweiler",1,50));
		System.out.println(fail.findDog("fido9"));
		fail.registerNewDog(new Dog("fido9","rottweiler",21,51));
		System.out.println(fail.findDog("fido9"));
		*/

		//assignment.increaseAge();
		/*
		assignment.removeDog();
		assignment.listDogs();
		assignment.removeDog();
		assignment.listDogs();
		assignment.removeDog();
		assignment.listDogs();
		assignment.removeDog();
		assignment.listDogs();
		*/
		//ArrayList<Dog> dogs = assignment.sortDogs();
		//assignment.listDogs();

		assignment.registerNewOwner(new Owner("Husse1"));	
		assignment.registerNewOwner(new Owner("Husse2"));	
		assignment.registerNewOwner(new Owner("Husse3"));	
		assignment.registerNewOwner(new Owner("h"));	
		assignment.giveDog();
		assignment.giveDog();


	}
}
