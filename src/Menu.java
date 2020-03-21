import org.json.simple.JSONObject;

import java.util.*;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    private JSONReader jsonReader = new JSONReader();
    private JSONWriter jsonWriter = new JSONWriter();

    void menu() {

        do {
            System.out.println(
                    "Welcome to Strucutred Data Service\n" +
                            "(1) All countries\n" +
                            "(2) Search by key\n" +
                            "(3) Write countries to JSON file\n" +
                            "(4) Quit"
            );

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    int i = 1;
                    for(Map.Entry<String, Country> country : jsonReader.getAllCountries().entrySet()) {
                        System.out.println(
                                "(" + i + ")\n" + country.getValue().toString() +
                                "\n----------------------------------------------"
                        );
                        i++;
                    }
                    System.out.println("The file contains: " + jsonReader.getAllCountries().size() + " countries");
                    break;
                case "2":
                    int j = 1;
                    for (Object object : jsonReader.getAllKeys()) {
                        System.out.println("(" + j + ") " + object);
                        j++;
                    }

                    System.out.println(
                            "What values do you want displayed? Write the number with a comma afterwards"
                    );
                    String values = sc.nextLine();
                    jsonReader.getValues(values);
                    break;
                case "3":
                    System.out.println("Printing json objects to json file...");
                    jsonWriter.writeToJson();
                    System.out.println("Json file have been created");
                    break;
                case "4":
                    System.exit(1);
            }

        } while (true);
    }
}
