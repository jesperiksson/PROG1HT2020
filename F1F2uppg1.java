

/*Skriv ett program som läser in en temperatur i grader Celsius och skriver 
 ut motsvarande temperatur i grader Fahrenheit.*/
import java.util.Scanner;
public class F1F2uppg1 {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);	
		System.out.print("Celsius:");
		double degC = myScanner.nextFloat();
		double degF = degC * (double) 1.8 + (double) 32.0;
		System.out.print(" Fahrenheit = "+degF);
	}
}	
