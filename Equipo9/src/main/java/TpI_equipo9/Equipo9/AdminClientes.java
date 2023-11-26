package TpI_equipo9.Equipo9;

import TpI_equipo9.Equipo9.Modelos.Cliente;
import TpI_equipo9.Equipo9.Services.ClienteService;

import java.util.List;
import java.util.Scanner;
public class AdminClientes {
	
		static Scanner scn= new Scanner(System.in);
		static Cliente clActual=null;
		static ClienteService srv= new ClienteService();
		
	 public static void menuClientes()
	    {
	    	int opt=0;
	    	int opt1=0;
	    	
	    	if(clActual!=null)
	    		System.out.println("ID CLIENTE ACTUAL: "+clActual.getId_cliente());
	    	System.out.println(
	    			"1) Mostrar todos los cliente ->\n"+
	    			"2) Buscar cliente ->\n"+
	    			"3) Ingresar cliente ->\n"+
	    			"4) <- Volver.\n");
	    	while(opt!=4)
			{
				switch (opt)
				{
				case 1:
						List<Cliente> clientes=srv.buscarTodos();
						clientes.forEach((cl)->System.out.println(clientes.indexOf(cl)+") "+cl.toString()+"\n"));
						System.out.println(")<- Volver.\n");
						while(opt1<clientes.size())
						{
							opt1=scn.nextInt();
							if(opt1>0 && opt1<clientes.size()) {
							clActual=clientes.get(opt1);
							opt1=clientes.size();
							}
						}
						opt1=0;
						opt=4;
						menuClientes();
					break;
				case 2:
					 System.out.println("Elija un metodo de busqueda:"+
							 	"1) Por ID ->\n"+
				    			"2) Por nombre ->\n"+
				    			"3) Por CUIT ->\n"+
				    			"4) Por servicio ->\n"+ // pensarlo
				    			"5) <- Volver.\n");
					 while(opt1!=5)
					 {
						 switch (opt1)
						 {
							 case 1:
								 	System.out.println("Ingrese el ID:");
								 	int id=scn.nextInt();
								 	clActual= srv.buscarPorID(id);
								 break;
							 case 2:
									System.out.println("Ingrese el nombre:");
								 	String nombre=scn.next();
								 	List<Cliente> nombresIguales= srv.buscarPorNombre(nombre);
								 	nombresIguales.forEach((cl)->System.out.println(nombresIguales.indexOf(cl)+") "+cl.toString()+"\n"));
								 	while(opt1<nombresIguales.size())
									{
										opt1=scn.nextInt();
										if(opt1>0 && opt1<nombresIguales.size()) {
										clActual=nombresIguales.get(opt1);
										opt1=nombresIguales.size();
										}
									}
								 	opt1=0;
								 	opt=4;
								 	menuClientes();
								 break;
							 case 3:
								 System.out.println("Ingrese el CUIT(solo numeros):");
								 	String CUIT=scn.next();
								 	List<Cliente> CUITIguales= srv.buscarPorNombre(CUIT);
								 	CUITIguales.forEach((cl)->System.out.println(CUITIguales.indexOf(cl)+") "+cl.toString()+"\n"));
								 	while(opt1<CUITIguales.size())
									{
										opt1=scn.nextInt();
										if(opt1>0 && opt1<CUITIguales.size()) {
										clActual=CUITIguales.get(opt1);
										opt1=CUITIguales.size();
										}
									}
								 	opt1=0;
								 	opt=4;
								 	menuClientes();
								 break;
							 case 4:
								 	//en desarrollo
								 break;
								 default:
									 break;
						 }
					 }
					break;
				case 3:
					System.out.println("Ingrese el Nombre,CUIT,razon social, telefono ,email\n");
					String[] datos=scn.next().split(",");
					Cliente cl= new Cliente();
					cl.setNombre(datos[0]);
					cl.setCUIT(Integer.parseInt(datos[1]));
					cl.setRazon_social(datos[2]);
					cl.setTelefono(datos[3]);
					cl.setEmail(datos[4]);
					srv.ingresarCliente(cl);
					break;
					default:
						break;
				}
				opt=scn.nextInt();
			}
	    }
	 
	    
}
