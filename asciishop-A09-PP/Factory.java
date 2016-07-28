/**
Dieses Interface wird von allen Factories implementiert und definiert die Schnittstelle über die eine Operation bezogen werden kann. Bei Bedarf können Sie dies statt als Interface auch als abstrakte Klasse gestalten. Die Aufgabe der Factory ist es bei Bedarf Eingaben einzulesen und dann eine neue Operation zu erzeugen. 
 */
 
import java.util.Scanner;

public interface Factory {

	//erzeugt ein neues Objekt vom Typ Operation. Welche konkrete Operation erzeugt wird, hängt von der implementierenden Factory ab. Bei Bedarf liest diese Methode vom übergebenen Scanner Parameter ein. Sollten Parameter fehlen oder von einem falschen Typ sein, so soll eine FactoryException geworfen werden.
	public Operation create(Scanner scanner) throws FactoryException;

}
