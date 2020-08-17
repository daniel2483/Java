import java.io.*;
import java.io.File;
import java.lang.Object;
import java.lang.*;

class rawsamplenode
{

	//Declaration of the Variables

	private String zeit;
	private double latitude;
	private double longitude;
	private int speed;
	private int arfcn00;
	private double arfcn00dbm;
	private int arfcn01;
	private double arfcn01dbm;
	private int arfcn02;
	private double arfcn02dbm;
	private int arfcn03;
	private double arfcn03dbm;
	private int arfcn04;
	private double arfcn04dbm;
	private int arfcn05;
	private double arfcn05dbm;
	private int arfcn06;
	private double arfcn06dbm;
	private int arfcn07;
	private double arfcn07dbm;
	private int arfcn08;
	private double arfcn08dbm;
	private int arfcn09;
	private double arfcn09dbm;
	private int arfcn10;
	private double arfcn10dbm;
	private int arfcn11;
	private double arfcn11dbm;
	private int arfcn12;
	private double arfcn12dbm;
	private int arfcn13;
	private double arfcn13dbm;
	private int arfcn14;
	private double arfcn14dbm;
	private int arfcn15;
	private double arfcn15dbm;
	private int arfcn16;
	private double arfcn16dbm;
	private int arfcn17;
	private double arfcn17dbm;
	private int arfcn18;
	private double arfcn18dbm;
	private int arfcn19;
	private double arfcn19dbm;
	private int arfcn20;
	private double arfcn20dbm;
	private int arfcn21;
	private double arfcn21dbm;
	private int arfcn22;
	private double arfcn22dbm;
	private int arfcn23;
	private double arfcn23dbm;
	private int arfcn24;
	private double arfcn24dbm;
	private int arfcn25;
	private double arfcn25dbm;
	private int arfcn26;
	private double arfcn26dbm;
	private int arfcn27;
	private double arfcn27dbm;
	private int zaehler;
	private double coordistance;
	private double totalroute;
	private int segmentnumber;
	private int maxarfcn;
	private double maxarfcndbm;

	private rawsamplenode next;
	private rawsamplenode previous;

	// Node Konstruktors

		public rawsamplenode(String zeit, rawsamplenode siguiente, rawsamplenode anterior)
		{
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
			this.zaehler=1;

			this.next=siguiente;
			this.previous=anterior;
		}

		public rawsamplenode(String zeit,double latitude, double longitude, rawsamplenode siguiente, rawsamplenode anterior)
			{
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
				this.zaehler=1;

				this.next=siguiente;
				this.previous=anterior;
		}

		public rawsamplenode(String zeit,double latitude, double longitude)
			{
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
				this.zaehler=1;

				this.next=null;
				this.previous=null;
		}

		// Methods to extract and set data

		public String getzeit()
		{
			return this.zeit;
		}
		public double getlatitude()
		{
			return this.latitude;
		}
		public double getlongitude()
		{
			return this.longitude;
		}
		public int getspeed()
		{
			return this.speed;
		}

