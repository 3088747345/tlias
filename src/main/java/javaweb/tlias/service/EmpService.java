package javaweb.tlias.service;

import java.util.List;

import javaweb.tlias.pojo.Emp;
import javaweb.tlias.pojo.EmpQueryParam;
import javaweb.tlias.pojo.Logininfo;
import javaweb.tlias.pojo.PageResult;

public interface EmpService {

    //分页查询
    PageResult<Emp> page(EmpQueryParam param);

    //新增员工
    void save(Emp emp);

    //删除员工
    void delete(List<Integer> ids);

    //查询回显员工
    Emp getInfo(Integer id);

    //更新员工
    void update(Emp emp);

    //登录校验
    Logininfo login(Emp emp);
    
}
