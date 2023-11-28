package TpI_equipo9;


import TpI_equipo9.Modelos.*;
import TpI_equipo9.Services.ConsolaService;

public class App 
{
	static Cliente clActual;
	static Tecnico tecActual;
	static Servicio servActual;
	static Incidente incActual; 
	static Especialidad espActual;
	static Problema probActual;
	
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
    			"5) Administar especialidades ->\n"+
    			"6) Administar problemas ->\n"+
    			"7) Salir.\n");
    	opt=ConsolaService.rangoOpciones(1, 7);
		switch (opt)
		{
			case 1:
				clActual=AdminClientes.menuClientes();
				clActual.getIncidentes().forEach(in->System.out.println(in.getDescripcion()));
				menuPrincipal();
				break;
			case 2:
				tecActual=AdminTecnicos.menuTecnicos();
				menuPrincipal();
				break;
			case 3:
				servActual=AdminServicios.menuServicios();
				menuPrincipal();
				break;
			case 4:
				incActual=AdminIncidentes.menuIncidentes();
				menuPrincipal();
				break;
			case 5:
				espActual=AdminEspecialidades.menuEspecialidades();
				menuPrincipal();
				break;
			case 6:
				probActual=AdminProblemas.menuProblemas();
				menuPrincipal();
				break;
				default:
					break;
			}
		System.out.println("Saliendo...");
		}
    }
    
   
    

