package controller;

import model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvar(livro);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id);
        if (livro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        livroService.deletar(id);
    }

    @PostMapping("/{id}/emprestar")
    public ResponseEntity<Livro> emprestar(@PathVariable Long id) {
        Livro livroEmprestado = livroService.emprestar(id);
        if (livroEmprestado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livroEmprestado, HttpStatus.OK);
    }

    @PostMapping("/{id}/devolver")
    public ResponseEntity<Livro> devolver(@PathVariable Long id) {
        Livro livroDevolvido = livroService.devolver(id);
        if (livroDevolvido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livroDevolvido, HttpStatus.OK);
    }
}
