package br.edu.utfpr.pb.oo24s.aula5.javafx.dao;

import br.edu.utfpr.pb.oo24s.aula5.javafx.model.Produto;

public class ProdutoDao extends GenericDao<Produto, Long> {

    public ProdutoDao() {
        super(Produto.class);
    }
}
