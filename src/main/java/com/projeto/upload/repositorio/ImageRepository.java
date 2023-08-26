package com.projeto.upload.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.upload.modelo.Imagem;

public interface ImageRepository extends JpaRepository<Imagem, Long> {
}