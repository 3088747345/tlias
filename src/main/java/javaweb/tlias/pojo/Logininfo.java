package javaweb.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logininfo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
