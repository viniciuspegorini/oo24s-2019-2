package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Venda;

public class VendaDao  extends GenericDao<Venda, Long> {

    public VendaDao() {
        super(Venda.class);
    }
    
}