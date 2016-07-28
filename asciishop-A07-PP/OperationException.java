/*
Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerfälle, die beim Ausführen von Operationen auftreten, eingesetzt.
*/

public class OperationException extends Exception
{
	//erzeugt eine leere OperationException. Ruft den entsprechenden Super-Konstruktor in der Klasse Exception auf.
	public OperationException()
	{
	}

	//erzeugt eine OperationException mit der entsprechenden Fehlerbeschreibung. Ruft den entsprechenden Super-Konstruktor in der Klasse Exception auf.
	public OperationException(String message)
	{
	}
}