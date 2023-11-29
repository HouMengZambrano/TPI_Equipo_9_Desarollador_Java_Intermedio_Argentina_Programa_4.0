package TpI_equipo9.Modelos;

import TpI_equipo9.Services.ConsolaService;

public class EstadoReportado extends Estado{

	boolean reportado;
	
	public EstadoReportado()
	{
		setEstadoActual("Reportado");
	}
	public final String estadoActual="Reportado";
	public void cheakearEstado(Incidente incidenteActual){
		if(!reportado)
		{
		  	if(incidenteActual.getCliente().getMetodoE()==Cliente.MetodoNotificacion.EMAIL)
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su email sobre el registro del incidente: \n"+incidenteActual.toString());
	    	}
	    	else
	    	{
	    		System.out.println("Se ha notificado al cliente: \n"+incidenteActual.getCliente().toString()+"\n a travez de su Nro de Whatsapp sobre el registro del incidente: \n"+incidenteActual.toString());  	
	    	}
			
		  	if(incidenteActual.getTecnico().getMetodoE()==Tecnico.MetodoNotificacion.EMAIL)
	    	{
	    		System.out.println("Se ha notificado al tecnico: \n"+incidenteActual.getTecnico().toString()+"\n a travez de su email sobre el registro del incidente: \n"+incidenteActual.toString());
	    	}
	    	else
	    	{
	    		System.out.println("Se ha notificado al tliente: \n"+incidenteActual.getTecnico().toString()+"\n a travez de su Nro de Whatsapp sobre el registro del incidente: \n"+incidenteActual.toString());  	
	    	}
		  	reportado=true;
		}else
		{
			if(cumpleCondicion())
			{
				incidenteActual.cambiarEstado(new EstadoEnRevision());
			}
		}
		
	}
	public boolean cumpleCondicion(){
		return ConsolaService.preguntaSioNo("El tecnico se hara cargo del incidente? s/n");
	};

}
