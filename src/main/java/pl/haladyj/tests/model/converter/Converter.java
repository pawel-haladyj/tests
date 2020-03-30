package pl.haladyj.tests.model.converter;

import org.springframework.stereotype.Component;

public interface Converter<Model, Dto> {
    Model toEntity(Dto dto);
    Dto toDto(Model model);
}
