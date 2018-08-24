package model;

import java.util.*;

import javax.swing.JOptionPane;

import controller.*;

public class Funcionario {

	private int id;
	private String nome;
	private int id_cargo;
	private String cargo;
	private int centro;
	private Date hr_acesso; 
	private List<Movimentacao> movimentacoes;
	private List<Pedido> pedidos;
	private List<Pedido> selecionados;
	
	public Funcionario(){}
	
	public Funcionario(String id){
		Funcionario f = new Funcionario();
		f = this.consultar(id);
		this.id = f.getId();
		this.nome = f.getNome();
		this.id_cargo = f.getId_cargo();
		this.cargo = f.getCargo();
		this.centro = f.getCentro();
		this.selecionados = new ArrayList<Pedido>();
		this.hr_acesso = f.getHr_acesso(); 
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
	public int getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getCentro() {
		return centro;
	}
	public void setCentro(int centro) {
		this.centro = centro;
	}
	
	public List<Pedido> getSelecionados(){
		return this.selecionados;
	}
	
	public List<Pedido> getPedidos(){
		DMPedido dmpedido = new DMPedido();
		this.pedidos = dmpedido.consultar(this);
		return this.pedidos;
	}

	public List<Movimentacao> getMovs(){
		DMMovimentacao dmmov = new DMMovimentacao();
		this.movimentacoes = dmmov.consultar(this);
		return this.movimentacoes;
	}
	
	public int quantSelecionados(){
		return this.selecionados.size();
	}
	
	public Date getHr_acesso() {
		return hr_acesso;
	}

	public Funcionario consultar(String id){
		DMFuncionario dmfunc = new DMFuncionario();
		return dmfunc.consultar(id);
	}
		
	public void ConcluirMov(String id){
		Movimentacao m;
		int x;
		int status = 0;
		if (id.length() > 0){
			for (x = 0; x < movimentacoes.size(); x++){
				m = movimentacoes.get(x);
				if (m.getId() == Integer.parseInt(id)){
					m.concluir();			
					status = 1;
				}
			}
			if (status == 1 ){
				JOptionPane.showMessageDialog(null,"Movimentação " + id + " concluida.");
			} else {
				JOptionPane.showMessageDialog(null,"Movimentação não encontrada.");
			}
		} else {
			
		}
		
	
	}
	
	public void incluirPedidoNovaMov(String text) {
		Pedido p = new Pedido(text);
		Pedido s;
		int status = 0;
		int i;
		if (p.getId() > 0) {
			for(i=0 ; i < selecionados.size() ; i++){
				s = selecionados.get(i);
				if (p.getId() == s.getId()){	
					status = 1;
					JOptionPane.showMessageDialog(null,"Pedido já está incluso!");
				}
			}
			
			if (status == 0){
				for(i=0 ; i < pedidos.size() ; i++){
					s = pedidos.get(i);
					if (p.getId() == s.getId()){
						selecionados.add(p);	
						status = 1;
					}
				}
				if (status == 0){
					JOptionPane.showMessageDialog(null,"Pedido não disponível!");
				}
			}
			
		} else {
			JOptionPane.showMessageDialog(null,"Pedido não existe!");
		}
	}
	
	public void excluirPedidoNovaMov(String text) {
		int i;
		Pedido p;
		for(i=0 ; i < selecionados.size() ; i++){
			p = selecionados.get(i);
			if (Integer.parseInt(text) == p.getId()){ selecionados.remove(i); }
		}
	}
	
}
