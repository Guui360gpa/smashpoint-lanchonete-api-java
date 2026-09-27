package br.com.lanchonete.smashpoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---- 404: recurso não encontrado / lista vazia ----
    @ExceptionHandler({
            ClienteNaoEncontradoException.class,
            PedidoNaoEncontradoException.class,
            ProdutoNaoEncontradoException.class,
            ItemPedidoNaoEncontradoException.class,
            ListaClientesVaziaException.class,
            ListaItemPedidoVaziaException.class,
            ListaPedidoVaziaException.class,
            ListaProdutosVaziaException.class
    })
    public ResponseEntity<ErrorResponse> handleNaoEncontrado(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Não encontrado", ex.getMessage()));
    }

    // ---- 409: conflito de estado (já existe / já está nesse status / regra de negócio bloqueando) ----
    @ExceptionHandler({
            ClienteExistenteNoBancoException.class,
            ProdutoExistenteNoBancoException.class,
            ClienteComPedidoAtivoJaExistenteException.class,
            PedidoJaAtivoException.class,
            PedidoJaDesativadoException.class,
            ProdutoJaAtivadoException.class,
            ProdutoJaDesativadoException.class,
            PedidoInativoException.class,
            ProdutoInativoException.class,
            PrazoReativacaoExpiradoException.class
    })
    public ResponseEntity<ErrorResponse> handleConflito(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(409, "Conflito", ex.getMessage()));
    }

    // ---- 400: entrada inválida ----
    @ExceptionHandler(ExcedeLimiteQuantidadeException.class)
    public ResponseEntity<ErrorResponse> handleEntradaInvalida(ExcedeLimiteQuantidadeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(400, "Requisição inválida", ex.getMessage()));
    }

    // ---- 400: falha de @Valid nos DTOs (campos obrigatórios, @CPF, @Min/@Max, etc.) ----
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("error", "Erro de validação");
        body.put("campos", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // ---- 500: qualquer coisa não mapeada (rede de segurança, não deveria ser o caminho normal) ----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenerico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(500, "Erro interno", "Ocorreu um erro inesperado."));
    }
}
