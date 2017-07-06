package exam.mybatis.mapper;

import exam.mybatis.model.User;

public interface UserMapper {
	public User selectByID(int id);

	public int insert(User user);

	public int update(User user);

	public int delete(int id);
}
