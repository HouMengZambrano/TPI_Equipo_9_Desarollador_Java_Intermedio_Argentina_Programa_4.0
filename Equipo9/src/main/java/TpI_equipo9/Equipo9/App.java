package TpI_equipo9.Equipo9;

import TpI_equipo9.Equipo9.Services.*;
import java.util.Scanner;

public class App 
{
	static Scanner scn= new Scanner(System.in);
    public static void main( String[] args )
    {
    	
    	menuPrincipal();
    }
    
    static void menuPrincipal()
    {
    	int opt=0;
    	System.out.println("Bienvenido! ingrese una de las siguentes opciones: \n");
    	System.out.println(
    			"1) Administar clientes ->\n"+
    			"2) Administar tecnicos ->\n"+
    			"3) Administar servicios ->\n"+
    			"4) Administar incidentes ->\n"+
    			"5) Salir.\n");
    	while(opt!=5)
		{
			switch (opt)
			{
			case 1:
				AdminClientes.menuClientes();
				opt=0;
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
				default:
					break;
			}
			opt=scn.nextInt();
		}
    	System.out.println("Saliendo...");
    }
    
   
    
}
