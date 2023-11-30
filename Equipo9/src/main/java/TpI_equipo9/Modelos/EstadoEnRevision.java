package TpI_equipo9.Modelos;

import TpI_equipo9.Services.ConsolaService;

public class EstadoEnRevision extends Estado{

	private String estadoActual;
	public EstadoEnRevision()
	{
		estadoActual="Revision";
	}
	@Override
	public String getEstadoActual() {
		return this.estadoActual;
	}
	public void cheakearEstado(Incidente incidenteActual){
		if(cumpleCondicion())
		{
			incidenteActual.cambiarEstado(new EstadoSolucionado());
		}
	}
	public boolean cumpleCondicion(){
		return ConsolaService.preguntaSioNo("El tecnico soluciono el incidente? s/n");
	};

}
