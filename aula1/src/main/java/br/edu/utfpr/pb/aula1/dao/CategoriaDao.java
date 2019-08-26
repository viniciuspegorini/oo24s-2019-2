package br.edu.utfpr.pb.aula1.dao;

import br.edu.utfpr.pb.aula1.db.DatabaseConnection;
import br.edu.utfpr.pb.aula1.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    private Connection conn;
    
    public CategoriaDao(){
        conn = DatabaseConnection.getInstance().getConnection();
    }
    
    public void insert(Categoria categoria){
        try {
            String sql = "INSERT INTO categoria(descricao) "
                    + "VALUES('" + categoria.getDescricao()+ "');";
            System.out.println("SQL = " + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            //pstmt.setString(1, categoria.getDescricao());
            
            if (pstmt.executeUpdate() > 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                categoria.setId(Long.valueOf(rs.getInt(1)));
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(Categoria categoria){
        try {
            String sql = "UPDATE categoria SET descricao=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoria.getDescricao());
            pstmt.setLong(2, categoria.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Long id){
        try {
            String sql = "DELETE FROM categoria WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Categoria> getAll(){
        List<Categoria> lista = new ArrayList<>();
        try {
            Categoria categoria;
            String sql = "SELECT id, descricao FROM categoria";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getLong("id"));
                categoria.setDescricao(rs.getString("descricao"));
                lista.add(categoria);
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





