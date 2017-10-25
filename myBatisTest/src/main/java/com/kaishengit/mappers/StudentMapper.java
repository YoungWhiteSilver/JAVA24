package com.kaishengit.mappers;

import com.kaishengit.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/24
 * 接口里的方法都为抽象方法，不能有普通方法
 *  方法前的public abstract是默认存在，可以省略
 *该接口中的所有方法名都要与com.kaishengit.mapper.StudentMapper.xml
 * 中的方法Id相同，返回这类型也要相同！
 */
public interface StudentMapper {

    /**
     *保存student
     * @param student 传入student对象
     *该映射方法的的返回值是默认存在的！无需再mapper.xml
     * 中增加resultType属性 存在默认返回的有 insert update
     *  delete
     *@return int 返回的是受影响行数
     */
    int save(Student student);

    /**
     * 通过ID查找
     * @param id studnet的id
     * @return student对象
     */
    Student findById(int id);

    /**
     * 修改studnet
     * @param student 传入student对象
     */
    void updateName(Student student) ;

    /**
     * 批量添加
     * @param studentList 传入studentLsit
     * @return 返回受影响行数
     */
    int saveSome(@Param("studentList")List<Student> studentList);


}
