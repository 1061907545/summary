package com.core.controller;


import com.core.entity.IEntity;
import com.core.service.IService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

public interface IController<E extends IEntity, ID extends Serializable, S extends IService<E, ID>> extends IReadonlyController<E, ID, S> {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    default void post(@Valid @RequestBody E e, BindingResult result) throws Exception {
        validate(result);
        getService().create(e);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    default void put(@PathVariable("id") Serializable id, @Valid @RequestBody E e, BindingResult result) throws Exception {
        validate(result);
        getService().update(e);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    default void patch(@PathVariable("id") ID id, @Valid @RequestBody JsonNode json, BindingResult result) throws Exception {
        validate(result);
        E e = getService().get(id);
        getObjectMapper().readerForUpdating(e).readValue(json);
        getService().update(e);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    default void delete(@PathVariable("id") ID id) throws Exception {
        getService().delete(id);
    }

    default void validate(BindingResult result) throws Exception {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                sb.append(error.getDefaultMessage()).append("，");
            });
            String s = sb.toString();
            s = s.substring(0, s.length() - 1) + "！";
            throw new Exception("数据校验失败");
        }
    }

    ObjectMapper getObjectMapper();
}
