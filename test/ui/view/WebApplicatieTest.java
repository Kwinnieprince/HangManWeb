package ui.view;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebApplicatieTest {
	private WebDriver driver;
	private String url = "http://localhost:8080/project1_woordenlijst_groep13/Servlet";

	@Before
	public void setUp () {
		System.setProperty("webdriver.gecko.driver", "C:\\Util\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void woord_wordt_toegevoegd_aan_lijst_wanneer_alle_velden_juist_ingevuld() {
		this.navigeerNaarFormulier();
		voegWoordToe("testwoord", "Beginner");
		assertEquals("Woordenlijst", driver.getTitle());
		assertTrue(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "testwoord"));
		assertTrue(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "beginner"));
	}
	
	@Test
	public void woord_niet_toegevoegd_wanneer_woord_veld_leeg() {
		this.navigeerNaarFormulier();
		voegWoordToe("", "Beginner");
		assertEquals("Woord Toevoegen", driver.getTitle());
	}
	
	@Test
	public void woord_wordt_toegevoegd_aan_lijst_wanneer_geen_niveau_geselecteerd() {
		this.navigeerNaarFormulier();
		driver.findElement(By.id("woord")).sendKeys("eenTestWoord");
		driver.findElement(By.id("voegToe")).click();
		assertEquals("Woordenlijst", driver.getTitle());
		assertTrue(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "eenTestWoord"));
	}
	
	@Test
	public void index_pagina_toont_correcte_berekende_waarde_voor_totaal_aantal_woorden() {
		driver.findElement(By.id("naarWoordenLijst")).click();
		List<WebElement> tds = driver.findElements(By.tagName("tr"));
		int aantalWoordenBegin = tds.size()-1;
		driver.get(url);
		assertTrue(driver.findElement(By.tagName("p")).getText().contains(aantalWoordenBegin + " woorden"));
		this.navigeerNaarFormulier();
		this.voegWoordToe("woordToevoegen", "Expert");
		driver.get(url);
		assertTrue(driver.findElement(By.tagName("p")).getText().contains((aantalWoordenBegin++) + " woorden"));
	}
	
	@Test
	public void overzicht_filterd_correct_op_beginner() {
		this.filterOp("beginnerFilter");
		assertTrue(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "beginner"));
		assertFalse(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "expert"));
	}
	
	@Test
	public void overzicht_filterd_correct_op_expert() {
		this.filterOp("expertFilter");
		assertFalse(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "beginner"));
		assertTrue(paginaBevatTdMetText(driver.findElements(By.tagName("td")), "expert"));
	}
	
	@Test
	public void index_pagina_toont_correct_langste_woord() {
		this.navigeerNaarFormulier();
		String woord = "aqwxxszedxwqazesqsxsqdsqwxsqaze";
		this.voegWoordToe(woord, "Expert");
		driver.get(url);
		assertTrue(driver.findElement(By.tagName("p")).getText().contains((woord)));		
	}
	
	@Test
	public void index_pagina_toont_correct_kortste_woord() {
		this.navigeerNaarFormulier();
		String woord = "er";
		this.voegWoordToe(woord, "Expert");
		driver.get(url);
		assertTrue(driver.findElement(By.tagName("p")).getText().contains((woord)));	
	}
	
	private void filterOp(String filter) {
		driver.findElement(By.id("naarWoordenLijst")).click();
		driver.findElement(By.id(filter)).click();
	}
	
	private void voegWoordToe(String woord, String niveau) {
		driver.findElement(By.id("woord")).sendKeys(woord);
		new Select(driver.findElement(By.id("niveau"))).selectByVisibleText(niveau);
		driver.findElement(By.id("voegToe")).click();
	}
	
	private void navigeerNaarFormulier() {
		driver.findElement(By.id("naarWoordenLijst")).click();
		driver.findElement(By.id("nieuwWoord")).click();
	}
	
	private boolean paginaBevatTdMetText(List<WebElement> tds, String tekst) {
		for (WebElement td : tds) {
			if (td.getText().equals(tekst)) {
				return true;
			}
		}
		return false;
	}
}
