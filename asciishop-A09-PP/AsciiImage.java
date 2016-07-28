/*
Diese Klasse repräsentiert ein ASCII-Bild, es speichert die Zeichen des Bildes und bietet entsprechende Methoden zur Modifikation und zur Abfrage von Eigenschaften, wie beispielsweise Höhe und Breite. 
*/

import java.util.ArrayList;

public class AsciiImage
{
	private int breite, hoehe;		//Breite und Höhe des Bildes
	private char[][] image;			//Gesamtes Bild als zweidimensionales char-Array
	private String charset;

	//erzeugt ein ASCII-Bild der spezifizierten Größe und mit dem angegebenen Zeichensatz. Anfangs sind alle Pixel auf den hellsten Wert des Zeichensatzes (also dem letzten Zeichen des Strings) gesetzt. Überprüfen Sie an dieser Stelle ob Breite und Höhe beide größer 0 sind und werfen Sie andernfalls eine IllegalArgumentException. Werfen Sie auch eine IllegalArgumentException, falls das charset ein Zeichen doppelt enthält oder gar keine Zeichen umfasst.
	public AsciiImage(int width, int height, String charset)
	{
		breite=width;
		hoehe=height;
		image=new char[height][width];		//Bild als zweidimensionales char-Array
		this.charset=charset;
		
		//Alle Zeichen initialisieren
		for(int y=0; y<hoehe; y++)
		{
			for(int x=0; x<breite; x++)
			{
				setPixel(x,y,charset.charAt(charset.length()-1));
			}
		}
	}
	
	//Kopierkonstruktor. Er erzeugt ein neues AsciiImage mit dem gleichen Inhalt, wie das übergebene Bild.
	public AsciiImage(AsciiImage img)
	{
		breite=img.getWidth();
		hoehe=img.getHeight();
		image=new char[hoehe][breite];		//Bild als zweidimensionales char-Array
		charset=img.getCharset();
		
		//Alle Zeichen vom Originalbild übernehmen:
		for(int y=0; y<hoehe; y++)
		{
			for(int x=0; x<breite; x++)
			{
				setPixel(x,y,img.getPixel(x,y));
			}
		}
	}
	
	//gibt die width des Bildes (die Länge der Zeilen) zurück
	public int getWidth()
	{
		return breite;
	}

	//gibt die Höhe des Bildes (die Anzahl der Zeilen) zurück.
	public int getHeight()
	{
		return hoehe;
	}
	
	//gibt den Zeichensatz des Bildes als String zurück.
	public String getCharset()
	{
		return charset;
	}
	
	//gibt das an den übergebenen Koordinaten/Indizes gespeicherte Zeichen zurück. Überprüfen Sie an dieser Stelle, ob die Indizes gültig sind und werfen Sie andernfalls eine IndexOutOfBoundsException.
	public char getPixel(int x, int y) throws IndexOutOfBoundsException
	{
		if((y<0)||(y>=hoehe)||(x<0)||(x>breite))
		{
			throw new IndexOutOfBoundsException("Ungueltige Koordinaten");
		}
		
		return image[y][x];
	}
	
	//gibt, analog zur Methode public char getPixel(int x, int y), das Zeichen, an der durch p spezifizierten Stelle, zurück. Überprüfen Sie an dieser Stelle, ob die Indizes gültig sind und werfen Sie andernfalls eine IndexOutOfBoundsException.
	public char getPixel(AsciiPoint p)
	{
		if((p.getY()<0)||(p.getY()>=hoehe)||(p.getX()<0)||(p.getX()>breite))
		{
			throw new IndexOutOfBoundsException("Ungueltige Koordinaten");
		}
		
		return this.getPixel(p.getX(),p.getY());
	}

	//speichert an den übergebenen Koordinaten/Indizes das übergebene Zeichen. Überprüfen Sie an dieser Stelle, ob die Indizes gültig sind und werfen Sie andernfalls eine IndexOutOfBoundsException. Werfen Sie eine IndexOutOfBoundsException, falls das Zeichen c nicht dem Zeichensatz des Bildes entspricht (sprich nicht im charset enthalten ist).
	public void setPixel(int x, int y, char c)
	{
		if((y<0)||(y>=hoehe)||(x<0)||(x>breite))
		{
			throw new IndexOutOfBoundsException("Ungueltige Koordinaten");
		}
		
		if (charset.indexOf(c) < 0)
		{
			throw new IndexOutOfBoundsException("Zeichen nicht im Zeichensatz enthalten");
		}
		
		image[y][x]=c;
	}
	
	//speichert, analog zur Methode public char setPixel(int x, int y, char c), das übergebene Zeichen an der durch p spezifizierten Stelle. Überprüfen Sie an dieser Stelle, ob die Indizes gültig sind und werfen Sie andernfalls eine IndexOutOfBoundsException. Werfen Sie eine IndexOutOfBoundsException, falls das Zeichen c nicht dem Zeichensatz des Bildes entspricht (sprich nicht im charset enthalten ist).
	public void setPixel(AsciiPoint p, char c)
	{
		if((p.getY()<0)||(p.getY()>=hoehe)||(p.getX()<0)||(p.getX()>breite))
		{
			throw new IndexOutOfBoundsException("Ungueltige Koordinaten");
		}
		
		if (charset.indexOf(c) < 0)
		{
			throw new IndexOutOfBoundsException("Zeichen nicht im Zeichensatz enthalten");
		}
		
		this.setPixel(p.getX(),p.getY(),c);
	}
	
	//gibt eine lesbare Darstellung des ASCII-Bildes zurück. Die einzelnen Zeilen sollen dabei durch Zeilenumbrüche ‘\n’ getrennt werden.
	public String toString()
	{
		String imghelp="";	//Hilsvariable

		//Gesamtes Bild durchlaufen:
		for(int i=0; i<hoehe; i++)
		{
			for(int j=0; j<breite; j++)
			{
				imghelp+=image[i][j];		//aktuelles Zeichen zum Hilfsstring hinzufügen
			}
			imghelp+="\n";				//Nach jeder Zeile einen Zeilenumbruch einfügen
		}
		
		return imghelp;
	}
	
	//gibt eine ArrayList aller Pixel eines bestimmten Zeichens zurück. In dieser ArrayList sind Objekte vom Typ AsciiPoint, sollte es keine Punkte mit dem angegebenen Zeichen geben, so soll eine leere Liste zurückgegeben werden. Verwenden Sie diese Methode überall dort, wo sie alle Pixel mit einem bestimmten Zeichen benötigen.
	public ArrayList<AsciiPoint> getPointList(char c)
	{
		ArrayList<AsciiPoint> liste = new ArrayList<AsciiPoint>();
		
		//Gesamtes Bild durchlaufen:
		for(int y=0; y<hoehe; y++)
		{
			for(int x=0; x<breite; x++)
			{
				if(getPixel(x,y)==c)
				{
					liste.add(new AsciiPoint(x,y));
				}
			}
		}
		return liste;
	}
	
}	
	

