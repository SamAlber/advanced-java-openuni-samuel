package mmn11.Q1;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

class ImpactBoolLogic {
    public static void main(String[] args) {
        Random random = new Random();

        String playAgain = "";

        do {
            String uniqueNumber = generateUniqueFourDigitNumber(random);
            char[] targetArray = uniqueNumber.toCharArray();

            // Uncomment the following line if you want to display the secret number (for debugging)
            // JOptionPane.showMessageDialog(null, "The program chose: " + uniqueNumber + " as a target number");

            int tries = 1;
            ArrayList<String> attemptedStrings = new ArrayList<>();

            while (true) {
                int directHits = 0;
                int indirectHits = 0;
               
                
                 //Build previous attempts message from scratch each time while iterating on attemptedStrings results 
                 //That were written in: String attemptResult = "Attempt " + tries + ": " + input + " - " + directHits + " direct hits and " + indirectHits + " indirect hits"; 
                 //attemptedStrings.add(attemptResult); 
                String previousAttemptsMessage = "";
                if (!attemptedStrings.isEmpty()) {
                    previousAttemptsMessage = "Previous attempts:\n";
                    for (String attempt : attemptedStrings) {
                        previousAttemptsMessage += attempt + "\n";
                    }
                }

                // Prompt the user with previous attempts included
                String input = JOptionPane.showInputDialog(null, "The target number is: " + uniqueNumber + "\n" + previousAttemptsMessage + "\nEnter a 4-digit number or cancel:");

                if (input == null) {
                    // User canceled the input
                    JOptionPane.showMessageDialog(null, "The target number was: " + uniqueNumber + "\nYou tried: " + (tries - 1) + " times\n" + previousAttemptsMessage);

                    int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        playAgain = "yes";
                    } else {
                        playAgain = "no";
                    }
                    break;
                }

                if (input.length() != 4 || !input.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Please enter exactly 4 digits.");
                    continue;
                }

                char[] inputArray = input.toCharArray();

                boolean[] matchedInTarget = new boolean[4];
                boolean[] matchedInInput = new boolean[4];

                // Check for direct hits
                for (int i = 0; i < inputArray.length; i++) {
                    if (inputArray[i] == targetArray[i]) {
                        directHits++;
                        matchedInTarget[i] = true;
                        matchedInInput[i] = true;
                    }
                }

                // Check for indirect hits
                for (int i = 0; i < inputArray.length; i++) {
                    if (!matchedInInput[i]) {
                        for (int j = 0; j < targetArray.length; j++) {
                            if (!matchedInTarget[j] && inputArray[i] == targetArray[j]) {
                                indirectHits++;
                                matchedInTarget[j] = true;
                                break;
                            }
                        }
                    }
                }

                String attemptResult = "Attempt " + tries + ": " + input + " - " + directHits + " direct hits and " + indirectHits + " indirect hits";
                attemptedStrings.add(attemptResult);

                if (directHits == 4) {
                    // User guessed the number correctly
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number: " + input + "\nYou tried " + tries + " times\n" + previousAttemptsMessage + attemptResult);

                    int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        playAgain = "yes";
                    } else {
                        playAgain = "no";
                    }
                    break;
                } else {
                    // Inform the user of their current attempt result
                    JOptionPane.showMessageDialog(null, attemptResult + "\nTry again!");
                    tries++;
                }
            }
        } while (playAgain.equalsIgnoreCase("yes"));

        JOptionPane.showMessageDialog(null, "Bye");
    }

    // Private method to generate a unique 4-digit number
    private static String generateUniqueFourDigitNumber(Random random) {
        int number;
        while (true) {
            number = 1000 + random.nextInt(9000); // Generate a 4-digit number between 1000 and 9999
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
