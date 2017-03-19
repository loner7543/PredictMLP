import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final double LEARMING_COEF = 0.005;
    public static final double ERROR = 0.01;

    public static void main(String[] args) throws IOException {
        double[] patterns = new double[]{0.1,0.2,0.3};
        double[] answer = new double[]{0.5,0.5,0.5};
        double[] sampling = new double[100];
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
        FileReader br = new FileReader("D:\\10Semestr\\IIS\\predict\\src\\main\\resources\\input.txt");
        BufferedReader input = new BufferedReader(br);
        while(input.readLine()!=null){
            double a = Double.parseDouble(input.readLine());
            System.out.print(a);
        }
        mlp.learn(patterns,answer,10);
//        Fann fann = new Fann( "/path/to/file" );
//        float[] inputs = new float[]{ -1, 1 };
//        float[] outputs = fann.run( inputs );

    }
}
