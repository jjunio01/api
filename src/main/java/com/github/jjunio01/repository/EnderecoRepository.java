package com.github.jjunio01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jjunio01.model.Endereco;

/**
 * @author JJunio
 *
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

	List<Endereco> findByClienteId(int id);

}
