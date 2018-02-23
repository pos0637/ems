package com.furongsoft.base.rbac.repositories;

import com.furongsoft.base.rbac.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 资源仓库
 *
 * @author Alex
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
