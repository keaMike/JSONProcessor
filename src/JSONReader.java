import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class JSONReader {

    private Map<String,Country> countries = new HashMap<>();
    private Map<String,JSONObject> jsonObjects = new HashMap<>();
    private int max;

    Map<String,Country> getAllCountries() {
        for(Object countryObj : getJsonArray()) {
            generateCountries((JSONObject) countryObj);
        }
        return countries;
    }

    List<Object> getAllKeys() {
        JSONObject keyObj = (JSONObject) getJsonArray().get(0);
        return Arrays.asList(keyObj.keySet().toArray());
    }

    void getValues(String values) {
        String[] valueList = values.split(",");
        List<Object> keyList = getAllKeys();
        mapJsonObjects(valueList, keyList);
        int k = 1;
        for (Map.Entry<String, JSONObject> entry : jsonObjects.entrySet()) {
            System.out.println(
                    "-----------------------------------------------\n" +
                    "(" + k + ")\n" + entry.getValue().get("Country")
            );
            for (String s : valueList) {
                System.out.println(
                        (keyList.get(Integer.parseInt(s) - 1)) + ": " +
                        entry.getValue().get(keyList.get(Integer.parseInt(s) - 1))
                );
            }
            k++;
        }
        System.out.println();
    }

    private JSONArray getJsonArray() {
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("country.json")) {
            Object obj = jsonParser.parse(fileReader);
            return (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generateCountries(JSONObject country) {
        // Create Country Object
        Country c = new Country();
        c.setCountry((String) country.get("Country"));
        c.setYear((String) country.get("Year"));
        if (country.get("Total population") != null) {
            c.setTotalPopulation(country.get("Total population").toString());
            mapCountries(c);
        }
    }

    private void mapCountries(Country c) {
        // If already in the Map, don't add, but compare
        if(!countries.containsKey(c.getCountry())) {
            max = Integer.parseInt(c.getYear());
            countries.put(c.getCountry(), c);
            // Else override key with new values
        } else {
            int year = Integer.parseInt((c.getYear()));
            if(max < year) {
                countries.put(c.getCountry(), c);
                max = year;
            }
        }
    }

    private void mapJsonObjects(String[] valueList, List<Object> keyList) {
        for (Object obj : getJsonArray()) {
            boolean isNull = false;
            JSONObject jsonObject = (JSONObject) obj;

            for (String s : valueList) {
                if(jsonObject.get(keyList.get(Integer.parseInt(s) - 1)) == null ||
                        jsonObject.get(keyList.get(Integer.parseInt(s) - 1)).equals("")) {
                    isNull = true;
                }
            }

            // If already in the Map, don't add, but compare
            if (!jsonObjects.containsKey(jsonObject.get("Country")) && !isNull) {
                max = Integer.parseInt((String) jsonObject.get("Year"));
                jsonObjects.put((String) jsonObject.get("Country"), jsonObject);
                // Else override key with new values
            } else if (!isNull){
                int year = Integer.parseInt((String) jsonObject.get("Year"));
                if (max < year) {
                    jsonObjects.put((String) jsonObject.get("Country"), jsonObject);
                    max = year;
                }
            }
        }
    }
}
