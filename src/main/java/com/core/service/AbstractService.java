package com.core.service;
import java.io.Serializable;

import com.core.entity.IEntity;

public abstract class AbstractService<E extends IEntity, ID extends Serializable> implements IService<E, ID> {

}
