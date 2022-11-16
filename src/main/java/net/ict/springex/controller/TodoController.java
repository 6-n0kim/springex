package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @RequestMapping("/list")    //최종경로는 /todo/list
  public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
    log.info("todo list..............");
    if(bindingResult.hasErrors()){
      pageRequestDTO = PageRequestDTO.builder().build();
    }
    model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    //model에는 'dtoList' 이름으로 목록데이터가 담겨 있다.
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public void register() {
    log.info("todo register......");
  }

  //todo/register 를 POST방식 으로 처리하는 메소드 TodoDTO 를 파라미터로 적용
  @PostMapping("/register")
  public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    log.info("POST todo register ...........");

    if (bindingResult.hasErrors()) {
      log.error("has errors.......");
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      return "redirect:/todo/register";
    }

    log.info(todoDTO);
    todoService.register(todoDTO);
    return "redirect:/todo/list";
  }

  @GetMapping({"/read", "/modify"})
  public void read(Long tno, Model model) {
    TodoDTO todoDTO = todoService.getOne(tno);
    log.info(todoDTO);
    model.addAttribute("dto", todoDTO);
  }

  @PostMapping("/remove")
  public String remove(Long tno, RedirectAttributes redirectAttributes) {
    todoService.remove(tno);
    log.info("삭제 완료 tno " + tno);
    return "redirect:/todo/list";
  }

  @PostMapping("/modify")
  public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult , RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      log.info("----has errors----");
      redirectAttributes.addFlashAttribute("errors" , bindingResult.getAllErrors());
      redirectAttributes.addAttribute("tno" , todoDTO.getTno());
      return "redirect:/todo/modify";
    }
    log.info(todoDTO);
    todoService.modify(todoDTO);
    return "redirect:/todo/list";
  }

}