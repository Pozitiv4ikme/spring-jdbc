package ua.lviv.iot.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeGroupTraining {
    private Integer id;
    private Integer trainerId;
    private Integer freeGroupProgramId;
}
