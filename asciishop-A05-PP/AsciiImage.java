public class AsciiImage
{
	private int breite, hoehe;		//Breite und Höhe des Bildes
	private char[][] image;			//Gesamtes Bild als zweidimensionales char-Array

	//erzeugt ein ASCII-Bild der spezifizierten Größe. Anfangs sind alle Pixel auf den Wert ‘.’ gesetzt.
	public AsciiImage(int width, int height)
	{
		breite=width;
		hoehe=height;
		image=new char[height][width];		//Bild als zweidimensionales char-Array
		
		//Alle Zeichen mit Punkten initialisieren:
		for(int i=0; i<hoehe; i++)
		{
			for(int j=0; j<breite; j++)
			{
				image[i][j]='.';
			}
		}
	}
	
	//setzt alle Pixel des Bildes auf das Zeichen ‘.’.
	public void clear()
	{
		//Alle Zeichen mit Punkten initialisieren
		for(int i=0; i<hoehe; i++)
		{
			for(int j=0; j<breite; j++)
			{
				image[i][j]='.';
			}
		}
	}
	
	//zeichnet eine Linie zwischen den Koordinaten (x0,y0) und (x1,y1). Anfangs- und Endpunkt sind dabei inkludiert. c spezifiziert das zu verwendende Zeichen.
	public void drawLine(int x0, int y0, int x1, int y1, char c)
	{
		double deltax=x1-x0, deltay=y1-y0;
		boolean invertiert=false;
		
		//Sind die Achsen invertiert? Wenn ja -> Koordinaten vertauschen
		if (Math.abs(deltay)>Math.abs(deltax))
		{
			int help=x0;
			x0=y0;
			y0=help;
			
			help=x1;
			x1=y1;
			y1=help;
			
			double help2=deltax;
			deltax=deltay;
			deltay=help2;
			
			invertiert=true;
		}
		
		//Ist x1 kleiner als x0? Wenn ja -> Anfangs- und Endpunkt vertauschen
		if(x1<x0)
		{
			int help=x0;
			x0=x1;
			x1=help;
			
			help=y0;
			y0=y1;
			y1=help;
		}
				
		int x;
		double y=y0;
		
		for(x=x0; x<=x1; x++)
		{
			if (invertiert==true)
			{
				image[x][(int)(Math.round(y))]=c;
			}
			else
			{
				image[(int)(Math.round(y))][x]=c;
			}
			y+=deltay/deltax;
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
	
	//gibt das an den übergebenen Koordinaten/Indizes gespeicherte Zeichen zurück.
	public char getPixel(int x, int y)
	{
		return image[y][x];
	}

	//speichert an den übergebenen Koordinaten/Indizes das übergebene Zeichen. Sie dürfen an dieser Stelle davon ausgehen, dass x und y gültige Koordinaten sind.
	public void setPixel(int x, int y, char c)
	{
		image[y][x]=c;
	}
	
	//ersetzt alle Vorkommen eines bestimmten Zeichens oldChar im Bild durch ein anderes Zeichen newChar.
	public void replace(char oldChar, char newChar)
	{
		//Gesamtes Bild durchlaufen:
		for(int i=0; i<hoehe; i++)
		{
			for(int j=0; j<breite; j++)
			{
				if(image[i][j]==oldChar)
				{
					image[i][j]=newChar;		//alten Zeichen durch neues ersetzen
				}
			}
		}
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

	//vertauscht Zeilen und Spalten des Bildes, sprich aus der ersten Zeile im Bild wird die erste Spalte usw.
	public void transpose()
	{
		char[][] imghelp = new char[breite][hoehe];		//Hilfsvariable

		//Gesamtes Bild durchlaufen:
		for(int i=0; i<breite; i++)
		{
			for(int j=0; j<hoehe; j++)
			{
				//Transponieren:
				imghelp[i][j]=image[j][i];		
			}
		}
		
		//Wenn Höhe und width nicht gleich -> Werte vertauschen
		int help = hoehe;
		hoehe = breite;
		breite = help;
		
		//Zurückschreiben:
		this.image=imghelp;
	}

	//Ersetzt das Zeichen an der Position (x,y) mit dem Zeichen c und ruft sich ggfs. selbst rekursiv auf (mit neuen Werten von (x,y) die den Nachbarpositionen entsprechen). Die Methode implementiert den rekursiven Floodfill Algorithmus (siehe Runde 3).
	public void fill(int x, int y, char c)
	{
		char origchar=image[y][x];		//Originales Zeichen an (x,y)
		image[y][x]=c;					//aktuelles Zeichen ändern
		
		//Ist Zeichen darunter gleich zum originalen Zeichen?
		if((((y+1)<hoehe))&&(image[y+1][x]==origchar))
		{
			fill(x,y+1,c);
		}
		//Ist Zeichen darüber gleich zum originalen Zeichen?
		if(((y-1)>=0)&&(image[y-1][x]==origchar))
		{
			fill(x,y-1,c);
		}
		//Ist Zeichen rechts gleich zum originalen Zeichen?
		if(((x+1)<breite)&&(image[y][x+1]==origchar))
		{
			fill(x+1,y,c);
		}
		//Ist Zeichen links gleich zum originalen Zeichen?
		if(((x-1)>=0)&&(image[y][x-1]==origchar))
		{
			fill(x-1,y,c);
		}
	}
}
