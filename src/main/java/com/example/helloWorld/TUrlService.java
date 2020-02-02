package com.example.helloWorld;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TUrlService {
    Optional<TUrl> findById(String id);

    List<TUrl> findAll();

    TUrl save (TUrl objTUrl);

    void delete(String id);

    Page<TUrl> findAll(Pageable pageable);

    //
    List<TUrl> findByFName(String name);
    List<TUrl> findByFNameLike(String name);
    List<TUrl> findByIdIn(Collection<String> ids);
}
