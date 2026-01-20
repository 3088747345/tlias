package javaweb.tlias.service;

import java.util.List;
import javaweb.tlias.pojo.Dept;

public interface DeptService {

    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getInfo(Integer id);

    void update(Dept dept);
    
}
