
import java.io.*;
import java.io.File;
import java.lang.Object;
import java.lang.*;

class rawsamplenode
{
	// Declaracion de variables
	private String zeit;	// Variable Tiempo que es de tipo string (cadena de caracteres)
	private double latitude;	// La latitud debe ser de tipo double ya que contiene valores decimales
	private double longitude;	// La longitud debe ser de tipo double ya que contiene valores decimales
	private int speed;
	
	private int arfcn00; // Frecuencia 1
	private double arfcn00dbm; //Potencia de la Frecuencia 1
	private int arfcn01; // Frecuencia 2
	private double arfcn01dbm; //Potencia de la Frecuencia 2
	private int arfcn02; // Frecuencia 3
	private double arfcn02dbm; //Potencia de la Frecuencia 3
	private int arfcn03; // Frecuencia 4
	private double arfcn03dbm; //Potencia de la Frecuencia 4
	private int arfcn04; // Frecuencia 5
	private double arfcn04dbm; //Potencia de la Frecuencia 5
	private int arfcn05; // Frecuencia 6
	private double arfcn05dbm; //Potencia de la Frecuencia 6
	private int arfcn06; // Frecuencia 7
	private double arfcn06dbm; //Potencia de la Frecuencia 7
	private int arfcn07; // Frecuencia 8
	private double arfcn07dbm; //Potencia de la Frecuencia 8
	private int arfcn08; // Frecuencia 9
	private double arfcn08dbm; //Potencia de la Frecuencia 9
	private int arfcn09; // Frecuencia 10
	private double arfcn09dbm; //Potencia de la Frecuencia 10
	private int arfcn10; // Frecuencia 11
	private double arfcn10dbm; //Potencia de la Frecuencia 11
	private int arfcn11; // Frecuencia 12
	private double arfcn11dbm; //Potencia de la Frecuencia 12
	private int arfcn12; // Frecuencia 13
	private double arfcn12dbm; //Potencia de la Frecuencia 13
	private int arfcn13; // Frecuencia 14
	private double arfcn13dbm; //Potencia de la Frecuencia 14
	private int arfcn14; // Frecuencia 15
	private double arfcn14dbm; //Potencia de la Frecuencia 15
	private int arfcn15; // Frecuencia 16
	private double arfcn15dbm; //Potencia de la Frecuencia 16
	private int arfcn16; // Frecuencia 17
	private double arfcn16dbm; //Potencia de la Frecuencia 17
	private int arfcn17; // Frecuencia 18
	private double arfcn17dbm; //Potencia de la Frecuencia 18
	private int arfcn18; // Frecuencia 19
	private double arfcn18dbm; //Potencia de la Frecuencia 19
	private int arfcn19; // Frecuencia 20
	private double arfcn19dbm; //Potencia de la Frecuencia 20
	private int arfcn20; // Frecuencia 21
	private double arfcn20dbm; //Potencia de la Frecuencia 21
	private int arfcn21; // Frecuencia 22
	private double arfcn21dbm; //Potencia de la Frecuencia 22
	private int arfcn22; // Frecuencia 23
	private double arfcn22dbm; //Potencia de la Frecuencia 23
	private int arfcn23; // Frecuencia 24
	private double arfcn23dbm; //Potencia de la Frecuencia 24
	private int arfcn24; // Frecuencia 25
	private double arfcn24dbm; //Potencia de la Frecuencia 25
	private int arfcn25; // Frecuencia 26
	private double arfcn25dbm; //Potencia de la Frecuencia 26
	private int arfcn26; // Frecuencia 27
	private double arfcn26dbm; //Potencia de la Frecuencia 27
	private int arfcn27; // Frecuencia 28
	private double arfcn27dbm; //Potencia de la Frecuencia 28	
//	private int arfcn28; // Frecuencia 29
//	private double arfcn28dbm; //Potencia de la Frecuencia 29
//	private int arfcn29; // Frecuencia 30
//	private double arfcn29dbm; //Potencia de la Frecuencia 30
//	private int arfcn30; // Frecuencia 31
//	private double arfcn30dbm; //Potencia de la Frecuencia 31
//	private int arfcn31; // Frecuencia 32
//	private double arfcn31dbm; //Potencia de la Frecuencia 32
//  ... En caso de que se requiera añadir mas frecuencias
 		
	// Programa para post-procesar datos con 28 frecuencias

	//--------------------------------------------------------------------------------------//
	//	NOTA 1: Si se desea realizar un cambio en el software por ampliacion de cantidad de //
	//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
	//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //  
	//  frecuencias: Se deben crear las variables arfcn28,arfcndbm28,...,arfcn30,arfcndbm30 // 
	//--------------------------------------------------------------------------------------//

	private int zaehler; // Variable contador
	private double coordistance; 
	private double totalroute; // Variable donde se maneja la ruta total
	private int segmentnumber;
	private int maxarfcn;
	private double maxarfcndbm;

	private rawsamplenode next;
	private rawsamplenode previous;

	// Nodos constructores
		
			// En caso de querer realizar una actualizacion de software ya que aumentaron
			// la cantidad de frecuencias en el post-proceso, se deben incluir dentro del
			// argumento de este metodo.
			
public rawsamplenode(String zeit, rawsamplenode siguiente, rawsamplenode anterior)
		{
			// La funcion this asigna el valor a la variable con el valor asignado dentro
			// de este metodo para inicializar valores.
			
				this.coordistance=0.0;
				this.totalroute=0.0;
				this.segmentnumber=0;
				this.zeit=zeit;
				this.latitude=0;
				this.longitude=0;
				this.speed=0;
				this.arfcn00=0;
				this.arfcn00dbm=0.0;
				this.arfcn01=0;
				this.arfcn01dbm=0.0;
				this.arfcn02=0;
				this.arfcn02dbm=0.0;
				this.arfcn03=0;
				this.arfcn03dbm=0.0;
				this.arfcn04=0;
				this.arfcn04dbm=0.0;
				this.arfcn05=0;
				this.arfcn05dbm=0.0;
				this.arfcn06=0;
				this.arfcn06dbm=0.0;
				this.arfcn07=0;
				this.arfcn07dbm=0.0;
				this.arfcn08=0;
				this.arfcn08dbm=0.0;
				this.arfcn09=0;
				this.arfcn09dbm=0.0;
				this.arfcn10=0;
				this.arfcn10dbm=0.0;
				this.arfcn11=0;
				this.arfcn11dbm=0.0;
				this.arfcn12=0;
				this.arfcn12dbm=0.0;
				this.arfcn13=0;
				this.arfcn13dbm=0.0;
				this.arfcn14=0;
				this.arfcn14dbm=0.0;
				this.arfcn15=0;
				this.arfcn15dbm=0.0;
				this.arfcn16=0;
				this.arfcn16dbm=0.0;
				this.arfcn17=0;
				this.arfcn17dbm=0.0;
				this.arfcn18=0;
				this.arfcn18dbm=0.0;
				this.arfcn19=0;
				this.arfcn19dbm=0.0;
				this.arfcn20=0;
				this.arfcn20dbm=0.0;
				this.arfcn21=0;
				this.arfcn21dbm=0.0;
				this.arfcn22=0;
				this.arfcn22dbm=0.0;
				this.arfcn23=0;
				this.arfcn23dbm=0.0;
				this.arfcn24=0;
				this.arfcn24dbm=0.0;
				this.arfcn25=0;
				this.arfcn25dbm=0.0;
				this.arfcn26=0;
				this.arfcn26dbm=0.0;
				this.arfcn27=0;
				this.arfcn27dbm=0.0;
//				this.arfcn28=0;
//				this.arfcn28dbm=0.0;
//				this.arfcn29=0;
//				this.arfcn29dbm=0.0;
//				this.arfcn30=0;
//				this.arfcn30dbm=0.0;
//				this.arfcn31=0;
//				this.arfcn31dbm=0.0;
//				... En caso de que se quieran añadir mas frecuencias

//--------------------------------------------------------------------------------------//
//	NOTA 2: Si se desea realizar un cambio en el software por ampliacion de cantidad de //
//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
//  frecuencias: Se deben crear las variables this.arfcn28=0,this.arfcndbm28=0.0,...,   //
//  this.arfcn30=0,this.arfcndbm30=0.0                                                  //
//--------------------------------------------------------------------------------------// 

			this.zaehler=1;

			this.next=siguiente;
			this.previous=anterior;
		}

public rawsamplenode(String zeit,double latitude, double longitude, rawsamplenode siguiente, rawsamplenode anterior)
			{
				// La funcion this asigna el valor a la variable con el valor asignado dentro
				// de este metodo para inicializar valores.				
				this.totalroute=0.0;
				this.segmentnumber=0;
				this.zeit=zeit;
				this.latitude=latitude;
				this.longitude=longitude;
				this.speed=speed;
				this.arfcn00=0;
				this.arfcn00dbm=0.0;
				this.arfcn01=0;
				this.arfcn01dbm=0.0;
				this.arfcn02=0;
				this.arfcn02dbm=0.0;
				this.arfcn03=0;
				this.arfcn03dbm=0.0;
				this.arfcn04=0;
				this.arfcn04dbm=0.0;
				this.arfcn05=0;
				this.arfcn05dbm=0.0;
				this.arfcn06=0;
				this.arfcn06dbm=0.0;
				this.arfcn07=0;
				this.arfcn07dbm=0.0;
				this.arfcn08=0;
				this.arfcn08dbm=0.0;
				this.arfcn09=0;
				this.arfcn09dbm=0.0;
				this.arfcn10=0;
				this.arfcn10dbm=0.0;
				this.arfcn11=0;
				this.arfcn11dbm=0.0;
				this.arfcn12=0;
				this.arfcn12dbm=0.0;
				this.arfcn13=0;
				this.arfcn13dbm=0.0;
				this.arfcn14=0;
				this.arfcn14dbm=0.0;
				this.arfcn15=0;
				this.arfcn15dbm=0.0;
				this.arfcn16=0;
				this.arfcn16dbm=0.0;
				this.arfcn17=0;
				this.arfcn17dbm=0.0;
				this.arfcn18=0;
				this.arfcn18dbm=0.0;
				this.arfcn19=0;
				this.arfcn19dbm=0.0;
				this.arfcn20=0;
				this.arfcn20dbm=0.0;
				this.arfcn21=0;
				this.arfcn21dbm=0.0;
				this.arfcn22=0;
				this.arfcn22dbm=0.0;
				this.arfcn23=0;
				this.arfcn23dbm=0.0;
				this.arfcn24=0;
				this.arfcn24dbm=0.0;
				this.arfcn25=0;
				this.arfcn25dbm=0.0;
				this.arfcn26=0;
				this.arfcn26dbm=0.0;
				this.arfcn27=0;
				this.arfcn27dbm=0.0;
//				this.arfcn28=0;
//				this.arfcn28dbm=0.0;
//				this.arfcn29=0;
//				this.arfcn29dbm=0.0;
//				this.arfcn30=0;
//				this.arfcn30dbm=0.0;
//				this.arfcn31=0;
//				this.arfcn31dbm=0.0;
//				... En caso de que se quieran añadir mas frecuencias

//--------------------------------------------------------------------------------------//
//	NOTA 3: Si se desea realizar un cambio en el software por ampliacion de cantidad de //
//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
//  frecuencias: Se deben crear las variables this.arfcn28=0,this.arfcndbm28=0.0,...,   //
//  this.arfcn30=0,this.arfcndbm30=0.0                                                  //
//--------------------------------------------------------------------------------------// 

				this.zaehler=1;

				this.next=siguiente;
				this.previous=anterior;
		}


public rawsamplenode(String zeit,double latitude, double longitude)
			{
				// La funcion this asigna el valor a la variable con el valor asignado 
				// dentro de este metodo para inicializar valores.				
				this.coordistance=0.0;
				this.totalroute=0.0;
				this.segmentnumber=0;
				this.zeit=zeit;
				this.latitude=latitude;
				this.longitude=longitude;
				this.speed=speed;
				this.arfcn00=0;
				this.arfcn00dbm=0.0;
				this.arfcn01=0;
				this.arfcn01dbm=0.0;
				this.arfcn02=0;
				this.arfcn02dbm=0.0;
				this.arfcn03=0;
				this.arfcn03dbm=0.0;
				this.arfcn04=0;
				this.arfcn04dbm=0.0;
				this.arfcn05=0;
				this.arfcn05dbm=0.0;
				this.arfcn06=0;
				this.arfcn06dbm=0.0;
				this.arfcn07=0;
				this.arfcn07dbm=0.0;
				this.arfcn08=0;
				this.arfcn08dbm=0.0;
				this.arfcn09=0;
				this.arfcn09dbm=0.0;
				this.arfcn10=0;
				this.arfcn10dbm=0.0;
				this.arfcn11=0;
				this.arfcn11dbm=0.0;
				this.arfcn12=0;
				this.arfcn12dbm=0.0;
				this.arfcn13=0;
				this.arfcn13dbm=0.0;
				this.arfcn14=0;
				this.arfcn14dbm=0.0;
				this.arfcn15=0;
				this.arfcn15dbm=0.0;
				this.arfcn16=0;
				this.arfcn16dbm=0.0;
				this.arfcn17=0;
				this.arfcn17dbm=0.0;
				this.arfcn18=0;
				this.arfcn18dbm=0.0;
				this.arfcn19=0;
				this.arfcn19dbm=0.0;
				this.arfcn20=0;
				this.arfcn20dbm=0.0;
				this.arfcn21=0;
				this.arfcn21dbm=0.0;
				this.arfcn22=0;
				this.arfcn22dbm=0.0;
				this.arfcn23=0;
				this.arfcn23dbm=0.0;
				this.arfcn24=0;
				this.arfcn24dbm=0.0;
				this.arfcn25=0;
				this.arfcn25dbm=0.0;
				this.arfcn26=0;
				this.arfcn26dbm=0.0;
				this.arfcn27=0;
				this.arfcn27dbm=0.0;
//				this.arfcn28=0;
//				this.arfcn28dbm=0.0;
//				this.arfcn29=0;
//				this.arfcn29dbm=0.0;
//				this.arfcn30=0;
//				this.arfcn30dbm=0.0;
//				this.arfcn31=0;
//				this.arfcn31dbm=0.0;
//				... En caso de que se quieran añadir mas frecuencias


	//--------------------------------------------------------------------------------------//
	//	NOTA 4: Si se desea realizar un cambio en el software por ampliacion de cantidad de //
	//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
	//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
	//  frecuencias: Se deben crear las variables this.arfcn28=0,this.arfcndbm28=0.0,...,   //
	//  this.arfcn30,this.arfcndbm30                                                        //
	//--------------------------------------------------------------------------------------// 

				this.zaehler=1;

				this.next=null;
				this.previous=null;
		}
		
