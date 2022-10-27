package ua.lviv.iot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gym {
    private Integer id;
    private String phone;
    private String streetAddress;
    private Integer cityId;
}
