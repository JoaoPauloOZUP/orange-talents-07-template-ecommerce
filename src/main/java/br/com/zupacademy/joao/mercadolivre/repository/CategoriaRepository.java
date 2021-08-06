package br.com.zupacademy.joao.mercadolivre.repository;

import br.com.zupacademy.joao.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
