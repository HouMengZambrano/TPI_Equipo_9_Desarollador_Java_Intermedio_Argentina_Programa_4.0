package TpI_equipo9.Modelos;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

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
    private Time tiempoMax;
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


}
