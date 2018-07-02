package exam.aop.anno;

import java.lang.annotation.*;

/**
 * 注解本身没有功能，是一种元数据，即解释数据的数据
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
