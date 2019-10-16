package br.edu.utfpr.pb.oo24s.aula4.model;

import br.edu.utfpr.pb.oo24s.aula4.converter.TipoContatoConverter;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String telefone;

    // @Enumerated(EnumType.STRING)
    @Convert(converter = TipoContatoConverter.class)
    @Column(nullable = false)
    private ETipoContato tipoContato;

    // @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EOperadora operadora;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public Contato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ETipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(ETipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public EOperadora getOperadora() {
        return operadora;
    }

    public void setOperadora(EOperadora operadora) {
        this.operadora = operadora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
