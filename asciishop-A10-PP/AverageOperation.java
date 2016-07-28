/*
Diese Klasse glättet ein Bild mit einem 3x3-Mittelwertfilter.
*/
import java.util.*; 

public class AverageOperation extends FilterOperation
{
	//erzeugt eine neue AverageOperation.
	public AverageOperation()
	{
	}
	
	//geerbt von FilterOperation
	//public AsciiImage execute(AsciiImage img);
	
	//führt mit dem übergebenen Block den Mittelwertfilter aus. Dafür wird das arithmetische Mittel (vgl. Arithmetisches_Mittel) der Helligkeitswerte bestimmt. Das Ergebnis wird mathematisch gerundet und als Ergebnis für diesen Block zurückgegeben.
	public int filter(int[] values)
	{
		double sum=0;
		
		//Summe der Helligkeitswerte bilden:
		for(int i=0; i<values.length; i++)
		{
			sum+=values[i];
		}
		
		//Mittelwert bilden und mathematisch gerundet zurückgeben:
		return (int)Math.round(sum/values.length);
	}
	
}