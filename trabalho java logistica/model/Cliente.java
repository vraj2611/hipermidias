package model;

import java.util.*;

import controller.*;

public class Cliente {

	private int id;
	private String nome;
	private String cpf_cnpj;
	private String telefone;
	private List<Pedido> pedidos = new ArrayList();
	
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

	public int getQuantPedidos(){
		return pedidos.size();
	}
	
	public Cliente consultar(String id){
		DMCliente dmcliente = new DMCliente();
		return dmcliente.consultar(id);      
	}

	public String acompanharPedido(String id_pedido){
		String lista = "Pedido " + id_pedido + " não encontrado!";
		int id = Integer.parseInt(id_pedido);
		int x, m;
		Pedido p;
		List<Movimentacao> movs;
		Movimentacao mov;
		String origem, destino, status;
		for (x = 0 ; x < pedidos.size() ; x++){
			p = pedidos.get(x);
			if (p.getId() == id){
				lista = "";
				p.atualizarMovimentacoes();
				movs = p.getMovimentacoes();
				for (m=0; m < movs.size() ; m++){
					mov = movs.get(m);
					
					if (mov.getOrigem().compareTo("Origem") == 0){
						origem = p.getOrigem();
					} else {
						origem = "Centro " + mov.getOrigem();
					}
					
					if (mov.getDestino().compareTo("Destino") == 0){
						destino = p.getDestino();
					} else {
						destino = "Centro " + mov.getDestino();
					}
					
					if (mov.getStatus() == 0) { status = "Em Transito"; } else { status = "Concluido";}
					
					lista += "De " + origem + " para " + destino + ". (" + status + ")\n"; 
				}
					
			}
		}	
		return lista;
	}
	
	public String listarPedidos(){
		DMPedido dmpedido = new DMPedido();
		this.pedidos = dmpedido.consultar(this); 
		
		int x;
		Pedido p;
		String lista = "ID\tOrigem\tDestino\tQuant\tStatus\n";
		for (x=0; x < pedidos.size() ; x++){
			p = pedidos.get(x);
			lista += p.getId() + "\t" + p.getOrigem() + "\t" + p.getDestino() + "\t" + p.getQuant() + "\t" + p.getStatus() + "\n";
		}
		return lista;
	}
}
