package TpI_equipo9;


import TpI_equipo9.Modelos.Especialidad;

import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Tecnico;

import TpI_equipo9.Services.ProblemaService;
import TpI_equipo9.Services.TecnicoService;

import TpI_equipo9.Services.ConsolaService;
import TpI_equipo9.Services.EspecialidadService;

import java.util.ArrayList;
import java.util.List;

public class AdminEspecialidades {
	
		
		static Especialidad epsActual=null;
		static Especialidad esp;
		static EspecialidadService srv= new EspecialidadService();
		static List<Especialidad> especialidades;
		static List<Tecnico> tecs;
		static List<Problema> probs;
		static ProblemaService pService= new ProblemaService(); 
		static TecnicoService tService= new TecnicoService();
	
	 public static Especialidad menuEspecialidades(boolean autorizacion)
	    {
	    	int opt=0;
	    	int opt1=0;
	    	if(epsActual!=null)
	    		System.out.println("\nDATOS ESPECIALIDAD ACTUAL: \n"+epsActual.toString());
	    	else
	    		System.out.println("\nNO HAY NINGUNA ESPECIALIDAD SELECCIONADO.\n");
	    	System.out.println(
	    			"\n1) Mostrar todos las especialidades ->\n"+
	    			"2) Buscar especialidad ->\n"+
	    			"3) Ingresar especialidad ->\n"+
	    			"4) Eliminar registro de la especialidad actual ->\n"+
	    			"5) Modificar registro de la especialidad actual ->\n"+
	    			"6) <- Volver.\n");
	    	opt=ConsolaService.rangoOpciones(1, 6);
	    	switch (opt)
				{
				case 1:
						System.out.println("Elija una especialidad: \n");
						especialidades=srv.buscarTodos();
						seleccionar();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:\n"+
							 	"1) Por ID ->\n"+
				    			"2)	Por nombre ->\n"+
				    			"3) Por tecnico ->\n"+
				    			"4) Por problema ->\n"+
				    			"5) <- Volver.\n");
					 opt1=ConsolaService.rangoOpciones(1, 5);
						 switch (opt1)
						 {
							 case 1:
								 	int id=ConsolaService.pedirEntero("Ingrese el ID: ");
								 	esp= srv.buscarPorID(id);
								 	if(epsActual==null)
								 	{							 		
								 		epsActual= srv.buscarPorID(id);
								 	}
								 	else
								 	{
								 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al cliente :\n"+esp.toString()+"\n s/n?"))
								 		{
								 			epsActual=esp;
								 		}
								 	}
								 break;
							 case 2:
								 String nombre=ConsolaService.pedirTexto("Ingrese nombre:");
								 especialidades= srv.buscarPorNombre(nombre);
							 	 seleccionar();
								 break;
							 case 3:
								 System.out.println("Busque tecnico: ");
								 	especialidades= srv.buscarPorTecnico(AdminTecnicos.menuTecnicos(false));
								 	seleccionar();
								 break;
							 case 4:
								 System.out.println("Busque problema: ");
								 	especialidades=srv.buscarPorProblema(AdminProblemas.menuProblemas(false));
								 	seleccionar();
								 break;
								 default:
									 break;
						 }
					break;
				case 3:
						if(autorizacion)
						{
							esp=new Especialidad();
							esp.setNombre(ConsolaService.pedirTexto("Ingrese nombre de la especialidad: "));
							srv.ingresarEspecialidad(esp);
							epsActual=esp;
						}else
						{
							System.out.println("Usted no posee autorizacion para realizar esta operacion.");
						}
					break;
				case 4:
						if(autorizacion)
						{
							if(epsActual!=null)
							{
								if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+epsActual.toString()+"\n s/n?"))
								{
									srv.borrarDatos(epsActual);
									System.out.println("Se han borrados los datos de la especialidad.");
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
						if(epsActual!=null)
						{
								esp=epsActual;
								System.out.println("Elija un campo para actualizar:\n"+
						    			"1) Nombre->\n"+
						    			"4) <- Volver.\n");
								opt1=ConsolaService.rangoOpciones(1, 7);
								List<Tecnico> tecEl=new ArrayList<Tecnico>();
								List<Problema> probEl=new ArrayList<Problema>();
								 switch (opt1)
								 {
									 case 1:
										 esp.setNombre(ConsolaService.pedirTexto("ingrese nombre: "));
										 break;
								
								 }
								 System.out.println("Datos viejos: \n"+epsActual.toString());
									System.out.println("Datos nuevos: \n"+esp.toString());
									if(ConsolaService.preguntaSioNo("Desea actualizar los datos? s/n?"))
									{
										epsActual=srv.ActualizarDatos(esp);
										if(tecs!=null)
										{
											tecs.forEach(t->{
												t.agregarEspecialidad(epsActual);
												tService.ActualizarDatos(t);
											});
										}
										if(tecEl!=null)
										{
											tecEl.forEach(t->{
												t.eliminarEspecialidad(epsActual);
												tService.ActualizarDatos(t);
											});
										}
										if(probs!=null)
										{
											probs.forEach(p->{
												p.agregarEspecialidad(epsActual);
												pService.ActualizarDatos(p);
											});
										}
										if(probEl!=null)
										{
											probEl.forEach(p->{
												p.eliminarEspecialidad(epsActual);
												pService.ActualizarDatos(p);
											});
										}
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
						return epsActual;
				}
	    		return menuEspecialidades(autorizacion);
			}
	 
	 
	 
	 
	 
	 public static void seleccionar()
	 {
		 	int o=0;
		 	especialidades.forEach((i)->System.out.println(especialidades.indexOf(i)+")\n "+i.toString()+"\n"));
			System.out.println(especialidades.size()+")<- Volver.\n");
			o=ConsolaService.rangoOpciones(0, especialidades.size());
			if(o<especialidades.size()) 
	 		{
	 		  esp= especialidades.get(o);
			 	if(epsActual==null)
			 	{							 		
			 		epsActual= especialidades.get(o);
			 	}
			 	else
			 	{
			 		if(ConsolaService.preguntaSioNo("Esta seguro que desea cambiar al incidente :\n"+esp.toString()+"\n s/n?"))
			 		{
			 			epsActual=esp;
			 		}
			 	}
	 			
	 		}
	 }
}
	  
