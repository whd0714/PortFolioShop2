package portfolioshop.main.dto;

import lombok.Data;

@Data
public class MainSearchDto {

    private String query;
    private String tagName;
    private String sort;
}
