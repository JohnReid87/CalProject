package cal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class cal {

	public Calendar c = Calendar.getInstance();
	public int i = 0;
	public int a;
public String day;
	public void setDate() {
		DateFormat curYear = new SimpleDateFormat("yyyy");
		DateFormat curMonth = new SimpleDateFormat("MM");
		DateFormat curDay = new SimpleDateFormat("dd");
		DateFormat curHour = new SimpleDateFormat("HH");
		DateFormat curMin = new SimpleDateFormat("mm");
		DateFormat curSec = new SimpleDateFormat("ss");

		System.out.println("\n Year: " + curYear.format(c.getTime())
				+ "\n Month: " + curMonth.format(c.getTime()) + "\n Day: "
				+ curDay.format(c.getTime()) + "\n Hour: "
				+ curHour.format(c.getTime()) + "\n Minute: "
				+ curMin.format(c.getTime()) + "\n Second: "
				+ curSec.format(c.getTime()));
		
		c.setFirstDayOfWeek(Calendar.FRIDAY);
		a = c.getFirstDayOfWeek();
	System.out.println("i is " + a);
	day = c.getDisplayName(a , Calendar.LONG, Locale.ENGLISH);


		//	a = c.getDisplayName(, Calendar.LONG, Locale.ENGLISH);
//		System.out.println("i is " + a);
//		a = c.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.ENGLISH);
//		System.out.println("i is " + a);
//		a = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
//		System.out.println("i is " + a);
//		i = Calendar.DAY_OF_WEEK;
//		System.out.println("i is " + i);
//		i = Calendar.ALL_STYLES;
//		System.out.println("i is " + i);

	}

}
