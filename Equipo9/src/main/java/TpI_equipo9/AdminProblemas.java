package TpI_equipo9;

import TpI_equipo9.Modelos.Especialidad;
import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Servicio;
import TpI_equipo9.Services.ProblemaService;
import TpI_equipo9.Services.ServicioService;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.EspecialidadService;

import java.util.ArrayList;
import java.util.List;

public class AdminProblemas {
	
		
		static Problema probActual=null;
		static Problema prob;
		static ProblemaService srv= new ProblemaService();
		static List<Problema> problemas;
		static List<Servicio> servs;
		static List<Especialidad> esps;
		static EspecialidadService eService= new EspecialidadService(); 
		static ServicioService sService= new ServicioService();
	
	 public static Problema menuProblemas()
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(probActual!=null)
	    		System.out.println("\nDATOS PROBLEMA ACTUAL: \n"+probActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUN PROBLEMA SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos los problemas ->\n"+
	    			"2) Buscar problema ->\n"+
	    			"3) Ingresar problema ->\n"+
	    			"4) Eliminar registro del problema actual ->\n"+
	    			"5) Modificar registro del problema actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija una especialidad: \n");
						problemas=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) por tipo ->\n"+
				    			"3) por tiempo maximo ->\n"+
				    			"4) por especialidad ->\n"+
				    			"4) por servicio ->\n"+
				    			"5) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 5);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	prob= srv.buscarPorID(id);
								 	if(probActual==null)
								 	{							 		
								 		probActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+prob.toString()+"\n s/n?"))
								 		{
								 			probActual=prob;
								 		}
								 	}
								 break;
							 case 2:
								 String tipo=ConsolaService.pedirTexto("Ingrese tipo:");
								 problemas= srv.buscarPorTipo(tipo);
							 	 seleccionar();
								 break;
							 case 3:
								 Double Tmax=Double.parseDouble(ConsolaService.pedirTexto("Ingrese tiempo maximo (HS): "));
								 problemas= srv.buscarPorTiempoMax(Tmax);
							 	 seleccionar();
								 break;
							 case 4:
								 System.out.println("Busque especialidad: ");
								 problemas= srv.buscarPorEspecialidad(AdminEspecialidades.menuEspecialidades());
								 	seleccionar();
								 break;
								 default:
									 break;
						 }
					break;
				case 3:
						prob=new Problema();
						prob.setTipo(ConsolaService.pedirTexto("Ingrese el tipo de problema: "));
						prob.setTiempoMax(Double.parseDouble(ConsolaService.pedirTexto("Ingrese tiempo maximo(HS):")));
						System.out.println("Elija una especialidad: ");
						do
						{
							prob.agregarEspecialidad(AdminEspecialidades.menuEspecialidades());
						}while(ConsolaService.preguntaSioNo("Desea agregar otra especialidad? s/n"));
						System.out.println("Elija un servicio: ");
						do
						{
							prob.agregarServicio(AdminServicios.menuServicios());
						}while(ConsolaService.preguntaSioNo("Desea agregar otro servicio? s/n"));
						esps=prob.getEspecialidades();
						srv.ingresarProblema(prob);
						
						if(esps!=null && !esps.isEmpty())
						{
							esps.forEach(e->{
								e.agregarProblema(prob);
								eService.ActualizarDatos(e);
							});
						}
						if(servs!=null && !servs.isEmpty())
						{
							servs.forEach(s->{
								s.agregarProblema(prob);
								sService.ActualizarDatos(s);
							});
						}
						
						
						probActual=prob;
					break;
				case 4:
						if(probActual!=null)
						{
							if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+probActual.toString()+"\n s/n?"))
							{
								srv.borrarDatos(probActual);
								System.out.println("Se han borrados los datos del servicio.");
							}
						}
					break;
				case 5:
						if(probActual!=null)
						{
							prob=probActual;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Tipo->\n"+
					    			"2) Tiempo maximo ->\n"+
					    			"3) Especialidades ->\n"+
					    			"4) Servicios->\n"+
					    			"7) <- Volver.\n");
							opt1=ConsolaService.rangoOpciones(1, 7);
							List<Especialidad> espsEl=new ArrayList<Especialidad>();
							List<Servicio> serEl=new ArrayList<Servicio>();
							 switch (opt1)
							 {
								 case 1:
									 prob.setTipo(ConsolaService.pedirTexto("ingrese nombre: "));
									 break;
								 case 2:
									 prob.setTiempoMax(Double.parseDouble(ConsolaService.pedirTexto("Ingrese tiempo maximo (HS): ")));
									 break;
								 case 3:
									 if(ConsolaService.preguntaSioNo("Desea agregar una especialidad nueva? s/n"))
									 	{
											do
											{
												prob.agregarEspecialidad(AdminEspecialidades.menuEspecialidades());
											}while(ConsolaService.preguntaSioNo("Desea agregar otro tecnico? s/n"));
											esps=prob.getEspecialidades();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar una especialidad? s/n"))
									 	{
									 		esps=prob.getEspecialidades();
											do
											{
												esps.forEach((e)->System.out.println(esps.indexOf(e)+")\n "+e.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, esps.size());
												Especialidad es=esps.get(indx);
												prob.agregarEspecialidad(es);
												esps.remove(es);
												espsEl.add(es);
												
											}while(ConsolaService.preguntaSioNo("Desea eliminar otra especialidad? s/n"));
									 	}
									break;
								 case 4:
									 if(ConsolaService.preguntaSioNo("Desea agregar un servicio nuevo? s/n"))
									 	{
											do
											{
												prob.agregarServicio(AdminServicios.menuServicios());
											}while(ConsolaService.preguntaSioNo("Desea agregar otro servicio? s/n"));
											servs=prob.getServicios();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar una especialidad? s/n"))
									 	{
									 		servs=prob.getServicios();
											do
											{
												servs.forEach((s)->System.out.println(servs.indexOf(s)+")\n "+s.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, servs.size());
												Servicio ser=servs.get(indx);
												prob.agregarServicio(ser);
												servs.remove(ser);
												serEl.add(ser);
												
											}while(ConsolaService.preguntaSioNo("Desea eliminar otra especialidad? s/n"));
									 	}
									 break;
							
							 }
							 	System.out.println("Datos viejos: \n"+probActual.toString());
								System.out.println("Datos nuevos: \n"+prob.toString());
								if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
								{
									probActual=srv.ActualizarDatos(prob);
									if(esps!=null)
									{
										esps.forEach(e->{
											e.agregarProblema(probActual);
											eService.ActualizarDatos(e);
										});
									}
									if(espsEl!=null)
									{
										espsEl.forEach(e->{
											e.eliminarProblema(probActual);
											eService.ActualizarDatos(e);
										});
									}
									if(servs!=null)
									{
										servs.forEach(e->{
											e.agregarProblema(probActual);
											sService.ActualizarDatos(e);
										});
									}
									if(serEl!=null)
									{
										serEl.forEach(s->{
											s.eliminarProblema(probActual);
											sService.ActualizarDatos(s);
										});
									}
								
								}
						break;
					}
					break;
					default:
						return probActual;
				}
	    		return menuProblemas();
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	problemas.forEach((i)->System.out.println(problemas.indexOf(i)+")\n "+i.toString()+"\n"));
			System.out.println(problemas.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, problemas.size());
			if(o<problemas.size()) 
	 		{
	 		  prob= problemas.get(o);
			 	if(probActual==null)
			 	{							 		
			 		probActual= problemas.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al incidente :\n"+prob.toString()+"\n s/n?"))
			 		{
			 			probActual=prob;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
