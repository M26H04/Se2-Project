package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;
import java.util.LinkedList;
import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.Medium;
/**
 * Mit Hilfe von Vormerkkarten werden beim Vormerken eines Mediums alle relevanten
 * Daten notiert.
 * 
 * 
 * @author Coole SE2 Gruppe
 * @version SoSe 2025
 */
public class Vormerkkarte 
{
	//Eigenschaften
	private final Medium _medium;
	private LinkedList<Kunde> _vormerker;
	
	/**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param medium Ein Medium.
     * @param kunde Ein Kunde
     * 
     * @require medium != null
     * @require kunde != null
     * 
     * @ensure #getMedium() == medium
     * @ensure #getVormerker.contains(kunde)
     */
	public Vormerkkarte(Medium medium, Kunde kunde)
	{
		assert medium != null : "Vorbedingung verletzt: medium != null";
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		_medium = medium;
		_vormerker = new LinkedList<Kunde>();
		_vormerker.add(kunde);
		
	}
	
	/**
	 * Überprüft ob vorgemerkt
	 * 
	 * @return ture oder false
	 */
	public boolean istVorgemerkt()
	{
		return (!_vormerker.isEmpty());
	}
	
	/**
	 * Fügt Kunden hinzu
	 * 
	 * @param kunde Kunde der rein soll
	 * 
	 * @require kunde != null
	 */
	public void fuegeKundeHinzu(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		assert istVormerkenMoeglich(kunde) : "Vorbedingung verletzt: istVormerkenMoeglich(kunde)";
		_vormerker.add(kunde);
		
	}
	
	/**
	 * Entfernt Kunde
	 * 
	 * @param kunde Kunde der weg soll
	 * 
	 * @require kunde != null
	 */
	public void entferneKunde(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		assert _vormerker.contains(kunde) : "Vorbedingung verletzt: _vormerker.contains(kunde)";
		_vormerker.remove(kunde);
	}
	
	/**
	 * Überprüft ob Kunde vormerken kann
	 * 
	 * @param kunde Kunde
	 * 
	 * @return true oder false
	 * 
	 * @require kunde != null
	 */
	public boolean istVormerkenMoeglich(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		if(!(_vormerker.contains(kunde)) && _vormerker.size() < 3)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Überprüft ob Kunde erster Vormerker ist
	 * 
	 * @param kunde Kunde
	 * 
	 * @return true oder false
	 * 
	 * @require kunde != null
	 */
	public boolean istErsterVormerker(Kunde kunde)
	{
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		return _vormerker.get(0).equals(kunde);
	}
	
	/**
	 * Sucht Kunde über Index
	 * 
	 * @param index Index
	 * 
	 * @return Kunde von Index
	 * 
	 * @require index <= null
	 * @require jndex >= 0
	 */
	public Kunde getVormerkerIndexSuche(int index)
	{
		assert index <= _vormerker.size() : "Vorbedingung verletzt: index <= _vormerker.size()";
		assert index >= 0 : "Vorbedingung verletzt: index <= 0";
		return _vormerker.get(index);
	}
	
	/**
	 * Gibt Medien der Karte wieder
	 * 
	 * @return Medium
	 */
	public Medium getMedium()
	{
		return _medium;
	}
	
	/**
	 * Gibt Liste der Vormerker wieder
	 * 
	 * @return Liste
	 */
	public LinkedList<Kunde> getVormerker()
	{
		return _vormerker;
	}
	
	@Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_medium == null) ? 0 : _medium.hashCode());
        result = prime * result
                + ((_vormerker == null) ? 0 : _vormerker.hashCode());
        return result;
    }
	
	@Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Vormerkkarte)
        {
            Vormerkkarte other = (Vormerkkarte) obj;

            if (other.getMedium()
                .equals(_medium)
                    && other.getVormerker()
                        .equals(_vormerker))


                result = true;
        }
        return result;
    }
}
