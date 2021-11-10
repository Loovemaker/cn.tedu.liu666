package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
class JtApplicationTests {

    Random random = new Random();

    @Autowired
    private UserMapper userMapper;

    @Test void findAll() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test void findById() {
        val id = 1;
        System.out.println(userMapper.selectById(id));

        val idFrom = 1; val idTo = 50;
        val list = new LinkedList<Integer>();
        IntStream.rangeClosed(idFrom, idTo).forEach(list::add);
        userMapper.selectBatchIds(list).forEach(System.out::println);
    }

    @Test void insert() {
        val name = "猴子";
        System.out.printf("%d rows affected\n",
                userMapper.insert(
                        new User(
                                null,
                                name,
                                random.nextInt() >>> 1,
                                randomSex()
                        )
                )
        );
    }

    @Test void findByName() {
        val name = "小乔";
        userMapper.selectList(new QueryWrapper<>(
                new User()
                        .setName(name)
        )).forEach(System.out::println);
    }

    @Test void findByAge() {
        val greaterThan = 18;
        userMapper.selectList(new QueryWrapper<User>()
                .ge("age", greaterThan)
        ).forEach(System.out::println);
    }

    @Test void findByAgeAndLikeAndSex() {
        val greaterThan = 18;
        val nameEndWith = "乔";
        val female = "女";
        userMapper.selectList(new QueryWrapper<User>()
                .ge("age", greaterThan)
                // 自动 AND
                .likeLeft("name", nameEndWith)
                .eq("sex", female)
        ).forEach(System.out::println);
    }

    @Test void findByMultIdsWithOrder() {
        val ids = new Integer[] {1, 3, 4, 6, 7};
        userMapper.selectList(new QueryWrapper<User>()
                .in("id", (Object[]) ids)
                .orderByDesc("id")
        ).forEach(System.out::println);
    }

    @Test void findByNullableNameAndSex() {
        IntStream.range(0, 4).forEachOrdered(i -> {
            val name = (i / 2 != 0) ? "_" : (String) null;
            val sex = (i % 2 != 0) ? (String) null :
                    randomSex();
            System.out.println(new StringJoiner(" ")
                    .add("querying:")
                    .add(name).add(sex)
            );
            userMapper.selectList(new QueryWrapper<User>()
                    .eq(StringUtils.hasLength(name), "name", name)
                    .eq(StringUtils.hasLength(sex), "sex", sex)
            ).forEach(System.out::println);
        });
    }

    @Test void findByObjs() {
        System.out.println(userMapper.selectObjs(null));
    }

    @Test void updateUserSexByName() {
        val name = "小乔";
        System.out.printf("%d rows affected\n",
                userMapper.update(
                        new User().setSex(randomSex()),
                        new QueryWrapper<User>()
                                .eq("name", name)
                )
        );
    }

    String randomSex() {
        return random.nextBoolean() ? "男" : "女";
    }

}
