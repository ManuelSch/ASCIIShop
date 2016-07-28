/*
*Diese Klasse repr�sentiert einen Punkt, spezifiziert durch zwei ganzzahlige Koordinaten.
*Diese Klasse ist unver�nderlich (immutable), sprich die Koordinaten sollen nachtr�glich nicht mehr ver�nderbar sein.
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

	//gibt die x-Koordinate des Punktes zur�ck.
	public int getX()
	{
		return x;
	}

	//gibt die y-Koordinate des Punktes zur�ck.
	public int getY()
	{
		return y;
	}
		
	//gibt eine lesbare Darstellung des Punktes in der Form (x,y) zur�ck. 
	public String toString()
	{
		return ("("+x+","+y+")");
	}

}