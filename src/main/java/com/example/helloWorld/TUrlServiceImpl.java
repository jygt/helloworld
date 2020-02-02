package com.example.helloWorld;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Spring 会自动扫描到 @Component 注解的类，并把这些类纳入Spring容器中管理，
@Service
public class TUrlServiceImpl implements TUrlService {

    //
    @Resource
    private TUrlRepository objTUrlRepository;

    @Override
    public Optional<TUrl> findById(String id){
        return objTUrlRepository.findById(id);
    }

    @Override
    public List<TUrl> findAll(){
        return objTUrlRepository.findAll();
    }

    // 添加事务 ： 如果出现异常（空指针NullPointException），数据存储会失败
    // 如果去掉注解，即使抛出异常，数据也会被成功写入
   // @Transactional
    @Override
    public TUrl save (TUrl objTUrl){
        TUrl res =
            objTUrlRepository.save(objTUrl);

        // 构建异常
        String err ="test" ;
        if(objTUrl.getF_cat()==1) {

            System.out.println("cat = 1 : null throw.");
             err = null;

            err.split("/");
        }

        return res;
    }

    @Override
    public void delete(String id){
        objTUrlRepository.deleteById(id);
    }
    @Override
    public Page<TUrl> findAll(Pageable pageable){
        return objTUrlRepository.findAll( pageable);
    }

    @Override
    public  List<TUrl> findByFName(String name){
        return objTUrlRepository.findByFName(name);
    }
    @Override
    public List<TUrl> findByFNameLike(String name){
        return objTUrlRepository.findByFNameLike(name);
    }
    @Override
    public List<TUrl> findByIdIn(Collection<String> ids){
        return objTUrlRepository.findByIdIn(ids);
    }

 //*/
}
