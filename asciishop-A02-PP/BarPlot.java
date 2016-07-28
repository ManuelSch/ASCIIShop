/**
 * AsciiShop-A02-pp
 * Das Programm BarPlot soll Balkenbeschriftungen und Werte paarweise einlesen und ein horizontales Balkendiagramm ausgeben.
 * Manuel Schüller (1426298), 20.10.2014
 */

import java.util.Scanner;

public class BarPlot
{
	//Liest die Daten und Befehle ein und gibt das Ergebnis aus:
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		String label;		//Zeilenbeschriftung
		boolean exit=false;	//Abbruchbedingung der Eingabeschleife
		int intvalue=0;		//Zwischenspeicher absolute Balkenlänge
		double doublevalue=0;	//Zwischenspeicher relative Balkenlänge

		while ((scanner.hasNext())&&(exit==false))
		{
			label=scanner.next();	//Zeilenbeschriftung einlesen
			
			//Wurde eine absolute Balkenlänge eingegeben?
			if (scanner.hasNextInt())
			{
				intvalue=scanner.nextInt();		//Balkenlänge einlesen
				if ((intvalue>=0)&&(intvalue<=30))
				{
					System.out.println(drawBar(label,intvalue));
				}
				else	//Fehlercode bei falscher Eingabe:
				{
					System.out.println("INPUT ERROR");
					exit=true;
				}
			}
			//Wurde eine relative Balkenlänge eingegeben?
			else if (scanner.hasNextDouble())
			{
				doublevalue=scanner.nextDouble();	//Balkenlänge einlesen
				if ((doublevalue>=0)&&(doublevalue<=1))
				{
					System.out.println(drawBar(label,doublevalue));
				}
				else	//Fehlercode bei falscher Eingabe:
				{
					System.out.println("INPUT ERROR");
					exit=true;
				}
			}
			//Fehlercode bei falscher Eingabe:
			else
			{
				System.out.println("INPUT ERROR");
				exit=true;
			}
		}
	}

	//Liefert einen String der Länge n zurück der nur aus dem Zeichen c besteht:
	static String repeat(char c, int n)
	{
		String output="";
		int i;

		//Das Zeichen c wird n mal an den leeren String angehängt:
		for (i=0;i<n;i++)
		{
			output+=c;
		}

		return(output);
	}

	//Liefert einen String zurück der label beinhaltet aber genau n Zeichen lang ist.
	//Wenn label zu lange ist wird es abgeschnitten, wenn label zu kurz ist, wird der Rückgabewert mit Leerzeichen aufgefüllt.
	static String drawLabel(String label, int n)
	{
		//Kürzere Strings mit Leerzeichen auffüllen:
		while((label.length()<n))
		{
			label+=" ";
		}

		//Längere Strings abschneiden:
		label=label.substring(0,n);

		return(label);
	}

	//Generiert eine Zeile des Balkendiagramms. (absolute Balkenlänge)
	//value bezeichnet die absolute des Balkens.
	static String drawBar(String label, int value)
	{
		//Zeilenbeschriftung ausgeben:
		label=drawLabel(label,8);
	
		//Balken ausgeben:
		label+="|"+drawLabel(repeat('#',value),30)+"|";
		
		return label;
	}

	//Generiert eine Zeile des Balkendiagramms (relative Balkenlänge)
	//value bezeichnet die prozentuelle Länge des Balkens.
	static String drawBar(String label, double value)
	{
		//Zeilenbeschriftung ausgeben:
		label=drawLabel(label,8);

		//Wert auf ganzzahlige Balkenlänge runden:
		value=Math.round(value*30);

		//Balken ausgeben:
		label+="|"+drawLabel(repeat('#',(int)value),30)+"|";
		
		return label;	
	}
}







