package com.ICM.MQTT.Services;

import com.ICM.MQTT.Models.UsuariosModel;
import com.ICM.MQTT.Repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    @Autowired
    UsuariosRepository usuariosRepository;

    public List<UsuariosModel> GetAll(){
        return usuariosRepository.findAll();
    }

    public Optional<UsuariosModel> GetById(Long id){
        return usuariosRepository.findById(id);
    }

    public UsuariosModel CreateUser(UsuariosModel usuariosModel){
        return usuariosRepository.save(usuariosModel);
    }

    public UsuariosModel Incrementar(Long id, Integer num){
        Optional<UsuariosModel> existing = usuariosRepository.findById(id);
        if(existing.isPresent()){
            UsuariosModel usuarios = new UsuariosModel();
            usuarios.setAumentador(usuarios.getAumentador() + num);
            return usuariosRepository.save(usuarios);
        }
        return null;
    }
}
