package com.core.service;



import com.core.entity.IEntity;
import com.core.repository.BaseRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface IService<E extends IEntity, ID extends Serializable> {
    default void create(E e) throws Exception {
        getRepository().save(e);
    }

    default void update(E e) throws Exception {
        getRepository().save(e);
    }

    default E get(ID id) throws Exception {
        return getRepository().findOne(id);
    }

    default void delete(ID id) throws Exception {
        getRepository().delete(id);
    }

    default Object list(Predicate predicate, Pageable pageable) throws Exception {
        return pageable == null ? list(predicate) : getRepository().findAll(predicate, pageable);
    }

    default Object list(Predicate predicate) throws Exception {
        return getRepository().findAll(predicate);
    }

    default long count(Predicate predicate) throws Exception {
        return getRepository().count(predicate);
    }

    BaseRepository<E, ID> getRepository();
}
