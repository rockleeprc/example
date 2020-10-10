package cn.kd.repository;

import cn.kd.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> findAllByStatus(int status) {
        String sql = "select account,password from account where status=?";
        Object[] params = new Object[]{status};
        List<Account> accounts = this.jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Account.class));
        return accounts;
    }
}
