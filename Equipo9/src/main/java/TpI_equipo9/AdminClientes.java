package TpI_equipo9;

import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Servicio;
import TpI_equipo9.Services.ClienteService;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.IncidenteService;
import TpI_equipo9.Services.ServicioService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminClientes {
	
		
		static Cliente clActual=null;
		static Cliente cli;
		static List<Cliente> clientes;
		static List<Servicio> servs;
		static List<Incidente> incs;
		static ClienteService srv= new ClienteService();
		static ServicioService sService=new ServicioService();
		static IncidenteService iService= new IncidenteService();
		
	 public static Cliente menuClientes(boolean autorizacion)
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(clActual!=null)
	    		System.out.println("\nDATOS CLIENTE ACTUAL: \n"+clActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUN CLIENTE SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos los clientes ->\n"+
	    			"2) Buscar cliente ->\n"+
	    			"3) Ingresar cliente ->\n"+
	    			"4) Eliminar registro del cliente actual ->\n"+
	    			"5) Modificar registro del cliente actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija un cliente: \n");
						clientes=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) Por nombre ->\n"+
				    			"3) Por CUIT ->\n"+
				    			"4) Por nro Whatsapp ->\n"+
				    			"5) Por correo electronico ->\n"+
				    			"6) Por fecha de alta ->\n"+
				    			"7) Por fecha de baja ->\n"+
				    			"8) Por servicio ->\n"+
				    			"9) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 9);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	cli= srv.buscarPorID(id);
								 	if(clActual==null)
								 	{							 		
								 		clActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+cli.toString()+"\n s/n?"))
								 		{
								 			clActual=cli;
								 		}
								 	}
								 break;
							 case 2:
								 	String nombre=ConsolaService.pedirTexto("Ingrese el nombre: ");
								 	 clientes= srv.buscarPorNombre(nombre);
								 	seleccionar();
								 break;
							 case 3:
								 	String CUIT=ConsolaService.pedirTexto("Ingrese el CUIT:");
								 	 clientes= srv.buscarPorCUIT(CUIT);
								 	seleccionar();
								 break;
							 case 4:
								 String nro=ConsolaService.pedirTexto("Ingrese el Nro Whatsapp:");
							 	 clientes= srv.buscarPorNroWhatsapp(nro);
							 	seleccionar();
								 break;
							 case 5:
								 String email=ConsolaService.pedirTexto("Ingrese el correo electronico:");
							 	 clientes= srv.buscarPorNroWhatsapp(email);
							 	seleccionar();
								 break;
							 case 6:
								 String fecha=ConsolaService.pedirTexto("Ingrese el fecha de alta (AAAA/MM/DD):");
							 	 clientes= srv.buscarPorFechaAlta(fecha);
							 	seleccionar();
								 break;
							 case 7:
								 fecha=ConsolaService.pedirTexto("Ingrese el fecha de baja (AAAA/MM/DD):");
							 	 clientes= srv.buscarPorFechaBaja(fecha);
							 	seleccionar();
								 break;
							 case 8:
								 System.out.println("Busque servicio: ");
							 	 clientes= srv.buscarPorServicios(AdminServicios.menuServicios(false));
							 	seleccionar();
								 break;
						
								 default:
									 break;
						 }
					break;
				case 3:
					if(autorizacion)
					{
						String datos= ConsolaService.pedirTexto("Ingrese el nombre, CUIT, razon social, telefono, email\n");
						while(true) {
							if(datos.split(",").length==5)
								{
									cli=new Cliente(datos);
									String res=ConsolaService.pedirTexto("Ingrese metodo preferido de notificaciion (email o whatsapp)");
									if(res.toLowerCase().equals("email"))
									{
										cli.setMetodoE(Cliente.MetodoNotificacion.EMAIL);
									}
									else
									{
										cli.setMetodoE(Cliente.MetodoNotificacion.NRO_WHATSAPP);
									}
									srv.ingresarCliente(cli);
									clActual=cli;
									break;
							}
							else
							{
								System.out.println("cantidad de datos ingresados("+datos.split(",").length+") erronea, deben ser 5 campos.");
								datos= ConsolaService.pedirTexto("Ingrese el nombre, CUIT, razon social, telefono, email\n");
							}
						}
					}
					else
					{
						System.out.println("Usted no posee autorizacion para realizar esta operacion.");
					}
					break;
				case 4:
						if(autorizacion)
						{
							if(clActual!=null)
							{
								if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+clActual.toString()+"\n s/n?"))
								{
									if(clActual.getIncidentes()==null||clActual.getIncidentes().isEmpty())
									{
										srv.borrarDatos(clActual);
										System.out.println("Se han borrados los datos del cliente.");
										clActual=null;	
									}
									else
									{
										clActual.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
										System.out.println("Se ha dado de baja al cliente.");
										clActual=null;	
									}
									
								}
							}
						}
						else
						{
							System.out.println("Usted no posee autorizacion para realizar esta operacion.");
						}
					break;
				case 5:
					if(autorizacion)
					{
						if(clActual!=null)
						{
							cli=clActual;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Nombre ->\n"+
					    			"2) CUIT ->\n"+
					    			"3) Nro Whatsapp ->\n"+
					    			"4) Correo electronico ->\n"+
					    			"5) Fecha de baja ->\n"+
					    			"6) Fecha de alta ->\n"+
					    			"7) Servicios ->\n"+
					    			"7) Incidentes ->\n"+
					    			"8) <- Volver.\n");
							opt1=ConsolaService.rangoOpciones(1, 8);
							List<Servicio> servEl = new ArrayList<Servicio>();
							 switch (opt1)
							 {
							 
								 case 1:
									 cli.setNombre(ConsolaService.pedirTexto("Ingresar nuevo nombre: "));
									 break;
								 case 2:
									 cli.setCUIT(ConsolaService.pedirTexto("Ingresar nuevo CUIT:"));
									 break;
								 case 3:
									 cli.setNroWhatsapp(ConsolaService.pedirTexto("Ingresar nuevo Nro Whatsapp:"));
									 break;
								 case 4:
									 cli.setEmail(ConsolaService.pedirTexto("Ingresar nuevo correo electronico:"));
									 break;
								 case 5:
									 
									 	String fecha=ConsolaService.pedirTexto("Ingresar fecha de baja (DD/MM/AAAA):");
									 	while(true)
									 	{
									 		if(fecha.split("/").length==3)
									 		{
									 			cli.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaBaja");
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
									 			cli.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaAlta");
									 			break;
									 		}
									 		else
									 		{
									 			System.out.println("Formato incorrecto!\n");
									 			fecha=ConsolaService.pedirTexto("Ingresar fecha de alta (DD/MM/AAAA): ");
									 		}
									 	}
									 break;
								 case 7:
									 
									 if(ConsolaService.preguntaSioNo("Desea agregar un servicio nuevo?"))
									 	{
											do
											{
												cli.agregarServicio(AdminServicios.menuServicios(false));
											}while(ConsolaService.preguntaSioNo("Desea agregar otro servicio?"));
											servs=cli.getServicios();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar un servicio?"))
									 	{
									 		 servs=cli.getServicios();
											do
											{
												servs.forEach((s)->System.out.println(servs.indexOf(s)+")\n "+s.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, servs.size());
												Servicio serv=servs.get(indx);
												cli.eliminarServicio(serv);
												servs.remove(indx);
												servEl.add(serv);
											}while(ConsolaService.preguntaSioNo("Desea eliminar otro servicio?"));
									 	}
									 break;
							 }
							 	System.out.println("Datos viejos: \n"+clActual.toString());
								System.out.println("Datos nuevos: \n"+cli.toString());
								if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
								{
									if(servs!=null)
									{
										servs.forEach(s->{
											s.agregarCliente(cli);
											sService.ActualizarDatos(s);
											});
									}
									if(servEl!=null)
									{
										servEl.forEach(s->{
											s.eliminarCliente(cli);
											sService.ActualizarDatos(s);
											});
									}
									clActual=srv.ActualizarDatos(cli);
								}
						}
						else
						{
							System.out.println("Usted no posee autorizacion para realizar esta operacion.");
						}
						break;
					}
					break;
					default:
						return clActual;
				}
	    		return menuClientes(autorizacion);
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	clientes.forEach((cl)->System.out.println(clientes.indexOf(cl)+")\n "+cl.toString()+"\n"));
			System.out.println(clientes.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, clientes.size());
			if(o<clientes.size()) 
	 		{
	 		  cli= clientes.get(o);
			 	if(clActual==null)
			 	{							 		
			 		clActual= clientes.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+cli.toString()+"\n s/n?"))
			 		{
			 			clActual=cli;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
