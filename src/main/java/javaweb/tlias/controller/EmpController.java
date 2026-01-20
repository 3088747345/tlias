package javaweb.tlias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaweb.tlias.pojo.Emp;
import javaweb.tlias.pojo.EmpQueryParam;
import javaweb.tlias.pojo.PageResult;
import javaweb.tlias.pojo.Result;
import javaweb.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmpController {
    //注入业务层对象
    @Autowired
    private EmpService empService;         
    
    //分页查询
    @GetMapping("/emps")
    public Result page(EmpQueryParam param) {
        log.info("分页查询，参数：{}", param);
        //调用业务层分页查询方法
        PageResult<Emp> pageResult = empService.page(param);

        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工，参数：{}", emp);
        //调用业务层新增员工方法
        empService.save(emp);
        return Result.success();
    }

    //删除员工
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工，参数：{}", ids);
        //调用业务层删除员工方法
        empService.delete(ids);
        return Result.success();
    }

    //查询回显员工
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询回显员工，参数：{}", id);
        //调用业务层查询回显员工方法
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //更新员工
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工，参数：{}", emp);
        //调用业务层更新员工方法
        empService.update(emp);
        return Result.success();
    }
}
