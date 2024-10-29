package com.hit.service;

import com.hit.model.dto.HabitDTO;
import com.hit.model.mapper.HabitMapper;
import com.hit.repository.HabitRepository;
import jakarta.inject.Singleton;

@Singleton
public class HabitService {

  private final HabitRepository habitRepository;
  private final HabitMapper habitMapper;

  public HabitService(HabitRepository habitRepository, HabitMapper habitMapper) {
    this.habitRepository = habitRepository;
    this.habitMapper = habitMapper;
  }

  public void createHabit(HabitDTO habitDTO) {
    habitRepository.save(habitMapper.toEntity(habitDTO));
  }
}
