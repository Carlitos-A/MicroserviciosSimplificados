package cl.duoc.Microservicios.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.Microservicios.inventario.model.productos;
//Esta interfaz actúa como la capa de acceso a datos del microservicio.
// Permite realizar operaciones CRUD sobre la entidad productos sin necesidad de escribir SQL manualmente.

public interface productoRepository extends JpaRepository<productos,Long> {
}
