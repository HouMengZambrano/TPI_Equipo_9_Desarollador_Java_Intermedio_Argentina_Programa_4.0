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
@Table(name="especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
  @ManyToMany(mappedBy = "especialidades")
    private List<Problema> problemas;
  @ManyToMany(mappedBy = "especialidades")
    private List<Tecnico> tecnicos;
  
  
  
  public void agregarTecnico(Tecnico tec)
  {
  	if(tecnicos==null) tecnicos=new ArrayList<Tecnico>();
  		if(!tecnicos.contains(tec))
  			tecnicos.add(tec);
  }
  public void eliminarTecnico(Tecnico tec)
  {
  	if(tecnicos!=null)
  		if(tecnicos.contains(tec))
  			tecnicos.remove(tec);
  	
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
  
  public String toString()
  {
  	return "[NOMBRE]:"+ this.nombre+"\n"+
  			getTecnicosString()+"\n"+
  			getProblemasString();
  			
  }
  String getTecnicosString()
  {
  	if(tecnicos!=null)
  	return "[TECNICOS]"+tecnicos.stream().map(t->t.toString()).collect(Collectors.joining("\n"));	
  	else
  		return "[TECNICOS] NO DISPONE.";
  }
  String getProblemasString()
  {
  	if(problemas!=null)
  	return "[PROBLEMAS]"+problemas.stream().map(p->p.toString()).collect(Collectors.joining("\n"));	
  	else
  		return "[PROBLEMAS] NO DISPONE.";
  }
  
}
