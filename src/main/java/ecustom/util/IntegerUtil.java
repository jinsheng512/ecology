package ecustom.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created by Administrator on 2017-6-10.
 */
public class IntegerUtil {

	public static int getRandomInt(int max) {
		Random r = new Random();
		int i = r.nextInt(max);
		return i;
	}
}
