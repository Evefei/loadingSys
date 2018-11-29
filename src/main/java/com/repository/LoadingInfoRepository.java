package com.repository;

import com.domain.LoadingInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoadingInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoadingInfoRepository extends JpaRepository<LoadingInfo, Long> {

}
