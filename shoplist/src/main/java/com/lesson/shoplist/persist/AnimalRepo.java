package com.lesson.shoplist.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

	List<Animal> findByUserUsername(String username);

	Long findById(Id id);
}
