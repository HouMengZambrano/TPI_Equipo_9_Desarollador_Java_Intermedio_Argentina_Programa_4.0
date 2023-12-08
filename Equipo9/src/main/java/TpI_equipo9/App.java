package TpI_equipo9;



import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
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
							Calendar cal= Calendar.getInstance();
							cal.set(Calendar.HOUR, 0);
							cal.set(Calendar.MINUTE, 0);
							Timestamp hoy=new Timestamp(cal.getTimeInMillis());
						
							List<Incidente> incs=iService.buscarTodos().stream().filter((in)->in.getFechaAlta().after(hoy)).collect(Collectors.toList());
							
							if(!incs.isEmpty()) {
								incs.forEach(in->System.out.println(in.toString()));
							}
							else
							{
								System.out.println("No se encontraron registros de incidentes a la fecha de hoy");		
							}
						break;
					case 3:
											 Timestamp[] fechas = ConsolaService.rangoFechas();
						tecs= tService.buscarTodos().stream().filter(t->t.getFechaBaja()==null).filter(t->t.getIncidentes().size()>0).collect(Collectors.toList());
						
						if(!tecs.isEmpty()) {	
							tecs.sort((t1,t2)->t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado") && i.getFechaResol().after(fechas[0]) && i.getFechaResol().before(fechas[1])).collect(Collectors.toList()).size()-t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado") && i.getFechaResol().after(fechas[0]) && i.getFechaResol().before(fechas[1])).collect(Collectors.toList()).size());
							if(!tecs.isEmpty()) {
								tec=tecs.get(tecs.size()-1);
								System.out.println("El tecnico con mas incidentes resueltos entre las fechas "+fechas[0]+" y "+fechas[1]+" es: \n"+tec.toString());
							}
							else
							{
								System.out.println("No se encontraron registros de tecnicos que tengan incidentes solucionados dentro de las fechas "+fechas[0]+" y "+fechas[1]);	
							}
						}
						else
						{
							System.out.println("No se encontraron registros de tecnicos que no esten dados de baja o que tengan incidentes asociados en las fechas "+fechas[0]+" y "+fechas[1]);
							
						}
						break;
					case 4:
						fechas = ConsolaService.rangoFechas();
						List<Especialidad> espe= eService.buscarTodos();
						if(!espe.isEmpty()) {
							espe.forEach(e->{
							tecs= tService.buscarPorEspecialidad(e).stream().filter(t->t.getFechaBaja()==null).collect(Collectors.toList());
							tecs.sort((t1,t2)->t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado") && i.getFechaResol().after(fechas[0]) && i.getFechaResol().before(fechas[1])).collect(Collectors.toList()).size()-t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado") && i.getFechaResol().after(fechas[0]) && i.getFechaResol().before(fechas[1])).collect(Collectors.toList()).size());
							if(!tecs.isEmpty()) {
							tec=tecs.get(tecs.size()-1);
							System.out.println("El tecnico con mas incidentes resueltos en la categoria "+e.getNombre()+" entre las fechas "+fechas[0]+" y "+fechas[1]+" es: \n"+tec.toString());
							}else
							{
								System.out.println("No se encontraron registros de tecnicos que tengan incidentes solucionados en la categiria: "+e.getNombre()+" dentro de las fechas "+fechas[0]+" y "+fechas[1]);
							}
							});
						}else
						{
							System.out.println("No se encontraron registros de especialidades");
						}
						
				
						break;
					case 5:
			
						tecs= tService.buscarTodos().stream().filter(t->t.getFechaBaja()==null).filter(t->t.getIncidentes().size()>0).collect(Collectors.toList());
				
						if(!tecs.isEmpty()) {
								tecs.sort(
								(t1,t2)->Long.compare(t1.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado")).min((i1,i2)->Long.compare(i1.tiempoTranscurrido(),i2.tiempoTranscurrido())).get().tiempoTranscurrido(),t2.getIncidentes().stream().filter(i->i.getEstadoActual().toLowerCase().equals("solucionado")).min((i1,i2)->Long.compare(i1.tiempoTranscurrido(),i2.tiempoTranscurrido())).get().tiempoTranscurrido()));
								tec=tecs.get(0);
								System.out.println("El tecnico mas rapido es: \n"+tec.toString());
								
						}else
						{
							System.out.println("No se encontraron registros de tecnicos que no esten dados de baja o que tengan incidentes asociados");
						}
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
				System.out.println("Elija tecnico: ");
				tecActual=AdminTecnicos.menuTecnicos(false);
				if(tecActual==null) menuPrincipal();
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
    
   
    

