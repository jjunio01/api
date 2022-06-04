package com.github.jjunio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jjunio01.model.Cliente;

/**
 * @author JJunio
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
