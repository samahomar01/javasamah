package departmentStore.promotion;

import java.util.Calendar;
import java.util.Date;

public class DayOfWeek implements Validator {
    public String[] daysOfWeek;

    public DayOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public boolean validate(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String todayString = getDayString(dayOfWeek);
        for (String day : daysOfWeek) {
            if (day.equalsIgnoreCase(todayString)) {
                return true;
            }
        }
        return false;
    }

    private String getDayString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "";
        }
    }

}