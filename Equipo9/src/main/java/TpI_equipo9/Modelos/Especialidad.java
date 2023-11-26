package TpI_equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
}
