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
		
		String stop;
		String locationname, locationnumber, outputpath, logfilename, date, analysisengineer, fieldengineer, icepersonnel, department;
		int thresholdselection, ercmethod;
		int morefiles;
		int threshold=0;

		// Se imprime version de la herramienta y creadores, maximo y minimo de frecuencias 
		//que deben poseer los Logs para que la herramienta corra.
		System.out.print("\n Version 1.0 max:39 frec min:15 frec");
		System.out.print("\n\n Hugo Navas Aparicio Java Program for ERICSSON");
		System.out.print("\n Last Update 2010-10-04 by Jose Daniel Rodriguez S.\n\n\tFecha Actual: ");
		
		//------------------------- Obtengo la fecha del sistema -------------------------
		Calendar fecha= Calendar.getInstance(); 
		int dia = fecha.get(Calendar.DAY_OF_MONTH);	// Obtengo el dia del mes del sistema
		int mes = fecha.get(Calendar.MONTH);		// Obtengo el mes del sistema
		mes=mes+1; // Correccion del mes
		int anno = fecha.get(Calendar.YEAR); // Obtengo el año del sistema
		int hora = fecha.get(Calendar.HOUR); // Obtengo la hora del sistema
		int minuto = fecha.get(Calendar.MINUTE); // Obtengo el minuto del sistema
		//--------------------------------------------------------------------------------
		
		// Imprimo fecha actual en pantalla
		System.out.println(dia+"-"+mes+"-"+anno+"  "+hora+":"+minuto+"\n"); 

		verketteteliste Originalliste = new verketteteliste();Originalliste.insertelement("ECTAP",0,0,null,null);
		conf confliste = new conf();confliste.insertelement("",0,0,0,0,0);
		hypothesislesen(confliste);
		confliste.deletefirst();
		confliste.output();

		locationname=Keyin.inString("Please provide Location Name: \t");
		locationnumber=Keyin.inString("Please provide Location Number: \t");
		thresholdselection=Keyin.inInt("Please provide Test Area [1]RAT [2]Location [3]Highway: \t");
		//System.out.println("Please full path for the output files: \t");
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
	// Se imprimen el numero de segmentos
	System.out.println("\n\tNumero de Segmentos: "+segments); 
	
	// En la variable errormargincorrection obtengo el numero de muestras requeridas
	errormargincorrection = confliste.errormarginadaption(segments);
	// En la variable errormargin obtengo el margen de error requerido para confiabilidad
	// de las muestras 
	errormargin = confliste.margintouse(errormargincorrection)*100;

	//System.out.println("Samples needed = "+errormargincorrection);
	System.out.println("\n\tMargen de error a utilizar = "+errormargin+"%");
	System.out.println("\n\tMuestras requeridas para confiabilidad = "+errormargincorrection+"\n");
	System.out.println("**************************************************************************");
	stop=Keyin.inString("\n\nPresione enter para continuar...\n");
	
	// Se selecciona valores aleatorios, Originalliste es la lista concatenada
	// El errormargincorrection, es el numero de muestras minimo para confiabilidad 
	// de datos
	randomselection(Originalliste,errormargincorrection);	
		
	// Creacion del archivo con valores aleatorios, creacion del archivo 
	// *******_Random_Segments.txt que contiene valores aleatorios
	Originalliste.ausgabedateiRNDM(outputpath, locationname, locationnumber);

	//stop=Keyin.inString("Parada \t");
	
	// Segmentos que pasan la prueba segun el umbral definido (threshold)	
	segmentspassed=Originalliste.passedsegments(threshold); 
		
	// Segmentos que fallan la prueba segun el umbral definido (threshold)	
	segmentsfailed=Originalliste.failedsegments(threshold);

	// Numero total de segmentos
	totalsegments=segmentspassed+segmentsfailed; 

	// Porcentaje de segmentos pasados 
	percentagepassed=((float)segmentspassed/(float)totalsegments)*100;
	
	// Porcentaje de segmentos fallidos	
	percentagefailed=((float)segmentsfailed/(float)totalsegments)*100;
	
	// Suma de porcentajes. Fallados mas pasados = al 100%
	sumpercentages=percentagepassed+percentagefailed; // Debe ser igual al 100%
	
	// El analisis que se realiza es el siguiente
	double limittocomply = 0.95-(errormargin/100); // Limite a cumplir
	
	// Porcentaje de limite a cumplir
	double limittocomply100=limittocomply*100;
	
	// Se crea la variable de tipo cadena de caracteres evaluacion,
	// en esta se coloca el mensaje final de la evaluacion
	String evaluation;

	// Si el porcentaje que pasa es mayor igual al limite de cumpliemiento
	if(percentagepassed>=limittocomply100)	// Se obtiene el mensaje de la evaluacion final.
		{
			evaluation="Prueba exitosa - Se cumple con el requerimiento";
		}
	else
		{
			evaluation="Prueba fallida - No se cumple con el requerimiento";
		}

// Se imprime la cantidad de segmentos y el porcentaje de segmentos que cumple con el requerimiento
		System.out.println("Passed: "+"\t"+segmentspassed+"\t"+percentagepassed+"%");		
// Se imprime la cantidad de segmentos y el porcentaje de segmentos fallidos
		System.out.println("Failed: "+"\t"+segmentsfailed+"\t"+percentagefailed+"%");
