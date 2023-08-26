package com.projeto.upload.controle;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.upload.modelo.Produto;
import com.projeto.upload.repositorio.ProdutoRepository;
import com.projeto.upload.servico.ImagemService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemService imagemService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return ResponseEntity.ok("Produto cadastrado com sucesso.");
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadImagem(@PathVariable Long id, @RequestParam("imagem") MultipartFile imagemFile) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            imagemService.salvarImagem(imagemFile, produto);
            produtoRepository.save(produto);
            return ResponseEntity.ok("Imagem cadastrada com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem.");
        }
    }
}