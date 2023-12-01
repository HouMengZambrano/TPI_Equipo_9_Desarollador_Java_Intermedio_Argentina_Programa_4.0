package TpI_equipo9;

import TpI_equipo9.Modelos.Servicio;
import TpI_equipo9.Services.ServicioService;
import TpI_equipo9.Services.ConsolaService;

import java.util.List;

public class AdminServicios {
	
		
		static Servicio servActual=null;
		static Servicio serv;
		static ServicioService srv= new ServicioService();
		static List<Servicio> servicios;
		
	 public static Servicio menuServicios(boolean autorizacion)
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(servActual!=null)
	    		System.out.println("\nDATOS SERVICIO ACTUAL: \n"+servActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUN SERVICIO SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos los servicios ->\n"+
	    			"2) Buscar servicio ->\n"+
	    			"3) Ingresar servicio ->\n"+
	    			"4) Eliminar registro del servicio actual->\n"+
	    			"5) Modificar registro del servicio actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija un servicio: \n");
						servicios=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) Por nombre ->\n"+
				    			"3) Por precio ->\n"+
				    			"4) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 4);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	serv= srv.buscarPorID(id);
								 	if(servActual==null)
								 	{							 		
								 		servActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al servicio :\n"+serv.toString()+"\n s/n?"))
								 		{
								 			servActual=serv;
								 		}
								 	}
								 break;
							 case 2:
								 	String nombre=ConsolaService.pedirTexto("Ingrese el nombre: ");
								 	 servicios= srv.buscarPorNombre(nombre);
								 	seleccionar();
								 break;
							 case 3:
								 	double precio=Double.parseDouble(ConsolaService.pedirTexto("Ingrese el precio: $"));
								 	 servicios= srv.buscarPorPrecio(precio);
								 	seleccionar();
								 break;
						 }
					break;
				case 3:
					if(autorizacion)
					{
						String datos= ConsolaService.pedirTexto("Ingrese el nombre, precio\n");
						while(true)
						{
							if(datos.split(",").length==2)
							{
								serv=new Servicio(datos);
								System.out.println(serv.toString());
								srv.ingresarServicio(serv);
								servActual=serv;
								break;
							}
							else
							{
								System.out.println("La cantidad de datos ingresados("+datos.split(",").length+") erronea, deben ser 2 campos.");
								datos= ConsolaService.pedirTexto("Ingrese el nombre, precio\n");
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
						if(servActual!=null)
						{
							if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+servActual.toString()+"\n s/n?"))
							{
								srv.borrarDatos(servActual);
								System.out.println("Se han borrados los datos del servicio.");
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
						if(servActual!=null)
						{
							serv=servActual;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Nombre ->\n"+
					    			"2) Precio ->\n"+
					    			"3) <- Volver.\n");
							opt1=ConsolaService.rangoOpciones(1, 3);
							 switch (opt1)
							 {
								 case 1:
									 serv.setNombre(ConsolaService.pedirTexto("Ingresar nuevo nombre: "));
									 break;
								 case 2:
									 serv.setPrecio(Double.parseDouble(ConsolaService.pedirTexto("Ingresar nuevo precio: $")));
									 break;
							 }
						 	System.out.println("Datos viejos: \n"+servActual.toString());
							System.out.println("Datos nuevos: \n"+serv.toString());
							if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
							{
								servActual=srv.ActualizarDatos(serv);
							}
						break;
					}
					}else
					{
						System.out.println("Usted no posee autorizacion para realizar esta operacion.");
					}
					break;
					default:
						return servActual;
				}
	    		return menuServicios(autorizacion);
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	servicios.forEach((s)->System.out.println(servicios.indexOf(s)+")\n "+s.toString()+"\n"));
			System.out.println(servicios.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, servicios.size());
			if(o<servicios.size()) 
	 		{
	 		  serv= servicios.get(o);
			 	if(servActual==null)
			 	{							 		
			 		servActual= servicios.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al servicio :\n"+serv.toString()+"\n s/n?"))
			 		{
			 			servActual=serv;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
