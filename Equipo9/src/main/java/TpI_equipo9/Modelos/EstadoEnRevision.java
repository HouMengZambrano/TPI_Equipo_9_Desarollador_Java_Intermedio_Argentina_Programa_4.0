package TpI_equipo9.Modelos;

import TpI_equipo9.Services.ConsolaService;

public class EstadoEnRevision extends Estado{
	boolean reportado;
	public EstadoEnRevision()
	{
		setEstadoActual("Revision");
		
	}
	public void cheakearEstado(Incidente incidenteActual){
		if(!reportado)
		{
			if(incidenteActual.getCliente().getMetodoE()==Cliente.MetodoNotificacion.EMAIL)
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su email sobre el incidente: \n"+incidenteActual.toString()
	    		+"\n El tiempo de resolucion para este incidente es: "+ incidenteActual.getTiempoResolucion()+"(HS) \n el tecnico asignado es: "+incidenteActual.getTecnico().toString());
	    	}
	    	else
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su Nro de Whatsapp sobre el incidente: \n"+incidenteActual.toString()+"\n El tiempo de resolucion para este incidente es: "+ incidenteActual.getTiempoResolucion()+
	    				"(HS)\n el tecnico asignado es: "+incidenteActual.getTecnico().toString());
	    	}
			reportado=true;
		}
		else
		{
			if(cumpleCondicion())
			{
				incidenteActual.cambiarEstado(new EstadoSolucionado());
			}
		}
	
		
	}
	public boolean cumpleCondicion(){
		return ConsolaService.preguntaSioNo("El tecnico termino de solucionar el incidente? s/n");
	};

}