		public int getarfcn00()
		{
			return this.arfcn00;
		}
		public double getarfcn00dbm()
		{
			return this.arfcn00dbm;
		}
		public int getarfcn01()
		{
			return this.arfcn01;
		}
		public double getarfcn01dbm()
		{
			return this.arfcn01dbm;
		}
		public int getarfcn02()
		{
			return this.arfcn02;
		}
		public double getarfcn02dbm()
		{
			return this.arfcn02dbm;
		}
		public int getarfcn03()
		{
			return this.arfcn03;
		}
		public double getarfcn03dbm()
		{
			return this.arfcn03dbm;
		}
		public int getarfcn04()
		{
			return this.arfcn04;
		}
		public double getarfcn04dbm()
		{
			return this.arfcn04dbm;
		}
		public int getarfcn05()
		{
			return this.arfcn05;
		}
		public double getarfcn05dbm()
		{
			return this.arfcn05dbm;
		}
		public int getarfcn06()
		{
			return this.arfcn06;
		}
		public double getarfcn06dbm()
		{
			return this.arfcn06dbm;
		}
		public int getarfcn07()
		{
			return this.arfcn07;
		}
		public double getarfcn07dbm()
		{
			return this.arfcn07dbm;
		}
		public int getarfcn08()
		{
			return this.arfcn08;
		}
		public double getarfcn08dbm()
		{
			return this.arfcn08dbm;
		}
		public int getarfcn09()
		{
			return this.arfcn09;
		}
		public double getarfcn09dbm()
		{
			return this.arfcn09dbm;
		}
		public int getarfcn10()
		{
			return this.arfcn10;
		}
		public double getarfcn10dbm()
		{
			return this.arfcn10dbm;
		}
		public int getarfcn11()
		{
			return this.arfcn11;
		}
		public double getarfcn11dbm()
		{
			return this.arfcn11dbm;
		}
		public int getarfcn12()
		{
			return this.arfcn12;
		}
		public double getarfcn12dbm()
		{
			return this.arfcn12dbm;
		}
		public int getarfcn13()
		{
			return this.arfcn13;
		}
		public double getarfcn13dbm()
		{
			return this.arfcn13dbm;
		}
		public int getarfcn14()
		{
			return this.arfcn14;
		}
		public double getarfcn14dbm()
		{
			return this.arfcn14dbm;
		}
		public int getarfcn15()
		{
			return this.arfcn15;
		}
		public double getarfcn15dbm()
		{
			return this.arfcn15dbm;
		}
		public int getarfcn16()
		{
			return this.arfcn16;
		}
		public double getarfcn16dbm()
		{
			return this.arfcn16dbm;
		}
		public int getarfcn17()
		{
			return this.arfcn17;
		}
		public double getarfcn17dbm()
		{
			return this.arfcn17dbm;
		}
		public int getarfcn18()
		{
			return this.arfcn18;
		}
		public double getarfcn18dbm()
		{
			return this.arfcn18dbm;
		}
		public int getarfcn19()
		{
			return this.arfcn19;
		}
		public double getarfcn19dbm()
		{
			return this.arfcn19dbm;
		}
		public int getarfcn20()
		{
			return this.arfcn20;
		}
		public double getarfcn20dbm()
		{
			return this.arfcn20dbm;
		}
		public int getarfcn21()
		{
			return this.arfcn21;
		}
		public double getarfcn21dbm()
		{
			return this.arfcn21dbm;
		}
		public int getarfcn22()
		{
			return this.arfcn22;
		}
		public double getarfcn22dbm()
		{
			return this.arfcn22dbm;
		}
		public int getarfcn23()
		{
			return this.arfcn23;
		}
		public double getarfcn23dbm()
		{
			return this.arfcn23dbm;
		}
		public int getarfcn24()
		{
			return this.arfcn24;
		}
		public double getarfcn24dbm()
		{
			return this.arfcn24dbm;
		}
		public int getarfcn25()
		{
			return this.arfcn25;
		}
		public double getarfcn25dbm()
		{
			return this.arfcn25dbm;
		}
		public int getarfcn26()
		{
			return this.arfcn26;
		}
		public double getarfcn26dbm()
		{
			return this.arfcn26dbm;
		}
		public int getarfcn27()
		{
			return this.arfcn27;
		}
		public double getarfcn27dbm()
		{
			return this.arfcn27dbm;
		}		
		
