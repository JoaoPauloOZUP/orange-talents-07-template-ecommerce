package br.com.zupacademy.joao.mercadolivre.repository;

import br.com.zupacademy.joao.mercadolivre.model.Pergunta;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByProduto(Produto produto);
}
