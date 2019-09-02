package br.edu.utfpr.pb.aula2.main;

import br.edu.utfpr.pb.aula2.dao.CategoriaDao;
import br.edu.utfpr.pb.aula2.model.Categoria;

public class Main {

    public static void main(String[] args) {
        new Main();
        System.exit(0);
    }
    
    public Main() {
        inserirCategoria();
    }

    private void inserirCategoria() {
        CategoriaDao categoriaDao = new CategoriaDao();
        
        Categoria categoria = new Categoria();
        categoria.setDescricao("Categoria 1");
        
        try {
            categoriaDao.insert(categoria);
            System.out.println("Categoria: " + categoria.getId() + " inserida com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