		//*************************************//
		// Metodos para extraer y setear datos //
		//*************************************//
		
		// Metodo que toma la hora 
		public String getzeit() 
		{
			return this.zeit;   // retorna el valor de tiempo
		}
		
		// Metodo que toma la latitud 
		public double getlatitude()
		{
			return this.latitude; // Retorna el valor de latitud
		}
		
		// Metodo que toma la longitud 		
		public double getlongitude()
		{
			return this.longitude; // Retorna el valor de longitud
		}

		// Metodo que toma la velocidad		
		public int getspeed()
		{
			return this.speed; // Retorna el valor de velocidad
		}


	//********************************************************************************//
	// En caso de querer realizar una actualizacion de software ya que aumentaron     //
	// la cantidad de frecuencias en el post-proceso, se deben incluir mayor cantidad //
	// de metodos setarfnXX		                                                      //
	//********************************************************************************// 

		// Metodo que toma la frecuencia 1
		public int getarfcn00()
		{
			return this.arfcn00;
		}
		
		// Metodo que toma la Potencia de la frecuencia 1
		public double getarfcn00dbm()
		{
			return this.arfcn00dbm;
		}
		
		// Metodo que toma la frecuencia 2
		public int getarfcn01()
		{
			return this.arfcn01;
		}
		
		// Metodo que toma la Potencia de la frecuencia 2
		public double getarfcn01dbm()
		{
			return this.arfcn01dbm;
		}
		
		// Metodo que toma la frecuencia 3
		public int getarfcn02()
		{
			return this.arfcn02;
		}
		
		// Metodo que toma la Potencia de la frecuencia 3
		public double getarfcn02dbm()
		{
			return this.arfcn02dbm;
		}
		
		// Metodo que toma la frecuencia 4
		public int getarfcn03()
		{
			return this.arfcn03;
		}
		
		// Metodo que toma la Potencia de la frecuencia 4
		public double getarfcn03dbm()
		{
			return this.arfcn03dbm;
		}
		
		// Metodo que toma la frecuencia 5
		public int getarfcn04()
		{
			return this.arfcn04;
		}
		
		// Metodo que toma la Potencia de la frecuencia 5
		public double getarfcn04dbm()
		{
			return this.arfcn04dbm;
		}
		
		// Metodo que toma la frecuencia 6
		public int getarfcn05()
		{
			return this.arfcn05;
		}
		
		// Metodo que toma la Potencia de la frecuencia 6
		public double getarfcn05dbm()
		{
			return this.arfcn05dbm;
		}
		
		// Metodo que toma la frecuencia 7
		public int getarfcn06()
		{
			return this.arfcn06;
		}
		
		// Metodo que toma la Potencia de la frecuencia 7
		public double getarfcn06dbm()
		{
			return this.arfcn06dbm;
		}
		
		// Metodo que toma la frecuencia 8
		public int getarfcn07()
		{
			return this.arfcn07;
		}
		
		// Metodo que toma la Potencia de la frecuencia 8
		public double getarfcn07dbm()
		{
			return this.arfcn07dbm;
		}
		
		// Metodo que toma la frecuencia 9
		public int getarfcn08()
		{
			return this.arfcn08;
		}
		
		// Metodo que toma la Potencia de la frecuencia 9
		public double getarfcn08dbm()
		{
			return this.arfcn08dbm;
		}
		
		// Metodo que toma la frecuencia 10
		public int getarfcn09()
		{
			return this.arfcn09;
		}
		
		// Metodo que toma la Potencia de la frecuencia 10
		public double getarfcn09dbm()
		{
			return this.arfcn09dbm;
		}
		
		// Metodo que toma la frecuencia 11
		public int getarfcn10()
		{
			return this.arfcn10;
		}
		
		// Metodo que toma la Potencia de la frecuencia 11
		public double getarfcn10dbm()
		{
			return this.arfcn10dbm;
		}
		
		// Metodo que toma la frecuencia 12
		public int getarfcn11()
		{
			return this.arfcn11;
		}
		
		// Metodo que toma la Potencia de la frecuencia 12
		public double getarfcn11dbm()
		{
			return this.arfcn11dbm;
		}
		
		// Metodo que toma la frecuencia 13
		public int getarfcn12()
		{
			return this.arfcn12;
		}
		
		// Metodo que toma la Potencia de la frecuencia 13
		public double getarfcn12dbm()
		{
			return this.arfcn12dbm;
		}
		
		// Metodo que toma la frecuencia 14
		public int getarfcn13()
		{
			return this.arfcn13;
		}
		
		// Metodo que toma la Potencia de la frecuencia 14
		public double getarfcn13dbm()
		{
			return this.arfcn13dbm;
		}
		
		// Metodo que toma la frecuencia 15
		public int getarfcn14()
		{
			return this.arfcn14;
		}
		
		// Metodo que toma la Potencia de la frecuencia 15
		public double getarfcn14dbm()
		{
			return this.arfcn14dbm;
		}
		
		// Metodo que toma la frecuencia 16
		public int getarfcn15()
		{
			return this.arfcn15;
		}
		
		// Metodo que toma la Potencia de la frecuencia 16
		public double getarfcn15dbm()
		{
			return this.arfcn15dbm;
		}
		
		// Metodo que toma la frecuencia 17
		public int getarfcn16()
		{
			return this.arfcn16;
		}
		
		// Metodo que toma la Potencia de la frecuencia 17
		public double getarfcn16dbm()
		{
			return this.arfcn16dbm;
		}
		
		// Metodo que toma la frecuencia 18
		public int getarfcn17()
		{
			return this.arfcn17;
		}
		
		// Metodo que toma la Potencia de la frecuencia 18
		public double getarfcn17dbm()
		{
			return this.arfcn17dbm;
		}
		
		// Metodo que toma la frecuencia 19
		public int getarfcn18()
		{
			return this.arfcn18;
		}
		
		// Metodo que toma la Potencia de la frecuencia 19
		public double getarfcn18dbm()
		{
			return this.arfcn18dbm;
		}
		
		// Metodo que toma la frecuencia 20
		public int getarfcn19()
		{
			return this.arfcn19;
		}
		
		// Metodo que toma la Potencia de la frecuencia 20
		public double getarfcn19dbm()
		{
			return this.arfcn19dbm;
		}
		
		// Metodo que toma la frecuencia 21
		public int getarfcn20()
		{
			return this.arfcn20;
		}
		
		// Metodo que toma la Potencia de la frecuencia 21
		public double getarfcn20dbm()
		{
			return this.arfcn20dbm;
		}
		
		// Metodo que toma la frecuencia 22
		public int getarfcn21()
		{
			return this.arfcn21;
		}
		
		// Metodo que toma la Potencia de la frecuencia 22
		public double getarfcn21dbm()
		{
			return this.arfcn21dbm;
		}
		
		// Metodo que toma la frecuencia 23
		public int getarfcn22()
		{
			return this.arfcn22;
		}
		
		// Metodo que toma la Potencia de la frecuencia 23
		public double getarfcn22dbm()
		{
			return this.arfcn22dbm;
		}
		
		// Metodo que toma la frecuencia 24
		public int getarfcn23()
		{
			return this.arfcn23;
		}
		
		// Metodo que toma la Potencia de la frecuencia 24
		public double getarfcn23dbm()
		{
			return this.arfcn23dbm;
		}
		
		// Metodo que toma la frecuencia 25
		public int getarfcn24()
		{
			return this.arfcn24;
		}
		
		// Metodo que toma la Potencia de la frecuencia 25
		public double getarfcn24dbm()
		{
			return this.arfcn24dbm;
		}
		
		// Metodo que toma la frecuencia 26
		public int getarfcn25()
		{
			return this.arfcn25;
		}
		
		// Metodo que toma la Potencia de la frecuencia 26
		public double getarfcn25dbm()
		{
			return this.arfcn25dbm;
		}
		
		// Metodo que toma la frecuencia 27
		public int getarfcn26()
		{
			return this.arfcn26;
		}
		
		// Metodo que toma la Potencia de la frecuencia 27
		public double getarfcn26dbm()
		{
			return this.arfcn26dbm;
		}
		
		// Metodo que toma la frecuencia 28
		public int getarfcn27()
		{
			return this.arfcn27;
		}
		
		// Metodo que toma la Potencia de la frecuencia 28
		public double getarfcn27dbm()
		{
			return this.arfcn27dbm;
		}

		// Metodo que toma la frecuencia 29
//		public int getarfcn28()
//		{
//			return this.arfcn28;
//		}
		
		// Metodo que toma la Potencia de la frecuencia 29	
//		public double getarfcn28dbm()
//		{
//			return this.arfcn28dbm;
//		}
		
		// Metodo que toma la frecuencia 30	
//		public int getarfcn29()
//		{
//			return this.arfcn29;
//		}
		
		// Metodo que toma la Potencia de la frecuencia 30	
//		public double getarfcn29dbm()
//		{
//			return this.arfcn29dbm;
//		}
		
		// Metodo que toma la frecuencia 31	
//		public int getarfcn30()
//		{
//			return this.arfcn30;
//		}
		
		// Metodo que toma la Potencia de la frecuencia 31
//		public double getarfcn30dbm()
//		{
//			return this.arfcn30dbm;
//		}

		// Metodo que toma la frecuencia 32	
//		public int getarfcn31()
//		{
//			return this.arfcn31;
//		}
		
		// Metodo que toma la Potencia de la frecuencia 32
//		public double getarfcn31dbm()
//		{
//			return this.arfcn31dbm;
//		}				

//		... Si se desea actualizar este software a mayor cantidad de frecuencias


		//***************************************************************************//
		// NOTA 5:			                                                         //
		// En caso de querer hacer una expansion del programa a mayor cantidad de    //
		// frecuencias, se deben crear mas funciones get. Ej: Si se desea aumentar a //
		// 1 frecuencia mas se debe agregar la funcion getarfcn28 y la funcion       //
		// getarfcn28dbm                                                             //
		//***************************************************************************// 
	
	
		// Metodo que toma el valor de contador
		public int getzaehler()
		{
			return this.zaehler;
		}
		
		// Metodo que toma el valor de la distancia entre coordenadas		
		public double getcoordistance()
		{
			return this.coordistance;
		}
		
		// Metodo que toma el valor de la ruta total		
		public double gettotalroute()
		{
			return this.totalroute;
		}
		
		// Metodo que toma el valor del numero de segmento
		public int getsegmentnumber()
		{
			return this.segmentnumber;
		}
		
		// Metodo que toma el valor de la frecuencia que posee la mayor potencia
		public int getmaxarfcn()
		{
			return this.maxarfcn;
		}
		
		// Metodo que toma el valor de la maxima Potencia		
		public double getmaxarfcndbm()
		{
			return this.maxarfcndbm;
		}
		
		// Metodo que toma el nodo siguiente
		public  rawsamplenode getnext()
		{
			return this.next;
		}
		
		// Metodo que toma el nodo previo		
		public  rawsamplenode getprevious()
		{
			return this.previous;
		}
		

		// Metodo que coloca el valor del nodo siguiente
		public void setlinknext(rawsamplenode siguiente)
		{
			this.next=siguiente;
		}
		
		// Metodo que coloca el valor del nodo anterior		
		public void setlinkprevious(rawsamplenode anterior)
		{
			this.previous=anterior;
		}
		
		// Metodo que coloca el valor de la velocidad		
		public void setspeed(int speed)
		{
			this.speed=speed;
		}


	
	//********************************************************************************//
	// En caso de querer realizar una actualizacion de software ya que aumentaron     //
	// la cantidad de frecuencias en el post-proceso, se deben incluir mayor cantidad //
	// de metodos setarfnXX		                                                      //
	//********************************************************************************// 
	
	
		
