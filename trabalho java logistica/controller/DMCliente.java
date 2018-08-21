package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import model.*;

public class DMCliente extends DMConexao implements ClienteInterface {
	
	public int incluir(Cliente c) {
		int id = 0;
		try {
	    	Statement statement = DMConexao.getConnection().createStatement();
	        String inserirSQL = "INSERT INTO clientes(nome, telefone, cpf_cnpj) VALUES ('";
	        inserirSQL += c.getNome() + "', '" + c.getTelefone() + "', '" + c.getCpf_cnpj() + "');";
	        statement.executeUpdate(inserirSQL);
	        statement.close();
	
	    	statement = DMConexao.getConnection().createStatement();
	    	String consultarSQL = "SELECT id from clientes WHERE nome LIKE '" + c.getNome() + "';";
	    	ResultSet id_incluido = statement.executeQuery(consultarSQL);
	    	if (id_incluido.next()) {
	    		id = Integer.parseInt(id_incluido.getString("id"));
	    		JOptionPane.showMessageDialog(null,c.getNome() + " incluido com sucesso. ID = " + id,"Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
	    	} else { JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE); }
	    	statement.close();
	    }
	    catch (SQLException e){ System.out.println("Problemas com o SQL de consultar cliente!"); }
		return id;
	}

	public Cliente consultar(String id) {
		Cliente c = new Cliente();
		try {
        	Statement statement = DMConexao.getConnection().createStatement();
            String consultarSQL = "SELECT * FROM clientes WHERE id = '"+id+"'";
            ResultSet result = statement.executeQuery(consultarSQL);
            id = "0";
            if (result.next()) {
            	c.setId(Integer.parseInt(result.getString("id")));
            	c.setNome(result.getString("nome"));
    			c.setTelefone(result.getString("telefone"));
    			c.setCpf_cnpj(result.getString("cpf_cnpj"));
                System.out.println("Entrou "+ c.getNome() + " - ID: " + id);
                result.close();
            }
            else {
            	c = null;
            	System.out.println( "Cliente não encontrado!\n" );
            }
            statement.close();
            
        }
        catch (SQLException e){ System.out.println("Problemas com o SQL para consultar cliente!"); }
		return c;        
	}

	public void excluir(Cliente c) {}
	public void alterar(Cliente c) {}


}