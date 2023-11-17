package model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
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
    private Estado estado; //  Esto hay que hacer un patron state por ahora queda asi;
    private Date fecha_alta;
    private Date fecha_baja;
    @ManyToMany(mappedBy = "incidentes")
    private List<Tecnico> tecnicos;
    @ManyToMany
    @JoinTable(
            name = "incidente_x_problema",
            joinColumns = @JoinColumn( name= "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;
    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
