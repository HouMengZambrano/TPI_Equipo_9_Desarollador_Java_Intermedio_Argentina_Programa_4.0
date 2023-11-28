package TpI_equipo9.Modelos;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double precio;
    private String nombre;
    @ManyToMany(mappedBy = "servicios")
    private List<Problema> problemas;
    @ManyToMany(mappedBy = "servicios")
    private List<Cliente> clientes;
    
    
    
    public Servicio(String datosString)
    {
    	String[] datos=datosString.split(",");
		this.nombre=datos[0];
		this.precio=Double.parseDouble(datos[1]);
	
    }
    
    

    public void agregarCliente(Cliente cl)
    {
    	if(clientes==null) clientes=new ArrayList<Cliente>();
    	clientes.add(cl);
    	
    }
    public void eliminarCliente(Cliente cl)
    {
    	if(clientes!=null)
    	clientes.remove(cl);
    	
    }
    public void agregarProblema(Problema pro)
    {
    	if(problemas==null) problemas= new ArrayList<Problema>();
    	problemas.add(pro);
    }
    public void eliminarProblema(Problema pro)
    {
    	if(problemas!=null)
    	problemas.remove(pro);
    }
    
    
    
    @Override
    public String toString()
    {
    	return "[NOMBRE]:"+ this.nombre+"\n"+
    			"[PRECIO]:"+ this.precio+"\n";
    }

}
