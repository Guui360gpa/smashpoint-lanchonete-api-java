package br.com.lanchonete.smashpoint.service.produto;

import br.com.lanchonete.smashpoint.dto.requests.ProdutoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.exception.ProdutoExistenteNoBancoException;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CadastrarProduto {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDto cadastrar(ProdutoRequestDto dto){

        if (produtoRepository.existsByNome(dto.nome())){
            throw new ProdutoExistenteNoBancoException("Produto existente");
        }

        Produto produto = new Produto(dto.nome(),dto.descricao(),dto.categoria(), BigDecimal.valueOf(dto.preco()));
        Produto produtoSalvo = salvarProdutoNoBanco(produto);

        return gerarProdutoResponse(produtoSalvo);
    }

    private ProdutoResponseDto gerarProdutoResponse(Produto p) {
        return new ProdutoResponseDto(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getCategoria().toString(),
                p.getPreco().doubleValue()
        );
    }

    private Produto salvarProdutoNoBanco(Produto produto) {
        return produtoRepository.save(produto);
    }


}
