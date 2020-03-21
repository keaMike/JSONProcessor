import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JSONWriter {

    private JSONReader jsonReader = new JSONReader();
    private JSONArray jsonArray = new JSONArray();

    void writeToJson() {
        try (FileWriter fileWriter = new FileWriter("World.json")) {
            for (Map.Entry<String, Country> entry: jsonReader.getAllCountries().entrySet()) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("Country: ", entry.getValue().getCountry());
                jsonObj.put("Total population: ", entry.getValue().getTotalPopulation());
                jsonObj.put("Year: ", entry.getValue().getYear());
                jsonArray.add(jsonObj);
            }
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
