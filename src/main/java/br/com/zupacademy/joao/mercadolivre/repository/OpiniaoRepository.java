package br.com.zupacademy.joao.mercadolivre.repository;

import br.com.zupacademy.joao.mercadolivre.model.Opiniao;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {

    List<Opiniao> findByProduto(Produto produto);
}
