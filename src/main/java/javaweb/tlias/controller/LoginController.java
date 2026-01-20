package javaweb.tlias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javaweb.tlias.pojo.Emp;
import javaweb.tlias.pojo.Logininfo;
import javaweb.tlias.pojo.Result;
import javaweb.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
    
    @Autowired
    private EmpService empService;
    //登录接口
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {

        log.info("登录请求参数：{}", emp);
        Logininfo info = empService.login(emp);
        if (info != null) {
            return Result.success(info);
        }
        //3.如果校验通过，返回token
        return Result.error("用户名或密码错误");
    }
}
