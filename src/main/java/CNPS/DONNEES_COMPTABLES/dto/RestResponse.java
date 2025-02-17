package CNPS.DONNEES_COMPTABLES.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestResponse<T> {

  private boolean success;
  private String message;
  private T result;

  public static <T> RestResponse<T> success(String message, T result) {
    return new RestResponse<T>(true, message, result);
  }

  public static <T> RestResponse<T> fail(String message, T result) {
    return new RestResponse<T>(false, message, result);
  }

  public static <T> RestResponse<T> success(T result) {
    return new RestResponse<T>(true, "success", result);
  }

  public static <T> RestResponse<T> fail(T result) {
    return new RestResponse<T>(false, "fail", result);
  }
}
