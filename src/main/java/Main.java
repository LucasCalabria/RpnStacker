import java.io.File;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String arquivoPath = "ops.txt";
        Stack<Integer> pilha = new Stack<>();

        try {
            File myObj = new File(arquivoPath);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (isNumeric(data)){
                    pilha.push(Integer.parseInt(data));
                }
                else{
                    int numB = pilha.pop();
                    int numA = pilha.pop();
                    pilha.push(solve(numA,numB,data));
                }
            }

            System.out.println(pilha.pop());
            myReader.close();

        } catch (Exception er) {
            System.out.println("Erro!");
            er.printStackTrace();
        }
    }

    private static boolean isNumeric(String str) { return str.matches("\\d+"); }

    private static int solve(int numA, int numB, String op) throws Exception {
        if (op.equals("+")){ return numA + numB; }
        if (op.equals("-")){ return numA - numB; }
        if (op.equals("*")){ return numA * numB; }
        if (op.equals("/")){ return numA / numB; }

        throw new Exception("Operacao invalida");
    }
}
