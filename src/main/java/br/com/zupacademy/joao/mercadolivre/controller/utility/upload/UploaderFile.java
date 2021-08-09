package br.com.zupacademy.joao.mercadolivre.controller.utility.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/***
 * Com uma interface, posso ter várias classes que enviam arquivos apenas abstraindo desta.
 */
public interface UploaderFile {

    public Set<String> upload(List<MultipartFile> files);
}
