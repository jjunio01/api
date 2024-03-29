package com.github.jjunio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jjunio01.model.Produto;

/**
 * @author JJunio
 *
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