		public int getzaehler()
		{
			return this.zaehler;
		}
		public double getcoordistance()
		{
			return this.coordistance;
		}
		public double gettotalroute()
		{
			return this.totalroute;
		}
		public int getsegmentnumber()
		{
			return this.segmentnumber;
		}
		public int getmaxarfcn()
		{
			return this.maxarfcn;
		}
		public double getmaxarfcndbm()
		{
			return this.maxarfcndbm;
		}
		public  rawsamplenode getnext()
		{
			return this.next;
		}
		public  rawsamplenode getprevious()
		{
			return this.previous;
		}
		public void setlinknext(rawsamplenode siguiente)
		{
			this.next=siguiente;
		}
		public void setlinkprevious(rawsamplenode anterior)
		{
			this.previous=anterior;
		}
		public void setspeed(int speed)
		{
			this.speed=speed;
		}
		public void setarfcn00(int value)
		{
			this.arfcn00=value;
		}
		public void setarfcn00dbm(double value)
		{
			this.arfcn00dbm=value;
		}
		public void setarfcn01(int value)
		{
			this.arfcn01=value;
		}
		public void setarfcn01dbm(double value)
		{
			this.arfcn01dbm=value;
		}
		public void setarfcn02(int value)
		{
			this.arfcn02=value;
		}
		public void setarfcn02dbm(double value)
		{
			this.arfcn02dbm=value;
		}
		public void setarfcn03(int value)
		{
			this.arfcn03=value;
		}
		public void setarfcn03dbm(double value)
		{
			this.arfcn03dbm=value;
		}
		public void setarfcn04(int value)
		{
			this.arfcn04=value;
		}
		public void setarfcn04dbm(double value)
		{
			this.arfcn04dbm=value;
		}
		public void setarfcn05(int value)
		{
			this.arfcn05=value;
		}
		public void setarfcn05dbm(double value)
		{
			this.arfcn05dbm=value;
		}
		public void setarfcn06(int value)
		{
			this.arfcn06=value;
		}
		public void setarfcn06dbm(double value)
		{
			this.arfcn06dbm=value;
		}
		public void setarfcn07(int value)
		{
			this.arfcn07=value;
		}
		public void setarfcn07dbm(double value)
		{
			this.arfcn07dbm=value;
		}
		public void setarfcn08(int value)
		{
			this.arfcn08=value;
		}
		public void setarfcn08dbm(double value)
		{
			this.arfcn08dbm=value;
		}
		public void setarfcn09(int value)
		{
			this.arfcn09=value;
		}
		public void setarfcn09dbm(double value)
		{
			this.arfcn09dbm=value;
		}
		public void setarfcn10(int value)
		{
			this.arfcn10=value;
		}
		public void setarfcn10dbm(double value)
		{
			this.arfcn10dbm=value;
		}
		public void setarfcn11(int value)
		{
			this.arfcn11=value;
		}
		public void setarfcn11dbm(double value)
		{
			this.arfcn11dbm=value;
		}
		public void setarfcn12(int value)
		{
			this.arfcn12=value;
		}
		public void setarfcn12dbm(double value)
		{
			this.arfcn12dbm=value;
		}
		public void setarfcn13(int value)
		{
			this.arfcn13=value;
		}
		public void setarfcn13dbm(double value)
		{
			this.arfcn13dbm=value;
		}
		public void setarfcn14(int value)
		{
			this.arfcn14=value;
		}
		public void setarfcn14dbm(double value)
		{
			this.arfcn14dbm=value;
		}
		public void setarfcn15(int value)
		{
			this.arfcn15=value;
		}
		public void setarfcn15dbm(double value)
		{
			this.arfcn15dbm=value;
		}
		public void setarfcn16(int value)
		{
			this.arfcn16=value;
		}
		public void setarfcn16dbm(double value)
		{
			this.arfcn16dbm=value;
		}
		public void setarfcn17(int value)
		{
			this.arfcn17=value;
		}
		public void setarfcn17dbm(double value)
		{
			this.arfcn17dbm=value;
		}
		public void setarfcn18(int value)
		{
			this.arfcn18=value;
		}
		public void setarfcn18dbm(double value)
		{
			this.arfcn18dbm=value;
		}
		public void setarfcn19(int value)
		{
			this.arfcn19=value;
		}
		public void setarfcn19dbm(double value)
		{
			this.arfcn19dbm=value;
		}
		public void setarfcn20(int value)
		{
			this.arfcn20=value;
		}
		public void setarfcn20dbm(double value)
		{
			this.arfcn20dbm=value;
		}
		public void setarfcn21(int value)
		{
			this.arfcn21=value;
		}
		public void setarfcn21dbm(double value)
		{
			this.arfcn21dbm=value;
		}
		public void setarfcn22(int value)
		{
			this.arfcn22=value;
		}
		public void setarfcn22dbm(double value)
		{
			this.arfcn22dbm=value;
		}
		public void setarfcn23(int value)
		{
			this.arfcn23=value;
		}
		public void setarfcn23dbm(double value)
		{
			this.arfcn23dbm=value;
		}
		public void setarfcn24(int value)
		{
			this.arfcn24=value;
		}
		public void setarfcn24dbm(double value)
		{
			this.arfcn24dbm=value;
		}
		public void setarfcn25(int value)
		{
			this.arfcn25=value;
		}
		public void setarfcn25dbm(double value)
		{
			this.arfcn25dbm=value;
		}
		public void setarfcn26(int value)
		{
			this.arfcn26=value;
		}
		public void setarfcn26dbm(double value)
		{
			this.arfcn26dbm=value;
		}
		public void setarfcn27(int value)
		{
			this.arfcn27=value;
		}
		public void setarfcn27dbm(double value)
		{
			this.arfcn27dbm=value;
		}		
		
		
		public void setzaehler(int value)
		{
			this.zaehler=value;
		}
		public void setmaxarfcn(int value)
		{
			this.maxarfcn=value;
		}
		public void setmaxarfcndbm(double value)
		{
			this.maxarfcndbm=value;
		}
		
		
		
