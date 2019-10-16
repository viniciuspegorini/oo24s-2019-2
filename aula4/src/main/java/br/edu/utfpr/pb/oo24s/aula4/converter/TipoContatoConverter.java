package br.edu.utfpr.pb.oo24s.aula4.converter;

import br.edu.utfpr.pb.oo24s.aula4.model.ETipoContato;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

//@Converter(autoApply = true)
@Convert
public class TipoContatoConverter implements 
                    AttributeConverter<ETipoContato, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ETipoContato value) {
        return value.getId();
    }

    @Override
    public ETipoContato convertToEntityAttribute(Integer value) {
        return ETipoContato.findById(value);
    }
    
}
