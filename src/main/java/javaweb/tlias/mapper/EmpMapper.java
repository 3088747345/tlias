package javaweb.tlias.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import javaweb.tlias.pojo.Emp;
import javaweb.tlias.pojo.EmpQueryParam;


/*
 * 员工信息
*/
@Mapper
public interface EmpMapper {
    
    /* 
    //原始分页查询
    //查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    //查询分页数据
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);

    */
   public List<Emp> list(EmpQueryParam param);

   //因为emp_expr表的emp_id字段是外键，所以获取emp表的id值，然后再插入emp_expr表数据
   @Options(useGeneratedKeys = true, keyProperty = "id")//主键回显
   @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
           "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
   public void insert(Emp emp);

   //根据员工id批量删除员工基本信息
   public void deleteByIds(List<Integer> ids);

   //根据员工id查询员工基本信息
   @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id where e.id = #{id}")
   public Emp selectById(Integer id);

   //根据员工id更新员工基本信息
   public void updateById(Emp emp);

   //统计员工职位人数
   public List<Map<String, Object>> countEmpJobData();

   //根据用户名和密码查询员工信息
   @Select("select e.* from emp e where e.username = #{username} and e.password = #{password}")
   public Emp selectByUsernameAndPassword(Emp emp);
}
