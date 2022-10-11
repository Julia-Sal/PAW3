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
	private String x;	//kwota
	private String y;	//ile miesiący
	private String z;	//oprocentowanie
	private Double result;	//rata miesięczna

	@Inject
	FacesContext ctx;
	//kwota
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
	//ile miesiący
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	//oprocentowanie
	public String getZ() {
		return y;
	}

	public void setZ(String z) {
		this.z = z;
	}
	//wynik
	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);
			double z = Double.parseDouble(this.z);

			result = Math.ceil((x + (z/100)*x*(y/12))/y);

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
