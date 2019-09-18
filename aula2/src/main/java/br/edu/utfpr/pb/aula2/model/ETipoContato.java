package br.edu.utfpr.pb.aula2.model;

public enum ETipoContato {
    RESIDENCIAL(11),
    CELULAR(22),
    COMERCIAL(33);
    
    private final Integer id;

    ETipoContato(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public static ETipoContato findById(Integer id) {
        for (ETipoContato tipoContato : ETipoContato.values()) {
            if (tipoContato.getId().equals(id)) return tipoContato;
        }
        throw new IllegalArgumentException("Tipo Contato inválido!");
    }
}
