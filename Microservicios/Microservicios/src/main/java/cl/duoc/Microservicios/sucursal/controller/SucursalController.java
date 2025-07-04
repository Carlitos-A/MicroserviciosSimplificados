package cl.duoc.Microservicios.sucursal.controller;

import cl.duoc.Microservicios.sucursal.model.Sucursal;
import cl.duoc.Microservicios.sucursal.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("api/s1/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<?> ListarSucursal() {
        List<Sucursal> sucursal = sucursalService.BuscarSucursales();
        if (sucursal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(sucursal);
        }
    }

    @GetMapping("/{idSucursal}")
    public ResponseEntity<?> BuscarSucursalPorId(@PathVariable Long idSucursal) {
        try{
            Sucursal sucursal = sucursalService.BuscarSucursal(idSucursal);
            return ResponseEntity.status(HttpStatus.OK).body(sucursal);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }

    }
    @PostMapping
    public ResponseEntity<?> GuardarSucursal(@RequestBody Sucursal sucursalGuardar) {
        try{
            Sucursal sucursalRegistrar = sucursalService.GuardarSucursal(sucursalGuardar);
            return ResponseEntity.ok(sucursalRegistrar);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("no se encuentra esa sucursal");
        }
    }

    @DeleteMapping("/{idSucursal}")
    public ResponseEntity<?> EliminarSucursal(@PathVariable Long idSucursal) {
        try{
            Sucursal sucursalBuscada = sucursalService.BuscarSucursal(idSucursal);
            sucursalService.EliminarSucursal(idSucursal);
            return ResponseEntity.ok(sucursalBuscada);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }
    }

    @PutMapping("/{idSucursal}")
    public ResponseEntity<?> ActualizarSucursal(@PathVariable Long idSucursal, @RequestBody Sucursal sucursalActualizar) {
        try{
            Sucursal sucursalActualizada = sucursalService.BuscarSucursal(idSucursal);
            sucursalActualizada.setNombre(sucursalActualizar.getNombre());
            sucursalActualizada.setDireccion( sucursalActualizar.getDireccion());
            sucursalActualizada.setTelefono(sucursalActualizar.getTelefono());
            sucursalActualizada.setEmail(sucursalActualizar.getEmail());
            sucursalActualizada.setCiudad(sucursalActualizar.getCiudad());
            sucursalService.GuardarSucursal(sucursalActualizada);
            return ResponseEntity.ok(sucursalActualizada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }

    }

}
