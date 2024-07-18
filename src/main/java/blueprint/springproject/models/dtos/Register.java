package blueprint.springproject.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Register(

        @NotNull
        @Size(min = 3, max = 50)
        String username,

        @NotNull
        @Size(min = 6, max = 50)
        String password
) {
}
