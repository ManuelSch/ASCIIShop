import java.util.Scanner;

/*
Diese Factory erzeugt ClearOperations. 
 */

public class ClearFactory implements Factory {

	//erzeugt eine neue ClearFactory.
	public ClearFactory()
	{
	}

	//erzeugt eine neue ClearOperation und gibt diese zurück.
	public Operation create(Scanner scanner) throws FactoryException
	{
		return new ClearOperation();
	}

}
