package TpI_equipo9;

import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Modelos.Especialidad;
import TpI_equipo9.Modelos.EstadoReportado;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Servicio;
import TpI_equipo9.Modelos.Tecnico;
import TpI_equipo9.Services.IncidenteService;
import TpI_equipo9.Services.ProblemaService;
import TpI_equipo9.Services.TecnicoService;
import TpI_equipo9.Services.ClienteService;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.EspecialidadService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AdminIncidentes {
	
		
		static Incidente incActual=null;
		static Incidente inc;
		static IncidenteService srv= new IncidenteService();
		static List<Incidente> incidentes;
		static List<Tecnico> tecs;
		static List<Problema> probs;
		static ProblemaService pService= new ProblemaService(); 
		static TecnicoService tService= new TecnicoService();
		static ClienteService cService= new ClienteService();
		static EspecialidadService eService= new EspecialidadService();
		static double tMax=0;
		
		
		
	 public static Incidente MesaAtuda()
	    {
		 
		 
		 inc=new Incidente();
			
			System.out.println("Elija cliente: ");
			Cliente cl=AdminClientes.menuClientes();
			inc.setCliente(cl);
			
			System.out.println("Elija servicio: ");
			List<Servicio> sers=cl.getServicios();
				sers.forEach((s)->System.out.println(sers.indexOf(s)+")\n "+s.toString()+"\n"));
				int indx=ConsolaService.rangoOpciones(0, sers.size());
				probs=pService.buscarPorServicios(sers.get(indx));
				
			System.out.println("Elija problema/s: ");	
			do
			{	
				probs.forEach((p)->System.out.println(probs.indexOf(p)+")\n "+p.toString()+"\n"));
				indx=ConsolaService.rangoOpciones(0, probs.size());
				inc.agregarProblema(probs.get(indx));
				probs.remove(indx);
			}while(ConsolaService.preguntaSioNo("Desea agregar otro problema? s/n"));
			
			inc.setDescripcion(ConsolaService.pedirTexto("Ingrese una descripcion\n"));
			
			
			probs.forEach(p->tMax+=p.getTiempoMax());
			inc.setTiempoResolucion(tMax);
			tMax=Double.parseDouble(ConsolaService.pedirTexto("Ingrese el tiempo estimado de resolucion(HS)"));
			if(tMax<inc.getTiempoResolucion()) inc.setTiempoResolucion(tMax);
				
			System.out.println("Elija tecnico: ");
			List<Especialidad> esps=new ArrayList<Especialidad>();
			inc.getProblemas().forEach(p->esps.addAll(eService.buscarPorProblema(p)));
			esps.forEach(e->tecs.addAll(tService.buscarPorEspecialidad(e)));
			tecs=tecs.stream().distinct().collect(Collectors.toList());
			tecs.forEach((t)->System.out.println(tecs.indexOf(t)+")\n "+t.toString()+"\n"));
			indx=ConsolaService.rangoOpciones(0, tecs.size());
			
			inc.setFechaAlta(new Date(Calendar.getInstance().getTimeInMillis()));
			
			inc.setTecnico(tecs.get(indx));
			inc.cambiarEstado(new EstadoReportado());
			srv.ingresarIncidente(inc);
			cl.agregarIncidente(inc);
			cService.ActualizarDatos(cl);
			inc.getTecnico().agregarIncidente(inc);
			tService.ActualizarDatos(inc.getTecnico());
			
			
			incActual=inc;
			incActual.cheakearEstado();
			return incActual;
	    }
		 
		 
		
	 public static void menuTecnico(Incidente incidente)
	    {
						if(incidente!=null)
						{
							inc=incidente;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Agregar colchon de horas->\n"+
					    			"2) Complejidad ->\n"+
					    			"3) Estado ->\n"+
					    			"4) <- Volver.\n");
							int opt1=ConsolaService.rangoOpciones(1, 4);
							 switch (opt1)
							 {
								 case 1:
									 if(inc.isComplejo())
									 inc.setTiempoResolucion(Double.parseDouble(ConsolaService.pedirTexto("ingrese tiempo de resolucion: ")));
									 else
									 {
										 System.out.println("El problema no es complejo, no se permite a agregar mas horas.");
									 }
								 case 2:
									 inc.setComplejo(ConsolaService.preguntaSioNo("El incidente es complejo s/n?: "));
									 break;
								 case 3:
									 incidente.cheakearEstado();
									 return;
							 }
							 	System.out.println("Datos viejos: \n"+incActual.toString());
								System.out.println("Datos nuevos: \n"+inc.toString());
								if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
								{
									incActual=srv.ActualizarDatos(inc);
								
								}
						}
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	incidentes.forEach((i)->System.out.println(incidentes.indexOf(i)+")\n "+i.toString()+"\n"));
			System.out.println(incidentes.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, incidentes.size());
			if(o<incidentes.size()) 
	 		{
	 		  inc= incidentes.get(o);
			 	if(incActual==null)
			 	{							 		
			 		incActual= incidentes.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al incidente :\n"+inc.toString()+"\n s/n?"))
			 		{
			 			incActual=inc;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
