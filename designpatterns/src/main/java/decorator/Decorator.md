
## 设计原则

1. 类应该对扩展开放，对修改关闭

2. 在不修改现有代码的情况下，使类容易扩展

3. 开-闭原则通常会引入新的抽象层次，增加代码的复杂度

4. 开-闭原则集中在程序设计中最有可能改变的地方

5. 每个地方应用开-闭原则是一种浪费

## 装饰者模式

1. 动态地将责任附加到对象上，比继承更有弹性的替代方案

2. 装饰者和被装饰者必须是一样的类型（继承、实现），因为装饰者必须能取代被装饰者

3. 装饰者模式引入了更对的对象，使用中通常用其它类似于工厂或生成器模式创建

## java.io

### Componet

	public abstract class InputStream implements Closeable

### ConcreateComponet

	public class FileInputStream extends InputStream
	
	public class StringBufferInputStream extends InputStream 
	
	public class ByteArrayInputStream extends InputStream

### Decorator

	public class FilterInputStream extends InputStream

### ConcreateDecorator

	public class PushbackInputStream extends FilterInputStream
	
	public class BufferedInputStream extends FilterInputStream
	
	public class BufferedInputStream extends FilterInputStream
	
	public class LineNumberInputStream extends FilterInputStream



