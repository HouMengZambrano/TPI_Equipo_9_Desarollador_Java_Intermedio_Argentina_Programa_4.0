package TpI_equipo9.Modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String CUIT;
    private  String nombre;
    private  String razonSocial;
    private String nroWhatsapp;
    private String email;
    private Date fechaAlta; 
    private Date fechaBaja;
    // No voy a colocar lo de fecha de alta y fecha de baja por que ya esta en el incidente;
    @ManyToMany
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
    		fechaAlta.setTime(cal.getTimeInMillis());
    	}
    	else if (fechaAIngresar.equals("fechaBaja"))
    	{
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