		public void setnode (String zeit,double latitude, double longitude, int speed,int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
		int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm ,int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,int arfcn27,double arfcn27dbm, rawsamplenode siguiente, rawsamplenode anterior)
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
			this.maxarfcn=0;
			this.maxarfcndbm=0.0;

			this.next=siguiente;
			this.previous=anterior;
		}
				public void setnode1 (String zeit,double latitude, double longitude, int speed,int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
				int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,int arfcn27,double arfcn27dbm)
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
					this.maxarfcn=0;
					this.maxarfcndbm=0.0;
		}

		public void setnode2 (int segmentnumber, int maxarfcn, double maxarfcndbm, double latitude, double longitude, int speed,double totalroute,int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
		int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,int arfcn27,double arfcn27dbm,rawsamplenode siguiente, rawsamplenode anterior)
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

// End der Klasse Rawsamplenode





//   Doppelt-verkettete Liste

class verketteteliste
{
	rawsamplenode head;

	// Konstruktor
	public verketteteliste()
	{
		this.head=null;
	}


	// Method to initialize and insert first element
	public void insertelement(String zeit,double latitude, double longitude, rawsamplenode siguiente, rawsamplenode anterior)
	{
		rawsamplenode newnode = new rawsamplenode(zeit,latitude,longitude,siguiente, anterior );
		this.head = newnode;
	}

	// Method to delete first element on the list
	public void deletefirst()
	{
		rawsamplenode tracer1 = this.head;	// trace1 = head  (objetos de tipo Rawsamplenode)Toma nodo inicial
		this.head=tracer1.getnext();		// this.head es igual a tracer1 siguiente
		tracer1=null;						// hace null tracer1 
	}

	//Method to insert an element at the end
	public void insertend (String zeit,double latitude, double longitude, int speed,int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
							int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,int arfcn27,double arfcn27dbm, int segmentdimension)
	{
		// Creo un nuevo objeto de clase rawsamplenode de constructor (zeit, lat ,long)
		// inicializo valores de arfcn[0-27] y arfcn[0-27]dbm y otros
		rawsamplenode nuevo = new rawsamplenode(zeit, latitude,  longitude);
	
		// Seteo un nuevo nodo con los valores actuales
		nuevo.setnode( zeit, latitude,  longitude,  speed, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, 
						arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,null,null);

		// Creo tracer1 de clase rawsamplenode
		rawsamplenode tracer1 = this.head;

		int counter=0;
				if(tracer1.getnext()==null)
					{
								tracer1.setlinknext(nuevo);					// el link siguiente de tracer1 apunta a nuevo
								nuevo.setlinkprevious(tracer1);				// el link previo de nuevo apunta a tracer1
								nuevo.setlinknext(null); 					// el link siguiente de nuevo apunta a nulo 
								tracer1.setlatitude(nuevo.getlatitude());	// Setea Latitud con el valor de latitud de objeto nuevo
								tracer1.setlongitude(nuevo.getlongitude());	// Setea Longitud con el valor de longitud de objeto nuevo
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
								// 
								nodedistancecheck(tracer1,nuevo,segmentdimension);
					}
	}

