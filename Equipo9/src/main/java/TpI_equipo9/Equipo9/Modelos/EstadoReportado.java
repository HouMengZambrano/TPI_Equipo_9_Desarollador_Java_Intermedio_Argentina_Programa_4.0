package TpI_equipo9.Equipo9.Modelos;

public class EstadoReportado extends Estado{

	public EstadoReportado()
	{
		setEstadoActual("Reportado");
	}
	public final String estadoActual="Reportado";
	public void cheakearEstado(Incidente incidenteActual){}
	public boolean cumpleCondicion(){
		return false;
	};

}
