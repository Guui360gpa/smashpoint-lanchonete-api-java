package br.com.lanchonete.smashpoint;

import br.com.lanchonete.smashpoint.main.Main;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmashpointApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository repositoryProduto;

	@Autowired
	private ClienteRepository repositoryCliente;

	@Autowired
	private PedidoRepository repositoryPedido;

	public static void main(String[] args) {
		SpringApplication.run(SmashpointApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositoryProduto,repositoryCliente,repositoryPedido);
		main.exibirMenuMain();


	}
}
