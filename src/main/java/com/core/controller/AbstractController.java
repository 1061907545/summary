package com.core.controller;


import com.core.entity.IEntity;
import com.core.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractController<E extends IEntity, ID extends Serializable> extends AbstractReadonlyController<E, ID> implements IController<E, ID, IService<E, ID>> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
