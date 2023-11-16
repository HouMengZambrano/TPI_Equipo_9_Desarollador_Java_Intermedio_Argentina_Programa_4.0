package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "problemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_problema;
    private String tipo;
    private LocalDate tiempo_maximo;
    @ManyToMany
            @JoinTable(
                    name = "problemas",
                    joinColumns = @JoinColumn( name= "id_problema"),
                    inverseJoinColumns = @JoinColumn(name = "id_especialidad")
            )
    private List<Especialidad> especialidades;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;


}
