package factory.calculate;

/**
 * 简单工厂 ： 用来生产同一等级结构中的任意产品。（不支持拓展增加产品）
 * 
 * 工厂方法 ：用来生产同一等级结构中的固定产品。（支持拓展增加产品）
 * 
 * 抽象工厂 ：用来生产不同产品族的全部产品。（不支持拓展增加产品；支持增加产品族）
 * 
 * @author Administrator
 *
 */
public abstract class Calculator {

	abstract int getResult(int i, int j);
}
