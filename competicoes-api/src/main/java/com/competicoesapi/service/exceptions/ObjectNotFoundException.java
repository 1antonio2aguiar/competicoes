package com.competicoesapi.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private final long serialVersionUID = 1L;

    public ObjectNotFoundException(Object id){
        super("" + id);
    }
}