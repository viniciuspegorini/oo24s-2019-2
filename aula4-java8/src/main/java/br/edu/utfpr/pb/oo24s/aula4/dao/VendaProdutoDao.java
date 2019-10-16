package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.VendaProduto;

public class VendaProdutoDao  extends GenericDao<VendaProduto, Long> {

    public VendaProdutoDao() {
        super(VendaProduto.class);
    }
    
}