package TpI_equipo9.Equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="incidentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_incidentes;
    private String descripcion;
    private Double tiempo_resolucion;
    private String complejidad;
    private Estado estado; //  Esto hay que hacer un patron state por ahora queda asi;
    private Date fechaAlta;
    private Date fechaResolucion;
    @ManyToMany(mappedBy = "incidentes")
    private List<Tecnico> tecnicos;
    @ManyToMany
    @JoinTable(
            name = "incidente_x_problema",
            joinColumns = @JoinColumn( name= "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;
    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    
    
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
    	else if (fechaAIngresar.equals("fechaResolucion"))
    	{
    		fechaResolucion=(Date) cal.getTime();
    	}
    	else
    	{
    		System.out.println("no se ingreso una fecha valida");
    	}
    }
    
    @Override
    public String toString()
    {
    	return "[DESCRIPCION]:"+ this.descripcion+"\n"+
    			"[TIEMPO_RESOLUCION]:"+ this.tiempo_resolucion+"\n"+
    			"[COMPLEJIDAD]:"+ this.complejidad+"\n"+
    			"[ESTADO]:"+ this.estado.getEstadoActual()+"\n"+
    			"[FECHA_ALTA]:"+ this.fechaAlta+"\n"+
    			"[FECHA_RESOLUCION]:"+ this.fechaResolucion+"\n";
    }
    
    
}
