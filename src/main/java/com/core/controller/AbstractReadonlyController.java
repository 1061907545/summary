package com.core.controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.core.entity.IEntity;
import com.core.service.IService;

import java.io.Serializable;

public abstract class AbstractReadonlyController<E extends IEntity, ID extends Serializable> implements IReadonlyController<E, ID, IService<E, ID>> {
    @Autowired
    private IService<E, ID> service;

    @Override
    public IService<E, ID> getService() {
        return service;
    }
}
