import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by BeckiKarl on 07/02/2018.
 */
public class Rate {
    CarParkKind kind;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) throws IllegalArgumentException{
        this.kind = kind;
        //Checks If normalRate > 0
        if(normalRate.compareTo(new BigDecimal(0)) < 1){
            throw new IllegalArgumentException();
        }
    }

    public BigDecimal calculate(Period periodStay){
        return new BigDecimal(8);
    }

}
