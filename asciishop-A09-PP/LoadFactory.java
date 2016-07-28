import java.util.Scanner;

/*
Diese Factory erzeugt LoadOperations.
 */

public class LoadFactory implements Factory
{

	//erzeugt eine neue LoadFactory.
	public LoadFactory()
	{
	}

	//liest den eof-String ein und übergibt in einem String alle Zeilen bis zum abschließenden eof-String durch Zeilenumbrüche getrennt an den Konstruktor der LoadOperation. Tritt beim Einlesen ein Fehler auf (eof fehlt), so wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		if (scanner.hasNext())
		{
			String eof = scanner.next();	//Abbruchstring des load-Befehls einlesen
			
			scanner.nextLine();				//Einlesen des Befehls beenden
			
			int zeilenanz=0;
			
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
				
			//Load-Operation zurückgeben:
			return new LoadOperation(inphelp);
			
		}
		else
		{
			//Abbruchstring falsch eingegeben:
			throw new FactoryException("Insufficient parameter");
		}
	}
}
