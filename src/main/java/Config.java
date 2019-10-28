import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


abstract class Config {
    static JSONObject getCompanyMap(String companyName) throws IOException, ParseException {
      FileReader reader = new FileReader("C:\\Users\\koytt\\IdeaProjects\\Robotics\\src\\main\\resources\\cofig.json");
      JSONParser jsonParser = new JSONParser();

      JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
      return (JSONObject) jsonObject.get(companyName);
   }

   static JSONObject getEmailConf() throws IOException, ParseException {
      FileReader reader = new FileReader("C:\\Users\\koytt\\IdeaProjects\\Robotics\\src\\main\\resources\\cofig.json");
      JSONParser jsonParser = new JSONParser();
      JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
      return (JSONObject) jsonObject.get("email");
   }
}
