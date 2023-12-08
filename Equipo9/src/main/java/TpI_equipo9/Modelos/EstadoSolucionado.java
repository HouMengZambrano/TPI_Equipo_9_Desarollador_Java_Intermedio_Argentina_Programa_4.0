package TpI_equipo9.Modelos;


import java.sql.Timestamp;
import java.util.Calendar;

public class EstadoSolucionado extends Estado{

	private String estadoActual;
	
	public EstadoSolucionado()
	{
		estadoActual="Solucionado";
	}
	@Override
	public String getEstadoActual() {
		return this.estadoActual;
	}
	
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
		incidenteActual.setFechaResol(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
	}
	

}
