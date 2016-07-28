/*
Diese Factory erzeugt MedianOperations.
 */
 
import java.util.Scanner;

public class FilterFactory implements Factory
{

	//erzeugt eine neue FilterFactory.
	public FilterFactory()
	{
	}

	//liest den nächsten String ein und gibt, falls dieser ‘median’ ist, eine neue MedianOperation zurück. Tritt beim Einlesen des Strings ein Fehler auf, oder ist der String nicht ‘median’, so wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		if (scanner.hasNext())
		{
			String filtertype = scanner.next();		//Filtertyp einlesen
			
			//Median-Filter:
			if(filtertype.equals("median"))
			{
				//Median-Operation ausführen:
				return new MedianOperation();
			}
			//Average-Filter:
			else if(filtertype.equals("average"))
			{
				//Average-Operation ausführen:
				return new AverageOperation();
			}
			else
			{
				//Filtertyp falsch eingegeben:
				throw new FactoryException("Insufficient parameter");
			}
		}
		else
		{
			//Filtertyp falsch eingegeben:
			throw new FactoryException("Insufficient parameter");
		}
	}

}
