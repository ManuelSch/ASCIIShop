/*
Diese Klasse setzt alle Pixel des Bildes auf das hellste Zeichen.
*/

public class ClearOperation implements Operation
{
	//erzeugt eine neue ClearOperation.
	public ClearOperation()
	{
	
	}
	
	//gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, wobei alle Zeichen auf das hellste Zeichen, sprich dem letzten Zeichen im Zeichensatz des Bildes, gesetzt sind.
	public AsciiImage execute(AsciiImage img)
	{
		String charset=img.getCharset();
		AsciiImage image = new AsciiImage(img);
		
		//Alle Zeichen initialisieren
		for(int y=0; y<img.getHeight(); y++)
		{
			for(int x=0; x<img.getWidth(); x++)
			{
				image.setPixel(x,y,charset.charAt(charset.length()-1));
			}
		}
		
		return image;
	}
}