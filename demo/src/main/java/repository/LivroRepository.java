package repository;

import model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro> {
}

