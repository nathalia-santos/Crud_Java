package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	//criar nessa classe os objetos
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			//criar a conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();

			//criar PreparedStatement para exceutar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar valores
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
		
			//executar query
			pstm.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			// fechar conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
