package view;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.*;

public class TelaCriarMovimentacao extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lpedido, lid, ldestino, lmotorista;
	private JTextField tpedido, tdestino, tmotorista;
	private JButton bCriar, bIncluir, bExcluir, bSair, bListarPedidos;
	private JPanel pInfo, pTodos, pSelecao;
	//private JTextArea ttodos, tselecao;
	private JScrollPane scrollt, scrolls;
	private JTable tselecao, ttodos;
	
	public TelaCriarMovimentacao(Funcionario f){
		
		this.setSize(450,600);
		this.setTitle("Criação de Movimentação");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("CRIAR MOVIMENTACÕES");
		ltitulo.setSize(260,30);
		ltitulo.setLocation(90,10);
		this.add(ltitulo);
		
		bSair = new JButton("Cancelar");
		bSair.setSize(100,30);
		bSair.setLocation(328,10);
		this.add(bSair);
			
		pInfo = new JPanel();
		pInfo.setSize(420,130);
		pInfo.setLocation(10,40);
		pInfo.setBorder(BorderFactory.createTitledBorder("Informações"));
		pInfo.setLayout(null);
		this.add(pInfo);	
		
		ldestino = new JLabel("Destino:");
		ldestino.setSize(90,30);
		ldestino.setLocation(20,20);
		pInfo.add(ldestino);
		
		tdestino = new JTextField();
		tdestino.setSize(140,20);
		tdestino.setLocation(90,25);
		pInfo.add(tdestino);

		lmotorista = new JLabel("ID Motorista:");
		lmotorista.setSize(100,30);
		lmotorista.setLocation(260,20);
		pInfo.add(lmotorista);
		
		tmotorista = new JTextField();
		tmotorista.setSize(40,20);
		tmotorista.setLocation(340,25);
		pInfo.add(tmotorista);
		
		lpedido = new JLabel("Pedido:");
		lpedido.setSize(90,30);
		lpedido.setLocation(20,50);
		pInfo.add(lpedido);
		
		tpedido = new JTextField();
		tpedido.setSize(140,20);
		tpedido.setLocation(90,55);
		pInfo.add(tpedido);
		
		bCriar = new JButton("Criar");
		bCriar.setSize(120,30);
		bCriar.setLocation(260,55);
		pInfo.add(bCriar);
		
		bIncluir = new JButton("Incluir");
		bIncluir.setSize(90,30);
		bIncluir.setLocation(20,90);
		pInfo.add(bIncluir);
		
		bExcluir = new JButton("Excluir");
		bExcluir.setSize(90,30);
		bExcluir.setLocation(140,90);
		pInfo.add(bExcluir);
		
		bListarPedidos = new JButton("Listar Pedidos");
		bListarPedidos.setSize(120,30);
		bListarPedidos.setLocation(260,90);
		pInfo.add(bListarPedidos);
		
		pSelecao = new JPanel();
		pSelecao.setSize(420,150);
		pSelecao.setLocation(10,180);
		pSelecao.setBorder(BorderFactory.createTitledBorder("Pedidos selecionados: 0"));
		pSelecao.setLayout(null);
		this.add(pSelecao);

		tselecao = new JTable();
		tselecao = f.listarTabelaSelecionados();
		scrolls = new JScrollPane(tselecao);
		scrolls.setSize(400, 110);
		scrolls.setLocation(10, 20);
		scrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		new JScrollPane();
		//tselecao.setEditable(true);
		//tselecao = f.listarTabelaSelecionados();
		pSelecao.add(scrolls);
		
		pTodos = new JPanel();
		pTodos.setSize(420,200);
		pTodos.setLocation(10,340);
		pTodos.setBorder(BorderFactory.createTitledBorder("Pedidos disponíveis: " + f.getPedidos().size()));
		pTodos.setLayout(null);
		this.add(pTodos);
		
		ttodos = new JTable();
		ttodos = f.listarTabelaPedidos();
		scrollt = new JScrollPane(ttodos);
		scrollt.setSize(400, 160);
		scrollt.setLocation(10, 20);
		scrollt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//ttodos.setEditable(true);
		//ttodos.setText(f.listarPedidos());
		pTodos.add(scrollt);
		
		lid = new JLabel(f.getCargo() + ": " + f.getNome() + " - ID: " + f.getId());
		lid.setSize(260,30);
		lid.setLocation(20,540);
		this.add(lid);
		
		this.setVisible(true);
		this.repaint();
		
		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bCriar) {
					new Movimentacao(String.valueOf(f.getCentro()), tdestino.getText(), Integer.parseInt(tmotorista.getText()), f.getSelecionados());
				}
				
				if (e.getSource() == bListarPedidos) {
					ttodos = f.listarTabelaPedidos();
					atualizarTela();
				}
			
				if (e.getSource() == bIncluir) {
					f.incluirPedidoNovaMov(tpedido.getText());
					tselecao = f.listarTabelaSelecionados();
					tselecao.repaint();
					pSelecao.setBorder(BorderFactory.createTitledBorder("Pedidos selecionados: " + f.getSelecionados().size()));
					pSelecao.remove(scrolls);
					atualizarTela();
				}
				
				if (e.getSource() == bExcluir) {
					f.excluirPedidoNovaMov(tpedido.getText());
					tselecao = f.listarTabelaSelecionados();
					pSelecao.setBorder(BorderFactory.createTitledBorder("Pedidos selecionados: " + f.getSelecionados().size()));
					atualizarTela();
				}
			
				if (e.getSource() == bSair) {
					fechar();
				} 
			}
		}
		
		Ouvinte ouv = new Ouvinte();
		bCriar.addMouseListener(ouv);
		bIncluir.addMouseListener(ouv);
		bExcluir.addMouseListener(ouv);
		bListarPedidos.addMouseListener(ouv);
		bSair.addMouseListener(ouv);
	}

	public void fechar(){ this.dispose();}
	
	public void atualizarTela(){
		scrolls.repaint();
		this.repaint();
	}
}