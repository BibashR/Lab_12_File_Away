import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        boolean moreData = true;

        while (moreData) {
            System.out.println("Enter a new record:");

            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String id = String.format("%06d", idCounter);  // 6-digit ID with leading zeros
            String email = SafeInput.getNonZeroLenString(in, "Enter Email");
            int yob = SafeInput.getRangedInt(in, "Enter Year of Birth", 1900, 2025);

            String record = firstName + ", " + lastName + ", " + id + ", " + email + ", " + yob;
            records.add(record);
            idCounter++;

            moreData = SafeInput.getYNConfirm(in, "Do you want to enter another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter name of the file to save (add .csv)").trim();

        try {
            FileWriter writer = new FileWriter("src/" + fileName);
            for (String rec : records) {
                writer.write(rec + "\n");
            }
            writer.close();
            System.out.println("Data saved successfully to src/" + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
}
