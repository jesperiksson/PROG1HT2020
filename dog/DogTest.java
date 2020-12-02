package dog;
public class DogTest {
	public static void main(String[] args){
		Dog fido = new Dog("Fido", "tax", 3, 2.4);	
		System.out.println(fido.getAge());
		fido.getOlder();
		System.out.println(fido.getAge());
	}
}
