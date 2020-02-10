package com.example.helloWorld;

import com.example.helloWorld.dao.TUrlMybatisDao;
import com.example.helloWorld.error.BusinessException;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

// Spring 会自动扫描到 @Component 注解的类，并把这些类纳入Spring容器中管理，
@Service
public class TUrlServiceImpl implements TUrlService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Resource
    private TUrlRepository objTUrlRepository;

    @Override
    public Optional<TUrl> findById(String id){
        return objTUrlRepository.findById(id);
    }

    private static Integer iRetry = 0;
    @Override
    /**
     * value 说明那些异常的时候触发重试
     * maxAttempts 表示最大重试次数默认为3
     * delay 表示重试延迟的时间
     * multiplier 表示上一次演示时间是这一次的倍数
     */
    @Retryable(value = {BusinessException.class},maxAttempts = 5,backoff = @Backoff(delay = 100,multiplier = 2))
    public List<TUrl> findAllRetry() {
        if(iRetry++ > 3) {
            iRetry = 0;
            return findAll();
        }
        logger.error("***************************** findAllRetry error " + iRetry);
        throw new BusinessException();
    }

    @Override
    public List<TUrl> findAll(){
        // 考察异步任务。这里记录下任务完成时间
        try {
            long start = System.currentTimeMillis();
            List<TUrl> res = objTUrlRepository.findAll();
            long end = System.currentTimeMillis();

            logger.info("Do findAll exhaused " + (end - start) + "ms.");
            return res;
        }
        catch(Exception exp)
        {
            logger.error("findAllAsync Error：",exp);
            return Collections.EMPTY_LIST;
        }
    }
    @Override
    @Async
    public Future<List<TUrl>> findAllAsync(){
        // 考察异步任务。这里记录下任务完成时间
        try {
            long start = System.currentTimeMillis();
            List<TUrl> res = objTUrlRepository.findAll();
            long end = System.currentTimeMillis();

            logger.info("Do findAllAsync exhaused " + (end - start) + "ms.");
            return new AsyncResult<List<TUrl> >(res);
        }
        catch(Exception exp)
        {
            logger.error("findAllAsync Error：",exp);
            return new AsyncResult<List<TUrl> >(null);
        }


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
        // 考察异步任务。这里记录下任务完成时间
        long start = System.currentTimeMillis();
        Page<TUrl> res = objTUrlRepository.findAll(pageable);
        long end = System.currentTimeMillis();

        logger.info("Do findAll exhaused "+(end-start)+"ms.");

        return res;

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
    @Resource
    private TUrlMybatisDao objTUrlMybatisDao;

    @Override
    public TUrl findByName(String name){
        return objTUrlMybatisDao.findByName(name);
    }
}
