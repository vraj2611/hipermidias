package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.*;
import model.*;

public class TelaIdentificarUsuario extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lidcliente, lidfuncionario;
	private JTextField tidcliente, tidfuncionario;
	private JButton bAcessarCliente, bNovoCliente, bAcessarFunc;
	private JPanel pCliente, pFuncionario;
	
	public TelaIdentificarUsuario(){

		this.setSize(290,310);
		this.setTitle("Logistica - Identificar Usuario");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("IDENTIFICACAO DO USUARIO");
		ltitulo.setSize(260,30);
		ltitulo.setLocation(60,10);
		this.add(ltitulo);
		
		//Painel Cliente 
		pCliente = new JPanel();
		pCliente.setSize(260,100);
		pCliente.setLocation(10,40);
		pCliente.setBorder(BorderFactory.createTitledBorder("Área do Cliente"));
		pCliente.setLayout(null);
		this.add(pCliente);	
		
		lidcliente = new JLabel("ID Cliente:");
		lidcliente.setSize(100,30);
		lidcliente.setLocation(20,20);
		pCliente.add(lidcliente);
		
		tidcliente = new JTextField();
		tidcliente.setSize(140,20);
		tidcliente.setLocation(90,25);
		pCliente.add(tidcliente);
		
		bAcessarCliente = new JButton("Acessar");
		bAcessarCliente.setSize(90,30);
		bAcessarCliente.setLocation(20,60);
		pCliente.add(bAcessarCliente);
		
		bNovoCliente = new JButton("Novo Cliente");
		bNovoCliente.setSize(120,30);
		bNovoCliente.setLocation(120,60);
		pCliente.add(bNovoCliente);
		
		pFuncionario = new JPanel();
		pFuncionario.setSize(260,100);
		pFuncionario.setLocation(10,160);
		pFuncionario.setBorder(BorderFactory.createTitledBorder("Área do Funcionario"));
		pFuncionario.setLayout(null);
		this.add(pFuncionario);
		
		lidfuncionario = new JLabel("ID Funcionario:");
		lidfuncionario.setSize(260,30);
		lidfuncionario.setLocation(20,20);
		pFuncionario.add(lidfuncionario);
		
		tidfuncionario = new JTextField();
		tidfuncionario.setSize(130,20);
		tidfuncionario.setLocation(110,25);
		pFuncionario.add(tidfuncionario);
		
		bAcessarFunc = new JButton("Acessar");
		bAcessarFunc.setSize(90,30);
		bAcessarFunc.setLocation(20,60);
		pFuncionario.add(bAcessarFunc);
		
		this.setVisible(true);
		this.repaint();
		
		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bAcessarCliente) { 
					Cliente c = new Cliente(tidcliente.getText());
					if (c.getId() > 0) {
						new TelaAcompanharPedido(c);	
					} else {
						JOptionPane.showMessageDialog(null, "Cliente ID "+ tidcliente.getText() +" não cadastrado.");
					}	
				}
				
				if (e.getSource() == bNovoCliente) {
					new TelaCadastrarUsuario();
				}
			
				if (e.getSource() == bAcessarFunc) {
					Funcionario f = new Funcionario(tidfuncionario.getText());
					if (f.getId() > 0){
						new TelaListarMovimentacao(f);
					} else {
						JOptionPane.showMessageDialog(null, "Funcionario ID "+ tidfuncionario.getText() +" não cadastrado.");
					}		
				}
			}
		}
		Ouvinte ouv = new Ouvinte();
		bAcessarCliente.addMouseListener(ouv);
		bNovoCliente.addMouseListener(ouv);
		bAcessarFunc.addMouseListener(ouv);
	}

	public static void main(String[] args) {
		new TelaIdentificarUsuario();
		DMConexao.conectaDataBase("db_log", "root", "usbw");
	}
}



