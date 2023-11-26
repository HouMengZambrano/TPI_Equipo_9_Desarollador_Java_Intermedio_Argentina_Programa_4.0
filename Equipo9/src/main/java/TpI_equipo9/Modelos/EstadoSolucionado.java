package TpI_equipo9.Modelos;

public class EstadoSolucionado extends Estado{

	public EstadoSolucionado()
	{
		setEstadoActual("Solucionado");
	}
	public final String estadoActual="Solucionado";
	public void cheakearEstado(Incidente incidenteActual){}
	public boolean cumpleCondicion(){
		return false;
	};

}
