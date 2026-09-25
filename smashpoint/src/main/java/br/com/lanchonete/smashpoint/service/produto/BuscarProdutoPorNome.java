package br.com.lanchonete.smashpoint.service.produto;

import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.exception.ProdutoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarProdutoPorNome {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDto buscar(String nome){

        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        return ProdutoResponseDto.fromEntity(produto);
    }

}
