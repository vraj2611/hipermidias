package view;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.*;


public class TelaListarMovimentacao extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lmov, lid;
	private JTextField tmov;
	private JButton bConcluir, bNovaMov, bListarMovs, bLogoff;
	private JPanel pInfo, pStatus;
	private JTextArea tstatus;
	private JScrollPane scroll;
	
	public TelaListarMovimentacao(Funcionario f){
		
		this.setSize(450,420);
		this.setTitle("Lista de Movimentações");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
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
		
		//Painel Cliente 
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
		
		tstatus = new JTextArea();
		scroll = new JScrollPane(tstatus);
		scroll.setSize(400, 160);
		scroll.setLocation(10, 20);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tstatus.setEditable(true);
		tstatus.setText(f.listarMovs());
		pStatus.add(scroll);
		
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
					tstatus.setText(f.listarMovs());
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

	public void fechar(){ this.dispose();}
}