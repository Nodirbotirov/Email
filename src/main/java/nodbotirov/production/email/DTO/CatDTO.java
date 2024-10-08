package nodbotirov.production.email.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatDTO {
    private int age;
    private int weight;
    private String name;

}
