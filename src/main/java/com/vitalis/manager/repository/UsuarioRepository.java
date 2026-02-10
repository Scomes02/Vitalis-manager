package com.vitalis.manager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vitalis.manager.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	// Método mágico para encontrar usuario por su nombre de login
    Optional<Usuario> findByUsername(String username);
	
}
