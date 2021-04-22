package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Vehicle;
import model.entities.carRental;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args)  throws ParseException{
		
		Locale.setDefault(Locale.US);
		
		Scanner sc =  new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("ENTER A RENTAL CAR DETAILS:");
		System.out.print("Enter a car model: ");
		String carModel = sc.nextLine();
		System.out.print("Enter a start Date: ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Enter a finish Date: ");
		Date finish = sdf.parse(sc.nextLine());
		
		carRental cr = new carRental(start, finish, new Vehicle(carModel));
		System.out.print("Price per Hour: ");
		double perHour = sc.nextDouble();
		System.out.print("Price per Day");
		double perDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(perDay, perHour, new BrazilTaxService());
		rentalService.ProcessInvoice(cr);
		
		System.out.println("INVOICE DETAILS");
		System.out.println("Basic Payment: " + String.format("%.2f",cr.getInvoice().getBasicPayment()));
		System.out.println("Tax Payment: " +  String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total Payment : " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
		
		
		
		
		
		
		
		
		
		
		
		
		sc.close();
		
		

	}

}
