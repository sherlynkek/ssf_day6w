package sg.edu.nus.iss.vttp5_day6w.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5_day6w.constant.Constant;
import sg.edu.nus.iss.vttp5_day6w.model.BoardGames;
import sg.edu.nus.iss.vttp5_day6w.service.BoardGamesService;




@Controller
@RequestMapping("/api")
public class BoardGamesController {

    @Autowired
    BoardGamesService boardGamesService;
    
    @GetMapping("/list")
    public String getList(Model model) throws FileNotFoundException {
        List<BoardGames> boardGames = boardGamesService.readFromRedis(Constant.boardGamesKey);
        
        model.addAttribute("boardGames", boardGames);
        return "boardGameList";
    }

    @PostMapping("")
    public String postBoardGames(@Valid @ModelAttribute BoardGames entity, BindingResult result, Model model) {
        
        
        return "boardGameList";
    }

    
    
    

}