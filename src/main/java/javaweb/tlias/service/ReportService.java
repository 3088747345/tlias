package javaweb.tlias.service;

import javaweb.tlias.pojo.JobOption;

public interface ReportService {
    
    //根据职位统计员工人数
    JobOption getEmpJobData();
}
