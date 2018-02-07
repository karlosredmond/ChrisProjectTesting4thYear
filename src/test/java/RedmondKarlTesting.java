import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by BeckiKarl on 07/02/2018.
 */
public class RedmondKarlTesting {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> normalPeriods;

    //Tests for Rate Constructor
    @Before
    public void beforeTest(){
        discountPeriods= new ArrayList<Period>(){{
            add(new Period(17,18));
        }};
        ArrayList<Period> normalPeriods= new ArrayList<Period>(){{
            add(new Period(14,17));
            add(new Period(18,19));
        }};
    }


    @org.junit.Test
    public void kindValid(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(4),new BigDecimal(3),discountPeriods,normalPeriods);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateLessThanZero(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(-1),new BigDecimal(-1),discountPeriods,normalPeriods);
    }


}