		// Metodo que recibe tracer1, nuevo y tamaño de segmento (en este caso 10 metros)
	public void nodedistancecheck(rawsamplenode tracer1, rawsamplenode nuevo, int segmentdimension)
	{
		int i=0;

		int newsegmentnumber;
		double si;
		int segmentid	=tracer1.getsegmentnumber();// En var segmentid obtengo el numero de segmento de tracer1
		double latp		=tracer1.getlatitude(); // Obtengo el valor de LAT de tracer1 y almaceno  en var latp
		double lat		=nuevo.getlatitude();	// Obtengo el valor de LAT de nuevo y almaceno en var lat
		double longip	=tracer1.getlongitude();// Obtengo el valor de LON de tracer1 y almaceno en var longip 
		double longi	=nuevo.getlongitude();	// Obtengo el valor de LON de nuevo y lo almaceno en var longi
		double distance;

		// En el caso de que posicion de tracer1 sea igual a posicion de nuevo
		if(latp==lat&&longip==longi)
				{ 	distance=0.0;}		// La distancia recorrida es cero
				
		// En el caso de que posicion de tracer1 sea diferente a posicion de nuevo entonces
		// calcular distancia
		else
				{
					// Formula para calcular distancia
				 	distance	=1000*(6378*Math.acos((Math.cos((longip*Math.PI/180)-(longi*Math.PI/180))*Math.cos(lat*Math.PI/180))*Math.cos(latp*Math.PI/180)+Math.sin(lat*Math.PI/180)*Math.sin(latp*Math.PI/180)));;
				}
				
		double totaldrivenalt=tracer1.gettotalroute();	// Obtengo el valor actual de la ruta
		double totaldrivennew=distance+totaldrivenalt;	// Al valor actual de la ruta le sumo la nueva distancia entre tracer1 y nuevo


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

	//Summarize nodes
	public void summarizenodes(rawsamplenode previous, rawsamplenode next, double totaldrivennew,double distance)
	{
		int alteszaehler=1;

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
		previous.setzaehler((previous.getzaehler()+next.getzaehler())/alteszaehler);

		next=null;	// Siguiente nodo es igual a null
	}


	// generate output file with raw data plus distance calculation
	public  void ausgabedatei(String outputpath,String locationname)throws java.io.IOException
	{
		// DatenFelder
		String fullpath = outputpath+"\\"+locationname;
		String outputfilename = fullpath+"\\"+locationname+".txt";

		rawsamplenode tracer1 = this.head;
		String delimiter ="\t";
		int i =0;

		// Initialisiere Ausgabedatei
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{ fos.println("ZAEHLER"+"\t"+"DISTANCE"+"\t"+"SEGMENT"+"\t"+"ROUTE"+"\t"+"Zeit"+"\t"+"LAT"+"\t"+"LONG"+"\t"+"SPEED"+"\t"+"DIST"+"\t"+"SEG"+"\t"+"TOTAL");
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
					fos.print(tracer1.getarfcn14dbm());fos.println(delimiter);
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
					System.out.println(i+"\n");
					i++;
					//fos.flush();
					//fos.close();
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Fehler beim Erzeugen der Ausgabedatei");
			}
		System.out.println("*************************************");
							//fos.flush();
					fos.close();
	}




//Methos Deletenode

public void deletenode(rawsamplenode tracer1)
{

	tracer1.getprevious().setlinknext(tracer1.getnext());
	tracer1.getnext().setlinkprevious(tracer1.getprevious());
	tracer1.setlinknext(null);
	tracer1.setlinkprevious(null);
	tracer1=null;
}



	public  void ausgabedateiAVG(String outputpath,String locationname, String locationnumber)throws java.io.IOException
	{
		// DatenFelder
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		String outputfilename = fullpath+"\\"+locationname+"_"+locationnumber+"_internal ericsson.txt";

		rawsamplenode tracer1 = this.head;
		String delimiter ="\t";
		//String delimiter2 ="\n";
		int i =0;

		// Initialisiere Ausgabedatei
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);

		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{ fos.println("SEGMENT NUMBER"+"\t"+"MAX ARFCN"+"\t"+"MAX AVG Scanned Level"+"\t"+"Number of Samples"+"\t"+"LAT"+"\t"+"LONG"+"\t"+" AVG SPEED"+"\t"+"TOTAL ROUTE"+"\t"+"ARFCN00"+"\t"+"AVG ARFCN00DBM"+"\t"+"ARFCN01"+"\t"+"AVG ARFCN01DBM"+"\t"+"ARFCN02"+"\t"+"AVG ARFCN02DBM"+"\t"+"ARFCN03"+"\t"+"AVG ARFCN03DBM"+"\t"+"ARFCN04"+"\t"+"AVG ARFCN04DBM"+"\t"+"ARFCN05"+"\t"+"AVG ARFCN05DBM"+"\t"+"ARFCN06"+"\t"+"AVG ARFCN06DBM"+"\t"+"ARFCN07"+"\t"+"AVG ARFCN07DBM"+"\t"+"ARFCN08"+"\t"+"AVG ARFCN08DBM"+"\t"+"ARFCN09"+"\t"+"AVG ARFCN09DBM"+"\t"+"ARFCN10"+"\t"+"AVG ARFCN10DBM"+"\t"+"ARFCN11"+"\t"+"AVG ARFCN11DBM"+"\t"+"ARFCN12"+"\t"+"AVG ARFCN12DBM"+"\t"+
							"ARFCN13"+"\t"+"AVG ARFCN13DBM"+"\t"+"ARFCN14"+"\t"+"AVG ARFCN14DBM"+"\t"+"ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t"+"ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t"+"ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t"+"ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t"+"ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t"+"ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t"+"ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t"+"ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t"+"ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t"+"ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t"+"ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t"+"ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t"+"ARFCN27"+"\t"+"AVG ARFCN27DBM");
				do
				{
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
					fos.print(tracer1.getarfcn14dbm());fos.println(delimiter);
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

					System.out.println(i+"\n");
					i++;
					//fos.flush();
					//fos.close();
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Fehler beim Erzeugen der Ausgabedatei");
			}
		System.out.println("*************************************");
							//fos.flush();
					fos.close();
	}

	//Calculate Average
	public void CalculateAVG()
	{
		rawsamplenode tracer1 = this.head;
		//System.out.println("LLegue aqui"); // Bandera
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

System.out.println("AVGD "+tracer1.getsegmentnumber());
tracer1=tracer1.getnext();
				}//while (tracer1.getnext()!=null);
	}

// Calculate Max Average of each segment
public void maxaverage()
{
	int maxarfcn;
	double maxarfcndbm;
	rawsamplenode tracer1 = this.head;

	do
	{
		maxarfcn=tracer1.getarfcn00();
		maxarfcndbm=tracer1.getarfcn00dbm();

		if(tracer1.getarfcn01dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn01();
			maxarfcndbm=tracer1.getarfcn01dbm();
		}
		if(tracer1.getarfcn02dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn02();
			maxarfcndbm=tracer1.getarfcn02dbm();
		}
		if(tracer1.getarfcn03dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn03();
			maxarfcndbm=tracer1.getarfcn03dbm();

		}
		if(tracer1.getarfcn04dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn04();
			maxarfcndbm=tracer1.getarfcn04dbm();

		}
		if(tracer1.getarfcn05dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn05();
			maxarfcndbm=tracer1.getarfcn05dbm();

		}
		if(tracer1.getarfcn06dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn06();
			maxarfcndbm=tracer1.getarfcn06dbm();

		}
		if(tracer1.getarfcn07dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn07();
			maxarfcndbm=tracer1.getarfcn07dbm();

		}
		if(tracer1.getarfcn08dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn08();
			maxarfcndbm=tracer1.getarfcn08dbm();

		}
		if(tracer1.getarfcn09dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn09();
			maxarfcndbm=tracer1.getarfcn09dbm();

		}
		if(tracer1.getarfcn10dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn10();
			maxarfcndbm=tracer1.getarfcn10dbm();

		}
		if(tracer1.getarfcn11dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn11();
			maxarfcndbm=tracer1.getarfcn11dbm();

		}
		if(tracer1.getarfcn12dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn12();
			maxarfcndbm=tracer1.getarfcn12dbm();

		}
		if(tracer1.getarfcn13dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn13();
			maxarfcndbm=tracer1.getarfcn13dbm();

		}
		if(tracer1.getarfcn14dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn14();
			maxarfcndbm=tracer1.getarfcn14dbm();
		}
		if(tracer1.getarfcn15dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn15();
			maxarfcndbm=tracer1.getarfcn15dbm();
		}
		if(tracer1.getarfcn16dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn16();
			maxarfcndbm=tracer1.getarfcn16dbm();

		}
		if(tracer1.getarfcn17dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn17();
			maxarfcndbm=tracer1.getarfcn17dbm();

		}
		if(tracer1.getarfcn18dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn18();
			maxarfcndbm=tracer1.getarfcn18dbm();

		}
		if(tracer1.getarfcn19dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn19();
			maxarfcndbm=tracer1.getarfcn19dbm();

		}
		if(tracer1.getarfcn20dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn20();
			maxarfcndbm=tracer1.getarfcn20dbm();

		}
		if(tracer1.getarfcn21dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn21();
			maxarfcndbm=tracer1.getarfcn21dbm();

		}
		if(tracer1.getarfcn22dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn22();
			maxarfcndbm=tracer1.getarfcn22dbm();

		}
		if(tracer1.getarfcn23dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn23();
			maxarfcndbm=tracer1.getarfcn23dbm();

		}
		if(tracer1.getarfcn24dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn24();
			maxarfcndbm=tracer1.getarfcn24dbm();

		}
		if(tracer1.getarfcn25dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn25();
			maxarfcndbm=tracer1.getarfcn25dbm();

		}
		if(tracer1.getarfcn26dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn26();
			maxarfcndbm=tracer1.getarfcn26dbm();

		}
		if(tracer1.getarfcn27dbm()>maxarfcndbm)
		{
			maxarfcn=tracer1.getarfcn27();
			maxarfcndbm=tracer1.getarfcn27dbm();
		}
		tracer1.setmaxarfcn(maxarfcn);
		tracer1.setmaxarfcndbm(maxarfcndbm);
		tracer1=tracer1.getnext();

	}while(tracer1.getnext()!=null);
}


// Method to calculate the number of segments in the list
	public int numberofsegments()
	{
		int counter =0;
		rawsamplenode tracer1 = this.head;
		do
		{
			tracer1=tracer1.getnext();
			counter++;
		}while(tracer1.getnext()!=null);

		return counter;
	}

// Method to delete a node from the list
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
		// DatenFelder
		String fullpath = outputpath+"\\"+locationnumber+" "+locationname;
		String outputfilename = fullpath+"\\"+locationname+"_"+locationnumber+"_Random_Segments.txt";

