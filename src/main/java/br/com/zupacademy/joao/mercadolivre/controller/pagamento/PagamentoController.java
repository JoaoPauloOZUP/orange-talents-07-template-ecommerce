package br.com.zupacademy.joao.mercadolivre.controller.pagamento;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request.PagseguroRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request.PaypalRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.EventoCompraSucesso;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.GatewayPagamentoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.service.CompraProcessamento;
import br.com.zupacademy.joao.mercadolivre.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
public class PagamentoController {

    private EntityManager manager;

    private CompraProcessamento compraProcessamento;

    public PagamentoController(EntityManager manager, CompraProcessamento compraProcessamento) {
        this.manager = manager;
        this.compraProcessamento = compraProcessamento;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> pagseguro(@PathVariable("id") Long idDaCompra, @RequestBody @Valid PagseguroRequest request) {
        return pagamento(idDaCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> paypal(@PathVariable("id") Long idDaCompra, @RequestBody @Valid PaypalRequest request) {
        return pagamento(idDaCompra, request);
    }

    private ResponseEntity<?> pagamento(@NotNull Long idDaCompra, @Valid GatewayPagamentoRequest request) {
        // Instancia a compra com ID
        Compra compra = manager.find(Compra.class, idDaCompra);

        // Em relação a esta compra, é feito um tentativa de pagamento
        compra.tentativaPagamento(request);

        // Ao persistir/merge a compra, a transação é armazenada
        manager.merge(compra);

        compraProcessamento.processa(compra);

        return ResponseEntity.ok().build();
    }
}
