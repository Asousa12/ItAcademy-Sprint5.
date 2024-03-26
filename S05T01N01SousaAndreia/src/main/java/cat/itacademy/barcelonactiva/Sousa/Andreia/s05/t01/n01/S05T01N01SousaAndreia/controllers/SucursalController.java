package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n01.S05T01N01SousaAndreia.controllers;

import java.util.List;

import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n01.S05T01N01SousaAndreia.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n01.S05T01N01SousaAndreia.services.SucursalServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    @Autowired
    SucursalServicesImp sucursalServicesImp;

    @PostMapping("/add")
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO sucursalDTO1 = sucursalServicesImp.save(sucursalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalDTO1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable int id, @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO sucursalDTO2 = sucursalServicesImp.update(id, sucursalDTO);

        return ResponseEntity.ok(sucursalDTO2);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable int id) {
        String message = sucursalServicesImp.delete(id);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SucursalDTO> getOneSucursal(@PathVariable int id) {
        try {
            SucursalDTO sucursalDTO = sucursalServicesImp.findById(id);
            return ResponseEntity.ok(sucursalDTO);
        } catch (EntityNotFoundException enfe) {
            enfe.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SucursalDTO>> getAll() {
        List<SucursalDTO> sucursales = sucursalServicesImp.findAll();

        if (sucursales.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sucursales);
        }
    }
}