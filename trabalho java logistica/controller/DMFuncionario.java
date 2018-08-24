package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.*;

public class DMFuncionario extends DMConexao {

	public void incluir(Funcionario f) {}

	public Funcionario consultar(String id) {

		Funcionario f = new Funcionario();
        try {
        	Statement statement = DMConexao.getConnection().createStatement();
        	Date agora = new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
        	String inserirhora = "UPDATE funcionarios SET hr_acesso = '" + sdf.format(agora) + "' WHERE id = " + id + ";"; 
        	System.out.println(inserirhora);
        	statement.executeUpdate(inserirhora);
        	
        	String consultarSQL = "SELECT f.*, c.descricao FROM funcionarios f JOIN cargos_func c ON f.id_cargo = c.id WHERE f.id = '" + id + "'";
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next()) {
            	f.setId(Integer.parseInt(result.getString("id")));
            	f.setNome(result.getString("nome"));
            	f.setId_cargo(Integer.parseInt(result.getString("id_cargo")));
            	f.setCargo(result.getString("descricao"));
            	f.setCentro(Integer.parseInt(result.getString("centro")));
                System.out.println("Entrou "+ result.getString("descricao") + " " + result.getString("nome") + "- ID: "+ result.getString("id"));
                result.close();
                
            }
            else {
            	f = null;
            	System.out.println( "Funcionario Não encontrado!\n" );
            }
            statement.close();
            
        }
        catch (SQLException e){ System.out.println("Problemas com o SQL para consultar funcionario!"); }
        return f;
	}


}
