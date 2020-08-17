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



public class ECATPadapt
{
	public static void main (String[] args) throws java.io.IOException,java.text.ParseException
	{

		int errormargincorrection;
		int segmentdimension=10;
		float errormargin;
		int segmentspassed, segmentsfailed, totalsegments;
		float percentagepassed,percentagefailed,sumpercentages;

		String locationname, locationnumber, outputpath, logfilename, date, analysisengineer, fieldengineer, icepersonnel, department;
		int thresholdselection, ercmethod;
		int morefiles;
		int threshold=0;

		System.out.println("Hugo Navas Aparicio Java Program for ERICSSON\nLast Update 2010-09-28 by Jose Daniel Rodriguez S.");
		verketteteliste Originalliste = new verketteteliste();Originalliste.insertelement("ECTAP",0,0,null,null);
		conf confliste = new conf();confliste.insertelement("",0,0,0,0,0);
		hypothesislesen(confliste);
		confliste.deletefirst();
		confliste.output();

		locationname=Keyin.inString("Please provide Location Name: \t");
		locationnumber=Keyin.inString("Please provide Location Number: \t");
		thresholdselection=Keyin.inInt("Please provide Test Area [1]RAT [2]Location [3]Highway: \t");
		System.out.println("Please full path for the output files: \t");
		outputpath="c:\\aaaa"; //Keyin.inString("Note> instead of '\\' write'\\\\': \t");
		date=""; //Keyin.inString("Enter date of drive test (YYYY/MM/DD): \t");
		analysisengineer=Keyin.inString("Name of Ericsson Consultant Engineer: \t");
		department="NPOC";//Keyin.inString("Department: \t");
		fieldengineer="";//Keyin.inString("Name of Ericsson Field Engineer: \t");
		icepersonnel="";//Keyin.inString("Name of ICE Representant: \t");
		ercmethod=1;//Keyin.inInt("Provide Analysis Method [1] ERCR1 [2]ERCR2: \t");
		morefiles=1;//Keyin.inInt("Amount of files [1]Only One [2]More files: \t");
		logfilename="";//Keyin.inString("Name of Logfile(s) separated by ';' : \t");

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

				blockedfilelesenadapted(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;



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

	//Aquí el análisis es llevado a cabo
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


		// Aquí está el archivo de salida se crea en HTML
		// Campos de datos
		String delimiter ="\t";
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		String outputfilename = fullpath+"\\"+locationnumber+"_"+locationname+"_FINAL REPORT.html";


		// Dar formato de archivo de salida
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{
					fos.println("<html>");fos.print(delimiter);
					fos.println("<head>");fos.print(delimiter);
					fos.println("<H1>Ericsson Costa Rica - Pruebas de Aceptación de Cobertura</H1>");fos.print(delimiter);
					fos.println("</head>");fos.print(delimiter);
					fos.println("<HR width=95%>");fos.print(delimiter);
					fos.println("<body>");fos.print(delimiter);
					fos.println("<P>País:		Costa Rica<br>");fos.print(delimiter);
					fos.println("Cliente:		ICE<br>");fos.print(delimiter);
					fos.println("Tecnología:	GSM<br>");fos.print(delimiter);
					fos.println("Proyecto:	ICE-GSM600k</P>");fos.print(delimiter);
					fos.println("<P><B>Datos de la Prueba</B></P>");fos.print(delimiter);

					fos.print("<P>Nombre de la localidad:");fos.print(delimiter);fos.print(locationname);
					fos.print("<br>Número de la localidad:");fos.print(delimiter);fos.print(locationnumber);
					fos.print("<br>Nivel de intensidad de señal requerido:");fos.print(delimiter);fos.print(threshold);fos.print(" dBm");
					fos.print("<br>Nombre de los archivos del Logfile:");fos.print(delimiter);fos.print(logfilename);
					fos.print("<br>Personal de Ericsson:");fos.print(delimiter);fos.print(fieldengineer);
					fos.print("<br>Personal del ICE:");fos.print(delimiter);fos.print(icepersonnel);


					fos.println("</P><P><B>Resultado</B></P>");fos.print(delimiter);
					fos.print("<P>Consultor de Ericsson:");fos.print(delimiter);fos.print(analysisengineer);
					fos.print("<br>Departamento:");fos.print(delimiter);fos.print(department);
					//fos.print("<br>Método de Análisis:ERCR-");fos.print(delimiter);fos.print(ercmethod);
					fos.print("<br>Tamaño del Segmento (m):");fos.print(delimiter);fos.print(segmentdimension);
					fos.print("<br>Fecha:");fos.print(delimiter);fos.print(date);
					fos.println("</P> ");fos.print(delimiter);

					fos.print("<P>Número de Segmentos total en la prueba ");fos.print(delimiter);fos.print(segments);fos.print("");
					fos.print("<br>Márgen de Error adaptado ");fos.print(delimiter);fos.print(errormargin);fos.print("%");
					fos.print("<br>Número de Segmentos necesarios y seleccionados para la prueba de hypotesis (en función del márgen de error): ");fos.print(delimiter);fos.print(errormargincorrection);fos.print("");
					fos.print("<br>Número de Segmentos exitosos:");fos.print(delimiter);fos.print(segmentspassed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagepassed+"%");
					fos.print("<br>Número de Segmentos fallidos:");fos.print(delimiter);fos.print(segmentsfailed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagefailed+"%");

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
				System.out.println("Error al crear el archivo de salida");
			}
		System.out.println("*************************************");
		fos.close();
	}

	// Metodos****************************************
	// Method to process the whole data****************

	public static void blockedfilelesenadapted(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
	{
		// Variables locales
		int counter =0;
		String dateiname;
		int anotherfile;

		String dummy;
		int segmentnumber, samples, maxarfcn,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27;
		double totalroute,maxarfcndbm;
		float arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm,arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm,arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm,arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
		String aline;
		double latitude,longitude;

		File lesedatei;

		do
		{

		dateiname=Keyin.inString("Enter file name to process: \t");

		// Inicializar el archivo de entrada

		//File lesedatei = new File(dateiname);
		lesedatei=new File(dateiname);
		if(lesedatei.exists()==false)
		{


			do{
				dateiname=Keyin.inString("File does not exists. Enter file name to process: \t");

				// Inicializar el archivo de entrada
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

						// Crear Token
						StringTokenizer tokens = new StringTokenizer(aline,"\t",false);

						//Crear un objeto NumberFormat
						NumberFormat nf = NumberFormat.getInstance();
						//Crear Lector (Leser)
						String[] leser = aline.split("\t",-1);

						segmentnumber=nf.parse(leser[0].trim()).intValue();
						maxarfcn=nf.parse(leser[1].trim()).intValue();
						maxarfcndbm=nf.parse(leser[2].trim()).floatValue();
						samples=nf.parse(leser[3].trim()).intValue();
						latitude=nf.parse(leser[4]).floatValue();
						longitude=nf.parse(leser[5].trim()).floatValue();
						speed=nf.parse(leser[6].trim()).intValue();
						totalroute=nf.parse(leser[7].trim()).floatValue();
						arfcn00=nf.parse(leser[8].trim()).intValue();
						arfcn00dbm=nf.parse(leser[9].trim()).floatValue();
						arfcn01=nf.parse(leser[10].trim()).intValue();
						arfcn01dbm=nf.parse(leser[11].trim()).floatValue();
						arfcn02=nf.parse(leser[12].trim()).intValue();
						arfcn02dbm=nf.parse(leser[13].trim()).floatValue();
						arfcn03=nf.parse(leser[14].trim()).intValue();
						arfcn03dbm=nf.parse(leser[15].trim()).floatValue();
						arfcn04=nf.parse(leser[16].trim()).intValue();
						arfcn04dbm=nf.parse(leser[17].trim()).floatValue();
						arfcn05=nf.parse(leser[18].trim()).intValue();
						arfcn05dbm=nf.parse(leser[19].trim()).floatValue();
						arfcn06=nf.parse(leser[20].trim()).intValue();
						arfcn06dbm=nf.parse(leser[21].trim()).floatValue();
						arfcn07=nf.parse(leser[22].trim()).intValue();
						arfcn07dbm=nf.parse(leser[23].trim()).floatValue();
						arfcn08=nf.parse(leser[24].trim()).intValue();
						arfcn08dbm=nf.parse(leser[25].trim()).floatValue();
						arfcn09=nf.parse(leser[26].trim()).intValue();
						arfcn09dbm=nf.parse(leser[27].trim()).floatValue();
						arfcn10=nf.parse(leser[28].trim()).intValue();
						arfcn10dbm=nf.parse(leser[29].trim()).floatValue();
						arfcn11=nf.parse(leser[30].trim()).intValue();
						arfcn11dbm=nf.parse(leser[31].trim()).floatValue();
						arfcn12=nf.parse(leser[32].trim()).intValue();
						arfcn12dbm=nf.parse(leser[33].trim()).floatValue();
						arfcn13=nf.parse(leser[34].trim()).intValue();
						arfcn13dbm=nf.parse(leser[35].trim()).floatValue();
						arfcn14=nf.parse(leser[36].trim()).intValue();
						arfcn14dbm=nf.parse(leser[37].trim()).floatValue();
						arfcn15=nf.parse(leser[38].trim()).intValue();
						arfcn15dbm=nf.parse(leser[39].trim()).floatValue();
						arfcn16=nf.parse(leser[40].trim()).intValue();
						arfcn16dbm=nf.parse(leser[41].trim()).floatValue();
						arfcn17=nf.parse(leser[42].trim()).intValue();
						arfcn17dbm=nf.parse(leser[43].trim()).floatValue();
						arfcn18=nf.parse(leser[44].trim()).intValue();
						arfcn18dbm=nf.parse(leser[45].trim()).floatValue();
						arfcn19=nf.parse(leser[46].trim()).intValue();
						arfcn19dbm=nf.parse(leser[47].trim()).floatValue();
						arfcn20=nf.parse(leser[48].trim()).intValue();
						arfcn20dbm=nf.parse(leser[49].trim()).floatValue();
						arfcn21=nf.parse(leser[50].trim()).intValue();
						arfcn21dbm=nf.parse(leser[51].trim()).floatValue();
						arfcn22=nf.parse(leser[52].trim()).intValue();
						arfcn22dbm=nf.parse(leser[53].trim()).floatValue();
						arfcn23=nf.parse(leser[54].trim()).intValue();
						arfcn23dbm=nf.parse(leser[55].trim()).floatValue();
						arfcn24=nf.parse(leser[56].trim()).intValue();
						arfcn24dbm=nf.parse(leser[57].trim()).floatValue();
						arfcn25=nf.parse(leser[58].trim()).intValue();
						arfcn25dbm=nf.parse(leser[59].trim()).floatValue();
						arfcn26=nf.parse(leser[60].trim()).intValue();
						arfcn26dbm=nf.parse(leser[61].trim()).floatValue();
						arfcn27=nf.parse(leser[62].trim()).intValue();
						arfcn27dbm=nf.parse(leser[63].trim()).floatValue();
						//dummy=leser[39].trim();System.out.println("wwww");
						
						// Si el dato existe en la tabla arfcn00, latitud y longitud tienen
						// valores distintos de nulo y cero
						if(latitude!=0 && longitude!=0 && arfcn00!=0)
							{
								Originalliste.insertendadapted( segmentnumber,maxarfcn,maxarfcndbm, latitude,  longitude,  speed,totalroute, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,segmentdimension);
							}
						//System.out.println(musterid+"\n");
						counter++;
						System.out.println(counter+"\n");
						aline=bf.readLine();

				}while(aline!=null);
			}
		else
			{
				System.out.println("**************** ERROR ****************");
				return;
			}

		// Aqui se pregunta si se quiere procesar otro archivo: se encuentra deshabilitado
		anotherfile=2;//Keyin.inInt("Do you want to process another file? [1] yes [2] no: \t");

		}while(anotherfile!=2);

			Originalliste.deletefirst();
			//Originalliste.CalculateAVG();
			//Originalliste.ausgabedatei(outputpath,locationname);
			Originalliste.maxaverage();
			Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber);

			System.out.println("Leyendo "+ counter+"\n");
			//System.out.println("MusterID Last "+ musterid+"\n");
			int segments = Originalliste.numberofsegments();
			System.out.println("Segments "+segments +"\n");
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
		// Variables Locales

		String initialtext;
		int samplesneeded;
		float coveragepercentage, confidencelevel, normalfactor, errormargin;
		String aline;

		// Inicializar el archivo de entrada
		File lesedatei = new File("HPTC.txt");

		if( lesedatei.exists()==true && lesedatei.canRead()==true)
			{
				BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
				aline = bf.readLine();
				do //while(aline!=null)
					{
						// Crear Token
						StringTokenizer tokens = new StringTokenizer(aline,",");
						//System.out.println(aline);

						//Crear un objeto NumberFormat
						NumberFormat nf = NumberFormat.getInstance();

						//Leer los datos de la cadena y lo almacena en la variable

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


}
