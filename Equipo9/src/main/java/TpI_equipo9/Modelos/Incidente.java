package TpI_equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name="incidentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Double tiempoResolucion;
    private boolean complejo=false;
    @Transient
    private Estado estado;
    private String estadoActual;
    private Timestamp fechaAlta;
    private Timestamp fechaResol;
    
    @ManyToOne(cascade=javax.persistence.CascadeType.MERGE)
    @JoinColumn(name = "id_tecnico")
    private Tecnico tecnico;
    @ManyToMany(cascade=javax.persistence.CascadeType.REMOVE)
    @JoinTable(
            name = "inc_prob",
            joinColumns = @JoinColumn( name= "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;
    @ManyToOne(cascade=javax.persistence.CascadeType.MERGE)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    
    
    public void cambiarEstado(Estado nuevoEstado)
    {
    	this.estado=nuevoEstado;
    	this.estadoActual=nuevoEstado.getEstadoActual();
    }
    
	public void cheakearEstado(){
		if(this.estado==null)
		{
			if(estadoActual==null||estadoActual.equals(""))
			{
				cambiarEstado(new EstadoReportado());				
			}
			else
			{
				switch(estadoActual.toLowerCase())
				{
				case "revision":
					cambiarEstado(new EstadoEnRevision());	
					break;
				case "solucionado":
					cambiarEstado(new EstadoSolucionado());	
					break;
					default:
						cambiarEstado(new EstadoReportado());	
						break;
				}
			}
		}
		this.estado.cheakearEstado(this);
	}
	
    
    public void setFechaInt(int dia,int mes,int año, String fechaAIngresar)
    {
    	
    	Calendar cal=Calendar.getInstance();
    	cal.set(Calendar.DATE, dia);
    	cal.set(Calendar.MONTH, mes-1);
    	cal.set(Calendar.YEAR, año);
    	
    	if(fechaAIngresar.equals("fechaAlta"))
    	{
    		fechaAlta=new Timestamp(Calendar.getInstance().get(Calendar.LONG));
    		fechaAlta.setTime(cal.getTimeInMillis());
    	}
    	else if (fechaAIngresar.equals("fechaResol"))
    	{
    		fechaResol=new Timestamp(Calendar.getInstance().get(Calendar.LONG));
    		fechaResol.setTime(cal.getTimeInMillis());
    	}
    	else
    	{
    		System.out.println("no se ingreso una fecha valida");
    	}
    }
    
    public long tiempoTranscurrido()
    {
    	return fechaResol.getTime()-fechaAlta.getTime();
    }
    public void agregarProblema(Problema pro)
    {
    	if(problemas==null) problemas=new ArrayList<Problema>();
    		if(!problemas.contains(pro))
    			problemas.add(pro);
    }
    public void eliminarProblema(Problema pro)
    {
    	if(problemas!=null)
    		if(problemas.contains(pro))
    			problemas.remove(pro);
    }
    
    
    @Override
    public String toString()
    {
    	return "[DESCRIPCION]:"+ this.descripcion+"\n"+
    			"[TIEMPO_RESOLUCION]:"+ this.tiempoResolucion+"\n"+
    			"[COMPLEJIDAD]:"+ this.complejo+"\n"+
    			"[CLIENTE]"+this.cliente.getId()+" "+this.cliente.getNombre()+" "+this.cliente.getCUIT()+"\n"+
    			"[TECNICO]"+this.tecnico.getId()+" "+this.tecnico.getNombre()+" "+this.tecnico.getApellido()+"\n"+
    			"[ESTADO]:"+ this.estadoActual+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_RESOLUCION]:"+ this.fechaResol+"\n";
    }
    
 
  
}
