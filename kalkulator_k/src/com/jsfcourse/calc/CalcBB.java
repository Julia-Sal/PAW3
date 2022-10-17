package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String amount;	//kwota
	private String time;	//ile miesiący
	private String interest;	//oprocentowanie
	private Double result;	//rata miesięczna

	@Inject
	FacesContext ctx;
	//kwota
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	//ile miesiący
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	//oprocentowanie
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
	//wynik
	public Double getResult() {
		return result;
	}

	

	public boolean doTheMath() {
		try {
			
			double amount = Double.parseDouble(this.amount);
			double time = Double.parseDouble(this.time);
			double interest = Double.parseDouble(this.interest);

			result = Math.ceil((amount + (interest/100)*amount*(time/12))/time);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
