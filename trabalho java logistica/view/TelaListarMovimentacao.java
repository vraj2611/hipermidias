package view;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.*;


public class TelaListarMovimentacao extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lmov, lid;
	private JTextField tmov;
	private JButton bConcluir, bNovaMov, bListarMovs, bLogoff;
	private JPanel pInfo, pStatus;
	private JTable tstatus;
	private JScrollPane scroll;
	private Funcionario funcionario;
	
	public TelaListarMovimentacao(Funcionario f){
		
		this.setSize(450,420);
		this.setTitle("Lista de Movimentações");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.funcionario = f;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("ACOMPANHAMENTO DE MOVIMENTAÇÕES");
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
		
		lmov = new JLabel("Mov.:");
		lmov.setSize(90,30);
		lmov.setLocation(20,20);
		pInfo.add(lmov);
		
		tmov = new JTextField();
		tmov.setSize(140,20);
		tmov.setLocation(90,25);
		pInfo.add(tmov);
		
		bConcluir = new JButton("Concluir");
		bConcluir.setSize(120,30);
		bConcluir.setLocation(260,20);
		pInfo.add(bConcluir);
		
		bNovaMov = new JButton("Nova Mov.");
		bNovaMov.setSize(120,30);
		bNovaMov.setLocation(50,60);
		if (f.getId_cargo() == 2) {pInfo.add(bNovaMov);}
		
		bListarMovs = new JButton("Listar Movs.");
		bListarMovs.setSize(120,30);
		bListarMovs.setLocation(260,60);
		pInfo.add(bListarMovs);
		
		pStatus = new JPanel();
		pStatus.setSize(420,200);
		pStatus.setLocation(10,160);
		pStatus.setBorder(BorderFactory.createTitledBorder("Status"));
		pStatus.setLayout(null);
		this.add(pStatus);
		
		exibirTabelaMovimentacoes();
		
		lid = new JLabel(f.getCargo() + ": " + f.getNome() + " - ID: " + f.getId());
		lid.setSize(260,30);
		lid.setLocation(20,360);
		this.add(lid);
		
		this.setVisible(true);
		this.repaint();
		
		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bConcluir) {
					f.ConcluirMov(tmov.getText());
				}
				
				if (e.getSource() == bListarMovs) {
					exibirTabelaMovimentacoes();
				}
			
				if (e.getSource() == bNovaMov) {
					new TelaCriarMovimentacao(f);
				}
				
				if (e.getSource() == bLogoff) {
					fechar();
				}
			}
		}
		
		Ouvinte ouv = new Ouvinte();
		bConcluir.addMouseListener(ouv);
		bNovaMov.addMouseListener(ouv);
		bListarMovs.addMouseListener(ouv);
		bLogoff.addMouseListener(ouv);
	}

	public void fechar(){
		this.dispose();
	}

	private JTable criarTabela(List<Movimentacao> movs) {
		JTable t = new JTable();
		String[] colunas = {"ID","Motorista","Origem","Destino","Status"};
		List<String[]> lista = new ArrayList<>();
		
		int x;
		Movimentacao m;
		String descr_status, end_destino, end_origem;
		for (x=0; x < movs.size() ; x++){
			m = movs.get(x);
			
			if (m.getStatus() == 0) {descr_status = "Em transito";} else {descr_status = "Concluido";}
			if (m.getDestino().compareTo("Destino") != 0) {end_destino = "Centro " + m.getDestino();} else {end_destino = m.getDestino();}
			if (m.getOrigem().compareTo("Origem") != 0) {end_origem = "Centro " + m.getOrigem();} else {end_origem = m.getOrigem();}
			
			lista.add(new String[]{
				String.valueOf(m.getId()),
				String.valueOf(m.getNome_motorista()),
				end_origem,
				end_destino,
				descr_status
			});
		}	
		DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]),colunas);
		t.setModel(model);
		return t;
	}

	private void exibirTabelaMovimentacoes(){
		tstatus = criarTabela(funcionario.getMovs());
		scroll = new JScrollPane(tstatus);
		scroll.setSize(400, 160);
		scroll.setLocation(10, 20);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pStatus.setBorder(BorderFactory.createTitledBorder("Movimentações: " + tstatus.getRowCount()));
		pStatus.removeAll();
		pStatus.add(scroll);
	}

}