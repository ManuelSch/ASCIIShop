import java.util.*;

/*
Eine einfache Metrik für Bilder, die die Anzahl unterschiedlicher Zeichen vergleicht.
*/

public class UniqueCharsMetric implements Metric<AsciiImage>
{
	//liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild. Zur Berechnung der Anzahl unterschiedlicher Zeichen eines Bildes kann die Methode getUniqueChars() aus Runde 4 herangezogen werden.
	public int distance(AsciiImage i1, AsciiImage i2)
	{
		return Math.abs(i1.getUniqueChars()-i2.getUniqueChars());
	}
}