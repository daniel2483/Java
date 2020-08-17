
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
		
		String stop;
		String locationname, locationnumber, outputpath, logfilename, date, analysisengineer, fieldengineer, icepersonnel, department;
		int thresholdselection, ercmethod;
		int morefiles;
		int threshold=0;

		System.out.print("\nHugo Navas Aparicio Java Program for ERICSSON \nLast Update 2010-09-28 by Jose Daniel Rodriguez S.\n\n\tFecha Actual: ");

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
		
		// Creo el objeto Originalliste que es de tipo verketteteliste
		verketteteliste Originalliste = new verketteteliste();
		
		// Se inicializa el primer elemento de la lista
		// insertelement posee los siguientes argumentos
		// (String zeit,double latitude, double longitude, rawsamplenode siguiente, 
		// rawsamplenode anterior)
		Originalliste.insertelement("ECTAP",0,0,null,null);

		// Creo el objeto confliste de tipo conf (conf es una clase definida dentro de hptest.java)		
		conf confliste = new conf();
		
		// Accedo al metodo insertelement de la clase conf y se inicializa los valores
		// de los argumentos en este caso son: String initialtext, float coveragepercentage,
		// float confidencelevel, float normalfactor, float errormargin e int samples
		confliste.insertelement("",0,0,0,0,0);
		//
		hypothesislesen(confliste);		// Lectura de Archivo de conf inicial
		confliste.deletefirst();
		confliste.output();

						// ######## Menu inicial ############//
						// En el promt se pide lo siguiente: //
						//  1. Pregunta por lugar del sitio  // 
						//       2. Pregunta por area        //  
						// 3. Nombre del Ingeniero Consultor //
						//       4. Nombre de archivo        //
						//     5. Nombre del archivo Log     //
						//###################################// 
		
		locationname=Keyin.inString("Please provide Location Name: \t");
		locationnumber=Keyin.inString("Please provide Location Number: \t");
		thresholdselection=Keyin.inInt("Please provide Test Area [1]RAT [2]Location [3]Highway: \t");
		//System.out.println("Please full path for the output files: \t");
		outputpath="c:\\aa";//Keyin.inString("Note> instead of '\\' write'\\\\': \t");
		//date="";//Keyin.inString("Enter date of drive test (YYYY/MM/DD): \t");
		analysisengineer=Keyin.inString("Name of Ericsson Engineer: \t");
		department=" NPOC ";//Keyin.inString("Department: \t");
		fieldengineer="";//Keyin.inString("Name of Ericsson Field Engineer: \t");
		icepersonnel="";//Keyin.inString("Name of ICE Representant: \t");
		ercmethod=1;// Se realizaran analisis mediante el metodo que define ericsson (ercmethod =1)//Keyin.inInt("Provide Analysis Method [1] ERCR1 [2]ERCR2: \t");
		morefiles=1;// Se define que solo se va a procesar un archivo a la vez//Keyin.inInt("Amount of files [1]Only One [2]More files: \t");
		logfilename=Keyin.inString("Name of Logfile: \t");

		//------------Aqui se selecciona el tipo de ruta y sus valores de umbral--------//
		switch(thresholdselection) // Seleccion de umbral
			{
				case 1:
					threshold=-70; //Umbral de RAT son -70dBm
					break;
				case 2:
					threshold=-85; //Umbral de Other Location son -85dBm
					break;
				case 3:
					threshold=-85; //Umbral de Highways son -85dBm
					break;
			}
		//------------------------------------------------------------------------------//


		if(ercmethod==1) //Este metodo es el que se ejecuta y es el que define Ericsson
			{
				blockedfilelesen(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;
			}
		else
			{
				if(ercmethod==2) // Este metodo no se ejecuta ya que es el establecido por el ICE
					{
						blockedfilelesenmethod2(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;
					}
			}


		int segments = Originalliste.numberofsegments();
		System.out.println("Segments "+segments +"\n"); // Se imprimen el numero de segmentos


		errormargincorrection = confliste.errormarginadaption(segments);
		errormargin = confliste.margintouse(errormargincorrection)*100;
		
		System.out.println("Samples needed = "+errormargincorrection);
		System.out.println("NEW Error Margin to use = "+errormargin+"%");
		System.out.println("Samples needed to achieve confidence level = "+errormargincorrection);

	//stop=Keyin.inString("Parada \t");

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
	// Se crea la variable de tipo cadena de caracteres evaluacion, en esta se coloca el mensaje final de la evaluacion
	String evaluation;

	// Si el porcentaje que pasa es mayor igual al limite de cumpliemiento
	if(percentagepassed>=limittocomply100) // Se obtiene el mensaje de la evaluacion final.
		{
			evaluation="Prueba exitosa - Se cumple con el requerimiento";
		}
	else
		{
			evaluation="Prueba fallida - No se cumple con el requerimiento";
		}

// Se imprime la cantidad de segmentos y el porcentaje de segmentos que cumple con el requerimiento
		System.out.println("Passed "+"\t"+segmentspassed+"\t"+percentagepassed+"%");		
// Se imprime la cantidad de segmentos y el porcentaje de segmentos fallidos
		System.out.println("Failed "+"\t"+segmentsfailed+"\t"+percentagefailed+"%");
// Se imprime la cantidad total de segmentos y el 100% (la suma de porcentajes)		
		System.out.println("Total Segments "+"\t"+totalsegments+"\t"+sumpercentages+"%");
// La cantidad del porcentaje de segmentos que debe cumplir con el requerimiento
		System.out.println("% to comply with:\t"+limittocomply100+"%");
// Se imprime la evaluacion, es decir si cumplio o no con el requerimiento
		System.out.println(evaluation);

		// El archivo de salida se crea en HTML 
		
		// Campos de Datos
		String delimiter ="\t";
		// Ruta completa: c:\\aa\\numero de locacion.espacio en blanco. Nombre de locacion
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		// Nombre y ruta del archivo que se crea
		String outputfilename = fullpath+"\\"+locationnumber+"_"+locationname+"_FINAL REPORT.html";
		
		
		// Dar formato al archivo de salida
		boolean success = new File(fullpath).mkdir();	// Se crea el archivo HTML
		File ausgabedatei = new File(outputfilename);   // Se crea el archivo de salida
		// Escritura
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true) // Si el archivo de salida existe y se puede escribir
			{
					fos.println("<html>");fos.print(delimiter);
					fos.println("<head>");fos.print(delimiter);
					fos.println("<center><H1>Ericsson Costa Rica - Pruebas de Aceptaci&oacute;n de Cobertura</H1></center>");fos.print(delimiter);
					fos.println("</head>");fos.print(delimiter);
					fos.println("<HR width=95%>");fos.print(delimiter);
					fos.println("<body>");fos.print(delimiter);
					fos.println("<P>Pa&iacute;s:		Costa Rica<br>");fos.print(delimiter);
					fos.println("Cliente:		ICE<br>");fos.print(delimiter);
					fos.println("Tecnolog&iacute;a:	GSM<br>");fos.print(delimiter);
					fos.println("Proyecto:	ICE-GSM600k</P>");fos.print(delimiter);
					fos.println("<P><B>Datos de la Prueba</B></P>");fos.print(delimiter);

					fos.print("<P>Nombre de la localidad:");fos.print(delimiter);fos.print(locationname);
					fos.print("<br>N&uacute;mero de la localidad:");fos.print(delimiter);fos.print(locationnumber);
					fos.print("<br>Nivel de intensidad de signal requerido:");fos.print(delimiter);fos.print(threshold);fos.print(" dBm");
					fos.print("<br>Nombre de los archivos del Logfile:");fos.print(delimiter);fos.print(logfilename);
					fos.print("<br>Personal de Ericsson:");fos.print(delimiter);fos.print(fieldengineer);
					fos.print("<br>Personal del ICE:");fos.print(delimiter);fos.print(icepersonnel);


					fos.println("</P><P><B>Resultado</B></P>");fos.print(delimiter);
					fos.print("<P>Consultor de Ericsson:");fos.print(delimiter);fos.print(analysisengineer);
					fos.print("<br>Departamento:");fos.print(delimiter);fos.print(department);
					fos.print("<br>M&eacute;todo de An&aacute;lisis:ERCR-");fos.print(delimiter);fos.print(ercmethod);
					fos.print("<br>Tama&ntilde;o del Segmento (m):");fos.print(delimiter);fos.print(segmentdimension);
					fos.print("<br>Fecha:");fos.print(delimiter);fos.print(fecha);
					fos.println("</P> ");fos.print(delimiter);

					fos.print("<P>N&uacute;mero de Segmentos total en la prueba ");fos.print(delimiter);fos.print(segments);fos.print("");
					fos.print("<br>Margen de Error adaptado ");fos.print(delimiter);fos.print(errormargin);fos.print("%");
					fos.print("<br>N&uacute;mero de Segmentos necesarios y seleccionados para la prueba de hypotesis (en funcion del margen de error): ");fos.print(delimiter);fos.print(errormargincorrection);fos.print("");
					fos.print("<br>N&uacute;mero de Segmentos exitosos:");fos.print(delimiter);fos.print(segmentspassed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagepassed+"%");
					fos.print("<br>N&uacute;mero de Segmentos fallidos:");fos.print(delimiter);fos.print(segmentsfailed);fos.print(delimiter);fos.print("Porcentaje\t"+delimiter);fos.print(percentagefailed+"%");

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
		else		// Si el archivo de salida esta dañado
			{
				System.out.println("Error al crear el archivo de salida");
			}
		System.out.println("*************************************");
		fos.close();

	}

	// Metodos****************************************
	// Metodo para procesar todos los datos****************

	public static void blockedfilelesen(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
	{
		// Variables locales
		int counter =0;		// Inicio contador
		String dateiname;	// Nombre de archivo
		int anotherfile;	// Otro archivo

		String zeit,dummy;
		int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27;
		double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm,arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm,arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm,arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
		String aline;
		double latitude,longitude; 
		String ms, framenumber, direction,event;
		File lesedatei; //Variable Leerarchivo es de tipo file, es decir es un archivo

		do
		{			
		// Espera nombre de archivo
		dateiname=Keyin.inString("Enter file name to process: \t");

		// Dar formato de archivo de entrada

		// File lesedatei = new File(dateiname); 
		// Hace Lectura de archivos  
		lesedatei=new File(dateiname); // lesedatei= lectura de archivo
		
		// En caso de que el archivo no exista escribir File do not exists y pregunta por otro file
		if(lesedatei.exists()==false)
		{

			do{
				dateiname=Keyin.inString("File does not exists. Enter file name to process: \t");

				// Inicializacion del archivo de entrada

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

						// La clase StringTokenizer divide cadena por tabulaciones
						StringTokenizer tokens = new StringTokenizer(aline,"\t",false);


						// Clase para dar formato a los numeros
						NumberFormat nf = NumberFormat.getInstance();
					
						//-------------------------------------------------------------//
						// NOTA: En caso de modificar este software se debe cambiar el // 
						//  limite del String[64] ya que se tendrian mayor cambiar de  //
						//  datos dentro del arreglo. Ej: En este caso llega hasta 28  //
						//frecuencias, si se quiere cambiar a 31 frecuencias el String //
						//            queda de la siguiente forma String[70]           //
						//-------------------------------------------------------------//
						
						// Separar aline (la linea o fila) cuando encuentra tabs 
						// y almacena los valores en el arreglo String [], 
						// mantiene empty strings del aline (-1)						
						String leser[] = new String[64]; // Lector posee un tamaño de 46
						
						//String[] 
						leser = aline.split("\t",-1);

						// Metodo trim() remueve espacios en blanco de un String al inicio y al final de esta
	
						// El valor del 1er valor del arreglo corresponde a la hora
						zeit=leser[0].trim(); // La primer del archivo columna es el tiempo
						
						// El valor del 2do valor del arreglo corresponde a la MSC
						ms=leser[1].trim(); // La segunda columna es el msc
						framenumber=leser[2].trim();// La tercera columna es el framenumber 
						direction=leser[3].trim(); // La cuarta columna es Direction

						event=leser[4].trim();System.out.println(event);// La quinta columna es Event

						// Obtengo el Lector de latitud
						dummy=leser[5];									// La sexta columna es Latitud
							if(dummy.compareTo("")==0)					// compara Latitud con espacio vacio
								{latitude=0;}
							else
								{
								latitude=nf.parse(leser[5]).floatValue(); // Convierte el valor leido de latitud que es un string a valor float 
								}

						// Obtengo el Lector de Longitud
						dummy=leser[6];									// La septima columnae es Longitud
							if(dummy.compareTo("")==0)					// compara Longitud con espacio vacio
								{longitude=0;}
							else
								{
								longitude=nf.parse(leser[6].trim()).floatValue();// Convierte el valor de longitud que es un string a valor float 
								}
								
						// Obtengo el Lector de Speed
						dummy=leser[7];				// La octava columna es Speed
							if(dummy.compareTo("")==0)
								{
								speed=0;
								}
							else
								{
								speed=nf.parse(leser[7].trim()).intValue(); // Convierte el valor de velocidad que es un string a entero
								}
						// System.out.println("LLegue aqui"); // Bandera
						// Obtengo el Lector de arfcn[0-27] y el arfcn[0-27]dbm
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

							// Lector de arfcn14 es leser[36]						
							// Verifico si Valor de arfcn14 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 15 frecuencias
							//
															
							else if (leser.length<=39)		//El archivo es de 15 frecuencias
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
									arfcn15=0;
									arfcn15dbm=-150;
									arfcn16=0;
									arfcn16dbm=-150;
									arfcn17=0;
									arfcn17dbm=-150;
									arfcn18=0;
									arfcn18dbm=-150;
									arfcn19=0;
									arfcn19dbm=-150;
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;
									dummy=leser[38].trim();									
								}

							// Lector de arfcn15 es leser[38]						
							// Verifico si Valor de arfcn15 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 16 frecuencias
							//
															
							else if (leser.length<=41) //El archivo es de 16 frecuencias
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
									arfcn16=0;
									arfcn16dbm=-150;
									arfcn17=0;
									arfcn17dbm=-150;
									arfcn18=0;
									arfcn18dbm=-150;
									arfcn19=0;
									arfcn19dbm=-150;
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=-150;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;
									dummy=leser[40].trim();																				
								}

							// Lector de arfcn16 es leser[40]						
							// Verifico si Valor de arfcn16 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 17 frecuencias
							//
															
							else if (leser.length<=43) //El archivo es de 17 frecuencias
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
									arfcn17=0;
									arfcn17dbm=-150;
									arfcn18=0;
									arfcn18dbm=-150;
									arfcn19=0;
									arfcn19dbm=-150;
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;
									dummy=leser[42].trim();																				
								}

							// Lector de arfcn17 es leser[42]						
							// Verifico si Valor de arfcn17 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 18 frecuencias
							//
															
							else if (leser.length<=45)	//El archivo es de 18 frecuencias
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
									arfcn18=0;
									arfcn18dbm=-150;
									arfcn18=0;
									arfcn18dbm=-150;
									arfcn19=0;
									arfcn19dbm=-150;
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;
									dummy=leser[44].trim();											
								}

							// Lector de arfcn18 es leser[44]						
							// Verifico si Valor de arfcn18 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 19 frecuencias
							//
															
							else if (leser.length<=47)	//El archivo es de 19 frecuencias
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
									arfcn19=0;
									arfcn19dbm=-150;
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;																												
									dummy=leser[46].trim();										
								}	

							// Lector de arfcn19  es leser[46]						
							// Verifico si Valor de arfcn19 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 20 frecuencias
							//
															
							else if (leser.length<=49)	//El archivo es de 20 frecuencias
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
									arfcn20=0;
									arfcn20dbm=-150;
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;	
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;																												
									dummy=leser[48].trim();																				
								}			
								
							// Lector de arfcn20 es leser[48]						
							// Verifico si Valor de arfcn20 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 21 frecuencias
							//
															
							else if (leser.length<=51)	//El archivo es de 21 frecuencias
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
									arfcn21=0;
									arfcn21dbm=-150;
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=0;
//									arfcn29=0;
//									arfcn29dbm=0;
//									arfcn30=0;
//									arfcn30dbm=0;
//									arfcn31=0;
//									arfcn31dbm=0;																												
									dummy=leser[50].trim();																				
								}	

								
							// Lector de arfcn21 es leser[50]					
							// Verifico si Valor de arfcn21 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 220 frecuencias
							//
															
							else if (leser.length<=53)	//El archivo es de 22 frecuencias
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
									arfcn22=0;
									arfcn22dbm=-150;
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;																		
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[52].trim();																					
								}

								
							// Lector de arfcn22 es leser[52]			
							// Verifico si Valor de arfcn22 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 23 frecuencias
							//
															
							else if (leser.length<=55)	//El archivo es de 23 frecuencias
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
									arfcn23=0;
									arfcn23dbm=-150;
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[54].trim();																				
								}

								
							// Lector de arfcn23 es leser[54]				
							// Verifico si Valor de arfcn23 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 24 frecuencias
							//
															
							else if (leser.length<=57)	//El archivo es de 24 frecuencias
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
									arfcn24=0;
									arfcn24dbm=-150;
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
								//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
								//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[56].trim();																					
								}

								
							// Lector de arfcn24 es leser[56]						
							// Verifico si Valor de arfcn24 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 25 frecuencias
							//
															
							else if (leser.length<=59)	//El archivo es de 25 frecuencias
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
									arfcn25=0;
									arfcn25dbm=-150;
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[58].trim();																				
								}

											
							// Lector de arfcn25 es leser[58]				
							// Verifico si Valor de arfcn25 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 26 frecuencias
							//
															
							else if (leser.length<=61)	//El archivo es de 26 frecuencias
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
									arfcn26=0;
									arfcn26dbm=-150;
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[61].trim();																			
								}
									
							// Lector de arfcn26 es leser[60]					
							// Verifico si Valor de arfcn26 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 27 frecuencias
							//
															
							else if (leser.length<=63)	//El archivo es de 27 frecuencias
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
									//Asigna valores de frecuencia 0 en caso de tener valor nulo
									arfcn27=0;
									//Asigna valores de potencia en caso de tener valor nulo
									arfcn27dbm=-150;	
