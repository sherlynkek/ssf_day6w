package sg.edu.nus.iss.vttp5_day6w.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public String getBoardgameById(String boardgameId) {
        
        String boardgame = mapRepo.get(Constant.boardGamesKey, boardgameId);
        
        return boardgame;
    }

    public ResponseEntity<Map<String, String>> updateBoardgame(String boardgameId, 
        String boardgameUpdate, Boolean upsert) {
    
        String existingBoardgame = mapRepo.get(Constant.boardGamesKey, boardgameId);

        Map<String, String> response = new HashMap<>(); 
    
        if (existingBoardgame != null) {
            mapRepo.create(Constant.boardGamesKey, boardgameId, boardgameUpdate);
    
            response.put("update_count", "1"); 
            response.put("id", boardgameId);
    
            return ResponseEntity.ok(response);
        } 
        else if (upsert) {

            mapRepo.create(Constant.boardGamesKey, boardgameId, boardgameUpdate);
    
            response.put("update_count", "1"); 
            response.put("id", boardgameId);
    
            return ResponseEntity.ok(response);
        } 
        else {
            response.put("error", "Board game not found for update");
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
