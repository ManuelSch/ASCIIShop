/**
 * Asciishop-A06-PP
 * Das Programm erstellt im ersten Schritt ein leeres Bild, auf dem dann unterschiedliche Operationen ausgeführt werden können. Neben den in der Vorrunde implementierten Operationen, gibt es noch zwei weitere Befehle, mit denen man Flächen eines bestimmten Zeichens wachsen lassen und deren Schwerpunkt bestimmen kann. Zusätzlich ist es nun möglich, eine beliebige Anzahl von Befehlen rückgängig zu machen. 
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
			//Neuen AsciiStack für die Undo-Funktion erstellen und initialisieren
			AsciiStack undo = new AsciiStack(3);	//Increment lt. Angabe = 3

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
							
						undo.push(image);						//Kopie des aktuellen Bilds auf den AsciiStack legen
						
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
									undo.push(image);					//Kopie des aktuellen Bilds auf den AsciiStack legen
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
											undo.push(new AsciiImage(image));				//Kopie des aktuellen Bilds auf den AsciiStack legen
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
							
							undo.push(image);							//Kopie des aktuellen Bilds auf den AsciiStack legen
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
					undo.push(image);	//Kopie des aktuellen Bilds auf den AsciiStack legen
					image.clear();
				}
				//*************************
				//**  transpose-Befehl   **
				//*************************
				else if (inp.equals("transpose"))
				{
					undo.push(image);	//Kopie des aktuellen Bilds auf den AsciiStack legen
					image.transpose();
				}
				//*************************
				//**     print-Befehl    **
				//*************************
				else if (inp.equals("print"))
				{
					System.out.println(image.toString());
				}
				//*************************
				//**   centroid-Befehl   **
				//*************************
				else if (inp.equals("centroid"))
				{
					if (scanner.hasNext())
					{
						char c = scanner.next().charAt(0);		//c-Parameter des fill-Befehls einlesen
						
						if(image.getCentroid(c)==null)
						{
							System.out.println("null");
						}
						else
						{
							System.out.println((image.getCentroid(c)).toString());
						}
					}
					else
					{
						//c-Parameter falsch eingegeben
						exit=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				//*************************
				//**     grow-Befehl     **
				//*************************
				else if (inp.equals("grow"))
				{
					if (scanner.hasNext())
					{
						char c = scanner.next().charAt(0);		//c-Parameter des fill-Befehls einlesen
									
						scanner.nextLine();						//Einlesen des Befehls beenden
				
						undo.push(image);						//Kopie des aktuellen Bilds auf den AsciiStack legen
						image.growRegion(c);					//Grow-Methode
					}
					else
					{
						//c-Parameter falsch eingegeben
						exit=true;
						System.out.println("INPUT MISMATCH");
					}
				}
				//*************************
				//**     undo-Befehl     **
				//*************************
				else if (inp.equals("undo"))
				{
					if(undo.empty()==false)
					{
						image=undo.pop();
						System.out.println("STACK USAGE "+undo.size()+"/"+undo.capacity());
					}
					else
					{
						System.out.println("STACK EMPTY");
					}
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













