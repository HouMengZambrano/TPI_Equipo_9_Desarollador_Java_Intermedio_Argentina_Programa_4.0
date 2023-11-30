package TpI_equipo9.Modelos;

import TpI_equipo9.Services.ConsolaService;

public class EstadoReportado extends Estado{

	
	private String estadoActual;
	public EstadoReportado()
	{
		estadoActual="Reportado";
	}
	@Override
	public String getEstadoActual() {
		return this.estadoActual;
	}
	public void cheakearEstado(Incidente incidenteActual){
		
		if(cumpleCondicion())
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
			incidenteActual.cambiarEstado(new EstadoEnRevision());
		}
		
	}
	public boolean cumpleCondicion(){
		return ConsolaService.preguntaSioNo("El tecnico aceptal incidente? s/n");
	};
	
	
}
