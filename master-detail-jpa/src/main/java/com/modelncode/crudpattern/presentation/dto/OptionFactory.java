package com.modelncode.crudpattern.presentation.dto;

import com.modelncode.crudpattern.domain.Group;
import com.modelncode.crudpattern.domain.User;

/**
 * Created by g on 2017-07-01.
 */
public class OptionFactory {

    public static OptionDto createBy(Group group) {
        OptionDto optionDto = new OptionDto();
        optionDto.setId(group.getId());
        optionDto.setValue(group.getName());

        return optionDto;
    }

    public static OptionDto createBy(User user) {
        OptionDto optionDto = new OptionDto();
        optionDto.setId(user.getId());
        optionDto.setValue(user.getName());

        return optionDto;
    }
}