		rawsamplenode tracer1 = this.head;
		String delimiter ="\t";
		String delimiter2 ="\n";
		int i =0;

		// Initialisiere Ausgabedatei
		boolean success = new File(fullpath).mkdir();
		File ausgabedatei = new File(outputfilename);
		//boolean success = new File("Pruebasnueva").mkdir();
		//File ausgabedatei = new File("C:\\TEMP\\JV\\v_2_0\\Pruebasnueva\\ListeOUT FINAL.txt");
		PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
		if(ausgabedatei.exists()==false || ausgabedatei.canWrite()==true)
			{ fos.println("SEGMENT NUMBER"+"\t"+"MAX ARFCN"+"\t"+"MAX Scanned (dBm)"+"\t"+"LATITUDE"+"\t"+"LONGITUDE"+"\t"+" AVG SPEED"+"\t"+"TOTAL ROUTE"+"\t"+"ARFCN00"+"\t"+"AVG ARFCN00DBM"+"\t"+"ARFCN01"+"\t"+"AVG ARFCN01DBM"+"\t"+"ARFCN02"+"\t"+"AVG ARFCN02DBM"+"\t"+"ARFCN03"+"\t"+"AVG ARFCN03DBM"+"\t"+"ARFCN04"+"\t"+"AVG ARFCN04DBM"+"\t"+"ARFCN05"+"\t"+"AVG ARFCN05DBM"+"\t"+"ARFCN06"+"\t"+"AVG ARFCN06DBM"+"\t"+"ARFCN07"+"\t"+"AVG ARFCN07DBM"+"\t"+"ARFCN08"+"\t"+"AVG ARFCN08DBM"+"\t"+"ARFCN09"+"\t"+"AVG ARFCN09DBM"+"\t"+"ARFCN10"+"\t"+"AVG ARFCN10DBM"+"\t"+"ARFCN11"+"\t"+"AVG ARFCN11DBM"+"\t"+"ARFCN12"+"\t"+
							"AVG ARFCN12DBM"+"\t"+"ARFCN13"+"\t"+"AVG ARFCN13DBM"+"\t"+"ARFCN14"+"\t"+"AVG ARFCN14DBM"+"\t"+"ARFCN15"+"\t"+"AVG ARFCN15DBM"+"\t"+"ARFCN16"+"\t"+"AVG ARFCN16DBM"+"\t"+"ARFCN17"+"\t"+"AVG ARFCN17DBM"+"\t"+"ARFCN18"+"\t"+"AVG ARFCN18DBM"+"\t"+"ARFCN19"+"\t"+"AVG ARFCN19DBM"+"\t"+"ARFCN20"+"\t"+"AVG ARFCN20DBM"+"\t"+"ARFCN21"+"\t"+"AVG ARFCN21DBM"+"\t"+"ARFCN22"+"\t"+"AVG ARFCN22DBM"+"\t"+"ARFCN23"+"\t"+"AVG ARFCN23DBM"+"\t"+"ARFCN24"+"\t"+"AVG ARFCN24DBM"+"\t"+"ARFCN25"+"\t"+"AVG ARFCN25DBM"+"\t"+"ARFCN26"+"\t"+"AVG ARFCN26DBM"+"\t"+"ARFCN27"+"\t"+"AVG ARFCN27DBM");
				do //while (tracer1.getnext()!=null)
				{
					//PrintWriter fos = new PrintWriter(new BufferedWriter(new FileWriter(ausgabedatei)));
					fos.print(tracer1.getsegmentnumber());fos.print(delimiter);
					fos.print(tracer1.getmaxarfcn());fos.print(delimiter);
					fos.print(tracer1.getmaxarfcndbm());fos.print(delimiter);

					//fos.print(tracer1.getzaehler());fos.print(delimiter);
					//fos.print(tracer1.getzeit());fos.print(delimiter);
					fos.print(tracer1.getlatitude());fos.print(delimiter);
					fos.print(tracer1.getlongitude());fos.print(delimiter);
					fos.print(tracer1.getspeed());fos.print(delimiter);

					//fos.print(tracer1.getcoordistance());fos.print(delimiter);

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
					fos.print(tracer1.getarfcn14dbm());fos.println(delimiter);
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
					//fos.print(delimiter2+"\r");
					System.out.println(i+"\n");
					i++;
					//fos.flush();
					//fos.close();
					tracer1=tracer1.getnext();
				}while (tracer1.getnext()!=null);
			}
		else
			{
				System.out.println("Fehler beim Erzeugen der Ausgabedatei");
			}
		System.out.println("*************************************");
							//fos.flush();
					fos.close();
	}


	//Final Analysis
	public int passedsegments(double goal)
	{
		rawsamplenode tracer1 = this.head;

		//Count segments that passed
		int passed=0;
		do
		{
			if(tracer1.getmaxarfcndbm()>=goal)
				{
					passed++;
				}
			tracer1=tracer1.getnext();

		}while(tracer1.getnext()!=null);
		return passed;

	}

		public int failedsegments(double goal)
		{
			rawsamplenode tracer1 = this.head;

			//Count segments that passed
			int failed=0;
			do
			{
				if(tracer1.getmaxarfcndbm()<goal)
					{
						failed++;
					}
				tracer1=tracer1.getnext();

			}while(tracer1.getnext()!=null);
			return failed;

	}

