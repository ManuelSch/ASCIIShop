import java.util.*;

public class SearchOperation implements Operation
{
	MetricSet<AsciiImage> saved;
	Metric<AsciiImage> m;
	
	//initialisiert diese SearchOperation mit einer angegebenen Metrik. saved ist eine Referenz auf ein MetricSet, in dem sich die gespeicherten Bilder befinden. m ist die Metrik.
	public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m)
	{
		this.saved=saved;
		this.m=m;
	}
	
	//liefert ein Bild mit minimaler Distanz zum spezifizierten Bild und liefert dieses als Kopie zurück. Gibt es mehrere gespeicherte Bilder mit minimaler Distanz, wird irgendeines dieser Bilder geliefert. Ist saved leer, wird eine OperationException geworfen.
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		MetricSet<AsciiImage> help = saved.search(img, m);
		
		if(help.isEmpty()==true)
		{
			throw new OperationException();
		}
		else
		{
			Iterator<AsciiImage> it = help.iterator();
			return new AsciiImage(it.next());
		}
	}
}