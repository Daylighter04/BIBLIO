package service;

import model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LivroRepository;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService() {
    }

    @Transactional
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    @Transactional
    public Livro emprestar(Long id) {
        Livro livro = buscarPorId(id);
        if (livro != null) {
            livro.setEmprestado(true);
            return salvar(livro);
        }
        return null;
    }

    @Transactional
    public Livro devolver(Long id) {
        Livro livro = buscarPorId(id);
        if (livro != null) {
            livro.setEmprestado(false);
            return salvar(livro);
        }
        return null;
    }
}

