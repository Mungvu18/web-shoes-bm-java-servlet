package service.iService;

import java.util.List;

public interface IGeneralService <T>{
    List<T> fillAll();
    void save(T t);
    T update(T t, int id);
    void delete(int id);
    T findById(int id);
    List<T> findByName(String name);
}
