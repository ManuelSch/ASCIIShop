/*
*Diese Klasse repräsentiert einen Punkt, spezifiziert durch zwei ganzzahlige Koordinaten.
*Diese Klasse ist unveränderlich (immutable), sprich die Koordinaten sollen nachträglich nicht mehr veränderbar sein.
*Stellen Sie dies durch den Einsatz geeigneter Modifier sicher. 
*/

public class AsciiPoint
{
	private int x,y;
	
	//erzeugt einen Punkt mit den angegebenen Koordinaten.
	public AsciiPoint(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

	//gibt die x-Koordinate des Punktes zurück.
	public int getX()
	{
		return x;
	}

	//gibt die y-Koordinate des Punktes zurück.
	public int getY()
	{
		return y;
	}
		
	//gibt eine lesbare Darstellung des Punktes in der Form (x,y) zurück. 
	public String toString()
	{
		return ("("+x+","+y+")");
	}

}