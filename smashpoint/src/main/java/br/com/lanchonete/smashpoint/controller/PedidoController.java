package br.com.lanchonete.smashpoint.controller;


import br.com.lanchonete.smashpoint.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoController{

    @Autowired
    private PedidoService pedidoService;

    public void realizarPedido(){

                pedidoService.realizar();

    }
}