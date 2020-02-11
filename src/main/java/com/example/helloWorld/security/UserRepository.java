package com.example.helloWorld.security;

import com.example.helloWorld.TUrl;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    // 自定义的函数，但是无法映射到具体的字段上去f_name; 修改POJO类，显示指定字段名即可
    /**
     * select u from t_url u where u.name = ?1
     * @param name
     * @return
     */
    User findByName(@Param("username") String name);

}
