package br.com.lanchonete.smashpoint.service.produto;

import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.exception.ProdutoJaDesativadoException;
import br.com.lanchonete.smashpoint.exception.ProdutoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesativarProduto {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDto desativar(String nome){
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        if (produto.getStatus().equals(Status.DESATIVADO)){
            throw new ProdutoJaDesativadoException("O produto " + produto.getNome() + " já está desativado");
        }

        produto.setStatus(Status.DESATIVADO);
        Produto produtoSalvo = produtoRepository.save(produto);

        return ProdutoResponseDto.fromEntity(produtoSalvo);
    }
}
