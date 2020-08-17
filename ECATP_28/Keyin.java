//  Programm: Keyin
//  Bezug: Lektion 20
//
//  Themen:
//     1. Verwenden der Methode read() der Klasse
//        InputStream im Paket java.io
//     2. Entwickeln einer Klasse f?r die Eingabe
//        von Zeichen und numerischen Typen ?ber die Konsole

import java.io.*;

public class Keyin
{

  //*******************************
  //     Hilfsfunktionen
  //*******************************
  // Anzeige des Prompt
  public static void printPrompt(String prompt)
  {
    System.out.print(prompt + " ");
    System.out.flush();
  }

  // Methode stellt sicher, dass keine Daten
  // im Eingabestream anliegen
  public static void inputFlush()
  {
    int dummy;
    int bAvail;

    try
    {
    while((System.in.available()) != 0)
      dummy = System.in.read();
    }
    catch(java.io.IOException e)
    {
      System.out.println("Eingabefehler");
    }
  }


  //********************************
  // Methoden zum Einlesen von 
  // Strings, int, char und double
  //********************************
  public static String inString(String prompt)
  {
    inputFlush();
    printPrompt(prompt);
    return inString();
  }

  public static String inString()
  {
    int aChar;
    String s = "";
    boolean finished = false;

    while(!finished)
    {
      try
      {
        aChar = System.in.read();
        if(aChar < 0 || (char)aChar == '\n') {
          finished = true;
        }
        else if ((char)aChar != '\r') {
	  s = s + (char) aChar;   // anf?gen
	}
      }
      catch(java.io.IOException e)
      {
        System.out.println("Eingabefehler");
        finished = true;
      }
    }
    return s;
  }

  public static int inInt(String prompt)
  {
    while(true)
    {
      inputFlush();
      printPrompt(prompt);
      try
      {
        return Integer.valueOf(inString().trim()).intValue();
      } 
      catch(NumberFormatException e)
      { 
        System.out.println("Eingabefehler");
      }
    }
  } 

  public static char inChar(String prompt)
  {
    int aChar = 0;

    inputFlush();
    printPrompt(prompt);

    try
    {
      aChar = System.in.read();
    }
    catch(java.io.IOException e)
    {
      System.out.println("Eingabefehler");
    }
    inputFlush();
    return (char) aChar;
  }

  public static double inDouble(String prompt)
  {
    while(true)
    {
      inputFlush();  
	    printPrompt(prompt);
      try
      {
        return Double.valueOf(inString().trim()).doubleValue();
      }
         catch(NumberFormatException e)
      {
         System.out.println("Eingabefehler");
      }
    }
  }
}

