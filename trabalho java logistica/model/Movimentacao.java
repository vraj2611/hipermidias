package model;

import java.util.*;

import controller.*;

public class Movimentacao {
	private int id;
	private String origem;
	private String destino;
	private int id_motorista;
	private String nome_motorista;
	private int status;
	private List<Pedido> pedidos;
	
	public Movimentacao(){}
	
	public Movimentacao(String origem, String destino, int id_motorista, List<Pedido> selecionados) {
		this.origem = origem;
		this.destino = destino;
		this.id_motorista = id_motorista;
		this.pedidos = selecionados;
		this.status = 0;
		DMMovimentacao dmmov = new DMMovimentacao();
		dmmov.incluir(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getId_motorista() {
		return id_motorista;
	}

	public void setId_motorista(int id_motorista) {
		this.id_motorista = id_motorista;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getNome_motorista() {
		return nome_motorista;
	}

	public void setNome_motorista(String nome_motorista) {
		this.nome_motorista = nome_motorista;
	}

	public void concluir(){
		this.atualizarPedidos();
		Pedido p;
		int x;
		for(x = 0; x < pedidos.size() ; x ++){
			p = pedidos.get(x);
			p.setLocal(this.destino);
			if (this.destino.compareTo("Destino") == 0){
				p.setStatus("Entregue");
			} else {
				p.setStatus("No Centro de Distribuição");
			}
			p.alterar();
		}
		
		this.setStatus(1);
		DMMovimentacao dmmov = new DMMovimentacao();
		dmmov.alterar(this);
	}
	
	public void atualizarPedidos(){
		DMPedido dmped = new DMPedido();
		this.pedidos = dmped.consultar(this);
	}
	
	public List<Pedido> getPedidos(){
		return pedidos;
	}
}
