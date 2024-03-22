package co.com.toxement.toxdoortest.controller;

import co.com.toxement.toxdoortest.entity.Producto;
import co.com.toxement.toxdoortest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {

        List<Producto> productos = productoRepository.findAll();
        if (!productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> createOne(@RequestBody @Valid Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productoRepository.save(producto)
        );
    }

}
