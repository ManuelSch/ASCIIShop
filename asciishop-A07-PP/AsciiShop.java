/**
 * Asciishop-A07-PP
 * Das Programm erstellt im ersten Schritt ein leeres Bild, auf dem dann unterschiedliche Operationen ausgeführt werden können. Neben einigen in den Vorrunden implementierten Operationen, gibt es noch einen weiteren Befehl, mit Hilfe dessen das Bild durch einen Medianfilter geglättet wird. Das Rückgängig-Machen einer beliebigen Anzahl von Befehlen ist weiterhin möglich.
 */

import java.util.Scanner;

public class AsciiShop
{
	public static void main(String[] args)
	{
		String inp;					//Zwischenspeicher für User-Input
		int breite=0, hoehe=0;		//Bildbreite und -höhe
		String charset="";			//Zeichensatz
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
						
						
						//Zeichensatz einlesen
						if (scanner.hasNext())
						{
							charset=scanner.next();
						
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
							//Kein korrekter Zeichensatz eingegeben:
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
			AsciiImage image = new AsciiImage(breite,hoehe,charset);
			//Neuen AsciiStack für die Undo-Funktion erstellen und initialisieren
			AsciiStack undo = new AsciiStack();

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
						
						String inphelp="";
						String zeile="";
						
						//Einzelne Zeilen einlesen:
						while (!(zeile.equals(eof))&&(scanner.hasNextLine()))
						{
							zeile = scanner.nextLine();
							
							if(!zeile.equals(eof))
							{
								inphelp+=zeile+"\n";	//Bild zeilenweise zum Hilfsstring hinzufügen
							}
						}
							
						//Load-Operation ausführen:
						try
						{
							LoadOperation lop = new LoadOperation(inphelp);
							image = lop.execute(image);
						}
						catch(OperationException opex)
						{
							System.out.println("OPERATION FAILED");
							exit=true;
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

							//Replace-Operation ausführen:
							try
							{
								ReplaceOperation rop = new ReplaceOperation(oldChar,newChar);
								image = rop.execute(image);
							}
							catch(OperationException opex)
							{
								System.out.println("OPERATION FAILED");
								exit=true;
							}
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

					//Clear-Operation ausführen:
					ClearOperation cop = new ClearOperation();
					image = cop.execute(image);
				}
				//*************************
				//**     print-Befehl    **
				//*************************
				else if (inp.equals("print"))
				{
					System.out.println(image.toString());
				}
				//*************************
				//**     undo-Befehl     **
				//*************************
				else if (inp.equals("undo"))
				{
					if(undo.empty()==false)
					{
						image=undo.pop();
					}
					else
					{
						System.out.println("STACK EMPTY");
					}
				}
				//*************************
				//**    filter-Befehl    **
				//*************************
				else if (inp.equals("filter"))
				{
					if (scanner.hasNext())
					{
						String filtertype = scanner.next();		//Filtertyp einlesen
						
						//Median-Filter:
						if(filtertype.equals("median"))
						{
							undo.push(image);	//Kopie des aktuellen Bilds auf den AsciiStack legen
							
							//Median-Operation ausführen:
							MedianOperation mop = new MedianOperation();
							image = mop.execute(image);
						}
					}
					else
					{
						//Filtertyp falsch eingegeben:
						exit=true;
						System.out.println("INPUT MISMATCH");
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













