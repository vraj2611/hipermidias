package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class TelaAcompanharPedido extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lpedido, lid;
	private JTextField tpedido;
	private JButton bAcompanhar, bNovoPedido, bListarPedidos, bLogoff;
	private JPanel pInfo, pStatus;
	private JTextArea tstatus;
	private JScrollPane scroll;
	
	public TelaAcompanharPedido(Cliente c){
		
		this.setSize(450,420);
		this.setTitle("Acompanhamento de Pedidos");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
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
		pStatus.setBorder(BorderFactory.createTitledBorder("Pedidos: " + c.getQuantPedidos()));
		pStatus.setLayout(null);
		this.add(pStatus);
		
		tstatus = new JTextArea();
		scroll = new JScrollPane(tstatus);
		scroll.setSize(400, 160);
		scroll.setLocation(10, 20);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tstatus.setEditable(true);
		tstatus.setText(c.listarPedidos());
		pStatus.add(scroll);
		
		lid = new JLabel("Cliente: " + c.getNome() + " - ID: " + c.getId());
		lid.setSize(260,30);
		lid.setLocation(20,360);
		this.add(lid);
		
		this.setVisible(true);
		this.repaint();
		
		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bAcompanhar) {
					tstatus.setText(c.acompanharPedido(tpedido.getText()));
					pStatus.setBorder(BorderFactory.createTitledBorder("Movimentações do Pedido " + tpedido.getText()));
				}
				
				if (e.getSource() == bListarPedidos) {
					tstatus.setText(c.listarPedidos());
					pStatus.setBorder(BorderFactory.createTitledBorder("Pedidos: " + c.getQuantPedidos()));
				}
			
				if (e.getSource() == bNovoPedido) {
					new TelaCriarPedido(c);
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

	public void fechar(){ this.dispose();}	
}