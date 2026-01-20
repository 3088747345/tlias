package javaweb.tlias.pojo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class JobOption {
    
    private List<Object> jobList;//数据列表
    private List<Object> dataList;//职位列表
}
