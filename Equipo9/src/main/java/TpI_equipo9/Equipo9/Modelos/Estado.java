package TpI_equipo9.Equipo9.Modelos;


public abstract class Estado{

	private static String estadoActual;
	public void setEstadoActual(String eA) {estadoActual=eA;}
	public String getEstadoActual() {return estadoActual;};
	public abstract void cheakearEstado(Incidente incidenteActual);
}
