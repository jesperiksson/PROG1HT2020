// Jesper Eriksson jeer6905
package dog;
class Auction{
	private static int counter = 0;
	private int serialNumber;
	private Dog dogForSale;
	private Bid highestBid;
	private Bid[] bidHistory = new Bid[0];
	public Auction(Dog dog){
		this.serialNumber = ++counter;
		this.dogForSale = dog;
		Bid initialBid = new Bid(1,new Owner("initialBid"));
		addBid(initialBid);
		this.highestBid = initialBid;
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
	public Bid getHighestBid(){
		return this.highestBid;
	}
	public Bid[] getBidHistory(){
		return this.bidHistory;
	}
	public boolean raiseBid(Bid newBid){
		if (newBid.getBid() > this.highestBid.getBid()){
			this.highestBid = newBid;
			addBid(newBid);
			System.out.println("Bid made");
			return true;
		} else {
			System.out.println("Error: bid too low");
			return false;
		}
	}
	private void addBid(Bid bid){
		int n = this.bidHistory.length + 1;
		Bid[] newArr = new Bid[n];
		for (int i = 0; i<n-1; i++){
			newArr[i] = this.bidHistory[i];
		}
		newArr[n-1] = bid;
		this.bidHistory =  newArr;
	}
}
