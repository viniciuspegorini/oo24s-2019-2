package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Compra;

public class CompraDao  extends GenericDao<Compra, Long> {

    public CompraDao() {
        super(Compra.class);
    }
    
}