/*
Diese abstrakte Klasse beinhaltet die Funktionalität, um das Bild zu durchlaufen und für jeden Pixel den benötigten Block an Nachbarpixeln zu bestimmen. Sie bietet mit der Methode filter eine Schablone (Template) für die konkreten Filter Operationen.
*/
import java.util.Arrays; 

public abstract class FilterOperation implements Operation
{
	//Konstruktor der FilterOperation.
	public FilterOperation()
	{
	
	}
	
	//führt den Blockfilter aus. Diese Methode muss von abgeleiteten Klassen nicht überschrieben werden. Die Methode durchläuft das Bild, bestimmt für jeden Pixel den Block an Nachbarpixeln und ruft damit dann die Methode filter auf. Das Ergebnis dieser Methode wird dann als neuer Pixel an der aktuellen Stelle gesetzt.
	public AsciiImage execute(AsciiImage img)
	{
		String charset=img.getCharset();
		AsciiImage image = new AsciiImage(img);
		
		//Alle Zeichen durchlaufen:
		for(int y=0; y<image.getHeight(); y++)
		{
			for(int x=0; x<image.getWidth(); x++)
			{
				int[] pix = new int[9];		//8 Nachbarpixel + 1 aktueller Pixel
				
				//Alle 9 Pixel standardmäßig auf die Hintergrundfarbe setzen:
				for(int i=0; i<pix.length; i++)
				{
					pix[i]=charset.indexOf(charset.charAt(charset.length()-1));
				}
				
				//Nachbarpixel einlesen:
				//Pixel links oben:
				if((x-1>=0)&&(y-1>=0))
				{
					pix[0]=charset.indexOf(img.getPixel(x-1,y-1));
				}
				//Pixel links unten:
				if((x-1>=0)&&(y+1<image.getHeight()))
				{
					pix[1]=charset.indexOf(img.getPixel(x-1,y+1));
				}
				//Pixel rechts unten:
				if((x+1<image.getWidth())&&(y+1<image.getHeight()))
				{
					pix[3]=charset.indexOf(img.getPixel(x+1,y+1));
				}
				//Pixel rechts oben:
				if((x+1<image.getWidth())&&(y-1>=0))
				{
					pix[2]=charset.indexOf(img.getPixel(x+1,y-1));
				}
				//Pixel oben:
				if(y-1>=0)
				{
					pix[4]=charset.indexOf(img.getPixel(x,y-1));
				}
				//Pixel unten:
				if(y+1<image.getHeight())
				{
					pix[5]=charset.indexOf(img.getPixel(x,y+1));
				}
				//Pixel links:
				if(x-1>=0)
				{
					pix[6]=charset.indexOf(img.getPixel(x-1,y));
				}
				//Pixel rechts:
				if(x+1<image.getWidth())
				{
					pix[7]=charset.indexOf(img.getPixel(x+1,y));
				}
				//Pixel in der Mitte:
				pix[8]=charset.indexOf(img.getPixel(x,y));
				
				//Array sortieren:
				Arrays.sort(pix);
				
				//Filter auf aktuellen Pixel ausführen:
				image.setPixel(x,y,image.getCharset().charAt(filter(pix)));
			}
		}
		
		return image;
	}
	
	//muss von den abgeleiteten Klassen implementiert werden. Sie führt die eigentliche Logik des Filters durch. Das übergebene Array umfasst die Helligkeitswerte der Pixel im Block Zeile für Zeile. Diese Methode gibt den berechneten Helligkeitswert für den neuen Pixel zurück.
	public abstract int filter(int[] values);
}