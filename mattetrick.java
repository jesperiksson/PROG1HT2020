import java.util.Scanner;
public class mattetrick {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Ange vilket år det är i år, t.ex. 2018:");
		int year = scan.nextInt();
		System.out.println("Ange din skostorlek, t.ex. 42:");
		int shoeSize = scan.nextInt();
		System.out.println("Ange ditt födelseår, t.ex. 1964:");
		int birth = scan.nextInt();

		int stepTwo = shoeSize * 5;
		int stepThree = stepTwo + 50;
		int stepFour = stepThree * 20;
		int stepFive = stepFour - 1000;
		int stepSix = stepFive + year;
		int stepSeven = stepSix - birth;
		
		System.out.println("Nu bör du kunna se ditt skonummer och din nuvarande ålder:"+stepSeven);	
	}	
}
