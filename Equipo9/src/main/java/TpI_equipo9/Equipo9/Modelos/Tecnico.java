package TpI_equipo9.Equipo9.Modelos;

import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="tecnicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tecnico;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Date fechaAlta;
    private Date fechaBaja;
    @ManyToMany
    @JoinTable(
            name = "tecnico_x_especialidad",
            joinColumns = @JoinColumn( name= "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;
    @ManyToMany
    @JoinTable(
            name = "tecnico_x_incidente",
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
    			"[APELLIDO]:"+ this.apellido+"\n"+
    			"[TELEFONO]:"+ this.telefono+"\n"+
    			"[EMAIL]:"+ this.email+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_BAJA]:"+ this.fechaBaja+"\n";
    }
    
    
    
}
