package miipedevelopertest;

import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Ryan Scott
 * Created for my application to the Miipe Quality Solutions Automation Engineer
 * position in Vaughan, Ontario, dated January 6th, 2019. I chose to complete the
 * first four questions requested on the 2018-2019 Miipe Developer Test.
 */
public class MiipeDeveloperTest {


    /*Stock method. Puts a blank line in the console, can overload for more than one.
    * Input: number of blank lines to create (int, optional).
    * Output: none.
    */
    private static void NewLine() {
        System.out.println("");
    }
    private static void NewLine(int j) {
        if (j > 0) {
            for (int i = 0; i < j; i++) {
                System.out.println("");
            }
        }
        else 
            System.out.println("");
    }

    /* Stock method. Gets user input confirmation before proceeding.
    * Input: none.
    * Output: none.
    */    
    private static void EnterKey() {
        NewLine();
        System.out.println("Press the Enter key to continue.");
        try {
            System.in.read();
        }
        catch(Exception e) {}
    }
    
    /* Demonstrating the FizzBuzz question: Counting from 1 to 100, and writing Fizz if
    * number is a multiple of 3, Buzz if a multiple of 5, and FizzBuzz if both.
    * Input: none.
    * Output: none.
    */
    private static void FizzBuzz() {
        NewLine();
        System.out.println("Demonstrating the FizzBuzz question:");
        System.out.println("Counting from 1 to 100, and writing Fizz if number is a multiple of 3,");
        System.out.println("Buzz if a multiple of 5, and FizzBuzz if both.");
        EnterKey();
        for (int i = 1; i <= 100; i++) {
            if ((i % 3 == 0) || (i % 5 == 0)) {
                if (i % 3 == 0)
                    System.out.print("Fizz");
                if (i % 5 == 0)
                    System.out.print("Buzz");
            }
            else {
                System.out.print(i);
            }
            NewLine();
        }
    }
    
    /* Demonstrating the Fibonacci question. Calculates the first 100 Fibonacci numbers (the sum of
    * the previous two numbers, with the first two being 0 and 1.
    * Input: none.
    * Output: none.
    */
    private static void Fibonacci() {
        NewLine();
        System.out.println("Demonstrating the Fibonacci question:");
        System.out.println("Calculating the first 100 Fibonacci numbers (the sum of the previous");
        System.out.println("two numbers, with the first two being 0 and 1).");
        EnterKey();
        BigInteger first = new BigInteger("0"); BigInteger second = new BigInteger("1");
        System.out.println(first);
        System.out.println(second);
        for (int i = 3; i <= 100; i++) {
            BigInteger third = new BigInteger((first.add(second)).toString());
            System.out.println(third);
            first = second; second = third;
        }
    }
    
