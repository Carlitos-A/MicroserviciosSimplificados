package cl.duoc.Microservicios.inventario.controller;


import cl.duoc.Microservicios.inventario.model.productos;
import cl.duoc.Microservicios.inventario.service.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/p1/productos") //Define lo que será manejado en la clase y en que ruta
public class productoController {

    @Autowired
    private productoService productoService;

    @GetMapping
    public ResponseEntity<?> ListarProductos(){
        List<productos> productos = productoService.BuscarProductos();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();

        }else{
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long idProducto){
        try{
            productos productoBuscado = productoService.BuscarUnProducto(idProducto);
            return ResponseEntity.ok(productoBuscado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarProducto(@RequestBody productos productoGuardar){
        try{
            productos productoRegistrar = productoService.GuardarProducto(productoGuardar);
            return ResponseEntity.ok(productoRegistrar);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al guardar el producto");
        }
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> EliminarProducto(@PathVariable Long idProducto){
        try{
            productos productoBuscado = productoService.BuscarUnProducto(idProducto);
            productoService.EliminarProducto(idProducto);
            return ResponseEntity.status(HttpStatus.OK).body("El producto ha sido eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto NO ENCONTRADO");
        }
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<?> ActualizarProducto(@PathVariable Long idProducto, @RequestBody productos productoActualizar){
        try{
            productos productoActualizado = productoService.BuscarUnProducto(idProducto);
            productoActualizado.setNombre(productoActualizar.getNombre());
            productoActualizado.setDescripcion(productoActualizar.getDescripcion());
            productoService.GuardarProducto(productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar el producto");
        }
    }





}
