// Jesper Eriksson jeer6905
package dog;
class Dog {
	private String name;
	private String breed;
	private int age;
	private double weight;	
	private double tail;
	private static String[] taxAlias = {"tax", "dachshund",
						"mäyräkoira","teckel"};

	public Dog(String name, String breed, int age, double weight){
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
		
	}

	public void getOlder(){
		this.age++;
	}
	
	public int getAge(){
		return this.age;
	}	
}

