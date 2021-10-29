package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper2 {
    List<User2> listAllUsers();
    List<User2> findIn(int[] ids);
    List<User2> findInList(List<Integer> ids);
    List<User2> findInMap(
            @Param("ids") int[] ids,
            @Param("sex") String sex
    );

    List<User2> findSqlWhere(User2 user2);

    Integer sqlUpdate(User2 user2);
}
