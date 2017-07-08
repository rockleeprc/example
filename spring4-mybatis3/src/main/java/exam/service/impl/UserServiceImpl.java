package exam.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.dao.UserMapper;
import exam.pojo.User;
import exam.service.IUserService;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Autowired
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
	public int countLikeName(String name) {
		return userMapper.countLikeName(name);
	}

	@Override
	public List<User> paramsToMap(Map<String, String> params) {
		return userMapper.paramsToMap(params);
	}

	@Override
	public List<User> paramsToAnnotation(String userName, String userAddress) {
		return userMapper.paramsToAnnotation(userName, userAddress);
	}

	@Override
	public List<User> paramsToBean(User params) {
		return userMapper.paramsToBean(params);
	}

	@Override
	public List<User> selectToWhere(User params) {
		return userMapper.selectToWhere(params);
	}

}
