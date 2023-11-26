package TpI_equipo9.Modelos;


public abstract class Estado{
//revisar
	private static String estadoActual;
	public void setEstadoActual(String eA) {estadoActual=eA;}
	public String getEstadoActual() {return estadoActual;};
	
	
	public abstract void cheakearEstado(Incidente incidenteActual);
}
