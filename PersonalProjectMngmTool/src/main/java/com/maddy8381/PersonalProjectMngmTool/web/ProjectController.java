package com.maddy8381.PersonalProjectMngmTool.web;

//Web is the Controller Layer

import com.maddy8381.PersonalProjectMngmTool.domain.Project;
import com.maddy8381.PersonalProjectMngmTool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    //Inject ProjectService onto the Controller
    @Autowired
    private ProjectService projectService;

    //ResponseEntity allows us to control resp statuses & JSON obj tht we r going to pass onto Client
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        //@Valid for good error message if invalid data
        //BindingResult is an Interface tht invokes validator on an object.

        if(result.hasErrors()){
            return new ResponseEntity<String>( "Invalid Project Object", HttpStatus.BAD_REQUEST);
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
