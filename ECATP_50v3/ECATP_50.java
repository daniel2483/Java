
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



public class ECATP_50
{
	//int numero_col;
	
	public static void main (String[] args) throws java.io.IOException,java.text.ParseException
	{

		int errormargincorrection;
		int segmentdimension=10;
		float errormargin;
		int segmentspassed, segmentsfailed, totalsegments;
		float percentagepassed,percentagefailed,sumpercentages;
  	//	classone classone = new classone(); ClassOne ClassOne = ClassOne nuevo ();
  	//	int newvariable = classone.variableone; newvariable int = classone.variableone;

		
		
		//int numero_columnas;
		String stop;
		String locationname, locationnumber, outputpath, logfilename, date, analysisengineer, fieldengineer, icepersonnel, department;
		int thresholdselection, ercmethod;
		int morefiles;
		int threshold=0;

		// Cambiar en caso de un cambio en el software
		// Se imprime version de la herramienta y creadores, maximo y minimo de frecuencias 
		//que deben poseer los Logs para que la herramienta corra.
		System.out.print("\n Version 3.0 max:50 frec min:15 frec");
		System.out.print("\n\n Hugo Navas Aparicio Java Program for ERICSSON");
		System.out.print("\n Last Update 2010-10-06 by Jose Daniel Rodriguez S.\n\n\tFecha Actual: ");
		
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
		logfilename="";//Keyin.inString("Name of Logfile: \t");

		//***************************************************************************************//
		// NOTA 1: En esta seccion se escogen los valores umbrales, que definen si una medicion  //
		// cumple con los requerimientos, dependiendo del area en donde se haya realizado la     //
		// medicion.                                                                             //
		//***************************************************************************************//

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
						//blockedfilelesenmethod2(Originalliste,segmentdimension, outputpath,locationname, locationnumber) ;
					}
			}

		//***************************************************************************************//
		// NOTA 2: En este punto se obtiene la correccion del margen de error que depende        //
		// directamente del numero de muestras (numero de segmentos), que se hayan tomado        //
		// El numero de segmentos se obtiene de la verketteliste (lista concatenada), aca        //
		// denominada Originalliste, mediante el metodo numberofsegments() que retorna del valor //
		// de un contador interno del metodo que es igual al numero de segmentos totales         //
		// La escogencia del numero de muestras a utilizar (muestras requeridas                  //
		// errormargincorrection) depende del numero de muestras obtenidas de las mediciones     //
		// El margen de error se obtiene mediante el metodo margintouse del objeto confliste     //
		// que es de tipo clase que se encuentra dentro del hptest.java. Este margen de error    //
		// depende del numero de muestras requeridas.                                            //
		// Todos los valores de numero de muestras para confiabilidad y el margen de error para  //
		// confiabilidad han sido tomados de estudios estadisticos.                              //
		//***************************************************************************************//
	
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
	
	//ECATP_39 clase=new ECATP_39();
	//numero_columnas=clase.getnumero_col();
	// Obtengo el numero de columnas del metodo
	//System.out.println("\n\tNumero de columnas: "+numero_columnas); 
	//stop=Keyin.inString("Parada \t");
	
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
	if(percentagepassed>=limittocomply100) // Se obtiene el mensaje de la evaluacion final.
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
					fos.print("<br>Fecha post-proceso: ");fos.print(delimiter);fos.print(dia+"-"+mes+"-"+anno+"  "+hora+":"+minuto);
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
		System.out.println("**************************************************************************");
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
		// Variable para obtener la cantidad de columnas validas de la primer fila
		// del archivo de TEMS, que son los nombres de las columnas
		int numero_columnas;
		String zeit,dummy;
		
		//****************************************************************************************//
		// NOTA 3: Si se requiere realizar una ampliacion de software para estudiar mayor cantidad//
		// de valores de frecuencia se deben aumentar la cantidad de variables dentro de este     //
		// metodo                                                                                 //
		//****************************************************************************************//
		
		int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06;
		int arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15;
		int arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24;
		int arfcn25,arfcn26,arfcn27,arfcn28,arfcn29,arfcn30,arfcn31,arfcn32,arfcn33;
		int arfcn34,arfcn35,arfcn36,arfcn37,arfcn38,arfcn39,arfcn40,arfcn41,arfcn42;
		int arfcn43,arfcn44,arfcn45,arfcn46,arfcn47,arfcn48,arfcn49;//,arfcn50;
		double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm;
		double arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm;
		double arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm;
		double arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
		double arfcn28dbm,arfcn29dbm,arfcn30dbm,arfcn31dbm,arfcn32dbm,arfcn33dbm,arfcn34dbm;
		double arfcn35dbm,arfcn36dbm,arfcn37dbm,arfcn38dbm,arfcn39dbm,arfcn40dbm,arfcn41dbm;
		double arfcn42dbm,arfcn43dbm,arfcn44dbm,arfcn45dbm,arfcn46dbm,arfcn47dbm,arfcn48dbm;
		double arfcn49dbm; //arfcn50dbm;
		
		// aline es la variable utilizada para realizar lecturas de cada linea (fila) del archivo
		// original creados por TEMS .txt por ello es de tipo String
		String aline;
		double latitude,longitude; 
		String ms, framenumber, direction,event;
		// Variable lesedatei (Leerarchivo) es de tipo file, ya que contiene
		// los datos del archivo .txt creado por TEMS
		File lesedatei; 

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

				// Separar aline (la linea o fila) cuando encuentra tabs 
				// y almacena los valores en el arreglo String [], 
				// mantiene empty strings del aline (-1)						
				String leser[] = new String[64]; // Lector posee un tamaño de 46

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
						

						//String[] 
						leser = aline.split("\t",-1);
						
						// Copio el valor de la longitud del lector para poder realizar
						// modificaciones a la longitud del lector
						int longitud_lector=leser.length-1; // Correccion del numero de columnas reales
						
						// Variable que contiene numero de columnas validas, es decir, numero 
						// columnas que no contienen informacion de arfcnxx y arfcnxxdbm
						// int col_valid = leser.length-9;

						// Metodo trim() remueve espacios en blanco de un String al inicio y al final de esta
						
						// En caso de que la columna arfcn00dbm posea "" dato invalido 
						// (cadena de caracteres vacio) 
						// entonces leo la siguiente linea del archivo

					//while (leser.length>10)
					//{
						
					while (leser.length<9)
					{
						counter++;
						// Ubico puntero en la siguiente linea
						aline = bf.readLine();
						//aline = bf.readLine();																	
						leser = aline.split("\t",-1);		
					}
					

					
				try
				{
					while (leser[9].compareTo("")==0) 
						{
						counter++;
						// Ubico puntero en la siguiente linea
						aline = bf.readLine();
						//aline = bf.readLine();																	
						leser = aline.split("\t",-1);																			
						}					
				}	

				catch (Exception e)
					{
						dummy="";
					}

					//	break;						
					//}



						
						//**********************************************************************//
						// NOTA 4:                                                              //
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

						// Correccion de numero real de columnas validas
						numero_columnas=longitud_lector;
						
						// El valor del 1er valor del arreglo corresponde a la hora
						zeit=leser[0].trim(); // La primer del archivo columna es el tiempo
						
						// El valor del 2do valor del arreglo corresponde a la MSC
						ms=leser[1].trim(); // La segunda columna es el msc
						framenumber=leser[2].trim();// La tercera columna es el framenumber 
						direction=leser[3].trim(); // La cuarta columna es Direction

						event=leser[4].trim();
						//System.out.println(event);// La quinta columna es Event

						// Obtengo el Lector de latitud
						dummy=leser[5];									// La sexta columna es Latitud
							if(dummy.compareTo("")==0)					// compara Latitud con espacio vacio
								{latitude=0;}
							else
								{
								// Convierte el valor leido de latitud que es un string a valor float
								latitude=nf.parse(leser[5]).floatValue();  
								}

						// Obtengo el Lector de Longitud
						dummy=leser[6];							// La septima columnae es Longitud
							if(dummy.compareTo("")==0)			// compara Longitud con espacio vacio
								{longitude=0;}
							else
								{
								// Convierte el valor de longitud que es un string a valor float 
								longitude=nf.parse(leser[6].trim()).floatValue();
								}
								
						// Obtengo el Lector de Speed
						dummy=leser[7];				// La octava columna es Speed
							if(dummy.compareTo("")==0)
								{
								speed=0;
								}
							else
								{
								
								try
									{
									// Convierte el valor de velocidad que es un string a entero
									speed=nf.parse(leser[7].trim()).intValue(); 
									}
								catch (Exception e)
									{
									speed=0;
									}

								}
								
						// System.out.println("LLegue aqui"); // Bandera
						// Inicializo los valores de arfcn00-arcfn38 y arfcn00dbm-arfcn38dbm
						// Obtengo el Lector de arfcn[0-27] y el arfcn[0-27]dbm
						if(leser.length>9)
						{
						dummy=leser[8];
						}
						else
						{
						dummy="";
						}	
							if(dummy.compareTo("")==0)
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
								if (longitud_lector>=11)
									{
									try	
										{
										arfcn00=nf.parse(leser[8].trim()).intValue();
										arfcn00dbm=nf.parse(leser[9].trim()).intValue();
										dummy=leser[10].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn00=0;
										arfcn00dbm=-150;
										}			
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
									try	
										{
										arfcn01=nf.parse(leser[10].trim()).intValue();
										arfcn01dbm=nf.parse(leser[11].trim()).intValue();
										dummy=leser[12].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn01=0;
										arfcn01dbm=-150;
										}
									}	
								
								else
									{
									arfcn01=0;
									arfcn01dbm=-150;
									}

								// Reviso si es un archivo de 3 frecuencias
								if (longitud_lector>=14)
									{
									try	
										{
										arfcn02=nf.parse(leser[12].trim()).intValue();
										arfcn02dbm=nf.parse(leser[13].trim()).intValue();
										dummy=leser[14].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn02=0;
										arfcn02dbm=-150;
										}
									}	
								
								else
									{
									arfcn02=0;
									arfcn02dbm=-150;
									}

								// Reviso si es un archivo de 4 frecuencias
								if (longitud_lector>=16)
									{
									try	
										{
										arfcn03=nf.parse(leser[14].trim()).intValue();
										arfcn03dbm=nf.parse(leser[15].trim()).intValue();
										dummy=leser[16].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn03=0;
										arfcn03dbm=-150;
										}
									}	
								
								else
									{
									arfcn03=0;
									arfcn03dbm=-150;
									}

								// Reviso si es un archivo de 5 frecuencias
								if (longitud_lector>=18)
									{
									try	
										{
										arfcn04=nf.parse(leser[16].trim()).intValue();
										arfcn04dbm=nf.parse(leser[17].trim()).intValue();
										dummy=leser[18].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn04=0;
										arfcn04dbm=-150;
										}
									}	
								
								else
									{
									arfcn04=0;
									arfcn04dbm=-150;
									}

								// Reviso si es un archivo de 6 frecuencias
								if (longitud_lector>=20)
									{
									try	
										{
										arfcn05=nf.parse(leser[18].trim()).intValue();
										arfcn05dbm=nf.parse(leser[19].trim()).intValue();
										dummy=leser[20].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn05=0;
										arfcn05dbm=-150;
										}
									}	
								
								else
									{
									arfcn05=0;
									arfcn05dbm=-150;
									}

								// Reviso si es un archivo de 7 frecuencias
								if (longitud_lector>=22)
									{
									try	
										{
										arfcn06=nf.parse(leser[20].trim()).intValue();
										arfcn06dbm=nf.parse(leser[21].trim()).intValue();
										dummy=leser[22].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn06=0;
										arfcn06dbm=-150;
										}
									}	
								
								else
									{
									arfcn06=0;
									arfcn06dbm=-150;
									}

								// Reviso si es un archivo de 8 frecuencias
								if (longitud_lector>=24)
									{
									try	
										{
										arfcn07=nf.parse(leser[22].trim()).intValue();
										arfcn07dbm=nf.parse(leser[23].trim()).intValue();
										dummy=leser[24].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn07=0;
										arfcn07dbm=-150;
										}
									}	
								
								else
									{
									arfcn07=0;
									arfcn07dbm=-150;
									}

								// Reviso si es un archivo de 9 frecuencias
								if (longitud_lector>=26)
									{
									try	
										{
										arfcn08=nf.parse(leser[24].trim()).intValue();
										arfcn08dbm=nf.parse(leser[25].trim()).intValue();
										dummy=leser[26].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn08=0;
										arfcn08dbm=-150;
										}
									}	
								
								else
									{
									arfcn08=0;
									arfcn08dbm=-150;
									}

								// Reviso si es un archivo de 10 frecuencias
								if (longitud_lector>=28)
									{
									try	
										{
										arfcn09=nf.parse(leser[26].trim()).intValue();
										arfcn09dbm=nf.parse(leser[27].trim()).intValue();
										dummy=leser[28].trim();	
										}
										
									catch (NumberFormatException e)
										{
										arfcn09=0;
										arfcn09dbm=-150;
										}
									}	
								
								else
									{
									arfcn09=0;
									arfcn09dbm=-150;
									}

								// Reviso si es un archivo de 11 frecuencias
								if (longitud_lector>=30)
									{
									try	
										{
										arfcn10=nf.parse(leser[28].trim()).intValue();
										arfcn10dbm=nf.parse(leser[29].trim()).intValue();
										dummy=leser[30].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn10=0;
										arfcn10dbm=-150;
										}
									}	
								
								else
									{
									arfcn10=0;
									arfcn10dbm=-150;
									}

								// Reviso si es un archivo de 12 frecuencias
								if (longitud_lector>=32)
									{
									try	
										{
										arfcn11=nf.parse(leser[30].trim()).intValue();
										arfcn11dbm=nf.parse(leser[31].trim()).intValue();
										dummy=leser[32].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn11=0;
										arfcn11dbm=-150;
										}
									}	
								
								else
									{
									arfcn11=0;
									arfcn11dbm=-150;
									}

								// Reviso si es un archivo de 13 frecuencias
								if (longitud_lector>=34)
									{
									try	
										{
										arfcn12=nf.parse(leser[32].trim()).intValue();
										arfcn12dbm=nf.parse(leser[33].trim()).intValue();
										dummy=leser[34].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn12=0;
										arfcn12dbm=-150;	
										}
									}	
								
								else
									{
									arfcn12=0;
									arfcn12dbm=-150;
									}

								// Reviso si es un archivo de 14 frecuencias
								if (longitud_lector>=36)
									{
									try	
										{
										arfcn13=nf.parse(leser[34].trim()).intValue();
										arfcn13dbm=nf.parse(leser[35].trim()).intValue();
										dummy=leser[36].trim();											
										}
										
									catch (NumberFormatException e)
										{
										arfcn13=0;
										arfcn13dbm=-150;
										}
									}	
								
								else
									{
									arfcn13=0;
									arfcn13dbm=-150;
									}

								// Reviso si es un archivo de 15 frecuencias
								if (longitud_lector>=38)
									{
									try	
										{
										arfcn14=nf.parse(leser[36].trim()).intValue();
										arfcn14dbm=nf.parse(leser[37].trim()).intValue();
										dummy=leser[38].trim();											
										}
										
									catch (NumberFormatException e)
										{
										arfcn14=0;
										arfcn14dbm=-150;
										}
									}	
								
								else
									{
									arfcn14=0;
									arfcn14dbm=-150;
									}

								// Reviso si es un archivo de 16 frecuencias
								if (longitud_lector>=40)
									{
									try	
										{
										arfcn15=nf.parse(leser[38].trim()).intValue();
										arfcn15dbm=nf.parse(leser[39].trim()).intValue();
										dummy=leser[40].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn15=0;
										arfcn15dbm=-150;
										}
									}	
								
								else
									{
									arfcn15=0;
									arfcn15dbm=-150;
									}

								// Reviso si es un archivo de 17 frecuencias
								if (longitud_lector>=42)
									{
									try	
										{
										arfcn16=nf.parse(leser[40].trim()).intValue();
										arfcn16dbm=nf.parse(leser[41].trim()).intValue();
										dummy=leser[42].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn16=0;
										arfcn16dbm=-150;
										}
									}	
								
								else
									{
									arfcn16=0;
									arfcn16dbm=-150;
									}

								// Reviso si es un archivo de 18 frecuencias
								if (longitud_lector>=44)
									{
									try	
										{
										arfcn17=nf.parse(leser[42].trim()).intValue();
										arfcn17dbm=nf.parse(leser[43].trim()).intValue();
										dummy=leser[44].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn17=0;
										arfcn17dbm=-150;	
										}
									}	
								
								else
									{
									arfcn17=0;
									arfcn17dbm=-150;
									}

								// Reviso si es un archivo de 19 frecuencias
								if (longitud_lector>=46)
									{
									try	
										{
										arfcn18=nf.parse(leser[44].trim()).intValue();
										arfcn18dbm=nf.parse(leser[45].trim()).intValue();
										dummy=leser[46].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn18=0;
										arfcn18dbm=-150;
										}
									}	
								
								else
									{
									arfcn18=0;
									arfcn18dbm=-150;
									}

								// Reviso si es un archivo de 20 frecuencias
								if (longitud_lector>=48)
									{
									try	
										{
										arfcn19=nf.parse(leser[46].trim()).intValue();
										arfcn19dbm=nf.parse(leser[47].trim()).intValue();
										dummy=leser[48].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn19=0;
										arfcn19dbm=-150;
										}
									}	
								
								else
									{
									arfcn19=0;
									arfcn19dbm=-150;
									}

								// Reviso si es un archivo de 21 frecuencias
								if (longitud_lector>=50)
									{
									try	
										{
										arfcn20=nf.parse(leser[48].trim()).intValue();
										arfcn20dbm=nf.parse(leser[49].trim()).intValue();
										dummy=leser[50].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn20=0;
										arfcn20dbm=-150;
										}
									}	
								
								else
									{
									arfcn20=0;
									arfcn20dbm=-150;
									}

								// Reviso si es un archivo de 22 frecuencias
								if (longitud_lector>=52)
									{
									try	
										{
										arfcn21=nf.parse(leser[50].trim()).intValue();
										arfcn21dbm=nf.parse(leser[51].trim()).intValue();
										dummy=leser[52].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn21=0;
										arfcn21dbm=-150;
										}
									}	
								
								else
									{
									arfcn21=0;
									arfcn21dbm=-150;
									}

								// Reviso si es un archivo de 23 frecuencias
								if (longitud_lector>=54)
									{
									try	
										{
										arfcn22=nf.parse(leser[52].trim()).intValue();
										arfcn22dbm=nf.parse(leser[53].trim()).intValue();
										dummy=leser[54].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn22=0;
										arfcn22dbm=-150;
										}
									}	
								
								else
									{
									arfcn22=0;
									arfcn22dbm=-150;
									}

								// Reviso si es un archivo de 24 frecuencias
								if (longitud_lector>=56)
									{
									try	
										{
										arfcn23=nf.parse(leser[54].trim()).intValue();
										arfcn23dbm=nf.parse(leser[55].trim()).intValue();
										dummy=leser[56].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn23=0;
										arfcn23dbm=-150;
										}
									}	
								
								else
									{
									arfcn23=0;
									arfcn23dbm=-150;
									}

								// Reviso si es un archivo de 25 frecuencias
								if (longitud_lector>=58)
									{
									try	
										{
										arfcn24=nf.parse(leser[56].trim()).intValue();
										arfcn24dbm=nf.parse(leser[57].trim()).intValue();
										dummy=leser[58].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn24=0;
										arfcn24dbm=-150;
										}
									}	
								
								else
									{
									arfcn24=0;
									arfcn24dbm=-150;
									}

								// Reviso si es un archivo de 26 frecuencias
								if (longitud_lector>=60)
									{
									try	
										{
										arfcn25=nf.parse(leser[58].trim()).intValue();
										arfcn25dbm=nf.parse(leser[59].trim()).intValue();
										dummy=leser[60].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn25=0;
										arfcn25dbm=-150;
										}
									}	
								
								else
									{
									arfcn25=0;
									arfcn25dbm=-150;
									}
									
								// Reviso si es un archivo de 27 frecuencias
								if (longitud_lector>=62)
									{
									try	
										{
										arfcn26=nf.parse(leser[60].trim()).intValue();
										arfcn26dbm=nf.parse(leser[61].trim()).intValue();
										dummy=leser[62].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn26=0;
										arfcn26dbm=-150;
										}
									}	
								
								else
									{
									arfcn26=0;
									arfcn26dbm=-150;
									}

								// Reviso si es un archivo de 28 frecuencias
								if (longitud_lector>=64)
									{
									try	
										{
										arfcn27=nf.parse(leser[62].trim()).intValue();
										arfcn27dbm=nf.parse(leser[63].trim()).intValue();
										dummy=leser[64].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn27=0;
										arfcn27dbm=-150;
										}
									}	
								
								else
									{
									arfcn27=0;
									arfcn27dbm=-150;
									}

								// Reviso si es un archivo de 29 frecuencias
								if (longitud_lector>=66)
									{
									try	
										{
										arfcn28=nf.parse(leser[64].trim()).intValue();
										arfcn28dbm=nf.parse(leser[65].trim()).intValue();
										dummy=leser[66].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn28=0;
										arfcn28dbm=-150;
										}
									}	
								
								else
									{
									arfcn28=0;
									arfcn28dbm=-150;
									}

								// Reviso si es un archivo de 30 frecuencias
								if (longitud_lector>=68)
									{
									try	
										{
										arfcn29=nf.parse(leser[66].trim()).intValue();
										arfcn29dbm=nf.parse(leser[67].trim()).intValue();
										dummy=leser[68].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn29=0;
										arfcn29dbm=-150;
										}
									}	
								
								else
									{
									arfcn29=0;
									arfcn29dbm=-150;
									}

								// Reviso si es un archivo de 31 frecuencias
								if (longitud_lector>=70)
									{
									try	
										{
										arfcn30=nf.parse(leser[68].trim()).intValue();
										arfcn30dbm=nf.parse(leser[69].trim()).intValue();
										dummy=leser[70].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn30=0;
										arfcn30dbm=-150;
										}
									}	
								
								else
									{
									arfcn30=0;
									arfcn30dbm=-150;
									}

								// Reviso si es un archivo de 32 frecuencias
								if (longitud_lector>=72)
									{
									try	
										{
										arfcn31=nf.parse(leser[70].trim()).intValue();
										arfcn31dbm=nf.parse(leser[71].trim()).intValue();
										dummy=leser[72].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn31=0;
										arfcn31dbm=-150;
										}
									}	
								
								else
									{
									arfcn31=0;
									arfcn31dbm=-150;
									}

								// Reviso si es un archivo de 33 frecuencias
								if (longitud_lector>=74)
									{
									try	
										{
										arfcn32=nf.parse(leser[72].trim()).intValue();
										arfcn32dbm=nf.parse(leser[73].trim()).intValue();
										dummy=leser[74].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn32=0;
										arfcn32dbm=-150;
										}
									}	
								
								else
									{
									arfcn32=0;
									arfcn32dbm=-150;
									}

								// Reviso si es un archivo de 34 frecuencias
								if (longitud_lector>=76)
									{
									try	
										{
										arfcn33=nf.parse(leser[74].trim()).intValue();
										arfcn33dbm=nf.parse(leser[75].trim()).intValue();
										dummy=leser[76].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn33=0;
										arfcn33dbm=-150;
										}
									}	
								
								else
									{
									arfcn33=0;
									arfcn33dbm=-150;
									}

								// Reviso si es un archivo de 35 frecuencias
								if (longitud_lector>=78)
									{
									try	
										{
										arfcn34=nf.parse(leser[76].trim()).intValue();
										arfcn34dbm=nf.parse(leser[77].trim()).intValue();
										dummy=leser[78].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn34=0;
										arfcn34dbm=-150;
										}
									}
								
								else
									{
									arfcn34=0;
									arfcn34dbm=-150;
									}
									
								// Reviso si es un archivo de 36 frecuencias
								if (longitud_lector>=80)
									{
									try	
										{
										arfcn35=nf.parse(leser[78].trim()).intValue();
										arfcn35dbm=nf.parse(leser[79].trim()).intValue();
										dummy=leser[80].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn35=0;
										arfcn35dbm=-150;
										}
									}	
								
								else
									{
									arfcn35=0;
									arfcn35dbm=-150;
									}
									
								// Reviso si es un archivo de 37 frecuencias
								if (longitud_lector>=82)
									{
									try	
										{
										arfcn36=nf.parse(leser[80].trim()).intValue();
										arfcn36dbm=nf.parse(leser[81].trim()).intValue();
										dummy=leser[82].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn36=0;
										arfcn36dbm=-150;
										}
									}	
								
								else
									{
									arfcn36=0;
									arfcn36dbm=-150;																			
									}

								// Reviso si es un archivo de 38 frecuencias
								if (longitud_lector>=84)
									{
									try	
										{
										arfcn37=nf.parse(leser[82].trim()).intValue();
										arfcn37dbm=nf.parse(leser[83].trim()).intValue();
										dummy=leser[84].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn37=0;
										arfcn37dbm=-150;
										}
									}	
								
								else
									{
									arfcn37=0;
									arfcn37dbm=-150;																			
									}

								// Reviso si es un archivo de 39 frecuencias
								if (longitud_lector>=86)
									{
									try	
										{
										arfcn38=nf.parse(leser[84].trim()).intValue();
										arfcn38dbm=nf.parse(leser[85].trim()).intValue();
										dummy=leser[86].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn38=0;
										arfcn38dbm=-150;
										}
									}	
								
								else
									{
									arfcn38=0;
									arfcn38dbm=-150;																			
									}
									
								// Reviso si es un archivo de 40 frecuencias
								if (longitud_lector>=88)
									{
									try	
										{
										arfcn39=nf.parse(leser[86].trim()).intValue();
										arfcn39dbm=nf.parse(leser[87].trim()).intValue();
										dummy=leser[88].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn39=0;
										arfcn39dbm=-150;
										}
									}	
								
								else
									{
									arfcn39=0;
									arfcn39dbm=-150;																			
									}

								// Reviso si es un archivo de 41 frecuencias
								if (longitud_lector>=90)
									{
									try	
										{
										arfcn40=nf.parse(leser[88].trim()).intValue();
										arfcn40dbm=nf.parse(leser[89].trim()).intValue();
										dummy=leser[90].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn40=0;
										arfcn40dbm=-150;
										}
									}	
								
								else
									{
									arfcn40=0;
									arfcn40dbm=-150;
									}

								// Reviso si es un archivo de 42 frecuencias
								if (longitud_lector>=92)
									{
									try	
										{
										arfcn41=nf.parse(leser[90].trim()).intValue();
										arfcn41dbm=nf.parse(leser[91].trim()).intValue();
										dummy=leser[92].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn41=0;
										arfcn41dbm=-150;
										}
									}	
								
								else
									{
									arfcn41=0;
									arfcn41dbm=-150;
									}

								// Reviso si es un archivo de 43 frecuencias
								if (longitud_lector>=94)
									{
									try	
										{
										arfcn42=nf.parse(leser[92].trim()).intValue();
										arfcn42dbm=nf.parse(leser[93].trim()).intValue();
										dummy=leser[94].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn42=0;
										arfcn42dbm=-150;
										}
									}	
								
								else
									{
									arfcn42=0;
									arfcn42dbm=-150;
									}

								// Reviso si es un archivo de 44 frecuencias
								if (longitud_lector>=96)
									{
									try	
										{
										arfcn43=nf.parse(leser[94].trim()).intValue();
										arfcn43dbm=nf.parse(leser[95].trim()).intValue();
										dummy=leser[96].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn43=0;
										arfcn43dbm=-150;
										}
									}	
								
								else
									{
									arfcn43=0;
									arfcn43dbm=-150;
									}

								// Reviso si es un archivo de 45 frecuencias
								if (longitud_lector>=98)
									{
									try	
										{
										arfcn44=nf.parse(leser[96].trim()).intValue();
										arfcn44dbm=nf.parse(leser[97].trim()).intValue();
										dummy=leser[98].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn44=0;
										arfcn44dbm=-150;
										}
									}
								
								else
									{
									arfcn44=0;
									arfcn44dbm=-150;
									}
									
								// Reviso si es un archivo de 46 frecuencias
								if (longitud_lector>=100)
									{
									try	
										{
										arfcn45=nf.parse(leser[98].trim()).intValue();
										arfcn45dbm=nf.parse(leser[99].trim()).intValue();
										dummy=leser[100].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn45=0;
										arfcn45dbm=-150;
										}
									}	
								
								else
									{
									arfcn45=0;
									arfcn45dbm=-150;
									}
									
								// Reviso si es un archivo de 47 frecuencias
								if (longitud_lector>=102)
									{
									try	
										{
										arfcn46=nf.parse(leser[100].trim()).intValue();
										arfcn46dbm=nf.parse(leser[101].trim()).intValue();
										dummy=leser[102].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn46=0;
										arfcn46dbm=-150;
										}
									}	
								
								else
									{
									arfcn46=0;
									arfcn46dbm=-150;
									}

								// Reviso si es un archivo de 48 frecuencias
								if (longitud_lector>=104)
									{
									try	
										{
										arfcn47=nf.parse(leser[102].trim()).intValue();
										arfcn47dbm=nf.parse(leser[103].trim()).intValue();
										dummy=leser[104].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn47=0;
										arfcn47dbm=-150;
										}
									}	
								
								else
									{
									arfcn47=0;
									arfcn47dbm=-150;
									}

								// Reviso si es un archivo de 49 frecuencias
								if (longitud_lector>=106)
									{
									try	
										{
										arfcn48=nf.parse(leser[104].trim()).intValue();
										arfcn48dbm=nf.parse(leser[105].trim()).intValue();
										dummy=leser[106].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn48=0;
										arfcn48dbm=-150;
										}
									}	
								
								else
									{
									arfcn48=0;
									arfcn48dbm=-150;
									}
									
								// Reviso si es un archivo de 40 frecuencias
								if (longitud_lector>=108)
									{
									try	
										{
										arfcn49=nf.parse(leser[106].trim()).intValue();
										arfcn49dbm=nf.parse(leser[107].trim()).intValue();
										dummy=leser[108].trim();
										}
										
									catch (NumberFormatException e)
										{
										arfcn49=0;
										arfcn49dbm=-150;
										}
									}	
								
								else
									{
									arfcn49=0;
									arfcn49dbm=-150;				
									}
						
					//---------------------------------------------------------------//
					// NOTA 5: En caso de modificar este software se debe agregar    //
					// valores arfcnxx y arfcnxxdbm al final de cada comparacion     //
					// else if (longitud_lector>=XX)                                 //
					//---------------------------------------------------------------//
								}										
									
	

						// Para insertar valores nodos al final arfcn debe tener valor diferente de cero
						// Esto para que el dato sea confiable (Posea informacion)
					if(latitude!=0 && longitude!=0 && arfcn00!=0 && arfcn00dbm!=0) 
						{
						// En el objeto Originalliste de tipo verketteteliste, se insertan
						// los nuevos valores de del archivo leido
						// System.out.println("LLegue aqui"); // Bandera

					//---------------------------------------------------------------//
					// NOTA 6: En caso de modificar este software a un analisis de   //
					// mayor cantidad de frecuencias se debe agregar mayor cantidad  //
					// de argumentos dentro del metodo insertend()                   //
					// arfcnxx y arfcnxxdbm                                          //
					//---------------------------------------------------------------//
						
						Originalliste.insertend( zeit, latitude,  longitude,  speed, 
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
							// Posible ampliacion a 40 frecuencias
							//arfcn39, arfcn39dbm,
							segmentdimension);
						}

						counter++;					//Sumo 1 a contador
						System.out.println(counter+ "  "+ zeit);//Imprimo contador y hora brinco siguiente linea
		
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
			System.out.println("\n");
			// El objeto Originalliste de clase verketteliste invoca método deletefirst()
			Originalliste.deletefirst();
			// El objeto Originalliste de clase verketteliste invoca método CalculateAVG()
			Originalliste.CalculateAVG();
			/////Originalliste.ausgabedatei(outputpath,locationname);
			Originalliste.maxaverage();
			int conversor=0;


			//String stop;
			//stop=Keyin.inString("Parada \t");
			
			// Creacion de Archivo de Salida. Con el nombre xxxx_internal ericsson.txt
			Originalliste.ausgabedateiAVG(outputpath, locationname,locationnumber,
										  numero_columnas, conversor);
			
			int result=(numero_columnas-8)/2;

			System.out.println("\n\tNumero de columnas del archivo: "+numero_columnas);
			System.out.println("\n\tFrecuencias Analizadas: "+result); 
			//String stop;
			//stop=Keyin.inString("Parada \t");

			System.out.println("\n"+"\t"+"Datos Leidos: "+ counter);
			//int segments = Originalliste.numberofsegments();
			//System.out.println("Segments "+segments +"\n");
			//ECATP_39 clase=new ECATP_39();
			//clase.setnumero_col(numero_columnas);
			
	}

