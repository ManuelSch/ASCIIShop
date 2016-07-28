/*
*Diese Klasse implementiert einen Stack (vgl. Stapelspeicher), der seine Größe dynamisch anpasst.
*Er kann eine beliebige Anzahl an AsciiImage-Objekten speichern, wobei der Zugriff immer nur auf das oberste Element möglich ist.
*Diese Implementierung nutzt intern ein Array zum Speichern der Elemente.
*/

public class AsciiStack
{
	int increment;
	int counter;
	AsciiImage[] image;
	
	//erzeugt einen Stack, der initial increment Elemente speichern kann. Sie dürfen davon ausgehen, dass increment größer 0 ist. Das Parameter increment gibt auch an, um wieviel der Stack bei Bedarf vergrößert bzw. verkleinert werden soll.
	public AsciiStack(int increment)
	{
		this.increment=increment;
		counter=0;
		
		image=new AsciiImage[increment];
		
		for(int i=0; i<capacity(); i++)
		{
			image[i]=null;
		}
	}

	//gibt die Anzahl der Stack bereit stehenden Plätze zurück (sprich wie groß das zu Grunde liegende Array ist). Aufgrund der Vorgehensweise bei Vergößerung und Verkleinerung, ist das Ergebnis dieser Methode immer ein Vielfaches von increment.
	public int capacity()
	{
		return image.length;
	}
		
	//überprüft, ob der Stack leer ist. In diesem Fall liefert die Methode true. Wenn mindestens ein Element am Stack liegt liefert die Methode false.
	public boolean empty()
	{
		if(counter==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	//gibt das oberste Element am Stack zurück und entfernt dieses. Liegt kein Element am Stack, so soll null zurückgegeben werden. Sind nach dem Entfernen mehr als increment Plätze leer, so soll der Stack um increment verkleinert werden. Jedoch soll der Stack nie kleiner als increment sein, sprich wenn alle Elemente entfernt wurden, soll die Kapazität des Stacks gleich increment sein.
	public AsciiImage pop()
	{
		//Ist der Stack leer?
		if(counter==0)
		{
			return null;
		}
		else
		{
			
			//Kann die Größe des Stacks verringert werden?
			if (counter+increment<=capacity())
			{
				AsciiImage[] imghelp=new AsciiImage[capacity()-increment];	//Neuen Stack mit verringertem Speicher
				
				//Inhalt des alten in den neuen Stack kopieren:
				for(int i=0; i<capacity()-increment; i++)
				{
					imghelp[i]=image[i];
				}
				
				//Neuen Stack in aktuellen Stack zurückschreiben:
				image=imghelp;
			}
			counter--;
			
			return image[counter];		//Zuletzt hinzugefügtes Element zurückgeben
		}
	}/**/


	//gibt das oberste Element am Stack zurück ohne es zu entfernen. Liegt nichts am Stack, so soll null zurückgegeben werden.
	public AsciiImage peek()
	{
		//Ist der Stack leer?
		if(counter==0)
		{
			return null;
		}
		else
		{
			return image[counter-1];	//Zuletzt hinzugefügtes Element zurückgeben
		}
	}


	//legt ein AsciiImage oben auf den Stack. Ist der Stack zu diesem Zeitpunkt voll, so soll der Stack um increment vergrößert werden um so das Bild speichern zu können.
	public void push(AsciiImage img)
	{
		//Ist der Stack bereits voll?
		if(counter+1>capacity())
		{
			AsciiImage[] imghelp=new AsciiImage[capacity()+increment];	//Neuen Stack mit erhöhtem Speicher
			
			//Inhalt des alten in den neuen Stack kopieren:
			for(int i=0; i<capacity(); i++)
			{
				imghelp[i]=image[i];
			}
			
			//Neuen Stack in aktuellen Stack zurückschreiben:
			image=imghelp;
		}
	
		//Neues Bild zum Stack hinzufügen:
		image[counter]=new AsciiImage(img);

		//Stackzähler erhöhen:
		counter++;
	}


	//gibt die Anzahl der im Stack belegten Plätze zurück. 
	public int size()
	{
		return counter;
	}
	
}/**/
