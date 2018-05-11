package org.chinaxlt.forInterface;

import org.chinaxlt.forClass.MyDTO;
import org.chinaxlt.forClass.MyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MyConverter {

    MyConverter instance = Mappers.getMapper(MyConverter.class);

    MyDTO domain2dto(MyModel myModel);

    MyModel dto2domain(MyDTO myDTO);

}
