import java.util.*;

/*
Eine einfache Metrik f�r Bilder, die Bildgr��en vergleicht.
*/

public class PixelCountMetric implements Metric<AsciiImage>
{
	//liefert den Absolutbetrag der Differenz der Bildgr��en von i1 und i2. Mit Bildgr��e ist das Produkt von H�he mal Breite des Bildes gemeint.
	public int distance(AsciiImage i1, AsciiImage i2)
	{
		return Math.abs(i1.getHeight()*i1.getWidth()-i2.getHeight()*i2.getWidth());
	}
}