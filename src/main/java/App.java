import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * Created by rootlet on 29.06.2017.
 */
public class App {

    double learning_rate = 0.1;
    INDArray weights_0_1;
    INDArray weights_1_2;
    INDArray input1;
    INDArray input2;


    public void start() {
        //weights_0_1 = Nd4j.create(new double[] {1.1, 0, 0, 1.1, 1, 1}, new int[] {2, 3});
        weights_0_1 = Nd4j.rand(2, 3);
        weights_1_2 = Nd4j.rand(1, 2);
        input1 = Nd4j.create(new double[] {1, 1, 0}, new int[] {3, 1});
        input2 = Nd4j.create(new double[] {1, 0, 1}, new int[] {3, 1});


        System.out.println("weights_0_1 = " + weights_0_1);
        System.out.println("--------------------------------");
        System.out.println("weights_1_2 = " + weights_1_2);
        System.out.println("--------------------------------");

        predict(input1);
        for (int i = 0; i < 5000; i++) {
            train(input1, 0.1);
            train(input2, 0.9);
        }

        predict(input1);
        predict(input2);

    }

    public INDArray predict(INDArray input) {

        //weights_0_1.muliRowVector(input);
        INDArray output1 = weights_0_1.mmul(input);
        System.out.println("output1 " + output1);
        output1 = Transforms.sigmoid(output1);
        System.out.println("sig output1 " + output1);

        INDArray output2 = weights_1_2.mmul(output1);
        System.out.println("output2 " + output2);
        output2 = Transforms.sigmoid(output2);
        System.out.println("sig output2 " + output2);
        return output2;
    }

    public void train(INDArray input, double expected_predict) {
        INDArray output1 = weights_0_1.mmul(input);
        output1 = Transforms.sigmoid(output1);
        INDArray output2 = weights_1_2.mmul(output1);
        output2 = Transforms.sigmoid(output2);
        System.out.println("output2 "  + output2);
        double actual_predict = output2.getDouble(0,0);
        System.out.println("actual_predict " + actual_predict);

        double error_layer_2 = actual_predict - expected_predict;
        System.out.println("error_layer_2 " + error_layer_2);
        double gradient_layer_2 = actual_predict * (1 - actual_predict);
        System.out.println("gradient_layer_2 " + gradient_layer_2);
        double weights_delta_layer_2 = error_layer_2 * gradient_layer_2;
        System.out.println("weights_delta_layer_2 " + weights_delta_layer_2);
        System.out.println("weights_1_2 " +  weights_1_2);
        weights_1_2 = weights_1_2.sub(output1.mul(weights_delta_layer_2 * learning_rate));
        System.out.println("output1 " + output1);
        System.out.println("weights_1_2 " + weights_1_2);


        INDArray errors_layer_1 = weights_1_2.mul(weights_delta_layer_2);
        weights_1_2 = weights_1_2.transpose();
        System.out.println("errors_layer_1 " + errors_layer_1);
        INDArray gradient_layer_1 = output1.mul(output1.rsub(1)); //надо умножить в рамках колонки
        System.out.println("gradient_layer_1 " + gradient_layer_1);
        INDArray weights_delta_layer_1 = errors_layer_1.mul(gradient_layer_1);
        System.out.println("weights_delta_layer_1 " + weights_delta_layer_1);

        //weights_delta_layer_1 = weights_delta_layer_1.transpose();
        //input = input.transpose();
        //weights_delta_layer_1 = weights_delta_layer_1.mul(learning_rate);
        System.out.println("weights_delta_layer_1 " + weights_delta_layer_1);
        System.out.println("input " + input);
        weights_0_1 = weights_0_1.sub(input.mmul(weights_delta_layer_1.transpose().mul(learning_rate)).transpose());
        System.out.println("weights_0_1 " + weights_0_1);

    }



    public static void main(String[] args) {
        /*JFrame frame = new JFrame("App");
        JPanel panel1 = new JPanel();
        //frame.setContentPane(panel1);
        MyPanel myPanel = new MyPanel();
        frame.setContentPane(myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/
        App app = new App();
        app.start();
    }
}
