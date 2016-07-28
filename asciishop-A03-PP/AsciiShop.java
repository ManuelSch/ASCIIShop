/**
 * AsciiShop-A03-pp
 * Das Programm liest eine angegebene Anzahl von Zeilen eines ASCII-Bildes ein. Dabei gilt es zu überprüfen, ob alle Zeilen gleich lang sind. Anschließend kann ein fill-Befehl eingegeben werden, dessen Parameter die Position der zu füllenden Fläche beschreibt. Abschließend wird das veränderte Bild sowie seine Breite und Höhe ausgegeben.
 * Manuel Schüller (1426298), 03.11.2014
 */

import java.util.Scanner;

public class AsciiShop
{
	static int zeilenanz=0;
	static char origchar;

	public static void main(String[] args)
	{
		String inp;								//Zwischenspeicher für User-Input
		int hoehe=0, breite=0;					//Anzahl der Zeilen und Spalten des Bildes.
		int fillx=0, filly=0;		//Parameter der read- und fill-Befehle
		char fillchar;
		boolean exit=false;					//Abbruchbedingung der Eingabeschleife
		int i=0;								//Zählvariable

		//Scanner für User-Input:
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNext())
		{
			//*************************
			//**read-Befehl einlesen:**
			//*************************
			inp=scanner.next();
			if (inp.equals("read"))
			{
				if (scanner.hasNextInt())
				{
					zeilenanz=scanner.nextInt();				//Zeilenanzahl einlesen
					
					String[] zeilen = new String[zeilenanz];	//Zwischenspeicher für Bildzeilen
					
					scanner.nextLine();							//Einlesen der read-Zeile beenden

					//Bild zeilenweise einlesen:
					if (scanner.hasNextLine())
					{
						inp = scanner.nextLine();				//Erste Bildzeile einlesen
						zeilen[hoehe] = inp;
						breite=inp.length();					//Bildbreite wird durch erste Zeile festgelegt
						hoehe++;
						
						while((exit==false)&&(hoehe<=(zeilenanz-1)))
						{	
							if(scanner.hasNextLine())
							{
								inp = scanner.nextLine();			//Weitere Zeilen einlesen
								zeilen[hoehe] = inp;				//Erste Bildzeile einlesen
								if(zeilen[hoehe].length()!=breite)	//Abbruch durch falschen User-Input
								{
									exit=true;
									System.out.println("INPUT MISMATCH");
								}
								hoehe++;
							}
						}
					}
					else
					{
						System.out.println("INPUT MISMATCH");
					}
					
					//*************************
					//**fill-Befehl einlesen:**
					//*************************
					while ((exit==false)&&(scanner.hasNext()))
					{
						inp = scanner.next();
						if (inp.equals("fill"))
						{
							if (scanner.hasNextInt())
							{
								fillx = scanner.nextInt();							//x-Parameter des fill-Befehls einlesen
								
								if (scanner.hasNextInt())
								{
									filly = scanner.nextInt();						//y-Parameter des fill-Befehls einlesen
									
									if (scanner.hasNext())
									{
										fillchar = scanner.next().charAt(0);		//c-Parameter des fill-Befehls einlesen
										
										//Befinden sich die eingegeben Koordinaten innerhalb des Bildbereiches?
										if ((fillx>=0)&&(filly>=0)&&(filly<zeilenanz)&&(fillx<zeilen[filly].length()))
										{
											origchar=zeilen[filly].charAt(fillx);	//Ursprüngliches Zeichen speichern
											fill(zeilen,fillx,filly,fillchar);		//Füllmethode
										}
										else
										{
											exit=true;
											System.out.println("OPERATION FAILED");
										}
									}
									else
									{
										exit=true;
										System.out.println("INPUT MISMATCH");
									}
								}
								else
								{
									exit=true;
									System.out.println("INPUT MISMATCH");
								}
							}
							else
							{
								exit=true;
								System.out.println("INPUT MISMATCH");
							}
						}
					}
					
					//Geändertes Bild zeilenweise ausgeben:
					if (exit==false)
					{
						for(i=0;i<zeilenanz;i++)
						{
							System.out.println(zeilen[i]);
						}
						System.out.println(breite+" "+hoehe);
					}
				}
				else
				{
					System.out.println("INPUT MISMATCH");
				}
			}
			else
			{
				System.out.println("INPUT MISMATCH");
			}
		}
		else
		{
			System.out.println("INPUT MISMATCH");
		}
	}
	
	public static void fill(String[] image, int x, int y, char c)
	{
		if ((x>=0)&&(y>=0)&&(y<zeilenanz)&&(x<image[y].length()))	//Befindet sich die aktuelle Instanz noch im gültigen Bildbereich?
		{
			if (image[y].charAt(x)==origchar)						//Ist das Zeichen an dieser Stelle gleich oder verschieden?
			{
				char[] zeile = image[y].toCharArray();				//Zeilenstring in Char-Array umwandeln
				zeile[x]=c;											//Zeichen ersetzen
				image[y]=String.valueOf(zeile);						//Char-Array in Zeilenstring umwandeln
				
				fill(image,x+1,y,c);								//Zeichen über, unter, links und rechts von der aktuellen Position ändern 
				fill(image,x-1,y,c);
				fill(image,x,y+1,c);
				fill(image,x,y-1,c);
			}
		}
	}
}







