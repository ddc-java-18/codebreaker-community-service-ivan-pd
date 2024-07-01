package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.service.AbstractUserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

  private final AbstractUserService userService;

  // TODO: 7/1/24 Declare and initialize in the constructor any dependencies
  @Autowired
  public GameController(AbstractUserService userService) {
    this.userService = userService;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Game post(@RequestBody Game game) {
    // TODO: 7/1/24 Invoke service method to start game, and return Game instance.
    throw new UnsupportedOperationException();
  }

  @GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE) // TODO: 7/1/24 Add Regex for key.
  public Game get(@PathVariable UUID key) {
    // TODO: 7/1/24 Invoke service method to retrieve specified game instance.
    throw new UnsupportedOperationException();
  }

}
