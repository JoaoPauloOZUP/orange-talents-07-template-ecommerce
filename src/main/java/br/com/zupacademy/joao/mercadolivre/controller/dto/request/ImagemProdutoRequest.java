package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.utility.upload.EnviarImagem;
import br.com.zupacademy.joao.mercadolivre.model.ImagemProduto;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImagemProdutoRequest {

    @Size(min = 1)
    @NotNull(message = "Deve conter no mínimo uma imagem")
    List<MultipartFile> images;

    /**
     * O método setImagem se faz nescessário pois a requisição não pelo o corpo e sim por um forms.
     * */
    public void setImages(@Size(min = 1) @NotNull List<MultipartFile> images) {
        this.images = images;
    }

    public Set<String> linksImagens(EnviarImagem enviar) {
        Set<ImagemProduto> imagemProdutos = new HashSet<>();
        Set<String> links = enviar.upload(this.images);

        return links;
    }
}