//									arfcn28=0;
//									arfcn28dbm=-150;
//									arfcn29=0;
//									arfcn29dbm=-150;
//									arfcn30=0;
//									arfcn30dbm=-150;
//									arfcn31=0;
//									arfcn31dbm=-150;																												
									dummy=leser[62].trim();																
								}	
																
							// Lector de arfcn27 es leser[62]						
							// Verifico si Valor de arfcn27 y comparo con nulo
							// Si es asi asumo que en el proceso se tomaron 27 frecuencias
							// Si la longitud de leser es menor que 66																																			
							
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
//									arfcn28=nf.parse(leser[64].trim()).intValue();
//									arfcn28dbm=nf.parse(leser[65].trim()).intValue();
//									arfcn29=nf.parse(leser[66].trim()).intValue();
//									arfcn29dbm=nf.parse(leser[67].trim()).intValue();
//									arfcn30=nf.parse(leser[68].trim()).intValue();
//									arfcn30dbm=nf.parse(leser[69].trim()).intValue();									
									dummy=leser[64].trim();
								}

						// Para insertar valores nodos al final arfcn debe tener valor diferente de cero
						// Esto para que el dato sea confiable (Posea informacion)
						if(latitude!=0 && longitude!=0 && arfcn00!=0) 
							{
								// En el objeto Originalliste de tipo verketteteliste, se insertan
								// los nuevos valores de del archivo leido
								// System.out.println("LLegue aqui"); // Bandera
								Originalliste.insertend( zeit, latitude,  longitude,  speed, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,segmentdimension);
							}

						counter++;							//Sumo 1 a contador
						System.out.println(counter+"\t"+zeit+"\n"); //Imprimo contador y hora brinco siguiente linea
		
						// Lee la siguiente línea de texto hasta que encuentra un carácter
						// de salto de línea (\n) y retorno de carro (\r).
						aline=bf.readLine();

				}while(aline!=null);	// Realizar mientras la linea leida sea diferente de nulo
										// Si la nueva linea (aline) es nula sale del ciclo
			}
			
		// En caso de que no pueda ser leido el archivo se imprime error.	
		else
			{
				// Se imprime ERROR
				System.out.println("**************** ERROR ****************");
				return;
			}

		// Se pregunta si se desea procesar otro archivo: No
		anotherfile=2;//Keyin.inInt("Do you want to process another file? [1] yes [2] no: \t");

		}while(anotherfile!=2);	// Realizar mientras anotherfile sea diferente de 2
								// Si anotherfile=2 sale del ciclo (no procesa otro archivo)

			// El objeto Originalliste de clase verketteliste invoca método deletefirst()
			Originalliste.deletefirst();
			// El objeto Originalliste de clase verketteliste invoca método CalculateAVG()
			Originalliste.CalculateAVG();
			/////Originalliste.ausgabedatei(outputpath,locationname);
			Originalliste.maxaverage();
			// Creacion de Archivo de Salida. Con el nombre xxxx_internal ericsson.txt
			Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber);

			System.out.println("Leyendo "+ counter+"\n");
			/////System.out.println("MusterID Last "+ musterid+"\n");
			int segments = Originalliste.numberofsegments();
			System.out.println("Segments "+segments +"\n");

			//Apply hypothesis test
			//Randomselection(Originalliste,max);
			//Originalliste.ausgabedatei3();
	}

	// Metodo que selecciona aleatoriamente de la lista los valores
	// Este metodo tiene de argumentos la lista concatenada y el maxrandom que es la 
	// cantidad de valores aleatorios requeridos
	public static void randomselection(verketteteliste Liste, int maxrandom)
	{
		int originalsegments;
		
		// Se obtiene la cantidad total de segmentos originales de la lista
		originalsegments=Liste.numberofsegments();
		
		// La cantidad de segmentos a borrar es igual cantidad total de segmentos 
		// originales menos la cantidad de segmentos aleatorios requeridos
		int segmentstodelete=originalsegments-maxrandom;
		int newsegmentnumber;
		
		// Inicializo contador de segmentos a borrar.
		int counter = 1; 
		int randomizer;
		Random r = new Random();
		
		// Ciclo se realiza mientras el	contador sea menor o igual al numero de
		// segmentos a borrar	
		while (counter<=segmentstodelete) 
			{
				newsegmentnumber = Liste.numberofsegments()-1;
				if (newsegmentnumber==0) {newsegmentnumber=newsegmentnumber+1;}

				randomizer =(r.nextInt(newsegmentnumber));
				
				// Se borra aleatoriamente un segmento, mediante variable radomizer, en
				// lista
				Liste.deleterandom(randomizer);

				counter++;
			}
	}

	public static void hypothesislesen(conf confliste) throws java.io.IOException, java.text.ParseException
	{
		// Variables locales
		String initialtext;
		int samplesneeded;
		float coveragepercentage, confidencelevel, normalfactor, errormargin;
		String aline;

		// Inicializar el archivo de entrada
		// Lee Archivo de Configuracion Inicial llamado HPTC.txt
		File lesedatei = new File("HPTC.txt"); 

		if( lesedatei.exists()==true && lesedatei.canRead()==true) // Si el archivo existe y se puede Leer
			{
				// Se realiza lectura del archivo HPTC.txt
				BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
				// Se realiza lectura de la primer linea
				aline = bf.readLine(); 
				do //while(aline!=null)
					{
						// Crear Token
						// Este método crea un array de elementos a partir de una cadena.
						// Usando como separador el carácter que se le pase como parámetro.
						// En caso de no pasarle separador asume que el espacio en blanco será dicho separador.
						StringTokenizer tokens = new StringTokenizer(aline,",");

						// Crear un objeto NumberFormat
						NumberFormat nf = NumberFormat.getInstance();

						// Leer los datos de un arreglo de cadena y lo almacena en la variable
						initialtext=tokens.nextToken();
						coveragepercentage=nf.parse(tokens.nextToken()).floatValue();
						confidencelevel=nf.parse(tokens.nextToken()).floatValue();
						normalfactor=nf.parse(tokens.nextToken()).floatValue();
						errormargin=nf.parse(tokens.nextToken()).floatValue();
						samplesneeded=nf.parse(tokens.nextToken()).intValue();
						confliste.insertend(initialtext, coveragepercentage,  confidencelevel,  normalfactor, errormargin, samplesneeded);
						// Se realiza lectura de la siguiente linea
						aline=bf.readLine(); 
					}while(aline!=null); // Hacer esto mientras la linea contenga valores diferentes a nulo.
										 // Llegue al fin del archivo de configuracion
			}
		else
			{
				System.out.println("**************** ERROR ****************");
				return;
			}
	}

		// Metodo que procesa todos los datos****************

		public static void blockedfilelesenmethod2(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
		{
			// Variables Locales
			int counter =0;
			String dateiname, dummy;
			int anotherfile;

			String zeit; // Variable hora
			int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06,arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15,arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24,arfcn25,arfcn26,arfcn27;
			double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm,arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm,arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm,arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
			String aline;
			double latitude,longitude;
			String ms, framenumber, direction,event;
			File lesedatei;

			do
			{

			// Nombre del archivo introducido desde el teclado
			dateiname=Keyin.inString("Enter file name to process: \t");

			// Inicializar el archivo de entrada

			// File lesedatei = new File(dateiname);
			// Realizo lectura del archivo introducido desde el teclado
			lesedatei=new File(dateiname);

			// Si el archivo no existe
			if(lesedatei.exists()==false) 
				{
					do{
						// Enviar mensaje que el archivo no existe
						dateiname=Keyin.inString("File does not exists. \nEnter file name to process: \t");

						// Realizo nuevamente lectura del nombre archivo introducido desde 
						// el teclado
						lesedatei=new File(dateiname);

					}while (lesedatei.exists()!=true); // Se queda esperando hasta que se 
													   // introduzca un archivo con el
													   // nombre correcto
					}
					
			// Si el archivo existe y ademas se puede leer
			if( lesedatei.exists()==true && lesedatei.canRead()==true)
				{
					BufferedReader bf = new BufferedReader(new FileReader(lesedatei));
					// Hace lectura de la primer fila y cambia a la siguiente fila
					aline = bf.readLine(); // La primer fila no se lea ya que son los
										   // nombres de las columnas (no posee datos)
					// Se hace lectura de la segunda fila y cursor cambia a la ste fila					   
					aline = bf.readLine();
					do
						{
								int maxarfcn;
								double maxarfcndbm;
								// Crear Token
								
								// Este método crea un arreglo de elementos a partir de 
								// una cadena. Usando como separador el carácter que se 
								// le pase como parámetro. En caso de no pasarle separador
								// asume que el espacio en blanco será dicho separador.
								StringTokenizer tokens = new StringTokenizer(aline,"\t",false);


								// Crear un objeto NumberFormat
								NumberFormat nf = NumberFormat.getInstance();
								
								// Crear lectores(Leser) y los almacena en un arreglo de
								// tipo string
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

							// Selecciona el maximo
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

							if(latitude!=0 && longitude!=0 && arfcn00!=0)
								{
									Originalliste.insertend( zeit, latitude,  longitude,  speed, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm, segmentdimension);
								}

							counter++;
							System.out.println(counter+zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm
										+"\t"+arfcn15+"\t"+arfcn15dbm+"\t"+arfcn16+"\t"+arfcn16dbm+"\t"+arfcn17+"\t"+arfcn17dbm+"\t"+arfcn18+"\t"+arfcn18dbm+"\t"+arfcn19+"\t"+arfcn19dbm+"\t"+arfcn20+"\t"+arfcn20dbm+"\t"+arfcn21+"\t"+arfcn21dbm+"\t"+arfcn22+"\t"+arfcn22dbm+"\t"+arfcn23+"\t"+arfcn23dbm+"\t"+arfcn24+"\t"+arfcn24dbm+"\t"+arfcn25+"\t"+arfcn25dbm+"\t"+arfcn26+"\t"+arfcn26dbm+"\t"+arfcn27+"\t"+arfcn27dbm+"\n");
							
							// Se realiza lectura de la siguiente fila en el archivo
							aline=bf.readLine();

						}while(aline!=null);	// Cuando una fila posee valores nulos
												// implica que se ha llegado al fin del
												// archivo
				}
			else		// Si el archivo no se puede leer imprimir error en pantalla
				{
					System.out.println("**************** ERROR ****************");
					return;
				}

			
			anotherfile=2;//Keyin.inInt("Do you want to process another file? [1] yes [2] no: \t");

			}while(anotherfile!=2);

				Originalliste.deletefirst();
				Originalliste.CalculateAVG();
				//Originalliste.ausgabedatei(outputpath,locationname);
				Originalliste.maxaverage();
				Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber);

				System.out.println("Leyendo "+ counter+"\n");
				//System.out.println("MusterID Last "+ musterid+"\n");
				int segments = Originalliste.numberofsegments();
				System.out.println("Segments "+segments +"\n");


				//apply hypothesis test

				//randomselection(Originalliste,max);
				//Originalliste.ausgabedatei3();



	}



}

