package cl.duoc.Microservicios.ecoventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.Microservicios.ecoventas.model.Venta;

public interface ventaRepository extends JpaRepository<Venta,Long> {
}
