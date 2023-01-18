package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private Double amount;	//kwota
	private Double time;	//ile miesiący
	private Double interest;	//oprocentowanie
	private Double result;	//rata miesięczna

	@Inject
	FacesContext ctx;
	
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;
	
	
	//kwota
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	//ile miesiący
	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}
	//oprocentowanie
	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}
	//wynik
	public Double getResult() {
		return result;
	}

	

	public boolean doTheMath() {
		try {
			
			result = Math.ceil((amount + (interest/100)*amount*(time/12))/time);
			return true;
			
		} catch (Exception e) {
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
