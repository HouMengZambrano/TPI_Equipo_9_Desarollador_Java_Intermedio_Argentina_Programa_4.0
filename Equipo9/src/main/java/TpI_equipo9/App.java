package TpI_equipo9;


import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import TpI_equipo9.Modelos.*;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.EspecialidadService;
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
	static EspecialidadService eService=new EspecialidadService();
	static List<Tecnico> tecs;
	static List<Incidente> incs;
	static Tecnico tec;
	static Tecnico inc;
	
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
			
						tecActual=AdminTecnicos.menuTecnicos(true);
					break;
					case 2:
							Date hoy=new Date(Calendar.getInstance().getTimeInMillis());
							List<Incidente> incs=iService.buscarTodos().stream().filter((in)->in.getFechaAlta().compareTo(hoy)==0).collect(Collectors.toList());
							incs.forEach(in->in.toString());
						break;
					case 3:
						
						tecs= tService.buscarTodos().stream().filter(t->t.getFechaBaja()!=null).collect(Collectors.toList());
						tecs.sort((t1,t2)->t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").collect(Collectors.toList()).size()-t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").collect(Collectors.toList()).size());
						tec=tecs.get(0);
						System.out.println("El tecnico con mas incidentes resueltos es: \n"+tec.toString());
						break;
					case 4:
						List<Especialidad> espe= eService.buscarTodos();
						espe.forEach(e->{
						tecs= tService.buscarPorEspecialidad(e).stream().filter(t->t.getFechaBaja()!=null).collect(Collectors.toList());
						tecs.sort((t1,t2)->t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").collect(Collectors.toList()).size()-t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").collect(Collectors.toList()).size());
						tec=tecs.get(0);
						System.out.println("El tecnico con mas incidentes resueltos en la categoria "+e.getNombre()+" es: \n"+tec.toString());
						});
						
				
						break;
					case 5:
					
						tecs= tService.buscarTodos().stream().filter(t->t.getFechaBaja()!=null).collect(Collectors.toList());
						tecs.sort(
								(t1,t2)->Long.compare(t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").max(Comparator.comparingLong(Incidente::tiempoTranscurrido)).get().tiempoTranscurrido(),t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase()=="solucionado").max(Comparator.comparingLong(Incidente::tiempoTranscurrido)).get().tiempoTranscurrido()));
						tec=tecs.get(0);
						System.out.println("El tecnico mas rapido es: \n"+tec.toString());
				
						break;
				}
				
				menuPrincipal();
			}
				break;
			case 2:
				clActual=AdminClientes.menuClientes(true);
				menuPrincipal();
				break;
			case 3:
				if(ConsolaService.preguntaSioNo("Desea ingresar un incidente nuevo? S/N"))
				{					
					incActual=AdminIncidentes.MesaAtuda();
				}
				menuPrincipal();
				break;
			case 4:
				tecActual=null;
				incActual=null;
				do{
					System.out.println("Elija tecnico: ");
					tecActual=AdminTecnicos.menuTecnicos(false);
				}while(tecActual==null);
				incs=iService.buscarPorTecnico(tecActual);
				if(incs.isEmpty()) 
				{
					System.out.println("El tecnico no posee incidentes en su registro");
					menuPrincipal();
					System.exit(0);
				}
				System.out.println("Elija incidente: ");
				incs.forEach((i)->System.out.println(incs.indexOf(i)+")\n "+i.toString()+"\n"));
				int o=ConsolaService.rangoOpciones(0, incs.size()-1);
				incActual=incs.get(o);
				AdminIncidentes.menuTecnico(incActual);
				menuPrincipal();
				break;
			case 5:
				espActual=AdminEspecialidades.menuEspecialidades(true);
				menuPrincipal();
				break;
			case 6:
				probActual=AdminProblemas.menuProblemas(true);
				menuPrincipal();
				break;
			case 7:
				servActual=AdminServicios.menuServicios(true);
				menuPrincipal();
				break;
				default:
					
					break;
			}
		System.out.println("Saliendo...");
		System.exit(0);
		}
    }
    
   
    

