package Сalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Calculation {
    public static boolean[] rome_state = {false, false};
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        System.out.println(operation);
        scanner.close();
        DividingOperation(operation);
    }
    public static String ConvertToArabe(String number){
        String [] rome = {"I","II","III","IV","V","VI","VII","VIII","IX", "X"};
        String [] arabe = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < 10; i++){
            if(rome[i].equals(number)){
                if(!rome_state[0])
                    rome_state[0] = true;
                else
                    rome_state[1] = true;
                return arabe[i];
            }
        }
        return number;
    }
    public static String ConvertToRome(String number){
        String [] rome = {"I","II","III","IV","V","VI","VII","VIII","IX", "X", "XL", "L", "XC", "C"};
        String [] arabe = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "40", "50", "90", "100"};
        StringBuilder helper = new StringBuilder();
        if(Integer.parseInt(number) > 0 && Integer.parseInt(number) < 11){
            for (int i = 0; i < 14; i++){
                if(arabe[i].equals(number)){
                    return rome[i];
                }
            }
        }else if(Integer.parseInt(number) > 10){
            for (int i = 0; i < 10; i++){
                if(number.endsWith(arabe[i])){
                    return rome[9] + rome[i];
                }else if(number.equals("20")){
                    return rome[9] + rome[9];
                }
            }
        }

        return number;
    }
    public static void DividingOperation(String operation) throws Exception {
        String[] numbers = operation.split(" ");
        if (numbers.length > 3)
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        else if(numbers.length < 3)
            throw new Exception("строка не является математической операцией");
        int number_1 = 0;
        int number_2 = 0;
        try {
            number_1 = Integer.parseInt(numbers[0]);
            number_2 = Integer.parseInt(numbers[2]);
        }catch(NumberFormatException e){
            number_1 = Integer.parseInt(ConvertToArabe(numbers[0]));
            number_2 = Integer.parseInt(ConvertToArabe(numbers[2]));
        }
        if(rome_state[0] != rome_state[1]){
            throw new Exception("используются одновременно разные системы счисления");
        }
        switch (numbers[1]) {
            case "+" -> {
                if (!rome_state[0] && !rome_state[1])
                    System.out.println(number_1 + number_2);
                else
                    System.out.println(ConvertToRome(String.valueOf(number_1 + number_2)));
            }
            case "-" -> {
                if (!rome_state[0] && !rome_state[1])
                    System.out.println(number_1 - number_2);
                else
                    throw new Exception("в римской системе нет отрицательных чисел");
            }
            case "/" -> {
                if (!rome_state[0] && !rome_state[1])
                    System.out.println(number_1 / number_2);
                else if (number_1 / number_2 > 0)
                    System.out.println(ConvertToRome(String.valueOf(number_1 / number_2)));
                else
                    throw new Exception("в римской системе нет отрицательных чисел и числа 0");
            }
            case "*" -> {
                if (!rome_state[0] && !rome_state[1])
                    System.out.println(number_1 * number_2);
                else if (number_1 / number_2 > 0)
                    System.out.println(ConvertToRome(String.valueOf(number_1 * number_2)));
            }
        }

    }
}
