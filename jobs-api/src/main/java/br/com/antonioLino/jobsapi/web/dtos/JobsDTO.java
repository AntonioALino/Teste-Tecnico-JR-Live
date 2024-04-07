package br.com.antonioLino.jobsapi.web.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobsDTO {
    
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private BigDecimal salary;

}
