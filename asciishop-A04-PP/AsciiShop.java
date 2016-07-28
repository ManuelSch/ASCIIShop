/**
 * AsciiShop-A04-pp
 * Das Programm liest eine angegebene Anzahl von Zeilen eines ASCII-Bildes ein. Diese Daten werden einem Objekt der Klasse AsciiImage zeilenweise hinzugefügt. Nach dem Einlesen des Bildes können mehrere Befehle folgen, um das Bild vertikal zu flippen oder transponieren, bzw. die Anzahl an unterschiedlichen vorkommenden Zeichen zu bestimmen. Zusätzlich soll auch der fill-Befehl aus Runde 3 benutzt werden können. Abschließend wird das Endergebnis ausgegeben.
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
		int fillx=0, filly=0;					//Parameter für fill-Befehl
		char fillchar;							//Zeichen zum Füllen (für fill-Befehl)
		boolean exit=false, inpmis=false;		//Abbruchbedingung der Eingabeschleife
		int i=0;								//Zählvariable

		//Scanner für User-Input:
		Scanner scanner = new Scanner(System.in);
		AsciiImage image = new AsciiImage();
		
		//*************************
		//**     read-Befehl     **
		//*************************
		while ((exit==false)&&(scanner.hasNext()))
		{
			inp=scanner.next();

			if (inp.equals("read"))
			{
				if (scanner.hasNextInt())
				{
					zeilenanz=scanner.nextInt();						//Zeilenanzahl einlesen
									
					scanner.nextLine();									//Einlesen der read-Zeile beenden

					//Bild zeilenweise einlesen:
					while(exit==false)
					{
						if (scanner.hasNextLine())
						{
							inp = scanner.nextLine();

							if (image.addLine(inp)==true)
							{
								if (hoehe<zeilenanz-1)
								{
									hoehe++;
								}
								else
								{
									exit=true;		//Mehr Zeilen als vorher angegeben
								}
							}
							else
							{
								//Zeilenlänge falsch oder erste Zeile weniger als 1 Zeichen
								exit=true;
								inpmis=true;
								System.out.println("INPUT MISMATCH");
							}
						}
					}
				}
				else
				{
					//Keine korrekte Int-Zahl nach "read" eingegeben:
					System.out.println("INPUT MISMATCH");
					inpmis=true;
				}
			}
		}
	

		//*************************
		//**  Befehle einlesen:  **
		//*************************
		exit=false;

		while ((exit==false)&&(scanner.hasNext()))
		{
			inp=scanner.next();
		
			//*************************
			//**     fill-Befehl     **
			//*************************
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
							if ((fillx>=0)&&(filly>=0)&&(filly<image.getHeight())&&(fillx<image.getWidth()))
							{
								image.fill(fillx,filly,fillchar);			//Füllmethode
							}
							else
							{
								//x- und y außerhalb des Bildes
								exit=true;
								inpmis=true;
								System.out.println("OPERATION FAILED");
							}
						}
						else
						{
							//c-Parameter falsch eingegeben
							exit=true;
							inpmis=true;
							System.out.println("INPUT MISMATCH");
						}
					}
					else
					{
						//y-Parameter falsch eingegeben
						exit=true;
						inpmis=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				else
				{
					//x-Parameter falsch eingegeben
					exit=true;
					inpmis=true;
					System.out.println("INPUT MISMATCH");
				}
			}
			//*************************
			//** uniqueChars-Befehl  **
			//*************************
			else if (inp.equals("uniqueChars"))
			{
				System.out.println(image.getUniqueChars());
			}
			//*************************
			//**    flip-v-Befehl    **
			//*************************
			else if (inp.equals("flip-v"))
			{
				image.flipV();
			}
			//*************************
			//**  transpose-Befehl   **
			//*************************
			else if (inp.equals("transpose"))
			{
				image.transpose();
			}
			else
			{
				//Nicht vorhandener Befehl eingegeben
				exit=true;
				inpmis=true;
				System.out.println("INPUT MISMATCH");
			}
		}
		//Alle Eingaben korrekt?
		if(inpmis==false)
		{
			//Bild, Bildbreite und -höhe ausgeben:
			System.out.print(image.toString());
			System.out.println(image.getWidth()+" "+image.getHeight());
		}
	}
}













