package mmn11.Q1;
import java.util.Scanner;
import java.util.Random;

class ImpactBool { 
    public static void main(String[] args){    

        Random random = new Random();
        String uniqueNumber = generateUniqueFourDigitNumber(random);

        char[] targetArray = uniqueNumber.toCharArray(); // Convert the target number to a char array

        System.out.println("The program chose: " + uniqueNumber + " as a target number");
       
        int score = 0;
        Scanner scan = new Scanner(System.in); // Create Scanner once outside the loop

        while(score != 4){
            score = 0; 

            System.out.print("Enter a 4-digit number: ");
            String input = scan.nextLine(); // Read input as a string

            if (input.length() != 4) {
                System.out.println("Please enter exactly 4 digits.");
                continue; // Skip to the next iteration of the loop
            }

            char[] inputArray = input.toCharArray(); // Convert the input to a char array

            for(int i = 0; i < inputArray.length; i++){
                char ch = inputArray[i];
                System.out.println("Current char is: " + ch);
                
                if(!Character.isDigit(ch)) {
                    System.out.println("Please enter only digits.");
                    score = -1; // Invalidate score to avoid misleading output
                    break; // Exit the loop if non-digit is found
                }

                if(ch == targetArray[i]) {
                    score++; // Bingo - matching position and digit
                }
            }

            if (score == 4) {
                System.out.println("Congratulations! You've guessed the number: " + input);
            } else if (score >= 0) { // Ensures only valid scores display
                System.out.println("Your score: " + score + " - Try again!");
            }
        } // end while 

        scan.close(); // Close the scanner after the loop
    }

    // Private method to generate a unique 4-digit number
    private static String generateUniqueFourDigitNumber(Random random) { 
        int number;
        while (true) {
            number = 1000 + random.nextInt(9000); // Generate a 4-digit number
            if (hasUniqueDigits(number)) {
                return Integer.toString(number);
            }
        }
    }

    // Private helper method to check for unique digits in a 4-digit number
    private static boolean hasUniqueDigits(int number) {
        String numStr = Integer.toString(number);
        for (int i = 0; i < numStr.length(); i++) {
            for (int j = i + 1; j < numStr.length(); j++) {
                if (numStr.charAt(i) == numStr.charAt(j)) {
                    return false; // Duplicate digit found
                }
            }
        }
        return true; // All digits are unique
    }
}
