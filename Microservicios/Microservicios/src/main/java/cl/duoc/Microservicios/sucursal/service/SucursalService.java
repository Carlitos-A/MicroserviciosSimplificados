package cl.duoc.Microservicios.sucursal.service;

import cl.duoc.Microservicios.sucursal.model.Sucursal;
import cl.duoc.Microservicios.sucursal.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> BuscarSucursales() {
        return sucursalRepository.findAll();
    }

    public Sucursal BuscarSucursal(Long idSucursal) {
        return sucursalRepository.findById(idSucursal).get();
    }

    public Sucursal GuardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void EliminarSucursal(Long idSucursal) {
        sucursalRepository.deleteById(idSucursal);
    }
}
