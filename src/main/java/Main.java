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
        double[][] sampling = new double[2][7];
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
        String str = "";
        boolean out=true;
        int i = 0;
        do{
            String read = input.readLine();
            if (read==null){
                out = false;
            }
            else {
                System.out.println(read);
                double[] item = parse(read);
                sampling[i] = item;
                i++;
            }
        }
        while (out);
        mlp.learn(sampling[0],answer,100);// учить предъявляя каждый набор по отдельности?

    }

    public static double[] parse(String s){
        String[] digs = s.split(",");
        double[] res = new double[digs.length];
        for (int i = 0;i<digs.length;i++){
            res[i] = Double.valueOf(digs[i]);
        }
        return res;// TODO дописать парсер и выделять результат классификации в отдельную переменную
    }
}
