// Jesper Eriksson jeer6905
import java.util.*; 
public class Owner{
	private String name;
	private Dog[] ownedDogs = new Dog[0];

	Owner(String name){
		this.name = name;
	}
	public String toString(){
		return String.format("Name: %s",this.name);
	}
	public String getName(){
		return this.name;
	}
	public int getNumberOfOwnedDogs(){
		return this.ownedDogs.length;
	}
	public String getOwnedDogs(){
	    return Arrays.toString(this.ownedDogs);
	}
	public void addDog(Dog dog){
		if (!ownerOfDog(dog)){
			this.ownedDogs = addDogToArray(this.ownedDogs,dog);
			dog.addOwner(this);
		}
	}
	private Dog[] addDogToArray( Dog[] arr,Dog dog){
		int n = arr.length + 1;
		Dog[] newArr = new Dog[n];
		for (int i = 0; i<n-1; i++){
			newArr[i] = arr[i];
		}
		newArr[n-1] = dog;
		return newArr;
	}
	public boolean ownerOfDog(Dog dog){
		for (int i = 0; i<this.ownedDogs.length;i++){
			if (ownedDogs[i].getName().equals(dog.getName())){
				return true;
			}
		} 
		return false;
	}
	public void removeDogFromOwner(Dog dog){
		Dog[] newOwnedDogs = new Dog[this.ownedDogs.length-1];
		int i = 0;
		int j = 0;
		while (i<this.ownedDogs.length){
			if (!this.ownedDogs[i].getName().equals(dog.getName())){
				newOwnedDogs[j] = ownedDogs[i];
				i++;
				j++;
			} else {
				i++;
			}
		}
		this.ownedDogs = newOwnedDogs;
	}
}
