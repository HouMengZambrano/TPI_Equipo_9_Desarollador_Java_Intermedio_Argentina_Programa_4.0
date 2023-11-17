package model;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servicio;
    private Double precio;
    private String nombre;
    @OneToMany(mappedBy = "servicio")
    private List<Problema> problemas;
    @ManyToMany(mappedBy = "servicios")
    private List<Cliente> clientes;

}
