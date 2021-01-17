//Jesper Eriksson jeer6905

class Bid{
	private int bid;
	private Owner bidder;

	Bid(int bid,Owner bidder){
		this.bid = bid;
		this.bidder = bidder;
	}
	public int getBid(){
		return this.bid;
	}
	public Owner getBidder(){
		return this.bidder;
	}
	public String toString(){
		return String.format("Bid: %d",this.bid);
	}
}
