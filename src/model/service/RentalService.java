package model.service;

import model.entities.Invoice;
import model.entities.carRental;

public class RentalService {
	
	private Double pricePerDay;
	private Double pricePerHour;
	
	private TaxService taxService;

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	public void ProcessInvoice(carRental carrental) {
		long t1 = carrental.getStart().getTime();
		long t2 = carrental.getFinish().getTime();
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;
		double basicPayment;
		if(hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour;
			
		}
		else {
			double days = (double)(hours / 24);
			basicPayment = Math.ceil(days) * pricePerDay;
			
		}
		
		double tax = taxService.tax(basicPayment);
		
		carrental.setInvoice(new Invoice(basicPayment, tax));
		
	}
}
