package com.core.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.core.entity.IEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface SqlBaseRepository<E extends IEntity, ID extends Serializable> extends BaseRepository<E, ID>, JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
