package javaweb.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//@Data是Lombok提供的注解，用于自动生成getter、setter、toString、equals、hashCode等方法
@Data
//@NoArgsConstructor是Lombok提供的注解，用于自动生成无参构造方法
//@AllArgsConstructor是Lombok提供的注解，用于自动生成全参构造方法
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;//数据库中是create_time，驼峰命名法
    private LocalDateTime updateTime;
}
