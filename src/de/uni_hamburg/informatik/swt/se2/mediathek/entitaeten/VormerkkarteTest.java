package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.wertobjekte.Kundennummer;

public class VormerkkarteTest 
{
	private Medium _medium;
	private Kunde _kunde;
	private Vormerkkarte _vormerkkarte;
	
	public VormerkkarteTest()
	{
		_medium = new CD("bar", "baz", "foo", 123);
		_kunde = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbande");
		_vormerkkarte = new Vormerkkarte(_medium, _kunde);
	}
	
	@Test
	public void testKonstruktor()
	{
		assertEquals(_medium, _vormerkkarte.getMedium());
		assertEquals(true, _vormerkkarte.getVormerker().contains(_kunde));
	}
	
	@Test
	public void istVorgemerktTest()
	{
		assertEquals(true, _vormerkkarte.istVorgemerkt());
		_vormerkkarte.entferneKunde(_kunde);
		assertEquals(false, _vormerkkarte.istVorgemerkt());
	}
	
	@Test
	public void fuegeKundeHinzuTest()
	{
		Kunde kunde = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbanden");
		_vormerkkarte.fuegeKundeHinzu(kunde);
		assertTrue(_vormerkkarte.getVormerker().contains(kunde));
	}
	
	@Test
	public void entferneKundeTest()
	{
		_vormerkkarte.entferneKunde(_kunde);
		assertFalse(_vormerkkarte.getVormerker().contains(_kunde));
		assertFalse(_vormerkkarte.istVorgemerkt());
	}
	
	@Test
	public void istVormerkenMoeglichTest()
	{
		Kunde kundev2 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenen");
		Kunde kundev3 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenenene");
		Kunde kundev4 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandeneneneene");
		_vormerkkarte.fuegeKundeHinzu(kundev2);
		_vormerkkarte.fuegeKundeHinzu(kundev3);
		assertFalse(_vormerkkarte.istVormerkenMoeglich(kundev4));
		assertEquals(3, _vormerkkarte.getVormerker().size());
		assertFalse(_vormerkkarte.getVormerker().contains(kundev4));
	}
	
	@Test
	public void kundeKannNichtDoppeltVorgemerktWerdenTest()
	{
	    assertFalse(_vormerkkarte.istVormerkenMoeglich(_kunde));
	}
	
	@Test
	public void istErsterVormerkerTest()
	{
		Kunde kundev2 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenen");
		_vormerkkarte.fuegeKundeHinzu(kundev2);
		assertEquals(true, _vormerkkarte.istErsterVormerker(_kunde));
	}
	
	@Test
	public void getVormerkerTest()
	{
		Kunde kundev2 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenen");
		_vormerkkarte.fuegeKundeHinzu(kundev2);
		assertEquals(2, _vormerkkarte.getVormerker().size());
		assertTrue(_vormerkkarte.getVormerker().contains(kundev2));
		assertTrue(_vormerkkarte.getVormerker().contains(_kunde));
	}
	
	@Test 
	public void getVormerkerIndexSucheTest()
	{
		Kunde kundev2 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenen");
		Kunde kundev3 = new Kunde(new Kundennummer(187187), "Gzuz", "Straßenbandenenene");
		_vormerkkarte.fuegeKundeHinzu(kundev2);
		_vormerkkarte.fuegeKundeHinzu(kundev3);
		assertEquals(kundev2, _vormerkkarte.getVormerkerIndexSuche(1));
	}
}
