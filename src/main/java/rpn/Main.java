package rpn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

                System.out.println(tolken);
            }

            while (tokenStack.size() != 1){
                Token data = tokenStack.remove(0);
                if (data.type == TokenType.NUM){
                    Token numB = tokenStack.remove(0);

                    Token op = tokenStack.remove(0);

                    tokenStack.add(0, solve(data, numB, op));
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

    private static Token solve(Token tokenNumA, Token tokenNumB, Token op) throws Exception {
        String result = "";

        int numA = Integer.parseInt(tokenNumA.lexeme);
        int numB = Integer.parseInt(tokenNumB.lexeme);

        if (op.type.equals(TokenType.PLUS)){  result = String.valueOf(numA + numB); }
        if (op.type.equals(TokenType.MINUS)){ result = String.valueOf(numA - numB); }
        if (op.type.equals(TokenType.STAR)){  result = String.valueOf(numA * numB); }
        if (op.type.equals(TokenType.SLASH)){ result = String.valueOf(numA / numB); }

        return new Token(TokenType.NUM, result);
    }

    private static Token scanner(String data) throws Exception {
        if (isNumeric(data)) {
            return new Token(TokenType.NUM, data);
        }
        if (isop(data)){
            if (data.equals("+")){ return new Token(TokenType.PLUS,  data); }
            if (data.equals("-")){ return new Token(TokenType.MINUS, data); }
            if (data.equals("*")){ return new Token(TokenType.STAR,  data); }
            if (data.equals("/")){ return new Token(TokenType.SLASH, data); }
        }

        throw new Exception ("Error: Unexpected character: " + data);
    }
}
