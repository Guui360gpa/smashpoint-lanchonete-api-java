package br.com.lanchonete.smashpoint.service.produto;

import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaProdutosVaziaException;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProdutosAtivos {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponseDto> listar (){
        List<Produto> produtos = produtoRepository.findByStatus(Status.ATIVADO);

        if (produtos.isEmpty()){
            throw new ListaProdutosVaziaException("Nenhum produto ativo");
        }

        return gerarListaProdutoResponse(produtos);
    }

    private List<ProdutoResponseDto> gerarListaProdutoResponse(List<Produto> produtos){
        return produtos.stream()
                .map(p -> new ProdutoResponseDto(
                        p.getId(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getCategoria().toString(),
                        p.getPreco().doubleValue(),
                        p.getStatus()
                )).toList();
    }

}
