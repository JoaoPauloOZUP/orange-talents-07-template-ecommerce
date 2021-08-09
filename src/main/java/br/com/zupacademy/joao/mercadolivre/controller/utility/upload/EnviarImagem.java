package br.com.zupacademy.joao.mercadolivre.controller.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnviarImagem implements UploaderFile {

    // Gera os "falsos" para simuar um envio de uploud de arqivos
    @Override
    public Set<String> upload(List<MultipartFile> files) {
        return files.stream().map(f -> "https://banco-de-imagens/"+f.getOriginalFilename()).collect(Collectors.toSet());
    }
}
