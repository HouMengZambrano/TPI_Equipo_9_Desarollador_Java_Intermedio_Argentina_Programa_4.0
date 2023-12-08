package TpI_equipo9.Services;



import java.sql.Timestamp;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolaService {
	static Scanner scn= new Scanner(System.in);

	
	public static int rangoOpciones(int min, int max)
	{
		int in=min-1;
		while(in<min || in>max)
		{
			System.out.println("ingrese un valor entre "+min+" y "+max+" : \n");
			try {
				in=scn.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("El valor ingresado no es numerico!");
				in=min-1;
				scn.nextLine();
			}
		}
		return in;
	}

	public static Timestamp[] rangoFechas()
	{
		Timestamp[] fechas= new Timestamp[2];
		String texto ="";
		String[] datos;
		Calendar cal =Calendar.getInstance();
		int fi=0;
		do {
			System.out.println("Ingrese una fecha (DD/MM/AAAA): ");
			texto=scn.nextLine();
			if(texto.equals("")) {
				texto=scn.nextLine();
			}
			datos=texto.split("/");
			if(datos.length==3)
			{
				cal.set(Calendar.DATE, Integer.parseInt(datos[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(datos[1])-1);
				cal.set(Calendar.YEAR, Integer.parseInt(datos[2]));
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				fechas[fi]=new Timestamp(cal.getTimeInMillis());
				fi++;
			}
			
			
		}while(fi<2);
		if(fechas[0].after(fechas[1]))
		{
			Timestamp fecha=fechas[0];
			fechas[0]=fechas[1];
			fechas[1]=fecha;
		}
		return fechas;
		
	}

	
	public static int pedirEntero(String mensaje)
	{
		int entero;
		do {
			try
			{
				if(mensaje==null)System.out.print("Ingrese un valor numerico entero: ");
				else System.out.print(mensaje);
				entero=scn.nextInt();
				return entero;
			}catch(InputMismatchException e)
			{
				System.out.println("Error de tipeo. Solo se aceptan numeros enteros");
				scn.next();
			}
		}while(true);
	}
	
	public static String pedirTexto(String mensaje) 
	{
		String texto;
		if(mensaje==null)System.out.print("Ingrese un texto: ");
		else System.out.print(mensaje);
		texto=scn.nextLine();
		if(texto.equals(""))
			texto=scn.nextLine();
		
		return texto;
	}
	public static boolean preguntaSioNo(String pregunta)
	{
		do {
			if(pregunta==null)System.out.println("ingrese (s)i o (n)o.");
			else System.out.println(pregunta);
			String respuesta=scn.next().toLowerCase();
			if(respuesta.contains("s")) {
				scn.nextLine();
				return true;
			} else if(respuesta.contains("n"))
			{
				scn.nextLine();
				return false;
			}
			else
			{
				System.out.println("ingrese (s)i o (n)o.");
			}
		}while(true);
	}
}
