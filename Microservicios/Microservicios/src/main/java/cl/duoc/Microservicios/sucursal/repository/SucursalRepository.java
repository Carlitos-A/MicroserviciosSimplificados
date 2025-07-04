package cl.duoc.Microservicios.sucursal.repository;

import cl.duoc.Microservicios.sucursal.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
