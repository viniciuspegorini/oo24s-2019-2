package br.edu.utfpr.pb.aula1.dao;

import br.edu.utfpr.pb.aula1.db.DatabaseConnection;
import br.edu.utfpr.pb.aula1.model.Categoria;
import br.edu.utfpr.pb.aula1.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private Connection conn;
    
    public ProdutoDao(){
        conn = DatabaseConnection.getInstance().getConnection();
    }
    
    public void insert(Produto produto){
        try {
            String sql = "INSERT INTO produto(nome,descricao,valor,"
                    + "categoria_id) VALUES(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getValor());
            pstmt.setLong(4, produto.getCategoria().getId());
            
            if (pstmt.executeUpdate() > 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                produto.setId(Long.valueOf(rs.getInt(1)));
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(Produto produto){
        try {
            String sql = "UPDATE produto SET nome=?,descricao=?,"
                    + "valor=?,idcategoria=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getValor());
            pstmt.setLong(4, produto.getCategoria().getId());
            pstmt.setLong(4, produto.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Long id){
        try {
            String sql = "DELETE FROM produto WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Produto> getAll(){
        List<Produto> lista = new ArrayList<>();
        try {
            Produto produto;
            String sql = "SELECT id, nome, descricao, valor, idcategoria"
                    + " FROM produto";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Categoria categoria;
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getDouble("valor"));
                categoria = new Categoria();
                categoria.setId(rs.getLong("idcategoria"));
                produto.setCategoria(categoria);
                lista.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Categoria getById(Long id){
        Categoria categoria = new Categoria();
        try {
            String sql = "SELECT id, descricao FROM categoria WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                categoria.setId(rs.getLong("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }
}





