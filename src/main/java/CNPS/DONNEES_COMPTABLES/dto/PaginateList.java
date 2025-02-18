package CNPS.DONNEES_COMPTABLES.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginateList<T> {
  private int page;
  private int size;
  private int pages;
  private int count;
  private int countAll;
  private List<T> list;

  public static <T> PaginateList<T> fromPage(Page<T> pageData) {
    return PaginateList.<T>builder()
        .page(pageData.getPageable().getPageNumber())
        .size(pageData.getPageable().getPageSize())
        .pages(pageData.getTotalPages())
        .count(pageData.getNumberOfElements())
        .countAll((int) pageData.getTotalElements())
        .list(pageData.getContent())
        .build();
  }
}
