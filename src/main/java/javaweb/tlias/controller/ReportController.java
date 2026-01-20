package javaweb.tlias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaweb.tlias.pojo.JobOption;
import javaweb.tlias.pojo.Result;
import javaweb.tlias.service.ReportService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    //根据职位统计员工人数
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("根据职位统计员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
      }
}
