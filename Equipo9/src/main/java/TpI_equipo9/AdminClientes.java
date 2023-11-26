package TpI_equipo9;

import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Services.ClienteService;
import TpI_equipo9.Services.ConsolaService;

import java.util.List;

public class AdminClientes {
	
		
		static Cliente clActual=null;
		static Cliente cli;
		static ClienteService srv= new ClienteService();
		static List<Cliente> clientes;
		
	 public static void menuClientes()
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
	    			"4) Eliminar registro cliente ->\n"+
	    			"5) Modificar registro cliente ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija un cliente: \n");
						clientes=srv.buscarTodos();
						seleccionar();
						menuClientes();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2) Por nombre ->\n"+
				    			"3) Por CUIT ->\n"+
				    			"4) Por Nro Whatsapp ->\n"+
				    			"5) Por Email ->\n"+
				    			"6) Por servicio ->\n"+ // pensarlo
				    			"7) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 7);
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
								 	menuClientes();
								 break;
							 case 2:
								 	String nombre=ConsolaService.pedirTexto("Ingrese el nombre: ");
								 	 clientes= srv.buscarPorNombre(nombre);
								 	seleccionar();
									menuClientes();
								 
								 break;
							 case 3:
								 	String CUIT=ConsolaService.pedirTexto("Ingrese el CUIT:");
								 	 clientes= srv.buscarPorCUIT(CUIT);
								 	seleccionar();
									menuClientes();
								 break;
							 case 4:
								 String nro=ConsolaService.pedirTexto("Ingrese el Nro Whatsapp:");
							 	 clientes= srv.buscarPorNroWhatsapp(nro);
							 	seleccionar();
								menuClientes();
								 break;
							 case 5:
								 String email=ConsolaService.pedirTexto("Ingrese el correo electronico:");
							 	 clientes= srv.buscarPorNroWhatsapp(email);
							 	seleccionar();
								menuClientes();
								 break;
							 case 6:
								 //en desarrollo
									menuClientes();
								 default:
									 break;
						 }
					break;
				case 3:
					String datos= ConsolaService.pedirTexto("Ingrese el nombre, CUIT, razon social, telefono ,email\n");
					Cliente cl=new Cliente(datos);
					System.out.println(cl.toString());
					srv.ingresarCliente(cl);
					menuClientes();
					break;
				case 4:
						if(clActual!=null)
						{
							if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+clActual.toString()+"\n s/n?"))
							{
								srv.borrarDatos(clActual);
								System.out.println("Se han borrados los datos del cliente.");
							}
						}
						menuClientes();
					break;
				case 5:
					if(clActual!=null)
					{
						datos= ConsolaService.pedirTexto("Ingrese el nombre, CUIT, razon social, telefono ,email\n");
						cl=new Cliente(datos);
						cl.setId(clActual.getId());
						System.out.println("Datos viejos: \n"+clActual.toString());
						System.out.println("Datos nuevos: \n"+cl.toString());
						if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
						{
							srv.ActualizarDatos(cl);
							clActual=cl;
						}
						menuClientes();
					}
					break;
					default:
						break;
				}
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
	  
