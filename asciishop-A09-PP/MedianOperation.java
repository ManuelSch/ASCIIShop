/*
Diese Klasse gl�ttet ein Bild mit einem 3x3-Medianfilter (vgl. Rangordnungsfilter#Medianfilter).
*/
import java.util.Arrays; 

public class MedianOperation extends FilterOperation
{
	//erzeugt eine neue MedianOperation.
	public MedianOperation()
	{
	}
	
	//geerbt von FilterOperation
	//public AsciiImage execute(AsciiImage img);
	
	//f�hrt mit dem �bergebenen Block den Medianfilter aus. Die Pixel des Blocks werden nach ihrem Helligkeitswert sortiert. Der Median (also das in der sortierten Liste in der Mitte stehende Zeichen) f�r diesen Block wird als Ergebnis zur�ckgegeben.
	public int filter(int[] values)
	{				
		return values[4];		//Mittlerer Wert des sortierten Arrays zur�ckgeben
	}
}