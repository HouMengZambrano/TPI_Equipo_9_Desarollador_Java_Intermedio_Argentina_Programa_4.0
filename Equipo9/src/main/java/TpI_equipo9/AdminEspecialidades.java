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
	
	 public static Especialidad menuEspecialidades()
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
								 	especialidades= srv.buscarPorTecnico(AdminTecnicos.menuTecnicos());
								 	seleccionar();
								 break;
							 case 4:
								 System.out.println("Busque problema: ");
								 	especialidades=srv.buscarPorProblema(AdminProblemas.menuProblemas());
								 	seleccionar();
								 break;
								 default:
									 break;
						 }
					break;
				case 3:
						esp=new Especialidad();
						esp.setNombre(ConsolaService.pedirTexto("Ingrese nombre de la especialidad: "));
						System.out.println("Elija problema/s: ");
						do
						{
							esp.agregarProblema(AdminProblemas.menuProblemas());
						}while(ConsolaService.preguntaSioNo("Desea agregar otro problema? s/n"));	
						probs=esp.getProblemas();
						srv.ingresarEspecialidad(esp);
						if(probs!=null)
						{
							probs.forEach(p->{
								p.agregarEspecialidad(esp);
								pService.ActualizarDatos(p);
							});
						}
						epsActual=esp;
					break;
				case 4:
						if(epsActual!=null)
						{
							if(ConsolaService.preguntaSioNo("Esta seguro que desea borrar los datos de :\n"+epsActual.toString()+"\n s/n?"))
							{
								srv.borrarDatos(epsActual);
								System.out.println("Se han borrados los datos de la especialidad.");
							}
						}
					break;
				case 5:
						if(epsActual!=null)
						{
							esp=epsActual;
							System.out.println("Elija un campo para actualizar:\n"+
					    			"1) Nombre->\n"+
					    			"2) Tecnicos ->\n"+
					    			"3) Problemas ->\n"+
					    			"4) <- Volver.\n");
							opt1=ConsolaService.rangoOpciones(1, 7);
							List<Tecnico> tecEl=new ArrayList<Tecnico>();
							List<Problema> probEl=new ArrayList<Problema>();
							 switch (opt1)
							 {
								 case 1:
									 esp.setNombre(ConsolaService.pedirTexto("ingrese nombre: "));
									 break;
								 case 2:
									 if(ConsolaService.preguntaSioNo("Desea agregar un tecnico nuevo? s/n"))
									 	{
											do
											{
												esp.agregarTecnico(AdminTecnicos.menuTecnicos());
											}while(ConsolaService.preguntaSioNo("Desea agregar otro tecnico? s/n"));
											tecs=esp.getTecnicos();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar un tecnico? s/n"))
									 	{
									 		 tecs=esp.getTecnicos();
											do
											{
												tecs.forEach((t)->System.out.println(tecs.indexOf(t)+")\n "+t.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, tecs.size());
												Tecnico tc=tecs.get(indx);
												esp.agregarTecnico(tc);
												tecs.remove(indx);
												tecEl.add(tc);
												
											}while(ConsolaService.preguntaSioNo("Desea eliminar otro tecnico? s/n"));
									 	}
									break;
								 case 3:
									  if(ConsolaService.preguntaSioNo("Desea agregar un problema nuevo? s/n"))
									 	{
											do
											{
												esp.agregarProblema(AdminProblemas.menuProblemas());
											}while(ConsolaService.preguntaSioNo("Desea agregar otro problema? s/n"));
											probs=esp.getProblemas();
									 	}
									 	if(ConsolaService.preguntaSioNo("Desea eleminar un problema? s/n"))
									 	{
											probs=esp.getProblemas();
											do
											{
												probs.forEach((p)->System.out.println(probs.indexOf(p)+")\n "+p.toString()+"\n"));
												int indx=ConsolaService.rangoOpciones(0, probs.size());
												Problema pr=probs.get(indx);
												esp.agregarProblema(pr);
												probs.remove(indx);
												probEl.add(pr);
												
											}while(ConsolaService.preguntaSioNo("Desea eliminar otro problema? s/n"));
									 	}
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
					break;
					default:
						return epsActual;
				}
	    		return menuEspecialidades();
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
	  
