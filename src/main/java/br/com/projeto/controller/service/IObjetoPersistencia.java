package br.com.projeto.controller.service;


import java.util.List;

public interface IObjetoPersistencia<T>{
    
    public void salvar(T obj);
    public void excluir(T obj);
    public void editar(T obj);
    public List<T> listar();
    public T buscar(T obj);
}
