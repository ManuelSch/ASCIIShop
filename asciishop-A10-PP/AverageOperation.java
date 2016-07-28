/*
Diese Klasse gl�ttet ein Bild mit einem 3x3-Mittelwertfilter.
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
	
	//f�hrt mit dem �bergebenen Block den Mittelwertfilter aus. Daf�r wird das arithmetische Mittel (vgl. Arithmetisches_Mittel) der Helligkeitswerte bestimmt. Das Ergebnis wird mathematisch gerundet und als Ergebnis f�r diesen Block zur�ckgegeben.
	public int filter(int[] values)
	{
		double sum=0;
		
		//Summe der Helligkeitswerte bilden:
		for(int i=0; i<values.length; i++)
		{
			sum+=values[i];
		}
		
		//Mittelwert bilden und mathematisch gerundet zur�ckgeben:
		return (int)Math.round(sum/values.length);
	}
	
}