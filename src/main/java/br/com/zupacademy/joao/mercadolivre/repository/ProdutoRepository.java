package br.com.zupacademy.joao.mercadolivre.repository;

import br.com.zupacademy.joao.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
