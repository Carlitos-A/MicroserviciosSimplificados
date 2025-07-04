package cl.duoc.Microservicios.sucursal.model;


import cl.duoc.Microservicios.ecoventas.model.Venta;
import cl.duoc.Microservicios.inventario.model.inventario_sucursal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "SUCURSAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSUCURSAL")
    private Long idsucursal;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;

    @Column(name = "CIUDAD", nullable = false)
    private String ciudad;

    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

    @Column(name = "EMAIL")
    private String email;

}


