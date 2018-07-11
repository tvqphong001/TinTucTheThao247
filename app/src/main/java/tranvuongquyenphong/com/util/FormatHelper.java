package tranvuongquyenphong.com.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FormatHelper {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    public static String formatNgay(Date ngay){
        return sdf.format(ngay);
    }
    public static Date formatString(String ngay) throws Exception
    {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngay);
        return date;
    }
    public static Date formatPubDate(String ngay) throws Exception
    {
        Date date = formatter.parse(ngay);
        return date;
    }

    //code pubDate

    private static final String[] DAY_OF_WEEK = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final String[] MONTH = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    /**
     * Parse RSS date format to Date object.
     * Example of RSS date:
     * Sat, 23 Sep 2006 22:25:11 +0000
     */
    public static Date parseDate(String dateString) {
        Date pubDate = null;
        try {
            // Split date string to values
            // 0 = week day
            // 1 = day of month
            // 2 = month
            // 3 = year (could be with either 4 or 2 digits)
            // 4 = time
            // 5 = GMT
            int weekDayIndex = 0;
            int dayOfMonthIndex = 2;
            int monthIndex = 1;
            int yearIndex = 5;
            int timeIndex = 3;
            int gmtIndex = 4;

            String[] values = dateString.split(" ");
            int columnCount = values.length;
            // Wed Aug 29 20:14:27 +0000 2007

            if( columnCount==5 ) {
                // Expected format:
                // 09 Nov 2006 23:18:49 EST
                dayOfMonthIndex = 0;
                monthIndex = 1;
                yearIndex = 2;
                timeIndex = 3;
                gmtIndex = 4;
            } else if( columnCount==7 ) {
                // Expected format:
                // Thu, 19 Jul  2007 00:00:00 N
                yearIndex = 4;
                timeIndex = 5;
                gmtIndex = 6;
            } else if( columnCount<5 || columnCount>6 ) {
                throw new Exception("Invalid date format: " + dateString);
            }

            // Day of month
            int dayOfMonth = Integer.parseInt( values[ dayOfMonthIndex ] );

            // Month
            String[] months =  {
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String monthString = values[ monthIndex ];
            int month=0;
            for(int monthEnumIndex=0; monthEnumIndex<12; monthEnumIndex++) {
                if( monthString.equals( months[ monthEnumIndex ] )) {
                    month = monthEnumIndex;
                }
            }

            // Year
            int year = Integer.parseInt(values[ yearIndex ]);
            if(year<100) {
                year += 2000;
            }

            // Time
            String[] timeValues = values[ timeIndex ].split(":");
            int hours = Integer.parseInt( timeValues[0] );
            int minutes = Integer.parseInt( timeValues[1] );
            int seconds = Integer.parseInt( timeValues[2] );

            pubDate = getCal(dayOfMonth, month, year, hours, minutes, seconds, values[ gmtIndex ]);

        } catch(Exception ex) {
            // TODO: Add exception handling code
            System.err.println("parseRssDate error while converting date string to object: " +
                    dateString + "," + ex.toString());
        } catch(Throwable t) {
            // TODO: Add exception handling code
            System.err.println("parseRssDate error while converting date string to object: " +
                    dateString + "," + t.toString());
        }
        return pubDate;
    }

    /** Get calendar date. **/
    public static Date getCal(int dayOfMonth, int month, int year, int hours,
                              int minutes, int seconds, String timezone) throws Exception {
        // Create calendar object from date values
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone( TimeZone.getTimeZone("GMT"+timezone) );
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);

        return cal.getTime();
    }

    public static String formatHTTPDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone( TimeZone.getTimeZone("GMT+0") );
        cal.setTime(date);
        return
                DAY_OF_WEEK[cal.get(Calendar.DAY_OF_WEEK)-1]+", "+
                        cal.get(Calendar.DAY_OF_MONTH)+" "+
                        MONTH[cal.get(Calendar.MONTH)]+" "+
                        cal.get(Calendar.YEAR)+" "+
                        cal.get(Calendar.HOUR_OF_DAY)+":"+
                        cal.get(Calendar.MINUTE)+":"+
                        cal.get(Calendar.SECOND)+" GMT";
        //Tue%2C+27+Mar+2007+22%3A55%3A48+GMT
    }
}


