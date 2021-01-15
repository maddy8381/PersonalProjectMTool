package com.maddy8381.PersonalProjectMngmTool.services;

import com.maddy8381.PersonalProjectMngmTool.domain.Project;
import com.maddy8381.PersonalProjectMngmTool.exceptions.ProjectIdException;
import com.maddy8381.PersonalProjectMngmTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            //Calling Custom Created Exception
            throw new ProjectIdException("Project Id "+ project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project Id does not exist");
        }

        return project;
    }
}


