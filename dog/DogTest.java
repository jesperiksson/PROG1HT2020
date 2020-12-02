package dog;
public class DogTest {
	public static void main(String[] args){
		Dog fido = new Dog("Fido", "tax", 3, 3);	
		System.out.println(fido);
		fido.getOlder();
		System.out.println(fido);
		
		Dog buster = new Dog("Buster","Labrador",5,43);
		System.out.println(buster);
		buster.getOlder();
		System.out.println(buster);

		Dog fido2 = new Dog("Fido2", "mäyräkoira",4,4);
		System.out.println(fido2);
		fido2.getOlder();
		System.out.println(fido2);


		Dog fido3 = new Dog("Fido3", "TAX",4,4);
		System.out.println(fido3);
		fido3.getOlder();
		System.out.println(fido3);
	}
}
