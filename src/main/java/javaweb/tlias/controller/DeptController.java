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
import javaweb.tlias.pojo.Dept;
import javaweb.tlias.pojo.Result;
import javaweb.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;

@Slf4j//等价于private static final Logger log = LoggerFactory.getLogger(DeptController.class);lombok提供的注解，用于自动注入日志记录对象
@RestController
public class DeptController {
    
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询所有部门");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer id) {//这里的@RequestParam(value = "id",required = false)可省略
        System.out.println("根据id删除部门" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门" + dept);
        deptService.add(dept);
        return Result.success();
    }

    //修改部门
    //1.查询回显部门信息
    //路径参数：用于获取URL中的动态参数
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id) {
        System.out.println("根据id查询部门" + id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }
    //2.修改部门信息
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("根据id修改部门" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
