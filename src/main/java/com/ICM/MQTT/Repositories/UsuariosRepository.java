package com.ICM.MQTT.Repositories;

import com.ICM.MQTT.Models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
}
