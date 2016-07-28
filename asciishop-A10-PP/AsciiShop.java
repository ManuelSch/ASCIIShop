/**
 * Asciishop-A10-PP
 * Diese Klasse ist ausführbar und beinhaltet daher die main-Methode. Sie verarbeitet die Eingaben, erzeugt das AsciiImage und gibt das Ergebnis aus. Methoden dieser Klasse lesen direkt von System.in ein und geben direkt auf System.out aus.
 */

import java.util.*;

public class AsciiShop
{
	//liest die Daten und Befehle ein und gibt das Ergebnis aus.
	public static void main(String[] args)
	{
		String inp;					//Zwischenspeicher für User-Input
		int breite=0, hoehe=0;		//Bildbreite und -höhe
		String charset="";			//Zeichensatz
		boolean exit=false;			//Abbruchbedingung der Eingabeschleife
		
		//Speicher für Bilder:
		MetricSet<AsciiImage> memory = new MetricSet<AsciiImage>();

		//Scanner für User-Input:
		Scanner scanner = new Scanner(System.in);
		HashMap<String,Factory> befehle = new HashMap<String,Factory>();
		
		//Alle Befehle, die das Bild ändern, werden in einer Hashmap gespeichert:
		befehle.put("binary",new BinaryFactory());
		befehle.put("clear",new ClearFactory());
		befehle.put("filter",new FilterFactory());
		befehle.put("load",new LoadFactory());
		befehle.put("replace",new ReplaceFactory());
		befehle.put("create",new CreateFactory());
		befehle.put("save",new SaveFactory(memory));
		befehle.put("search",new SearchFactory(memory));
		
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
				//**       Befehle       **
				//*************************
				if(befehle.containsKey(inp))
				{
					try
					{
						Operation op = befehle.get(inp).create(scanner);	//neue Operation erzeugen
						undo.push(image);		//Kopie des aktuellen Bilds auf den AsciiStack legen
						image = new AsciiImage(op.execute(image));
					}
					catch (FactoryException faex)			//Falsche Parameter-Eingabe
					{
						System.out.println("INPUT MISMATCH");
						exit=true;
					}
					catch(OperationException opex)		//Operation konnte nicht durchgeführt werden
					{
						System.out.println("OPERATION FAILED");
						exit=true;
					}
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
				//**  printsaved-Befehl  **
				//*************************
				else if (inp.equals("printsaved"))
				{
					if(memory.isEmpty()==true)
					{
						System.out.println("NO SAVED IMAGES");
					}
					else
					{
						Iterator<AsciiImage> it = memory.iterator();
						
						while(it.hasNext())
						{
							System.out.println(it.next().toString());
						}
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













