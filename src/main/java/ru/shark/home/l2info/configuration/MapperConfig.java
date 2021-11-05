package ru.shark.home.l2info.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shark.home.common.dao.util.ConverterUtil;
import ru.shark.home.l2info.common.converters.ClassEnumConverter;
import ru.shark.home.l2info.dao.dto.ClassDto;
import ru.shark.home.l2info.dao.entity.ClassEntity;

@Configuration
public class MapperConfig {

    @Bean
    public ConverterUtil getConverterUtil() {
        return new ConverterUtil();
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(ClassEntity.class, ClassDto.class)
                        .fields("type", "type", FieldsMappingOptions.customConverter(ClassEnumConverter.class));
            }
        };
    }
    @Bean
    public Mapper getMapper() {
         DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder());
        return mapper;
    }
}
