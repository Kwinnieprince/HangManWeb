package domain.model;

import java.util.regex.Pattern;

import domain.DomainException;

public class Woord {
	private String woord;
	private String niveau;
	
	public Woord(String woord, String niveau) {
		if (niveau != null)niveau = niveau.toLowerCase();
		if (woord == null || woord.trim().isEmpty()) throw new DomainException("Geen geldig woord: " + woord);
		this.woord=woord;
		for (Character c : woord.toCharArray()) {
			String s=c+"";
			boolean p = Pattern.matches("^[a-zA-Z ]{1}$", s);
			if(!p) throw new DomainException("Woord mag het karakter \"" + s + "\" niet bevatten.");
		}
		this.niveau=niveau;
	}
	
	public String getNiveau() {
		if (this.niveau==null) return "";
		return this.niveau;
	}
	
	public String getWoord() {
		return this.woord;
	}
	
	@Override
	public String toString() {
		return this.woord;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		boolean b = false;
		if (o instanceof Woord) {
			b = true;
			if (!((Woord) o).getWoord().equals(this.woord)) b = false;
			if (this.getNiveau()==null) {
				if (((Woord)o).getNiveau()!=null) b = false;
			}
			if (((Woord)o).getNiveau()==null) {
				if (this.getNiveau()!=null) b = false;
			}
			if (((Woord)o).getNiveau()!=null&&this.getNiveau()!=null) {
				if (!((Woord)o).getNiveau().equals(getNiveau())) b = false;
			}
		}
		return b;
	}
}
