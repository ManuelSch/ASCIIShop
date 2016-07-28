/**
 * AsciiShop-A01-pp
 * Das Programm soll ein ASCII-Bild zeilenweise von der Standardeingabe einlesen und überprüfen, ob alle Zeilen gleich lang sind. Nach dem Einlesen wird die Höhe und die Breite des Bildes (sprich Länge und Anzahl der Zeilen) ausgegeben. Sollte die Eingabe fehlerhaft sein, so wird stattdessen eine Fehlermeldung ausgegeben.
 * Manuel Schüller (1426298), 20.10.2014
 */

import java.util.Scanner;

public class AsciiShop
{
	public static void main(String[] args)
	{
		String inp;			//Zwischenspeicher für User-Input
		int hoehe=0, breite=0;		//Anzahl der Zeilen und Spalten des Bildes.
		boolean mismatch=false;		//Abbruchbedingung der Eingabeschleife

		//Scanner für User-Input:
		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextLine())
		{
			inp = scanner.nextLine();	//Erste Zeile einlesen
			hoehe++;
			breite=inp.length();		//Bildbreite wird durch erste Zeile festgelegt
			while((mismatch==false)&&(scanner.hasNextLine()))
			{	
			 	hoehe++;
				inp = scanner.nextLine();	//Weitere Zeilen einlesen
			  	if(inp.length()!=breite)	//Abbruch durch falschen User-Input
			   	{
					System.out.println("INPUT MISMATCH");
					mismatch=true;
				}
			}
		}

		if (mismatch==false)		//Erfolgreiches Beenden des Programms
		{
			System.out.println(breite+" "+hoehe);	//Ausgabe der Bildbreite- und Höhe
		}
	}
}







