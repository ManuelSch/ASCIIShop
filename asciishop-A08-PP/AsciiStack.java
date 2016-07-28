/*
Diese Klasse implementiert einen Stack (vgl. Stapelspeicher), der seine Größe dynamisch anpasst. Er kann eine beliebige Anzahl an AsciiImage-Objekten speichern, wobei der Zugriff immer nur auf das oberste Element möglich ist. Sie können ihre Implementierung dieser Klasse (zusammen mit AsciiStackNode) aus der Vorrunde nutzen oder die vorgefertigte Klasse java.util.Stack verwenden. 
*/

public class AsciiStack
{	
	//Einzelne Elemente der Liste:
	private class AsciiStackNode
	{
		private AsciiImage image;			//das in diesem Element gespeicherte Bild
		private AsciiStackNode next;		//Referenz auf nächstes Element (Listenrest)
		
		//initialisiert den Listenknoten.
		public AsciiStackNode(AsciiImage image, AsciiStackNode next)
		{
			this.image=image;
			this.next=next;
		}
		
		//liefert die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste plus eins (für diesen Knoten).
		public int size()
		{
			return 1+next.size();
		}
		
		public void add(AsciiImage img)
		{
			next = new AsciiStackNode(image,next);
			image = img;
		}
	}

	private AsciiStackNode head;		//1. Element
	
	//erzeugt einen leeren Stack.
	public AsciiStack()
	{
		head=null;
	}
		
	//überprüft, ob zumindest ein Element am Stack liegt.
	public boolean empty()
	{
		//Ist der Stack leer?
		if(head==null)
		{
			return true;
		}
		
		return false;
	}


	//gibt das oberste Element am Stack zurück und entfernt dieses. Liegt kein Element am Stack, so soll null zurückgegeben werden.
	public AsciiImage pop()
	{
		//Ist der Stack leer?
		if(head==null)
		{
			return null;
		}
		
		//1. Element entfernen:
		AsciiImage imghelp = new AsciiImage(head.image);
		head=head.next;	
		
		return imghelp;
	}


	//gibt das oberste Element am Stack zurück ohne es zu entfernen. Liegt nichts am Stack, so soll null zurückgegeben werden.
	public AsciiImage peek()
	{
		//Ist der Stack leer?
		if(head==null)
		{
			return null;
		}

		return new AsciiImage(head.image);
	}


	//legt ein AsciiImage oben auf den Stack.
	public void push(AsciiImage img)
	{
		//Ist der Stack leer?
		if(head==null)
		{
			head = new AsciiStackNode(img,null);
		}
		else
		{
			head.add(img);
		}
	}


	//gibt die Anzahl der Elemente im Stack zurück.
	public int size()
	{
		return head.size();
	}
	
}
