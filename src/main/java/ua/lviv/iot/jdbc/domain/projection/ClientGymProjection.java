package ua.lviv.iot.jdbc.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientGymProjection {
    private Integer gymId;
    private String name;
    private String surname;
    private String birthday;
    private String phone;
    private String type;
}
