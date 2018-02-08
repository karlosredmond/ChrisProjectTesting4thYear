import org.junit.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * Created by Karl Redmond on 07/02/2018.
 */
public class RedmondKarlTesting {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> normalPeriods;
    ArrayList<Period> calculateChargeDiscountPeriod;
    ArrayList<Period> calculateChargeNormalPeriod;
    BigDecimal b;
    Period p;

    //=========================================Tests for Rate Constructor=============================================
    // Set Period arrays to be used in Rate Constructor, these arrays will be used across most test, unique arrays
    // will be constructed within some individual tests to test boundaries and such.

    @Before
    public void beforeTest(){
        discountPeriods= new ArrayList<Period>(){{
            add(new Period(17,18));
        }};

        normalPeriods= new ArrayList<Period>(){{
            add(new Period(14,17));
            add(new Period(18,19));
        }};

        calculateChargeDiscountPeriod = new ArrayList<Period>(){{
            add(new Period(0,2));
            add(new Period(13,17));
            add(new Period(23,24));
        }};

        calculateChargeNormalPeriod = new ArrayList<Period>(){{
            add(new Period(2,4));
            add(new Period(18,19));
        }};
        b = null;
    }

    // Test 1. Check Constructor works with Kind, as kind will be an enum, invalid entries will be captured at compile time,
    // however a test is provided for completion.

