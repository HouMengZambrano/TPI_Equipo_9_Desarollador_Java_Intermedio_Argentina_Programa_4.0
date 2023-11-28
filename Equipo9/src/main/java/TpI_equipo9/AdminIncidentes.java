package TpI_equipo9;

import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Modelos.EstadoReportado;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Tecnico;
import TpI_equipo9.Services.IncidenteService;
import TpI_equipo9.Services.ProblemaService;
import TpI_equipo9.Services.TecnicoService;
import TpI_equipo9.Services.ClienteService;
import TpI_equipo9.Services.ConsolaService;

import java.util.ArrayList;
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
	 public static Incidente menuIncidentes()
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(incActual!=null)
	    		System.out.println("\nDATOS INCIDENTE ACTUAL: \n"+incActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUN INCIDENTE SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos los incidentes ->\n"+
	    			"2) Buscar incidente ->\n"+
	    			"3) Ingresar incidente ->\n"+
	    			"4) Eliminar registro del incidente actual ->\n"+
	    			"5) Modificar registro del incidente actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija un incidente: \n");
						incidentes=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) Por fecha de resolucion ->\n"+
				    			"3) Por fecha de alta ->\n"+
				    			"4) Por complejidad ->\n"+
				    			"5) Por cliente ->\n"+
				    			"6) Por tecnico ->\n"+
				    			"7) Por tiempo de resolucion ->\n"+
				    			"8) Por estado actual ->\n"+ // pensarlo
				    			"9) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 9);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	inc= srv.buscarPorID(id);
								 	if(incActual==null)
								 	{							 		
								 		incActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+inc.toString()+"\n s/n?"))
								 		{
								 			incActual=inc;
								 		}
								 	}
								 break;
							 case 2:
								 String fecha=ConsolaService.pedirTexto("Ingrese el fecha de resolucion (AAAA/MM/DD):");
								 incidentes= srv.buscarPorFechaAlta(fecha);
							 	 seleccionar();
								 break;
							 case 3:
								 fecha=ConsolaService.pedirTexto("Ingrese el fecha de alta (AAAA/MM/DD):");
								 incidentes= srv.buscarPorFechaAlta(fecha);
							 	seleccionar();
								 break;
							 case 4:
								 int complejidad=ConsolaService.pedirEntero("Ingrese la complejidad (0 a 9):");
							 	 incidentes= srv.buscarPorComplejidad(complejidad);
							 	seleccionar();
								 break;
							 case 5:
								 System.out.println("Busque cliente: ");
							 	 incidentes= srv.buscarPorCliente(AdminClientes.menuClientes());
							 	seleccionar();
								 break;
							 case 6:
								 System.out.println("Busque tecnico: ");
							 	 incidentes= srv.buscarPorTecnico(AdminTecnicos.menuTecnicos());
							 	seleccionar();
								 break;
							 case 7:
								  double tiempoRes=Double.parseDouble(ConsolaService.pedirTexto("ingrese tiempo de resolucion: "));
							 	 incidentes= srv.buscarPorTiempoRes(tiempoRes);
							 	seleccionar();
								 break;
							 case 8:
								  String estadoAct=ConsolaService.pedirTexto("ingrese estado actual (Reportado,Revision,Solucionado): ");
							 	 incidentes= srv.buscarPorEstado(estadoAct);
							 	seleccionar();
								 break;
								 default:
									 break;
						 }
					break;
				case 3:
						inc=new Incidente();
						System.out.println("Elija cliente: ");
						Cliente cl=AdminClientes.menuClientes();
						inc.setCliente(cl);
						System.out.println("Elija tecnico/s: ");
						do
						{
							Tecnico tc=AdminTecnicos.menuTecnicos();
							inc.agregarTecnico(tc);
						}while(ConsolaService.preguntaSioNo("Desea agregar otro tecnico? s/n"));
						inc.setDescripcion(ConsolaService.pedirTexto("Ingrese una descripcion\n"));
						//agregar problemas...
						tecs=inc.getTecnicos();
						inc.cambiarEstado(new EstadoReportado());
						srv.ingresarIncidente(inc);
						cl.agregarIncidente(inc);
						cService.ActualizarDatos(cl);
						if(tecs!=null)
						{
							tecs.forEach(t->{
								t.agregarIncidente(inc);
								tService.ActualizarDatos(t);
							});
						}
		
						incActual=inc;
					break;
				case 4:
						if(incActual!=null)
						{
							if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+incActual.toString()+"\n s/n?"))
							{
								srv.borrarDatos(incActual);
								System.out.println("Se han borrados los datos del incidente.");
							}
						}
					break;
				case 5:
						if(incActual!=null)
						{
							inc=incActual;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Tiempo de resolucion ->\n"+
					    			"2) Complejidad ->\n"+
					    			"3) Cliente ->\n"+
					    			"4) Tecnicos->\n"+
					    			"5) Fecha de resolucion ->\n"+
					    			"6) Fecha de alta ->\n"+
					    			"7) <- Volver.\n");
							opt1=ConsolaService.rangoOpciones(1, 7);
							List<Tecnico> tecEl=new ArrayList<Tecnico>();
							 switch (opt1)
							 {
								 case 1:
									 inc.setTiempoResolucion(Double.parseDouble(ConsolaService.pedirTexto("ingrese tiempo de resolucion: ")));
									 break;
								 case 2:
									 inc.setComplejidad(ConsolaService.pedirEntero("Ingresar complejidad(0 a 9): "));
									 break;
								 case 3:
									 System.out.println("Busque cliente: ");
									 inc.setCliente(AdminClientes.menuClientes());
									 
									 break;
								 case 4:
									 	if(ConsolaService.preguntaSioNo("Desea agregar un tecnico nuevo? s/n"))
									 	{
											do
											{
												inc.agregarTecnico(AdminTecnicos.menuTecnicos());
											}while(ConsolaService.preguntaSioNo("Desea agregar otro tecnico? s/n"));
											tecs=inc.getTecnicos();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar un tecnico? s/n"))
									 	{
									 		 tecs=inc.getTecnicos();
											do
											{
												tecs.forEach((t)->System.out.println(tecs.indexOf(t)+")\n "+t.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, tecs.size());
												Tecnico tc=tecs.get(indx);
												inc.agregarTecnico(tc);
												tecs.remove(indx);
												tecEl.add(tc);
												
											}while(ConsolaService.preguntaSioNo("Desea eliminar otro tecnico? s/n"));
									 	}
									 break;
								 case 5:
									 	String fecha=ConsolaService.pedirTexto("Ingresar fecha de resolucion (DD/MM/AAAA):");
									 	while(true)
									 	{
									 		if(fecha.split("/").length==3)
									 		{
									 			inc.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaResol");
									 			break;
									 		}
									 		else
									 		{
									 			System.out.println("Formato incorrecto!\n");
									 			fecha=ConsolaService.pedirTexto("Ingresar fecha de baja (DD/MM/AAAA): ");
									 		}
									 	}
									 break;
								 case 6:
									 	 fecha=ConsolaService.pedirTexto("Ingresar fecha de alta (DD/MM/AAAA): ");
									 	while(true)
									 	{
									 		if(fecha.split("/").length==3)
									 		{
									 			inc.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaAlta");
									 			break;
									 		}
									 		else
									 		{
									 			System.out.println("Formato incorrecto!\n");
									 			fecha=ConsolaService.pedirTexto("Ingresar fecha de alta (DD/MM/AAAA): ");
									 		}
									 	}
									 break;
							 }
							 System.out.println("Datos viejos: \n"+incActual.toString());
								System.out.println("Datos nuevos: \n"+inc.toString());
								if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
								{
									incActual=srv.ActualizarDatos(inc);
									if(tecs!=null)
									{
										tecs.forEach(t->{
											t.agregarIncidente(incActual);
											tService.ActualizarDatos(t);
										});
									}
									if(tecEl!=null)
									{
										tecEl.forEach(t->{
											t.eliminarIncidente(incActual);
											tService.ActualizarDatos(t);
										});
									}
									inc.getCliente().agregarIncidente(incActual);
									cService.ActualizarDatos(incActual.getCliente());
								}
						break;
					}
					break;
					default:
						return incActual;
				}
	    		return menuIncidentes();
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
	  
