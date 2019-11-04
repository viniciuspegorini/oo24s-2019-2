package br.edu.utfpr.pb.oo24s.aula5.javafx.util;

import br.edu.utfpr.pb.oo24s.aula5.javafx.model.ETipoContato;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoContatoConverter implements 
        AttributeConverter<ETipoContato, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ETipoContato attribute) {
        return attribute.getId();
    }

    @Override
    public ETipoContato convertToEntityAttribute(Integer dbData) {
        return ETipoContato.findById(dbData);
    }
    
}
