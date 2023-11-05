package com.yuzarsif.youcontribute.repository;

import com.yuzarsif.youcontribute.models.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Integer> {
}
