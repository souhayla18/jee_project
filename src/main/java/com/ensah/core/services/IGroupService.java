package com.ensah.core.services;

import com.ensah.core.bo.Group;

import java.util.List;
import java.util.Set;


public interface IGroupService {

    public void addGroup(Group pGroup);

    public void updateGroup(Long groupId,Group pGroup);

    public List<Group> getAllGroups();

    public void deleteGroup(Long id);

    public Group getGroupById(Long id);

    public Group getGroupByTitle(String title);

//    Group addProfessorToGroup(Long groupId, Long professorId);

    public void addProfessorsToGroup(Long groupId, Set<Long> professorIds);

//    void addOneProfessorToGroup(Long groupId, Long professorId);

}
