package TpI_equipo9;


import TpI_equipo9.Services.ConsolaService;

public class App 
{
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
    	opt=ConsolaService.rangoOpciones(1, 5);
		switch (opt)
		{
			case 1:
				AdminClientes.menuClientes();
				menuPrincipal();
				break;
			case 2:
				AdminTecnicos.menuTecnicos();
				menuPrincipal();
				break;
			case 3:
				break;
			case 4:
				break;
				default:
					break;
			}
		System.out.println("Saliendo...");
		}
    }
    
   
    

