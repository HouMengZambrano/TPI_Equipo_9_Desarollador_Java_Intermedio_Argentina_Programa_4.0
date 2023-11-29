package TpI_equipo9;


import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.Modelos.*;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.IncidenteService;
import TpI_equipo9.Services.TecnicoService;

public class App 
{
	static Cliente clActual;
	static Tecnico tecActual;
	static Servicio servActual;
	static Incidente incActual; 
	static Especialidad espActual;
	static Problema probActual;
	static TecnicoService tService=new TecnicoService();
	static IncidenteService iService=new IncidenteService();
    public static void main( String[] args )
    {
    	
    	menuPrincipal();
    }
    
    static void menuPrincipal()
    {
    	int opt=0;
    	System.out.println("Bienvenido! ingrese una de las siguentes opciones: \n");
    	System.out.println(
    			"1) Area RR.HH ->\n"+
    			"2) Area comercial ->\n"+
    			"3) Mesa de ayuda ->\n"+
    			"4) Menu Tecnico ->\n"+
    			"5) Administar especialidades ->\n"+
    			"6) Administar problemas ->\n"+
    			"7) Administar servicios ->\n"+
    			"8) Salir.\n");
    	opt=ConsolaService.rangoOpciones(1, 8);
		switch (opt)
		{
			case 1:
			{
				System.out.println(
		    			"1) Administar tecnicos ->\n"+
		    			"2) Reporte diario \n"+
		    			"3) Tecnico con mas incidentes resueltos ->\n"+
		    			"4) Tecnico con mas incidentes resuelto por especialidad->\n"+
		    			"5) Tecnico mas veloz ->\n"+
		    			"6) <- volver.\n");
				opt=ConsolaService.rangoOpciones(1, 6);
				switch(opt)
				{
					case 1:
						
						tecActual=AdminTecnicos.menuTecnicos();
					break;
					case 2:
							Date hoy=new Date(Calendar.getInstance().getTimeInMillis());
							List<Incidente> incs=iService.buscarTodos().stream().filter((in)->in.getFechaAlta().compareTo(hoy)==0).collect(Collectors.toList());
							incs.forEach(in->in.toString());
						break;
					case 3:
						System.out.println();
					
						break;
				}
				
			}
				break;
			case 2:
				clActual=AdminClientes.menuClientes();
				menuPrincipal();
				break;
			case 3:
				incActual=AdminIncidentes.MesaAtuda();
				menuPrincipal();
				break;
			case 4:
				AdminIncidentes.menuTecnico(incActual);
				break;
			case 5:
				espActual=AdminEspecialidades.menuEspecialidades();
				menuPrincipal();
				break;
			case 6:
				probActual=AdminProblemas.menuProblemas();
				menuPrincipal();
				break;
			case 7:
				servActual=AdminServicios.menuServicios();
				menuPrincipal();
				break;
				default:
					break;
			}
		System.out.println("Saliendo...");
		}
    }
    
   
    

