package exam.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import exam.mybatis.model.User;

public interface UserMapper {
	public User selectByID(int id);

	public int insert(User user);

	public int update(User user);

	public int updateById(User user);

	public int delete(int id);

	public int countLikeName(String name);

	public List<User> paramsToMap(Map<String, String> params);

	public List<User> paramsToAnnotation(@Param("name") String name, @Param("address") String address);

	public List<User> paramsToBean(User params);

	public List<User> selectToWhere(User params);
}
