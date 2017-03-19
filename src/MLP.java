/**
 * Created by User on 019 19.03.17.
 * по умолчанию использзовал сигмоиду
 */
public class MLP {
    private static final long serialVersionUID = 1L;
    private int inputCount;
    private double[] input;
    private double[] output;
    private double[] hidden;
    private double[][] inputToHidden;
    private double[][] hiddenToOutput;
    private  ActivationFunction activationFunction;
    private double learningCoefficient;
    private double error;

    public MLP(int inputCount, int hiddenCount, int outputCount,
               ActivationFunction activationFunction, double learningCoefficient,
               double error) {

        this.inputCount = inputCount;
        input = new double[inputCount];
        hidden = new double[hiddenCount];
        output = new double[outputCount];
        inputToHidden = new double[input.length][hidden.length];
        hiddenToOutput = new double[hidden.length][output.length];
        this.activationFunction = activationFunction;
        this.learningCoefficient = learningCoefficient;
        this.error = error;
        intializeRandomWeights();

    }

    public void learn(double[] pattern, double[] answer, int iterationsCount) {
        int count = 0;
        double[] error = new double[hidden.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = pattern[i];
        }
        double gError = 0;
        do {
            count++;
            gError = 0;
            // подаются на вход значения

            // считает значение на выходе
            countOuter(input, false);

            // считается ошибка - разница между полученным на выходе и
            // ожидаемым
            double[] lError = new double[output.length];
            for (int i = 0; i < output.length; i++) {
                lError[i] = (answer[i] - output[i]) * ActivationFunction.getDerivativeBySigmFunction(output[i]);
                gError += Math.abs(lError[i]);
            }

            for (int i = 0; i < hidden.length; i++) {
                for (int j = 0; j < output.length; j++) {
                    error[i] += lError[j] * hiddenToOutput[i][j];
                }
            }
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < hidden.length; j++) {
                    inputToHidden[i][j] += learningCoefficient * error[j]
                            * input[i];
                }
            }
            for (int i = 0; i < hidden.length; i++) {
                for (int j = 0; j < output.length; j++) {
                    hiddenToOutput[i][j] += learningCoefficient * lError[j]
                            * hidden[i];
                }
            }

        } while (gError > this.error && count < iterationsCount);
        System.out.println("Число циклов: " + count);
    }

    public double[] countOuter(double[] input, boolean print) {
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
        }
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = 0;
            for (int j = 0; j < input.length; j++) {
                hidden[i] += input[j] * inputToHidden[j][i];
            }
            // hidden[i] = (hidden[i] > 0.5) ? 1 : 0;
            hidden[i] = ActivationFunction.getSigmoidValue(hidden[i]);
        }

        if (print)
            System.out.println("Output");
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
            for (int j = 0; j < hidden.length; j++) {
                output[i] += hidden[j] * hiddenToOutput[j][i];

            }
            // output[i] = (output[i] > 0.5) ? 1 : 0;
            output[i] =  ActivationFunction.getSigmoidValue(hidden[i]);
            if (print) {

                System.out.print(output[i] + " ");
            }
        }
        if (print)
            System.out.println();

        return output;
    }

    private void intializeRandomWeights() {
        for (int i = 0; i < inputToHidden.length; i++) {
            for (int j = 0; j < inputToHidden[i].length; j++) {
                inputToHidden[i][j] = Math.random() * 0.3 + 0.1;
            }
        }

        for (int i = 0; i < hiddenToOutput.length; i++) {
            for (int j = 0; j < hiddenToOutput[i].length; j++) {
                hiddenToOutput[i][j] = Math.random() * 0.3 + 0.1;
            }
        }
    }

    public double[] getOut() {

        return output;
    }

    public int getInputCount() {
        return inputCount;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input) {
        this.input = input;
    }

    public double[] getHidden() {
        return hidden;
    }

    public void setHidden(double[] hidden) {
        this.hidden = hidden;
    }

    public double[] getOutput() {
        return output;
    }

    public double[][] getInputToHidden() {
        return inputToHidden;
    }

    public void setInputToHidden(double[][] inputToHidden) {
        this.inputToHidden = inputToHidden;
    }

    public double[][] getHiddenToOutput() {
        return hiddenToOutput;
    }

    public void setHiddenToOutput(double[][] hiddenToOutput) {
        this.hiddenToOutput = hiddenToOutput;
    }

    public double getLearningCoefficient() {
        return learningCoefficient;
    }

    public void setLearningCoefficient(double learningCoefficient) {
        this.learningCoefficient = learningCoefficient;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }

}
