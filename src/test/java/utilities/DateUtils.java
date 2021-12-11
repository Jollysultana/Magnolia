package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String returnNextMonth(){
        // Creating Date object
        Date dNow = new Date();

        // Creating Calendar object for Gregorian Calendar
        Calendar calendar = new GregorianCalendar();

        // Set Calendar Date to Current Date
        calendar.setTime(dNow);

        //Create object of SimpleDateFormat
        // Defining Date Format to - example: (Dec-2021)
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-YYYY");

        // Incrementing the current month
        calendar.add(Calendar.MONTH,1);

        // Generating the date based on the specified format
        String date = sdf.format(calendar.getTime());

        return date;
    }
}
