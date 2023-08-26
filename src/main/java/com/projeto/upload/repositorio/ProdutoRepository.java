package com.projeto.upload.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.upload.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}