/*	public void setnumero_col(int number)
	{
	System.out.println("\n\t"+number);
	this.numero_col=number;
	System.out.println("\n\t"+numero_col);
	}
	
	public int getnumero_col()
	{
	System.out.println("\n\t"+numero_col);
	return this.numero_col;
	}
*/


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
/*
		public static void blockedfilelesenmethod2(verketteteliste Originalliste, int segmentdimension, String outputpath, String locationname, String locationnumber) throws java.io.IOException, java.text.ParseException
		{
			// Variables Locales
			int counter =0;
			String dateiname, dummy;
			int anotherfile;

			String zeit; // Variable hora
			int musterid,speed,arfcn00,arfcn01,arfcn02,arfcn03,arfcn04,arfcn05,arfcn06;
			int arfcn07,arfcn08,arfcn09,arfcn10,arfcn11,arfcn12,arfcn13,arfcn14,arfcn15;
			int arfcn16,arfcn17,arfcn18,arfcn19,arfcn20,arfcn21,arfcn22,arfcn23,arfcn24;
			int arfcn25,arfcn26,arfcn27,arfcn28,arfcn29,arfcn30,arfcn31,arfcn32,arfcn33;
			int arfcn34,arfcn35,arfcn36,arfcn37,arfcn38;
			double arfcn00dbm,arfcn01dbm,arfcn02dbm,arfcn03dbm,arfcn04dbm,arfcn05dbm,arfcn06dbm;
			double arfcn07dbm,arfcn08dbm,arfcn09dbm,arfcn10dbm,arfcn11dbm,arfcn12dbm,arfcn13dbm;
			double arfcn14dbm,arfcn15dbm,arfcn16dbm,arfcn17dbm,arfcn18dbm,arfcn19dbm,arfcn20dbm;
			double arfcn21dbm,arfcn22dbm,arfcn23dbm,arfcn24dbm,arfcn25dbm,arfcn26dbm,arfcn27dbm;
			double arfcn28dbm,arfcn29dbm,arfcn30dbm,arfcn31dbm,arfcn32dbm,arfcn33dbm,arfcn34dbm;
			double arfcn35dbm,arfcn36dbm,arfcn37dbm,arfcn38dbm;
		
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

							if(arfcn28dbm>maxarfcndbm)
							{
								maxarfcn=arfcn28;
								maxarfcndbm=arfcn28dbm;
							}

							if(arfcn29dbm>maxarfcndbm)
							{
								maxarfcn=arfcn29;
								maxarfcndbm=arfcn29dbm;
							}

							if(arfcn30dbm>maxarfcndbm)
							{
								maxarfcn=arfcn30;
								maxarfcndbm=arfcn30dbm;
							}
							
							if(arfcn31dbm>maxarfcndbm)
							{
								maxarfcn=arfcn31;
								maxarfcndbm=arfcn31dbm;
							}

							if(arfcn32dbm>maxarfcndbm)
							{
								maxarfcn=arfcn32;
								maxarfcndbm=arfcn32dbm;
							}

							if(arfcn33dbm>maxarfcndbm)
							{
								maxarfcn=arfcn33;
								maxarfcndbm=arfcn33dbm;
							}

							if(arfcn34dbm>maxarfcndbm)
							{
								maxarfcn=arfcn34;
								maxarfcndbm=arfcn34dbm;
							}

							if(arfcn35dbm>maxarfcndbm)
							{
								maxarfcn=arfcn35;
								maxarfcndbm=arfcn35dbm;
							}

							if(arfcn36dbm>maxarfcndbm)
							{
								maxarfcn=arfcn36;
								maxarfcndbm=arfcn36dbm;
							}

							if(arfcn37dbm>maxarfcndbm)
							{
								maxarfcn=arfcn37;
								maxarfcndbm=arfcn37dbm;
							}

							if(arfcn38dbm>maxarfcndbm)
							{
								maxarfcn=arfcn38;
								maxarfcndbm=arfcn38dbm;
							}
							
							//END SELECT MAXIMUM

							//System.out.println(zeit+"\t"+latitude+"\t"+longitude+"\t"+speed+"\t"+arfcn00+"\t"+arfcn00dbm+"\t"+arfcn01+"\t"+arfcn01dbm+"\t"+arfcn02+"\t"+arfcn02dbm+"\t"+arfcn03+"\t"+arfcn03dbm+"\t"+arfcn04+"\t"+arfcn04dbm+"\t"+arfcn05+"\t"+arfcn05dbm+"\t"+arfcn06+"\t"+arfcn06dbm+"\t"+arfcn07+"\t"+arfcn07dbm+"\t"+arfcn08+"\t"+arfcn08dbm+"\t"+arfcn09+"\t"+arfcn09dbm+"\t"+arfcn10+"\t"+arfcn10dbm+"\t"+arfcn11+"\t"+arfcn11dbm+"\t"+arfcn12+"\t"+arfcn12dbm+"\t"+arfcn13+"\t"+arfcn13dbm+"\t"+arfcn14+"\t"+arfcn14dbm+"\t");

							if(latitude!=0 && longitude!=0 && arfcn00!=0)
								{
									Originalliste.insertend( zeit,latitude,longitude,speed,
									arfcn00,arfcn00dbm,arfcn01,arfcn01dbm,arfcn02,arfcn02dbm,
									arfcn03,arfcn03dbm,arfcn04,arfcn04dbm,arfcn05,arfcn05dbm,
									arfcn06,arfcn06dbm,arfcn07,arfcn07dbm,arfcn08,arfcn08dbm,
									arfcn09,arfcn09dbm,arfcn10,arfcn10dbm,arfcn11,arfcn11dbm,
									arfcn12,arfcn12dbm,arfcn13,arfcn13dbm,arfcn14,arfcn14dbm,
									arfcn15,arfcn15dbm,arfcn16,arfcn16dbm,arfcn17,arfcn17dbm,
									arfcn18,arfcn18dbm,arfcn19,arfcn19dbm,arfcn20,arfcn20dbm,
									arfcn21,arfcn21dbm,arfcn22,arfcn22dbm,arfcn23,arfcn23dbm,
									arfcn24,arfcn24dbm,arfcn25,arfcn25dbm,arfcn26,arfcn26dbm,
									arfcn27,arfcn27dbm,arfcn28,arfcn28dbm,arfcn29,arfcn29dbm,
									arfcn30,arfcn30dbm,arfcn31,arfcn31dbm,arfcn32,arfcn32dbm,
									arfcn33,arfcn33dbm,arfcn34,arfcn34dbm,arfcn35,arfcn35dbm,
									arfcn36,arfcn36dbm,arfcn37,arfcn37dbm,arfcn38,arfcn38dbm,
									segmentdimension);
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


*/

}