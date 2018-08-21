package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import model.*;

public class TelaCadastrarUsuario extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lnome, ltelefone, lcpfcnpj;
	private JTextField tnome, ttelefone, tcpfcnpj;
	private JButton bCadastrar;
	
	public TelaCadastrarUsuario() {

		this.setSize(270,260);
		this.setTitle("Cadastro de Usuario");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("CADASTRO DE USUARIO");
		ltitulo.setSize(260,30);
		ltitulo.setLocation(60,10);
		this.add(ltitulo);
	
		lnome = new JLabel("Nome:");
		lnome.setSize(100,30);
		lnome.setLocation(20,50);
		this.add(lnome);
		
		tnome = new JTextField();
		tnome.setSize(140,20);
		tnome.setLocation(90,55);
		this.add(tnome);

		ltelefone = new JLabel("Telefone:");
		ltelefone.setSize(100,30);
		ltelefone.setLocation(20,80);
		this.add(ltelefone);
		
		ttelefone = new JTextField();
		ttelefone.setSize(140,20);
		ttelefone.setLocation(90,85);
		this.add(ttelefone);

		lcpfcnpj = new JLabel("CPF/CNPJ:");
		lcpfcnpj.setSize(100,30);
		lcpfcnpj.setLocation(20,110);
		this.add(lcpfcnpj);
		
		tcpfcnpj = new JTextField();
		tcpfcnpj.setSize(140,20);
		tcpfcnpj.setLocation(90,115);
		this.add(tcpfcnpj);
		
		bCadastrar = new JButton("Cadastrar");
		bCadastrar.setSize(100,30);
		bCadastrar.setLocation(70,150);
		this.add(bCadastrar);

		this.setVisible(true);
		this.repaint();

		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bCadastrar) {
					new Cliente(tnome.getText(), ttelefone.getText(), tcpfcnpj.getText());
					fechar();
				}
				
			}
		}
		Ouvinte ouv = new Ouvinte();
		bCadastrar.addMouseListener(ouv);
		
		
	}
	
	public void fechar(){ this.dispose(); }
}
		