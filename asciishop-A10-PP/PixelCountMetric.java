import java.util.*;

/*
Eine einfache Metrik für Bilder, die Bildgrößen vergleicht.
*/

public class PixelCountMetric implements Metric<AsciiImage>
{
	//liefert den Absolutbetrag der Differenz der Bildgrößen von i1 und i2. Mit Bildgröße ist das Produkt von Höhe mal Breite des Bildes gemeint.
	public int distance(AsciiImage i1, AsciiImage i2)
	{
		return Math.abs(i1.getHeight()*i1.getWidth()-i2.getHeight()*i2.getWidth());
	}
}