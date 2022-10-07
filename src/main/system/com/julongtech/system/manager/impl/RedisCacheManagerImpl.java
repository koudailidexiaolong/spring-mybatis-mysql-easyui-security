package com.julongtech.system.manager.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.julongtech.system.manager.RedisCacheManager;

import redis.clients.jedis.Jedis;


/**
 * 缓存模块
 * @author julong
 * @date 2017-12-12 上午8:47:56
 */
@Service
@Scope("singleton")
public class RedisCacheManagerImpl  implements RedisCacheManager{

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManagerImpl.class);
	//保存目前缓存的对象
	public static Map<String,String> maps = new Hashtable<String,String>();

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 缓存一个对象 
	 * @param key 键值对关系
	 * @param obj
	 * @return ValueOperations<String,Object>
	 * @author julong
	 * @date 2017-12-8 上午9:33:21
	 */
	@Override
	public boolean setCacheObject(String key,Object obj){
		logger.debug("缓存模块-缓存一个对象");
		boolean connection  = this.connection();
		if(connection){
			this.redisTemplate.opsForValue().set(key,obj);
			return true;
		}
		return false;
	}

	/**
	 * 从缓存中取值
	 * @param key 缓存的编号
	 * @return
	 * @author julong
	 * @date 2017-12-8 上午9:35:58
	 */
	@Override
	public Object getCacheObject(String key){
		logger.debug("缓存模块-获取缓存一个对象{}",key);
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.opsForValue().get(key);
		}
		return null;
	}

	/**
	 * 获取key的失效时间
	 * @param key
	 * @return long
	 * @author julong
	 * @date 2019年3月18日 上午10:09:35
	 */
	public long getExpire(String key){
		logger.debug("缓存模块-设置缓存时间{}",key);
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.getExpire(key,TimeUnit.SECONDS);
		}
		return 0;
	}
	
	/**
	 * 设置缓存失效时间
	 * @param key
	 * @param timeout
	 * @return
	 * @author julong
	 * @date 2019年3月18日 上午10:15:43
	 */
	public boolean setExpire(String key ,long timeout){
		logger.debug("缓存模块-设置缓存时间{}",key);
		boolean connection  = this.connection();
		if(connection){
			if(timeout >0){
				this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 缓存集合list 存储 实现右插
	 * @param key
	 * @param dataList
	 * @return
	 * @author julong
	 * @throws IOException 
	 * @date 2017-12-12 下午2:50:05
	 */
	@Override
	public long setCacheList(String key,List<? extends Object> dataList){
		long count = 0;
		logger.debug("缓存模块-存储一个集合");
		boolean connection  = this.connection();
		if(connection){
			if(null != dataList){
				for(int i = 0; i < dataList.size() ; i ++){
					count = this.redisTemplate.opsForList().rightPush(key,dataList.get(i));
				}
			}
		}
		return count;
	}

	/**
	 * 缓存集合 list 取值 实现左取
	 * @param key
	 * @return List<? extends Object>
	 * @author julong
	 * @date 2017-12-8 上午9:54:57
	 */
	@Override
	public List<? extends Object> getCacheList(String key){
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.opsForList().range(key, 0, -1);
		}
		return null;
	}
	
	/**
	 * 缓存对象到list 存储 实现右插
	 * @param key
	 * @param dataList
	 * @return
	 * @author julong
	 * @throws IOException 
	 * @date 2017-12-12 下午2:50:05
	 */
	@Override
	public long setCacheObjectToList(String key, Object object) {
		// TODO Auto-generated method stub
		long count = 0;
		logger.debug("缓存模块-存储一个集合");
		boolean connection  = this.connection();
		if(connection){
			count = this.redisTemplate.opsForList().rightPush(key,object);
		}
		return count;
	}

	/**
	 * 判断是否存在此list缓存
	 * @param key
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午10:13:17
	 */
	@Override
	public boolean existList(String key){
		logger.debug("缓存模块-判断缓存是否存在{}",key);
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.opsForList().size(key) > 0;
		}
		return false;
	}

	/**
	 * 获取所有的keys
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午11:46:50
	 */
	@Override
	public Set<String> getKeys(String key){
		logger.debug("缓存模块-获取值{}",key);
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.keys(key);
		}
		return null;
	}

	/**
	 * 获取所有逇key 和 值长度
	 * @param key
	 * @return
	 * @author julong
	 * @date 2018-6-10 下午2:51:27
	 */
	@Override
	public Map<String,String> getKeysLen(String key){
		Map<String,String> maps = new HashMap<String, String>();
		boolean connection  = this.connection();
		if(connection){
			Set<String> set = this.redisTemplate.keys(key);
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String type = iterator.next();
				long size = this.getListSize(type);
				maps.put(type, size+"");
			}
		}
		return maps;
	}
	
	/**
	 * 获取key保存的值的长度
	 * @return
	 * @author julong
	 * @date 2017-12-12 上午11:46:50
	 */
	@Override
	public long getListSize(String key) {
		// TODO Auto-generated method stub
		Long size =  this.redisTemplate.opsForList().size(key);
		logger.debug("缓存模块-根据key 获取长度{}",size);
		return size;
	}

	/**
	 * 缓存map
	 * @param key
	 * @param dataMap
	 * @return
	 * @author julong
	 * @date 2017-12-8 上午10:23:53
	 */
	@Override
	public boolean setCacheMap(String key,Map<String,Object> dataMap){
		logger.debug("缓存模块-缓存到map");
		boolean connection  = this.connection();
		if(connection){
			if(null != dataMap){
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {  
					this.redisTemplate.opsForHash().put(key,entry.getKey(),entry.getValue());
				} 
				return true;
			}
		}
		return false;
	}

	/**
	 * map中取值
	 * @param key
	 * @return
	 * @author julong
	 * @date 2017-12-8 上午10:24:03
	 */
	@Override
	public Map<Object,Object> getCacheMap(String key){
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.opsForHash().entries(key);
		}
		return null;
	}

	/**
	 * 删除单个缓存
	 * @param key
	 * @author julong
	 * @date 2017-12-11 下午8:55:53
	 */
	@Override
	public boolean deleteCache(String key){
		logger.debug("缓存模块-删除一个缓存");
		boolean connection  = this.connection();
		if(connection){
			this.redisTemplate.delete(key);
			return true;
		}
		return false;
	}

	/**
	 * 根据集合删除缓存
	 * @param keys
	 * @author julong
	 * @date 2017-12-11 下午8:56:13
	 */
	@Override
	public boolean deleteCache(Set<String> keys){
		logger.debug("缓存模块-删除多个缓存对象");
		boolean connection  = this.connection();
		if(connection){
			this.redisTemplate.delete(keys);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasKey(String key){
		logger.debug("缓存模块-判断是否存在缓存对象");
		boolean connection  = this.connection();
		if(connection){
			return this.redisTemplate.hasKey(key);
		}
		return false;
	}




	/**
	 * 判断是否连接redis成功
	 * @return
	 * @author julong
	 * @date 2018-5-28 下午5:07:23
	 */
	@Override
	public boolean connection() {
		logger.debug("缓存模块-判断是否连接正常");
		String ping = "";
		try {
			ping = this.redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					// TODO Auto-generated method stub
					return connection.ping();
				}
			});
		} catch (Exception e) {
			logger.error("缓存模块-连接redis发生异常：",e);
			//日志记录
		}
		return "PONG".equals(ping);
	}


	public static void main(String[] args){
		Jedis jedis = new Jedis("192.168.10.222");
		System.out.println("Connection to server sucessfully");
		//查看服务是否运行
		System.out.println("Server is running: "+jedis.ping());
		//查看服务是否运行
		System.out.println("Server is running: "+jedis.ping());
		//		System.out.println("Server is running: "+jedis.get("test"));
		//设置key
		//		jedis.set("julong", "julong");
		System.out.println("Server is running: "+jedis.get("MENU"));

	}

}
