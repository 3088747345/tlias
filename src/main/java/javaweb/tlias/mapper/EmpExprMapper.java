package javaweb.tlias.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javaweb.tlias.pojo.EmpExpr;

/**
 * 工作经历
 */
@Mapper
public interface EmpExprMapper {

    //根据员工id批量新增员工工作经历信息
    void insertBatch(List<EmpExpr> exprList);

    //根据员工id批量删除员工工作经历信息
    void deleteByEmpIds(List<Integer> ids);

    //根据员工id查询员工工作经历信息
    @Select("select * from emp_expr where emp_id = #{id}")
    List<EmpExpr> selectByEmpId(Integer id);
    
}
