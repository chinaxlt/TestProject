package org.chinaxlt.i️nterfaceTest;

import org.chinaxlt.classTest.MyDTO;
import org.chinaxlt.classTest.MyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MyConverter {

    MyConverter instance = Mappers.getMapper(MyConverter.class);

    MyDTO domain2dto(MyModel myModel);

    MyModel dto2domain(MyDTO myDTO);

}