    @org.junit.Test
    public void kindIsValid(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(4),new BigDecimal(3),discountPeriods,normalPeriods);
    }

    //  Test 2. normalRate Greater than 0 boundary check, an Illegal argument is expected here, as discountRate should be less than
    //  normalRate, but also it should be greater than 0, hence the expected exception
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateGreaterThanZero(){
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(1),new BigDecimal(0),discountPeriods,normalPeriods);
    }

    //  Test 3. normalRate Greater than 0, value 10
    //
    @org.junit.Test
    public void normalRateGreaterThanZeroValueEqual_10(){
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(10),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    //  Test 4. normalRate max Value
    //
    @org.junit.Test
    public void normalRateGreaterThanZeroMaxValue(){
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(1000000000),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    // Test 5. Normal Rate < 0
    //          This test should also subsequrently fail on the discount Rate not being positive
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateLessThanZeroMinus1(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(-1),new BigDecimal(-2),discountPeriods,normalPeriods);
    }

    // Test 6. normalRate == 0
    //          This test should also subsequrently fail on the discount Rate not being positive
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateEqualTo0(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(0),new BigDecimal(-1),discountPeriods,normalPeriods);
    }

    // Test 7. normalRate &  discountRate same value
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateAndDiscountRateSameValue(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(2),new BigDecimal(2),discountPeriods,normalPeriods);
    }

    // Test 8. Invalid Entry for normalRate, such as character
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateInvalidCharacter(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal('B'),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 9. normalRate set to null
    @org.junit.Test (expected = NullPointerException.class)
    public void normalRateNull(){
        Rate r = new Rate(CarParkKind.STUDENT, b,new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 10.
    @org.junit.Test
    public void discountRateEqual1(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(3),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    // Test 11. Aribrary Number test for discount
    @org.junit.Test
    public void discountRateArbitraryNumber(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(11),new BigDecimal(10),discountPeriods,normalPeriods);
    }

    // Test 12. discountRate Max Value
    @org.junit.Test
    public void discountRateMaxValue(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(1000000000),new BigDecimal(999999999),discountPeriods,normalPeriods);
    }

    // Test 13. discountRate < 0
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateLessThan0(){
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(100),new BigDecimal(-1),discountPeriods,normalPeriods);
    }

    // Test 14. discountRate = 0
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateEqual0(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(100),new BigDecimal(0),discountPeriods,normalPeriods);
    }

    // Test 15. discountRate Invalid Character
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateInvalidCharacter(){
        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5),new BigDecimal('F'),discountPeriods,normalPeriods);
    }


    // Test 16. discountRate = null
    @org.junit.Test (expected = NullPointerException.class)
    public void discountRateIsNull(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(5),b ,discountPeriods,normalPeriods);
    }

    // Test 17. normalRate > discountRate
    @org.junit.Test
    public void normalRateGreaterThanDiscountRate(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 18. normalRate > discountRate
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateGreaterThanNormalRated(){
        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(2),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 19. discountPeriod max length
    @org.junit.Test
    public void discountPeriodMaxLength(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>() ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>() ;
        int i=0;
        while(i < 24){
            discountPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 20. discountPeriod max length boundary
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountPeriodMaxLengthBoundaryAbove(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>() ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>() ;
        int i=0;
        while(i < 25){
            discountPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 21. discountPeriod max length
    @org.junit.Test
    public void discountPeriodMaxLengthBoundaryUnder(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>() ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>() ;
        int i=0;
        while(i < 23){
            discountPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 22. discountPeriod empty
    @org.junit.Test
    public void discountPeriodEmpty(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>() ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(2,3));
            add(new Period(3,4));
            add(new Period(4,5));
            add(new Period(6,7));
        }} ;

        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 23. discountPeriod does not overlap with normal period
    @org.junit.Test
    public void discountPeriodDoesNotOverlapWithNormalPeriod(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(5,6));
            add(new Period(7,8));
        }} ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(6,7));
        }} ;

        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 24. discountPeriod overlap
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountPeriodOverlap(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(17,19));
            add(new Period(18,20));
        }} ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(2,3));
            add(new Period(3,4));
            add(new Period(4,5));
        }} ;

        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 25. discountPeriod overlaps with normal period
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountPeriodOverlapWithNormalPeriods(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(4,6));
        }} ;
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(5,6));
        }} ;

        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 26. discountPeriod Array has null pointer object
    @org.junit.Test (expected = NullPointerException.class)
    public void discountPeriodNullPointer(){
        p = null;
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(p);
        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        int i=0;
        while(i < 24){
            normalPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }


    // Test 27. normalPeriod lenght Max
    @org.junit.Test
    public void normalPeriodLengthMax(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        int i=0;
        while(i < 24){
            normalPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 28. normalPeriod Length of 23
    @org.junit.Test
    public void normalPeriodLengthOKBoundary(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        int i=0;
        while(i < 23){
            normalPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 29. normalPeriod length of 25
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalPeriodLengthNotOKBoundary(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        int i=0;
        while(i < 25){
            normalPeriods.add(new Period(i,i+1));
            i++;
        }
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 30. normalPeriod empty
    @org.junit.Test
    public void normalPeriodEmpty(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(5,6));
            add(new Period(6,7));

        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 31. normalPeriod does not overlap with discountPeriod
    @org.junit.Test
    public void normalPeriodDoesNotOverlapWithDiscountPeriod(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(5,6));
        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(2,3));
            add(new Period(3,4));
            add(new Period(4,5));
            add(new Period(6,7));
        }};
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 32. normalPeriod overlap
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalPeriodOverlap(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(8,9));
        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(2,4));
            add(new Period(3,4));
        }};
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 33. normalPeriod overlaps with discountPeriod
    @org.junit.Test
    public void normalPeriodOverlapsWithDiscountPeriod(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(5,7));
        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(new Period(2,3));
            add(new Period(3,4));
            add(new Period(4,5));
            add(new Period(6,7));
        }};
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 34. normalPeriod has nul pointer object
    @org.junit.Test (expected = NullPointerException.class)
    public void normalPeriodNullPointer(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            add(new Period(5,7));
        }};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            add(p);
        }};
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    // Test 35. normalPeriod & discountPeriod has empty arrays
    @org.junit.Test
    public void normalPeriodsAndDiscountPeriodsEmpty(){
        ArrayList<Period> discountPeriods = new ArrayList<Period>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }



    //=========================================Tests for calculate(Period)=============================================

    // Test 1. boundary check for discountPeriod lower limit
    @org.junit.Test
    public void discountPeriodsCalculateCharge12am_2amBoundary(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(4),new BigDecimal(1),
                calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        Period p = new Period(0,2);
        assertEquals(BigDecimal.valueOf(2), r.calculate(p));
    }

    //Test 2. Boundary check for discountPeriod upper limit
    @org.junit.Test
    public void discountPeriodsCalculateCharge11pm_12amBoundary(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(4),new BigDecimal(1),
                calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        Period p = new Period(23,24);
        assertEquals(BigDecimal.valueOf(1), r.calculate(p));
    }

    //Test 3. Arbitrary check for discountPeriod
    @org.junit.Test
    public void discountPeriodsCalculateChargeArbitraryEvening(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(4),new BigDecimal(1),
                calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        Period p = new Period(14,17);
        assertEquals(BigDecimal.valueOf(3), r.calculate(p));
    }

    //Test 4. Arbitrary check morning
    @org.junit.Test
    public void discountPeriodsCalculateChargeArbitraryNoCharge(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(4),new BigDecimal(1),
                calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        Period p = new Period(4,6);
        assertEquals(BigDecimal.valueOf(0), r.calculate(p));
    }

    //Test 5. normalPeriods boundary check lower
    @org.junit.Test
    public void normalPeriodsCalculateChargeBoundaryLower(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(0,1);
        assertEquals(BigDecimal.valueOf(3), r.calculate(p));
    }

    //Test 6. normalPeriods boundary check upper
    @org.junit.Test
    public void normalPeriodsCalculateChargeBoundaryUpper(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(23,24);
        assertEquals(BigDecimal.valueOf(3), r.calculate(p));
    }

    //Test 7. normalPeriods Arbitrary
    @org.junit.Test
    public void normalPeriodsCalculateChargeArbitrary(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(14,17);
        assertEquals(BigDecimal.valueOf(9), r.calculate(p));
    }

    //Test 8. normalPeriods no Charge
    @org.junit.Test
    public void normalPeriodsCalculateChargeArbitraryNoCost(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(12,13);
        assertEquals(BigDecimal.valueOf(0), r.calculate(p));
    }

    //Test 9. free and normal period crossover
    @org.junit.Test
    public void calculateChargeFreeAndNormalCrossOver(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(16,18);
        assertEquals(BigDecimal.valueOf(3), r.calculate(p));
    }

    //Test 10. normal and discount period crossover
    @org.junit.Test
    public void calculateChargeNormalAndDiscountCrossOver(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(1,3);
        assertEquals(BigDecimal.valueOf(5), r.calculate(p));
    }

    //Test 11. discount and free period crossover
    @org.junit.Test
    public void calculateChargeDiscountAndFreeCrossOver(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(18,20);
        assertEquals(BigDecimal.valueOf(2), r.calculate(p));
    }

    //Test 12. free and normal and discount period crossover
    @org.junit.Test
    public void calculateChargeFreeAndNormalAndDiscountCrossover(){
        Rate r = new Rate(CarParkKind.STAFF, new BigDecimal(3),new BigDecimal(2),
                calculateChargeNormalPeriod,calculateChargeDiscountPeriod);
        Period p = new Period(16,24);
        assertEquals(BigDecimal.valueOf(8), r.calculate(p));

    }
}

