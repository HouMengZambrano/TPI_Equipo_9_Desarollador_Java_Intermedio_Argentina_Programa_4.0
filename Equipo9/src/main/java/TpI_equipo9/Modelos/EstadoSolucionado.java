package TpI_equipo9.Modelos;

import java.sql.Date;
import java.util.Calendar;

public class EstadoSolucionado extends Estado{

	public EstadoSolucionado()
	{
		setEstadoActual("Solucionado");
	}
	public final String estadoActual="Solucionado";
	public void cheakearEstado(Incidente incidenteActual){
		
		if(incidenteActual.getCliente().getMetodoE()==Cliente.MetodoNotificacion.EMAIL)
    	{
    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su email sobre el incidente: \n"+incidenteActual.toString()
    		+"\n ha sido solucionado");
    	}
    	else
    	{
    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su Nro de Whatsapp sobre el incidente: \n"+incidenteActual.toString()
    		+"\n ha sido solucionado");
		}
		incidenteActual.setFechaResol(new Date(Calendar.getInstance().getTimeInMillis()));
		
	}
	

}
