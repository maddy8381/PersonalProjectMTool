package com.maddy8381.PersonalProjectMngmTool.repositories;

import com.maddy8381.PersonalProjectMngmTool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
