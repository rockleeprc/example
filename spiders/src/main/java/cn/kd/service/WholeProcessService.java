package cn.kd.service;

import cn.kd.entity.Account;
import cn.kd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class WholeProcessService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ThreadPoolExecutor threadPool;

    public void invokeWholeProcessTask() {
        List<Account> accounts = this.accountRepository.findAllByStatus(1);
        accounts.forEach(account ->
                this.threadPool.execute(new WholeProcessTask(account.getAccount(), account.getPassword(), this.accountRepository))
        );
    }


}
