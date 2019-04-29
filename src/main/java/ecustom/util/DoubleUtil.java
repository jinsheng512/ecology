package ecustom.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * Created by Administrator on 2017-6-8.
 */
public class DoubleUtil {

	public static double add(Double d1, Double d2) {
		d1 = d1 == null ? 0.0 : d1;
		d2 = d2 == null ? 0.0 : d2;
		return d1 + d2;
	}

	public static double getRandomDouble(int max, int decimal) {
		Random r = new Random();
		double d = r.nextDouble() * max;
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(decimal, RoundingMode.UP).doubleValue();
	}
}
