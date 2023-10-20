package com.img.mockito;

import com.img.mockito.model.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Dummy,Integer> {
}
