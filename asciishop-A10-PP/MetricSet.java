import java.util.*;

/*
Diese generische Klasse implementiert ein spezielles Set auf der Basis von LinkedHashSet mit der Besonderheit, dass in dem MetricSet mit Hilfe einer Metrik nach Objekten gesucht werden kann, die einem spezifizierten Objekt �hnlich sind. Beachten Sie dazu das Interface Metric. Die Klasse soll als Erweiterung der Klasse LinkedHashSet<E> definiert werden. Ein LinkedHashSet<E> ist Untertyp von HashSet<E> mit der Besonderheit, dass die Einf�gereihenfolge der Elemente erhalten bleibt (siehe Hinweise). Es gibt Methoden, die von der Klasse LinkedHashSet bzw. deren direkten und indirekten Oberklassen geerbt werden und hier nicht explizit angegeben sind. Die f�r diese Aufgabe n�tzlichen geerbten Methoden sind im Folgenden angef�hrt (diese k�nnen genutzt werden, �berschreiben ist nicht gefragt). F�r eine vollst�ndige Auflistung der Methoden, siehe java.util.LinkedHashSet und Oberklasse java.util.HashSet.
*/

public class MetricSet<E> extends LinkedHashSet<E>
{
	//initialisiert ein leeres MetricSet.
	public MetricSet()
	{
	}
	
	//initialisiert das MetricSet mit den Elementen aus c.
	public MetricSet(Collection<? extends E> c)
	{
		//Alle Elemente von c dem MetricSet hinzuf�gen:
		addAll(c);
	}
	
	//liefert ein neues MetricSet zur�ck, in dem nur die Elemente enthalten sind, die die minimale Distanz zum spezifizierten Element e haben. Das kann auch nur ein Element sein. m ist die Metrik, die als Distanzma� benutzt werden soll.
	public MetricSet<E> search(E e, Metric<? super E> m)
	{
		MetricSet<E> mhelp = new MetricSet<E>();
		Iterator<E> it = this.iterator();
		int min=-1;
		
		//Minimale Distanz bestimmten:
		while(it.hasNext())
		{
			E ehelp = it.next();
			if((min==-1) || (min>m.distance(e,ehelp)))
			{
				min = m.distance(e,ehelp);
			}
		}
		
		//Neues MetricSet erstellen:
		it = this.iterator();	//Iterator zur�cksetzen
		while(it.hasNext())
		{
			E ehelp = it.next();
			if(m.distance(e,ehelp)==min)
			{
				mhelp.add(ehelp);
			}
		}
		
		return mhelp;
	}
	
	//Stellt sicher, dass das spezifizierte Objekt e, in der Menge vorhanden ist. Genauer gesagt: F�gt das Element e dem Set hinzu, wenn es in diesem kein Element e2 gibt, sodass (e==null ? e2==null : e.equals(e2)). Liefert true wenn es hinzugef�gt wurde und false wenn ein solches Element bereits vorhanden war.
	//public boolean add(E e)
	
	//F�gt alle Elemente von col dem Set hinzu. Liefert true wenn das Set dadurch ver�ndert wurde und false, wenn alle Elemente bereits vorhanden waren.
	//public boolean addAll(Collection<? extends E> col)
	
	//Gibt an, ob das Objekt o in diesem Set enthalten ist, d.h., ob es in diesem Set ein Element e gibt, sodass (o==null ? e==null : o.equals(e)).
	//public boolean contains(Object o);
	
	//liefert einen Iterator auf dem Set. Der Iterator durchmustert die Elemente des Set in der gleichen Reihenfolge, in der Sie dem Set hinzugef�gt wurden.
	//public Iterator<E> iterator();	
}