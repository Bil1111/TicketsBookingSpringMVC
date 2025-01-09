package com.example.config.concerts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertsRepository extends JpaRepository<Concert,Long> {

}
