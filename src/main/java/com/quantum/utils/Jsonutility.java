package com.quantum.utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Jsonutility {
    public static JSONObject readJson(String FilePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(FilePath));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void WriteJson(String FilePath, String Key, String Value) {
        FileReader reader = null;
        JSONParser jsonParser = new JSONParser();
        try {
            reader = new FileReader(FilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            FileWriter fileWriter = new FileWriter(FilePath);
//            JSONArray jsonArray = (JSONArray) jsonObject.get(Key);
//            jsonObject = ((JSONObject) (jsonArray).get(0));
            jsonObject.put(Key, Value);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void WriteJson(String FilePath, String Key, boolean Value) {
        FileReader reader = null;
        JSONParser jsonParser = new JSONParser();
        try {
            reader = new FileReader(FilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            FileWriter fileWriter = new FileWriter(FilePath);
//            JSONArray jsonArray = (JSONArray) jsonObject.get(Key);
//            jsonObject = ((JSONObject) (jsonArray).get(0));
            jsonObject.put(Key, Value);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String GetRandomMobileNo(){
        Random rand = new Random();
        int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(10000);
        return String.valueOf(num1)+String.valueOf(num2)+String.valueOf(num3);
    }

    public static String GetRandomNo(){
        Random rand = new Random();
        int num1 = (rand.nextInt(7) + 1) * 10000 + (rand.nextInt(8) * 100) + rand.nextInt(8);
        return String.valueOf(num1);
    }
    public static String GetRandomEmailID(){
        String EMAILCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder emailid = new StringBuilder();
        Random rnd = new Random();
        while (emailid.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * EMAILCHARS.length());
            emailid.append(EMAILCHARS.charAt(index));
        }

        return emailid.toString()+"@gmail.com";
    }
    public static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }

        return password;
    }
    public static void main(String arg[]){
//        JSONObject jsonObject =readJson("src/main/resources/data/addbloodbank.json");
//        System.out.println( jsonObject.get("mobile"));


    }
}
