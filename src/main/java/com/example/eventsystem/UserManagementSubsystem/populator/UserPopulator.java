package com.example.eventsystem.UserManagementSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.UserManagementSubsystem.dto.UserDto;
import com.example.eventsystem.UserManagementSubsystem.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator extends GenericPopulator<User, UserDto> {
    @Override
    protected UserDto populate(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        if (StringUtils.isNotEmpty(user.getMiddleName())) userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setBirthDate(user.getBirthDate());
        return userDto;
    }
}
