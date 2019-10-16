package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Venda;

public class VendaDao  extends GenericDao<Venda, Long> {

    public VendaDao() {
        super(Venda.class);
    }
    
}