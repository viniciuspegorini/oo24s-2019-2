package br.edu.utfpr.pb.aula2.main;

import br.edu.utfpr.pb.aula2.dao.CategoriaDao;
import br.edu.utfpr.pb.aula2.dao.PermissaoDao;
import br.edu.utfpr.pb.aula2.dao.ProdutoDao;
import br.edu.utfpr.pb.aula2.dao.UsuarioDao;
import br.edu.utfpr.pb.aula2.model.Categoria;
import br.edu.utfpr.pb.aula2.model.Permissao;
import br.edu.utfpr.pb.aula2.model.Produto;
import br.edu.utfpr.pb.aula2.model.Usuario;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main();
        System.exit(0);
    }

    public Main() {
        System.out.println("***** Método inserirCategoria() *****");
        inserirCategoria();

        System.out.println("***** Método inserirProduto() *****");
        inserirProduto();

        System.out.println("***** Método testes() *****");
        testes();

        System.out.println("***** Método inserirUsuario() *****");
        inserirUsuario();
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

    private void inserirProduto() {
        ProdutoDao produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setNome("Monitor 20pol.");
        produto.setDescricao("Descrição do monitor de 20pol.");
        produto.setValor(100D);

        CategoriaDao categoriaDao = new CategoriaDao();
        //Categoria categoria = categoriaDao.getById( 1 );
        //produto.setCategoria( categoria );

        produto.setCategoria(categoriaDao.getById(1));

        try {
            produtoDao.insert(produto);
            System.out.println("Produto " + produto.getId()
                    + " inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testes() {
        try {

            CategoriaDao categoriaDao = new CategoriaDao();
            ProdutoDao produtoDao = new ProdutoDao();

            // inserindo uma nova categoria
            Categoria c2 = new Categoria();
            c2.setDescricao("Categoria 2");
            categoriaDao.insert(c2);

            // listando todas as categorias do banco
            categoriaDao.getAll().forEach(
                    c -> System.out.println(c)
            );

            // inserindo um novo produto
            Produto p2 = new Produto();
            p2.setNome("Produto 2");
            p2.setDescricao("Descrição do produto 2...");
            p2.setValor(999.55);
            p2.setCategoria(c2);

            produtoDao.insert(p2);

            // listando todos os produtos
            produtoDao.getAll().forEach(
                    p -> System.out.println(p)
            );

            // Editando a categoria 1
            Categoria c1 = categoriaDao.getById(1);
            c1.setDescricao("Nova descrição da categoria 1");

            categoriaDao.update(c1);
            // listando todas as categorias do banco
            categoriaDao.getAll().forEach(
                    c -> System.out.println(c)
            );

            // Consulta categoriaDao.findByDescricaoLike
            categoriaDao.findByDescricaoLike("Nova").forEach(
                    c -> System.out.println(c)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirUsuario() {
        try {

            PermissaoDao permissaoDao = new PermissaoDao();
            UsuarioDao usuarioDao = new UsuarioDao();

            // cadastrando novas permissões
            Permissao p1 = new Permissao();
            p1.setNome("P_USER");
            permissaoDao.insert(p1);
            
            Permissao p2 = new Permissao();
            p2.setNome("P_ADMIN");
            permissaoDao.insert(p2);

            // Cadastrando um novo usuário
            Usuario u1 = new Usuario();
            u1.setNome("João das Neves");
            u1.setEmail("joao@gmail.com");
            u1.setSenha("d4n1");
            u1.setDataNascimento(LocalDate.of(1990, Month.JANUARY, 1));
            u1.setAtivo(Boolean.TRUE);
            
            // adicionar as permissões 
            List<Permissao> permissoes = new ArrayList<>();
            permissoes.add(p1);
            permissoes.add(p2);
            u1.setPermissoes(permissoes);
            
            //ou
            //u1.setPermissoes( new ArrayList<>() );
            //u1.getPermissoes().add(p1);
            //u1.getPermissoes().add(p2);
            
            
            usuarioDao.insert(u1);
            
            u1.getPermissoes().remove(p1);
            
            usuarioDao.update(u1);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
