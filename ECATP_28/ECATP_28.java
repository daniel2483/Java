import java.io.FileInputStream;
import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Random;
import java.util.Calendar;
import java.lang.*;
import java.lang.Object;



public class ECATP_28
{
	public static void main (String[] args) throws java.io.IOException,java.text.ParseException
	{

		int errormargincorrection;
		int segmentdimension=10;
		float errormargin;
		int segmentspassed, segmentsfailed, totalsegments;
		float percentagepassed,percentagefailed,sumpercentages;

		//Hier fehlt folgendes
		// Einfuhrung
		//fragt nach filename
		// fragt nach logfile name
		//Fragt nach Zone
		//fragt nach Ausgabedatei

		String locationname, locationnumber, outputpath, logfilename, date, analysisengineer, fieldengineer, icepersonnel, department;
		int thresholdselection, ercmethod;
		int morefiles;
		int threshold=0;

/*Calendar c=Calendar.getInstance();
int day=c.get(Calendar.DAY);
int month=c.get(Calendar.MONTH);
int year=c.get(Calendar.YEAR);
String dates=day+"/"+month+"/"+year;
System.out.println(day);*/

		System.out.println("Hugo Navas Aparicio Java Program for ERICSSON \nLast Update 2010-05-30 by Jose Daniel Rodriguez S.");
		verketteteliste Originalliste = new verketteteliste();Originalliste.insertelement("ECTAP",0,0,null,null);
		conf confliste = new conf();confliste.insertelement("",0,0,0,0,0);
		hypothesislesen(confliste);		// Lectura de Archivo de conf inicial
		confliste.deletefirst();
		confliste.output();

		locationname=Keyin.inString("Please provide Location Name: \t");
		locationnumber=Keyin.inString("Please provide Location Number: \t");
		thresholdselection=Keyin.inInt("Please provide Test Area [1]RAT [2]Location [3]Highway: \t");
		//System.out.println("Please full path for the output files: \t");
		outputpath="c:\\aa";//Keyin.inString("Note> instead of '\\' write'\\\\': \t");
		date="";//Keyin.inString("Enter date of drive test (YYYY/MM/DD): \t");
		analysisengineer="HN";//Keyin.inString("Name of Ericsson Consultant Engineer: \t");
		department="NPOC";//Keyin.inString("Department: \t");
		fieldengineer="";//Keyin.inString("Name of Ericsson Field Engineer: \t");
		icepersonnel="";//Keyin.inString("Name of ICE Representant: \t");
		ercmethod=1;//Keyin.inInt("Provide Analysis Method [1] ERCR1 [2]ERCR2: \t");
		morefiles=Keyin.inInt("Amount of files [1]Only One [2]More files: \t");
		logfilename=Keyin.inString("Name of Logfile(s) separated by ';' : \t");

		//Aqui se selecciona el tipo de ruta
		switch(thresholdselection)
			{
				case 1:
					threshold=-70; //RAT
					break;
				case 2:
					threshold=-85; //Other Location
					break;
				case 3:
					threshold=-85; //Highways
					break;
			}

		if(ercmethod==1)
			{
				blockedfilelesen(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;
			}
		else
			{
				if(ercmethod==2)
					{
						blockedfilelesenmethod2(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;
					}
			}


		int segments = Originalliste.numberofsegments();
		System.out.println("Segments "+segments +"\n");


		errormargincorrection=confliste.errormarginadaption(segments);
		errormargin=confliste.margintouse(errormargincorrection)*100;

		System.out.println("Samples needed = "+errormargincorrection);
		System.out.println("NEW Error Margin to use = "+errormargin+"%");
		System.out.println("Samples needed to achieve confidence level = "+errormargincorrection);

	randomselection(Originalliste,errormargincorrection);
	Originalliste.ausgabedateiRNDM(outputpath, locationname, locationnumber);

	segmentspassed=Originalliste.passedsegments(threshold);
	segmentsfailed=Originalliste.failedsegments(threshold);
	totalsegments=segmentspassed+segmentsfailed;

	percentagepassed=((float)segmentspassed/(float)totalsegments)*100;
	percentagefailed=((float)segmentsfailed/(float)totalsegments)*100;
	sumpercentages=percentagepassed+percentagefailed;

	//Hier wird der Analysis durchgefuehrt
	double limittocomply = 0.95-(errormargin/100);
	double limittocomply100=limittocomply*100;
	String evaluation;
	if(percentagepassed>=limittocomply100)
		{
			evaluation="Prueba exitosa - Se cumple con el requerimiento";
		}
	else
		{
			evaluation="Prueba fallida - No se cumple con el requerimiento";
		}


	System.out.println("Passed "+"\t"+segmentspassed+"\t"+percentagepassed+"%");
	System.out.println("Failed "+"\t"+segmentsfailed+"\t"+percentagefailed+"%");
	System.out.println("Total Segments "+"\t"+totalsegments+"\t"+sumpercentages+"%");
	System.out.println("% to comply with:\t"+limittocomply100+"%");
	System.out.println(evaluation);





	// Hier wird die Ausgabedatei erzeugt in HTML mit logos
		// DatenFelder
		String delimiter ="\t";
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		String outputfilename = fullpath+"\\"+locationnumber+"_"+locationname+"_FINAL REPORT.html";


		// Initialisiere Ausgabedatei
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{
					fos.println("<html>");fos.print(delimiter);
					fos.println("<head>");fos.print(delimiter);
					fos.println("<H1>Ericsson Costa Rica - Pruebas de Aceptacion de Cobertura</H1>");fos.print(delimiter);
					fos.println("</head>");fos.print(delimiter);
					fos.println("<HR width=95%>");fos.print(delimiter);
					fos.println("<body>");fos.print(delimiter);
					fos.println("<P>Pais:		Costa Rica<br>");fos.print(delimiter);
					fos.println("Cliente:		ICE<br>");fos.print(delimiter);
					fos.println("Tecnologia:	GSM<br>");fos.print(delimiter);
					fos.println("Proyecto:	ICE-GSM600k</P>");fos.print(delimiter);
					fos.println("<P><B>Datos de la Prueba</B></P>");fos.print(delimiter);

					fos.print("<P>Nombre de la localidad:");fos.print(delimiter);fos.print(locationname);
					fos.print("<br>Numero de la localidad:");fos.print(delimiter);fos.print(locationnumber);
					fos.print("<br>Nivel de intensidad de signal requerido:");fos.print(delimiter);fos.print(threshold);fos.print(" dBm");
					fos.print("<br>Nombre de los archivos del Logfile:");fos.print(delimiter);fos.print(logfilename);
					fos.print("<br>Personal de Ericsson:");fos.print(delimiter);fos.print(fieldengineer);
					fos.print("<br>Personal del ICE:");fos.print(delimiter);fos.print(icepersonnel);


					fos.println("</P><P><B>Resultado</B></P>");fos.print(delimiter);
					fos.print("<P>Consultor de Ericsson:");fos.print(delimiter);fos.print(analysisengineer);
					fos.print("<br>Departamento:");fos.print(delimiter);fos.print(department);
					fos.print("<br>Metodo de Analisis:ERCR-");fos.print(delimiter);fos.print(ercmethod);
					fos.print("<br>Tamano del Segmento (m):");fos.print(delimiter);fos.print(segmentdimension);
					fos.print("<br>Fecha:");fos.print(delimiter);fos.print(date);
					fos.println("</P> ");fos.print(delimiter);

					fos.print("<P>Numero de Segmentos total en la prueba ");fos.print(delimiter);fos.print(segments);fos.print("");
					fos.print("<br>Margen de Error adaptado ");fos.print(delimiter);fos.print(errormargin);fos.print("%");
					fos.print("<br>Numero de Segmentos necesarios y seleccionados para la prueba de hypotesis (en funcion del margen de error): ");fos.print(delimiter);fos.print(errormargincorrection);fos.print("");
					fos.print("<br>Numero de Segmentos exitosos:");fos.print(delimiter);fos.print(segmentspassed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagepassed+"%");
					fos.print("<br>Numero de Segmentos fallidos:");fos.print(delimiter);fos.print(segmentsfailed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagefailed+"%");

					fos.print("<br><P>Porcentaje a cumplir considerando el margen de error:");fos.print(delimiter);fos.print(limittocomply100);fos.print("%");
					fos.print("<br><b>RESULTADO FINAL DE LA PRUEBA:");fos.print(delimiter);fos.print(evaluation);fos.print(delimiter);fos.print("<b></P>");

					fos.println("<P><B>Representante de Ericsson</B></P>");fos.print(delimiter);
					fos.println("<P>Firma</P>");fos.print(delimiter);
					fos.println("<P>Fecha</P>");fos.print(delimiter);
					fos.println("<P><B>Representante de ICE</B></P>");fos.print(delimiter);
					fos.println("<P>Firma</P>");fos.print(delimiter);
					fos.println("<P>Fecha</P>");fos.print(delimiter);
					fos.println("</body>");fos.print(delimiter);
					fos.println("</html>");fos.print(delimiter);


			}
		else
			{
				System.out.println("Fehler beim Erzeugen der Ausgabedatei");
			}
		System.out.println("*************************************");
		//fos.flush();
		fos.close();

	}








	// Methoden****************************************
	// Method to process the whole data****************

	public static void blockedfilelesen(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
	{
		// lokale variablen
		int counter =0;			// Inicio contador
		String dateiname;
		int anotherfile;

		String zeit,dummy;
		int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27;
		double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm,arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm,arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm,arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
		String aline;
		double latitude,longitude;
		String ms, framenumber, direction,event;
		File lesedatei;

		do
		{
			
		// Espera nombre de archivo
		dateiname=Keyin.inString("Enter file name to process: \t");

		// Initialisiere Eingabedatei
		// Dar formato de archivo de entrada

		// File lesedatei = new File(dateiname);
		// Hace Lectura de archivos  
		lesedatei=new File(dateiname);
		
		// En caso de que el archivo no exista escribir File do not exists y pregunta por otro file
		if(lesedatei.exists()==false)
		{

			do{
				dateiname=Keyin.inString("File does not exists. Enter file name to process: \t");

				// Initialisiere Eingabedatei

				lesedatei=new File(dateiname);

				}while (lesedatei.exists()!=true);	// Hacer mientras archivo no exista
													// Sale de ciclo si archivo existe
		}

		// En caso de que el archivo exista y pueda ser leido
		if( lesedatei.exists()==true && lesedatei.canRead()==true)
			{
				// Hago lectura de lesedatei en caso de existir y poderse leer
				// Crea el objeto bf de la lectura realizada
				BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
				
				// Lee una línea de texto hasta que encuentra un carácter de salto de línea (\n) y retorno de carro (\r). 
				aline = bf.readLine();		// La primer fila es invalida ya que son los nombres de las columnas
				// Lee una línea de texto hasta que encuentra un carácter de salto de línea (\n) y retorno de carro (\r).
				aline = bf.readLine();		// La segunda fila ya es válida
				
				do
				{
						// Erzeuge Token
						// La clase StringTokenizer divide cadena por tabulaciones
						StringTokenizer tokens = new StringTokenizer(aline,"\t",false);


						// Erzeuge ein NumberFormatter Objekt
						// Clase para dar formato a los numeros
						NumberFormat nf = NumberFormat.getInstance();
						
						// Erzeuge Leser
						// Separar aline (la linea o fila) cuando encuentra tabs 
						// y almacena los valores en el arreglo String [], 
						// mantiene empty strings del aline (-1)
						String[] leser = aline.split("\t",-1);

						// Metodo trim() remueve espacios en blanco de un String al inicio y al final
						zeit=leser[0].trim(); 							// La primer columna es el tiempo
						ms=leser[1].trim();   							// La segunda columna es el ms
						framenumber=leser[2].trim();					// La tercera columna es el framenumber 
						direction=leser[3].trim();						// La cuarta columna es Direction

						event=leser[4].trim();System.out.println(event);// La quinta columna es Event

						// Obtengo el Lector de latitud
						dummy=leser[5];									// La sexta columna es Latitud
							if(dummy.compareTo("")==0)
								{latitude=0;}
							else
								{
								latitude=nf.parse(leser[5]).floatValue();
								}

						//Obtengo el Lector de Longitud
						dummy=leser[6];									// La septima columnae es Longitud
							if(dummy.compareTo("")==0)
								{longitude=0;}
							else
								{
								longitude=nf.parse(leser[6].trim()).floatValue();
								}

						//Obtengo el Lector de Speed
						dummy=leser[7];									// La octava columna es Speed
							if(dummy.compareTo("")==0)
								{speed=0;}
							else
								{
								speed=nf.parse(leser[7].trim()).intValue();
								}

						//Obtengo el Lector de arfcn[0-27] y el arfcn[0-27]dbm
						dummy=leser[8];
							if(dummy.compareTo("")==0)
								{ 	arfcn00=0;
									arfcn00=0;
									arfcn00dbm=0;
									arfcn01=0;
									arfcn01dbm=0;
									arfcn02=0;
									arfcn02dbm=0;
									arfcn03=0;
									arfcn03dbm=0;
									arfcn04=0;
									arfcn04dbm=0;
									arfcn05=0;
									arfcn05dbm=0;
									arfcn06=0;
									arfcn06dbm=0;
									arfcn07=0;
									arfcn07dbm=0;
									arfcn08=0;
									arfcn08dbm=0;
									arfcn09=0;
									arfcn09dbm=0;
									arfcn10=0;
									arfcn10dbm=0;
									arfcn11=0;
									arfcn11dbm=0;
									arfcn12=0;
									arfcn12dbm=0;
									arfcn13=0;
									arfcn13dbm=0;
									arfcn14=0;
									arfcn14dbm=0;
									arfcn15=0;
									arfcn15dbm=0;
									arfcn16=0;
									arfcn16dbm=0;
									arfcn17=0;
									arfcn17dbm=0;
									arfcn18=0;
									arfcn18dbm=0;
									arfcn19=0;
									arfcn19dbm=0;
									arfcn20=0;
									arfcn20dbm=0;
									arfcn21=0;
									arfcn21dbm=0;
									arfcn22=0;
									arfcn22dbm=0;
									arfcn23=0;
									arfcn23dbm=0;
									arfcn24=0;
									arfcn24dbm=0;
									arfcn25=0;
									arfcn25dbm=0;
									arfcn26=0;
									arfcn26dbm=0;
									arfcn27=0;
									arfcn27dbm=0;
								}
							else
								{
									//System.out.println("LLegue aqui"); // Bandera
									arfcn00=nf.parse(leser[8].trim()).intValue();
									arfcn00dbm=nf.parse(leser[9].trim()).intValue();
									arfcn01=nf.parse(leser[10].trim()).intValue();
									arfcn01dbm=nf.parse(leser[11].trim()).intValue();
									arfcn02=nf.parse(leser[12].trim()).intValue();
									arfcn02dbm=nf.parse(leser[13].trim()).intValue();
									arfcn03=nf.parse(leser[14].trim()).intValue();
									arfcn03dbm=nf.parse(leser[15].trim()).intValue();
									arfcn04=nf.parse(leser[16].trim()).intValue();
									arfcn04dbm=nf.parse(leser[17].trim()).intValue();
									arfcn05=nf.parse(leser[18].trim()).intValue();
									arfcn05dbm=nf.parse(leser[19].trim()).intValue();
									arfcn06=nf.parse(leser[20].trim()).intValue();
									arfcn06dbm=nf.parse(leser[21].trim()).intValue();
									arfcn07=nf.parse(leser[22].trim()).intValue();
									arfcn07dbm=nf.parse(leser[23].trim()).intValue();
									arfcn08=nf.parse(leser[24].trim()).intValue();
									arfcn08dbm=nf.parse(leser[25].trim()).intValue();
									arfcn09=nf.parse(leser[26].trim()).intValue();
									arfcn09dbm=nf.parse(leser[27].trim()).intValue();
									arfcn10=nf.parse(leser[28].trim()).intValue();
									arfcn10dbm=nf.parse(leser[29].trim()).intValue();
									arfcn11=nf.parse(leser[30].trim()).intValue();
									arfcn11dbm=nf.parse(leser[31].trim()).intValue();
									arfcn12=nf.parse(leser[32].trim()).intValue();
									arfcn12dbm=nf.parse(leser[33].trim()).intValue();
									arfcn13=nf.parse(leser[34].trim()).intValue();
									arfcn13dbm=nf.parse(leser[35].trim()).intValue();
									arfcn14=nf.parse(leser[36].trim()).intValue();
									arfcn14dbm=nf.parse(leser[37].trim()).intValue();
									arfcn15=nf.parse(leser[38].trim()).intValue();
									arfcn15dbm=nf.parse(leser[39].trim()).intValue();
									arfcn16=nf.parse(leser[40].trim()).intValue();
									arfcn16dbm=nf.parse(leser[41].trim()).intValue();
									arfcn17=nf.parse(leser[42].trim()).intValue();
									arfcn17dbm=nf.parse(leser[43].trim()).intValue();
									arfcn18=nf.parse(leser[44].trim()).intValue();
									arfcn18dbm=nf.parse(leser[45].trim()).intValue();
									arfcn19=nf.parse(leser[46].trim()).intValue();
									arfcn19dbm=nf.parse(leser[47].trim()).intValue();
									arfcn20=nf.parse(leser[48].trim()).intValue();
									arfcn20dbm=nf.parse(leser[49].trim()).intValue();
									arfcn21=nf.parse(leser[50].trim()).intValue();
									arfcn21dbm=nf.parse(leser[51].trim()).intValue();
									arfcn22=nf.parse(leser[52].trim()).intValue();
									arfcn22dbm=nf.parse(leser[53].trim()).intValue();
									arfcn23=nf.parse(leser[54].trim()).intValue();
									arfcn23dbm=nf.parse(leser[55].trim()).intValue();
									arfcn24=nf.parse(leser[56].trim()).intValue();
									arfcn24dbm=nf.parse(leser[57].trim()).intValue();
									arfcn25=nf.parse(leser[58].trim()).intValue();
									arfcn25dbm=nf.parse(leser[59].trim()).intValue();
									arfcn26=nf.parse(leser[60].trim()).intValue();
									arfcn26dbm=nf.parse(leser[61].trim()).intValue();
									arfcn27=nf.parse(leser[62].trim()).intValue();
									arfcn27dbm=nf.parse(leser[63].trim()).intValue();
									dummy=leser[64].trim();
								}
						//System.out.println(zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm+"\t");

						//Lies die Daten im String und Speichere sie im variablen

						//musterid=nf.parse(tokens.nextToken()).intValue();

						/*zeit=tokens.nextToken();System.out.println(zeit);
						ms=tokens.nextToken();System.out.println(ms);
						framenumber=tokens.nextToken();System.out.println(framenumber);
						direction=tokens.nextToken();System.out.println("direction"+direction);

						event=tokens.nextToken();System.out.println("eventi "+event);


						latitude=nf.parse(tokens.nextToken()).floatValue();System.out.println("latitude"+latitude);
						longitude=nf.parse(tokens.nextToken()).floatValue();System.out.println("longitude"+longitude);
						speed=nf.parse(tokens.nextToken()).intValue();System.out.println("speed"+speed);
						arfcn00=nf.parse(tokens.nextToken()).intValue();System.out.println("arfcn00"+arfcn00);
						arfcn00dbm=nf.parse(tokens.nextToken()).intValue();System.out.println(arfcn00dbm);
						arfcn01=nf.parse(tokens.nextToken()).intValue();
						arfcn01dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn02=nf.parse(tokens.nextToken()).intValue();
						arfcn02dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn03=nf.parse(tokens.nextToken()).intValue();
						arfcn03dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn04=nf.parse(tokens.nextToken()).intValue();
						arfcn04dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn05=nf.parse(tokens.nextToken()).intValue();
						arfcn05dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn06=nf.parse(tokens.nextToken()).intValue();
						arfcn06dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn07=nf.parse(tokens.nextToken()).intValue();
						arfcn07dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn08=nf.parse(tokens.nextToken()).intValue();
						arfcn08dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn09=nf.parse(tokens.nextToken()).intValue();
						arfcn09dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn10=nf.parse(tokens.nextToken()).intValue();
						arfcn10dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn11=nf.parse(tokens.nextToken()).intValue();
						arfcn11dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn12=nf.parse(tokens.nextToken()).intValue();
						arfcn12dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn13=nf.parse(tokens.nextToken()).intValue();
						arfcn13dbm=nf.parse(tokens.nextToken()).intValue();
						arfcn14=nf.parse(tokens.nextToken()).intValue();
						arfcn14dbm=nf.parse(tokens.nextToken()).intValue();*/


						//System.out.println(musterid+"\t"+zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm+"\t");

						// Para insertar valores nodos al final arfcn debe ser igual a 512
						// Esto para que el dato sea confiable
						if(latitude!=0 && longitude!=0 && arfcn00==512) 
							{
								// En el objeto Originalliste de tipo verketteteliste, se insertan
								// los nuevos valores de del archivo leido
								// System.out.println("LLegue aqui"); // Bandera
								Originalliste.insertend( zeit, latitude,  longitude,  speed, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,segmentdimension);
							}

						counter++;							//Sumo 1 a contador
						System.out.println(counter+"\t"+zeit+"\n"); //Imprimo contador, hora brinco siguiente linea
		
						// Lee una línea de texto hasta que encuentra un carácter de salto de línea (\n) y retorno de carro (\r).
						aline=bf.readLine();

				}while(aline!=null);	// Realizar mientras aline sea diferente de nulo
										// Si la nueva linea (aline) es nula sale del ciclo
			}
			
		// En caso de que no pueda ser leido el archivo se imprime error.	
		else
			{
				// Se imprime ERROR
				System.out.println("**************** ERROR ****************");
				return;
			}

		// Se pregunta si se desea procesar otro archivo
		anotherfile=Keyin.inInt("Do you want to process another file? [1] yes [2] no: \t");

		}while(anotherfile!=2);	// Realizar mientras anotherfile sea diferente de 2
								// Si anotherfile=2 sale del ciclo (no procesa otro archivo)

			// El objeto Originalliste de clase verketteliste invoca método deletefirst()
			Originalliste.deletefirst();
			// El objeto Originalliste de clase verketteliste invoca método CalculateAVG()
			Originalliste.CalculateAVG();
			/////Originalliste.ausgabedatei(outputpath,locationname);
			Originalliste.maxaverage();
			//Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber);

			System.out.println("LEA: "+ counter+"\n");
			/////System.out.println("MusterID Last "+ musterid+"\n");
			int segments = Originalliste.numberofsegments();
			System.out.println("Segments "+segments +"\n");


			//apply hypothesis test

			//randomselection(Originalliste,max);
			//Originalliste.ausgabedatei3();



	}

	public static void randomselection(verketteteliste Liste, int maxrandom)
	{
		int originalsegments;
		originalsegments=Liste.numberofsegments();
		int segmentstodelete=originalsegments-maxrandom;
		int newsegmentnumber;
		int counter = 1;
		int randomizer;
		Random r = new Random();
		while (counter<=segmentstodelete)
			{
				newsegmentnumber = Liste.numberofsegments()-1;
				if (newsegmentnumber==0) {newsegmentnumber=newsegmentnumber+1;}

				randomizer =(r.nextInt(newsegmentnumber));
				Liste.deleterandom(randomizer);

				counter++;
			}
	}

	public static void hypothesislesen(conf confliste) throws java.io.IOException, java.text.ParseException
	{
		// lokale variablen

		String initialtext;
		int samplesneeded;
		float coveragepercentage, confidencelevel, normalfactor, errormargin;
		String aline;

		// Initialisiere Eingabedatei
		// Lee Archivo de Configuracion Inicial
		File lesedatei = new File("HPTC.txt");

		if( lesedatei.exists()==true && lesedatei.canRead()==true)
			{
				BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
				aline = bf.readLine();
				do //while(aline!=null)
					{
						// Erzeuge Token
						StringTokenizer tokens = new StringTokenizer(aline,",");
						//System.out.println(aline);

						//Erzeuge ein NumberFormatter Objekt
						NumberFormat nf = NumberFormat.getInstance();

						//Lies die Daten im String und Speichere sie im variablen

						initialtext=tokens.nextToken();
						coveragepercentage=nf.parse(tokens.nextToken()).floatValue();
						confidencelevel=nf.parse(tokens.nextToken()).floatValue();
						normalfactor=nf.parse(tokens.nextToken()).floatValue();
						errormargin=nf.parse(tokens.nextToken()).floatValue();
						samplesneeded=nf.parse(tokens.nextToken()).intValue();
						confliste.insertend(initialtext, coveragepercentage,  confidencelevel,  normalfactor, errormargin, samplesneeded);
						aline=bf.readLine();
					}while(aline!=null);
			}
		else
			{
				System.out.println("**************** ERROR ****************");
				return;
			}
	}


		// Method to process the whole data****************

		public static void blockedfilelesenmethod2(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
		{
			// lokale variablen
			int counter =0;
			String dateiname, dummy;
			int anotherfile;

			String zeit;
			int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27;
			double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm,arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm,arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm,arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
			String aline;
			double latitude,longitude;
			String ms, framenumber, direction,event;
			File lesedatei;

			do
			{

			dateiname=Keyin.inString("Enter file name to process: \t");

			// Initialisiere Eingabedatei

			//File lesedatei = new File(dateiname);
			lesedatei=new File(dateiname);
					if(lesedatei.exists()==false)
					{


						do{
							dateiname=Keyin.inString("File does not exists. Enter file name to process: \t");

							// Initialisiere Eingabedatei

							lesedatei=new File(dateiname);

							}while (lesedatei.exists()!=true);
					}

			if( lesedatei.exists()==true && lesedatei.canRead()==true)
				{
					BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
					aline = bf.readLine();
					aline = bf.readLine();
					do
						{
								int maxarfcn;
								double maxarfcndbm;
								// Erzeuge Token
								StringTokenizer tokens = new StringTokenizer(aline,"\t",false);


								//Erzeuge ein NumberFormatter Objekt
								NumberFormat nf = NumberFormat.getInstance();
								//Erzeuge Leser
								String[] leser = aline.split("\t",-1);

								zeit=leser[0].trim();
								ms=leser[1].trim();
								framenumber=leser[2].trim();
								direction=leser[3].trim();

								event=leser[4].trim();System.out.println(event);

								dummy=leser[5];
									if(dummy.compareTo("")==0)
										{latitude=0;}
									else
										{
										latitude=nf.parse(leser[5]).floatValue();
										}

								dummy=leser[6];
									if(dummy.compareTo("")==0)
										{longitude=0;}
									else
										{
										longitude=nf.parse(leser[6].trim()).floatValue();
										}

								dummy=leser[7];
									if(dummy.compareTo("")==0)
										{speed=0;}
									else
										{
										speed=nf.parse(leser[7].trim()).intValue();
										}


								dummy=leser[8];
									if(dummy.compareTo("")==0)
										{ 	arfcn00=0;
											arfcn00=0;
											arfcn00dbm=0;
											arfcn01=0;
											arfcn01dbm=0;
											arfcn02=0;
											arfcn02dbm=0;
											arfcn03=0;
											arfcn03dbm=0;
											arfcn04=0;
											arfcn04dbm=0;
											arfcn05=0;
											arfcn05dbm=0;
											arfcn06=0;
											arfcn06dbm=0;
											arfcn07=0;
											arfcn07dbm=0;
											arfcn08=0;
											arfcn08dbm=0;
											arfcn09=0;
											arfcn09dbm=0;
											arfcn10=0;
											arfcn10dbm=0;
											arfcn11=0;
											arfcn11dbm=0;
											arfcn12=0;
											arfcn12dbm=0;
											arfcn13=0;
											arfcn13dbm=0;
											arfcn14=0;
											arfcn14dbm=0;
											arfcn15=0;
											arfcn15dbm=0;
											arfcn16=0;
											arfcn16dbm=0;
											arfcn17=0;
											arfcn17dbm=0;
											arfcn18=0;
											arfcn18dbm=0;
											arfcn19=0;
											arfcn19dbm=0;
											arfcn20=0;
											arfcn20dbm=0;
											arfcn21=0;
											arfcn21dbm=0;
											arfcn22=0;
											arfcn22dbm=0;
											arfcn23=0;
											arfcn23dbm=0;
											arfcn24=0;
											arfcn24dbm=0;
											arfcn25=0;
											arfcn25dbm=0;
											arfcn26=0;
											arfcn26dbm=0;
											arfcn27=0;
											arfcn27dbm=0;
										}
									else
										{
											arfcn00=nf.parse(leser[8].trim()).intValue();
											arfcn00dbm=nf.parse(leser[9].trim()).intValue();
											arfcn01=nf.parse(leser[10].trim()).intValue();
											arfcn01dbm=nf.parse(leser[11].trim()).intValue();
											arfcn02=nf.parse(leser[12].trim()).intValue();
											arfcn02dbm=nf.parse(leser[13].trim()).intValue();
											arfcn03=nf.parse(leser[14].trim()).intValue();
											arfcn03dbm=nf.parse(leser[15].trim()).intValue();
											arfcn04=nf.parse(leser[16].trim()).intValue();
											arfcn04dbm=nf.parse(leser[17].trim()).intValue();
											arfcn05=nf.parse(leser[18].trim()).intValue();
											arfcn05dbm=nf.parse(leser[19].trim()).intValue();
											arfcn06=nf.parse(leser[20].trim()).intValue();
											arfcn06dbm=nf.parse(leser[21].trim()).intValue();
											arfcn07=nf.parse(leser[22].trim()).intValue();
											arfcn07dbm=nf.parse(leser[23].trim()).intValue();
											arfcn08=nf.parse(leser[24].trim()).intValue();
											arfcn08dbm=nf.parse(leser[25].trim()).intValue();
											arfcn09=nf.parse(leser[26].trim()).intValue();
											arfcn09dbm=nf.parse(leser[27].trim()).intValue();
											arfcn10=nf.parse(leser[28].trim()).intValue();
											arfcn10dbm=nf.parse(leser[29].trim()).intValue();
											arfcn11=nf.parse(leser[30].trim()).intValue();
											arfcn11dbm=nf.parse(leser[31].trim()).intValue();
											arfcn12=nf.parse(leser[32].trim()).intValue();
											arfcn12dbm=nf.parse(leser[33].trim()).intValue();
											arfcn13=nf.parse(leser[34].trim()).intValue();
											arfcn13dbm=nf.parse(leser[35].trim()).intValue();
											arfcn14=nf.parse(leser[36].trim()).intValue();
											arfcn14dbm=nf.parse(leser[37].trim()).intValue();
											arfcn15=nf.parse(leser[38].trim()).intValue();
											arfcn15dbm=nf.parse(leser[39].trim()).intValue();
											arfcn16=nf.parse(leser[40].trim()).intValue();
											arfcn16dbm=nf.parse(leser[41].trim()).intValue();
											arfcn17=nf.parse(leser[42].trim()).intValue();
											arfcn17dbm=nf.parse(leser[43].trim()).intValue();
											arfcn18=nf.parse(leser[44].trim()).intValue();
											arfcn18dbm=nf.parse(leser[45].trim()).intValue();
											arfcn19=nf.parse(leser[46].trim()).intValue();
											arfcn19dbm=nf.parse(leser[47].trim()).intValue();
											arfcn20=nf.parse(leser[48].trim()).intValue();
											arfcn20dbm=nf.parse(leser[49].trim()).intValue();
											arfcn21=nf.parse(leser[50].trim()).intValue();
											arfcn21dbm=nf.parse(leser[51].trim()).intValue();
											arfcn22=nf.parse(leser[52].trim()).intValue();
											arfcn22dbm=nf.parse(leser[53].trim()).intValue();
											arfcn23=nf.parse(leser[54].trim()).intValue();
											arfcn23dbm=nf.parse(leser[55].trim()).intValue();
											arfcn24=nf.parse(leser[56].trim()).intValue();
											arfcn24dbm=nf.parse(leser[57].trim()).intValue();
											arfcn25=nf.parse(leser[58].trim()).intValue();
											arfcn25dbm=nf.parse(leser[59].trim()).intValue();
											arfcn26=nf.parse(leser[60].trim()).intValue();
											arfcn26dbm=nf.parse(leser[61].trim()).intValue();
											arfcn27=nf.parse(leser[62].trim()).intValue();
											arfcn27dbm=nf.parse(leser[63].trim()).intValue();
											dummy=leser[64].trim();
								}

							// Select Maximum
							// Obtengo el maximo
							maxarfcn=arfcn00;
							maxarfcndbm=arfcn00dbm;
							if(arfcn01dbm>maxarfcndbm)
							{
								maxarfcn=arfcn01;
								maxarfcndbm=arfcn01dbm;
							}
							if(arfcn02dbm>maxarfcndbm)
							{
								maxarfcn=arfcn02;
								maxarfcndbm=arfcn02dbm;
							}
							if(arfcn03dbm>maxarfcndbm)
							{
								maxarfcn=arfcn03;
								maxarfcndbm=arfcn03dbm;
							}
							if(arfcn04dbm>maxarfcndbm)
							{
								maxarfcn=arfcn04;
								maxarfcndbm=arfcn04dbm;
							}
							if(arfcn05dbm>maxarfcndbm)
							{
								maxarfcn=arfcn05;
								maxarfcndbm=arfcn05dbm;
							}
							if(arfcn06dbm>maxarfcndbm)
							{
								maxarfcn=arfcn06;
								maxarfcndbm=arfcn06dbm;
							}
							if(arfcn07dbm>maxarfcndbm)
							{
								maxarfcn=arfcn07;
								maxarfcndbm=arfcn07dbm;
							}
							if(arfcn08dbm>maxarfcndbm)
							{
								maxarfcn=arfcn08;
								maxarfcndbm=arfcn08dbm;
							}
							if(arfcn09dbm>maxarfcndbm)
							{
								maxarfcn=arfcn09;
								maxarfcndbm=arfcn09dbm;
							}
							if(arfcn10dbm>maxarfcndbm)
							{
								maxarfcn=arfcn10;
								maxarfcndbm=arfcn10dbm;
							}
							if(arfcn11dbm>maxarfcndbm)
							{
								maxarfcn=arfcn11;
								maxarfcndbm=arfcn11dbm;
							}
							if(arfcn12dbm>maxarfcndbm)
							{
								maxarfcn=arfcn12;
								maxarfcndbm=arfcn12dbm;
							}
							if(arfcn13dbm>maxarfcndbm)
							{
								maxarfcn=arfcn13;
								maxarfcndbm=arfcn13dbm;
							}
							if(arfcn14dbm>maxarfcndbm)
							{
								maxarfcn=arfcn14;
								maxarfcndbm=arfcn14dbm;
							}
							if(arfcn15dbm>maxarfcndbm)
							{
								maxarfcn=arfcn15;
								maxarfcndbm=arfcn15dbm;
							}
							if(arfcn16dbm>maxarfcndbm)
							{
								maxarfcn=arfcn16;
								maxarfcndbm=arfcn16dbm;
							}
							if(arfcn17dbm>maxarfcndbm)
							{
								maxarfcn=arfcn17;
								maxarfcndbm=arfcn17dbm;
							}
							if(arfcn18dbm>maxarfcndbm)
							{
								maxarfcn=arfcn18;
								maxarfcndbm=arfcn18dbm;
							}
							if(arfcn19dbm>maxarfcndbm)
							{
								maxarfcn=arfcn19;
								maxarfcndbm=arfcn19dbm;
							}
							if(arfcn20dbm>maxarfcndbm)
							{
								maxarfcn=arfcn20;
								maxarfcndbm=arfcn20dbm;
							}
							if(arfcn21dbm>maxarfcndbm)
							{
								maxarfcn=arfcn21;
								maxarfcndbm=arfcn21dbm;
							}
							if(arfcn22dbm>maxarfcndbm)
							{
								maxarfcn=arfcn22;
								maxarfcndbm=arfcn22dbm;
							}
							if(arfcn23dbm>maxarfcndbm)
							{
								maxarfcn=arfcn23;
								maxarfcndbm=arfcn23dbm;
							}
							if(arfcn24dbm>maxarfcndbm)
							{
								maxarfcn=arfcn24;
								maxarfcndbm=arfcn24dbm;
							}
							if(arfcn25dbm>maxarfcndbm)
							{
								maxarfcn=arfcn25;
								maxarfcndbm=arfcn25dbm;
							}
							if(arfcn26dbm>maxarfcndbm)
							{
								maxarfcn=arfcn26;
								maxarfcndbm=arfcn26dbm;
							}
							if(arfcn27dbm>maxarfcndbm)
							{
								maxarfcn=arfcn27;
								maxarfcndbm=arfcn27dbm;
							}

							//END SELECT MAXIMUM

							//System.out.println(zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm+"\t");

							if(latitude!=0 && longitude!=0 && arfcn00==666)
								{
									Originalliste.insertend( zeit, latitude,  longitude,  speed, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm, segmentdimension);
								}

							counter++;
							System.out.println(counter+"hola"+zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm+"\n");

							aline=bf.readLine();

						}while(aline!=null);
				}
			else
				{
					System.out.println("**************** ERROR ****************");
					return;
				}


			anotherfile=Keyin.inInt("Do you want to process another file? [1] yes [2] no: \t");

			}while(anotherfile!=2);

				Originalliste.deletefirst();
				Originalliste.CalculateAVG();
				//Originalliste.ausgabedatei(outputpath,locationname);
				Originalliste.maxaverage();
				Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber);

				System.out.println("GELESEN "+ counter+"\n");
				//System.out.println("MusterID Last "+ musterid+"\n");
				int segments = Originalliste.numberofsegments();
				System.out.println("Segments "+segments +"\n");


				//apply hypothesis test

				//randomselection(Originalliste,max);
				//Originalliste.ausgabedatei3();



	}



}

