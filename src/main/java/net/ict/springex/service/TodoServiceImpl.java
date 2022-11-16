package net.ict.springex.service;

import com.sun.tools.javac.comp.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
//데이터베이스를 처리하는 TOdoMapper 와 DTO와 VO 변환을 처리하는 ModelMapper주입

@Service
@Log4j2
@RequiredArgsConstructor //의존성 주입이 필요한 객체의 타입을 final로 고정하고 생성자인 @RequiredArgsConstructor 로 생성자를 생성하는 방식
public class TodoServiceImpl implements TodoService {
  private final TodoMapper todoMapper;
  private final ModelMapper modelMapper;

  @Override
  public void register(TodoDTO todoDTO) {
    log.info(modelMapper);
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    log.info(todoVO);
    todoMapper.insert(todoVO);
  }

  /*@Override
  public List<TodoDTO> getAll() {
    List<TodoDTO> dtoList = todoMapper.selectAll().stream()
            .map(vo -> modelMapper.map(vo, TodoDTO.class))
            .collect(Collectors.toList());
    return dtoList;

    //List<TodoVO>를 List<TodoDTO>로 변환하는 작업을 stream을 이용하여
    //각 TodoVO는 map()을 통해서 TodoDTO로 바꾸고 collect()을 이용하여 List<TodoDTO로 묶어준다.
  }*/

  @Override
  public TodoDTO getOne(Long tno) {
    TodoVO todoVO = todoMapper.selectOne(tno);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
    return todoDTO;
  }

  @Override
  public void remove(Long tno) {
    todoMapper.delete(tno);
  }

  @Override
  public void modify(TodoDTO todoDTO) {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    todoMapper.update(todoVO);
  }

  @Override
  public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
    List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
    List<TodoDTO> dtoList = voList.stream()
            .map(vo -> modelMapper.map(vo, TodoDTO.class))
            .collect(Collectors.toList());
    int total = todoMapper.getCount(pageRequestDTO);

    PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
            .dtoList(dtoList)
            .total(total)
            .pageRequestDTO(pageRequestDTO)
            .build();

    return pageResponseDTO;
  }
}
