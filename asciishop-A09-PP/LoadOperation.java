/*
L�dt zeilenweise vorliegende Bilddaten in ein AsciiImage.
*/

import java.util.Scanner;

public class LoadOperation implements Operation
{
	private String data;
	
	//erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten. Diese Bilddaten liegen als String vor, wobei die Bildzeilen durch Zeilenumbr�che (�\n�) getrennt sind.
	public LoadOperation(String data)
	{
		this.data=data;
	}
	
	//gibt ein neues AsciiImage zur�ck, das von Gr��e und Zeichensatz dem �bergebenen AsciiImage entspricht und in das die Daten geladen wurden. Tritt beim Laden ein Fehler auf (zu wenige oder zu viele Daten bzw. ung�ltige Zeichen), so wird eine OperationException mit einer entsprechenden Fehlermeldung geworfen.
	public AsciiImage execute(AsciiImage img) throws OperationException
	{
		Scanner scanner = new Scanner (data);
		AsciiImage image = new AsciiImage(img);
		int y=0;
		
		//Zeilenweise ins neue Bild schreiben:
		while(scanner.hasNextLine())
		{
			String zeile = scanner.nextLine();
			
			//Hat die aktuelle Zeile die richtige L�nge?
			if(zeile.length()==img.getWidth())
			{
				for(int x=0; x<zeile.length(); x++)
				{
					if (img.getCharset().indexOf(zeile.charAt(x)) < 0)
					{
						throw new OperationException("Zeichen nicht im Zeichensatz enthalten");
					}
					else
					{
						image.setPixel(x,y,zeile.charAt(x));
					}
				}
				y++;
			}
			else
			{
				throw new OperationException("Zeilenlaenge falsch");
			}
		}
	
		//Hat das Bild die richtige H�he?
		if(y==img.getHeight())
		{
			return image;
		}
		else
		{
			throw new OperationException("Zeilenanzahl falsch");
		}
	}
}