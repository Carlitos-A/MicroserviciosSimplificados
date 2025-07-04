package cl.duoc.Microservicios.inventario.model;
//Clase que define la entidad del producto, esta definida y mapeada la tabla donde estarán estas entidades en BD. Contiene todos los atributos para
// describir un producto

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario_sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class inventario_sucursal {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "IDINVENTARIOSUCURSAL")
        private Long idInventarioSucursal;

        @ManyToOne
        @JoinColumn(name = "IDSUCURSAL", insertable = false, updatable = false)
        private cl.duoc.Microservicios.sucursal.model.Sucursal sucursal;

        @ManyToOne
        @JoinColumn(name = "IDPRODUCTO", insertable = false, updatable = false)
        private productos producto;

        @Column(name = "STOCK", nullable = false)
        private Integer stock;
}

