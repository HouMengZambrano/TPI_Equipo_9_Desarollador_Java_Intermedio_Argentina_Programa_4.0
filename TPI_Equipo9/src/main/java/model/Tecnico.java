package model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tecnicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tecnico;
    private String nombre;
    private String apellido;
    private String nro_telefonico;
    private String mail;
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
}
