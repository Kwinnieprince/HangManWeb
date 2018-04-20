package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.*;
import domain.db.Woordenlijst;
import domain.model.Woord;

public class WoordenLijstTest {
	private Woord woord1 = new Woord("test", null);
	private Woord woord2 = new Woord("woord", "expert");
	private Woord woord3 = new Woord("test", null);
	private Woordenlijst lijst = new Woordenlijst(false);;


	@Test
	public void woord1_en_woord3_zijn_hetzelfde() {
		assertTrue(woord1.equals(woord3));
	}
	
	@Test
	public void woord1_en_woord2_zijn_verschillend() {
		assertFalse(woord1.equals(woord2));
	}
	
	@Test (expected = DomainException.class)
	public void woord_wordt_niet_toegevoegd_aan_lijst_als_het_er_al_in_zit() {
		lijst.voegWoordToe(woord1);
		assertTrue(lijst.voegWoordToe(woord2));
		assertFalse(lijst.voegWoordToe(woord3));
	}
}
