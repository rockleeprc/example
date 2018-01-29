package exam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import exam.pojo.User;

public interface IUserService {

	public List<User> findAll();

	public User selectByID(int id);

	public int insert(User user);

	public int update(User user);

	public int updateById(User user);

	public int delete(int id);

}
