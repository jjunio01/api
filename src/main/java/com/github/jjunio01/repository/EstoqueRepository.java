package com.github.jjunio01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jjunio01.model.Estoque;

/**
 * @author JJunio
 *
 */
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

	List<Estoque> findByFornecedorId(int id);

	boolean existsByProdutoId(int idProduto);

	Optional<Estoque> findByProdutoId(int idProduto);

}
