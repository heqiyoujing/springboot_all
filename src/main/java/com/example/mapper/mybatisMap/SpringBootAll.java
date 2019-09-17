package com.example.mapper.mybatisMap;

import com.example.mapper.mybatisMap.cache.TimeTaskLongTimeHashMap;
import com.example.mapper.mybatisMap.library.JobPool;
import com.example.mapper.mybatisMap.library.LimitQueue;
import com.example.mapper.mybatisMap.rabbitMQ.RabbitMQ;
import com.example.mapper.mybatisMap.rabbitMQ.RabbitMQConfig;
import com.example.mapper.mybatisMap.redis.redisPool.RedisConnPool;
import com.example.mapper.mybatisMap.redis.redisPool.RedisConnPoolConfig;
import com.example.mapper.mybatisMap.tool.MybatisMysql;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeoutException;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class SpringBootAll {

	public static MybatisMysql mybatisMysqlPool;
	private static String env = "local";
	public static SqlSessionFactory sqlSessionFactory;
	private static LimitQueue<Long> limitQueue;
	public static RedisConnPoolConfig redisConfig;
	private static RabbitMQConfig mqConfig;

	private static CombinedConfiguration configuration = new CombinedConfiguration();

	private static Logger logger = LoggerFactory.getLogger(SpringBootAll.class.getName());

	//读取配置信息
	public void loadConfiguration( String filename ) throws Exception{
		try {
			configuration.addConfiguration(new XMLConfiguration( filename ));
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.warn( "Configuuration File:[%s] is not exist!\tMessage:[%s]", filename, e.getMessage() );
		}
	}

	public static void main(String[] args) throws Exception{
		new  SpringBootAll().init();
		/*args = new String[1];
		args[0] = "--spring.profiles.active=alpha";*/
		SpringApplication.run(SpringBootAll.class, args);
	}

	public void init() throws Exception{
		//初始化数据库-主从数据库
		initMysql();
		//初始化数据库-单个数据库
		initMySql();
		//初始化发事件线程池
		new JobPool().initPool(5);
		limitQueue = new LimitQueue<Long>(1000);
		//清理缓存concurrentHashMap
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimeTaskLongTimeHashMap(), 1000*60*30, 1000*60*30);
		//初始化redis
		loadRedisConfig();
		RedisConnPool.getInstance().init(redisConfig);
		//初始化rabbitMQ
		/*loadRabbitMQConfig();
		RabbitMQ.getInstance().init(mqConfig);*/
	}

	/**
	 * 主从数据库
	 */
	private void initMysql() {
		MybatisMysql mysqlPool = MybatisMysql.getInstance();
		String resource = String.format("mybatis-config-%s.xml", SpringBootAll.env);
		Reader masterReader = null;
		Reader slaveReader = null;
		try {
			masterReader = Resources.getResourceAsReader(resource);
			slaveReader = Resources.getResourceAsReader(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			//设置主库连接池
			mysqlPool.setMasterPool(builder.build(masterReader, "master"));
			List<SqlSessionFactory> slavePoolList = new ArrayList<SqlSessionFactory>();
			slavePoolList.add(builder.build(slaveReader, "slave"));
			//从库连接池
			mysqlPool.setSlavePool(slavePoolList);
			SpringBootAll.mybatisMysqlPool = mysqlPool;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 单个数据库
	 */
	private void initMySql(){
		try{
			String resource = String.format("mybatis-config-%s.xml", SpringBootAll.env);
			Reader reader = Resources.getResourceAsReader(resource);
			SpringBootAll.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return limitQueue
	 */
	public static LimitQueue<Long> getLimitQueue(){
		return SpringBootAll.limitQueue;
	}
	//加载redis配置
	private void loadRedisConfig(){
		redisConfig = RedisConnPoolConfig.getInstance();
		redisConfig.setHost("192.168.16.107");
		redisConfig.setPort(6379);
		redisConfig.setPassword("123456");
		redisConfig.setMaxTotal(8);
		redisConfig.setMaxIdle(8);
		redisConfig.setMinIdle(0);
		redisConfig.setMaxWaitMillis(-1);
		redisConfig.setTimeBetweenEvictionRunsMillis(1800000);
		redisConfig.setTimeout(300);
	}
	//加载mqrabbit配置
	/*private void loadRabbitMQConfig() throws IOException, TimeoutException {
		mqConfig = RabbitMQConfig.getInstance();
		mqConfig.setHost("localhost");
		mqConfig.setUser("guest");
		mqConfig.setPassword("guest");
		mqConfig.setPort(5672);
		mqConfig.setVirtual_host("");
	}*/
}
