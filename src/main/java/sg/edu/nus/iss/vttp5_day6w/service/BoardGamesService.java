package sg.edu.nus.iss.vttp5_day6w.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5_day6w.constant.Constant;
import sg.edu.nus.iss.vttp5_day6w.constant.Url;
import sg.edu.nus.iss.vttp5_day6w.model.BoardGames;
import sg.edu.nus.iss.vttp5_day6w.repo.ListRepo;
import sg.edu.nus.iss.vttp5_day6w.repo.MapRepo;

@Service
public class BoardGamesService {

    @Autowired
    MapRepo boardMapRepo;
    
    RestTemplate restTemplate = new RestTemplate();

    public void loadToRedis() throws FileNotFoundException {
        File file = new File("C:\\Users\\Sherlyn Kek\\SSF\\vttp5_day6w\\src\\main\\resources\\static\\game.json");
        InputStream is = new FileInputStream(file);
        JsonReader reader = Json.createReader(is);
        JsonArray data = reader.readArray();
        // List<BoardGames> board = new ArrayList<>();

        for(int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.getJsonObject(i);

            // BoardGames bg = new BoardGames(jsonObject.getInt("gid"), 
            // jsonObject.getString("name"), jsonObject.getInt("year"),
            // jsonObject.getInt("ranking"), jsonObject.getInt("users_rated"), 
            // jsonObject.getString("url"));

            // board.add(bg);

            boardMapRepo.create(Constant.boardGamesKey, String.valueOf(jsonObject.getInt("gid")), jsonObject.toString());
            
        }
        
        // ResponseEntity<List<BoardGames>> boardgame = restTemplate.exchange();
        // return boardgame.getBody();
    }

    public List<BoardGames> readFromRedis(String redisKey){
        List<BoardGames> boardG = new ArrayList<>();
        Map<String, String> boardGamesJSONString = boardMapRepo.getEntries(redisKey);
        for(Entry<String, String> entry : boardGamesJSONString.entrySet()){
            String jsonString = entry.getValue();
            boardG.add(convertJSONStringToRedis(jsonString));
        }

        return boardG;
    }

    public BoardGames convertJSONStringToRedis(String JSONString){
        JsonReader reader = Json.createReader(new StringReader(JSONString));
        JsonObject jsonObject = reader.readObject();
        BoardGames bg = new BoardGames(jsonObject.getInt("gid"), 
            jsonObject.getString("name"), jsonObject.getInt("year"),
            jsonObject.getInt("ranking"), jsonObject.getInt("users_rated"), 
            jsonObject.getString("url"));
        return bg;
    }

}
