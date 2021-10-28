package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User findUserById(Integer id);
    Integer saveUser(User user);
    Integer updateUser(User user);
    Integer deleteUser(User user);

    List<User> findByAge(Map<String, Integer> map);
    List<User> findParam(
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge
    );
    List<User> findByLike(String name);
}
