import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final double LEARMING_COEF = 0.005;
    public static final double ERROR = 0.01;

    public static void main(String[] args) throws IOException {
        double[] patterns = new double[]{0.1,0.2,0.3};
        double[] answer = new double[]{0.5,0.5,0.5};
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ввеите число входных нейронов");
        int inputCount = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Ввеите число скрытых слоев");
        int hiddenLayersCount = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Ввеите число выходных  нейронов");
        int outputCount = Integer.parseInt(bufferedReader.readLine());
        int hiddenNeurCount = (inputCount+outputCount)/2;
        ActivationFunction activationFunction = new ActivationFunction();
        MLP mlp = new MLP(inputCount,hiddenNeurCount,outputCount,activationFunction,LEARMING_COEF,ERROR);
        mlp.learn(patterns,answer,10);

    }
}
