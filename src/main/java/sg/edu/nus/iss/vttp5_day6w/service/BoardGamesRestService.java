package sg.edu.nus.iss.vttp5_day6w.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5_day6w.constant.Constant;
import sg.edu.nus.iss.vttp5_day6w.model.BoardGames;
import sg.edu.nus.iss.vttp5_day6w.repo.MapRepo;

@Service
public class BoardGamesRestService {
    
    @Autowired
    MapRepo mapRepo;

    public List<BoardGames> boardGames() throws FileNotFoundException {
        File file = new File("C:\\Users\\Sherlyn Kek\\SSF\\vttp5_day6w\\src\\main\\resources\\static\\game.json");
        InputStream is = new FileInputStream(file);
        JsonReader reader = Json.createReader(is);
        JsonArray data = reader.readArray();

        List<BoardGames> boardList = new ArrayList<>();
        
        for(int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.getJsonObject(i);
            
            BoardGames bg = new BoardGames(jsonObject.getInt("gid"), 
            jsonObject.getString("name"), jsonObject.getInt("year"),
            jsonObject.getInt("ranking"), jsonObject.getInt("users_rated"), 
            jsonObject.getString("url"), jsonObject.getString("image"));

            mapRepo.create(Constant.boardGamesKey, String.valueOf(jsonObject.getInt("gid")), jsonObject.toString());
            
            boardList.add(bg);
        }

        return boardList;
    }

    public String getBoardgameById(Integer boardgameId) {
        
        return null;
    }



}
