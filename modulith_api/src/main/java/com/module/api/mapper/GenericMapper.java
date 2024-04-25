package com.module.api.mapper;

import java.util.List;

public interface GenericMapper<Entity, Object> {

    Object toDto(Entity entity);
    List<Object> toDtos(List<Entity> entities);

}
