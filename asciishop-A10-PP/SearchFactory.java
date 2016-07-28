import java.util.Scanner;

/*
Diese Factory erzeugt SearchOperation.
 */

public class SearchFactory implements Factory
{
	MetricSet<AsciiImage> saved;

	//erzeugt eine neue SearchFactory. saved ist eine Referenz auf ein MetricSet, in dem sich die gespeicherten Bilder befinden.
	public SearchFactory(MetricSet<AsciiImage> saved)
	{
		this.saved=saved;
	}

	//Erzeugt eine neue SearchOperation. Dazu wird zunächst mit dem angegebenen Scanner ein String eingelesen, der angibt, welche Metrik benutzt werden soll ("pixelcount" oder "uniquechars"). Kann dieser String nicht eingelesen werden, oder ist der eingelesene String unbekannt, wird eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException
	{
		//Metrik einlesen ("pixelcount" oder "uniquechars")
		if (scanner.hasNext())
		{
			String metrik = scanner.next();						
			
			if (metrik.equals("pixelcount"))
			{
				return new SearchOperation(saved, new PixelCountMetric());
			}
			else if (metrik.equals("uniquechars"))
			{
				return new SearchOperation(saved, new UniqueCharsMetric());
			}
			else
			{
				//Keine korrekte Metrik eingegeben:
				throw new FactoryException("Insufficient parameter");
			}
		}
		else
		{
			//Keine Metrik eingegeben:
			throw new FactoryException("Insufficient parameter");
		}
	}

}
