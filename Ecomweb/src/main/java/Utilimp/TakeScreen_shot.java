package Utilimp;

import java.util.Date;

public class TakeScreen_shot {
	public static String getName() {
		Date d1=new Date();
		String name=d1.toString().replace(' ','_').replace(':', '_');
		return name;
	}

}
