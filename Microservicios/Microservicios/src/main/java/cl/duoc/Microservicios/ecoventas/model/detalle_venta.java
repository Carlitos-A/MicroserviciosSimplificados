package cl.duoc.Microservicios.ecoventas.model;


import cl.duoc.Microservicios.inventario.model.productos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class detalle_venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDETALLEVENTA")
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "IDVENTA", insertable = false, updatable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "IDPRODUCTO", insertable = false, updatable = false)
    private productos producto;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
}

