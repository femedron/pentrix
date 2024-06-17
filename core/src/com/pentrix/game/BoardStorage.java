//package com.pentrix.game;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class BoardStorage {
//
//    JSONArray array;
//
//    public BoardStorage(){
//        loadFromFile("assets/game_scores/scores.json");
//    }
//    private String readJson(String file){
//
//    }
//    private void parseModeObject(JSONObject mode)
//    {
//        //Get employee object within list
//        JSONObject levelsBoard = (JSONObject) mode.get("levels");
//        JSONObject scoreBoard = (JSONObject) mode.get("score");
//
//        HashMap<String, Integer> levelsMap = new HashMap<String, Integer>();
//        for (int i = 0; i < levelsBoard.size(); i++) {
//            JSONObject jsonObject = (JSONObject) retrievedJsonArray.get(i);
//            Map<String, String> retrievedMap = new HashMap<>(jsonObject);
//            retrievedMapArray[i] = retrievedMap;
//        }
//        //Get employee first name
//        String firstName = (String) employeeObject.get("firstName");
//        System.out.println(firstName);
//
//        //Get employee last name
//        String lastName = (String) employeeObject.get("lastName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        String website = (String) employeeObject.get("website");
//        System.out.println(website);
//    }
//    private void loadFromFile(String f){
//        File file = new File(f);
//        if (!file.exists())
//            return;
//
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader(file)){
//            Object obj = jsonParser.parse(reader);
//
//            array = (JSONArray) obj;
//            System.out.println(array);
//
//            //Iterate over employee array
//            array.forEach( mode -> parseModeObject( (JSONObject) mode ) );
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        array = new JSONArray();
//        JSONParser parser = new JSONParser();
//        FileReader fileReader = new FileReader(file);
//        try {
//            array = (JSONArray) parser.parse(jsonString);
//            Map<String, String>[] retrievedMapArray = new HashMap[retrievedJsonArray.size()];
//            for (int i = 0; i < retrievedJsonArray.size(); i++) {
//                JSONObject jsonObject = (JSONObject) retrievedJsonArray.get(i);
//                Map<String, String> retrievedMap = new HashMap<>(jsonObject);
//                retrievedMapArray[i] = retrievedMap;
//            }
//
//            // Print the retrieved array of HashMaps
//            for (Map<String, String> map : retrievedMapArray) {
//                System.out.println("Retrieved HashMap: " + map);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    public void putEntry(String name, int mode, ScoreType scoreType, int val){
//        JSONObject file = new JSONObject();
//
//        file.put("Full Name", "Ritu Sharma");
//        file.put("Roll No.", new Integer(1704310046));
//        file.put("Tuition Fees", new Double(65400));
//    }
//    public int getEntry(String name, int mode, ScoreType scoreType){
//        return 0;
//    }
//}
