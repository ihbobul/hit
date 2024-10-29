package com.hit.model.mapper;

import com.hit.model.dto.HabitDTO;
import com.hit.model.entity.HabitEntity;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface HabitMapper {

  @Mapper
  HabitEntity toEntity(HabitDTO habitDTO);
}
