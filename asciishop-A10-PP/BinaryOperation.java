/*
Diese Klasse wandelt ein AsciiImage in ein Binärbild um.
*/

import java.util.Scanner;

public class BinaryOperation implements Operation
{
	private char threshold;
	
	//erzeugt eine neue BinaryOperation mit dem entsprechenden Schwellwert.
	public BinaryOperation(char threshold)
	{
		this.threshold=threshold;
	}
	
	//gibt ein neues AsciiImage zurück, das das Binärbild des übergebenen AsciiImage ist. Zur Umwandlung in ein Binärbild werden alle Zeichen, die im Zeichensatz des Bildes vor dem Schwellwert kommen, auf das dunkelste Zeichen gesetzt, alle Zeichen ab dem Schwellwert werden auf das hellste Zeichen gesetzt. Sollte das Schwellwertzeichen nicht im Zeichensatz des AsciiImage vorkommen, so wird eine OperationException geworfen.
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		String charset = img.getCharset();
		AsciiImage image = new AsciiImage(img);
		
		//Ist der angegebene Threshold überhaupt im Zeichensatz des Bildes vorhanden?
		if (charset.indexOf(threshold) < 0)
		{
			throw new OperationException("Zeichen nicht im Zeichensatz enthalten");
		}
		else
		{
			//Alle Zeichen durchlaufen:
			for(int y=0; y<image.getHeight(); y++)
			{
				for(int x=0; x<image.getWidth(); x++)
				{
					if(charset.indexOf(img.getPixel(x,y))<charset.indexOf(threshold))
					{
						//Aktueller Pixel ist dunkler als der Schwellenwert:
						image.setPixel(x,y,charset.charAt(0));
					}
					else
					{
						//Aktueller Pixel ist heller oder gleich dem Schwellenwert:
						image.setPixel(x,y,charset.charAt(charset.length()-1));
					}
					
				}
			}
		}
		
		return image;
	}
}