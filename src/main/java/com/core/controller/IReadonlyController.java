package com.core.controller;


import com.core.entity.IEntity;
import com.core.service.IService;
import com.querydsl.core.types.Predicate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.lang.reflect.Field;

public interface IReadonlyController<E extends IEntity, ID extends Serializable, S extends IService<E, ID>> {
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    default Object get(@PathVariable("id") ID id) throws Exception {
        return getService().get(id);
    }


    @ResponseBody
    @RequestMapping(value = "{id}/{field}", method = RequestMethod.GET)
    default Object getField(@PathVariable("id") ID id, @PathVariable("field") String fieldName) throws Exception {
        E e = getService().get(id);
        if (e == null) {
            return null;
        }
        //这里对象强制作为字符串返回不妥
//        String str = BeanUtils.getProperty(e, fieldName);
        Field field = e.getClass().getDeclaredField(fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(e);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    Object list(Predicate predicate, Pageable pageable) throws Exception;


    S getService();
}
