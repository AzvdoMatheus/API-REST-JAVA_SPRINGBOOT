package med.voll.api.infra.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice //indicar que é uma classe para tratar erros
public class TratadorErros {
  
  //entidade nao encontrada
  @ExceptionHandler(EntityNotFoundException.class) //tratar o erro 404 que for recebido
  public ResponseEntity<EntityNotFoundException> tratarErro404() {
    return ResponseEntity.notFound().build();
  }

  //informacoes passadas nao estao no formato necessario
  @ExceptionHandler(MethodArgumentNotValidException.class) //tratar o erro 400 que for recebido
  public ResponseEntity<List<DadosErroValidacaoDTO>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<DadosErroValidacaoDTO> erros = ex.getFieldErrors().stream()
            .map(DadosErroValidacaoDTO::new)
            .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(erros);
  }

  private record DadosErroValidacaoDTO(String campo, String mensagem){

    public DadosErroValidacaoDTO(FieldError erro) {
      this(erro.getField(), erro.getDefaultMessage());
    }
  }
    
}
