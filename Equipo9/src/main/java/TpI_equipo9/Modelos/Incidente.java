package TpI_equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
    private Estado estado= new EstadoReportado(); //  Esto hay que hacer un patron state por ahora queda asi;
    @Column(name="estado")
    private String estadoActual;
    private Date fechaAlta;
    private Date fechaResol;
    @ManyToOne()
    @JoinColumn(name = "id_tecnico")
    private Tecnico tecnico;
    @ManyToMany
    @JoinTable(
            name = "inc_prob",
            joinColumns = @JoinColumn( name= "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;
    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    
    
    public void cambiarEstado(Estado nuevoEstado)
    {
    	this.estado=nuevoEstado;
    	this.estadoActual=nuevoEstado.getEstadoActual();
    }
    
	public void cheakearEstado(){
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
    		fechaAlta=new Date(Calendar.getInstance().get(Calendar.LONG));
    		fechaAlta.setTime(cal.getTimeInMillis());
    	}
    	else if (fechaAIngresar.equals("fechaResol"))
    	{
    		fechaResol=new Date(Calendar.getInstance().get(Calendar.LONG));
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
    			"[CLIENTE]"+this.cliente.toString()+"\n"+
    			"[TECNICO]"+this.tecnico.toString()+"\n"+
    			"[ESTADO]:"+ this.estadoActual+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_RESOLUCION]:"+ this.fechaResol+"\n";
    }
    
 
    String getProblemasString()
    {
    	if(problemas!=null)
    	return "[PROBLEMAS]"+problemas.stream().map(p->p.toString()).collect(Collectors.joining("\n"));	
    	else
    		return "[PROBLEMAS] NO DISPONE.";
    }
}
