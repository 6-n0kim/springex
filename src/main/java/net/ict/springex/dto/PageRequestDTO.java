package net.ict.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

  @Builder.Default //page의 기본값
  @Min(value = 1)
  @Positive
  private int page = 1;

  @Builder.Default //size의 기본값
  @Min(value = 10)
  @Max(value = 100)
  @Positive
  private int size = 10;

  public int getSkip() {
    return (page - 1) * 10;
  }

}
