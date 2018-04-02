
public class ReplaceTempWithQuery {

	private int quantity = 1;
	private int itemPrice = 2;

	public int template() {
		int basePrice = quantity * itemPrice;
		int discountFactor;
		if (basePrice > 1000) {
			discountFactor = 2;
		} else {
			discountFactor = 1;
		}
		return basePrice * discountFactor;
	}

	/**
	 * basePrice、discountFactor声明final，检查是否只被赋值一次<br/>
	 * 提取quantity * itemPrice为basePrice()
	 * 
	 * @return
	 */
	public int t1() {
		final int basePrice = basePrice();
		final int discountFactor;
		if (basePrice > 1000) {
			discountFactor = 2;
		} else {
			discountFactor = 1;
		}
		return basePrice * discountFactor;
	}

	private int basePrice() {
		return quantity * itemPrice;
	}

	/**
	 * 提取discountFactory()
	 * 
	 * @return
	 */
	public int t2() {
		final int result = discountFactory();
		return basePrice() * result;
	}

	private int discountFactory() {
		if (basePrice() > 1000) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * 最后只剩两个函数
	 * 
	 * @return
	 */
	public int t3() {
		return basePrice() * discountFactory();
	}
}
