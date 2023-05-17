package com.teste.demo.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // ResourceNotFoundException SIGNIFICA QUE NÃO ENCONTRAMOS O RECURSO(ID) QUE BUSCAMOS, ENTÃO VAMOS RETORNAR...
    // UMA EXCEÇÃO.

    // NO SUPER ESTAMOS CHAMANDO O CONSTRUTOR DA SUPER CLASSE QUE É RuntimeException
    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " + id);
    }
}
