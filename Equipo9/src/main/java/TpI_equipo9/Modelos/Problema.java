package TpI_equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "problemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private double tiempoMax;
    @ManyToMany
            @JoinTable(
                    name = "prob_esp",
                    joinColumns = @JoinColumn( name= "id_problema"),
                    inverseJoinColumns = @JoinColumn(name = "id_especialidad")
            )
    private List<Especialidad> especialidades;
    @ManyToMany
    @JoinTable(
            name = "serv_prob",
            joinColumns = @JoinColumn( name= "id_problema"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios;

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
    public void agregarEspecialidad(Especialidad esp)
    {
    	if(especialidades==null) especialidades=new ArrayList<Especialidad>();
    		if(!especialidades.contains(esp))
    			especialidades.add(esp);
    }
    public void eliminarEspecialidad(Especialidad esp)
    {
    	if(especialidades!=null)
    		if(especialidades.contains(esp))
    			especialidades.remove(esp);
    }

    @Override
    public String toString()
    {
    	return "[TIPO]:"+ this.tipo+"\n"+
    			"[TIEMPO_MAX(HS)]:"+ this.tiempoMax+"\n"+
    			getServiciosString()+"\n"+
    			getEspecialidadesString();
    
    }
    String getServiciosString()
    {
    	if(servicios!=null)
    	return "[SERVICIOS]"+servicios.stream().map(s->s.toString()).collect(Collectors.joining("\n"));	
    	else
    		return "[SERVICIOS] NO DISPONE.";
    }
    String getEspecialidadesString()
    {
    	if(especialidades!=null)
    	return "[SERVICIOS]"+especialidades.stream().map(e->e.toString()).collect(Collectors.joining("\n"));	
    	else
    		return "[ESPECIALIDADES] NO DISPONE.";
    }
    
}
