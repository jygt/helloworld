package com.example.helloWorld;

import com.example.helloWorld.activemq.AyMood;
import com.example.helloWorld.activemq.AyMoodProducer;
import com.example.helloWorld.activemq.AyMoodService;
import com.example.helloWorld.mongo.UserDoc;
import com.example.helloWorld.mongo.UserDocService;
import com.example.helloWorld.security.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootTest
class HelloWorldApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void mySqlTest(){
		String sql = "select f_id,f_name ,f_crt_tm,f_mod_tm,f_desc from t_category";

		List<TCategory> cateList = (List<TCategory>) jdbcTemplate.query(
				sql,new RowMapper<TCategory>(){
					@Override
					public TCategory mapRow(ResultSet rs,int rowNum) throws SQLException {
						TCategory cate = new TCategory();

						cate.setF_id(rs.getInt("f_id"));
						cate.setF_crt_tm(rs.getString("f_crt_tm"));
						cate.setF_mod_tm(rs.getString("f_mod_tm"));
						cate.setF_name(rs.getString("f_name"));
						cate.setF_desc(rs.getString("f_desc"));

						return cate;
					}

				}
		);

		System.out.println("Query ok:");

		for(TCategory itm:cateList){
			System.out.println(itm.getF_id()+"\t"+
					itm.getF_name()+"\t"+
					itm.getF_crt_tm()+"\t"+
					itm.getF_mod_tm()+"\t"+
					itm.getF_desc()
			);
		}
	}
	@Resource
	private TUrlRepository objTUrlRepository;
	@Resource
	private TUrlService objTUrlService;
	@Test
	public void testRepository(){
		List<TUrl> urlList = objTUrlRepository.findAll();

		System.out.println("findAll() : " + urlList.size());

		for (TUrl itm : urlList){
			System.out.println(itm.getFName()+"\t"+itm.getF_url());
		}

		// 分页
		Pageable pageable = PageRequest.of(0,10);

		Page<TUrl> urlPageList = objTUrlRepository.findAll(pageable);
		System.out.println(urlPageList.getTotalPages()+"/"+urlPageList.getSize());
		for (TUrl itm : urlPageList){
			System.out.println(itm.getFName()+"\t"+itm.getF_url());
		}




	}
	@Test
	public void testDelete(){
		// delete
		objTUrlRepository.deleteById("9001");
	}
	@Test
	public void testAddRecord_1(){
		// add
		// 未跑成功，总提示f_name为空
		TUrl objUrl = new TUrl();

		objUrl.setId("9001");
		objUrl.setF_cat(2);
		objUrl.setFName("PPT1234");
		objUrl.setF_url("www.sina.com");
		objUrl.setF_crt_tm("2020-01-31 00:00:00");
		objUrl.setF_mod_tm("2020-01-31 01:00:00");
		objUrl.setF_acc("");
		objUrl.setF_passwd("");
		objUrl.setF_desc("");
		objTUrlService.save(objUrl);
	}
	@Test
	public void testAddRecord(){
		// add
		// 未跑成功，总提示f_name为空
		TUrl objUrl = new TUrl();

		objUrl.setId("9001");
		objUrl.setF_cat(1);
		objUrl.setFName("PPT1234");
		objUrl.setF_url("www.sina.com");
		objUrl.setF_crt_tm("2020-01-31 00:00:00");
		objUrl.setF_mod_tm("2020-01-31 01:00:00");
		objUrl.setF_acc("");
		objUrl.setF_passwd("");
		objUrl.setF_desc("");
		objTUrlRepository.save(objUrl);
	}
	///*
	@Test
	public void testRepository_self(){
		List<TUrl> urlList = objTUrlRepository.findByFName("PPT");

		System.out.println("findAll() : " + urlList.size());

		for (TUrl itm : urlList){
			System.out.println(itm.getFName()+"\t"+itm.getF_url());
		}


		urlList = objTUrlRepository.findByFNameLike("P");
		System.out.println("findAll() : " + urlList.size());

		for (TUrl itm : urlList){
			System.out.println(itm.getFName()+"\t"+itm.getF_url());
		}

		List<String >ids = new ArrayList<String>();

		ids.add("1");
		ids.add("13");
		ids.add("10");
		ids.add("12");
		ids.add("11");


		urlList = objTUrlRepository.findByIdIn(ids);
		System.out.println("findAll() : " + urlList.size());

		for (TUrl itm : urlList){
			System.out.println(itm.getFName()+"\t"+itm.getF_url());
		}


	}
	//	 */
	//@Resource
	//private RedisTemplate objRedisTemplate;
	@Resource
	private StringRedisTemplate objStringRedisTemplate;

	@Test
	public void testRedis(){

		//objStringRedisTemplate.opsForValue().set("a","abcd");

		String name = (String) objStringRedisTemplate.opsForValue().get("a");
		System.out.println(name);

		objStringRedisTemplate.delete("b");

		objStringRedisTemplate.opsForValue().set("b","hello");

		name = (String) objStringRedisTemplate.opsForValue().get("b");
		System.out.println(name);

		objStringRedisTemplate.opsForList().leftPush("al","abcd");
		objStringRedisTemplate.opsForSet().add("as","abcf");
		objStringRedisTemplate.opsForZSet().add("azs","hello",100);
		objStringRedisTemplate.opsForHash().put("ah","key1","vale");

	}


	@Test
	public void testMyBatis(){
		TUrl objTUrl = objTUrlService.findByName("GIT");
		logger.info("GIT" + ":" + objTUrl.getF_url());
	}

	@Resource
	private AyMoodService objAyMoodService;

	@Test
	public void testAyMood(){
		AyMood objAyMood = new AyMood();

		objAyMood.setId("1");
		objAyMood.setUserId("1");
		objAyMood.setContent("This is the first shuoshuo; 这是第一条说说");
		objAyMood.setPraiseNum(0);
		objAyMood.setPublishTime(new Date());

		// 插入数据到数据库
		AyMood objRes = objAyMoodService.save(objAyMood);

	}

	@Resource
	private AyMoodProducer objAyMoodProducer;

	@Test
	public void testActiveMQ(){
		Destination dest = new ActiveMQQueue("ay.queue");
		objAyMoodProducer.sendMessage(dest,"hello mq !!! 你好，队列啊！！");
	}

	@Test
	public void testActiveMQ2AyMood(){
		AyMood objAyMood = new AyMood();

		objAyMood.setId("3");
		objAyMood.setUserId("3");
		objAyMood.setContent("This is the first shuoshuo; 这是第一条说说");
		objAyMood.setPraiseNum(0);
		objAyMood.setPublishTime(new Date());

		// 插入数据到数据库
		String res  = objAyMoodService.asynSave(objAyMood);
		logger.info("Async deploy note : "+ res);
	}

	// sync vs async
	@Test
	public void testTUrlFindAllAsync() throws InterruptedException {
		long starttime = System.currentTimeMillis();
		logger.info("01 call findAllAsync :");
		Future<List<TUrl>> objListUrl_01 = objTUrlService.findAllAsync();
		logger.info("02 call findAllAsync :");
		Future<List<TUrl>> objListUrl_02 = objTUrlService.findAllAsync();
		logger.info("03 call findAllAsync :");
		Future<List<TUrl>> objListUrl_03 = objTUrlService.findAllAsync();
		while(true){
			if(objListUrl_01.isDone() && objListUrl_02.isDone() && objListUrl_03.isDone())
				break;
			else
				Thread.sleep(10);
		}
		long endtime = System.currentTimeMillis();
		logger.info("Aysnc Total Exhaused :"+(endtime-starttime) + "ms");




	}
	@Test

	public void testTUrlFindAll() throws InterruptedException {
		long starttime = System.currentTimeMillis();
		logger.info("01 call findAll :");
		List<TUrl> objList_01 = objTUrlService.findAll();
		logger.info("02 call findAll :");
		List<TUrl> objList_02 = objTUrlService.findAll();
		logger.info("03 call findAll :");
		List<TUrl> objList_03 = objTUrlService.findAll();
		long endtime = System.currentTimeMillis();
		logger.info("Total Exhaused :"+(endtime-starttime) + "ms");



	}

	@Resource
	private UserDocService objUserDocService;

	@Test
	public void testMongo(){
		UserDoc objUserDoc = new UserDoc();

		objUserDoc.setId("2");
		objUserDoc.setName("yangchengfei");
		objUserDoc.setAge("3");
		objUserDoc.setFile("1.doc");

		objUserDocService.save(objUserDoc);

		logger.info("保存成功");
	}

}
