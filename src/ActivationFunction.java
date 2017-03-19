/**
 * Created by User on 019 19.03.17.
 */
public class ActivationFunction {

    public static double getLinearValue(double x){
        return  x;
    }

    public static double getSigmoidValue(double x){
        return 1/(1+Math.exp(-x));
    }

    static double getDerivativeBySigmFunction(double value) {
        return (1 - Math.pow(Math.tanh(value), 2));
    }

    public static double getHyperThValue(double x){
        return (Math.exp(2*x)-1)/(Math.exp(2*x)+1);
    }

    private double getDerivativeByHyperFunction(double value) {
        double countedValue = getSigmoidValue(value);
        return countedValue * (1 - countedValue);
    }
}
