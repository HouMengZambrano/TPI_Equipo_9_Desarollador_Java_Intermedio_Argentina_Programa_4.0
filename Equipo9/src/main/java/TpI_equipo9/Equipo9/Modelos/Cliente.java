package TpI_equipo9.Equipo9.Modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
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
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    private  Integer CUIT;
    private  String nombre;
    private  String razon_social;
    private String telefono;
    private String email;
    private Date fechaAlta;
    private Date fechaBaja;
    // No voy a colocar lo de fecha de alta y fecha de baja por que ya esta en el incidente;
    @ManyToMany
    @JoinTable(
            name = "clientes_x_servicio",
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
    		fechaAlta=(Date) cal.getTime();
    	}
    	else if (fechaAIngresar.equals("fechaBaja"))
    	{
    		fechaBaja=(Date) cal.getTime();
    	}
    	else
    	{
    		System.out.println("no se ingreso una fecha valida");
    	}
    }
    
    
    @Override
    public String toString()
    {
    	return "[NOMBRE]:"+ this.nombre+"\n"+
    			"[CUIT]:"+ this.CUIT+"\n"+
    			"[RAZON_SOCIAL]:"+ this.razon_social+"\n"+
    			"[TELEFONO]:"+ this.telefono+"\n"+
    			"[EMAIL]:"+ this.email+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_BAJA]:"+ this.fechaBaja+"\n";
    }
    
}