//Method to insert an element at the end
	public void insertendadapted (int segmentnumber, int maxarfcn, double maxarfcndbm, double latitude, double longitude, int speed,double totalroute,int arfcn00,double arfcn00dbm,int arfcn01,double arfcn01dbm,int arfcn02,double arfcn02dbm,int arfcn03,double arfcn03dbm,int arfcn04,double arfcn04dbm,int arfcn05,double arfcn05dbm,int arfcn06,double arfcn06dbm,int arfcn07,double arfcn07dbm,int arfcn08,double arfcn08dbm,int arfcn09,double arfcn09dbm,int arfcn10,double arfcn10dbm,int arfcn11,double arfcn11dbm,
									int arfcn12,double arfcn12dbm,int arfcn13,double arfcn13dbm,int arfcn14,double arfcn14dbm,int arfcn15,double arfcn15dbm,int arfcn16,double arfcn16dbm,int arfcn17,double arfcn17dbm,int arfcn18,double arfcn18dbm,int arfcn19,double arfcn19dbm,int arfcn20,double arfcn20dbm,int arfcn21,double arfcn21dbm,int arfcn22,double arfcn22dbm,int arfcn23,double arfcn23dbm,int arfcn24,double arfcn24dbm,int arfcn25,double arfcn25dbm,int arfcn26,double arfcn26dbm,int arfcn27,double arfcn27dbm, int segmentdimension)
	{
		rawsamplenode nuevo = new rawsamplenode(" ", latitude,  longitude);
		nuevo.setnode2( segmentnumber,maxarfcn,maxarfcndbm,latitude,  longitude,  speed,totalroute, arfcn00, arfcn00dbm, arfcn01, arfcn01dbm, arfcn02, arfcn02dbm, arfcn03, arfcn03dbm, arfcn04, arfcn04dbm, arfcn05, arfcn05dbm, arfcn06, arfcn06dbm, arfcn07, arfcn07dbm, arfcn08, arfcn08dbm, arfcn09, arfcn09dbm, arfcn10, arfcn10dbm, arfcn11, 
						arfcn11dbm, arfcn12, arfcn12dbm, arfcn13, arfcn13dbm, arfcn14, arfcn14dbm, arfcn15, arfcn15dbm, arfcn16, arfcn16dbm, arfcn17, arfcn17dbm, arfcn18, arfcn18dbm, arfcn19, arfcn19dbm, arfcn20, arfcn20dbm, arfcn21, arfcn21dbm, arfcn22, arfcn22dbm, arfcn23, arfcn23dbm, arfcn24, arfcn24dbm, arfcn25, arfcn25dbm, arfcn26, arfcn26dbm, arfcn27, arfcn27dbm,null,null);
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
