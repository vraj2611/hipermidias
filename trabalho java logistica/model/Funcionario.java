package model;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		this.selecionados = new ArrayList();
		//this.hr_acesso =  
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
		this.listarPedidos();
		return this.pedidos;
	}
	
	public int quantSelecionados(){
		return this.selecionados.size();
	}
	
	public Date getHr_acesso() {
		return hr_acesso;
	}

	public void setHr_acesso(Date hr_acesso) {
		this.hr_acesso = hr_acesso;
	}

	public Funcionario consultar(String id){
		DMFuncionario dmfunc = new DMFuncionario();
		return dmfunc.consultar(id);
	}

	public void atualizarMovs(){
		
	}
	
	public String listarMovs(){
		DMMovimentacao dmmov = new DMMovimentacao();
		this.movimentacoes = dmmov.consultar(this);
		
		String texto = "ID\tMotorista\tOrigem\tDestino\tStatus\n";
		Movimentacao m;
		int x;
		for (x = 0; x < movimentacoes.size(); x++){
			m = movimentacoes.get(x);
			String descr_status, end_destino, end_origem;
			
			if (m.getStatus() == 0) {descr_status = "Em transito";} else {descr_status = "Concluido";}
			if (m.getDestino().compareTo("Destino") != 0) {end_destino = "Centro " + m.getDestino();} else {end_destino = m.getDestino();}
			if (m.getOrigem().compareTo("Origem") != 0) {end_origem = "Centro " + m.getOrigem();} else {end_origem = m.getOrigem();}
			
			texto += m.getId() + "\t" + m.getNome_motorista() + "\t" + end_origem + "\t" + end_destino + "\t" + descr_status + "\n";
		}
		return texto;
	}
	
	public void ConcluirMov(String id){
		Movimentacao m;
		int x;
		for (x = 0; x < movimentacoes.size(); x++){
			m = movimentacoes.get(x);
			if (m.getId() == Integer.parseInt(id)){
				m.concluir();			
				System.out.println("Movimentação " + id + " concluida.");
			}
		}
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

	public String listarSelecionados() {
		int x;
		Pedido p = new Pedido();
		String lista = "ID\tOrigem\tDestino\tQuant\tStatus\n";
		if (selecionados.size() > 0){
			for (x=0; x < selecionados.size() ; x++){
				p = selecionados.get(x);
				lista += p.getId() + "\t" + p.getOrigem() + "\t" + p.getDestino() + "\t" + p.getQuant() + "\t" + p.getStatus() + "\n";
			}
		}
		return lista;
	}

	public void incluirPedidoNovaMov(String text) {
		Pedido p = new Pedido(text);
		Pedido s;
		int i;
		for(i=0 ; i < selecionados.size() ; i++){
			s = selecionados.get(i);
			if (p.getId() == s.getId()){
				p = null;	
				JOptionPane.showMessageDialog(null,"Pedido já está incluso!");
			}
		}
		
		if (p.getId() > 0) {
			selecionados.add(p);
		} else {
			JOptionPane.showMessageDialog(null,"Pedido não encontrado!");
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

	public JTable listarTabelaSelecionados() {
		JTable t = new JTable();
		String[] colunas = {"ID","Origem","Destino","Quant","Status"};
		List<String[]> lista = new ArrayList<>(); 
		
		lista.add(new String[]{"a","b","c","d","e"});
		
		int x;
		Pedido p;
		if (selecionados.size() > 0){
			for (x=0; x < selecionados.size() ; x++){
				p = pedidos.get(x);
				lista.add(new String[]{
					String.valueOf(p.getId()),
					String.valueOf(p.getOrigem()),
					String.valueOf(p.getDestino()),
					String.valueOf(p.getQuant()),
					String.valueOf(p.getStatus())
				});
			}
		}
		DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]),colunas);
		t.setModel(model);
		
		return t;
	}

	public JTable listarTabelaPedidos(){
		DMPedido dmpedido = new DMPedido();
		this.pedidos = dmpedido.consultar(this); 
		
		JTable t = new JTable();
		String[] colunas = {"ID","Origem","Destino","Quant","Status"};
		List<String[]> lista = new ArrayList<>();
		
		int x;
		Pedido p;
		for (x=0; x < pedidos.size() ; x++){
			p = pedidos.get(x);
			lista.add(new String[]{
				String.valueOf(p.getId()),
				String.valueOf(p.getOrigem()),
				String.valueOf(p.getDestino()),
				String.valueOf(p.getQuant()),
				String.valueOf(p.getStatus())
			});
		}	
		DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]),colunas);
		t.setModel(model);
		
		return t;

	}
	
}
