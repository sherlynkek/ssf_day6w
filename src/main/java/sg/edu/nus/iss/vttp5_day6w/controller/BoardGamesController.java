package sg.edu.nus.iss.vttp5_day6w.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api")
public class BoardGamesController {

    @GetMapping("/boardgame")
    public String boardGame() {
        return "boardGame";
    }
    

}
