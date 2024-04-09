package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.controllers;

import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.domain.FlorEntity;
import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.dto.FlorDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.services.FlorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("Api/Flore")
public class FlorController {

    @Autowired
    FlorServices florServices;


    @PostMapping("/add")
    public ResponseEntity<?> createFlor(@RequestBody FlorDTO florDTO) {
        FlorDTO savedFlorDTO = florServices.save(florDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlorDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFlor(@PathVariable Integer id, @RequestBody FlorDTO florDTO) {
        FlorDTO updatedFlorDTO = florServices.update(id, florDTO);
        if (updatedFlorDTO != null) {
            return ResponseEntity.ok(updatedFlorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlor(@PathVariable Integer id) {
        florServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> obtenerFlorPorId(@PathVariable Integer id) {
        FlorDTO florDTO = florServices.findById(id);
        if (florDTO != null) {
            return ResponseEntity.ok(florDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasFlores() {
        List<FlorDTO> florDTOs = florServices.findAll();
        return ResponseEntity.ok(florDTOs);
    }
}
