package CNPS.DONNEES_COMPTABLES.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Action<T> {
  private boolean ok;
  private String message;
  private T result;

  public static <T> Action<T> success(String message, T result) {
    return new Action<T>(true, message, result);
  }

  public static <T> Action<T> success(T result) {
    return new Action<T>(true, "success:operation", result);
  }

  public static <T> Action<T> success(String message) {
    return new Action<T>(true, message, null);
  }

  public static <T> Action<T> success() {
    return new Action<T>(true, "success:operation", null);
  }

  public static <T> Action<T> fail(String message, T result) {
    return new Action<T>(false, message, result);
  }

  public static <T> Action<T> fail(T result) {
    return new Action<T>(false, "fail:operation", result);
  }

  public static <T> Action<T> fail() {
    return new Action<T>(false, "fail:operation", null);
  }

  public static <T> Action<T> fail(String message) {
    return new Action<T>(false, message, null);
  }
}
