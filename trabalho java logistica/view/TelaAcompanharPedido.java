package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.*;

public class TelaAcompanharPedido extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lpedido, lid;
	private JTextField tpedido;
	private JButton bAcompanhar, bNovoPedido, bListarPedidos, bLogoff;
	private JPanel pInfo, pStatus;
	private JTable tabstatus;
	private JScrollPane scroll;
	private Cliente cliente;
	
	public TelaAcompanharPedido(Cliente c){
		
		this.setSize(450,420);
		this.setTitle("Acompanhamento de Pedidos");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.cliente = c;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("ACOMPANHAMENTO DE PEDIDOS");
		ltitulo.setSize(260,30);
		ltitulo.setLocation(90,10);
		this.add(ltitulo);
		
		bLogoff = new JButton("Logoff");
		bLogoff.setSize(70,30);
		bLogoff.setLocation(358,10);
		this.add(bLogoff);
		
		
		pInfo = new JPanel();
		pInfo.setSize(420,100);
		pInfo.setLocation(10,40);
		pInfo.setBorder(BorderFactory.createTitledBorder("Informações"));
		pInfo.setLayout(null);
		this.add(pInfo);	
		
		lpedido = new JLabel("Pedido:");
		lpedido.setSize(90,30);
		lpedido.setLocation(20,20);
		pInfo.add(lpedido);
		
		tpedido = new JTextField();
		tpedido.setSize(140,20);
		tpedido.setLocation(90,25);
		pInfo.add(tpedido);
		
		bAcompanhar = new JButton("Acompanhar");
		bAcompanhar.setSize(120,30);
		bAcompanhar.setLocation(260,20);
		pInfo.add(bAcompanhar);
		
		bNovoPedido = new JButton("Novo Pedido");
		bNovoPedido.setSize(120,30);
		bNovoPedido.setLocation(50,60);
		pInfo.add(bNovoPedido);
		
		bListarPedidos = new JButton("Listar Pedidos");
		bListarPedidos.setSize(120,30);
		bListarPedidos.setLocation(260,60);
		pInfo.add(bListarPedidos);
		
		pStatus = new JPanel();
		pStatus.setSize(420,200);
		pStatus.setLocation(10,160);
		pStatus.setLayout(null);
		this.add(pStatus);
		
		exibirTabelaPedidos();
		
		lid = new JLabel("Cliente: " + cliente.getNome() + " - ID: " + cliente.getId());
		lid.setSize(260,30);
		lid.setLocation(20,360);
		this.add(lid);
		
		this.setVisible(true);
		this.repaint();
		
		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bAcompanhar) {
					if (tpedido.getText().length() > 0){
						exibirTabelaMovimentacoes();
					} else {
						JOptionPane.showMessageDialog(null,"Informe um pedido para acompanhar");
					}
				}
				if (e.getSource() == bListarPedidos) {
					exibirTabelaPedidos();
				}
			
				if (e.getSource() == bNovoPedido) {
					new TelaCriarPedido(cliente);
				}
			
				if (e.getSource() == bLogoff) {
					fechar();
				} 
			}
		}
		
		Ouvinte ouv = new Ouvinte();
		bAcompanhar.addMouseListener(ouv);
		bNovoPedido.addMouseListener(ouv);
		bListarPedidos.addMouseListener(ouv);
		bLogoff.addMouseListener(ouv);
	}

	public void fechar(){
		this.dispose();
	}
	
	private JTable criarTabelaPedidos(List<Pedido> pedidos) {
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
	
	public void exibirTabelaPedidos(){
		tabstatus = criarTabelaPedidos(cliente.getPedidos());
		scroll = new JScrollPane(tabstatus);
		scroll.setSize(400, 160);
		scroll.setLocation(10, 20);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pStatus.setBorder(BorderFactory.createTitledBorder("Pedidos: " + cliente.getPedidos().size()));
		pStatus.removeAll();
		pStatus.add(scroll);	
	}
	
	public void exibirTabelaMovimentacoes(){
		tabstatus = criarTabelaMovimentacoes(tpedido.getText());
		scroll = new JScrollPane(tabstatus);
		scroll.setSize(400, 160);
		scroll.setLocation(10, 20);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pStatus.setBorder(BorderFactory.createTitledBorder("Pedidos: " + cliente.getPedidos().size()));
		pStatus.removeAll();
		pStatus.add(scroll);	
	}
	
	
	public JTable criarTabelaMovimentacoes(String id_pedido){
		JTable t = new JTable();
		List<String[]> lista = new ArrayList<>();
		String[] colunas = {"Origem","Destino","Status"};
		int id = Integer.parseInt(id_pedido);
		List<Pedido> pedidos = cliente.getPedidos();
		Pedido p;
		int x;
		for (x = 0 ; x < pedidos.size() ; x++){
			p = pedidos.get(x);
			if (p.getId() == id){
				p.atualizarMovimentacoes();
				List<Movimentacao> movs = p.getMovimentacoes();						
			
				String origem, destino, status;
				Movimentacao mov;
				int m;

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
					
					lista.add(new String[]{origem, destino, status});
				}

			}
		}
		
		if (lista.size() == 0) { JOptionPane.showMessageDialog(null,"Pedido não encontrado"); }
		DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]),colunas);
		t.setModel(model);
		return t;
	}
		
}
		