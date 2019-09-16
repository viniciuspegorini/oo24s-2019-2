package br.edu.utfpr.pb.aula2.main;

import br.edu.utfpr.pb.aula2.dao.CategoriaDao;
import br.edu.utfpr.pb.aula2.dao.ClienteDao;
import br.edu.utfpr.pb.aula2.dao.PermissaoDao;
import br.edu.utfpr.pb.aula2.dao.ProdutoDao;
import br.edu.utfpr.pb.aula2.dao.UsuarioDao;
import br.edu.utfpr.pb.aula2.dao.VendaDao;
import br.edu.utfpr.pb.aula2.dao.VendaProdutoDao;
import br.edu.utfpr.pb.aula2.model.Categoria;
import br.edu.utfpr.pb.aula2.model.Cliente;
import br.edu.utfpr.pb.aula2.model.Permissao;
import br.edu.utfpr.pb.aula2.model.Produto;
import br.edu.utfpr.pb.aula2.model.Usuario;
import br.edu.utfpr.pb.aula2.model.Venda;
import br.edu.utfpr.pb.aula2.model.VendaProduto;
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
        
        System.out.println("***** Método inserirCliente() *****");
        inserirCliente();
        
        System.out.println("***** Método inserirVenda() *****");
        inserirVenda();
        
        System.out.println("***** Método inserirVenda2() - OneToMany *****");
        inserirVenda2();
        
        System.out.println("***** Método listarVendas() *****");
        listarVendas();
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
        
        // INSERT SEGUNDO PRODUTO
        Produto p2 = new Produto();
        p2.setNome("Produto 2");
        p2.setDescricao("Descrição do Produto 2...");
        p2.setValor(999D);
        p2.setCategoria( categoriaDao.getById(1) );

        try {
            produtoDao.insert(produto);
            System.out.println("Produto " + produto.getId()
                    + " inserido com sucesso!");
            // INSERT SEGUNDO PRODUTO
            produtoDao.insert(p2);
            System.out.println("Produto " + p2.getId()
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

    private void inserirCliente() {
        try {
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = new Cliente();
            
            cliente.setNome("João das Neves");
            cliente.setCpf("22233344422");
            
            clienteDao.insert(cliente);
            System.out.println("Cliente " + cliente.getId() + " inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void inserirVenda() {
        try {
            VendaDao vendaDao = new VendaDao();
            ClienteDao clienteDao = new ClienteDao();
            VendaProdutoDao vendaProdutoDao = new VendaProdutoDao();
            ProdutoDao produtoDao = new ProdutoDao();
            
            
            Venda venda = new Venda();
            venda.setData(LocalDate.now());
            venda.setCliente( clienteDao.getById(1L) );
            
            vendaDao.insert(venda);
            
            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setProduto( produtoDao.getById(1L) );
            vendaProduto.setVenda(venda);
            vendaProduto.setQuantidade(2);
            vendaProduto.setValor( produtoDao.getById(1L).getValor() );
            
            vendaProdutoDao.insert(vendaProduto);
            
            System.out.println("Venda " + venda.getId() + " salva com sucesso!");
            System.out.println("VendaProduto " + vendaProduto.getId() + " salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirVenda2() {
        try {
            VendaDao vendaDao = new VendaDao();
            ClienteDao clienteDao = new ClienteDao();
            ProdutoDao produtoDao = new ProdutoDao();
            
            Venda venda = new Venda();
            venda.setData( LocalDate.now() );
            venda.setCliente( clienteDao.getById(1L) );
            
            venda.setVendaProdutos( new ArrayList<>() );
            
            //VENDA do PRODUTO cód= 1
            VendaProduto vp1 = new VendaProduto();
            vp1.setProduto( produtoDao.getById(1L) );
            vp1.setVenda( venda );
            vp1.setQuantidade( 4 );
            vp1.setValor( produtoDao.getById(1L).getValor() );

            venda.getVendaProdutos().add(vp1);
            
            //VENDA do PRODUTO cód= 2
            VendaProduto vp2 = new VendaProduto();
            vp2.setProduto( produtoDao.getById(2L) );
            vp2.setVenda( venda );
            vp2.setQuantidade( 3 );
            vp2.setValor( produtoDao.getById(2L).getValor() );
            
            venda.getVendaProdutos().add(vp2);
            
            
            vendaDao.insert(venda);
            System.out.println("Venda " + venda.getId() + " inserida com suceso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarVendas() {
        try {
            VendaDao vendaDao = new VendaDao();
            
            vendaDao.getAll().forEach( v -> {
                System.out.println("\n\nVenda: " + v.getId() 
                                 + " - Cliente: " + v.getCliente().getNome() 
                                 + " - Data: " + v.getData() );
                System.out.println("Produto | Qtde | Valor");
                v.getVendaProdutos().forEach( vp -> 
                        System.out.println(vp.getProduto().getNome() + " | " + 
                                           vp.getQuantidade() + " | " + 
                                            vp.getValor() )
                );
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
