package TpI_equipo9.Modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;



import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cliente implements Serializable{
	public enum MetodoNotificacion {NRO_WHATSAPP,EMAIL}
	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String CUIT;
    private  String nombre;
    private  String razonSocial;
    private String nroWhatsapp;
    private String email;
    private Timestamp fechaAlta;
    private Timestamp fechaBaja;
    private MetodoNotificacion metodoE=MetodoNotificacion.EMAIL;
    
    @ManyToMany(cascade=javax.persistence.CascadeType.REMOVE)
    @JoinTable(
            name = "serv_cli",
            joinColumns = @JoinColumn( name= "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios;
    @OneToMany(mappedBy = "cliente")
    private List<Incidente> incidentes;
    
 
    
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
    	else if (fechaAIngresar.equals("fechaBaja"))
    	{
    		fechaBaja=new Timestamp(Calendar.getInstance().get(Calendar.LONG));
    		fechaBaja.setTime(cal.getTimeInMillis());
    	}
    	else
    	{
    		System.out.println("no se ingreso una fecha valida");
    	}
    }
    
    
    public Cliente(String datosString)
    {
    	String[] datos=datosString.split(",");
		this.nombre=datos[0];
		this.CUIT=datos[1];
		this.razonSocial=datos[2];
		this.nroWhatsapp=datos[3];
		this.email=datos[4];
		this.fechaAlta=new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
    
    public void agregarIncidente(Incidente in)
    {
    	if(incidentes==null) incidentes=new ArrayList<Incidente>();
    		if(!incidentes.contains(in))
    			incidentes.add(in);
    	 
    }
    public void eliminarIncidente(Incidente in)
    {
    	if(incidentes!=null)
    		if(incidentes.contains(in))
    			incidentes.remove(in);
    	
    }
    public void agregarServicio(Servicio ser)
    {
    	if(servicios==null) servicios= new ArrayList<Servicio>();
    		if(!servicios.contains(ser))
    			servicios.add(ser);
    }
    public void eliminarServicio(Servicio ser)
    {
    	if(servicios!=null)
    		if(servicios.contains(ser))
    			servicios.remove(ser);
    }
    
    
    
    
    @Override
    public String toString()
    {
    	return "[NOMBRE]:"+ this.nombre+"\n"+
    			"[CUIT]:"+ this.CUIT+"\n"+
    			"[RAZON_SOCIAL]:"+ this.razonSocial+"\n"+
    			"[TELEFONO]:"+ this.nroWhatsapp+"\n"+
    			"[EMAIL]:"+ this.email+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_BAJA]:"+ this.fechaBaja+"\n";
    }


  
}
