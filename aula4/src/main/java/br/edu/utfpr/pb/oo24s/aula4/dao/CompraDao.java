package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Compra;

public class CompraDao  extends GenericDao<Compra, Long> {

    public CompraDao() {
        super(Compra.class);
    }
    
}