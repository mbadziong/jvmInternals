package pl.mbadziong.jvm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleDateFormat extends SimpleDateFormat {

    ThreadLocal<SimpleDateFormat> lockedSimpleDateFormat;

    public MySimpleDateFormat() {
        super();
        lockedSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat();
            }
        };
    }

    public MySimpleDateFormat(String pattern) {
        super(pattern);
        lockedSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(pattern);
            }
        };
    }

    public Date parse(String source) throws ParseException {
        return lockedSimpleDateFormat.get().parse(source);
    }
}
