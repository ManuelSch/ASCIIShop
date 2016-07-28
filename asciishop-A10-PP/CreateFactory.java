import java.util.Scanner;

/*
Diese Factory erzeugt CreateOperation.
 */

public class CreateFactory implements Factory {

	//erzeugt eine neue CreateFactory.
	public CreateFactory()
	{
	}

	//liest mit Hilfe des Scanners Breite und Höhe und einen String ein und gibt eine damit initialisierte neue CreateOperation zurück. Tritt beim Einlesen ein Fehler (zu wenig Parameter, falsche Parameter), so wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		int breite=0, hoehe=0;		//Bildbreite und -höhe
		String charset="";			//Zeichensatz
		
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
						return new CreateOperation(breite,hoehe,charset);
					}
					else
					{
						//Bildbreite oder -höhe kleiner als 1:
						throw new FactoryException("Insufficient parameter");
					}
				}
				else
				{
					//Kein korrekter Zeichensatz eingegeben:
					throw new FactoryException("Insufficient parameter");
				}
			}
			else
			{
				//Keine korrekte Bildhöhe eingegeben:
				throw new FactoryException("Insufficient parameter");
			}
		}
		else
		{
			//Keine korrekte Bildbreite eingegeben:
			throw new FactoryException("Insufficient parameter");
		}
	}

}
