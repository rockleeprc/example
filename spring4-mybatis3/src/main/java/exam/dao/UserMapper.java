package exam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jarvis.cache.annotation.Cache;

import exam.pojo.User;

@Repository()
public interface UserMapper {
	static final String cacheName = "user";

	@Cache(expire = 600, autoload = true, key = "'user_mapper_selectByID_'+#args[0]", condition = "#args[0]>0")
	public User selectByID(int id);

	public int insert(User user);

	public int update(User user);

	public int updateById(User user);

	public int delete(int id);

	public int countLikeName(String name);

	public List<User> paramsToMap(Map<String, String> params);

	public List<User> paramsToAnnotation(@Param("userName") String userName, @Param("userAddress") String userAddress);

	public List<User> paramsToBean(User params);

	public List<User> selectToWhere(User params);
}
