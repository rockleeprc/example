package exam.aop.anno;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/2.
 */
@Service
public class AnnotationService {

    @Action(name = "注解拦截")
    public void add() {
    }
}
