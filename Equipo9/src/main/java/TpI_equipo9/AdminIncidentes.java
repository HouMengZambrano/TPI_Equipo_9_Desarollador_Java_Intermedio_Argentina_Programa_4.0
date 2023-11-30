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
		 Cliente cl;
		 int indx;
		 
		 	// INGRESO DE EL CLIENTE QUE HIZO EL RECLAMO
			do {
				System.out.println("Elija cliente: ");
				cl=AdminClientes.menuClientes(); // DESDE EL MENU DE CLIENTES SE PERMITE BUSCAR EL CLIENTE DESEADO Y SE LO ASIGNA AL ATRIBUTO CORRESPONDIENTE
			}while(cl==null); // PARA EVITAR QUE EL PROGRAMA SIGA SI NO SE ELIJIO NINGUN CLIENTE. 
			inc.setCliente(cl);
			
			// INGRESO DE EL SERVICIO AL CUAL SE RELACIONA EL RECLAMO
			do
			{	
				System.out.println("Elija servicio: ");
				List<Servicio> sers=cl.getServicios(); // SE BUSCA DENTRO DE LOS SERVICIOS ASOCIADOS AL CLIENTE ELEGIDO
				sers.forEach((s)->System.out.println(sers.indexOf(s)+")\n "+s.toString()+"\n"));
				indx=ConsolaService.rangoOpciones(0, sers.size()-1);
				probs=pService.buscarPorServicios(sers.get(indx));
				break;
			}while(true);
			
			// INGRESO DE EL/LOS PROBLEMA/S RELACIONADOS AL SERVICIO
			System.out.println("Elija problema/s: ");	
			do
			{
				probs.forEach((p)->System.out.println(probs.indexOf(p)+")\n "+p.toString()+"\n"));
				indx=ConsolaService.rangoOpciones(0, probs.size()-1);
				inc.agregarProblema(probs.get(indx));
				probs.remove(indx);
			}while(ConsolaService.preguntaSioNo("Desea agregar otro problema? s/n"));
			
			// INGRESO DE LA DESCRIPCION DEL INCIDENTE
			inc.setDescripcion(ConsolaService.pedirTexto("Ingrese una descripcion\n"));
			
			// SETEO DEL TIEMPO MAXIMO DE RESOLUCION EN BASE A LA SUMA DE LOS TIEMPOS ESTIMADOS DE CADA PROBLEMA.
			probs.forEach(p->tMax+=p.getTiempoMax());
			inc.setTiempoResolucion(tMax);
			tMax=Double.parseDouble(ConsolaService.pedirTexto("Ingrese el tiempo estimado de resolucion(HS) tiempo maximo("+tMax+"): "));
			if(tMax<inc.getTiempoResolucion()) inc.setTiempoResolucion(tMax);
				
			// INGRESO DEL TECNICO AISLANDO SOLO LOS QUE CORRESPONDAN A LA/LAS ESPECIALIDAD/ES RELACIONADAS A LOS PROBLEMAS DADOS
			do {
					System.out.println("Elija tecnico: ");
					List<Especialidad> esps=new ArrayList<Especialidad>();
					tecs=new ArrayList<Tecnico>();
					inc.getProblemas().forEach(p->esps.addAll(eService.buscarPorProblema(p)));
					esps.forEach(e->
								tService.buscarPorEspecialidad(e).forEach(t->
								{
									if(!tecs.contains(t))tecs.add(t);
								})
							);
					tecs.forEach((t)->System.out.println(tecs.indexOf(t)+")\n "+t.toString()+"\n"));
					indx=ConsolaService.rangoOpciones(0, tecs.size()-1);
					inc.setTecnico(tecs.get(indx));
					break;
			}while(true);
			
			//SETEO DE FECHA DE ALTA EN EL MOMENTO DE LA CREACION
			inc.setFechaAlta(new Date(Calendar.getInstance().getTimeInMillis()));
			
			
			// PERSISTENCIA DE DATOS5
			
			
			
			
			srv.ingresarIncidente(inc);
			cl.agregarIncidente(inc);
			cService.ActualizarDatos(cl);
			inc.getTecnico().agregarIncidente(inc);
			tService.ActualizarDatos(inc.getTecnico());
			
			
			incActual=inc;
			reportar();
			return incActual;
	    }
		 
		 
		
	 public static void menuTecnico(Incidente incidente)
	    {
		 
						if(incidente!=null)
						{
							int opt1=0;
							inc=incidente;
							do {
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Agregar colchon de horas->\n"+
					    			"2) Cambiar complejidad ->\n"+
					    			"3) Cambiar estado ->\n"+
					    			"4) <- Volver.\n");
							 opt1=ConsolaService.rangoOpciones(1, 4);
							 switch (opt1)
							 {
								 case 1:
									 if(inc.isComplejo())
									 inc.setTiempoResolucion(Double.parseDouble(ConsolaService.pedirTexto("ingrese tiempo de resolucion: ")));
									 else
									 {
										 System.out.println("El problema no es complejo, no se permite a agregar mas horas.");
									 }
									 break;
								 case 2:
									 inc.setComplejo(ConsolaService.preguntaSioNo("El incidente es complejo s/n?: "));
									 break;
								 case 3:
									 inc.cheakearEstado();
									 break;
							 }
							
							}while(opt1!=4);
							 	System.out.println("Datos viejos: \n"+incidente.toString());
								System.out.println("Datos nuevos: \n"+inc.toString());
								if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
								{
									incidente=srv.ActualizarDatos(inc);
								
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
	 
	 public static void reportar()
	 {

			if(incActual.getCliente().getMetodoE()==Cliente.MetodoNotificacion.EMAIL)
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incActual.getCliente().toString()+"\n a travez de su email sobre el registro del incidente: \n"+incActual.toString());
	    	}
	    	else
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incActual.getCliente().toString()+"\n a travez de su Nro de Whatsapp sobre el registro del incidente: \n"+incActual.toString());  	
	    	}
			
		  	if(incActual.getTecnico().getMetodoE()==Tecnico.MetodoNotificacion.EMAIL)
	    	{
	    		System.out.println("Se ha notificado al tecnico: \n"+incActual.getTecnico().toString()+"\n a travez de su email sobre el registro del incidente: \n"+incActual.toString());
	    	}
	    	else
	    	{
	    		System.out.println("Se ha notificado al tliente: \n"+incActual.getTecnico().toString()+"\n a travez de su Nro de Whatsapp sobre el registro del incidente: \n"+incActual.toString());  	
	    	}
		  	incActual.cambiarEstado(new EstadoReportado());
			
	 }
	
	 
	 
}
	  
