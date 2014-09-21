package utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtility {

	public static final int IDLENGTH = 6;

	public synchronized static String ramId() {
		String ram = RandomStringUtils.randomAlphanumeric(IDLENGTH);
		return ram;
	}

}
