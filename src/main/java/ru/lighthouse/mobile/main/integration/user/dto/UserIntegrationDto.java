package ru.lighthouse.mobile.main.integration.user.dto;

import lombok.Data;
import ru.lighthouse.mobile.main.mapper.model.DtoModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserIntegrationDto implements DtoModel {
    private Long id;
    private String userAgent;
    private Set<AuthorityDto> authorities = new HashSet<>();
    private String phoneNumber;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthDate;
    private Date registrationDate;
    private Date lastLogin;
}
