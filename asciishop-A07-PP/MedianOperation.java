/*
Diese Klasse glättet ein Bild mit einem 3x3-Medianfilter (vgl. Rangordnungsfilter#Medianfilter).
*/
import java.util.Arrays; 

public class MedianOperation implements Operation
{
	//erzeugt eine neue MedianOperation.
	public MedianOperation()
	{
	
	}
	
	//führt auf einer Kopie des Bildes den Medianfilter aus. Dabei werden immer 3 mal 3 Größe Blöcke des Bildes betrachtet, die Pixel nach ihrem `Helligkeitswert' sortiert und dann der Median (also das in der sortierten Liste in der Mitte stehende Zeichen) als neues Pixel im Mittelpunkt des Blocks gesetzt.
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
				
				//Aktuell betrachteter Bildpunkt durch neuen Helligkeitswert ersetzen:				
				image.setPixel(x,y,image.getCharset().charAt(pix[4]));		//pix[4]...Mittelwert des sortierten Arrays
				
			}
		}
		return image;
	}
}