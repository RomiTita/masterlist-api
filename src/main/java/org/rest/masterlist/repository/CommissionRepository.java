package org.rest.masterlist.repository;

import org.rest.masterlist.model.Career;
import org.rest.masterlist.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    Commission getAllByCareer(Career career);
}
