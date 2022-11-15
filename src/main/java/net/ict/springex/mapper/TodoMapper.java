package net.ict.springex.mapper;

import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {
  String getTime();
  void insert(TodoVO todoVO);
  List<TodoVO> selectAll();
}
