import java.text.DateFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Calendar;

public class Main
{

    public static void main(String[] args)
    {
        ///1
        Calendar date = Calendar.getInstance();

        ///2
        System.out.println("Year: " + date.get(Calendar.YEAR));
        System.out.println("Month: " + date.get(Calendar.MONTH));
        System.out.println("Day : " + date.get(Calendar.DATE));
        System.out.println("Hour: " + date.get(Calendar.HOUR));
        System.out.println("Minute: " + date.get(Calendar.MINUTE));

        ///3
        System.out.println("Maximum year: " + date.getActualMaximum(Calendar.YEAR));
        System.out.println("Maximum month: " + date.getActualMaximum(Calendar.MONTH));
        System.out.println("Maximum week of year : " + date.getActualMaximum(Calendar.WEEK_OF_YEAR));
        System.out.println("Maximum week of month : " + date.getActualMaximum(Calendar.WEEK_OF_MONTH));
        System.out.println("Maximum date : " + date.getActualMaximum(Calendar.DATE) + "." + date.getActualMaximum(Calendar.MONTH) + "."+date.getActualMaximum(Calendar.YEAR));

        ///4
        System.out.println("Minimum year: " + date.getActualMinimum(Calendar.YEAR));
        System.out.println("Minimum month: " + date.getActualMinimum(Calendar.MONTH));
        System.out.println("Minimum week of year : " + date.getActualMinimum(Calendar.WEEK_OF_YEAR));
        System.out.println("Minimum week of month : " + date.getActualMinimum(Calendar.WEEK_OF_MONTH));
        System.out.println("Minimum date : " + date.getActualMinimum(Calendar.DATE)+"." + date.getActualMinimum(Calendar.MONTH) + "."+date.getActualMinimum(Calendar.YEAR));

        //5
        Calendar ny = Calendar.getInstance();
        ny.setTimeZone(TimeZone.getTimeZone("New York"));
        System.out.println("Time in New York: " + ny.get(Calendar.HOUR)+":" + ny.get(Calendar.MINUTE) + ":" + ny.get(Calendar.SECOND));

        ///6
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current date: " + currentDate);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current time: " + currentTime);

        ///7
        System.out.println("Last day of month: " + date.getActualMaximum(Calendar.DAY_OF_MONTH));

        ///8
        Calendar lastDay = Calendar.getInstance();
        lastDay.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("Last date of the month: " + lastDay.getTime());

        ///9
        Calendar date2 = Calendar.getInstance();
        date2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("First day of the week: " + date2.getTime());
        for (int i = 0; i<6; i++)
        {
            date2.add(Calendar.DATE, 1);
        }
        System.out.println("Last day of the week: " + date2.getTime());

        ///10
        Calendar lastDay2 = Calendar.getInstance();
        Calendar firstDay = Calendar.getInstance();
        lastDay2.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        firstDay.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
        String[] daysList = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        System.out.println("First day of the month is " + daysList[firstDay.get(Calendar.DAY_OF_WEEK)-1]);
        System.out.println("Last day of the month is " + daysList[lastDay2.get(Calendar.DAY_OF_WEEK)-1]);

        ///11
        System.out.println("Number of days of the month: " + date.getActualMaximum(Calendar.DAY_OF_MONTH));

        ///12
        DateFormatSymbols dni = new DateFormatSymbols(new Locale("pl"));
        String[] weekdays = dni.getWeekdays();
        for (String day : weekdays)
        {
            System.out.println(day + "\n");
        }

        ///13
        Calendar data3 = Calendar.getInstance();
        System.out.println("The day of the week of this date is " + daysList[data3.get(Calendar.DAY_OF_WEEK)-1]);

        ///14 back in 6???

        ///15
        LocalTime in5 = currentTime.plusHours(5);
        System.out.println("Time in 5 hours will be: " + in5);

        ///16
        Calendar data6 = Calendar.getInstance();
        data6.add(Calendar.WEEK_OF_MONTH, 2);
        System.out.println("The date after 2 weeks is " + data6.getTime());

        ///16 v2
        Calendar data8 = Calendar.getInstance();
        for (int j = 0; j <14; j++)
        {
            data8.add(Calendar.DATE, 1);
        }
        System.out.println("The date after 2 weeks: " + data8.getTime());

        ///17
        Calendar data4 = Calendar.getInstance();
        data4.add(Calendar.YEAR, 1);
        System.out.println("Date after 1 year: " + data4.getTime());
        data4.add(Calendar.YEAR, -2);
        System.out.println("Date 1 year ago: " + data4.getTime());

        ///18
        int rok = date.get(Calendar.YEAR);
        if(rok%4==0)
        {
            System.out.println("it's a leap year");
        }
        else {
            System.out.println("it's not a leap year");
        }
        Calendar nowa = Calendar.getInstance();
        nowa.set(2054,0,15);
        System.out.println("The difference in years: "+Math.abs((date.get(Calendar.YEAR)-nowa.get(Calendar.YEAR))));
        System.out.println("The difference in months: "+Math.abs((date.get(Calendar.MONTH)-nowa.get(Calendar.MONTH))));


        ///20
        Instant timestamp = Instant.now();
        System.out.println("Current timestamp: " + timestamp);

        ///21
        ZoneId.SHORT_IDS.keySet().
                stream().forEach(zoneKey ->System.out.println(" " + ZoneId.of( ZoneId.SHORT_IDS.get( zoneKey ) ) + ": " + LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get( zoneKey ) ) ) ) );

        ///22
        Calendar data5 = Calendar.getInstance();
        data5.add(Calendar.DATE, 10);
        System.out.println("Date in 10 days: "+data5.getTime());

        ///23
        String[] months = new DateFormatSymbols(new Locale("en")).getMonths();
        System.out.println("months to go:");
        for(int miesiacteraz = date.get(Calendar.MONTH); miesiacteraz<12; miesiacteraz++)
        {
            String miesiaczostaly = months[miesiacteraz];
            System.out.println(miesiaczostaly);
        }

        ///24
        System.out.println(currentDate);
        System.out.println(currentDate.format(DateTimeFormatter.ofPattern("d::MMM::yyyy")));
        LocalDateTime datazczasem = LocalDateTime.now();
        System.out.println(datazczasem);
        System.out.println(datazczasem.format(DateTimeFormatter.ofPattern("d::MMM::yyyy HH::mm::ss")));
        System.out.println(timestamp);
        LocalDateTime parsed = LocalDateTime.parse("17::lut::2004 10::45::43", DateTimeFormatter.ofPattern("d::MMM::yyyy HH::mm::ss"));
        System.out.println(parsed);
    }
}