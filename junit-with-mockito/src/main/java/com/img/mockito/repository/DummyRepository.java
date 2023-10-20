package com.img.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.img.mockito.model.Dummy;

public interface DummyRepository extends JpaRepository<Dummy, Integer>{

}
