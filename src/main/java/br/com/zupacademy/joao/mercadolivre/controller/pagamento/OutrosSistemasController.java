package br.com.zupacademy.joao.mercadolivre.controller.pagamento;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request.NotaFiscalDePagamentoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request.RankingVendedorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

    @PostMapping("/notas-ficais")
    public void notasFiscais(@RequestBody NotaFiscalDePagamentoRequest request) throws InterruptedException {
        System.out.println("Criando nota para "+request.getIdCompra());
        System.out.println("Comprador: "+request.getGetIdComprador());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody RankingVendedorRequest resquest) throws InterruptedException {
        System.out.println("Inserindo pontuacao para "+resquest.getGetIdComprador());
        System.out.println("Pela ter feito a venda"+resquest.getIdCompra());
        Thread.sleep(150);
    }
}
