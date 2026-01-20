package javaweb.tlias.service.impl;

import javaweb.tlias.mapper.EmpExprMapper;
import javaweb.tlias.mapper.EmpMapper;
import javaweb.tlias.pojo.Emp;
import javaweb.tlias.pojo.EmpExpr;
import javaweb.tlias.pojo.EmpQueryParam;
import javaweb.tlias.pojo.Logininfo;
import javaweb.tlias.pojo.PageResult;
import javaweb.tlias.service.EmpService;
import javaweb.tlias.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Slf4j
@Service
public class EmpServiceimpl implements EmpService {
    //注入员工映射器
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;
    
    //分页查询
    @Override
    public PageResult<Emp> page(EmpQueryParam param) {

        /* 
        //原始分页查询
        //1.查询总记录数
        Long count = empMapper.count();
        //2.查询分页数据
        Integer start = (page - 1) * pageSize;
        List<Emp> list = empMapper.page(start, pageSize);
        //3.封装分页结果
        return new PageResult<Emp>(count, list);

        */

        //使用PageHelper分页查询
        //1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());
        //2.执行查询
        List<Emp> list = empMapper.list(param);
        //3.封装分页结果
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //新增员工
    //开启事务管理,位置在方法上、类上、接口上都可以，推荐位置在方法上
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//rollbackFor:指定哪些异常触发回滚，默认是RuntimeException和Error；propagation:指定事务的传播行为，默认是REQUIRED，即如果当前存在事务，则加入该事务；如果当前不存在事务，则创建一个新的事务。
    //REQUIRES_NEW:无论有无事务，都创建一个新的事务
    //事务的四大特性：原子性、一致性、隔离性、持久性
    @Override
    public void save(Emp emp) {
        //1.设置默认值
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2.执行新增员工基本信息
        empMapper.insert(emp);
        //3.执行新增员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //4.设置员工工作经历信息的员工ID
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            //5.执行新增员工工作经历信息
            empExprMapper.insertBatch(exprList);
            };
        }


    //删除员工
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //1.执行删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.执行删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    //查询回显员工
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Emp getInfo(Integer id) {
        // 1. 执行查询员工基本信息
        Emp emp = empMapper.selectById(id);
    
        if (emp != null) { // 防止空指针异常
            // 2. 执行查询员工工作经历信息
            List<EmpExpr> exprList = empExprMapper.selectByEmpId(id);
            // 3. 封装员工工作经历信息
            emp.setExprList(exprList);
        }
    
        return emp;
    }

    //更新员工
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.设置更新时间
        emp.setUpdateTime(LocalDateTime.now());
        //2.执行更新员工基本信息
        empMapper.updateById(emp);
        //4.先删除员工工作经历信息
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
        //5.执行新增员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //6.设置员工工作经历信息的员工ID
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            //7.执行新增员工工作经历信息
            empExprMapper.insertBatch(exprList);
        }
    }

    //登录校验
    @Override
    public Logininfo login(Emp emp) {
        //1.根据用户名查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.判断员工是否存在
        if(e != null){
            log.info("登录成功");
            //3.生成JWT令牌
            String jwt = JwtUtils.generateToken(e.getId(), e.getUsername());
            return new Logininfo(e.getId(), e.getUsername(),e.getName(),jwt);
        }
        log.info("登录失败");
        return null;
    }

}
