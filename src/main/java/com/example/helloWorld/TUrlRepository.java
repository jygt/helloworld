package com.example.helloWorld;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TUrlRepository extends JpaRepository<TUrl, String> {

    // 自定义的函数，但是无法映射到具体的字段上去f_name; 修改POJO类，显示指定字段名即可
    /**
     * select u from t_url u where u.name = ?1
     * @param name
     * @return
     */
    List<TUrl> findByFName(String name);

    /**
     * select u from t_url u where u.name like ?1
     * @param name
     * @return
     */
    List<TUrl> findByFNameLike(String name);


    /**
     * select u from t_url u where id in (?,?,?)
     * @param ids
     * @return
     */
    List<TUrl> findByIdIn(Collection<String> ids);
}