// Se imprime la cantidad total de segmentos y el 100% (la suma de porcentajes)		
		System.out.println("Total Segments: "+"\t"+totalsegments+"\t"+sumpercentages+"%");
// La cantidad del porcentaje de segmentos que debe cumplir con el requerimiento
		System.out.println("% to comply with:\t"+limittocomply100+"%");
// Se imprime la evaluacion, es decir si cumplio o no con el requerimiento
		System.out.println(evaluation);

		// El reporte final (archivo de salida) se crea en HTML 


		// Aquí está el archivo de salida se crea en HTML
		// Campos de Datos
		String delimiter ="\t";
		// Ruta completa: c:\\aa\\numero de locacion.espacio en blanco. Nombre de locacion
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		// Nombre y ruta del archivo que se crea
		String outputfilename = fullpath+"\\"+locationnumber+"_"+locationname+"_FINAL REPORT.html";
		

		// Dar formato de archivo de salida
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		// Escritura
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{
					fos.println("<html>");fos.print(delimiter);
					fos.println("<head>");fos.print(delimiter);
					fos.println("<center><H1>Ericsson Costa Rica - Pruebas de Aceptaci&oacute;n de Cobertura</H1></center>");fos.print(delimiter);
					fos.println("</head>");fos.print(delimiter);
					fos.println("<HR width=95%>");fos.print(delimiter);
					fos.println("<body>");fos.print(delimiter);
					fos.println("<P>Pa&iacute;s:		Costa Rica<br>");fos.print(delimiter);
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
		System.out.println("**************************************************************************");
		fos.close();
	}

	// Metodos****************************************
	// Method to process the whole data****************

	public static void blockedfilelesenadapted(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
	{
		// Variables locales
		int counter =0;
		String dateiname;
		// Variable para obtener la cantidad de columnas validas de la primer fila
		// del archivo de TEMS, que son los nombres de las columnas
		int numero_columnas;
				
		int anotherfile;

		String dummy;
		int segmentnumber, samples, maxarfcn,speed;
		int arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09;
		int arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19;
		int arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27,arfcn28,arfcn29;
		int arfcn30,arfcn31,arfcn32,arfcn33,arfcn34,arfcn35,arfcn36,arfcn37,arfcn38,arfcn39;
		int arfcn40,arfcn41,arfcn42,arfcn43,arfcn44,arfcn45,arfcn46,arfcn47,arfcn48,arfcn49;
		//,arfcn50;
				
		double totalroute,maxarfcndbm;
		
		float arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm;
		float arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm;
		float arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm; 
		float arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
		float arfcn28dbm,arfcn29dbm,arfcn30dbm,arfcn31dbm,arfcn32dbm,arfcn33dbm,arfcn34dbm;
		float arfcn35dbm,arfcn36dbm,arfcn37dbm,arfcn38dbm,arfcn39dbm,arfcn40dbm,arfcn41dbm;
		float arfcn42dbm,arfcn43dbm,arfcn44dbm,arfcn45dbm,arfcn46dbm,arfcn47dbm,arfcn48dbm;
		float arfcn49dbm; //arfcn50dbm;
			
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

		// En caso de que el archivo exista y pueda ser leido
		if( lesedatei.exists()==true && lesedatei.canRead()==true)
			{
				// Hago lectura de lesedatei en caso de existir y poderse leer
				// Crea el objeto bf de la lectura realizada
				BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
				
				// Lee una línea de texto hasta que encuentra un carácter de salto de línea
				// (\n) y retorno de carro (\r). 
				// La primer fila del archivo es invalida ya que posee los nombres de las 
				// columnas
				aline = bf.readLine();		

				// Lee una línea de texto hasta que encuentra un carácter de salto de línea
				// (\n) y retorno de carro (\r).
				// La segunda fila del archivo ya es válida
				aline = bf.readLine();
				do
				{

						// Crear Token
						StringTokenizer tokens = new StringTokenizer(aline,"\t",false);

						//Crear un objeto NumberFormat
						NumberFormat nf = NumberFormat.getInstance();
						//Crear Lector (Leser)
						String[] leser = aline.split("\t",-1);

						int longitud_lector=leser.length;


						// En caso de que la columna arfcn00dbm posea "" dato invalido 
						// (cadena de caracteres vacio) 
						// entonces leo la siguiente linea del archivo
						while (leser[9].compareTo("")==0) 
							{
							// counter++;
							// Ubico puntero en la siguiente linea
							aline = bf.readLine();
							// Leo la siguiente linea (valor de arfcndbm00 con "")
							leser = aline.split("\t",-1);								
							}

						//**********************************************************************//
						// NOTA :                                                               //
						// En caso de que el archivo generado por TEMS tenga mayor cantidad de  //
						// columnas en la primer fila (nombres de los valores de las columnas)  //
						// que valores medidos de frecuencia, entonces implica que la longitud  //
						// real del lector es menor, por ello se le resta 2, para eliminar la   //
						// ultima columna arfcnN y arfcnNdbm, si la siguiente sucede lo mismo,se//
						// vuelve a resta 2 hasta obtener la longitud real del Lector de fila   //
						//**********************************************************************//
						
						while (leser[longitud_lector-1].compareTo("")==0&&leser[8].compareTo("")!=0)
							{
								// Correccion del numero de columnas en caso de que el archivo
								// se corrumpa y no aparezcan los ultimos valores
								longitud_lector=longitud_lector-2;					
							}

						numero_columnas=longitud_lector;
				
					//	System.out.println("\n"+numero_columnas+"\n");
				//		String prueba=Keyin.inString("Prueba");
						
						segmentnumber=nf.parse(leser[0].trim()).intValue();
						maxarfcn=nf.parse(leser[1].trim()).intValue();
						maxarfcndbm=nf.parse(leser[2].trim()).floatValue();
						samples=nf.parse(leser[3].trim()).intValue();
						latitude=nf.parse(leser[4]).floatValue();
						longitude=nf.parse(leser[5].trim()).floatValue();
						speed=nf.parse(leser[6].trim()).intValue();
						totalroute=nf.parse(leser[7].trim()).floatValue();
						
						if(leser[7].compareTo("")==0)
								{ 	
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
									arfcn28=0;
									arfcn28dbm=0;
									arfcn29=0;
									arfcn29dbm=0;
									arfcn30=0;
									arfcn30dbm=0;
									arfcn31=0;
									arfcn31dbm=0;
									arfcn32=0;
									arfcn32dbm=0;
									arfcn33=0;
									arfcn33dbm=0;
									arfcn34=0;
									arfcn34dbm=0;
									arfcn35=0;
									arfcn35dbm=0;
									arfcn36=0;
									arfcn36dbm=0;
									arfcn37=0;
									arfcn37dbm=0;
									arfcn38=0;
									arfcn38dbm=0;
									arfcn39=0;
									arfcn39dbm=0;
									arfcn40=0;
									arfcn40dbm=0;
									arfcn41=0;
									arfcn41dbm=0;
									arfcn42=0;
									arfcn42dbm=0;
									arfcn43=0;
									arfcn43dbm=0;
									arfcn44=0;
									arfcn44dbm=0;
									arfcn45=0;
									arfcn45dbm=0;
									arfcn46=0;
									arfcn46dbm=0;
									arfcn47=0;
									arfcn47dbm=0;
									arfcn48=0;
									arfcn48dbm=0;
									arfcn49=0;
									arfcn49dbm=0;									
									//arfcn50=0;
									//arfcn50dbm=0;									
								}
							else 
							{
	
								// Reviso si es un archivo de 1 frecuencia
								if (longitud_lector>=10)
									{
									arfcn00=nf.parse(leser[8].trim()).intValue();
									arfcn00dbm=nf.parse(leser[9].trim()).intValue();						
									}	
								
								else
									{
									arfcn00=0;
									arfcn00dbm=-150;
									System.out.println("\n\tError: Archivo sin informacion valida\n");
									}

								// Reviso si es un archivo de 2 frecuencias
								if (longitud_lector>=12)
									{
									arfcn01=nf.parse(leser[10].trim()).intValue();
									arfcn01dbm=nf.parse(leser[11].trim()).intValue();						
									}	
								
								else
									{
									arfcn01=0;
									arfcn01dbm=-150;
									}

								// Reviso si es un archivo de 3 frecuencias
								if (longitud_lector>=14)
									{
									arfcn02=nf.parse(leser[12].trim()).intValue();
									arfcn02dbm=nf.parse(leser[13].trim()).intValue();						
									}	
								
								else
									{
									arfcn02=0;
									arfcn02dbm=-150;
									}

								// Reviso si es un archivo de 4 frecuencias
								if (longitud_lector>=16)
									{
									arfcn03=nf.parse(leser[14].trim()).intValue();
									arfcn03dbm=nf.parse(leser[15].trim()).intValue();						
									}	
								
								else
									{
									arfcn03=0;
									arfcn03dbm=-150;
									}

								// Reviso si es un archivo de 5 frecuencias
								if (longitud_lector>=18)
									{
									arfcn04=nf.parse(leser[16].trim()).intValue();
									arfcn04dbm=nf.parse(leser[17].trim()).intValue();						
									}	
								
								else
									{
									arfcn04=0;
									arfcn04dbm=-150;
									}

								// Reviso si es un archivo de 6 frecuencias
								if (longitud_lector>=20)
									{
									arfcn05=nf.parse(leser[18].trim()).intValue();
									arfcn05dbm=nf.parse(leser[19].trim()).intValue();						
									}	
								
								else
									{
									arfcn05=0;
									arfcn05dbm=-150;
									}

								// Reviso si es un archivo de 7 frecuencias
								if (longitud_lector>=22)
									{
									arfcn06=nf.parse(leser[20].trim()).intValue();
									arfcn06dbm=nf.parse(leser[21].trim()).intValue();						
									}	
								
								else
									{
									arfcn06=0;
									arfcn06dbm=-150;
									}

								// Reviso si es un archivo de 8 frecuencias
								if (longitud_lector>=24)
									{
									arfcn07=nf.parse(leser[22].trim()).intValue();
									arfcn07dbm=nf.parse(leser[23].trim()).intValue();						
									}	
								
								else
									{
									arfcn07=0;
									arfcn07dbm=-150;
									}

								// Reviso si es un archivo de 9 frecuencias
								if (longitud_lector>=26)
									{
									arfcn08=nf.parse(leser[24].trim()).intValue();
									arfcn08dbm=nf.parse(leser[25].trim()).intValue();						
									}	
								
								else
									{
									arfcn08=0;
									arfcn08dbm=-150;
									}

								// Reviso si es un archivo de 10 frecuencias
								if (longitud_lector>=28)
									{
									arfcn09=nf.parse(leser[26].trim()).intValue();
									arfcn09dbm=nf.parse(leser[27].trim()).intValue();						
									}	
								
								else
									{
									arfcn09=0;
									arfcn09dbm=-150;
									}

								// Reviso si es un archivo de 11 frecuencias
								if (longitud_lector>=30)
									{
									arfcn10=nf.parse(leser[28].trim()).intValue();
									arfcn10dbm=nf.parse(leser[29].trim()).intValue();						
									}	
								
								else
									{
									arfcn10=0;
									arfcn10dbm=-150;
									}

								// Reviso si es un archivo de 12 frecuencias
								if (longitud_lector>=32)
									{
									arfcn11=nf.parse(leser[30].trim()).intValue();
									arfcn11dbm=nf.parse(leser[31].trim()).intValue();						
									}	
								
								else
									{
									arfcn11=0;
									arfcn11dbm=-150;
									}

								// Reviso si es un archivo de 13 frecuencias
								if (longitud_lector>=34)
									{
									arfcn12=nf.parse(leser[32].trim()).intValue();
									arfcn12dbm=nf.parse(leser[33].trim()).intValue();						
									}	
								
								else
									{
									arfcn12=0;
									arfcn12dbm=-150;
									}

								// Reviso si es un archivo de 14 frecuencias
								if (longitud_lector>=36)
									{
									arfcn13=nf.parse(leser[34].trim()).intValue();
									arfcn13dbm=nf.parse(leser[35].trim()).intValue();						
									}	
								
								else
									{
									arfcn13=0;
									arfcn13dbm=-150;
									}

								// Reviso si es un archivo de 15 frecuencias
								if (longitud_lector>=38)
									{
									arfcn14=nf.parse(leser[36].trim()).intValue();
									arfcn14dbm=nf.parse(leser[37].trim()).intValue();						
									}	
								
								else
									{
									arfcn14=0;
									arfcn14dbm=-150;
									}

								// Reviso si es un archivo de 16 frecuencias
								if (longitud_lector>=40)
									{
									arfcn15=nf.parse(leser[38].trim()).intValue();
									arfcn15dbm=nf.parse(leser[39].trim()).intValue();							
									}	
								
								else
									{
									arfcn15=0;
									arfcn15dbm=-150;
									}

								// Reviso si es un archivo de 17 frecuencias
								if (longitud_lector>=42)
									{
									arfcn16=nf.parse(leser[40].trim()).intValue();
									arfcn16dbm=nf.parse(leser[41].trim()).intValue();						
									}	
								
								else
									{
									arfcn16=0;
									arfcn16dbm=-150;
									}

								// Reviso si es un archivo de 18 frecuencias
								if (longitud_lector>=44)
									{
									arfcn17=nf.parse(leser[42].trim()).intValue();
									arfcn17dbm=nf.parse(leser[43].trim()).intValue();							
									}	
								
								else
									{
									arfcn17=0;
									arfcn17dbm=-150;
									}

								// Reviso si es un archivo de 19 frecuencias
								if (longitud_lector>=46)
									{
									arfcn18=nf.parse(leser[44].trim()).intValue();
									arfcn18dbm=nf.parse(leser[45].trim()).intValue();						
									}	
								
								else
									{
									arfcn18=0;
									arfcn18dbm=-150;
									}

								// Reviso si es un archivo de 20 frecuencias
								if (longitud_lector>=48)
									{
									arfcn19=nf.parse(leser[46].trim()).intValue();
									arfcn19dbm=nf.parse(leser[47].trim()).intValue();						
									}	
								
								else
									{
									arfcn19=0;
									arfcn19dbm=-150;
									}

								// Reviso si es un archivo de 21 frecuencias
								if (longitud_lector>=50)
									{
									arfcn20=nf.parse(leser[48].trim()).intValue();
									arfcn20dbm=nf.parse(leser[49].trim()).intValue();	
									}	
								
								else
									{
									arfcn20=0;
									arfcn20dbm=-150;
									}

								// Reviso si es un archivo de 22 frecuencias
								if (longitud_lector>=52)
									{
									arfcn21=nf.parse(leser[50].trim()).intValue();
									arfcn21dbm=nf.parse(leser[51].trim()).intValue();							
									}	
								
								else
									{
									arfcn21=0;
									arfcn21dbm=-150;
									}

								// Reviso si es un archivo de 23 frecuencias
								if (longitud_lector>=54)
									{
									arfcn22=nf.parse(leser[52].trim()).intValue();
									arfcn22dbm=nf.parse(leser[53].trim()).intValue();						
									}	
								
								else
									{
									arfcn22=0;
									arfcn22dbm=-150;
									}

								// Reviso si es un archivo de 24 frecuencias
								if (longitud_lector>=56)
									{
									arfcn23=nf.parse(leser[54].trim()).intValue();
									arfcn23dbm=nf.parse(leser[55].trim()).intValue();						
									}	
								
								else
									{
									arfcn23=0;
									arfcn23dbm=-150;
									}

								// Reviso si es un archivo de 25 frecuencias
								if (longitud_lector>=58)
									{
									arfcn24=nf.parse(leser[56].trim()).intValue();
									arfcn24dbm=nf.parse(leser[57].trim()).intValue();						
									}	
								
								else
									{
									arfcn24=0;
									arfcn24dbm=-150;
									}

								// Reviso si es un archivo de 26 frecuencias
								if (longitud_lector>=60)
									{
									arfcn25=nf.parse(leser[58].trim()).intValue();
									arfcn25dbm=nf.parse(leser[59].trim()).intValue();						
									}	
								
								else
									{
									arfcn25=0;
									arfcn25dbm=-150;
									}
									
								// Reviso si es un archivo de 27 frecuencias
								if (longitud_lector>=62)
									{
									arfcn26=nf.parse(leser[60].trim()).intValue();
									arfcn26dbm=nf.parse(leser[61].trim()).intValue();						
									}	
								
								else
									{
									arfcn26=0;
									arfcn26dbm=-150;
									}

								// Reviso si es un archivo de 28 frecuencias
								if (longitud_lector>=64)
									{
									arfcn27=nf.parse(leser[62].trim()).intValue();
									arfcn27dbm=nf.parse(leser[63].trim()).intValue();		
									}	
								
								else
									{
									arfcn27=0;
									arfcn27dbm=-150;
									}

								// Reviso si es un archivo de 29 frecuencias
								if (longitud_lector>=66)
									{
									arfcn28=nf.parse(leser[64].trim()).intValue();
									arfcn28dbm=nf.parse(leser[65].trim()).intValue();					
									}	
								
								else
									{
									arfcn28=0;
									arfcn28dbm=-150;
									}

								// Reviso si es un archivo de 30 frecuencias
								if (longitud_lector>=68)
									{
									arfcn29=nf.parse(leser[66].trim()).intValue();
									arfcn29dbm=nf.parse(leser[67].trim()).intValue();							
									}	
								
								else
									{
									arfcn29=0;
									arfcn29dbm=-150;
									}

								// Reviso si es un archivo de 31 frecuencias
								if (longitud_lector>=70)
									{
									arfcn30=nf.parse(leser[68].trim()).intValue();
									arfcn30dbm=nf.parse(leser[69].trim()).intValue();						
									}	
								
								else
									{
									arfcn30=0;
									arfcn30dbm=-150;
									}

								// Reviso si es un archivo de 32 frecuencias
								if (longitud_lector>=72)
									{
									arfcn31=nf.parse(leser[70].trim()).intValue();
									arfcn31dbm=nf.parse(leser[71].trim()).intValue();						
									}	
								
								else
									{
									arfcn31=0;
									arfcn31dbm=-150;
									}

								// Reviso si es un archivo de 33 frecuencias
								if (longitud_lector>=74)
									{
									arfcn32=nf.parse(leser[72].trim()).intValue();
									arfcn32dbm=nf.parse(leser[73].trim()).intValue();						
									}	
								
								else
									{
									arfcn32=0;
									arfcn32dbm=-150;																			
									}

								// Reviso si es un archivo de 34 frecuencias
								if (longitud_lector>=76)
									{
									arfcn33=nf.parse(leser[74].trim()).intValue();
									arfcn33dbm=nf.parse(leser[75].trim()).intValue();						
									}	
								
								else
									{
									arfcn33=0;
									arfcn33dbm=-150;																			
									}

								// Reviso si es un archivo de 35 frecuencias
								if (longitud_lector>=78)
									{
									arfcn34=nf.parse(leser[76].trim()).intValue();
									arfcn34dbm=nf.parse(leser[77].trim()).intValue();							
									}
								
								else
									{
									arfcn34=0;
									arfcn34dbm=-150;																			
									}
									
								// Reviso si es un archivo de 36 frecuencias
								if (longitud_lector>=80)
									{
									arfcn35=nf.parse(leser[78].trim()).intValue();
									arfcn35dbm=nf.parse(leser[79].trim()).intValue();						
									}	
								
								else
									{
									arfcn35=0;
									arfcn35dbm=-150;																			
									}
									
								// Reviso si es un archivo de 37 frecuencias
								if (longitud_lector>=82)
									{
									arfcn36=nf.parse(leser[80].trim()).intValue();
									arfcn36dbm=nf.parse(leser[81].trim()).intValue();						
									}	
								
								else
									{
									arfcn36=0;
									arfcn36dbm=-150;																			
									}

								// Reviso si es un archivo de 38 frecuencias
								if (longitud_lector>=84)
									{
									arfcn37=nf.parse(leser[82].trim()).intValue();
									arfcn37dbm=nf.parse(leser[83].trim()).intValue();						
									}	
								
								else
									{
									arfcn37=0;
									arfcn37dbm=-150;																			
									}

								// Reviso si es un archivo de 39 frecuencias
								if (longitud_lector>=86)
									{
									arfcn38=nf.parse(leser[84].trim()).intValue();
									arfcn38dbm=nf.parse(leser[85].trim()).intValue();						
									}	
								
								else
									{
									arfcn38=0;
									arfcn38dbm=-150;																			
									}

								// Reviso si es un archivo de 40 frecuencias
								if (longitud_lector>=88)
									{
									arfcn39=nf.parse(leser[86].trim()).intValue();
									arfcn39dbm=nf.parse(leser[87].trim()).intValue();													
									dummy=leser[88].trim();							
									}	
								
								else
									{
									arfcn39=0;
									arfcn39dbm=-150;																			
									}

								// Reviso si es un archivo de 41 frecuencias
								if (longitud_lector>=90)
									{
									arfcn40=nf.parse(leser[88].trim()).intValue();
									arfcn40dbm=nf.parse(leser[89].trim()).intValue();
									dummy=leser[90].trim();							
									}	
								
								else
									{
									arfcn40=0;
									arfcn40dbm=-150;
									}

								// Reviso si es un archivo de 42 frecuencias
								if (longitud_lector>=92)
									{
									arfcn41=nf.parse(leser[90].trim()).intValue();
									arfcn41dbm=nf.parse(leser[91].trim()).intValue();													
									dummy=leser[92].trim();							
									}	
								
								else
									{
									arfcn41=0;
									arfcn41dbm=-150;
									}

								// Reviso si es un archivo de 43 frecuencias
								if (longitud_lector>=94)
									{
									arfcn42=nf.parse(leser[92].trim()).intValue();
									arfcn42dbm=nf.parse(leser[93].trim()).intValue();													
									dummy=leser[94].trim();							
									}	
								
								else
									{
									arfcn42=0;
									arfcn42dbm=-150;																			
									}

								// Reviso si es un archivo de 44 frecuencias
								if (longitud_lector>=96)
									{
									arfcn43=nf.parse(leser[94].trim()).intValue();
									arfcn43dbm=nf.parse(leser[95].trim()).intValue();													
									dummy=leser[96].trim();							
									}	
								
								else
									{
									arfcn43=0;
									arfcn43dbm=-150;																			
									}

								// Reviso si es un archivo de 45 frecuencias
								if (longitud_lector>=98)
									{
									arfcn44=nf.parse(leser[96].trim()).intValue();
									arfcn44dbm=nf.parse(leser[97].trim()).intValue();													
									dummy=leser[98].trim();							
									}
								
								else
									{
									arfcn44=0;
									arfcn44dbm=-150;																			
									}
									
								// Reviso si es un archivo de 46 frecuencias
								if (longitud_lector>=100)
									{
									arfcn45=nf.parse(leser[98].trim()).intValue();
									arfcn45dbm=nf.parse(leser[99].trim()).intValue();													
									dummy=leser[100].trim();							
									}	
								
								else
									{
									arfcn45=0;
									arfcn45dbm=-150;																			
									}
									
								// Reviso si es un archivo de 47 frecuencias
								if (longitud_lector>=102)
									{
									arfcn46=nf.parse(leser[100].trim()).intValue();
									arfcn46dbm=nf.parse(leser[101].trim()).intValue();													
									dummy=leser[102].trim();							
									}	
								
								else
									{
									arfcn46=0;
									arfcn46dbm=-150;																			
									}

								// Reviso si es un archivo de 48 frecuencias
								if (longitud_lector>=104)
									{
									arfcn47=nf.parse(leser[102].trim()).intValue();
									arfcn47dbm=nf.parse(leser[103].trim()).intValue();													
									dummy=leser[104].trim();							
									}	
								
								else
									{
									arfcn47=0;
									arfcn47dbm=-150;																			
									}

								// Reviso si es un archivo de 39 frecuencias
								if (longitud_lector>=106)
									{
									arfcn48=nf.parse(leser[104].trim()).intValue();
									arfcn48dbm=nf.parse(leser[105].trim()).intValue();													
									dummy=leser[106].trim();							
									}	
								
								else
									{
									arfcn48=0;
									arfcn48dbm=-150;																			
									}
									
								// Reviso si es un archivo de 40 frecuencias
								if (longitud_lector>=108)
									{
									arfcn49=nf.parse(leser[1066].trim()).intValue();
									arfcn49dbm=nf.parse(leser[107].trim()).intValue();													
									dummy=leser[108].trim();							
									}	
								
								else
									{
									arfcn49=0;
									arfcn49dbm=-150;																			
									}
						
					//---------------------------------------------------------------//
					// NOTA  : En caso de modificar este software se debe agregar    //
					// valores arfcnxx y arfcnxxdbm al final de cada comparacion     //
					// else if (longitud_lector>=XX)                                 //
					//---------------------------------------------------------------//
							}
				
				// Obtengo el valor mayor
				maxarfcn=arfcn00;
				maxarfcndbm=arfcn00dbm;
				 
				// En caso de que el valor de Potencia de la Frecuencia 1
				// sea mayor que el valor de la Potencia de la Frecuencia 0 
				if(arfcn01dbm>maxarfcndbm) 
				{
					// Se define la frecuencia 1 como la que posee maximo 
					// valor de Potencia de RX			
					maxarfcn=arfcn01;
					maxarfcndbm=arfcn01dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 2
				// sea mayor que el valor de la Potencia de la Frecuencia 1 		
				if(arfcn02dbm>maxarfcndbm)
				{
					// Se define la frecuencia 2 como la que posee maximo 
					// valor de Potencia de RX	
					maxarfcn=arfcn02;
					maxarfcndbm=arfcn02dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 3
				// sea mayor que el valor de la Potencia de la Frecuencia 2 		
				if(arfcn03dbm>maxarfcndbm)
				{
					// Se define la frecuencia 3 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn03;
					maxarfcndbm=arfcn03dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 4
				// sea mayor que el valor de la Potencia de la Frecuencia 3 		
				if(arfcn04dbm>maxarfcndbm)
				{
					// Se define la frecuencia 4 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn04;
					maxarfcndbm=arfcn04dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 5
				// sea mayor que el valor de la Potencia de la Frecuencia 4 		
				if(arfcn05dbm>maxarfcndbm)
				{
					// Se define la frecuencia 5 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn05;
					maxarfcndbm=arfcn05dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 6
				// sea mayor que el valor de la Potencia de la Frecuencia 5 		
				if(arfcn06dbm>maxarfcndbm)
				{
					// Se define la frecuencia 6 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn06;
					maxarfcndbm=arfcn06dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 7
				// sea mayor que el valor de la Potencia de la Frecuencia 6 		
				if(arfcn07dbm>maxarfcndbm)
				{
					// Se define la frecuencia 7 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn07;
					maxarfcndbm=arfcn07dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 8
				// sea mayor que el valor de la Potencia de la Frecuencia 7 		
				if(arfcn08dbm>maxarfcndbm)
				{
					// Se define la frecuencia 8 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn08;
					maxarfcndbm=arfcn08dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 9
				// sea mayor que el valor de la Potencia de la Frecuencia 8 		
				if(arfcn09dbm>maxarfcndbm)
				{
					// Se define la frecuencia 9 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn09;
					maxarfcndbm=arfcn09dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 10
				// sea mayor que el valor de la Potencia de la Frecuencia 9 		
				if(arfcn10dbm>maxarfcndbm)
				{
					// Se define la frecuencia 10 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn10;
					maxarfcndbm=arfcn10dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 11
				// sea mayor que el valor de la Potencia de la Frecuencia 10 		
				if(arfcn11dbm>maxarfcndbm)
				{
					// Se define la frecuencia 11 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn11;
					maxarfcndbm=arfcn11dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 12
				// sea mayor que el valor de la Potencia de la Frecuencia 11 		
				if(arfcn12dbm>maxarfcndbm)
				{
					// Se define la frecuencia 12 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn12;
					maxarfcndbm=arfcn12dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 13
				// sea mayor que el valor de la Potencia de la Frecuencia 12 		
				if(arfcn13dbm>maxarfcndbm)
				{
					// Se define la frecuencia 13 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn13;
					maxarfcndbm=arfcn13dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 14
				// sea mayor que el valor de la Potencia de la Frecuencia 13 		
				if(arfcn14dbm>maxarfcndbm)
				{
					// Se define la frecuencia 14 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn14;
					maxarfcndbm=arfcn14dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 15
				// sea mayor que el valor de la Potencia de la Frecuencia 14 		
				if(arfcn15dbm>maxarfcndbm)
				{
					// Se define la frecuencia 15 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn15;
					maxarfcndbm=arfcn15dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 16
				// sea mayor que el valor de la Potencia de la Frecuencia 15 		
				if(arfcn16dbm>maxarfcndbm)
				{
					// Se define la frecuencia 16 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn16;
					maxarfcndbm=arfcn16dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 17
				// sea mayor que el valor de la Potencia de la Frecuencia 16 		
				if(arfcn17dbm>maxarfcndbm)
				{
					// Se define la frecuencia 17 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn17;
					maxarfcndbm=arfcn17dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 18
				// sea mayor que el valor de la Potencia de la Frecuencia 17 		
				if(arfcn18dbm>maxarfcndbm)
				{
					// Se define la frecuencia 18 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn18;
					maxarfcndbm=arfcn18dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 19
				// sea mayor que el valor de la Potencia de la Frecuencia 18 		
				if(arfcn19dbm>maxarfcndbm)
				{
					// Se define la frecuencia 19 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn19;
					maxarfcndbm=arfcn19dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 20
				// sea mayor que el valor de la Potencia de la Frecuencia 19 		
				if(arfcn20dbm>maxarfcndbm)
				{
					// Se define la frecuencia 20 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn20;
					maxarfcndbm=arfcn20dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 21
				// sea mayor que el valor de la Potencia de la Frecuencia 20 		
				if(arfcn21dbm>maxarfcndbm)
				{
					// Se define la frecuencia 21 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn21;
					maxarfcndbm=arfcn21dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 22
				// sea mayor que el valor de la Potencia de la Frecuencia 21 		
				if(arfcn22dbm>maxarfcndbm)
				{
					// Se define la frecuencia 22 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn22;
					maxarfcndbm=arfcn22dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 23
				// sea mayor que el valor de la Potencia de la Frecuencia 22 		
				if(arfcn23dbm>maxarfcndbm)
				{
					// Se define la frecuencia 23 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn23;
					maxarfcndbm=arfcn23dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 24
				// sea mayor que el valor de la Potencia de la Frecuencia 23 		
				if(arfcn24dbm>maxarfcndbm)
				{
					// Se define la frecuencia 24 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn24;
					maxarfcndbm=arfcn24dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 25
				// sea mayor que el valor de la Potencia de la Frecuencia 24 		
				if(arfcn25dbm>maxarfcndbm)
				{
					// Se define la frecuencia 25 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn25;
					maxarfcndbm=arfcn25dbm;
				}
				
				// En caso de que el valor de Potencia de la Frecuencia 26
				// sea mayor que el valor de la Potencia de la Frecuencia 25 		
				if(arfcn26dbm>maxarfcndbm)
				{
					// Se define la frecuencia 26 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn26;
					maxarfcndbm=arfcn26dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 27
				// sea mayor que el valor de la Potencia de la Frecuencia 26 		
				if(arfcn27dbm>maxarfcndbm)
				{
					// Se define la frecuencia 27 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn27;
					maxarfcndbm=arfcn27dbm;
				}

				// En caso de que el valor de Potencia de la Frecuencia 28
				// sea mayor que el valor de la Potencia de la Frecuencia 27 		
				if(arfcn28dbm>maxarfcndbm)
				{
					// Se define la frecuencia 28 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn28;
					maxarfcndbm=arfcn28dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 29
				// sea mayor que el valor de la Potencia de la Frecuencia 28 		
				if(arfcn29dbm>maxarfcndbm)
				{
					// Se define la frecuencia 29 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn29;
					maxarfcndbm=arfcn29dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 30
				// sea mayor que el valor de la Potencia de la Frecuencia 29 		
				if(arfcn30dbm>maxarfcndbm)
				{
					// Se define la frecuencia 30 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn30;
					maxarfcndbm=arfcn30dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 31
				// sea mayor que el valor de la Potencia de la Frecuencia 30 		
				if(arfcn31dbm>maxarfcndbm)
				{
					// Se define la frecuencia 31 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn31;
					maxarfcndbm=arfcn31dbm;
				}

				// En caso de que el valor de Potencia de la Frecuencia 32
				// sea mayor que el valor de la Potencia de la Frecuencia 31 		
				if(arfcn32dbm>maxarfcndbm)
				{
					// Se define la frecuencia 32 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn32;
					maxarfcndbm=arfcn32dbm;
				}

				// En caso de que el valor de Potencia de la Frecuencia 33
				// sea mayor que el valor de la Potencia de la Frecuencia 32 		
				if(arfcn33dbm>maxarfcndbm)
				{
					// Se define la frecuencia 33 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn33;
					maxarfcndbm=arfcn33dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 34
				// sea mayor que el valor de la Potencia de la Frecuencia 33 		
				if(arfcn34dbm>maxarfcndbm)
				{
					// Se define la frecuencia 34 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn34;
					maxarfcndbm=arfcn34dbm;
				}

				// En caso de que el valor de Potencia de la Frecuencia 35
				// sea mayor que el valor de la Potencia de la Frecuencia 34 		
				if(arfcn35dbm>maxarfcndbm)
				{
					// Se define la frecuencia 35 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn35;
					maxarfcndbm=arfcn35dbm;
				}

				// En caso de que el valor de Potencia de la Frecuencia 36
				// sea mayor que el valor de la Potencia de la Frecuencia 35 		
				if(arfcn36dbm>maxarfcndbm)
				{
					// Se define la frecuencia 36 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn36;
					maxarfcndbm=arfcn36dbm;
				}	

				// En caso de que el valor de Potencia de la Frecuencia 37
				// sea mayor que el valor de la Potencia de la Frecuencia 36 		
				if(arfcn37dbm>maxarfcndbm)
				{
					// Se define la frecuencia 37 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn37;
					maxarfcndbm=arfcn37dbm;
				}
		
				// En caso de que el valor de Potencia de la Frecuencia 38
				// sea mayor que el valor de la Potencia de la Frecuencia 37 		
				if(arfcn38dbm>maxarfcndbm)
				{
					// Se define la frecuencia 38 como la que posee maximo 
					// valor de Potencia de RX				
					maxarfcn=arfcn38;
					maxarfcndbm=arfcn38dbm;
				}		

			//************************************************************************//
			// NOTA : En caso de querer realizar una ampliacion de numero de          //
			// de frecuencias en este metodo se debe agregar mayor cantidad de        //
			// de comparaciones if(tracer1.getarfcnxxdbm()>maxarfcndbm)               //
			//************************************************************************// 
						
										
						// Si el dato existe en la tabla arfcn00, latitud y longitud tienen
						// valores distintos de nulo y cero
						if(latitude!=0 && longitude!=0 && arfcn00!=0)
							{
							Originalliste.insertendadapted( counter,maxarfcn,
							maxarfcndbm,latitude,longitude,speed,totalroute,
							arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm,
							arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm,
							arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm,
							arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm,
							arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm,
							arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm,
							arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm,
							arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm,
							arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm,
							arfcn27, arfcn27dbm, arfcn28, arfcn28dbm, arfcn29, arfcn29dbm,
							arfcn30, arfcn30dbm, arfcn31, arfcn31dbm, arfcn32, arfcn32dbm,
							arfcn33, arfcn33dbm, arfcn34, arfcn34dbm, arfcn35, arfcn35dbm,
							arfcn36, arfcn36dbm, arfcn37, arfcn37dbm, arfcn38, arfcn38dbm,
							arfcn39, arfcn39dbm, arfcn40, arfcn40dbm, arfcn41, arfcn41dbm,
							arfcn42, arfcn42dbm, arfcn43, arfcn43dbm, arfcn44, arfcn44dbm,
							arfcn45, arfcn45dbm, arfcn46, arfcn46dbm, arfcn47, arfcn47dbm,
							arfcn48, arfcn48dbm, arfcn49, arfcn49dbm, //arfcn50, arfcn50dbm,
							segmentdimension);
							}
						//System.out.println(musterid+"\n");
						System.out.println(counter+"\n");
						aline=bf.readLine();
						counter++;
						
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
			
			int conversor=0;
			Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber,
										  numero_columnas, conversor);

			//System.out.println("MusterID Last "+ musterid+"\n");
			//int segments = Originalliste.numberofsegments();
			System.out.println("\n"+"\t"+"Datos Leidos: "+ counter--);
			//System.out.println("Segments "+segments +"\n");
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
