// Jesper Eriksson jeer6905
package dog;
class Auction{
	private static int counter = 0;
	private int serialNumber;
	private Dog dogForSale;
	public Auction(Dog dog){
		this.serialNumber = ++counter;
		this.dogForSale = dog;
	}
	public String toString(){
		return String.format("Auction # %d\nDog for sale: %s",this.serialNumber,this.dogForSale.getName());
	}
	public int getSerialNumber(){
		return this.serialNumber;
	}
	public Dog getDogForSale(){
		return this.dogForSale;
	}
}
