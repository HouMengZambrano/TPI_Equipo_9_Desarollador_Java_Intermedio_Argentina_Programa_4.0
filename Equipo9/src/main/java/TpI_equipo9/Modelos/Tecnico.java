package TpI_equipo9.Modelos;

import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="tecnicos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tecnico implements Serializable{
	public enum MetodoNotificacion {NRO_WHATSAPP,EMAIL}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String nroWhatsapp;
    private String email;
    private Date fechaAlta;
    private Date fechaBaja;
    private MetodoNotificacion metodoE=MetodoNotificacion.EMAIL;
    @ManyToMany(cascade=javax.persistence.CascadeType.MERGE)
    @JoinTable(
            name = "tec_esp",
            joinColumns = @JoinColumn( name= "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;
    @ManyToMany(cascade=javax.persistence.CascadeType.MERGE)
    @JoinTable(
            name = "tec_inc",
            joinColumns = @JoinColumn( name= "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_incidente")
    )
    private List<Incidente> incidentes;
    
    
    
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
    	else if (fechaAIngresar.equals("fechaBaja"))
    	{
    		fechaBaja=new Date(Calendar.getInstance().get(Calendar.LONG));
    		fechaBaja.setTime(cal.getTimeInMillis());
    	}
    	else
    	{
    		System.out.println("no se ingreso una fecha valida");
    	}
    }
    
    public Tecnico(String datosString)
    {
    	String[] datos=datosString.split(",");
		this.nombre=datos[0];
		this.apellido=datos[1];
		this.nroWhatsapp=datos[2];
		this.email=datos[3];
		this.fechaAlta=new Date(Calendar.getInstance().getTimeInMillis());
    }
    
    
    
    public void agregarIncidente(Incidente in)
    {
    	if(incidentes==null) incidentes=new ArrayList<Incidente>();
    	incidentes.add(in);
    }
    public void eliminarIncidente(Incidente in)
    {
    	if(incidentes!=null)
    	incidentes.remove(in);
    	
    }
    public void agregarEspecialidad(Especialidad esp)
    {
    	if(especialidades==null) especialidades=new ArrayList<Especialidad>();
    	especialidades.add(esp);
    }
    public void eliminarEspecialidad(Especialidad esp)
    {
    	if(especialidades!=null)
    	especialidades.remove(esp);
    }
    
    
    
    @Override
    public String toString()
    {
    	return "[NOMBRE]:"+ this.nombre+"\n"+
    			"[APELLIDO]:"+ this.apellido+"\n"+
    			"[TELEFONO]:"+ this.nroWhatsapp+"\n"+
    			"[EMAIL]:"+ this.email+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_BAJA]:"+ this.fechaBaja+"\n";
    			
    }
   public  String getEspecialidadesString()
    {
    	if(especialidades!=null)
    	return "[SERVICIOS]"+especialidades.stream().map(e->e.toString()).collect(Collectors.joining("\n"));	
    	else
    		return "[ESPECIALIDADES] NO DISPONE.";
    }
   public String getIncidentesString()
    {
    	if(incidentes!=null)
    	return "[INCIDENTES]"+incidentes.stream().map(i->i.toString()).collect(Collectors.joining("\n"));	
    	else
    		return "[INCIDENTES] NO DISPONE.";
    }
  
    
    
}
