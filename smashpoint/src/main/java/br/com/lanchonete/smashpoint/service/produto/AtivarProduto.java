package br.com.lanchonete.smashpoint.service.produto;

import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.exception.ProdutoJaAtivadoException;
import br.com.lanchonete.smashpoint.exception.ProdutoJaDesativadoException;
import br.com.lanchonete.smashpoint.exception.ProdutoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtivarProduto {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDto ativar(String nome){
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        if (produto.getStatus().equals(Status.ATIVADO)){
            throw new ProdutoJaAtivadoException("O produto " + produto.getNome() + " já está ativado");
        }

        produto.setStatus(Status.ATIVADO);
        Produto produtoSalvo = salvarProdutoNoBanco(produto);

        return gerarProdutoResponse(produtoSalvo);
    }

    private Produto salvarProdutoNoBanco(Produto produto) {
        return produtoRepository.save(produto);
    }

    private ProdutoResponseDto gerarProdutoResponse(Produto p) {
        return new ProdutoResponseDto(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getCategoria().toString(),
                p.getPreco().doubleValue(),
                p.getStatus()
        );
    }
}
