package br.com.softworks.pesquisei.exception.model;

public class ServiceException extends RuntimeException {

    public ServiceException(String classe, Long id) {
        super(String.format("%s não encontrada pelo Id %s", classe, id));
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
