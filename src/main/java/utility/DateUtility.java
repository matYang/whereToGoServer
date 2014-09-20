package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtility {

	public static final long MILI_SEC = 1000l;
	public static final long MILI_MIN = MILI_SEC * 60;
	public static final long MILI_HOUR = MILI_MIN * 60;
	public static final long MILI_DAY = MILI_HOUR * 24;
	public static final long MILI_WEEK = MILI_DAY * 7;

	public static Calendar curTimeInstance() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	}

	public static long curTime() {
		Calendar c = curTimeInstance();
		return c.getTimeInMillis();
	}

	public static Calendar longToCal(long mili) {
		Calendar c = curTimeInstance();
		c.setTimeInMillis(mili);
		return c;
	}

	public static long calToLong(Calendar c) {
		return c.getTimeInMillis();
	}

	public static int compareday(Calendar cal1, Calendar cal2) {
		if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
			return -1;
		} else if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) < cal2
						.get(Calendar.DAY_OF_YEAR)) {
			return -1;
		} else if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
						.get(Calendar.DAY_OF_YEAR)) {
			return 0;
		} else {
			return 1;
		}

	}

	public static String toReadable(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");
		String dateStr = sdf.format(c.getTime());
		if (dateStr.indexOf("0") == 0) {
			dateStr = dateStr.substring(1, dateStr.length());
		}
		return dateStr;
	}

}
