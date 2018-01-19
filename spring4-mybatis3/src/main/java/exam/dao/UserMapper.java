package exam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import com.jarvis.cache.type.CacheOpType;

import exam.pojo.User;

@Repository()
public interface UserMapper {
	static final String CACHE_NAME = "user_";
	static final int expire = 600;

	@Cache(expire = expire, autoload = true, key = "'" + CACHE_NAME + "'+#args[0]", condition = "#args[0]>0")
	public User selectByID(int id);

	//@Cache(expire = expire, key = "'" + CACHE_NAME + "'+#args[0].id", opType = CacheOpType.WRITE)
	@Cache(expire=expire, key="'" + CACHE_NAME + "'+#retVal.id", opType=CacheOpType.WRITE)
	public int insert(User user);

	@CacheDelete({ @CacheDeleteKey(value = "'" + CACHE_NAME + "'+#args[0].id", condition = "null != #args[0]") })
	public int update(User user);

	public int updateById(User user);

	public int delete(int id);

	public int countLikeName(String name);

	public List<User> paramsToMap(Map<String, String> params);

	public List<User> paramsToAnnotation(@Param("userName") String userName, @Param("userAddress") String userAddress);

	public List<User> paramsToBean(User params);

	public List<User> selectToWhere(User params);
}
