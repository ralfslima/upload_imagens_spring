package com.projeto.upload.servico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.upload.modelo.Imagem;
import com.projeto.upload.modelo.Produto;

@Service
public class ImagemService {
    @Value("${upload.path}") // Configurado no application.properties
    private String uploadPath;

    public void salvarImagem(MultipartFile file, Produto produto) throws IOException {
        String nomeArquivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(uploadPath, nomeArquivo), StandardCopyOption.REPLACE_EXISTING);

        Imagem imagem = new Imagem();
        imagem.setNomeArquivo(nomeArquivo);
        imagem.setProduto(produto);
        produto.getImagens().add(imagem);
    }
}