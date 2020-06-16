package ru.sbrf;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHelper {
    JSONObject json = new JSONObject();
    JSONParser jsonParser = new JSONParser();

    public void readFromJson(String args, Storage storage) throws IOException, ParseException {
        Object obj = jsonParser.parse(new FileReader(args));
        JSONObject jsonObject = (JSONObject) obj;
        storage.setBill100((int) (long) jsonObject.get("bill100"));
        storage.setBill500((int) (long) jsonObject.get("bill500"));
        storage.setBill1000((int) (long) jsonObject.get("bill1000"));
    }

    public void writeToJson(String args, Storage storage) throws IOException, ParseException {
        Object obj = jsonParser.parse(new FileReader(args));
        JSONObject jsonObject = (JSONObject) obj;
        json.put("bill100", storage.getBill100());
        json.put("bill500", storage.getBill500());
        json.put("bill1000", storage.getBill1000());
        FileWriter fileWriter = new FileWriter(args);
        fileWriter.write(json.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public void createNewJson(String args, Storage storage) throws IOException {
        File file = new File(args);
        file.createNewFile();
        storage.setBill100(5);
        storage.setBill500(5);
        storage.setBill1000(5);
        json.put("bill100", storage.getBill100());
        json.put("bill500", storage.getBill500());
        json.put("bill1000", storage.getBill1000());
        FileWriter fileWriter = new FileWriter(args);
        fileWriter.write(json.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