    /* Demonstrating the ability to determine a calculation from a string. To prevent this from
    * turning into a much more sophisticated question than it need be, only the four basic
    * operations of addition, subtraction, multiplication and division will be used. In addition,
    * only integers will be accepted and calculated, rounded as needed.
    * Input: none.
    * Output: none.
    * 
    * In addition, I did my best to eliminate as much possible user error through the input fields.
    */
    private static void StringCalculation() {
        String validNumbers = "0123456789";
        String validOperations = "+-*/";
        NewLine();
        System.out.println("Demonstrating the ability to determine a calculation from a string:");
        System.out.println("For simplicity's sake, only numeric expressions with no text will");
        System.out.println("be allowed (for example, 7 + 3 * 6 / 5). Whitespace will be trimmed.");
        System.out.println("Only integers are accepted, no decimal places. Answers will be rounded.");
        System.out.println("The order of operations is sequential, and the following operations");
        System.out.println("are allowed:");
        System.out.println("Addition +");
        System.out.println("Subtraction -");
        System.out.println("Multiplication *");
        System.out.println("Division /");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        outerloop:
        while (true) {
            NewLine();
            String input;
            try {
                input = br.readLine();
                input = input.replaceAll("\\s", "");
            }
            catch(Exception e) {
                NewLine();
                System.out.println("Error accepting input, please try again.");
                continue;
            }
            if ("".equals(input)) {
                NewLine();
                System.out.println("No input entered, please try again.");
                continue;
            }
            //Could be turned into a validation method.
            char[] characters = input.toCharArray();
            boolean opMemory = false;
            int j = 0;
            ArrayList<Integer> opIndices = new ArrayList<>();
            for (char c:characters) {
                boolean isNum = validNumbers.contains(String.valueOf(c));
                boolean isOp = validOperations.contains(String.valueOf(c));
                if (!isNum && !isOp) {
                    System.out.println("Invalid character entered: " + String.valueOf(c));
                    System.out.println("Please try again.");
                    continue outerloop;
                }
                else if (isOp) {
                    if (opMemory == true) {
                        System.out.println("Two operators entered in a row.");
                        System.out.println("Please try again.");
                        continue outerloop;
                    }
                    else { opMemory = true; }
                    if (j == characters.length - 1) {
                        System.out.println("Last character of expression cannot be an operator.");
                        System.out.println("Please try again.");
                        continue outerloop;
                    }
                    opIndices.add(j);
                }  
                else { opMemory = false; }
                j++;
            }
            if (opIndices.size() > 0) {
                String firstOperand = input.substring(0, opIndices.get(0));
                
                for (int i = 0; i < opIndices.size(); i++) {
                    String secondOperand = "";
                    if (i+2 > opIndices.size()) {
                        secondOperand = input.substring(opIndices.get(i)+1, input.length());
                    }
                    else {
                        secondOperand = input.substring(opIndices.get(i)+1, opIndices.get(i+1));
                    }
                    switch (input.substring(opIndices.get(i), opIndices.get(i)+1)) {
                        case "+": 
                            firstOperand = Integer.toString(Integer.parseInt(firstOperand) + Integer.parseInt(secondOperand));
                            break;
                        case "-":
                            firstOperand = Integer.toString(Integer.parseInt(firstOperand) - Integer.parseInt(secondOperand));
                            break;
                        case "*":
                            firstOperand = Integer.toString(Integer.parseInt(firstOperand) * Integer.parseInt(secondOperand));
                            break;
                        case "/":
                            firstOperand = Integer.toString(Integer.parseInt(firstOperand) / Integer.parseInt(secondOperand));
                            break;
                    }
                }
                System.out.println("Answer: " + firstOperand);
            }
            else {
                System.out.println("Answer: " + input);     
            } 
            break;
        }
    }
    
    /* Determining a palindrome: an expression that is the same forwards as it is backwards. Note
    * that character sequence is all that matters, the legibility of the reversed phrase will not
    * be determined. Far beyond the scope of this limited application to decide what's English and
    * what's not.
    * Input: none.
    * Output: none.
    *
    * Same as above, I attempted to eliminate the possibility of user error, though to a lesser
    * degree than the above question.
    *
    * Note that when the exported jar file is run from the command line, it has an annoyingly
    * persistent bug of keeping an empty line in the buffer. This bug was not replicable in the
    * Netbeans IDE.
    */
    private static void Palindrome() {
        NewLine();
        System.out.println("Determining a palindrome: an expression that is the same forwards as");
        System.out.println("it is backwards. Note that character sequence is all that matters, the");
        System.out.println("legibility of the reversed phrase will not be determined.");
        NewLine();
        System.out.println("Please enter the string to be tested as a palindrome.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            NewLine();
            String input, filteredInput;
            try {
                input = br.readLine();
                filteredInput = (input.replaceAll("\\s", "")).toLowerCase();
            }
            catch(Exception e) {
                NewLine();
                System.out.println("Error accepting input, please try again.");
                continue;
            }
            if ("".equals(input)) {
                NewLine();
                System.out.println("No input entered, please try again.");
                continue;
            }
            String palindrome = "";
            for (int i = filteredInput.length(); i > 0; i--) {
                palindrome += filteredInput.substring(i-1, i);
            }
            if (filteredInput.equals(palindrome)) 
                System.out.println(input + " is a palindrome with " + palindrome);
            else 
                System.out.println(input + " is not a palindrome with " + palindrome);
            break;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello, whomever may be opening this up.");
        System.out.println("This command line application is to complete the Miipe Developer Test for");
        System.out.println("Ryan Scott's application to the Java Developer position.");
        FizzBuzz();
        EnterKey();
        Fibonacci();
        EnterKey();
        StringCalculation();
        EnterKey();
        Palindrome();
        EnterKey();
        System.out.println("Thank you for taking the time to read Ryan Scott's application.");
        System.out.println("If you have any further questions, you can email me at rscottcodes@gmail.com");
    }
    
}

