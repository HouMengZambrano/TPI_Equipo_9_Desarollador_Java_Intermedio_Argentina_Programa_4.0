package TpI_equipo9.Equipo9.Modelos;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servicio;
    private Double precio;
    private String nombre;
    @OneToMany(mappedBy = "servicio")
    private List<Problema> problemas;
    @ManyToMany(mappedBy = "servicios")
    private List<Cliente> clientes;
    
    @Override
    public String toString()
    {
    	return "[NOMBRE]:"+ this.nombre+"\n"+
    			"[PRECIO]:"+ this.precio+"\n";
    }

}
