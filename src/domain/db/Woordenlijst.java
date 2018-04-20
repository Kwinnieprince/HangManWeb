package domain.db;

import java.util.ArrayList;

import domain.DomainException;
import domain.model.Woord;

public class Woordenlijst {
	private ArrayList<Woord> lijst;
	
	public Woordenlijst(boolean leeg) {
		this.lijst = new ArrayList<>();
		if (!leeg) {
		this.voegWoordToe(new Woord("overerving", "beginner"));
		this.voegWoordToe(new Woord("interface", null));
		this.voegWoordToe(new Woord("test first development", "expert"));
		this.voegWoordToe(new Woord("IllegalArgumentException", "expert"));
		}
	}
	
	public ArrayList<Woord> getAll() {
		return this.lijst;
	}
	
	public boolean voegWoordToe(Woord woord) {
		if (woord == null) throw new DomainException("Geen geldig woord om toe te voegen.");
		if (this.getAantalWoorden() > 0) {
			for(Woord w : this.lijst) {
				if (woord.equals(w)) throw new DomainException("Woord zit al in de lijst");
			}
		}
		this.lijst.add(woord);
		return true;
	}
	
	public boolean verwijderWoord(Woord woord) {
		if (woord == null) throw new DomainException("Geen geldig woord om toe te verwijderen.");
		if (this.getAantalWoorden() > 0) {
			for(Woord w : this.lijst) {
				if (woord.equals(w)) {
					this.lijst.remove(lijst.indexOf(w));
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
	
	public boolean verwijderWoord(int index) {
		if (index < 0 || index > this.getAantalWoorden()-1) throw new DomainException("Index ligt buiten bereik van woordenlijst.");
		this.lijst.remove(index);
		return true;
	}
	
	public Woord getWoord(int index) {
		if (index < 0 || index > this.getAantalWoorden()-1) throw new DomainException("Index ligt buiten bereik van woordenlijst.");
		return this.lijst.get(index);
	}
	
	public int getAantalWoorden() {
		return this.lijst.size();
	}
	
	public String getLangsteWoord() {
		if (this.getAantalWoorden() < 1) return " ";
		else {
			String langsteWoord = "";
			for (Woord woord : this.lijst) {
				if (langsteWoord.length()<woord.getWoord().length()) langsteWoord = woord.getWoord();
			}
			return langsteWoord;
		}
	}
	
	public String getKortsteWoord() {
		if (this.getAantalWoorden() < 1) return " ";
		else {
			String kortsteWoord = this.lijst.get(0).getWoord();
			for (Woord woord : this.lijst) {
				if (kortsteWoord.length()>woord.getWoord().length()) kortsteWoord = woord.getWoord();
			}
			return kortsteWoord;
		}
	}
	
	public Woordenlijst getLijstFilterExpert() {
		Woordenlijst nieuweLijst = new Woordenlijst(true);
		for (Woord woord : this.lijst) {
			if (woord.getNiveau().equals("expert")) nieuweLijst.voegWoordToe(woord);
		}
		return nieuweLijst;
	}
	
	public Woordenlijst getLijstFilterBeginner() {
		Woordenlijst nieuweLijst = new Woordenlijst(true);
		for (Woord woord : this.lijst) {
			if (woord.getNiveau().equals("beginner")) nieuweLijst.voegWoordToe(woord);
		}
		return nieuweLijst;
	}
	
	public int getGemiddeldVerschillendeLetters() {
		if (this.getAantalWoorden() < 1) return 0;
		int getal = 0;
		int lettersiInW = 0;
		boolean b = false;
		ArrayList<Character> letters = new ArrayList<>();
		for (Woord woord : this.lijst) {
			letters = new ArrayList<>();
			for (Character c : woord.getWoord().toCharArray()) {
				b = true;
				for (Character v : letters) {
					if (c.equals(v)) b = false;
				}
				if (b) letters.add(c);
			}
			getal+=letters.size();
		}
		return getal/this.getAantalWoorden();
	}
}
