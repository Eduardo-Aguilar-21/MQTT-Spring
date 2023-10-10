package com.ICM.MQTT.Controllers;

import com.ICM.MQTT.Models.UsuariosModel;
import com.ICM.MQTT.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {
    @Autowired
    UsuariosService usuariosService;

    @GetMapping
    public List<UsuariosModel> GetAll() {
        return usuariosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> GetById(@PathVariable Long id){
        Optional<UsuariosModel> usuario = usuariosService.GetById(id);
        if(usuario.isPresent()){
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UsuariosModel> CreateUser(@RequestBody UsuariosModel usuariosModel){
        UsuariosModel usuario = usuariosService.CreateUser(usuariosModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/incrementar/{id}/{num}")
    public ResponseEntity<UsuariosModel> Incrementar(@PathVariable Long id,@PathVariable Integer num){
        Optional<UsuariosModel> existing = usuariosService.GetById(id);
        if (existing.isPresent()){
            UsuariosModel usuario = usuariosService.Incrementar(id, num);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
