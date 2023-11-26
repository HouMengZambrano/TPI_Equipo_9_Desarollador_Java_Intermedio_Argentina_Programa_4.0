package TpI_equipo9;


import TpI_equipo9.Modelos.Tecnico;
import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.TecnicoService;

import java.util.List;


public class AdminTecnicos {

	static Tecnico tecActual=null;
	static Tecnico tec;
	static TecnicoService srv= new TecnicoService();
	static List<Tecnico> tecnicos;
	
 public static void menuTecnicos()
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
    			"4) Eliminar registro tecnico ->\n"+
    			"5) Modificar registro tecnico ->\n"+
    			"6) <- Volver.\n");
    	opt=ConsolaService.rangoOpciones(1, 6);
    	switch (opt)
			{
			case 1:
					System.out.println("Elija un tecnico: \n");
					tecnicos=srv.buscarTodos();
					seleccionar();
					menuTecnicos();
					opt=6;
				break;
			case 2:
				 System.out.println("Elija un metodo de busqueda:\n"+
						 	"1) Por ID ->\n"+
			    			"2) Por nombre ->\n"+
			    			"3) Por apellido ->\n"+
			    			"4) Por Nro Whatsapp ->\n"+
			    			"5) Por Nro email ->\n"+
			    			"6) <- Volver.\n");
				 opt1=ConsolaService.rangoOpciones(1, 6);
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
							 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al tecnico :\n"+tec.toString()+"\n s/n?"))
							 		{
							 			tecActual=tec;
							 		}
							 	}
							 	menuTecnicos();
							 break;
						 case 2:
							 	String nombre=ConsolaService.pedirTexto("Ingrese el nombre: ");
							 	 tecnicos= srv.buscarPorNombre(nombre);
							 	seleccionar();
								menuTecnicos();
							 break;
						 case 3:
							 	String apellido=ConsolaService.pedirTexto("Ingrese el apellido:");
							 	tecnicos= srv.buscarPorApellido(apellido);
							 	seleccionar();
								menuTecnicos();
							 break;
						 case 4:
							 	String nro=ConsolaService.pedirTexto("Ingrese el Nro Whatsapp:");
							 	tecnicos= srv.buscarPorNroWhatsapp(nro);
							 	seleccionar();
							 	menuTecnicos();
							 break;
						 case 5:
							 	String email=ConsolaService.pedirTexto("Ingrese el correo electronico:");
							 	tecnicos= srv.buscarPorEmail(email);
							 	seleccionar();
							 	menuTecnicos();
							 break;
							 default:
								 break;
					 }
					
				break;
			case 3:
				String datos= ConsolaService.pedirTexto("Ingrese el nombre, apellido, telefono, email\n");
				tec=new Tecnico(datos);
				System.out.println(tec.toString());
				srv.ingresarTecnico(tec);
				menuTecnicos();
				break;
			case 4:
					if(tecActual!=null)
					{
						if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+tecActual.toString()+"\n s/n?"))
						{
							srv.borrarDatos(tecActual);
							System.out.println("Se han borrados los datos del cliente.");
						}
					}
					menuTecnicos();
				break;
			case 5:
				if(tecActual!=null)
				{
					datos= ConsolaService.pedirTexto("Ingrese el nombre, apellido, telefono, email\n");
					tec=new Tecnico(datos);
					tec.setId(tecActual.getId());
					System.out.println("Datos viejos: \n"+tecActual.toString());
					System.out.println("Datos nuevos: \n"+tec.toString());
					if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
					{
						srv.ActualizarDatos(tec);
						tecActual=tec;
					}
					menuTecnicos();
				}
				break;
				default:
					break;
			}
		}
 
 public static void seleccionar()
 {
	 int o=0;
	 tecnicos.forEach((t)->System.out.println(tecnicos.indexOf(t)+")\n "+t.toString()+"\n"));
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
