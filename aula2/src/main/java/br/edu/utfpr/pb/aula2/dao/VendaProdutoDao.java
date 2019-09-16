package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.VendaProduto;

public class VendaProdutoDao  extends GenericDao<VendaProduto, Long> {

    public VendaProdutoDao() {
        super(VendaProduto.class);
    }
    
}