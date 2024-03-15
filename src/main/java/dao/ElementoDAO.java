package dao;

import java.sql.*;

import model.Elemento;

public class ElementoDAO {
	
	private Connection conn;
	
	public ElementoDAO() {
		conn = null;
	}
	
	public boolean connect() {
		
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydb = "teste";
		int port = 5432;
		
		String url = "jdbc:postgresql://"+serverName+":"+port+""+"/"+ mydb;
		String username = "ti2cc";
		String password = "root";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			status = (conn == null);
		}catch(ClassNotFoundException e) {
			System.err.println("Conexao nao efetuada com o PostgreSQL" + e.getMessage());
		}catch(SQLException e) {
			System.err.println("Conexao nao efetuada" + e.getMessage());
		}
		
		return status;
	}
	
	
	public boolean close() {
		boolean status = false;
		
		try {
			conn.close();
			status = true;
		}catch(SQLException e) {
			System.err.println("Erro ao fechar conexao" + e.getMessage());
		}
		
		return status;
	}
	
	/*
	 CRUD
	*/
	public boolean createElemento(Elemento elemento) {
		
		boolean status = false;
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO elementos(\"numeroAtomico\", nome, simbolo, familia) "
					+ "VALUES ("+ elemento.getNumeroAtomico() +", "
							+ "'"+elemento.getNome()+"', "
							+ "'"+elemento.getSimbolo()+"', "
							+ "'"+elemento.getFamilia()+"');");
			
			st.close();
			status = true;
			
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return status;
	}

	public Elemento[] readElementos() {
		
		Elemento[] elementos = null;
		
		try {
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM elementos;");
			
			if(rs.next()) {
				rs.last();
				elementos = new Elemento[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0; rs.next(); i++) {
					elementos[i] = new Elemento(rs.getInt("numeroAtomico"),
							rs.getString("nome"), 
							rs.getString("simbolo"), 
							rs.getString("familia"));
				}
			}
			
			st.close();
			
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return elementos;
	}
	
	public Elemento readElemento(int numeroAtomico) {
		
		Elemento elemento = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM elementos WHERE \"numeroAtomico\" = ?");
            ps.setInt(1, numeroAtomico);
            ResultSet rs = ps.executeQuery();
            
			if(rs.next()) {
				
				elemento = new Elemento(rs.getInt("numeroAtomico"),
						rs.getString("nome"), 
						rs.getString("simbolo"), 
						rs.getString("familia"));
			}
             
            ps.close();
			
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return elemento;
	}
	
	public boolean updateElemento(Elemento elemento) {
		
		boolean status = false;
		
		try {
            Statement st = conn.createStatement();
            String sql = "UPDATE elementos SET "
					+ "nome = '"+elemento.getNome()+"', "
					+ "simbolo = '"+elemento.getSimbolo()+"', "
					+ "familia = '"+elemento.getFamilia()+"' "
                    + "WHERE \"numeroAtomico\" = "+ elemento.getNumeroAtomico();
            
            st.executeUpdate(sql);
            st.close();
            status = true;
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	

	
	public boolean deleteElemento(int numeroAtomico) {
		
		boolean status = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM elementos WHERE \"numeroAtomico\" = ?");
            ps.setInt(1, numeroAtomico);
            ps.executeUpdate();
            ps.close();
            
            status = true;
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
		
		return status;
	}
}
