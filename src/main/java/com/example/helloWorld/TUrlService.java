package com.example.helloWorld;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface TUrlService {
    Optional<TUrl> findById(String id);

    List<TUrl> findAll();

    List<TUrl> findAllRetry();
    Future<List<TUrl>> findAllAsync();

    TUrl save (TUrl objTUrl);

    void delete(String id);

    Page<TUrl> findAll(Pageable pageable);

    //
    List<TUrl> findByFName(String name);
    List<TUrl> findByFNameLike(String name);
    List<TUrl> findByIdIn(Collection<String> ids);
    // dao of mybatis
    TUrl findByName(String name);
}
