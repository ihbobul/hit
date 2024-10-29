package com.hit.repository;

import com.hit.model.entity.HabitEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface HabitRepository extends CrudRepository<HabitEntity, Long> {

}
