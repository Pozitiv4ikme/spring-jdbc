package ua.lviv.iot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
  private Integer id;
  private String name;
  private String surname;
  private String phone;
  private Integer gymId;
}
