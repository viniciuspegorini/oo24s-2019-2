package br.edu.utfpr.pb.aula1.main;

import br.edu.utfpr.pb.aula1.dao.CategoriaDao;
import br.edu.utfpr.pb.aula1.dao.ProdutoDao;
import br.edu.utfpr.pb.aula1.model.Categoria;
import br.edu.utfpr.pb.aula1.model.Produto;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        Categoria c1 = new Categoria();
        
        c1.setDescricao("Informática");
        
        CategoriaDao categoriaDao = new CategoriaDao();
        categoriaDao.insert(c1);
        
        System.out.println("Categoria ID=" + c1.getId() + 
                " inserida com sucesso!");
    
    
    
        Produto p1 = new Produto();
        p1.setNome("Produto 1");
        p1.setDescricao("Descrição do produto 1!!");
        p1.setValor(500.99);
        Categoria categoria = categoriaDao.getById(1L);
        p1.setCategoria(c1);
    
        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.insert(p1);
    
        
        Categoria c2 = new Categoria();
        c2.setDescricao(JOptionPane.showInputDialog("Informe um nome"));
        categoriaDao.insert(c2);
        //a'); drop table venda_produto; INSERT INTO categoria(descricao) VALUES('a
        //a'); delete from produto where id=1; INSERT INTO categoria(descricao) VALUES('a
        System.out.println("Categoria ID=" + c2.getId() + 
                " inserida com sucesso!");
    
    }
    
}