		// Funcion que coloca el valor la frecuencia 1
		public void setarfcn00(int value)
		{
			this.arfcn00=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 1		
		public void setarfcn00dbm(double value)
		{
			this.arfcn00dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 2	
		public void setarfcn01(int value)
		{
			this.arfcn01=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 2	
		public void setarfcn01dbm(double value)
		{
			this.arfcn01dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 3
		public void setarfcn02(int value)
		{
			this.arfcn02=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 3	
		public void setarfcn02dbm(double value)
		{
			this.arfcn02dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 4
		public void setarfcn03(int value)
		{
			this.arfcn03=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 4
		public void setarfcn03dbm(double value)
		{
			this.arfcn03dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 5
		public void setarfcn04(int value)
		{
			this.arfcn04=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 5
		public void setarfcn04dbm(double value)
		{
			this.arfcn04dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 6
		public void setarfcn05(int value)
		{
			this.arfcn05=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 6
		public void setarfcn05dbm(double value)
		{
			this.arfcn05dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 7
		public void setarfcn06(int value)
		{
			this.arfcn06=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 7
		public void setarfcn06dbm(double value)
		{
			this.arfcn06dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 8
		public void setarfcn07(int value)
		{
			this.arfcn07=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 8
		public void setarfcn07dbm(double value)
		{
			this.arfcn07dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 9
		public void setarfcn08(int value)
		{
			this.arfcn08=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 9
		public void setarfcn08dbm(double value)
		{
			this.arfcn08dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 10
		public void setarfcn09(int value)
		{
			this.arfcn09=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 10
		public void setarfcn09dbm(double value)
		{
			this.arfcn09dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 11
		public void setarfcn10(int value)
		{
			this.arfcn10=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 11
		public void setarfcn10dbm(double value)
		{
			this.arfcn10dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 12
		public void setarfcn11(int value)
		{
			this.arfcn11=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 12
		public void setarfcn11dbm(double value)
		{
			this.arfcn11dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 13
		public void setarfcn12(int value)
		{
			this.arfcn12=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 13
		public void setarfcn12dbm(double value)
		{
			this.arfcn12dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 14
		public void setarfcn13(int value)
		{
			this.arfcn13=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 14
		public void setarfcn13dbm(double value)
		{
			this.arfcn13dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 15
		public void setarfcn14(int value)
		{
			this.arfcn14=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 15
		public void setarfcn14dbm(double value)
		{
			this.arfcn14dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 16
		public void setarfcn15(int value)
		{
			this.arfcn15=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 16
		public void setarfcn15dbm(double value)
		{
			this.arfcn15dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 17
		public void setarfcn16(int value)
		{
			this.arfcn16=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 17
		public void setarfcn16dbm(double value)
		{
			this.arfcn16dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 18
		public void setarfcn17(int value)
		{
			this.arfcn17=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 18
		public void setarfcn17dbm(double value)
		{
			this.arfcn17dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 19
		public void setarfcn18(int value)
		{
			this.arfcn18=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 19
		public void setarfcn18dbm(double value)
		{
			this.arfcn18dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 20
		public void setarfcn19(int value)
		{
			this.arfcn19=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 20
		public void setarfcn19dbm(double value)
		{
			this.arfcn19dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 21
		public void setarfcn20(int value)
		{
			this.arfcn20=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 21
		public void setarfcn20dbm(double value)
		{
			this.arfcn20dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 22
		public void setarfcn21(int value)
		{
			this.arfcn21=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 22
		public void setarfcn21dbm(double value)
		{
			this.arfcn21dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 23
		public void setarfcn22(int value)
		{
			this.arfcn22=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 23
		public void setarfcn22dbm(double value)
		{
			this.arfcn22dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 24
		public void setarfcn23(int value)
		{
			this.arfcn23=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 24
		public void setarfcn23dbm(double value)
		{
			this.arfcn23dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 25
		public void setarfcn24(int value)
		{
			this.arfcn24=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 25
		public void setarfcn24dbm(double value)
		{
			this.arfcn24dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 26
		public void setarfcn25(int value)
		{
			this.arfcn25=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 26
		public void setarfcn25dbm(double value)
		{
			this.arfcn25dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 27
		public void setarfcn26(int value)
		{
			this.arfcn26=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 27
		public void setarfcn26dbm(double value)
		{
			this.arfcn26dbm=value;
		}
		
		// Funcion que coloca el valor la frecuencia 28
		public void setarfcn27(int value)
		{
			this.arfcn27=value;
		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 28
		public void setarfcn27dbm(double value)
		{
			this.arfcn27dbm=value;
		}		
	
		// Funcion que coloca el valor la frecuencia 29
//		public void setarfcn28(int value)
//		{
//			this.arfcn28=value;
//		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 29	
//		public void setarfcn28dbm(double value)
//		{
//			this.arfcn28dbm=value;
//		}
		
		// Funcion que coloca el valor la frecuencia 30
//		public void setarfcn29(int value)
//		{
//			this.arfcn29=value;
//		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 30	
//		public void setarfcn29dbm(double value)
//		{
//			this.arfcn29dbm=value;
//		}
		
		// Funcion que coloca el valor la frecuencia 31
//		public void setarfcn30(int value)
//		{
//			this.arfcn30=value;
//		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 31
//		public void setarfcn30dbm(double value)
//		{
//			this.arfcn30dbm=value;
//		}

		// Funcion que coloca el valor la frecuencia 32
//		public void setarfcn31(int value)
//		{
//			this.arfcn31=value;
//		}
		
		// Funcion que coloca el valor de la Potencia de la frecuencia 32
//		public void setarfcn31dbm(double value)
//		{
//			this.arfcn31dbm=value;
//		}				

//		... Si se desea actualizar este software a mayor cantidad de frecuencias


		//***************************************************************************//
		// NOTA 6:	        		                                                 //
		// En caso de querer hacer una expansion del programa a mayor cantidad de    //
		// frecuencias, se deben crear mas funciones get. Ej: Si se desea aumentar a //
		// 1 frecuencia mas se debe agregar la funcion setarfcn28 y la funcion       //
		// setarfcn28dbm                                                             //
		//***************************************************************************// 

		// Funcion que coloca el valor de contador
		public void setzaehler(int value)
		{
			this.zaehler=value;
		}
		
		// Funcion que coloca el valor de la frecuencia que posee mayor Potencia 
		public void setmaxarfcn(int value)
		{
			this.maxarfcn=value;
		}
		
		// Funcion que coloca el valor maximo de Potencia 		
		public void setmaxarfcndbm(double value)
		{
			this.maxarfcndbm=value;
		}
				

		// Funciones para colocar valores a los nodos dentro de la lista
		
		//************************************************************************//
		// Nota 7: En caso de querer ampliar software a mayor cantidad de valores //
		// de frecuncia se deben añadir argumentos respectivos al public void     //
		// setnode. Ej: se desea añadir una frecuencia mas, los argumentos deben  //
		// deben de tener un int arfcnxx y un double arfcnxxdbm mas dentro de los //
		// argumentos siguiendo el orden correspondiente                          //
		//************************************************************************// 
		
	public void setnode (String zeit,double latitude, double longitude, int speed,
	int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,
	int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,
	int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,
	int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
	int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,
	int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,
	int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,
	int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,
	int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,
	int arfcn27,double arfcn27dbm, //int arfcn28,double arfcn28dbm,int arfcn29,double arfcn29dbm,
	//int arfcn30,double arfcn30dbm,int arfcn31,double arfcn31dbm,
		rawsamplenode siguiente, rawsamplenode anterior)
		{

			this.zeit=zeit;
			this.latitude=latitude;
			this.longitude=longitude;
			this.speed=speed;
			this.arfcn00=arfcn00;
			this.arfcn00dbm=arfcn00dbm;
			this.arfcn01=arfcn01;
			this.arfcn01dbm=arfcn01dbm;
			this.arfcn02=arfcn02;
			this.arfcn02dbm=arfcn02dbm;
			this.arfcn03=arfcn03;
			this.arfcn03dbm=arfcn03dbm;
			this.arfcn04=arfcn04;
			this.arfcn04dbm=arfcn04dbm;
			this.arfcn05=arfcn05;
			this.arfcn05dbm=arfcn05dbm;
			this.arfcn06=arfcn06;
			this.arfcn06dbm=arfcn06dbm;
			this.arfcn07=arfcn07;
			this.arfcn07dbm=arfcn07dbm;
			this.arfcn08=arfcn08;
			this.arfcn08dbm=arfcn08dbm;
			this.arfcn09=arfcn09;
			this.arfcn09dbm=arfcn09dbm;
			this.arfcn10=arfcn10;
			this.arfcn10dbm=arfcn10dbm;
			this.arfcn11=arfcn11;
			this.arfcn11dbm=arfcn11dbm;
			this.arfcn12=arfcn12;
			this.arfcn12dbm=arfcn12dbm;
			this.arfcn13=arfcn13;
			this.arfcn13dbm=arfcn13dbm;
			this.arfcn14=arfcn14;
			this.arfcn14dbm=arfcn14dbm;
			this.arfcn15=arfcn15;
			this.arfcn15dbm=arfcn15dbm;
			this.arfcn16=arfcn16;
			this.arfcn16dbm=arfcn16dbm;
			this.arfcn17=arfcn17;
			this.arfcn17dbm=arfcn17dbm;
			this.arfcn18=arfcn18;
			this.arfcn18dbm=arfcn18dbm;
			this.arfcn19=arfcn19;
			this.arfcn19dbm=arfcn19dbm;
			this.arfcn20=arfcn20;
			this.arfcn20dbm=arfcn20dbm;
			this.arfcn21=arfcn21;
			this.arfcn21dbm=arfcn21dbm;
			this.arfcn22=arfcn22;
			this.arfcn22dbm=arfcn22dbm;
			this.arfcn23=arfcn23;
			this.arfcn23dbm=arfcn23dbm;
			this.arfcn24=arfcn24;
			this.arfcn24dbm=arfcn24dbm;
			this.arfcn25=arfcn25;
			this.arfcn25dbm=arfcn25dbm;
			this.arfcn26=arfcn26;
			this.arfcn26dbm=arfcn26dbm;
			this.arfcn27=arfcn27;
			this.arfcn27dbm=arfcn27dbm;
//			this.arfcn28=arfcn28;
//			this.arfcn28dbm=arfcn28dbm;
//			this.arfcn29=arfcn29;
//			this.arfcn29dbm=arfcn29dbm;
//			this.arfcn30=arfcn30;
//			this.arfcn30dbm=arfcn30dbm;
//			this.arfcn31=arfcn31;
//			this.arfcn31dbm=arfcn31dbm;
//			... En caso de que se quieran añadir mas frecuencias


//--------------------------------------------------------------------------------------//
//	NOTA 8: Si se desea realizar un cambio en el software por ampliacion de cantidad de //
//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
//  frecuencias: Se deben crear las variables this.arfcn28=arfcn28,this.arfcndbm28=     //
//  arfcn28dbm,...,this.arfcn30=arfcn30,this.arfcndbm30=arfcn30dbm                      //
//--------------------------------------------------------------------------------------// 


			this.maxarfcn=0;
			this.maxarfcndbm=0.0;

			this.next=siguiente;
			this.previous=anterior;
		}
		
		
		//************************************************************************//
		// Nota 9: En caso de querer ampliar software a mayor cantidad de valores //
		// de frecuncia se deben añadir argumentos respectivos al public void     //
		// setnode1. Ej: se desea añadir una frecuencia mas, los argumentos deben //
		// deben de tener un int arfcnxx y un double arfcnxxdbm mas dentro de los //
		// argumentos siguiendo el orden correspondiente                          //
		//************************************************************************// 
				
	public void setnode1 (String zeit,double latitude, double longitude, int speed,
	int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,
	int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,
	int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,
	int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
	int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,
	int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,
	int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,
	int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,
	int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,
	int arfcn27,double arfcn27dbm //,int arfcn28,double arfcn28dbm,int arfcn29,double arfcn29dbm,
	//int arfcn30,double arfcn30dbm,int arfcn31,double arfcn31dbm,
	)
				{

			this.zeit=zeit;
			this.latitude=latitude;
			this.longitude=longitude;
			this.speed=speed;
			this.arfcn00=arfcn00;
			this.arfcn00dbm=arfcn00dbm;
			this.arfcn01=arfcn01;
			this.arfcn01dbm=arfcn01dbm;
			this.arfcn02=arfcn02;
			this.arfcn02dbm=arfcn02dbm;
			this.arfcn03=arfcn03;
			this.arfcn03dbm=arfcn03dbm;
			this.arfcn04=arfcn04;
			this.arfcn04dbm=arfcn04dbm;
			this.arfcn05=arfcn05;
			this.arfcn05dbm=arfcn05dbm;
			this.arfcn06=arfcn06;
			this.arfcn06dbm=arfcn06dbm;
			this.arfcn07=arfcn07;
			this.arfcn07dbm=arfcn07dbm;
			this.arfcn08=arfcn08;
			this.arfcn08dbm=arfcn08dbm;
			this.arfcn09=arfcn09;
			this.arfcn09dbm=arfcn09dbm;
			this.arfcn10=arfcn10;
			this.arfcn10dbm=arfcn10dbm;
			this.arfcn11=arfcn11;
			this.arfcn11dbm=arfcn11dbm;
			this.arfcn12=arfcn12;
			this.arfcn12dbm=arfcn12dbm;
			this.arfcn13=arfcn13;
			this.arfcn13dbm=arfcn13dbm;
			this.arfcn14=arfcn14;
			this.arfcn14dbm=arfcn14dbm;
			this.arfcn15=arfcn15;
			this.arfcn15dbm=arfcn15dbm;
			this.arfcn16=arfcn16;
			this.arfcn16dbm=arfcn16dbm;
			this.arfcn17=arfcn17;
			this.arfcn17dbm=arfcn17dbm;
			this.arfcn18=arfcn18;
			this.arfcn18dbm=arfcn18dbm;
			this.arfcn19=arfcn19;
			this.arfcn19dbm=arfcn19dbm;
			this.arfcn20=arfcn20;
			this.arfcn20dbm=arfcn20dbm;
			this.arfcn21=arfcn21;
			this.arfcn21dbm=arfcn21dbm;
			this.arfcn22=arfcn22;
			this.arfcn22dbm=arfcn22dbm;
			this.arfcn23=arfcn23;
			this.arfcn23dbm=arfcn23dbm;
			this.arfcn24=arfcn24;
			this.arfcn24dbm=arfcn24dbm;
			this.arfcn25=arfcn25;
			this.arfcn25dbm=arfcn25dbm;
			this.arfcn26=arfcn26;
			this.arfcn26dbm=arfcn26dbm;
			this.arfcn27=arfcn27;
			this.arfcn27dbm=arfcn27dbm;
//			this.arfcn28=arfcn28;
//			this.arfcn28dbm=arfcn28dbm;
//			this.arfcn29=arfcn29;
//			this.arfcn29dbm=arfcn29dbm;
//			this.arfcn30=arfcn30;
//			this.arfcn30dbm=arfcn30dbm;
//			this.arfcn31=arfcn31;
//			this.arfcn31dbm=arfcn31dbm;
//			... En caso de que se quieran añadir mas frecuencias


//--------------------------------------------------------------------------------------//
//	NOTA 10: Si se desea realizar un cambio en el software por ampliacion de cantidad de//
//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
//  frecuencias: Se deben crear las variables this.arfcn28=arfcn28,this.arfcndbm28=     //
//  arfcn28dbm,...,this.arfcn30=arfcn30,this.arfcndbm30=arfcn30dbm                      //
//--------------------------------------------------------------------------------------// 										
					this.maxarfcn=0;
					this.maxarfcndbm=0.0;
		}


		//************************************************************************//
		// Nota 11: En caso de querer ampliar software a mayor cantidad de valores//
		// de frecuencia se deben añadir argumentos respectivos al public void    //
		// setnode2. Ej: se desea añadir una frecuencia mas, los argumentos deben //
		// deben de tener un int arfcnxx y un double arfcnxxdbm mas dentro de los //
		// argumentos siguiendo el orden correspondiente                          //
		//************************************************************************// 

	public void setnode2 (int segmentnumber, int maxarfcn, double maxarfcndbm, 
	double latitude, double longitude, int speed,double totalroute,
	int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,
	int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,
	int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,
	int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
	int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,
	int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,
	int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,
	int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,
	int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,
	int arfcn27,double arfcn27dbm,//,int arfcn28,double arfcn28dbm,int arfcn29,double arfcn29dbm,
	//int arfcn30,double arfcn30dbm,int arfcn31,double arfcn31dbm,
	rawsamplenode siguiente, rawsamplenode anterior)
		{

			this.segmentnumber=segmentnumber;
			this.maxarfcn=maxarfcn;
			this.maxarfcndbm=maxarfcndbm;
			this.zaehler=0;
			this.totalroute=totalroute;


			this.latitude=latitude;
			this.longitude=longitude;
			this.speed=speed;
			this.arfcn00=arfcn00;
			this.arfcn00dbm=arfcn00dbm;
			this.arfcn01=arfcn01;
			this.arfcn01dbm=arfcn01dbm;
			this.arfcn02=arfcn02;
			this.arfcn02dbm=arfcn02dbm;
			this.arfcn03=arfcn03;
			this.arfcn03dbm=arfcn03dbm;
			this.arfcn04=arfcn04;
			this.arfcn04dbm=arfcn04dbm;
			this.arfcn05=arfcn05;
			this.arfcn05dbm=arfcn05dbm;
			this.arfcn06=arfcn06;
			this.arfcn06dbm=arfcn06dbm;
			this.arfcn07=arfcn07;
			this.arfcn07dbm=arfcn07dbm;
			this.arfcn08=arfcn08;
			this.arfcn08dbm=arfcn08dbm;
			this.arfcn09=arfcn09;
			this.arfcn09dbm=arfcn09dbm;
			this.arfcn10=arfcn10;
			this.arfcn10dbm=arfcn10dbm;
			this.arfcn11=arfcn11;
			this.arfcn11dbm=arfcn11dbm;
			this.arfcn12=arfcn12;
			this.arfcn12dbm=arfcn12dbm;
			this.arfcn13=arfcn13;
			this.arfcn13dbm=arfcn13dbm;
			this.arfcn14=arfcn14;
			this.arfcn14dbm=arfcn14dbm;
			this.arfcn15=arfcn15;
			this.arfcn15dbm=arfcn15dbm;
			this.arfcn16=arfcn16;
			this.arfcn16dbm=arfcn16dbm;
			this.arfcn17=arfcn17;
			this.arfcn17dbm=arfcn17dbm;
			this.arfcn18=arfcn18;
			this.arfcn18dbm=arfcn18dbm;
			this.arfcn19=arfcn19;
			this.arfcn19dbm=arfcn19dbm;
			this.arfcn20=arfcn20;
			this.arfcn20dbm=arfcn20dbm;
			this.arfcn21=arfcn21;
			this.arfcn21dbm=arfcn21dbm;
			this.arfcn22=arfcn22;
			this.arfcn22dbm=arfcn22dbm;
			this.arfcn23=arfcn23;
			this.arfcn23dbm=arfcn23dbm;
			this.arfcn24=arfcn24;
			this.arfcn24dbm=arfcn24dbm;
			this.arfcn25=arfcn25;
			this.arfcn25dbm=arfcn25dbm;
			this.arfcn26=arfcn26;
			this.arfcn26dbm=arfcn26dbm;
			this.arfcn27=arfcn27;
			this.arfcn27dbm=arfcn27dbm;
//			this.arfcn28=arfcn28;
//			this.arfcn28dbm=arfcn28dbm;
//			this.arfcn29=arfcn29;
//			this.arfcn29dbm=arfcn29dbm;
//			this.arfcn30=arfcn30;
//			this.arfcn30dbm=arfcn30dbm;
//			this.arfcn31=arfcn31;
//			this.arfcn31dbm=arfcn31dbm;
//			... En caso de que se quieran añadir mas frecuencias


//--------------------------------------------------------------------------------------//
//	NOTA 12: Si se desea realizar un cambio en el software por ampliacion de cantidad de//
//	numero de frecuencias, se deben declarar mayor cantidad de variables arfcn y        //
//  arfcndbm. Por ejemplo se desea aumentar este programa a una cantidad de 31          //
//  frecuencias: Se deben crear las variables this.arfcn28=arfcn28,this.arfcndbm28=     //
//  arfcn28dbm,...,this.arfcn30=arfcn30,this.arfcndbm30=arfcn30dbm                      //
//--------------------------------------------------------------------------------------//

			this.maxarfcn=0;
			this.maxarfcndbm=0.0;

			this.next=siguiente;
			this.previous=anterior;
		}

		public void setcalculus (double coordistance, double totalroute, int segmentnumber)
			{
				this.coordistance=coordistance;
				this.totalroute=totalroute;
				this.segmentnumber=segmentnumber;
			}
		public void setdistance (double distance)
			{
				this.coordistance=distance;
			}
		public void settotalroute (double totalroute)
			{
				this.totalroute=totalroute;
			}
		public void setsegmentnumber (int segmentnumber)
			{
				this.segmentnumber=segmentnumber;
			}
		public void setlatitude (double latitude)
			{
				this.latitude=latitude;
			}
		public void setlongitude (double longitude)
			{
				this.longitude=longitude;
			}
}

// Fin de la clase Rawsamplenode. Esta clase 12 notas para poder modificar el software
// para aumentar el numero de frecuencias



class verketteteliste
{
	rawsamplenode head; // Variable head de tipo rawsamplenode

	// Constructor
	public verketteteliste()
	{
		this.head=null; // Inicializo valor de head, nulo
	}


	// Metodo que inicializa e inserta el primer elemento 
	public void insertelement(String zeit,double latitude, double longitude, 
	rawsamplenode siguiente, rawsamplenode anterior)
		
	{
		rawsamplenode newnode = new rawsamplenode(zeit,latitude,longitude,siguiente, anterior );
		this.head = newnode;
	}

	// Metodo para borrar primer elemento de la lista
	public void deletefirst()
	{
		// trace1 = head  (objetos de tipo Rawsamplenode)Toma el valor de head de tipo
		// rawsamplenode inicial
		rawsamplenode tracer1 = this.head;	
			
		// this.head es igual al siguiente tracer1 
		this.head=tracer1.getnext();	
			
		// hace null tracer1 (Borra tracer1) 
		// Borra el nodo inicial		
		tracer1=null;						
	}

		//************************************************************************//
		// Nota 13: En caso de querer ampliar software a mayor cantidad de valores//
		// de frecuencia se deben añadir argumentos respectivos al public void    //
		// insertend. Ej: se desea añadir una frecuencia mas, los argumentos deben//
		// deben de tener un int arfcnxx y un double arfcnxxdbm mas dentro de los //
		// argumentos siguiendo el orden correspondiente                          //
		//************************************************************************// 

	//Metodo que inserta un elemento al final de la lista
	public void insertend (String zeit,double latitude, double longitude, int speed,
	int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,
	int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,
	int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,
	int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
	int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,
	int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,
	int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,
	int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,
	int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,
	int arfcn27,double arfcn27dbm,//,int arfcn28,double arfcn28dbm,int arfcn29,double arfcn29dbm,
	//int arfcn30,double arfcn30dbm,int arfcn31,double arfcn31dbm,
	int segmentdimension)
	{
		// Creo un nuevo objeto de clase rawsamplenode de constructor (zeit, lat ,long)
		// inicializo valores de arfcn[0-27] y arfcn[0-27]dbm y otros
		rawsamplenode nuevo = new rawsamplenode(zeit, latitude,  longitude);
		
		//*********************************************************************//
		// NOTA 14: En caso de querer realizar una ampliacion de numero de     //
		// de frecuencias en este software, se debe incrementar el numero de   //
		// argumentos de entrada en nuevo.setnode                              //
		//*********************************************************************//
		     
		// Seteo un nuevo nodo con los valores actuales
		// Variable nuevo es un objeto de tipo Rawsamplenode
		nuevo.setnode( zeit, latitude,  longitude,  speed, 
			           arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, 
			           arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, 
			           arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, 
			           arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm, 
			           arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, 
			           arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, 
			           arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, 
			           arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, 
			           arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, 
			           arfcn27, arfcn27dbm, //arfcn28, arfcn28dbm,arfcn29, arfcn29dbm,
					   // arfcn30, arfcn30dbm, arfcn31, arfcn31dbm,
			           null,null);

		// Creo tracer1 de clase rawsamplenode
		rawsamplenode tracer1 = this.head;
		// Inicializo contador
		int counter=0;
		
				// Si el valor siguiente tomado de Tracer1 es igual a nulo
				if(tracer1.getnext()==null)	
					{
								// El link siguiente de tracer1 apunta a nuevo
								tracer1.setlinknext(nuevo);
								// El link previo de nuevo apunta a tracer1					
								nuevo.setlinkprevious(tracer1);
								// El link siguiente de nuevo apunta a nulo 			
								nuevo.setlinknext(null);
								// Setea Latitud con el valor de latitud de objeto nuevo		
								tracer1.setlatitude(nuevo.getlatitude());
								// Setea Longitud con el valor de longitud de objeto nuevo
								tracer1.setlongitude(nuevo.getlongitude());	
								tracer1.setsegmentnumber(0);				
								tracer1.setdistance(0);				
								tracer1.settotalroute(0);
								nuevo.setsegmentnumber(0);
								nuevo.setdistance(0);
								nuevo.settotalroute(0);
					}
				else	//  sino
					{
								do
								{
									tracer1=tracer1.getnext();	// Busca siguiente nodo
								}while (tracer1.getnext()!=null);	// sale del ciclo hasta que nodo
																	// siguiente sea nulo 
								// Se calcula la distancia recorrida
								// Se revisa la distancia recorrido para ver si existe 
								// otro segmento.
								nodedistancecheck(tracer1,nuevo,segmentdimension);
					}
	}

	// Metodo que recibe tracer1, nuevo y tamaño de segmento (en este caso 10 metros)
	// Metodo para revisar la distancia entre nodos.
	// En este metodo se analiza las distancias existentes entre un nodo y otro, cuando se 
	// llega al limite del tamaño del segmento, se crea el resumen
	public void nodedistancecheck(rawsamplenode tracer1, rawsamplenode nuevo, int segmentdimension)
	{
		int i=0;

		int newsegmentnumber;
		
		double si;
		// En var segmentid obtengo el numero de segmento de tracer1		
		int segmentid	=tracer1.getsegmentnumber();
		// Obtengo el valor de LAT de tracer1 y almaceno  en var latp		
		double latp		=tracer1.getlatitude(); 
		// Obtengo el valor de LAT de nuevo y almaceno en var lat	
		double lat		=nuevo.getlatitude();	
		// Obtengo el valor de LON de tracer1 y almaceno en var longip	
		double longip	=tracer1.getlongitude();
		// Obtengo el valor de LON de nuevo y lo almaceno en var longi 
		double longi	=nuevo.getlongitude();	
		double distance;

		// En el caso de que posicion de tracer1 sea igual a posicion de nuevo
		if(latp==lat&&longip==longi)
				{ 	distance=0.0;}		// La distancia recorrida es cero, el dispositivo se encuentra en el mismo sitio
				
		// En el caso de que posicion de tracer1 sea diferente a posicion de nuevo entonces
		// calcular distancia
		else
				{
				// Formula matematica para calcular distancia entre dos coordenadas geograficas.
				 distance	=1000*(6378*Math.acos((Math.cos((longip*Math.PI/180)-(longi*Math.PI/180))*Math.cos(lat*Math.PI/180))*Math.cos(latp*Math.PI/180)+Math.sin(lat*Math.PI/180)*Math.sin(latp*Math.PI/180)));;
				}
		// Obtengo el valor actual de la ruta		
		double totaldrivenalt=tracer1.gettotalroute();	
		// Al valor actual de la ruta le sumo la nueva distancia entre tracer1 y nuevo
		double totaldrivennew=distance+totaldrivenalt;	

		// En si obtengo total manejado / tamaño de segmento (10m en este caso)	
		si=(totaldrivennew/segmentdimension);

			// En newsegmentnumber almaceno la parte entera de si
			// que representa el numero de segmento en que me encuentro
			newsegmentnumber=(int)si;
			
				// En el caso de que segmentid sea a igual a newsegmentnumber
				if(segmentid-newsegmentnumber==0)
					{
						// Hacer un resumen de nodos
						// valor de nodo previo es tracer1, valor de nodo siguiente es nuevo
						// Se crea el resumen del nodo.
						summarizenodes(tracer1,nuevo,totaldrivennew, distance);
					}
					
				// Sino hacer
				else
					{
						// Inicializo valores: el link siguiente de nodo tracer1 apunta a nodo nuevo
						// El nodo nuevo de la clase rawsamplenode inicializa valores
						tracer1.setlinknext(nuevo);
						nuevo.setlinkprevious(tracer1);
						nuevo.setsegmentnumber(newsegmentnumber);
						nuevo.setdistance(distance);
						nuevo.settotalroute(totaldrivennew);
						nuevo.setlinknext(null);
					}
	}

	//Summarize nodes. 
	// En este metodo se crea el Resumen de nodo, el cual crea un nuevo segmento dentro de
	// la lista con la informacion necesaria de cada nodo
	public void summarizenodes(rawsamplenode previous, rawsamplenode next, double totaldrivennew,double distance)
	{
		int alteszaehler=1;
		
		// Se toma la muestra anterior para crear el resumen con el valor previo y el 
		// valor siguiente

		previous.setspeed((previous.getspeed()+next.getspeed())/alteszaehler);
		previous.setdistance(distance);
		previous.settotalroute(totaldrivennew);
		previous.setlatitude(next.getlatitude());
		previous.setlongitude(next.getlongitude());

		previous.setarfcn00((previous.getarfcn00()+next.getarfcn00())/alteszaehler);
		previous.setarfcn00dbm((previous.getarfcn00dbm()+next.getarfcn00dbm())/alteszaehler);
		previous.setarfcn01((previous.getarfcn01()+next.getarfcn01())/alteszaehler);
		previous.setarfcn01dbm((previous.getarfcn01dbm()+next.getarfcn01dbm())/alteszaehler);
		previous.setarfcn02((previous.getarfcn02()+next.getarfcn02())/alteszaehler);
		previous.setarfcn02dbm((previous.getarfcn02dbm()+next.getarfcn02dbm())/alteszaehler);
		previous.setarfcn03((previous.getarfcn03()+next.getarfcn03())/alteszaehler);
		previous.setarfcn03dbm((previous.getarfcn03dbm()+next.getarfcn03dbm())/alteszaehler);
		previous.setarfcn04((previous.getarfcn04()+next.getarfcn04())/alteszaehler);
		previous.setarfcn04dbm((previous.getarfcn04dbm()+next.getarfcn04dbm())/alteszaehler);
		previous.setarfcn05((previous.getarfcn05()+next.getarfcn05())/alteszaehler);
		previous.setarfcn05dbm((previous.getarfcn05dbm()+next.getarfcn05dbm())/alteszaehler);
		previous.setarfcn06((previous.getarfcn06()+next.getarfcn06())/alteszaehler);
		previous.setarfcn06dbm((previous.getarfcn06dbm()+next.getarfcn06dbm())/alteszaehler);
		previous.setarfcn07((previous.getarfcn07()+next.getarfcn07())/alteszaehler);
		previous.setarfcn07dbm((previous.getarfcn07dbm()+next.getarfcn07dbm())/alteszaehler);
		previous.setarfcn08((previous.getarfcn08()+next.getarfcn08())/alteszaehler);
		previous.setarfcn08dbm((previous.getarfcn08dbm()+next.getarfcn08dbm())/alteszaehler);
		previous.setarfcn09((previous.getarfcn09()+next.getarfcn09())/alteszaehler);
		previous.setarfcn09dbm((previous.getarfcn09dbm()+next.getarfcn09dbm())/alteszaehler);
		previous.setarfcn10((previous.getarfcn10()+next.getarfcn10())/alteszaehler);
		previous.setarfcn10dbm((previous.getarfcn10dbm()+next.getarfcn10dbm())/alteszaehler);
		previous.setarfcn11((previous.getarfcn11()+next.getarfcn11())/alteszaehler);
		previous.setarfcn11dbm((previous.getarfcn11dbm()+next.getarfcn11dbm())/alteszaehler);
		previous.setarfcn12((previous.getarfcn12()+next.getarfcn12())/alteszaehler);
		previous.setarfcn12dbm((previous.getarfcn12dbm()+next.getarfcn12dbm())/alteszaehler);
		previous.setarfcn13((previous.getarfcn13()+next.getarfcn13())/alteszaehler);
		previous.setarfcn13dbm((previous.getarfcn13dbm()+next.getarfcn13dbm())/alteszaehler);
		previous.setarfcn14((previous.getarfcn14()+next.getarfcn14())/alteszaehler);
		previous.setarfcn14dbm((previous.getarfcn14dbm()+next.getarfcn14dbm())/alteszaehler);
		previous.setarfcn15((previous.getarfcn15()+next.getarfcn15())/alteszaehler);
		previous.setarfcn15dbm((previous.getarfcn15dbm()+next.getarfcn15dbm())/alteszaehler);
		previous.setarfcn16((previous.getarfcn16()+next.getarfcn16())/alteszaehler);
		previous.setarfcn16dbm((previous.getarfcn16dbm()+next.getarfcn16dbm())/alteszaehler);
		previous.setarfcn17((previous.getarfcn17()+next.getarfcn17())/alteszaehler);
		previous.setarfcn17dbm((previous.getarfcn17dbm()+next.getarfcn17dbm())/alteszaehler);
		previous.setarfcn18((previous.getarfcn18()+next.getarfcn18())/alteszaehler);
		previous.setarfcn18dbm((previous.getarfcn18dbm()+next.getarfcn18dbm())/alteszaehler);
		previous.setarfcn19((previous.getarfcn19()+next.getarfcn19())/alteszaehler);
		previous.setarfcn19dbm((previous.getarfcn19dbm()+next.getarfcn19dbm())/alteszaehler);
		previous.setarfcn20((previous.getarfcn20()+next.getarfcn20())/alteszaehler);
		previous.setarfcn20dbm((previous.getarfcn20dbm()+next.getarfcn20dbm())/alteszaehler);
		previous.setarfcn21((previous.getarfcn21()+next.getarfcn21())/alteszaehler);
		previous.setarfcn21dbm((previous.getarfcn21dbm()+next.getarfcn21dbm())/alteszaehler);
		previous.setarfcn22((previous.getarfcn22()+next.getarfcn22())/alteszaehler);
		previous.setarfcn22dbm((previous.getarfcn22dbm()+next.getarfcn22dbm())/alteszaehler);
		previous.setarfcn23((previous.getarfcn23()+next.getarfcn23())/alteszaehler);
		previous.setarfcn23dbm((previous.getarfcn23dbm()+next.getarfcn23dbm())/alteszaehler);
		previous.setarfcn24((previous.getarfcn24()+next.getarfcn24())/alteszaehler);
		previous.setarfcn24dbm((previous.getarfcn24dbm()+next.getarfcn24dbm())/alteszaehler);
		previous.setarfcn25((previous.getarfcn25()+next.getarfcn25())/alteszaehler);
		previous.setarfcn25dbm((previous.getarfcn25dbm()+next.getarfcn25dbm())/alteszaehler);
		previous.setarfcn26((previous.getarfcn26()+next.getarfcn26())/alteszaehler);
		previous.setarfcn26dbm((previous.getarfcn26dbm()+next.getarfcn26dbm())/alteszaehler);
		previous.setarfcn27((previous.getarfcn27()+next.getarfcn27())/alteszaehler);
		previous.setarfcn27dbm((previous.getarfcn27dbm()+next.getarfcn27dbm())/alteszaehler);
//		previous.setarfcn28((previous.getarfcn28()+next.getarfcn28())/alteszaehler);
//		previous.setarfcn28dbm((previous.getarfcn28dbm()+next.getarfcn28dbm())/alteszaehler);
//		previous.setarfcn29((previous.getarfcn29()+next.getarfcn29())/alteszaehler);
//		previous.setarfcn29dbm((previous.getarfcn29dbm()+next.getarfcn29dbm())/alteszaehler);
//		previous.setarfcn30((previous.getarfcn30()+next.getarfcn30())/alteszaehler);
//		previous.setarfcn30dbm((previous.getarfcn30dbm()+next.getarfcn30dbm())/alteszaehler);
//		previous.setarfcn31((previous.getarfcn31()+next.getarfcn31())/alteszaehler);
//		previous.setarfcn31dbm((previous.getarfcn31dbm()+next.getarfcn31dbm())/alteszaehler);
//		... En caso de tener que agregar mas frecuencias
		
		//*********************************************************************//
		// NOTA 15: En caso de querer realizar una ampliacion de numero de     //
		// de frecuencias en este metodo se debe agregar mayor cantidad de     //
		// de previous.setarfcnxx  y previous.setarfcnxxdbm                    //
		//*********************************************************************// 
	
		previous.setzaehler((previous.getzaehler()+next.getzaehler())/alteszaehler);
		
		// Siguiente nodo de resumen es igual a nulo (ya que se encuentra en blanco)
		next=null;	
	}


	// Generate output file with raw data plus distance calculation
	public  void ausgabedatei(String outputpath,String locationname)throws java.io.IOException
	{
		// Campos de datos
		String fullpath = outputpath+"\\"+locationname;
		String outputfilename = fullpath+"\\"+locationname+".txt";

		rawsamplenode tracer1 = this.head;
		String delimiter ="\t";
		int i =0;

		// Dar formato de archivo de salida
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{ 				
				
			fos.println("CONTADOR"+"\t"+"DISTANCE"+"\t"+"SEGMENT"+"\t"+"ROUTE"+"\t"+"Tiempo"+"\t"+"LAT"+"\t"+"LONG"+"\t"+"SPEED"+"\t"+"DIST"+"\t"+"SEG"+"\t"+"TOTAL");
				do //while (tracer1.getnext()!=null)
				{
					fos.print(tracer1.getzaehler());fos.print(delimiter);

					fos.print(tracer1.getcoordistance());fos.print(delimiter);
					fos.print(tracer1.getsegmentnumber());fos.print(delimiter);
					fos.print(tracer1.gettotalroute());fos.print(delimiter);

					fos.print(tracer1.getzeit());fos.print(delimiter);
					fos.print(tracer1.getlatitude());fos.print(delimiter);
					fos.print(tracer1.getlongitude());fos.print(delimiter);
					fos.print(tracer1.getspeed());fos.print(delimiter);

					fos.print(tracer1.getarfcn00());fos.print(delimiter);
					fos.print(tracer1.getarfcn00dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn01());fos.print(delimiter);
					fos.print(tracer1.getarfcn01dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn02());fos.print(delimiter);
					fos.print(tracer1.getarfcn02dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn03());fos.print(delimiter);
					fos.print(tracer1.getarfcn03dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn04());fos.print(delimiter);
					fos.print(tracer1.getarfcn04dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn05());fos.print(delimiter);
					fos.print(tracer1.getarfcn05dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn06());fos.print(delimiter);
					fos.print(tracer1.getarfcn06dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn07());fos.print(delimiter);
					fos.print(tracer1.getarfcn07dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn08());fos.print(delimiter);
					fos.print(tracer1.getarfcn08dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn09());fos.print(delimiter);
					fos.print(tracer1.getarfcn09dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn10());fos.print(delimiter);
					fos.print(tracer1.getarfcn10dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn11());fos.print(delimiter);
					fos.print(tracer1.getarfcn11dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn12());fos.print(delimiter);
					fos.print(tracer1.getarfcn12dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn13());fos.print(delimiter);
					fos.print(tracer1.getarfcn13dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn14());fos.print(delimiter);
					fos.print(tracer1.getarfcn14dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn15());fos.print(delimiter);
					fos.print(tracer1.getarfcn15dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn16());fos.print(delimiter);
					fos.print(tracer1.getarfcn16dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn17());fos.print(delimiter);
					fos.print(tracer1.getarfcn17dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn18());fos.print(delimiter);
					fos.print(tracer1.getarfcn18dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn19());fos.print(delimiter);
					fos.print(tracer1.getarfcn19dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn20());fos.print(delimiter);
					fos.print(tracer1.getarfcn20dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn21());fos.print(delimiter);
					fos.print(tracer1.getarfcn21dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn22());fos.print(delimiter);
					fos.print(tracer1.getarfcn22dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn23());fos.print(delimiter);
					fos.print(tracer1.getarfcn23dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn24());fos.print(delimiter);
					fos.print(tracer1.getarfcn24dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn25());fos.print(delimiter);
					fos.print(tracer1.getarfcn25dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn26());fos.print(delimiter);
					fos.print(tracer1.getarfcn26dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn27());fos.print(delimiter);
					fos.print(tracer1.getarfcn27dbm());fos.println(delimiter);
//					fos.print(tracer1.getarfcn28());fos.print(delimiter);
//					fos.print(tracer1.getarfcn28dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn29());fos.print(delimiter);
//					fos.print(tracer1.getarfcn29dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn30());fos.print(delimiter);
//					fos.print(tracer1.getarfcn30dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn31());fos.print(delimiter);
//					fos.print(tracer1.getarfcn31dbm());fos.println(delimiter); 
//					... En caso de tener que agregar mas frecuencias
						
					//****************************************************************//
					//  NOTA 16: En caso de realizar una actualizacion de software:   //
					//  Para poder generar el archivo con el formato correcto,        //
					//  el ultimo delimiter se debe imprimir con println, ya que esta //
					//  realiza impresion del caracter, ademas inserta un enter al    //
					//  final, por lo que cambia a la linea siguiente. Ademas se      //
					//  deben agregar las lineas de fos.print(tracer1.getarfcnxx)y    //
					//  fos.print(tracer1.getarfcnxxdbm)                              //
					//****************************************************************// 
					
					System.out.println(i+"\n");
					i++;
					
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Error al crear el archivo de salida");
			}
		System.out.println("*************************************");
							//fos.flush();
					fos.close();
	}


// Metodo para borrar nodo

public void deletenode(rawsamplenode tracer1)
{
	tracer1.getprevious().setlinknext(tracer1.getnext());
	tracer1.getnext().setlinkprevious(tracer1.getprevious());
	tracer1.setlinknext(null);
	tracer1.setlinkprevious(null);
	tracer1=null;
}

	// Metodo donde se crean los Archivos de salida con valores promedio
	
	public  void ausgabedateiAVG(String outputpath,String locationname, String locationnumber)throws java.io.IOException
	{
		// Ruta donde se crea el archivo de salida
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;	// Ruta completa
		// Nombre con el que se crea el archivo de salida xxx_internal ericsson.txt
		// Ruta de txt interno
		String outputfilename = fullpath+"\\"+locationname+"_"+locationnumber+"_Internal_Ericsson.txt"; 

		rawsamplenode tracer1 = this.head;
		// Delimitador para insertar un tab
		String delimiter ="\t";
		int i =0;

		// Dar formato de archivo de salida
		boolean success = new File(fullpath).mkdir();
		// Se define al archivo de salida con el nombre outputfilename (Archivo interno de Ericsson)
		File ausgabedatei = new File(outputfilename); 
		// Se define el escritor del archivo con el nombre fos
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		
	if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)	// Si el archivo no existe y se puede escribir archivo de salida
		{ 	// Escribir en archivo de salida
			// Se imprime la primera fila con los nombres de las columnas
				
			//*********************************************************************//
			// NOTA 17: En caso de querer realizar una ampliacion de numero de     //
			// de frecuencias en este metodo se debe agregar mayor cantidad de     //
			// de impresion de columnas para que el archivo generado tenga         //
			// el formato adecuado                                                 //
			//*********************************************************************// 
			
		fos.print("SEGMENT NUMBER"+"\t"+"MAX ARFCN"+"\t"+"MAX AVG Scanned Level"+"\t"+
					"Number of Samples"+"\t"+"LAT"+"\t"+"LONG"+"\t"+" AVG SPEED"+"\t"+"TOTAL ROUTE"+"\t"+
					"ARFCN00"+"\t"+"AVG ARFCN00DBM"+"\t"+"ARFCN01"+"\t"+"AVG ARFCN01DBM"+"\t"+
					"ARFCN02"+"\t"+"AVG ARFCN02DBM"+"\t"+"ARFCN03"+"\t"+"AVG ARFCN03DBM"+"\t"+
					"ARFCN04"+"\t"+"AVG ARFCN04DBM"+"\t"+"ARFCN05"+"\t"+"AVG ARFCN05DBM"+"\t"+
					"ARFCN06"+"\t"+"AVG ARFCN06DBM"+"\t"+"ARFCN07"+"\t"+"AVG ARFCN07DBM"+"\t"+
					"ARFCN08"+"\t"+"AVG ARFCN08DBM"+"\t"+"ARFCN09"+"\t"+"AVG ARFCN09DBM"+"\t"+
					"ARFCN10"+"\t"+"AVG ARFCN10DBM"+"\t"+"ARFCN11"+"\t"+"AVG ARFCN11DBM"+"\t"+
					"ARFCN12"+"\t"+"AVG ARFCN12DBM"+"\t"+"ARFCN13"+"\t"+"AVG ARFCN13DBM"+"\t"+
					"ARFCN14"+"\t"+"AVG ARFCN14DBM"+"\t"+"ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t"+
					"ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t"+"ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t"+
					"ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t"+"ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t"+
					"ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t"+"ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t"+
					"ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t"+"ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t"+
					"ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t"+"ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t"+
					"ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t"+"ARFCN27"+"\t"+"AVG ARFCN27DBM"); 

/*
		if(tracer1.getarfcn15()!=0)
				{
				fos.println("ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t");						
				}		

		if(tracer1.getarfcn16()!=0)
				{
				fos.println("ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn17()!=0)
				{
				fos.println("ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn18()!=0)
				{
				fos.println("ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn19()!=0)
				{
				fos.println("ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t");						
				}																										

		if(tracer1.getarfcn20()!=0)
				{
				fos.println("ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn21()!=0)
				{
				fos.println("ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t");						
				}	

		if(tracer1.getarfcn22()!=0)
				{
				fos.println("ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t");						
				}	

		if(tracer1.getarfcn23()!=0)
				{
				fos.println("ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t");						
				}	

		if(tracer1.getarfcn24()!=0)
				{
				fos.println("ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t");						
				}	

		if(tracer1.getarfcn25()!=0)
				{
				fos.println("ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn26()!=0)
				{
				fos.println("ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t");						
				}	
				
		if(tracer1.getarfcn27()!=0)
				{
				fos.println("ARFCN27"+"\t"+"AVG ARFCN27DBM"+"\t");						
				}
*/					
		fos.print(" ");

				do
				{	// Escribe numero de segmento				y tabulador
					fos.print(tracer1.getsegmentnumber());fos.print(delimiter); 
					fos.print(tracer1.getmaxarfcn());fos.print(delimiter);
					fos.print(tracer1.getmaxarfcndbm());fos.print(delimiter);
					fos.print(tracer1.getzaehler());fos.print(delimiter);
					fos.print(tracer1.getlatitude());fos.print(delimiter);
					fos.print(tracer1.getlongitude());fos.print(delimiter);
					fos.print(tracer1.getspeed());fos.print(delimiter);
					fos.print(tracer1.gettotalroute());fos.print(delimiter);
					fos.print(tracer1.getarfcn00());fos.print(delimiter);
					fos.print(tracer1.getarfcn00dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn01());fos.print(delimiter);
					fos.print(tracer1.getarfcn01dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn02());fos.print(delimiter);
					fos.print(tracer1.getarfcn02dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn03());fos.print(delimiter);
					fos.print(tracer1.getarfcn03dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn04());fos.print(delimiter);
					fos.print(tracer1.getarfcn04dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn05());fos.print(delimiter);
					fos.print(tracer1.getarfcn05dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn06());fos.print(delimiter);
					fos.print(tracer1.getarfcn06dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn07());fos.print(delimiter);
					fos.print(tracer1.getarfcn07dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn08());fos.print(delimiter);
					fos.print(tracer1.getarfcn08dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn09());fos.print(delimiter);
					fos.print(tracer1.getarfcn09dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn10());fos.print(delimiter);
					fos.print(tracer1.getarfcn10dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn11());fos.print(delimiter);
					fos.print(tracer1.getarfcn11dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn12());fos.print(delimiter);
					fos.print(tracer1.getarfcn12dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn13());fos.print(delimiter);
					fos.print(tracer1.getarfcn13dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn14());fos.print(delimiter);
					fos.print(tracer1.getarfcn14dbm());fos.print(delimiter);
										
//				if	(tracer1.getarfcn15()!=0)
//					{					
					fos.print(tracer1.getarfcn15());fos.print(delimiter);
					fos.print(tracer1.getarfcn15dbm());fos.print(delimiter);
//					}						
			
//				if	(tracer1.getarfcn16()!=0)
//					{
					fos.print(tracer1.getarfcn16());fos.print(delimiter);
					fos.print(tracer1.getarfcn16dbm());fos.print(delimiter);						
//					}
			
//				if	(tracer1.getarfcn17()!=0)
//					{
					fos.print(tracer1.getarfcn17());fos.print(delimiter);
					fos.print(tracer1.getarfcn17dbm());fos.print(delimiter);					
//					}										
			
//				if	(tracer1.getarfcn18()!=0)
//					{
					fos.print(tracer1.getarfcn18());fos.print(delimiter);
					fos.print(tracer1.getarfcn18dbm());fos.print(delimiter);			
//					}		
				
//				if	(tracer1.getarfcn19()!=0)
//					{
					fos.print(tracer1.getarfcn19());fos.print(delimiter);
					fos.print(tracer1.getarfcn19dbm());fos.print(delimiter);					
//					}								
				
//				if	(tracer1.getarfcn20()!=0)
//					{
					fos.print(tracer1.getarfcn20());fos.print(delimiter);
					fos.print(tracer1.getarfcn20dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn21()!=0)
//					{
					fos.print(tracer1.getarfcn21());fos.print(delimiter);
					fos.print(tracer1.getarfcn21dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn22()!=0)
//					{
					fos.print(tracer1.getarfcn22());fos.print(delimiter);
					fos.print(tracer1.getarfcn22dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn23()!=0)
//					{
					fos.print(tracer1.getarfcn23());fos.print(delimiter);
					fos.print(tracer1.getarfcn23dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn24()!=0)
//					{
					fos.print(tracer1.getarfcn24());fos.print(delimiter);
					fos.print(tracer1.getarfcn24dbm());fos.print(delimiter);					
//					}						
	
//				if	(tracer1.getarfcn25()!=0)
//					{
					fos.print(tracer1.getarfcn25());fos.print(delimiter);
					fos.print(tracer1.getarfcn25dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn26()!=0)
//					{
					fos.print(tracer1.getarfcn26());fos.print(delimiter);
					fos.print(tracer1.getarfcn26dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn27()!=0)
//					{
					fos.print(tracer1.getarfcn27());fos.print(delimiter);
					fos.print(tracer1.getarfcn27dbm());fos.println(delimiter);					
//					}
					
//					fos.print(tracer1.getarfcn28());fos.print(delimiter);
//					fos.print(tracer1.getarfcn28dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn29());fos.print(delimiter);
//					fos.print(tracer1.getarfcn29dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn30());fos.print(delimiter);
//					fos.print(tracer1.getarfcn30dbm());fos.print(delimiter);
//					fos.print(tracer1.getarfcn31());fos.print(delimiter);
//					fos.print(tracer1.getarfcn31dbm());fos.println(delimiter); 
//					... En caso de tener que agregar mas frecuencias

					// Cambio a la siguiente linea
					fos.println(" ");
						
					//****************************************************************//
					//  NOTA 18: En caso de realizar una actualizacion de software:   //
					//  Para poder generar el archivo con el formato correcto,        //
					//  el ultimo delimiter se debe imprimir con println, ya que esta //
					//  realiza impresion del caracter, ademas inserta un enter al    //
					//  final, por lo que cambia a la linea siguiente. Ademas se      //
					//  deben agregar las lineas de fos.print(tracer1.getarfcnxx)y    //
					//  fos.print(tracer1.getarfcnxxdbm)                              //
					//****************************************************************// 					
					
    		    	System.out.println(i+"\n");	// Se imprime numero de 
					i++;	// Aumento contador en uno
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Error al crear el archivo de salida");
			}
		System.out.println("*************************************");
		
		//fos.flush();
		fos.close();
	}

	//Calcula Promedio
	public void CalculateAVG()
	{
		rawsamplenode tracer1 = this.head;
		
		//System.out.println("LLegue aqui"); // Bandera
		
		// Realizar mientra tracer1 sea diferente de nulo
		while (tracer1.getnext()!=null)

				{
						tracer1.setspeed(tracer1.getspeed()/tracer1.getzaehler());
						
						tracer1.setarfcn00(tracer1.getarfcn00()/tracer1.getzaehler());
						tracer1.setarfcn00dbm(tracer1.getarfcn00dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn01(tracer1.getarfcn01()/tracer1.getzaehler());
						tracer1.setarfcn01dbm(tracer1.getarfcn01dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn02(tracer1.getarfcn02()/tracer1.getzaehler());
						tracer1.setarfcn02dbm(tracer1.getarfcn02dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn03(tracer1.getarfcn03()/tracer1.getzaehler());
						tracer1.setarfcn03dbm(tracer1.getarfcn03dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn04(tracer1.getarfcn04()/tracer1.getzaehler());
						tracer1.setarfcn04dbm(tracer1.getarfcn04dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn05(tracer1.getarfcn05()/tracer1.getzaehler());
						tracer1.setarfcn05dbm(tracer1.getarfcn05dbm()/tracer1.getzaehler());
					
						tracer1.setarfcn06(tracer1.getarfcn06()/tracer1.getzaehler());
						tracer1.setarfcn06dbm(tracer1.getarfcn06dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn07(tracer1.getarfcn07()/tracer1.getzaehler());
						tracer1.setarfcn07dbm(tracer1.getarfcn07dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn08(tracer1.getarfcn08()/tracer1.getzaehler());
						tracer1.setarfcn08dbm(tracer1.getarfcn08dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn09(tracer1.getarfcn09()/tracer1.getzaehler());
						tracer1.setarfcn09dbm(tracer1.getarfcn09dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn10(tracer1.getarfcn10()/tracer1.getzaehler());
						tracer1.setarfcn10dbm(tracer1.getarfcn10dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn11(tracer1.getarfcn11()/tracer1.getzaehler());
						tracer1.setarfcn11dbm(tracer1.getarfcn11dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn12(tracer1.getarfcn12()/tracer1.getzaehler());
						tracer1.setarfcn12dbm(tracer1.getarfcn12dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn13(tracer1.getarfcn13()/tracer1.getzaehler());
						tracer1.setarfcn13dbm(tracer1.getarfcn13dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn14(tracer1.getarfcn14()/tracer1.getzaehler());
						tracer1.setarfcn14dbm(tracer1.getarfcn14dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn15(tracer1.getarfcn15()/tracer1.getzaehler());
						tracer1.setarfcn15dbm(tracer1.getarfcn15dbm()/tracer1.getzaehler());
							
						tracer1.setarfcn16(tracer1.getarfcn16()/tracer1.getzaehler());
						tracer1.setarfcn16dbm(tracer1.getarfcn16dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn17(tracer1.getarfcn17()/tracer1.getzaehler());
						tracer1.setarfcn17dbm(tracer1.getarfcn17dbm()/tracer1.getzaehler());
					
						tracer1.setarfcn18(tracer1.getarfcn18()/tracer1.getzaehler());
						tracer1.setarfcn18dbm(tracer1.getarfcn18dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn19(tracer1.getarfcn19()/tracer1.getzaehler());
						tracer1.setarfcn19dbm(tracer1.getarfcn19dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn20(tracer1.getarfcn20()/tracer1.getzaehler());
						tracer1.setarfcn20dbm(tracer1.getarfcn20dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn21(tracer1.getarfcn21()/tracer1.getzaehler());
						tracer1.setarfcn21dbm(tracer1.getarfcn21dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn22(tracer1.getarfcn22()/tracer1.getzaehler());
						tracer1.setarfcn22dbm(tracer1.getarfcn22dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn23(tracer1.getarfcn23()/tracer1.getzaehler());
						tracer1.setarfcn23dbm(tracer1.getarfcn23dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn24(tracer1.getarfcn24()/tracer1.getzaehler());
						tracer1.setarfcn24dbm(tracer1.getarfcn24dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn25(tracer1.getarfcn25()/tracer1.getzaehler());
						tracer1.setarfcn25dbm(tracer1.getarfcn25dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn26(tracer1.getarfcn26()/tracer1.getzaehler());
						tracer1.setarfcn26dbm(tracer1.getarfcn26dbm()/tracer1.getzaehler());
						
						tracer1.setarfcn27(tracer1.getarfcn27()/tracer1.getzaehler());
						tracer1.setarfcn27dbm(tracer1.getarfcn27dbm()/tracer1.getzaehler());

//						tracer1.setarfcn28(tracer1.getarfcn28()/tracer1.getzaehler());
//						tracer1.setarfcn28dbm(tracer1.getarfcn28dbm()/tracer1.getzaehler());
						
//						tracer1.setarfcn29(tracer1.getarfcn29()/tracer1.getzaehler());
//						tracer1.setarfcn29dbm(tracer1.getarfcn29dbm()/tracer1.getzaehler());
						
//						tracer1.setarfcn30(tracer1.getarfcn30()/tracer1.getzaehler());
//						tracer1.setarfcn30dbm(tracer1.getarfcn30dbm()/tracer1.getzaehler());
						
//						tracer1.setarfcn31(tracer1.getarfcn31()/tracer1.getzaehler());
//						tracer1.setarfcn31dbm(tracer1.getarfcn31dbm()/tracer1.getzaehler());

//						... En caso de tener que agregar mas frecuencias

			//************************************************************************//
			// NOTA 19: En caso de querer realizar una ampliacion de numero de        //
			// de frecuencias en este metodo se debe agregar mayor cantidad de        //
			// de tracer1.setarfcnxx(tracer1.getarfcnxx()/tracer1.getzaehler());      //
			// y  tracer1.setarfcnxxdbm(tracer1.getarfcnxxdbm()/tracer1.getzaehler());//
			//************************************************************************// 

//System.out.println("AVGD "+tracer1.getsegmentnumber()+"\t"+
//tracer1.getarfcn00()+" "+tracer1.getarfcn00dbm()+tracer1.getarfcn01()+" "+tracer1.getarfcn01dbm());
//tracer1=tracer1.getnext();
//String stop;
//stop=Keyin.inString("Parada:");
				}//while (tracer1.getnext()!=null);
	}


//******************************************************************************//
// Metodo que Calcula La Maxima Potencia Promedio de las distintas frecuencias  //
// en cada segmento                                                             //
//******************************************************************************//

public void maxaverage()
{
	int maxarfcn;
	double maxarfcndbm;
	rawsamplenode tracer1 = this.head;

	do
	{
		// En un inicio se define la Frecuencia 0 como la que posee maximo 
		// valor de Potencia de RX

//System.out.println("Valores:"+
//tracer1.getarfcn00()+" "+tracer1.getarfcn00dbm()+"\t"+
//tracer1.getarfcn27()+" "+tracer1.getarfcn27dbm());
//String stop;
//stop=Keyin.inString("Parada:");		
		
		maxarfcn=tracer1.getarfcn00(); 
		maxarfcndbm=tracer1.getarfcn00dbm();

		// En caso de que el valor de Potencia de la Frecuencia 1
		// sea mayor que el valor de la Potencia de la Frecuencia 0 
		if(tracer1.getarfcn01dbm()>maxarfcndbm) 
		{
			// Se define la frecuencia 1 como la que posee maximo 
			// valor de Potencia de RX			
			maxarfcn=tracer1.getarfcn01();
			maxarfcndbm=tracer1.getarfcn01dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 2
		// sea mayor que el valor de la Potencia de la Frecuencia 1 		
		if(tracer1.getarfcn02dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 2 como la que posee maximo 
			// valor de Potencia de RX	
			maxarfcn=tracer1.getarfcn02();
			maxarfcndbm=tracer1.getarfcn02dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 3
		// sea mayor que el valor de la Potencia de la Frecuencia 2 		
		if(tracer1.getarfcn03dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 3 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn03();
			maxarfcndbm=tracer1.getarfcn03dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 4
		// sea mayor que el valor de la Potencia de la Frecuencia 3 		
		if(tracer1.getarfcn04dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 4 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn04();
			maxarfcndbm=tracer1.getarfcn04dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 5
		// sea mayor que el valor de la Potencia de la Frecuencia 4 		
		if(tracer1.getarfcn05dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 5 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn05();
			maxarfcndbm=tracer1.getarfcn05dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 6
		// sea mayor que el valor de la Potencia de la Frecuencia 5 		
		if(tracer1.getarfcn06dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 6 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn06();
			maxarfcndbm=tracer1.getarfcn06dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 7
		// sea mayor que el valor de la Potencia de la Frecuencia 6 		
		if(tracer1.getarfcn07dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 7 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn07();
			maxarfcndbm=tracer1.getarfcn07dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 8
		// sea mayor que el valor de la Potencia de la Frecuencia 7 		
		if(tracer1.getarfcn08dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 8 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn08();
			maxarfcndbm=tracer1.getarfcn08dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 9
		// sea mayor que el valor de la Potencia de la Frecuencia 8 		
		if(tracer1.getarfcn09dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 9 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn09();
			maxarfcndbm=tracer1.getarfcn09dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 10
		// sea mayor que el valor de la Potencia de la Frecuencia 9 		
		if(tracer1.getarfcn10dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 10 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn10();
			maxarfcndbm=tracer1.getarfcn10dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 11
		// sea mayor que el valor de la Potencia de la Frecuencia 10 		
		if(tracer1.getarfcn11dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 11 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn11();
			maxarfcndbm=tracer1.getarfcn11dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 12
		// sea mayor que el valor de la Potencia de la Frecuencia 11 		
		if(tracer1.getarfcn12dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 12 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn12();
			maxarfcndbm=tracer1.getarfcn12dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 13
		// sea mayor que el valor de la Potencia de la Frecuencia 12 		
		if(tracer1.getarfcn13dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 13 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn13();
			maxarfcndbm=tracer1.getarfcn13dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 14
		// sea mayor que el valor de la Potencia de la Frecuencia 13 		
		if(tracer1.getarfcn14dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 14 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn14();
			maxarfcndbm=tracer1.getarfcn14dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 15
		// sea mayor que el valor de la Potencia de la Frecuencia 14 		
		if(tracer1.getarfcn15dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 15 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn15();
			maxarfcndbm=tracer1.getarfcn15dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 16
		// sea mayor que el valor de la Potencia de la Frecuencia 15 		
		if(tracer1.getarfcn16dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 16 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn16();
			maxarfcndbm=tracer1.getarfcn16dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 17
		// sea mayor que el valor de la Potencia de la Frecuencia 16 		
		if(tracer1.getarfcn17dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 17 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn17();
			maxarfcndbm=tracer1.getarfcn17dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 18
		// sea mayor que el valor de la Potencia de la Frecuencia 17 		
		if(tracer1.getarfcn18dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 18 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn18();
			maxarfcndbm=tracer1.getarfcn18dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 19
		// sea mayor que el valor de la Potencia de la Frecuencia 18 		
		if(tracer1.getarfcn19dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 19 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn19();
			maxarfcndbm=tracer1.getarfcn19dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 20
		// sea mayor que el valor de la Potencia de la Frecuencia 19 		
		if(tracer1.getarfcn20dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 20 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn20();
			maxarfcndbm=tracer1.getarfcn20dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 21
		// sea mayor que el valor de la Potencia de la Frecuencia 20 		
		if(tracer1.getarfcn21dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 21 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn21();
			maxarfcndbm=tracer1.getarfcn21dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 22
		// sea mayor que el valor de la Potencia de la Frecuencia 21 		
		if(tracer1.getarfcn22dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 22 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn22();
			maxarfcndbm=tracer1.getarfcn22dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 23
		// sea mayor que el valor de la Potencia de la Frecuencia 22 		
		if(tracer1.getarfcn23dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 23 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn23();
			maxarfcndbm=tracer1.getarfcn23dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 24
		// sea mayor que el valor de la Potencia de la Frecuencia 23 		
		if(tracer1.getarfcn24dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 24 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn24();
			maxarfcndbm=tracer1.getarfcn24dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 25
		// sea mayor que el valor de la Potencia de la Frecuencia 24 		
		if(tracer1.getarfcn25dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 25 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn25();
			maxarfcndbm=tracer1.getarfcn25dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 26
		// sea mayor que el valor de la Potencia de la Frecuencia 25 		
		if(tracer1.getarfcn26dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 26 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn26();
			maxarfcndbm=tracer1.getarfcn26dbm();
		}
		
		// En caso de que el valor de Potencia de la Frecuencia 27
		// sea mayor que el valor de la Potencia de la Frecuencia 26 		
		if(tracer1.getarfcn27dbm()>maxarfcndbm)
		{
			// Se define la frecuencia 27 como la que posee maximo 
			// valor de Potencia de RX				
			maxarfcn=tracer1.getarfcn27();
			maxarfcndbm=tracer1.getarfcn27dbm();
		}

		// En caso de que el valor de Potencia de la Frecuencia 28
		// sea mayor que el valor de la Potencia de la Frecuencia 27 		
//		if(tracer1.getarfcn28dbm()>maxarfcndbm)
//		{
			// Se define la frecuencia 28 como la que posee maximo 
			// valor de Potencia de RX				
//			maxarfcn=tracer1.getarfcn28();
//			maxarfcndbm=tracer1.getarfcn28dbm();
//		}
		
		// En caso de que el valor de Potencia de la Frecuencia 29
		// sea mayor que el valor de la Potencia de la Frecuencia 28 		
//		if(tracer1.getarfcn29dbm()>maxarfcndbm)
//		{
			// Se define la frecuencia 29 como la que posee maximo 
			// valor de Potencia de RX				
//			maxarfcn=tracer1.getarfcn29();
//			maxarfcndbm=tracer1.getarfcn29dbm();
//		}
		
		// En caso de que el valor de Potencia de la Frecuencia 30
		// sea mayor que el valor de la Potencia de la Frecuencia 29 		
//		if(tracer1.getarfcn30dbm()>maxarfcndbm)
//		{
			// Se define la frecuencia 30 como la que posee maximo 
			// valor de Potencia de RX				
//			maxarfcn=tracer1.getarfcn30();
//			maxarfcndbm=tracer1.getarfcn30dbm();
//		}
		
		// En caso de que el valor de Potencia de la Frecuencia 31
		// sea mayor que el valor de la Potencia de la Frecuencia 30 		
//		if(tracer1.getarfcn31dbm()>maxarfcndbm)
//		{
			// Se define la frecuencia 31 como la que posee maximo 
			// valor de Potencia de RX				
//			maxarfcn=tracer1.getarfcn31();
//			maxarfcndbm=tracer1.getarfcn31dbm();
//		}

	//************************************************************************//
	// NOTA 20: En caso de querer realizar una ampliacion de numero de        //
	// de frecuencias en este metodo se debe agregar mayor cantidad de        //
	// de comparaciones if(tracer1.getarfcnxxdbm()>maxarfcndbm)               //
	//************************************************************************// 

		//------------------------------------------------------------------//
		//      Al final se obtienen el valor de la potencia maxima y       // 
		//  Se determina el valor final de la frecuencia que posee maximo   //
		//     valor de Potencia de RX, y el valor de dicha frecuencia		//	
		//------------------------------------------------------------------//
		//String stop;
//		System.out.println("MAX ARFCN:\t"+maxarfcn);
//		System.out.println("MAX ARFCNDBM:\t"+maxarfcndbm);
//		stop=Keyin.inString("Parada:");			
		tracer1.setmaxarfcn(maxarfcn);
		tracer1.setmaxarfcndbm(maxarfcndbm);
		tracer1=tracer1.getnext();

	}while(tracer1.getnext()!=null); // El ciclo se repite hasta encontrar nodo nulo
}


// Metodo que calcula numero de segmentos de la lista
	public int numberofsegments()
	{
		int counter =0;
		rawsamplenode tracer1 = this.head;
		do
		{
			tracer1=tracer1.getnext();
			// Realiza cuenta, la cual posee al final la cantidad total de segmentos de 
			// la lista.
			counter++; 
		}while(tracer1.getnext()!=null); // Realiza ciclo hasta que encuentre valor de 
										 // tracer1 igual a nulo (fin de lista)
		
		// Dicho metodo devuelve el valor de contador
		return counter; 
	}

// Metodo que borra un nodo de la lista aleatoriamente
public  void deleterandom(int numbertodelete)
{
	rawsamplenode tracer1 = this.head;
	for (int counter=0;counter<(numbertodelete+1);counter++){tracer1=tracer1.getnext();};

	tracer1.getprevious().setlinknext(tracer1.getnext());
	tracer1.getnext().setlinkprevious(tracer1.getprevious());
	tracer1=null;
}

public  void ausgabedateiRNDM(String outputpath, String locationname, String locationnumber)throws java.io.IOException
	{
		// Ruta donde se crea el archivo de salida
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		// Nombre con el que se crea el archivo de salida xxx_Random_Segments.txt
		String outputfilename = fullpath+"\\"+locationname+"_"+locationnumber+"_Random_Segments.txt";
		String	pausa;
		rawsamplenode tracer1 = this.head;
		String delimiter ="\t";
		String delimiter2 ="\n";
		int i =0;

		// Dar formato de archivo de salida
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		//boolean success = new File("Pruebasnueva").mkdir();
		//File ausgabedatei = new File("C:\\TEMP\\JV\\v_2_0\\Pruebasnueva\\ListeOUT FINAL.txt");
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		// Si el archivo no existe (en este caso se crea) o el archivo se puede escribir
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true) 
			{ 
				
			//*********************************************************************//
			// NOTA 21: En caso de querer realizar una ampliacion de numero de     //
			// de frecuencias en este metodo se debe agregar mayor cantidad de     //
			// de impresion de columnas para que el archivo generado tenga         //
			// el formato adecuado                                                 //
			//*********************************************************************// 
				
	fos.print("SEGMENT NUMBER"+"\t"+"MAX ARFCN"+"\t"+"MAX Scanned (dBm)"+"\t"+"LATITUDE"+"\t"+
			  "LONGITUDE"+"\t"+" AVG SPEED"+"\t"+"TOTAL ROUTE"+"\t"+
			  "ARFCN00"+"\t"+"AVG ARFCN00DBM"+"\t"+"ARFCN01"+"\t"+"AVG ARFCN01DBM"+"\t"+
			  "ARFCN02"+"\t"+"AVG ARFCN02DBM"+"\t"+"ARFCN03"+"\t"+"AVG ARFCN03DBM"+"\t"+
			  "ARFCN04"+"\t"+"AVG ARFCN04DBM"+"\t"+"ARFCN05"+"\t"+"AVG ARFCN05DBM"+"\t"+
			  "ARFCN06"+"\t"+"AVG ARFCN06DBM"+"\t"+"ARFCN07"+"\t"+"AVG ARFCN07DBM"+"\t"+
			  "ARFCN08"+"\t"+"AVG ARFCN08DBM"+"\t"+"ARFCN09"+"\t"+"AVG ARFCN09DBM"+"\t"+
			  "ARFCN10"+"\t"+"AVG ARFCN10DBM"+"\t"+"ARFCN11"+"\t"+"AVG ARFCN11DBM"+"\t"+
			  "ARFCN12"+"\t"+"AVG ARFCN12DBM"+"\t"+"ARFCN13"+"\t"+"AVG ARFCN13DBM"+"\t"+
			  "ARFCN14"+"\t"+"AVG ARFCN14DBM");/*+"\t"+"ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t"+
			  "ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t"+"ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t"+
			  "ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t"+"ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t"+
			  "ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t"+"ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t"+
			  "ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t"+"ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t"+
			  "ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t"+"ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t"+
			  "ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t"+"ARFCN27"+"\t"+"AVG ARFCN27DBM");
*/
	/*						
						
		if(tracer1.getarfcn15()!=0)
				{
				fos.println("ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t");						
				}		

		if(tracer1.getarfcn16()!=0)
				{
				fos.println("ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn17()!=0)
				{
				fos.println("ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn18()!=0)
				{
				fos.println("ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn19()!=0)
				{
				fos.println("ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t");						
				}																										

		if(tracer1.getarfcn20()!=0)
				{
				fos.println("ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn21()!=0)
				{
				fos.println("ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t");						
				}	

		if(tracer1.getarfcn22()!=0)
				{
				fos.println("ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t");						
				}	

		if(tracer1.getarfcn23()!=0)
				{
				fos.println("ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t");						
				}	

		if(tracer1.getarfcn24()!=0)
				{
				fos.println("ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t");						
				}	

		if(tracer1.getarfcn25()!=0)
				{
				fos.println("ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn26()!=0)
				{
				fos.println("ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn27()!=0)
				{
				fos.println("ARFCN27"+"\t"+"AVG ARFCN27DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn28()!=0)
				{
				fos.println("ARFCN28"+"\t"+"AVG ARFCN28DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn29()!=0)
				{
				fos.println("ARFCN29"+"\t"+"AVG ARFCN29DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn30()!=0)
				{
				fos.println("ARFCN30"+"\t"+"AVG ARFCN30DBM"+"\t");						
				}	
					
		if(tracer1.getarfcn31()!=0)
				{
				fos.println("ARFCN31"+"\t"+"AVG ARFCN31DBM"+"\t"+						
				}	
					
		if(tracer1.getarfcn32()!=0)
				{
				fos.println("ARFCN32"+"\t"+"AVG ARFCN32DBM"+"\t"+						
				}	
					
		if(tracer1.getarfcn33()!=0)
				{
				fos.println("ARFCN33"+"\t"+"AVG ARFCN33DBM"+"\t"+						
				}	
				
		if(tracer1.getarfcn34()!=0)
				{
				fos.println("ARFCN34"+"\t"+"AVG ARFCN34DBM"+"\t"+						
				}	
					
		if(tracer1.getarfcn35()!=0)
				{
				fos.println("ARFCN35"+"\t"+"AVG ARFCN35DBM"+"\t"+						
				}	
					
		if(tracer1.getarfcn36()!=0)
				{
				fos.println("ARFCN36"+"\t"+"AVG ARFCN36DBM"+"\t"+						
				}	
					
		if(tracer1.getarfcn37()!=0)
				{
				fos.println("ARFCN37"+"\t"+"AVG ARFCN37DBM"+"\t"+						
				}	

		if(tracer1.getarfcn38()!=0)
				{
				fos.println("ARFCN38"+"\t"+"AVG ARFCN38DBM"+"\t"+						
				}					
	*/
			fos.println(" ");		
																																																																																									
				do //while (tracer1.getnext()!=null)
				{
					
					fos.print(tracer1.getsegmentnumber());fos.print(delimiter);
					fos.print(tracer1.getmaxarfcn());fos.print(delimiter);
					fos.print(tracer1.getmaxarfcndbm());fos.print(delimiter);

					fos.print(tracer1.getlatitude());fos.print(delimiter);
					fos.print(tracer1.getlongitude());fos.print(delimiter);
					fos.print(tracer1.getspeed());fos.print(delimiter);
					fos.print(tracer1.gettotalroute());fos.print(delimiter);

					fos.print(tracer1.getarfcn00());fos.print(delimiter);
					fos.print(tracer1.getarfcn00dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn01());fos.print(delimiter);
					fos.print(tracer1.getarfcn01dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn02());fos.print(delimiter);
					fos.print(tracer1.getarfcn02dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn03());fos.print(delimiter);
					fos.print(tracer1.getarfcn03dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn04());fos.print(delimiter);
					fos.print(tracer1.getarfcn04dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn05());fos.print(delimiter);
					fos.print(tracer1.getarfcn05dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn06());fos.print(delimiter);
					fos.print(tracer1.getarfcn06dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn07());fos.print(delimiter);
					fos.print(tracer1.getarfcn07dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn08());fos.print(delimiter);
					fos.print(tracer1.getarfcn08dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn09());fos.print(delimiter);
					fos.print(tracer1.getarfcn09dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn10());fos.print(delimiter);
					fos.print(tracer1.getarfcn10dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn11());fos.print(delimiter);
					fos.print(tracer1.getarfcn11dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn12());fos.print(delimiter);
					fos.print(tracer1.getarfcn12dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn13());fos.print(delimiter);
					fos.print(tracer1.getarfcn13dbm());fos.print(delimiter);
					fos.print(tracer1.getarfcn14());fos.print(delimiter);
					fos.print(tracer1.getarfcn14dbm());fos.print(delimiter);		
			
//				if	(tracer1.getarfcn15()!=0)
//					{					
					fos.print(tracer1.getarfcn15());fos.print(delimiter);
					fos.print(tracer1.getarfcn15dbm());fos.print(delimiter);
//					}						
			
//				if	(tracer1.getarfcn16()!=0)
//					{
					fos.print(tracer1.getarfcn16());fos.print(delimiter);
					fos.print(tracer1.getarfcn16dbm());fos.print(delimiter);						
//					}
			
//				if	(tracer1.getarfcn17()!=0)
//					{
					fos.print(tracer1.getarfcn17());fos.print(delimiter);
					fos.print(tracer1.getarfcn17dbm());fos.print(delimiter);					
//					}										
			
//				if	(tracer1.getarfcn18()!=0)
//					{
					fos.print(tracer1.getarfcn18());fos.print(delimiter);
					fos.print(tracer1.getarfcn18dbm());fos.print(delimiter);			
//					}		
				
//				if	(tracer1.getarfcn19()!=0)
//					{
					fos.print(tracer1.getarfcn19());fos.print(delimiter);
					fos.print(tracer1.getarfcn19dbm());fos.print(delimiter);					
//					}								
				
//				if	(tracer1.getarfcn20()!=0)
//					{
					fos.print(tracer1.getarfcn20());fos.print(delimiter);
					fos.print(tracer1.getarfcn20dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn21()!=0)
//					{
					fos.print(tracer1.getarfcn21());fos.print(delimiter);
					fos.print(tracer1.getarfcn21dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn22()!=0)
//					{
					fos.print(tracer1.getarfcn22());fos.print(delimiter);
					fos.print(tracer1.getarfcn22dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn23()!=0)
//					{
					fos.print(tracer1.getarfcn23());fos.print(delimiter);
					fos.print(tracer1.getarfcn23dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn24()!=0)
//					{
					fos.print(tracer1.getarfcn24());fos.print(delimiter);
					fos.print(tracer1.getarfcn24dbm());fos.print(delimiter);					
//					}						
	
//				if	(tracer1.getarfcn25()!=0)
//					{
					fos.print(tracer1.getarfcn25());fos.print(delimiter);
					fos.print(tracer1.getarfcn25dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn26()!=0)
//					{
					fos.print(tracer1.getarfcn26());fos.print(delimiter);
					fos.print(tracer1.getarfcn26dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn27()!=0)
//					{
					fos.print(tracer1.getarfcn27());fos.print(delimiter);
					fos.print(tracer1.getarfcn27dbm());fos.println(delimiter);					
//					}						

//				if	(tracer1.getarfcn28!=0)
//					{
//					fos.print(tracer1.getarfcn28());fos.print(delimiter);
//					fos.print(tracer1.getarfcn28dbm());fos.println(delimiter);					
//					}						
					
//				if	(tracer1.getarfcn29!=0)
//					{
//					fos.print(tracer1.getarfcn28());fos.print(delimiter);
//					fos.print(tracer1.getarfcn28dbm());fos.print(delimiter);					
//					}						

//				if	(tracer1.getarfcn30!=0)
//					{
//					fos.print(tracer1.getarfcn30());fos.print(delimiter);
//					fos.print(tracer1.getarfcn30dbm());fos.print(delimiter);					
//					}	

//				if	(tracer1.getarfcn31!=0)
//					{
//					fos.print(tracer1.getarfcn31());fos.print(delimiter);
//					fos.print(tracer1.getarfcn31dbm());fos.print(delimiter);					
//					}	

//				if	(tracer1.getarfcn32!=0)
//					{
//					fos.print(tracer1.getarfcn32());fos.print(delimiter);
//					fos.print(tracer1.getarfcn32dbm());fos.println(delimiter); 					
//					}	

//				if	(tracer1.getarfcn33!=0)
//					{
//					fos.print(tracer1.getarfcn33());fos.print(delimiter);
//					fos.print(tracer1.getarfcn33dbm());fos.println(delimiter); 					
//					}	

//				if	(tracer1.getarfcn34!=0)
//					{
//					fos.print(tracer1.getarfcn34());fos.print(delimiter);
//					fos.print(tracer1.getarfcn34dbm());fos.println(delimiter); 					
//					}	
						
//				if	(tracer1.getarfcn35!=0)
//					{
//					fos.print(tracer1.getarfcn35());fos.print(delimiter);
//					fos.print(tracer1.getarfcn35dbm());fos.println(delimiter); 					
//					}	

//				if	(tracer1.getarfcn36!=0)
//					{
//					fos.print(tracer1.getarfcn36());fos.print(delimiter);
//					fos.print(tracer1.getarfcn36dbm());fos.println(delimiter); 					
//					}

//				if	(tracer1.getarfcn37!=0)
//					{
//					fos.print(tracer1.getarfcn32());fos.print(delimiter);
//					fos.print(tracer1.getarfcn32dbm());fos.println(delimiter); 					
//					}	

//				if	(tracer1.getarfcn38!=0)
//					{
//					fos.print(tracer1.getarfcn32());fos.print(delimiter);
//					fos.print(tracer1.getarfcn32dbm());fos.println(delimiter); 					
//					}																															
//					... En caso de tener que agregar mas frecuencias
					
					// Cambio a la siguiente linea
					fos.println("");	
											
					//****************************************************************//
					//  NOTA 22: En caso de realizar una actualizacion de software:   //
					//  Para poder generar el archivo con el formato correcto,        //
					//  el ultimo delimiter se debe imprimir con println, ya que esta //
					//  realiza impresion del caracter, ademas inserta un enter al    //
					//  final, por lo que cambia a la linea siguiente. Ademas se      //
					//  deben agregar las lineas de fos.print(tracer1.getarfcnxx)y    //
					//  fos.print(tracer1.getarfcnxxdbm)                              //
					//****************************************************************//
					
					System.out.println(i+"\n");
					i++;
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Error al crear el archivo de salida");
			}
		System.out.println("*************************************");
							//fos.flush();
					fos.close();
	}

	//**********************************************************************************//
	//                 Metodo utilizado para realizar el analisis final                 //
	// En la variable goal se introduce el valor minimo de potencia con que debe contar //
	//                              la frecuencia que posee                             //
	//**********************************************************************************//
	
	public int passedsegments(double goal) 
	{
		rawsamplenode tracer1 = this.head;

		//Contador de segmentos que pasaron la prueba
		int passed=0; 
		do
		{
			// En caso de que el valor maximo de potencia sea mayor igual a goal 
			// (que es el umbral de requerimiento)
			if(tracer1.getmaxarfcndbm()>=goal)
				{
					// Se suma 1 en el contador de segmentos que pasen la prueba
					passed++; 
				}
			tracer1=tracer1.getnext();

		}while(tracer1.getnext()!=null); // En caso de que tracer1 llegue a encontrar valor
										 // nulo, se ha llegado al fin de la lista, y el 
										 // recorrido se ha realizado completamente
		
		// Este metodo retorna la cantidad de segmentos que pasan la prueba
		return passed;	

	}

	//**********************************************************************************//
	//                 Metodo utilizado para realizar el analisis final                 //
	// En la variable goal se introduce el valor minimo de potencia con que debe contar //
	//                              la frecuencia que posee                             //
	//**********************************************************************************//

		public int failedsegments(double goal)
		{
			rawsamplenode tracer1 = this.head;

			//Count segments that passed
			
			int failed=0;
			do
			{
				// En caso de que el valor maximo de potencia sea menor a goal
				// (que es el umbral de requerimiento)
				if(tracer1.getmaxarfcndbm()<goal)
					{
						// Se suma 1 en el contador de segmentos que fallen la prueba
						failed++;
					}
				tracer1=tracer1.getnext();

			}while(tracer1.getnext()!=null);
			
		// Este metodo retorna la cantidad de segmentos que fallan la prueba
		return failed;

	}
	
	
		//************************************************************************//
		// Nota 23: En caso de querer ampliar software a mayor cantidad de valores//
		// de frecuencia se deben añadir argumentos respectivos al public void    //
		// insertendadapted. Ej: se desea añadir una frecuencia mas, los          //
		// argumentos deben de tener un int arfcnxx y un double arfcnxxdbm mas    //
		// dentro de los argumentos siguiendo el orden correspondient             //
		//************************************************************************// 
	
	// Metodo adaptado para insertar un elemento al final
	public void insertendadapted (int segmentnumber, int maxarfcn, double maxarfcndbm, 
	double latitude, double longitude, int speed,double totalroute,
	int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,
	int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,
	int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,
	int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
	int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,
	int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,
	int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,
	int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,
	int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,
	int arfcn27,double arfcn27dbm, //int arfcn28,double arfcn28dbm,int arfcn29,double arfcn29dbm,
	//int arfcn30,double arfcn30dbm,int arfcn31,double arfcn31dbm,
	int segmentdimension)
	{
	rawsamplenode nuevo = new rawsamplenode(" ", latitude,  longitude);

	//*********************************************************************//
	// NOTA 24: En caso de querer realizar una ampliacion de numero de     //
	// de frecuencias en este software, se debe incrementar el numero de   //
	// argumentos de entrada en nuevo.setnode2                              //
	//*********************************************************************//
	
	nuevo.setnode2(segmentnumber,maxarfcn,maxarfcndbm,latitude,longitude,speed,totalroute,
	arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, 
	arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm,
	arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, arfcn11dbm,
	arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, 
	arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm,
	arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm,
	arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,
//	arfcn28, arfcn28dbm, arfcn29, arfcn29dbm, arfcn30, arfcn30dbm, arfcn31, arfcn31dbm,
	null,null);

	rawsamplenode tracer1 = this.head;

		int counter=0;
				if(tracer1.getnext()==null)
					{
								tracer1.setlinknext(nuevo);
								nuevo.setlinkprevious(tracer1);
								nuevo.setlinknext(null);
								tracer1.setlatitude(nuevo.getlatitude());
								tracer1.setlongitude(nuevo.getlongitude());
								tracer1.setsegmentnumber(0);
								tracer1.setdistance(0);
								tracer1.settotalroute(0);
								nuevo.setsegmentnumber(0);
								nuevo.setdistance(0);
								nuevo.settotalroute(0);
					}
				else
					{
								do
								{
									tracer1=tracer1.getnext();
								}while (tracer1.getnext()!=null);
								//nodedistancecheck(tracer1,nuevo,segmentdimension);
						tracer1.setlinknext(nuevo);
						nuevo.setlinkprevious(tracer1);
						nuevo.setlinknext(null);
					}
	}

}
