package com.ensah.core.web.controllers;


import com.ensah.core.bo.Group;
import com.ensah.core.services.IGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/Group")
public class GroupController {

    @Autowired
    IGroupService groupService;

    @PostMapping()
    public ResponseEntity<Group> addGroupRS(@RequestBody Group group) {
        groupService.addGroup(group);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{groupnelId}")
    public ResponseEntity<Group> updateGroupRS(@PathVariable Long groupnelId,@Valid @RequestBody Group groupDetails) {
        groupService.updateGroup(groupnelId, groupDetails);
        return ResponseEntity.noContent().build();
    }



    @GetMapping
    public ResponseEntity<List<Group>> getAllGroupRS() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }



    @GetMapping("/{groupnelId}")
    public ResponseEntity<Group> getOneGroupRS(@PathVariable Long groupnelId) {
        return ResponseEntity.ok(groupService.getGroupById(groupnelId));

    }

    @DeleteMapping("/{groupnelId}")
    public ResponseEntity<Void> deleteGroupRS(@PathVariable Long groupnelId) {
        groupService.deleteGroup(groupnelId);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/addProfessors")
//    public void addProfessorsToGroup(@RequestParam Long groupId, @RequestBody List<Long> professorIds) {
//        groupService.addProfessorsToGroup(groupId, professorIds);
//    }
//
//    @PostMapping("/addProfessortogroup")
//    public void addProfessorToGroup(@RequestParam Long groupId, @RequestParam Long professorId) {
//        groupService.addOneProfessorToGroup(groupId, professorId);
//    }

//    @PutMapping("/{groupId}/addProfessortogroup/{professorId}")
//    public ResponseEntity<Group> addProfessorToGroup(@PathVariable Long groupId, @PathVariable Long professorId) {
//        return new ResponseEntity<Group>(groupService.addProfessorToGroup(groupId, professorId), HttpStatus.OK);
//    }

    @PutMapping("/{groupId}/addProfessortogroup")
    public ResponseEntity<Group> addProfessorsToGroup(@PathVariable Long groupId, @RequestBody Set<Long> professorIds) {
        groupService.addProfessorsToGroup(groupId, professorIds);
        return new ResponseEntity(HttpStatus.OK);
    }

}


