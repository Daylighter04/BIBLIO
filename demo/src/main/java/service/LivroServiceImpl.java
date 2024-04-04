package service;

import model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LivroRepository;

@Service
public class LivroServiceImpl extends LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroServiceImpl(LivroRepository livroRepository) {
        super();
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    @Override
    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Livro emprestar(Long id) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null && !livro.isEmprestado()) {
            livro.setEmprestado(true);
            return livroRepository.save(livro);
        }
        return null;
    }

    @Override
    public Livro devolver(Long id) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null && livro.isEmprestado()) {
            livro.setEmprestado(false);
            return livroRepository.save(livro);
        }
        return null;
    }
}

