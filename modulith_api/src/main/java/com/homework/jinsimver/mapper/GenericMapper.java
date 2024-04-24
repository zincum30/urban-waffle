package com.homework.jinsimver.mapper;

import java.util.List;

public interface GenericMapper<Entity, Object> {

    Object toDto(Entity entity);
    List<Object> toDtos(List<Entity> entities);

}
