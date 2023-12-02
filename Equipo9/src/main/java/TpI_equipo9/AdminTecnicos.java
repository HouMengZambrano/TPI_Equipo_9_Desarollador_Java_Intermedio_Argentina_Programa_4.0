package TpI_equipo9;

import TpI_equipo9.Modelos.Tecnico;
import TpI_equipo9.Services.TecnicoService;
import TpI_equipo9.Services.ConsolaService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class AdminTecnicos {
	
		
		static Tecnico tecActual=null;
		static Tecnico tec;
		static TecnicoService srv= new TecnicoService();
		static List<Tecnico> tecnicos;
		
	 public static Tecnico menuTecnicos(boolean autorizacion)
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(tecActual!=null)
	    		System.out.println("\nDATOS TECNICO ACTUAL: \n"+tecActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUN TECNICO SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos los tecnicos ->\n"+
	    			"2) Buscar tecnico ->\n"+
	    			"3) Ingresar tecnico ->\n"+
	    			"4) Eliminar registro del tecnico actual->\n"+
	    			"5) Modificar registro del tecnico actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija un tecnico: \n");
						tecnicos=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) Por nombre ->\n"+
				    			"3) Por apellido ->\n"+
				    			"4) Por nro Whatsapp ->\n"+
				    			"5) Por correo electronico ->\n"+
				    			"6) Por fecha de alta ->\n"+
				    			"7) Por fecha de baja ->\n"+
				    			"8) Por especialidad ->\n"+
				    			"9) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 9);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	tec= srv.buscarPorID(id);
								 	if(tecActual==null)
								 	{							 		
								 		tecActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+tec.toString()+"\n s/n?"))
								 		{
								 			tecActual=tec;
								 		}
								 	}
								 break;
							 case 2:
								 	String nombre=ConsolaService.pedirTexto("Ingrese el nombre: ");
								 	 tecnicos= srv.buscarPorNombre(nombre);
								 	seleccionar();
								 break;
							 case 3:
								 	String apellido=ConsolaService.pedirTexto("Ingrese el apellido:");
								 	 tecnicos= srv.buscarPorApellido(apellido);
								 	seleccionar();
								 break;
							 case 4:
								 String nro=ConsolaService.pedirTexto("Ingrese el Nro Whatsapp:");
							 	 tecnicos= srv.buscarPorNroWhatsapp(nro);
							 	seleccionar();
								 break;
							 case 5:
								 String email=ConsolaService.pedirTexto("Ingrese el correo electronico:");
							 	 tecnicos= srv.buscarPorNroWhatsapp(email);
							 	seleccionar();
								 break;
							 case 6:
								 String fecha=ConsolaService.pedirTexto("Ingrese el fecha de alta (AAAA/MM/DD):");
							 	 tecnicos= srv.buscarPorFechaAlta(fecha);
							 	seleccionar();
								 break;
							 case 7:
								 fecha=ConsolaService.pedirTexto("Ingrese el fecha de baja (AAAA/MM/DD):");
								  tecnicos= srv.buscarPorFechaBaja(fecha);
							 	seleccionar();
								 break;
							 case 8:
								 System.out.println("Busque especialidad");
								  tecnicos= srv.buscarPorEspecialidad(AdminEspecialidades.menuEspecialidades(false));
							 	seleccionar();
								 break;
								 default:
									 break;
						 }
					break;
				case 3:
					if(autorizacion)
					{
						String datos= ConsolaService.pedirTexto("Ingrese el nombre, apellido, telefono, email\n");
						while(true)
						{
							if(datos.split(",").length==4)
							{
								tec=new Tecnico(datos);
								String res=ConsolaService.pedirTexto("Ingrese metodo preferido de notificaciion (email o whatsapp)");
								if(res.toLowerCase().equals("email"))
								{
									tec.setMetodoE(Tecnico.MetodoNotificacion.EMAIL);
								}
								else
								{
									tec.setMetodoE(Tecnico.MetodoNotificacion.NRO_WHATSAPP);
								}
								System.out.println(tec.toString());
								srv.ingresarTecnico(tec);
								tecActual=tec;
								break;
							}
							else
							{
								System.out.println("cantidad de datos ingresados("+datos.split(",").length+") erronea, deben ser 4 campos.");
								datos= ConsolaService.pedirTexto("Ingrese el nombre, apellido, telefono, email\n");
							}
			
						}
					}else
					{
						System.out.println("Usted no posee autorizacion para realizar esta operacion.");
					}
					break;
				case 4:
						if(autorizacion)
						{
						if(tecActual!=null)
						{
							if(tecActual.getIncidentes()==null||tecActual.getIncidentes().isEmpty())
							{
								srv.borrarDatos(tecActual);
								System.out.println("Se han borrados los datos del tecnico.");
								tecActual=null;	
							}
							else
							{
								tecActual.setFechaBaja(new Timestamp(Calendar.getInstance().getTimeInMillis()));
								System.out.println("Se ha dado de baja al tecnico.");
								tecActual=null;	
							}
						}
						}else
						{
							System.out.println("Usted no posee autorizacion para realizar esta operacion.");
							
						}
					break;
				case 5:
						if(autorizacion)
						{
							if(tecActual!=null)
							{
								tec=tecActual;
								System.out.println("Elija un campo para actualizar:\n"+
						    			"1) Nombre ->\n"+
						    			"2) Apellido ->\n"+
						    			"3) Nro Whatsapp ->\n"+
						    			"4) Email ->\n"+
						    			"5) Fecha de baja ->\n"+
						    			"6) Fecha de alta ->\n"+
						    			"7) <- Volver.\n");
								opt1=ConsolaService.rangoOpciones(1, 7);
								 switch (opt1)
								 {
									 case 1:
										 tec.setNombre(ConsolaService.pedirTexto("Ingresar nuevo nombre: "));
										 break;
									 case 2:
										 tec.setApellido(ConsolaService.pedirTexto("Ingresar nuevo apellido:"));
										 break;
									 case 3:
										 tec.setNroWhatsapp(ConsolaService.pedirTexto("Ingresar nuevo Nro Whatsapp:"));
										 break;
									 case 4:
										 tec.setEmail(ConsolaService.pedirTexto("Ingresar nuevo correo electronico:"));
										 break;
									 case 5:
										 	String fecha=ConsolaService.pedirTexto("Ingresar fecha baja (DD/MM/AAAA):");
										 	while(true)
										 	{
										 		if(fecha.split("/").length==3)
										 		{
										 			tec.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaBaja");
										 			break;
										 		}
										 		else
										 		{
										 			System.out.println("Formato incorrecto!\n");
										 			fecha=ConsolaService.pedirTexto("Ingresar fecha baja (DD/MM/AAAA): ");
										 		}
										 	}
										 break;
									 case 6:
										 	 fecha=ConsolaService.pedirTexto("Ingresar fecha alta (DD/MM/AAAA): ");
										 	while(true)
										 	{
										 		if(fecha.split("/").length==3)
										 		{
										 			tec.setFechaInt(Integer.parseInt(fecha.split("/")[0]), Integer.parseInt(fecha.split("/")[1]), Integer.parseInt(fecha.split("/")[2]), "fechaAlta");
										 			break;
										 		}
										 		else
										 		{
										 			System.out.println("Formato incorrecto!\n");
										 			fecha=ConsolaService.pedirTexto("Ingresar fecha alta (DD/MM/AAAA): ");
										 		}
										 	}
										 break;
								 }
								 System.out.println("Datos viejos: \n"+tecActual.toString());
									System.out.println("Datos nuevos: \n"+tec.toString());
									if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
									{
										tecActual=srv.ActualizarDatos(tec);
									}
							break;
							}
						}
						else
						{
							System.out.println("Usted no posee autorizacion para realizar esta operacion.");
						}
					break;
					default:
						return tecActual;
				}
	    		
	    		return menuTecnicos(autorizacion);
	    		
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	tecnicos.forEach((tc)->System.out.println(tecnicos.indexOf(tc)+")\n "+tc.toString()+"\n"));
			System.out.println(tecnicos.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, tecnicos.size());
			if(o<tecnicos.size()) 
	 		{
	 		  tec= tecnicos.get(o);
			 	if(tecActual==null)
			 	{							 		
			 		tecActual= tecnicos.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al tecnico :\n"+tec.toString()+"\n s/n?"))
			 		{
			 			tecActual=tec;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
