import java.io.*;
import java.lang.Object;
import java.lang.*;

class hptest
{

		// Declaracion de variables
		private String initialtext;
		private float coveragepercentage;
		private float confidencelevel;
		private float normalfactor;
		private float errormargin;
		private int	  samplesneeded;
		private hptest next;
		private hptest previous;

		// Constructor
		public hptest(String initialtext, float coveragepercentage, float confidencelevel, float normalfactor, float errormargin, int samples)
		{
			this.initialtext=initialtext;
			this.coveragepercentage=coveragepercentage;
			this.confidencelevel=confidencelevel;
			this.normalfactor=normalfactor;
			this.errormargin=errormargin;
			this.samplesneeded=samples;
			this.next=null;
			this.previous=null;
		}

		//Metodos
		public float geterrormargin()
		{
			return this.errormargin;
		}
		public float getcoveragepercentage()
		{
			return this.coveragepercentage;
		}
		public float getnormalfactor()
		{
			return this.normalfactor;
		}
		public float getconfidencelevel()
		{
			return this.confidencelevel;
		}
		public int getsamplesneeded()
		{
			return this.samplesneeded;
		}
		public hptest getnext()
		{
			return this.next;
		}
		public hptest getprevious()
		{
			return this.previous;
		}
		public void setnode(String initialtext, float coveragepercentage, float confidencelevel, float normalfactor, float errormargin, int samples)
		{
						this.initialtext=initialtext;
						this.coveragepercentage=coveragepercentage;
						this.confidencelevel=confidencelevel;
						this.normalfactor=normalfactor;
						this.errormargin=errormargin;
						this.samplesneeded=samplesneeded;
						this.next=null;
						this.previous=null;
		}
		public void setnext(hptest siguiente)
		{
			this.next=siguiente;
		}
		public void setprevious(hptest previous)
		{
			this.previous=previous;
		}


}

class conf
{
	hptest head;

	// Constructor
	public conf()
	{
		this.head=null;
	}

	//Metodos 
	public void insertelement(String initialtext, float coveragepercentage, float confidencelevel, float normalfactor, float errormargin, int samples)
	{
			hptest newnode = new hptest(initialtext,coveragepercentage,confidencelevel,normalfactor,errormargin,samples );
			this.head = newnode;
			newnode.setnext(null);
			newnode.setprevious(null);
	}
	public void insertend(String initialtext, float coveragepercentage, float confidencelevel, float normalfactor, float errormargin, int samples)
	{
		hptest nuevo = new hptest(initialtext,coveragepercentage,confidencelevel,normalfactor,errormargin,samples);
		hptest tracer1=this.head;

		if (tracer1.getnext()==null)
			{
				tracer1.setnext(nuevo);
				nuevo.setprevious(tracer1);
			}
		else
			{
				while(tracer1.getnext()!=null)//do
				{
					tracer1=tracer1.getnext();
				}//while(tracer1.getnext()!=null);
				tracer1.setnext(nuevo);
				nuevo.setprevious(tracer1);
			}
	}

	// Salida
	public void output()
	{
		hptest tracer1=this.head;
		System.out.println("Configuration file - Hypothesis Test");
		System.out.println("Coverage Percentage \t"+ tracer1.getcoveragepercentage());
		System.out.println("Confidence Level \t"+ tracer1.getconfidencelevel());
		System.out.println("Normal Factor \t"+ tracer1.getnormalfactor());
		System.out.println("Error Margin \t"+"Samples needed");

		do //while(tracer1.getnext()!=null)//do
		{
			System.out.println(tracer1.geterrormargin()+"\t"+"\t"+"\t"+tracer1.getsamplesneeded());
			tracer1=tracer1.getnext();
		}while(tracer1.getnext()!=null);
		if(tracer1.getnext()==null)
			{
				System.out.println(tracer1.geterrormargin()+"\t"+"\t"+"\t"+tracer1.getsamplesneeded());
			}
	}


		// Error margin Adaption
		public int  errormarginadaption(int segments)
		{
			int samplesneeded=0;
			hptest tracer1=this.head;
			//System.out.println(segments);

			do //while(tracer1.getnext()!=null)
			{

				if(segments>tracer1.getsamplesneeded())
				{
					samplesneeded = tracer1.getsamplesneeded();
					return samplesneeded;
				}
				tracer1=tracer1.getnext();
			}while(tracer1.getnext()!=null|| (segments>=tracer1.getsamplesneeded()));

			if (segments<tracer1.getsamplesneeded()) {samplesneeded = tracer1.getsamplesneeded();}
			//System.out.println(samplesneeded);

			// Retorna el numero de muestras requeridas
			return samplesneeded;
		}


	//Get margin
	public float margintouse(int samples)
	{
		hptest tracer1=this.head;
		float margin=0;
		while(tracer1.getnext()!=null)
		{

			if(samples==tracer1.getsamplesneeded())
			{
				margin = tracer1.geterrormargin();
				return margin;
			}
			tracer1=tracer1.getnext();
		}

			return margin;
	}

	// Metodo para borrar el primer elemento de la lista
	public void deletefirst()
	{
		hptest tracer1 = this.head;
		this.head=tracer1.getnext();
		tracer1=null;
	}


}
