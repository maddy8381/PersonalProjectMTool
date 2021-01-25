package com.maddy8381.PersonalProjectMngmTool.services;

import com.maddy8381.PersonalProjectMngmTool.domain.Backlog;
import com.maddy8381.PersonalProjectMngmTool.domain.ProjectTask;
import com.maddy8381.PersonalProjectMngmTool.repositories.BacklogRepository;
import com.maddy8381.PersonalProjectMngmTool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //Exceptions: Project Not Found

        //ProjectTasks to be added to a specific project, project != null, Backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //Set the backlog to the ProjectTask
        projectTask.setBacklog(backlog);

        //we want our projectSequence  to be like this - IDPRO-1, IDPRO-2
        Integer BacklogSequence = backlog.getPTSequence();
        //Update the Backlog Sequence
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);
        //Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier+"-"+ BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //Initial Priority when priority is null
        if( projectTask.getPriority() == null) //TODO projectTask.getPriority() == 0 ||
            projectTask.setPriority(3); //Low Priority

        //Initial Status when status is null
        if(projectTask.getStatus() == "" || projectTask.getStatus() == null)
            projectTask.setStatus("TO_DO");

        return projectTaskRepository.save(projectTask);

    }
}
