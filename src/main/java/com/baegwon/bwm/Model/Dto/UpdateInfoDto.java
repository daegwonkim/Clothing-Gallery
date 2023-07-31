package com.baegwon.bwm.Model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInfoDto {

    private Long id;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "E-mail must be valid")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[.`!@#$%^&*?])[A-Za-z\\d@.`!@#$%^&*?]{8,16}$",
            message = "Password must be between 8 and 16 characters including english, numbers, and special characters.")
    private String password;

    @NotBlank(message = "Nickname is required")
    @Size(min = 2, max = 10, message = "Nickname must be between 2 and 10 characters.")
    private String nickname;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "Phone must be valid")
    private String phone;
}
