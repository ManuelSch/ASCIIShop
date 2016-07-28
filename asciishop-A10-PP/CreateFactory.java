import java.util.Scanner;

/*
Diese Factory erzeugt CreateOperation.
 */

public class CreateFactory implements Factory {

	//erzeugt eine neue CreateFactory.
	public CreateFactory()
	{
	}

	//liest mit Hilfe des Scanners Breite und H�he und einen String ein und gibt eine damit initialisierte neue CreateOperation zur�ck. Tritt beim Einlesen ein Fehler (zu wenig Parameter, falsche Parameter), so wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		int breite=0, hoehe=0;		//Bildbreite und -h�he
		String charset="";			//Zeichensatz
		
		//Bildbreite einlesen
		if (scanner.hasNextInt())
		{
			breite=scanner.nextInt();						
			
			//Bildh�he einlesen
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
						//Bildbreite oder -h�he kleiner als 1:
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
				//Keine korrekte Bildh�he eingegeben:
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
