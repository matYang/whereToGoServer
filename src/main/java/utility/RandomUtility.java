package utility;

import org.apache.commons.lang3.RandomStringUtils;

import service.DaoService;

public class RandomUtility {

	public synchronized static String ramId() {
		String ram = RandomStringUtils.randomAlphanumeric(DaoService.IDLENGTH);
		return ram;
	}

}
