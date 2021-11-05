package ru.shark.home.l2info.common.converters;

import org.dozer.DozerConverter;
import ru.shark.home.common.dao.dto.EnumDto;
import ru.shark.home.l2info.enums.ClassType;

public class ClassEnumConverter extends DozerConverter<ClassType, EnumDto> {

    public ClassEnumConverter() {
        super(ClassType.class, EnumDto.class);
    }

    @Override
    public EnumDto convertTo(ClassType classType, EnumDto enumDto) {
        if (classType == null) {
            return null;
        }
        return new EnumDto(classType.name(), classType.getTitle());
    }

    @Override
    public ClassType convertFrom(EnumDto enumDto, ClassType classType) {
        if (enumDto == null) {
            return null;
        }
        return ClassType.valueOf(enumDto.getCode());
    }
}
