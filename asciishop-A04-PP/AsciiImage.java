public class AsciiImage
{
	private static int width, height;		//Breite und Höhe des Bildes
	private static String image;			//Gesamtes Bild in einem String

	//initialisiert das neue ASCII-Bild (Höhe und Breite sind anfangs 0 und verändern sich erst durch das Hinzufügen von Zeilen zum Bild).
	public AsciiImage()
	{
		width=0;
		height=0;
		image="";
	}

	//fügt dem Bild eine Zeile hinzu. Beim Hinzufügen der ersten Zeile wird die Breite des Bildes festgelegt. Ist die erste Zeile nicht mindestens ein Zeichen breit, so wird false zurückgegeben, andernfalls definiert die Länge der Zeile die Breite des Bildes. Danach übergebene Bildzeilen müssen dieser Breite entsprechen, ansonsten gibt die Methode false zurück. Diese Methode muss sicherstellen, dass abhängige Eigenschaften des Bildes (Höhe, Breite) aktualisiert werden. Die Methode gibt true zurück, wenn die Länge der Zeile mit der Breite des Bildes übereinstimmt und daher die Zeile dem Bild hinzugefügt werden konnte.
	public boolean addLine(String line)
	{
		if(line.length()>=1)
		{
			if(height==0)				//Erste Zeile -> Bildbreite festlegen
			{
				width=line.length();
			}

			if (line.length()==width)	//Stimmt Zeilenlänge mit Bildbreite überein?
			{
				image+=line;
				height++;
			}
			else
			{
				return false;	//Zeilenlänge stimmt nicht überein
			}

			return true;		//Zeile erfolgreich hinzugefügt
		}
		else
		{
			return false;		//Erste Zeile weniger als 1 Zeichen
		}
	}

	//gibt die Breite des Bildes (die Länge der Zeilen) zurück
	public int getWidth()
	{
		return width;
	}

	//gibt die Höhe des Bildes (die Anzahl der Zeilen) zurück.
	public int getHeight()
	{
		return height;
	}

	//gibt eine lesbare Darstellung des ASCII-Bildes zurück. Die einzelnen Zeilen sollen dabei durch Zeilenumbrüche ‘\n’ getrennt werden.
	public String toString()
	{
		String imagehelp="";

		//Gesamtes Bild durchlaufen:
		for(int i=0; i<width*height; i++)
		{
			imagehelp+=image.charAt(i);

			//Nach jeder Zeile einen Zeilenumbruch einfügen:
			if(i%width==width-1)
			{
				imagehelp+="\n";
			}
		}
		return imagehelp;
	}

	//gibt zurück wieviele unterschiedliche Zeichen im Bild vorkommen.
	public int getUniqueChars()
	{
		char[] uniquechar= new char[255];	//Alle bisher vorhandenen Zeichen im Bild
		int counter=0;						//Anzahl der unterschiedlichen Zeichen im Bild
		boolean unique = true;				//aktuelles Zeichen einzigartig im Bild?
		
		//Bild zeichenweise durchsuchen:
		for(int i=0; i<image.length(); i++)
		{
			unique = true;
			
			for(int j=0; j<255; j++)
			{
				//aktuelles Zeichen bereits vorhanden?
				if(image.charAt(i)==uniquechar[j])
				{
					unique=false;
				}
			}
			
			if(unique==true)
			{
				//Wenn Zeichen bisher noch nicht vorhanden -> im Array speichern
				uniquechar[counter]=image.charAt(i);
				counter++;
			}
		}
				
		return counter;
	}

	//dreht das Bild vertikal um, sprich es vertauscht die Zeilen des Bildes (die erste mit der letzten, die zweite mit der vorletzten, usw.)
	public void flipV()
	{
		String help="";		//Hilfsvariable
		
		//Spiegeln:
		for(int i=image.length(); i>=0; i-=width)
		{
			if(i>=width)
			{
				help+=image.substring(i-width,i);
			}
		}
		
		//Zurückschreiben:
		image=help;
	}

	//vertauscht Zeilen und Spalten des Bildes, sprich aus der ersten Zeile im Bild wird die erste Spalte usw.
	public void transpose()
	{
		String help="";		//Hilfsvariable

		//Transponieren:
		for(int i=0; i<width; i++)
		{
			for(int j=i; j<image.length(); j+=width)
			{
				help+=image.charAt(j);
			}
		}

		//Zurückschreiben:
		image=help;
		
		//Wenn Höhe und Breite nicht gleich -> Werte vertauschen
		int help2=height;
		height=width;
		width=help2;
	}

	//Ersetzt das Zeichen an der Position (x,y) mit dem Zeichen c und ruft sich ggfs. selbst rekursiv auf (mit neuen Werten von (x,y) die den Nachbarpositionen entsprechen). Die Methode implementiert den rekursiven Floodfill Algorithmus (siehe Runde 3).
	public void fill(int x, int y, char c)
	{
		char origchar=image.charAt(x+y*width);		//Originales Zeichen an (x,y)
		char[] imghelp=image.toCharArray();			//image in char-Array umwandeln
		imghelp[x+y*width]=c;						//aktuelles Zeichen ändern
		image=String.valueOf(imghelp);				//in image zurückschreiben
		
		//Ist Zeichen darunter gleich zum originalen Zeichen?
		if((((y+1)<height))&&(image.charAt(x+(y+1)*width)==origchar))
		{
			fill(x,y+1,c);
			imghelp=image.toCharArray();
		}
		//Ist Zeichen darüber gleich zum originalen Zeichen?
		if(((y-1)>=0)&&(image.charAt(x+(y-1)*width)==origchar))
		{
			fill(x,y-1,c);
			imghelp=image.toCharArray();
		}
		//Ist Zeichen rechts gleich zum originalen Zeichen?
		if(((x+1)<width)&&(image.charAt((x+1)+y*width)==origchar))
		{
			fill(x+1,y,c);
			imghelp=image.toCharArray();
		}
		//Ist Zeichen links gleich zum originalen Zeichen?
		if(((x-1)>=0)&&(image.charAt((x-1)+y*width)==origchar))
		{
			fill(x-1,y,c);
			imghelp=image.toCharArray();
		}
	}

}
