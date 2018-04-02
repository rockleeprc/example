package exam.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exam.dao.UserMapper;
import exam.pojo.User;
import exam.service.IUserService;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Resource(name="userMapper")  
	private UserMapper userMapper;

	@Override
	public User selectByID(int id) {
		return userMapper.selectByID(id);
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public int updateById(User user) {
		return userMapper.updateById(user);
	}

	@Override
	public int delete(int id) {
		return userMapper.delete(id);
	}

	

	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

}
