package SoccerApp.utility;

import java.util.Scanner;

public class InputHandler {
	static Scanner scanner=new Scanner(System.in);
	
	public static int integerInput(String message) {
		int choice;
		while (true) {
			System.out.print(message);
			try {
				choice = scanner.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Gecersiz girdi, lütfen integer giriniz");
			}
			finally {
				scanner.nextLine();
			}
		}
		return choice;
	}
	
	public static int integerInput() {
		return integerInput("Secim yapiniz: ");
	}
}