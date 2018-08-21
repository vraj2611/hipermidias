package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.*;

public class DMMovimentacao extends DMConexao {


	public void incluir(Movimentacao mov) {
		try {
			Statement statement = DMConexao.getConnection().createStatement();
			String inserirmov = "INSERT INTO movimentacoes (origem, destino, id_motorista, status) VALUES ('";
			inserirmov += mov.getOrigem() + "', '";
			inserirmov += mov.getDestino() + "', ";
			inserirmov += mov.getId_motorista() + ", 0);";
			System.out.println(inserirmov);
			statement.executeUpdate(inserirmov);
	        
			String idmov = "";
			String consultaidmov = "SELECT id from movimentacoes ORDER BY id DESC LIMIT 1;"; 
			ResultSet result = statement.executeQuery(consultaidmov);
			while (result.next()) {
				idmov = result.getString("id");
			}
			
			int x;
			List<Pedido> lista_pedidos = mov.getPedidos();
			Pedido p;
			for(x=0; x < lista_pedidos.size() ; x++){
				p = lista_pedidos.get(x);
				inserirmov = "INSERT INTO mov_pedidos (id_mov, id_pedido) VALUES (" + idmov + ", " + p.getId() + ");";
				System.out.println(inserirmov);
				statement.executeUpdate(inserirmov);
			}
			
			statement.close();
	        System.out.println("Movimentacao incluida com sucesso!");
		}
		catch (SQLException e){ System.out.println("Problemas com o SQL para inserir movimentacao!"); }	
	}

	public List<Movimentacao> consultar(Object obj) {
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		try {
			Statement statement = DMConexao.getConnection().createStatement();
			String consultarSQL = "SELECT * FROM movimentacoes m LEFT JOIN funcionarios f ON m.id_motorista = f.id ";
            if (obj instanceof Pedido) {
            	Pedido p = (Pedido) obj;
            	consultarSQL += "LEFT JOIN mov_pedidos mp ON m.id = mp.id_mov ";
            	consultarSQL += "WHERE mp.id_pedido = " + p.getId();
            	consultarSQL += " ORDER BY m.id ASC;";
            }
            if (obj instanceof Funcionario) {
            	Funcionario f = (Funcionario) obj;
            	consultarSQL += "WHERE origem LIKE 'Origem' AND destino LIKE '" + f.getCentro() + "' ";
            	consultarSQL += "OR origem LIKE '" + f.getCentro() + "' ";
            	consultarSQL += " ORDER BY m.id ASC;";
            }
			            	
            System.out.println(consultarSQL);
            ResultSet result = statement.executeQuery(consultarSQL);
            Movimentacao novo;
            while (result.next()) {
            	System.out.println(result.getString("id"));
            	novo = new Movimentacao();
            	novo.setId(Integer.parseInt(result.getString("id")));
            	novo.setOrigem(result.getString("origem"));
            	novo.setDestino(result.getString("destino"));
            	novo.setStatus(Integer.parseInt(result.getString("status")));
            	novo.setId_motorista(Integer.parseInt(result.getString("id_motorista")));
            	novo.setNome_motorista(result.getString("nome"));
            	lista.add(novo);
            }
            statement.close();    
        }
        catch (SQLException e){ System.out.println("Problemas com o SQL para consultar movimentacao!"); }
        return lista;
	}


	public void excluir(Object obj) {	}


	public void alterar(Movimentacao mov) {
		try {
			Statement statement = DMConexao.getConnection().createStatement();
			String atualizarmov = "UPDATE movimentacoes m SET ";
			atualizarmov += "status = '" + mov.getStatus() + "', ";
			atualizarmov += "id_motorista = '" + mov.getId_motorista() + "', ";
			atualizarmov += "origem = '" + mov.getOrigem() + "', ";
			atualizarmov += "destino = '" + mov.getDestino() + "' ";
			atualizarmov += "WHERE m.id = " + mov.getId() + ";";
			
			statement.executeUpdate(atualizarmov);
	        statement.close();
	        System.out.println("Movimentacao alterada!");
		}
		catch (SQLException e){ System.out.println("Problemas com o SQL para consultar movimentacao!"); }
	}

}
