package TpI_equipo9.Modelos;


public abstract class Estado{
//revisar
	
	private String estadoActual;
	
	public String getEstadoActual() {return estadoActual;};
	
	
	public abstract void cheakearEstado(Incidente incidenteActual);
}
