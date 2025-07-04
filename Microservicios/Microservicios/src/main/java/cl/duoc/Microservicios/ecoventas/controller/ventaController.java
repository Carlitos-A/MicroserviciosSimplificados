package cl.duoc.Microservicios.ecoventas.controller;


import cl.duoc.Microservicios.ecoventas.model.Venta;;
import cl.duoc.Microservicios.ecoventas.service.ventaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class ventaController {
    @Autowired
    private ventaService ventaservice;

    @GetMapping
    public ResponseEntity<?> ListarVentas() {
        List<Venta> ventas =  ventaservice.BuscarTodaVenta();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCUENTRAN DATOS");
        }else{
            return ResponseEntity.ok(ventas);
        }
    }

    @GetMapping("/{idventa}")
    public ResponseEntity<?> BuscarVenta(@PathVariable Long idventa) {
        try {
            Venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            return ResponseEntity.ok(ventabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCUENTRAN VENTAS");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarVenta(@RequestBody Venta ventaGuardar) {
        try{
            Venta ventaregistrar = ventaservice.GuardarVenta(ventaGuardar);
            return ResponseEntity.ok(ventaregistrar);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("NO SE PUEDE ALMACENAR LA VENTA");
        }

    }
    @DeleteMapping("/{idventa}")
    public ResponseEntity<?> EliminarVenta(@PathVariable Long idventa) {
        try{
            Venta ventaBuscada = ventaservice.BuscarUnaVenta(idventa);
            ventaservice.EliminarVenta(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("LA VENTA HA SIDO ELIMINADA");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VENTA NO ENCONTRADA");

        }


    }
    @PutMapping("/{idventa}")
    public ResponseEntity<?> ActualizarVenta(@PathVariable Long idventa, @RequestBody Venta ventaActualizar) {
        try{
            Venta ventaActualizada = ventaservice.BuscarUnaVenta(idventa);
            ventaActualizada.setUsuario(ventaActualizar.getUsuario());
            ventaActualizada.setFechaventa(ventaActualizar.getFechaventa());
            ventaservice.GuardarVenta(ventaActualizada);
            return ResponseEntity.ok(ventaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ESA VENTA NO SE ENCUENTRA REGISTTRADA");
        }

    }
}


