package org.rest.masterlist.repository;

import org.rest.masterlist.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Integer> {
}
