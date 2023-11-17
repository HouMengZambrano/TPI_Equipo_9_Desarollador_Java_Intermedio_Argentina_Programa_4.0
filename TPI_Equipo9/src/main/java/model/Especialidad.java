package model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name="especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_especialidad;
    private String nombre;
  @ManyToMany(mappedBy = "especialidades")
    private List<Problema> problemas;
  @ManyToMany(mappedBy = "especialidades")
    private List<Tecnico> tecnicos;
}
