package net.ict.springex.service;

import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
  void register(TodoDTO todoDTO);

  //List<TodoDTO> getAll();

  TodoDTO getOne(Long tno);

  void remove(Long tno);

  void modify(TodoDTO todoDTO);

  PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
}
