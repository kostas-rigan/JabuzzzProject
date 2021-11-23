package gr.aueb.dmst.jabuzzz.entities;
public class Category {
	boolean mythologia;
	boolean geografia;
	boolean istoria;
	
	public Category() {
		mythologia = false;
		geografia = false;
		istoria = false;
	}
	public void setGeografia(boolean geografia) {
		this.geografia = geografia;
	}
	public void setIstoria(boolean istoria) {
		this.istoria = istoria;
	}
	public void setMythologia(boolean mythologia) {
		this.mythologia = mythologia;
	}
	public boolean getGeografia() {
		return geografia;
	}
	public boolean getMythologia() {
		return mythologia;
	}
	public boolean getIstoria() {
		return istoria;
	}
	

}
