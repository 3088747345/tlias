package javaweb.tlias.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaweb.tlias.mapper.EmpMapper;
import javaweb.tlias.pojo.JobOption;
import javaweb.tlias.service.ReportService;

@Service
public class ReportServiceimpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    //根据职位统计员工人数
    @Override
    public JobOption getEmpJobData() {

        //1.调用mapper方法查询数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        //2.将查询到的数据转换为JobOption对象
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    
}
