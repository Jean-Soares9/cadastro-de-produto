package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/cadastro?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "";
	

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			return con;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	/**
	 * Inserir produto.
	 *
	 * @param produto the produto
	 */
	public void inserirProduto(JavaBeans produto) {
		String create = "insert into produto (nome,qtde,valor_unit,data_cad)values (?,?,?,?)";
		
		try {
			
			Connection con = conectar();
			
			
			PreparedStatement pst = con.prepareStatement(create);
			
			
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQtde());
			pst.setFloat(3, produto.getValor_unit());
			pst.setDate(4, java.sql.Date.valueOf(produto.getData_cad()));
			
			
			pst.executeUpdate();
			
			
			con.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Listar produtos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarProdutos() {
		
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		
		String read = "select * from produto order by nome";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			
			while (rs.next()) {
				
				
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				int qtde = rs.getInt(3);
				float valorunit = rs.getFloat(4);
				LocalDate data_cadastro = rs.getDate("data_cad").toLocalDate();
				
				
				produtos.add(new JavaBeans(id,nome,qtde,valorunit,data_cadastro));
				
			}
			
			con.close();
			return produtos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
	/**
	 * Selecionar produto.
	 *
	 * @param produto the produto
	 */
	public void selecionarProduto(JavaBeans produto) {
		String read2 = "select * from produto where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, produto.getId());
			ResultSet rs = pst.executeQuery();
			
			
			while (rs.next()) {
				
				produto.setId(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setQtde(rs.getInt(3));
				produto.setValor_unit(rs.getFloat(4));
				produto.setData_cad(rs.getDate(5).toLocalDate());
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	/**
	 * Alterar produto.
	 *
	 * @param produto the produto
	 */
	public void alterarProduto(JavaBeans produto) {
		String update = "update produto set nome=?,qtde=?,valor_unit=?,data_cad=? where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, produto.getNome());
			pst.setInt(2, produto.getQtde());
			pst.setFloat(3, produto.getValor_unit());
			pst.setDate(4, java.sql.Date.valueOf(produto.getData_cad()));
			pst.setInt(5, produto.getId());
			
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Deletar produto.
	 *
	 * @param produto the produto
	 */
	public void deletarProduto(JavaBeans produto) {
		String delete = "delete  from produto where id=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, produto.getId());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
