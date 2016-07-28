import java.util.Scanner;

/*
Diese Factory erzeugt BinaryOperations.
 */

public class BinaryFactory implements Factory {

	//erzeugt eine neue BinaryFactory.
	public BinaryFactory()
	{
	}

	//liest mit dem Scanner das Schwellwert Zeichen ein, erzeugt damit eine neue BinaryOperation und gibt diese zur�ck. Tritt beim Einlesen des Zeichens ein Fehler auf, so wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		if (scanner.hasNext())
		{
			char threshold = scanner.next().charAt(0);		//Schwellenwert einlesen
			
			//Binary-Operation zur�ckgeben
			return new BinaryOperation(threshold);
		}
		else
		{
			//Schwellenwert falsch eingegeben:
			throw new FactoryException("Insufficient parameter");
		}
	}
}
