package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="incidentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_incidentes;
    private String descripcion;
    private Double tiempo_resolucion;
    private String complejidad;
    private Boolean estado; //  Esto hay que hacer un patron state por ahora queda asi;
    private LocalDate fecha_alta;
    private LocalDate fecha_baja;
    @ManyToMany(mappedBy = "incidentes")
    private List<Tecnico> tecnicos;
    @ManyToMany
    @JoinTable(
            name = "problemas",
            joinColumns = @JoinColumn( name= "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;
    @ManyToMany(mappedBy = "incidentes")
    private Cliente cliente;
}
