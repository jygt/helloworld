package com.example.helloWorld;

import org.junit.jupiter.api.Test;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HelloWorldApplicationTests {

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

}
