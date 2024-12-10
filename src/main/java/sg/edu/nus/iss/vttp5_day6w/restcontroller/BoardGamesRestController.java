package sg.edu.nus.iss.vttp5_day6w.restcontroller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5_day6w.constant.Constant;
import sg.edu.nus.iss.vttp5_day6w.service.BoardGamesRestService;

@RestController
@RequestMapping("/api/boardgame")
public class BoardGamesRestController {

    @Autowired
    BoardGamesRestService boardGamesRestService;

    @PostMapping
    public ResponseEntity<String> allBoardGames() throws FileNotFoundException {
        JsonObject jObject = Json.createObjectBuilder()
        .add("insert_count", 1)
        .add("id", Constant.boardGamesKey)
        .build();

        boardGamesRestService.boardGames();
        ResponseEntity<String> resp = ResponseEntity.status(HttpStatusCode.valueOf(201))
        .body(jObject.toString());
        return resp;
        
    }

    @GetMapping("/{boardgameId}")
    // day 16 slide GET Endpoint
    public ResponseEntity<String> givenBoardGames(@PathVariable String boardgameId) {
        String boardgameFound = boardGamesRestService.getBoardgameById(Integer.parseInt(boardgameId));
    
        if (boardgameFound != null) {
            return new ResponseEntity<>(boardgameFound, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("No boardgame found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{boardgameId}")
    public ResponseEntity<String> updateBoardGames(@PathVariable String boardgameId,
    @RequestBody String boardgameUpdate, @RequestParam(required = false, defaultValue = "false") Boolean upsert) {
        
        
        return null;
        
    }

}