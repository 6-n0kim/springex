package net.ict.springex.mapper;

import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {
  String getTime();

  void insert(TodoVO todoVO);

  List<TodoVO> selectAll();

  TodoVO selectOne(Long tno);

  void delete(Long tno);

  void update(TodoVO todoVO);
}
