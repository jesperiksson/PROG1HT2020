// Jesper Eriksson jeer6905

class Dog {
	private String name;
	private String breed;
	private int age;
	private int weight;	
	private double tail;
	private Owner owner;
	private String[] taxAlias = {"tax", "dachshund","mäyräkoira","teckel"};
	private boolean hasOwner;
	private final float taxTail = 3.7f;

	Dog(String name, String breed, int age, int weight){
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
		if (isInArray(this.taxAlias, this.breed)){
			this.tail = taxTail;
		}else{
			this.tail = (float)this.age*this.weight/10.0f;
		}
	}
	public void increaseAge(){
		this.age++;
		if (isInArray(this.taxAlias, this.breed)){
			this.tail = taxTail;
		}else{
			this.tail = (float)this.age*this.weight/10.0f;
		}
	}
	public String getName(){
		return this.name;
	}
	public String getBreed() {
		return this.breed;
	}
	public int getAge(){
		return this.age;
	}
	public int getWeight(){
		return this.weight;
	}	
	public double getTailLength(){
		return this.tail;
	}
	public boolean getHasOwner(){
		return this.hasOwner;
	}
	public Owner getOwner(){
		return this.owner;
	}
	public String toString(){
		return String.format(
			"Name: %s, Breed: %s, Age: %d, Weight: %d, Tail length: %.1f",
			this.name, this.breed, this.age, this.weight, this.tail);
	}
	public boolean isInArray(String[] arr, String targetValue) {
		for(String s: arr){
			if(s.equals(targetValue.toLowerCase()))
				return true;
		}
		return false;
	}
	public void addOwner(Owner owner){
		if (!this.hasOwner){
			this.owner = owner;
			this.hasOwner = true;
			owner.addDog(this);
		}
	}
}

