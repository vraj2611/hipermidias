package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import model.*;

public class TelaCriarPedido extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JLabel ltitulo, lorigem, ldestino, lquant, lprod;
	private JTextField torigem, tdestino, tquant, tprod;
	private JButton bCadastrar, bProd;
	
	public TelaCriarPedido(Cliente c)
	{
		//configurações da janela
		this.setSize(270,260);
		this.setTitle("Criaçao de pedido");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Logistica/src/images/executar.gif");
		this.setIconImage(img);
		
		ltitulo = new JLabel("CRIAR PEDIDO");
		ltitulo.setSize(260,30);
		ltitulo.setLocation(90,10);
		this.add(ltitulo);
	
		lorigem = new JLabel("Origem:");
		lorigem.setSize(100,30);
		lorigem.setLocation(20,50);
		this.add(lorigem);
		
		torigem = new JTextField();
		torigem.setSize(160,20);
		torigem.setLocation(90,55);
		this.add(torigem);

		ldestino = new JLabel("Destino:");
		ldestino.setSize(100,30);
		ldestino.setLocation(20,80);
		this.add(ldestino);
		
		tdestino = new JTextField();
		tdestino.setSize(160,20);
		tdestino.setLocation(90,85);
		this.add(tdestino);

		lquant = new JLabel("Quant:");
		lquant.setSize(60,30);
		lquant.setLocation(20,110);
		this.add(lquant);
		
		tquant = new JTextField();
		tquant.setSize(40,20);
		tquant.setLocation(60,115);
		this.add(tquant);
		
		lprod = new JLabel("Prod:");
		lprod.setSize(100,30);
		lprod.setLocation(110,110);
		this.add(lprod);
		
		tprod = new JTextField();
		tprod.setSize(40,20);
		tprod.setLocation(150,115);
		this.add(tprod);		
		
		bProd = new JButton("?");
		bProd.setSize(50,30);
		bProd.setLocation(200,110);
		this.add(bProd);		
		
		bCadastrar = new JButton("Cadastrar");
		bCadastrar.setSize(100,30);
		bCadastrar.setLocation(70,150);
		this.add(bCadastrar);

		this.setVisible(true);
		this.repaint();

		class Ouvinte extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bCadastrar) {
					new Pedido(c.getId(), Integer.parseInt(tquant.getText()), torigem.getText(), tdestino.getText());
					fechar();
				}
				
				if (e.getSource() == bProd) {
					String str = "Lista de Produtos\n";
					str += "1 = Local Expresso\n";
					str += "2 = Local Padrão\n";
					str += "3 = Padrão\n";
					str += "4 = Grandes Cargas\n";
					JOptionPane.showMessageDialog(null, str);
				}
				
			}
		}
		Ouvinte ouv = new Ouvinte();
		bCadastrar.addMouseListener(ouv);
		bProd.addMouseListener(ouv);

	}
	
	public void fechar(){ this.dispose();}

}
