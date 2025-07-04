package cl.duoc.Microservicios.inventario.model;
//Clase que define la entidad del producto, esta definida y mapeada la tabla donde estarán estas entidades en BD. Contiene todos los atributos para
// describir un producto

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class productos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "IDPRODUCTO")
        private Long idProducto;

        @Column(name = "NOMBRE", nullable = false)
        private String nombre;

        @Column(name = "DESCRIPCION", nullable = false)
        private String descripcion;

        @Column(name = "CATEGORIA", nullable = false)
        private String categoria;

        @Column(name = "MARCA", nullable = false)
        private String marca;

        @Column(name = "PRECIO_VENTA", nullable = false)
        private Integer precioVenta;
}


