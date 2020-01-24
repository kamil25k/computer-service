package pl.kamil25k.pcservice.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto mapToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

}
