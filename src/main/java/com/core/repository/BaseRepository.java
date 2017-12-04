package com.core.repository;



import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.core.entity.IEntity;

import java.io.Serializable;




@NoRepositoryBean
public interface BaseRepository<E extends IEntity, ID extends Serializable> extends PagingAndSortingRepository<E, ID>, QueryDslPredicateExecutor<E> {
}
