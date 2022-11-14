package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
@RequestMapping("/todo")
public class TodoController {
  @RequestMapping("/list")    //최종경로는 /todo/list
  public void list(){
    log.info("todo list..............");
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public void register(){
    log.info("todo register......");
  }

  //'/todo/register'를 POST 방식으로 처리하는 메소드  TodoDTO를 파라미터로 적용
  @PostMapping("/register")
  public void registerPost(TodoDTO todoDTO){
    log.info("POST todo register.......");
    log.info(todoDTO);
  }
}
