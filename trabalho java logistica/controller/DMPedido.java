package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

import model.*;

public class DMPedido extends DMConexao {

	public void incluir(Pedido p) {
		try {
	    	Statement statement = DMConexao.getConnection().createStatement();
	        String inserirSQL = "INSERT INTO pedidos(id_cliente, quant, valor, origem, destino, local, status) VALUES ('";
	        inserirSQL += p.getId_cliente() + "', '";
	        inserirSQL += p.getQuant() + "', 0,'";
	        inserirSQL += p.getOrigem() + "', '";
	        inserirSQL += p.getDestino() + "', 'Origem', 'Aguardando Recolhimento');";
	        statement.executeUpdate(inserirSQL);
	        statement.close();
	    	statement = DMConexao.getConnection().createStatement();
	    	String consultarSQL = "SELECT id_pedido from pedidos WHERE id_cliente = '" + p.getId_cliente() + "' ORDER BY id_pedido DESC LIMIT 1;";
	    	ResultSet id_incluido = statement.executeQuery(consultarSQL);
	    	if (id_incluido.next()) {JOptionPane.showMessageDialog(null,"Pedido incluido com sucesso. ID = " + id_incluido.getString("id_pedido"),"Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
	    	} else { JOptionPane.showMessageDialog(null,"Erro ao criar pedido!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE); }
	    	statement.close();
		}
	    catch (SQLException e){ System.out.println("Problemas com o SQL para inserir pedido!");}
		
	}
	
	public void excluir(Pedido p) {	}

	public void alterar(Pedido p) {
		try{
			Statement statement = DMConexao.getConnection().createStatement();
			String atualizarpedido = "UPDATE pedidos p SET ";
			atualizarpedido += "id_cliente = '" + p.getId_cliente() + "', ";
			atualizarpedido += "local = '" + p.getLocal() + "', ";
			atualizarpedido += "origem = '" + p.getOrigem() + "', ";
			atualizarpedido += "destino = '" + p.getDestino() + "', ";
			atualizarpedido += "status = '" + p.getStatus() + "' ";
			atualizarpedido += " WHERE p.id_pedido = " + p.getId() + ";";

			statement.executeUpdate(atualizarpedido);
	        statement.close();
	        System.out.println("Pedido alterado!");
		}
		catch (SQLException e){ System.out.println("Problemas com o SQL para alterar pedido!"); }

	}

	public List<Pedido> consultar(Object obj) {
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			Statement statement = DMConexao.getConnection().createStatement();
			String consultarSQL = "SELECT p.* FROM pedidos p " ;
            if (obj instanceof Cliente) {
            	Cliente c = (Cliente) obj;
            	consultarSQL += "WHERE id_cliente = " + c.getId();
            }
            if (obj instanceof Funcionario) {
            	Funcionario f = (Funcionario) obj;
            	consultarSQL += "WHERE local LIKE " + f.getCentro() + " OR local LIKE 'Origem'";
            }
            if (obj instanceof Movimentacao) {
            	Movimentacao m = (Movimentacao) obj;
            	consultarSQL += "LEFT JOIN mov_pedidos mp ON p.id_pedido = mp.id_pedido WHERE mp.id_mov = " + m.getId();
            }
            if (obj instanceof String){
            	String id = (String) obj;
            	consultarSQL += "WHERE id_pedido = " + id;
            }
            
            consultarSQL += " ORDER BY id_pedido ASC;";	
            //System.out.println(consultarSQL);
            ResultSet result = statement.executeQuery(consultarSQL);
            Pedido novo;
            while (result.next()) {
            	//System.out.println(result.getString("id_pedido"));
            	novo = new Pedido();
            	novo.setId(Integer.parseInt(result.getString("id_pedido")));
            	novo.setId_cliente(Integer.parseInt(result.getString("id_cliente")));
            	novo.setQuant(Integer.parseInt(result.getString("quant")));
            	novo.setOrigem(result.getString("origem"));
            	novo.setDestino(result.getString("destino"));
            	novo.setStatus(result.getString("status"));
        		novo.setLocal(result.getString("local"));
            	lista.add(novo);
            }
            statement.close();    
        }
        catch (SQLException e){ System.out.println("Problemas com o SQL para consultar pedido!"); }
        return lista;
	}

}
