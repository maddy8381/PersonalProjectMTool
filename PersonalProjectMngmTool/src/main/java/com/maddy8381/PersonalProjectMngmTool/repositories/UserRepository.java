package com.maddy8381.PersonalProjectMngmTool.repositories;

import com.maddy8381.PersonalProjectMngmTool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}
