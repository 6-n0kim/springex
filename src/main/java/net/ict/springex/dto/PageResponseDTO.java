package net.ict.springex.dto;
/* PageResponseDTO의 기능
   -TodoDTO 목록
   -전체 데이터의 수
   -페이지 번호 처리를 위한 데이터(시작 페이지 번호 / 끝 페이지 번호)
 */

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> { //제너릭을 이용한 이유는 다른 종류의 객체를 이용하여 PageResponseDTO를 구성할 수 있도록
                                  //예로 회원 정보게시판이나 공지사항도 페이징 처리가 필요하므로 공통처리를 위해 제너릭<E>로 처리
  private int page;
  private int size;
  private int total;

  //시작페이지 번호
  private int start;
  //끝페이지 번호
  private int end;

  //이전 페이지 존재여부
  private boolean prev;
  //다음 페이지 존재여부
  private boolean next;

  private List<E> dtoList;

  @Builder(builderMethodName ="withAll")
  public PageResponseDTO(PageRequestDTO pageRequestDTO , List<E> dtoList, int total){
    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();

    this.total = total;
    this.dtoList = dtoList;
    //마지막 페이지(end)를 구하는 계산식 end는 현재의 페이지 번호를 기준으로 계산한다.
    this.end = (int)(Math.ceil(this.page/10.0)) * 10;
    //마지막 페이지를 먼저 계산하는 이유는 시작페이지 계산을 쉽게 하기 위해. 시작 페이지의 경우 계산한 마지막 페이지에서 9를 뺌
    this.start = this.end-9;
    //10개씩 보여주는 경우 전체 개수를 구하여 last를 구함. 만약 전체 개수가 75라면 마지막페이지는 75/10.0 => 8
    int last = (int)(Math.ceil(total/(double)size));
    //마지막 페이지 검사 : 앞에서 구한 last값보다 작은경우는 last가 end가 된다.
    this.end = end>last ? last:end;
    //시작 페이지가 1이 아니라면 이전 페이지는 무조건 true
    this.prev = this.start>1;
    //다음 페이지는 마지막 페이지와 페이지당 수(size)를 곱한 값보다 전체개수가 더 많은지 체크
    this.next = total> this.end * this.size;
  }
}
