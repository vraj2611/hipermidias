package model;

import java.util.*;

import controller.*;

public class Cliente {

	private int id;
	private String nome;
	private String cpf_cnpj;
	private String telefone;
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Cliente(){}
	
	public Cliente(String id){
		Cliente c = new Cliente();
		c = this.consultar(id);
		this.id = c.getId();
		this.nome = c.getNome();
		this.telefone = c.getTelefone();
		this.cpf_cnpj = c.getCpf_cnpj();
	}
	
	public Cliente(String nome, String telefone, String cpf_cnpj){
		this.nome = nome;
		this.telefone = telefone;
		this.cpf_cnpj = cpf_cnpj;
		DMCliente dmcliente = new DMCliente();
		dmcliente.incluir(this);
	}
	
	public Cliente consultar(String id){
		DMCliente dmcliente = new DMCliente();
		return dmcliente.consultar(id);      
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Pedido> getPedidos(){
		DMPedido dmpedido = new DMPedido();
		this.pedidos = dmpedido.consultar(this); 
		return this.pedidos;
	}
	
}
