package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    private  Integer CUI;
    private  String nombre;
    private  String razon_social;
    private String telefono;
    private String email;
    // No voy a colocar lo de fecha de alta y fecha de baja por que ya esta en el incidente;
    @ManyToMany
    @JoinTable(
            name = "clientes_x_servicio",
            joinColumns = @JoinColumn( name= "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios;
    @OneToMany(mappedBy = "cliente")
    private List<Incidente> incidentes;
}
