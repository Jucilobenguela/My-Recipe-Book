package academy.mindswap.my_recipe_book.dtos.request_dtos;

import lombok.Data;

@Data
public class RequestChangePassWordDTO {
    private String currentPassWord;
    private String newPassword;
}
