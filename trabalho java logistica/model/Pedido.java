package model;

import java.util.*;

import controller.*;

public class Pedido {

	private int id_pedido;
	private int id_cliente;
	private int quant;
	private String origem;
	private String destino;
	private String status;
	private String local;
	private List<Movimentacao> movimentacoes;
	
	public Pedido(){}
	
	public Pedido(String id){
		DMPedido dmped = new DMPedido();
		List<Pedido> lp = dmped.consultar(id);
		if (lp.size() > 0){
			Pedido p = lp.get(0);
			this.id_pedido = p.getId();
			this.id_cliente = p.getId_cliente();
			this.quant = p.getQuant();
			this.origem = p.getOrigem();
			this.destino = p.getDestino();
			this.status = p.getStatus();
			this.local = p.getLocal();
		}
	}
	
	public Pedido(int id_cliente, int quant, String origem, String destino) {
		this.id_cliente = id_cliente;
		this.quant = quant;
		this.origem = origem;
		this.destino = destino;
		this.incluir();
	}
	
	public int getId() {
		return id_pedido;
	}
	public void setId(int id) {
		this.id_pedido = id;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public void atualizarMovimentacoes(){
		DMMovimentacao dmmov = new DMMovimentacao();
		this.movimentacoes = (List<Movimentacao>) dmmov.consultar(this);
	}
	
	public List<Movimentacao> getMovimentacoes(){
		DMMovimentacao dmmov = new DMMovimentacao();
		this.movimentacoes = (List<Movimentacao>) dmmov.consultar(this);
		return movimentacoes;
	}

	public void incluir(){
		DMPedido dmpedido = new DMPedido();
		dmpedido.incluir(this);
	}

	public void alterar(){
		DMPedido dmpedido = new DMPedido();
		dmpedido.alterar(this);
	}

}
