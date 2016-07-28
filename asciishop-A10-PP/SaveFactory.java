import java.util.Scanner;

/*
Diese Factory erzeugt SaveOperation.
 */

public class SaveFactory implements Factory
{
	MetricSet<AsciiImage> saved;

	//erzeugt eine neue SaveFactory. saved ist eine Referenz auf ein MetricSet, dem durch eine SaveOperation Bilder hinzugefügt werden sollen.
	public SaveFactory(MetricSet<AsciiImage> saved)
	{
		this.saved=saved;
	}

	//Erzeugt eine neue SaveOperation.
	public Operation create(Scanner scanner) throws FactoryException
	{
		return new SaveOperation(saved);
	}

}
