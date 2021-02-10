package ru.lighthouse.mobile.main.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.lighthouse.mobile.main.core.rest.mapper.MapperRegister;

import java.util.Map;

@Component
public class MapperFacadeFactoryBean implements FactoryBean<MapperFacade>, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public MapperFacade getObject() throws Exception {
        DefaultMapperFactory mapperFactory =  new DefaultMapperFactory.Builder().build();
        registerCustomMappers(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    private void registerCustomMappers(DefaultMapperFactory mapperFactory) {
        Map<String, MapperRegister> beansOfType = context.getBeansOfType(MapperRegister.class);
        beansOfType.values().forEach(r -> r.register(mapperFactory));
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }
}