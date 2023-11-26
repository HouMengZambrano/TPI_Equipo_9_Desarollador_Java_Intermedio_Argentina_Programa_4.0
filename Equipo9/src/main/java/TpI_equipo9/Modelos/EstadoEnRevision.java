package TpI_equipo9.Modelos;

public class EstadoEnRevision extends Estado{

	public EstadoEnRevision()
	{
		setEstadoActual("En revision");
	}
	public void cheakearEstado(Incidente incidenteActual){}
	public boolean cumpleCondicion(){
		return false;
	};

}
