public class CreateOperation implements Operation
{
	int breite, hoehe;
	String charset;
	
	//erzeugt eine neue CreateOperation, die ein neues Bild mit angegebener Bildg��e und Zeichensatz erzeugt. Alle Pixel werden mit dem "hellsten" Zeichen, d.h. dem Zeichen mit gr��ten Index in charset initialisiert.
	public CreateOperation(int width, int height, String charset)
	{
		this.breite=width;
		this.hoehe=height;
		this.charset=charset;
	}
	
	//gibt ein neues AsciiImage zur�ck. Der Parameter wird ignoriert (beispielsweise kann auch null �bergeben werden).
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		return new AsciiImage(breite,hoehe,charset);
	}
}