package cl.duoc.Microservicios.ecoventas.model;


import cl.duoc.Microservicios.loginmicroservicio.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "VENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENTA")
    private Long idventa;

    @Column(name = "FECHAVENTA", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaventa;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "IDSUCURSAL", insertable = false, updatable = false)
    private cl.duoc.Microservicios.sucursal.model.Sucursal sucursal;


}
