package com.taskplan.service.util;

import java.lang.reflect.ParameterizedType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 

public class BaseMapper<R, E> implements IMapper<R, E> {     

       private ModelMapper modelMapper=new ModelMapper(); 

       public E convertToEntity(R resource) {
              E entity = getEntityInstance();
              modelMapper.map(resource, entity);
              return entity;
       }
       public R convertToResource(E entity) {
              R resource = null;
              if(entity != null){
                     resource = getResourceInstance();
                     modelMapper.map(entity, resource);
              }
              return resource;
       }
        @SuppressWarnings("unchecked")
       private Class<R> getResourceClass() {
              ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
              return (Class<R>) type.getActualTypeArguments()[0];
       } 

       @SuppressWarnings("unchecked")

       private Class<E> getEntityClass() {
              ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
              return (Class<E>) type.getActualTypeArguments()[1];
       } 

       private E getEntityInstance() {
              E entity = null;
              try {
                     entity = getEntityClass().newInstance();

              } catch (InstantiationException e) {
                     e.printStackTrace();
              } catch (IllegalAccessException e) {
                     e.printStackTrace();
              }
              return entity;

       }

       private R getResourceInstance() {
              R resource = null;
              try {
                     resource = getResourceClass().newInstance();

              } catch (InstantiationException e) {
                     e.printStackTrace();
              } catch (IllegalAccessException e) {
                     e.printStackTrace();
              }
              return resource;
       }

}

