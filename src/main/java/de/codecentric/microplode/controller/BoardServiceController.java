package de.codecentric.microplode.controller;

import de.codecentric.microplode.action.BoardAction;
import de.codecentric.microplode.domain.Field;
import de.codecentric.microplode.domain.PlayerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/boardservice")
public class BoardServiceController {

    @Autowired
    private BoardAction boardAction;

    @RequestMapping(value="/initialize-game", method=RequestMethod.GET)
    public @ResponseBody String Initialize() throws Exception{
        boardAction.initialize();
        return "Game initialized";
    }
    
    @RequestMapping(value="/make-move/{xpos}/{ypos}/{pid}", method=RequestMethod.GET)
    public @ResponseBody String makeMove(@PathVariable("xpos") Integer xPos,
                                         @PathVariable("ypos") Integer yPos,
                                         @PathVariable("pid") String pId) throws Exception {

        PlayerInfo player = new PlayerInfo(pId, "P" + pId);
        Field field = new Field(xPos, yPos);
        boardAction.makeMove(field, player);

        return "Move executed";
    }

}