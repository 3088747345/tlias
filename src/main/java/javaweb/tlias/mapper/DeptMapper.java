package javaweb.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javaweb.tlias.pojo.Dept;

//公共路径：/depts
@Mapper
public interface DeptMapper {

    //查询所有部门
    //当数据库字段名与实体类属性名不一致时，需要用如下方法来映射
    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")
    List<Dept> findAll();

    //根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    //根据id查询部门
    @Select("select id, name, create_time createTime, update_time updateTime from dept where id = #{id}")
    Dept getById(Integer id);

    //根据id修改部门
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
    
}
