package service;

import java.util.List;

public interface IGeneralService <T>{
    List<T> fillAll();
    void save(T t);
    T update(T t, int id);
    void delete(int id);
    T findById(int id);
    T findByName(String name);
}