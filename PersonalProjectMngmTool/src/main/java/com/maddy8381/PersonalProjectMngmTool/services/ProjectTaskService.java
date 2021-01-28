package com.maddy8381.PersonalProjectMngmTool.services;

import com.maddy8381.PersonalProjectMngmTool.domain.Backlog;
import com.maddy8381.PersonalProjectMngmTool.domain.Project;
import com.maddy8381.PersonalProjectMngmTool.domain.ProjectTask;
import com.maddy8381.PersonalProjectMngmTool.exceptions.ProjectNotFoundException;
import com.maddy8381.PersonalProjectMngmTool.repositories.BacklogRepository;
import com.maddy8381.PersonalProjectMngmTool.repositories.ProjectRepository;
import com.maddy8381.PersonalProjectMngmTool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //Exceptions: Project Not Found

        try{
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
            if(projectTask.getPriority() == 0 || projectTask.getPriority() == null)
                projectTask.setPriority(3); //Low Priority

            //Initial Status when status is null
            if(projectTask.getStatus() == "" || projectTask.getStatus() == null)
                projectTask.setStatus("TO_DO");

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project Not Found");
        }


    }

    public Iterable<ProjectTask> findBacklogById(String id){
        Project project = projectRepository.findByProjectIdentifier(id);
        //Removing case where project does not exist and still we are providing empty array
        if(project == null){
            throw new ProjectNotFoundException("Project With Id: " + id + " does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_id){

        //Make sure we are searching on the existing backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null){
            throw new ProjectNotFoundException("Project With Id: " + backlog_id + " does not exist");
        }
        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task : " + pt_id + "  not found");
        }
        //make sure that the backlog/project id in the path corresponds to right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task: " + pt_id + " does not exist in project: "+ backlog_id);
        }

        return projectTask;
    }

    //Update Project Task
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id){

        //Find existing project task
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_id); //this has validation already
        //Replace it with updated task
        projectTask = updatedTask;

        //Save update
        return projectTaskRepository.save(projectTask);
    }


    public void deletePTByProjectSequence(String backlog_id, String pt_id){
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_id);

        projectTaskRepository.delete(projectTask);
    }
}
