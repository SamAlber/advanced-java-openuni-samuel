package mmn11.Q1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class ImpactBoolLogic { 
    public static void main(String[] args){    
        Scanner scan = new Scanner(System.in); // Create Scanner once outside the loop
        Random random = new Random();
        
        String playAgain  = "";
        
        do {
            String uniqueNumber = generateUniqueFourDigitNumber(random);
            char[] targetArray = uniqueNumber.toCharArray(); // Convert the target number to a char array
            System.out.println("The program chose: " + uniqueNumber + " as a target number");

            int tries = 1;
            ArrayList<String> attemptedStrings = new ArrayList<>(); // Use ArrayList for dynamic storage

            while (true) {
                int directHits = 0; // Number of exact matches
                int indirectHits = 0; // Number of correct digits in the wrong position

                System.out.print("Enter a 4-digit number (or quit (q)): ");
                String input = scan.nextLine(); // Read input as a string
                if(input.equalsIgnoreCase("q")){
                    System.out.println("The secret number was: "+ uniqueNumber);
                    break;
                }
                    
                attemptedStrings.add(input); // Add each attempt to the list

                if (input.length() != 4) {
                    System.out.println("Please enter exactly 4 digits.");
                    continue; // Skip to the next iteration of the loop
                }

                char[] inputArray = input.toCharArray(); // Convert the input to a char array

                // Check for direct hits
                boolean[] matchedInTarget = new boolean[4]; // Track matched positions in target
                boolean[] matchedInInput = new boolean[4];  // Track matched positions in input

                for (int i = 0; i < inputArray.length; i++) {
                    if (inputArray[i] == targetArray[i]) {
                        directHits++;
                        matchedInTarget[i] = true;
                        matchedInInput[i] = true;
                    }
                }

                // Check for indirect hits
                for (int i = 0; i < inputArray.length; i++) {
                    if (!matchedInInput[i]) { // Only check unmatched positions
                        for (int j = 0; j < targetArray.length; j++) {
                            if (!matchedInTarget[j] && inputArray[i] == targetArray[j]) {
                                indirectHits++;
                                matchedInTarget[j] = true;
                                break;
                            }
                        }
                    }
                }

                if (directHits == 4) {
                    System.out.println("Congratulations! You've guessed the number: " + input + "\nYou tried " + tries + " times");
                    System.out.println("Your attempts: " + attemptedStrings); // Print all attempts
                    System.out.println("Do you want to play again? (yes or no)");
                    
                    playAgain = scan.nextLine(); // Read input to determine if user wants to play again
                    break;
                } else { 
                    System.out.println("Your score: " + directHits + " direct hits and " + indirectHits + " indirect hits - Try again!");
                    tries++;
                }
            }

        } while (playAgain.equalsIgnoreCase("yes")); // Loop repeats if the user enters "yes"

        System.out.println("Bye");
        scan.close(); // Close the scanner after the loop
    }

    // Private method to generate a unique 4-digit number
    private static String generateUniqueFourDigitNumber(Random random) { 
        int number;
        while (true) {
            number = 1000 + random.nextInt(9000); // Generate a 4-digit number - 1000-9999
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
