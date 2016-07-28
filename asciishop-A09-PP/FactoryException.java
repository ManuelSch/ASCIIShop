/*
Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerfälle, die in einer Factory, also beim Einlesen von Parametern oder dem Erzeugen eines Befehls, auftreten, eingesetzt. Sie können bei Bedarf auch noch weitere Konstruktoren definieren. 
*/

public class FactoryException extends Exception
{
	//erzeugt eine leere FactoryException. Ruft den entsprechenden Super-Konstruktor in der Klasse Exception auf.
	public FactoryException()
	{
	}

	//erzeugt eine FactoryException mit der entsprechenden Fehlerbeschreibung. Ruft den entsprechenden Super-Konstruktor in der Klasse Exception auf. 
	public FactoryException(String message)
	{	
	}
}
    
