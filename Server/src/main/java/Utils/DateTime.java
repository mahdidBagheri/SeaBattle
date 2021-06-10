package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTime {

    public String Now(){
        LocalDate Date = LocalDate.now();
        LocalTime Time = LocalTime.now();
        return Date.toString() + " " + Time.getHour() + ":" + Time.getMinute() + ":" + Time.getSecond();
    }


}
