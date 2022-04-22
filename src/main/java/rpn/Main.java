package rpn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String arquivoPath = "ops.txt";
        List<Token> tokenStack = new ArrayList<Token>();

        try {
            File myObj = new File(arquivoPath);
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Token tolken = scanner(data);
                tokenStack.add(tolken);

                System.out.println(tolken.toString());
            }

            while (tokenStack.size() != 1){
                Token data = tokenStack.remove(0);
                if (data.type == TokenType.NUM){
                    int numA = Integer.parseInt(data.lexeme);
                    int numB = Integer.parseInt(tokenStack.remove(0).lexeme);

                    String op = tokenStack.remove(0).lexeme;

                    int resp = solve(numA, numB, op);

                    tokenStack.add(0, new Token(TokenType.NUM, String.valueOf(resp)));
                }
                else{
                    throw new Exception("Error: not a number: " + data.lexeme);
                }
            }

            System.out.println(tokenStack.remove(0).lexeme);
            myReader.close();

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    private static boolean isNumeric(String str) { return str.matches("\\d+"); }

    private static boolean isop(String str) { return str.matches("\\+|\\*|-|/"); }

    private static int solve(int numA, int numB, String op) throws Exception {
        if (op.equals("+")){ return numA + numB; }
        if (op.equals("-")){ return numA - numB; }
        if (op.equals("*")){ return numA * numB; }
        if (op.equals("/")){ return numA / numB; }

        throw new Exception("Operacao invalida");
    }

    private static Token scanner(String data) throws Exception {
        if (isNumeric(data)) {
            return new Token(TokenType.NUM, data);
        }
        if (isop(data)){
            if (data.equals("+")){ return new Token(TokenType.PLUS, data); }
            if (data.equals("-")){ return new Token(TokenType.MINUS, data); }
            if (data.equals("*")){ return new Token(TokenType.STAR, data); }
            if (data.equals("/")){ return new Token(TokenType.SLASH, data); }
        }

        throw new Exception ("Error: Unexpected character:" + data);
    }
}