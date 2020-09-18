package main;//Weronika Wawrzyniak weronika.wawrzyniak98@gmail.com
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Plakatowanie implements Runnable {
    Stack<Integer> stack = new Stack<>();
    int size, currentHeight, posters=0;
    String line;
    String[] buildingMeasurements;
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            size = Integer.valueOf(br.readLine());
            for (int i = 0; i < size; i++) {
                line = br.readLine();
                buildingMeasurements = line.split(" ");
                currentHeight = Integer.valueOf(buildingMeasurements[1]);
                while (stack.size()!=0 && stack.peek()>currentHeight) {
                    stack.pop();
                }
                if (stack.size()==0 || stack.peek()<currentHeight) {
                    stack.push(currentHeight);
                    posters++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(posters);
        }
    }
    public static void main(String[] args) {
        new Thread(new Plakatowanie()).start();
    }
}
