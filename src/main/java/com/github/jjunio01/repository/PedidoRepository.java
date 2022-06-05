package com.github.jjunio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jjunio01.model.Pedido;

/**
 * @author JJunio
 *
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
