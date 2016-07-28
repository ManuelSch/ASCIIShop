/**
 * AsciiShop-A04-pp
 * Das Programm liest eine angegebene Anzahl von Zeilen eines ASCII-Bildes ein. Diese Daten werden einem Objekt der Klasse AsciiImage zeilenweise hinzugefügt. Nach dem Einlesen des Bildes können mehrere Befehle folgen, um das Bild vertikal zu flippen oder transponieren, bzw. die Anzahl an unterschiedlichen vorkommenden Zeichen zu bestimmen. Zusätzlich soll auch der fill-Befehl aus Runde 3 benutzt werden können. Abschließend wird das Endergebnis ausgegeben.
 */

import java.util.Scanner;

public class AsciiShop
{
	public static void main(String[] args)
	{
		String inp;					//Zwischenspeicher für User-Input
		int breite=0, hoehe=0;		//Bildbreite und -höhe
		boolean exit=false;			//Abbruchbedingung der Eingabeschleife

		//Scanner für User-Input:
		Scanner scanner = new Scanner(System.in);
		
		
		//*************************
		//**    create-Befehl    **
		//*************************
		if(scanner.hasNext())		//"create" einlesen
		{
			inp=scanner.next();

			if (inp.equals("create"))
			{
				//Bildbreite einlesen
				if (scanner.hasNextInt())
				{
					breite=scanner.nextInt();						
					
					//Bildhöhe einlesen
					if (scanner.hasNextInt())
					{
						hoehe=scanner.nextInt();
									
						scanner.nextLine();		//Einlesen der create-Zeile beenden
					
						if((breite>0)&&(hoehe>0))
						{
							exit=true;			//Image darf initialisiert werden
						}
						else
						{
							//Bildbreite oder -höhe kleiner als 1:
							System.out.println("INPUT MISMATCH");
						}
					}
					else
					{
						//Keine korrekte Bildhöhe eingegeben:
						System.out.println("INPUT MISMATCH");
					}
				}
				else
				{
					//Keine korrekte Bildbreite eingegeben:
					System.out.println("INPUT MISMATCH");
				}
			}
			else
			{
				//"create" wurde nicht eingegeben:
				System.out.println("INPUT MISMATCH");
			}
		}
		
		//wurde create-Befehl richtig eingegeben?
		if(exit==true)
		{
			//Neues Bild-Objekt erstellen und initialisieren:
			AsciiImage image = new AsciiImage(breite,hoehe);

			//*************************
			//**  Befehle einlesen:  **
			//*************************
			exit=false;

			while ((exit==false)&&(scanner.hasNext()))
			{
				inp=scanner.next();
				
				//*************************
				//**     load-Befehl     **
				//*************************
				if (inp.equals("load"))
				{
					if (scanner.hasNext())
					{
						String eof = scanner.next();			//Abbruchstring des load-Befehls einlesen
						
						scanner.nextLine();						//Einlesen des Befehls beenden
						
						int zeilenanz=0;
							
						//Einzelne Zeilen einlesen:
						while ((exit==false)&&(zeilenanz<=image.getHeight())&&(scanner.hasNextLine()))
						{
							String zeile = scanner.nextLine();
							
							//Stimmt die eingegebene Zeile mit dem Abbruchstring überein?
							if(zeile.equals(eof))
							{
								zeilenanz=image.getHeight();	//Eingabeschleife beenden
							}
							//Hat die eingegebene Zeile mit der Bildbreite überein?
							else if (zeile.length()==image.getWidth())
							{
								for(int i=0; i<image.getWidth(); i++)
								{
									image.setPixel(i,zeilenanz,zeile.charAt(i));
								}
							}
							else
							{
								//Eingegebene Zeile zu kurz oder zu lang:
								exit=true;
								System.out.println("INPUT MISMATCH");
							}
							
							zeilenanz++;
						}
					}
					else
					{
						//Abbruchstring falsch eingegeben:
						exit=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				//*************************
				//**     fill-Befehl     **
				//*************************
				else if (inp.equals("fill"))
				{
					if (scanner.hasNextInt())
					{
						int x = scanner.nextInt();						//x-Parameter des fill-Befehls einlesen
						
						if (scanner.hasNextInt())
						{
							int y = scanner.nextInt();					//y-Parameter des fill-Befehls einlesen
							
							if (scanner.hasNext())
							{
								char c = scanner.next().charAt(0);		//c-Parameter des fill-Befehls einlesen
											
								scanner.nextLine();						//Einlesen des Befehls beenden
						
								//Befinden sich die eingegeben Koordinaten innerhalb des Bildbereiches?
								if ((x>=0)&&(y>=0)&&(y<image.getHeight())&&(x<image.getWidth()))
								{
									image.fill(x,y,c);					//Füllmethode
								}
								else
								{
									//x- und y-Koordinaten außerhalb des Bildes
									exit=true;
									System.out.println("OPERATION FAILED");
								}
							}
							else
							{
								//c-Parameter falsch eingegeben
								exit=true;
								System.out.println("INPUT MISMATCH");
							}
						}
						else
						{
							//y-Parameter falsch eingegeben
							exit=true;
							System.out.println("INPUT MISMATCH");
						}
					}
					else
					{
						//x-Parameter falsch eingegeben
						exit=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				//*************************
				//**     line-Befehl     **
				//*************************
				else if (inp.equals("line"))
				{
					if (scanner.hasNextInt())
					{
						int x0 = scanner.nextInt();					//x0-Parameter des line-Befehls einlesen
						
						if (scanner.hasNextInt())
						{
							int y0 = scanner.nextInt();				//y0-Parameter des line-Befehls einlesen
							
							if (scanner.hasNextInt())
							{
								int x1 = scanner.nextInt();			//x1-Parameter des line-Befehls einlesen
								
								if (scanner.hasNextInt())
								{
									int y1 = scanner.nextInt();		//y1-Parameter des line-Befehls einlesen
									
									if (scanner.hasNext())
									{
										char c = scanner.next().charAt(0);	//c-Parameter des line-Befehls einlesen
													
										scanner.nextLine();					//Einlesen des Befehls beenden
								
										//Befinden sich die eingegeben Koordinaten innerhalb des Bildbereiches?
										if ((x0>=0)&&(y0>=0)&&(x0<image.getWidth())&&(y0<image.getHeight())&&
											(x1>=0)&&(y1>=0)&&(x1<image.getWidth())&&(y1<image.getHeight()))
										{
											image.drawLine(x0,y0,x1,y1,c);	//Line-Methode
										}
									}
									else
									{
										//c-Parameter falsch eingegeben
										exit=true;
										System.out.println("INPUT MISMATCH");
									}
								}
								else
								{
									//y1-Parameter falsch eingegeben
									exit=true;
									System.out.println("INPUT MISMATCH");
								}
							}
							else
							{
								//x1-Parameter falsch eingegeben
								exit=true;
								System.out.println("INPUT MISMATCH");
							}	
						}
						else
						{
							//y0-Parameter falsch eingegeben
							exit=true;
							System.out.println("INPUT MISMATCH");
						}
					}
					else
					{
						//x0-Parameter falsch eingegeben
						exit=true;
						System.out.println("INPUT MISMATCH");
					}				
				}
				//*************************
				//**    replace-Befehl   **
				//*************************
				else if (inp.equals("replace"))
				{
					if (scanner.hasNext())
					{
						char oldChar = scanner.next().charAt(0);		//oldChar-Parameter des fill-Befehls einlesen
						
						if (scanner.hasNext())
						{
							char newChar = scanner.next().charAt(0);	//newChar-Parameter des fill-Befehls einlesen
							
							scanner.nextLine();							//Einlesen des Befehls beenden
							
							image.replace(oldChar,newChar);
						}
						else
						{
							//newChar-Parameter falsch eingegeben
							exit=true;
							System.out.println("INPUT MISMATCH");
						}
					}
					else
					{
						//oldChar-Parameter falsch eingegeben
						exit=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				//*************************
				//**    clear-Befehl     **
				//*************************
				else if (inp.equals("clear"))
				{
					image.clear();
				}
				//*************************
				//**  transpose-Befehl   **
				//*************************
				else if (inp.equals("transpose"))
				{
					image.transpose();
				}
				//*************************
				//**     print-Befehl    **
				//*************************
				else if (inp.equals("print"))
				{
					System.out.println(image.toString());
				}
				else
				{
					//Nicht vorhandener Befehl eingegeben
					exit=true;
					System.out.println("UNKNOWN COMMAND");
				}
			}
		}
	}
}













