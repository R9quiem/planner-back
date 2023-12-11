package task_planner_backend.utils;
import java.util.Random;

public class RandomStringGenerator {

    public static String generateRandomString(int length) {
        // Define the characters that can be used in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a Random object
        Random random = new Random();

        // Use StringBuilder to efficiently build the random string
        StringBuilder stringBuilder = new StringBuilder(length);

        // Generate random characters and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        // Convert StringBuilder to String and return
        return stringBuilder.toString();
    }
}
