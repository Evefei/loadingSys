package com.repository;

import com.domain.Loading;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Loading entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoadingRepository extends JpaRepository<Loading, Long> {

}
