package com.kaishengit.mappers;

import com.kaishengit.entity.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *@CacheNamespace 开启二级缓存
 * @author silver
 * @date 2017/10/26
 */

/*@CacheNamespace*/

public interface TeamMapper {

    /**
     * 查找所有
     * 使用注解时，需要再主配置文件的<mappers></mappers>里，添加对应的配置
     * @return 返回boy对象
     */
    @Select("select id, team_name from t_team")
    List<Team> findAll();

    /**
     * 插入
     * 注解生成主键的方法
     * @Options(useCache=false)
     *把相对的语句禁用耳二级缓存
     *
     * @param team
     *
     */
    @Insert("insert into t_team (team_name) value (#{teamName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Team team);

    /**1
     * @param id 1
     * @return 1
     */
    Team findByIdTwo(int id);


}
