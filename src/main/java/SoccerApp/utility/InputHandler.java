package SoccerApp.utility;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	
	public static LocalDate localDateInput() {
		return localDateInput("Enter localdate");
	}
	
	public static LocalDate localDateInput(String message) {
		do{
			System.out.println(message);
			System.out.print("YYYY-MM-DD> ");
			String tempLocalDate = scanner.nextLine();
			try{
				return LocalDate.parse(tempLocalDate);
			} catch (DateTimeParseException e) {
				System.out.println("format error..." + e.getMessage());
			}
		}while(true);
	}
	
	// TODO private static numberInput(String message, Class clazz){}
	
	public static double doubleInput(){
		return doubleInput("Double değer giriniz");
	}
	
	public static double doubleInput(String message) {
		double choice;
		while (true) {
			System.out.print(message);
			try {
				choice = scanner.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Gecersiz girdi, lütfen double giriniz");
			}
			finally {
				scanner.nextLine();
			}
		}
		return choice;
	}
}