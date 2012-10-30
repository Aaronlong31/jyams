import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.junit.Test;



/**
 * @author zhanglong
 *	
 * 2012-10-30 
 */
public class JodaTimeTest {

    @Test
    public void test1(){
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.dayOfMonth().get());
        System.out.println(dateTime.dayOfWeek().get());
        System.out.println(dateTime.get(DateTimeFieldType.secondOfMinute()));
        System.out.println(dateTime.getCenturyOfEra());
        System.out.println(dateTime.getEra());
        System.out.println(dateTime.getHourOfDay());
        System.out.println(dateTime.getMillis());
        System.out.println(dateTime.getMillisOfDay());
        System.out.println(dateTime.getMillisOfSecond());
        System.out.println(dateTime.getMinuteOfDay());
        System.out.println(dateTime.getSecondOfMinute());
    }
}
