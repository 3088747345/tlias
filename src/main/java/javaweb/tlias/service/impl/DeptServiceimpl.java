package javaweb.tlias.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javaweb.tlias.mapper.DeptMapper;
import javaweb.tlias.pojo.Dept;
import javaweb.tlias.service.DeptService;

@Service
public class DeptServiceimpl implements DeptService {
    
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {

        //1.补全部门信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层方法新增部门
        deptMapper.insert(dept);
    }

    @Override
    public Dept getInfo(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.补全部门信息
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层方法修改部门
        deptMapper.update(dept);
    }
}