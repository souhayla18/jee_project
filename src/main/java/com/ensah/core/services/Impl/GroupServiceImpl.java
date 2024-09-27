package com.ensah.core.services.Impl;

import com.ensah.core.bo.Group;
import com.ensah.core.bo.Person;
import com.ensah.core.bo.Professor;
import com.ensah.core.dao.IGroupDao;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.services.IGroupService;
import com.ensah.core.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Autowired
    IGroupDao groupDao;
    @Autowired
    IPersonDao personDao;

    public void addGroup(Group pGroup) {
        groupDao.save(pGroup);

    }

    public void updateGroup(Long groupId, Group pGroup) {
        Optional<Group> groupOptional = groupDao.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new ResourceNotFoundException("Group", "id", groupId);
        }
        Group group = groupOptional.get();

        if (pGroup.getTitle() != null) {
            group.setTitle(pGroup.getTitle());
        }

        if (pGroup.getDescription() != null) {
            group.setDescription(pGroup.getDescription());
        }
        groupDao.save(group);
    }


    public List<Group> getAllGroups() {
        return groupDao.findAll();

    }

    public void deleteGroup(Long id) {
        if (getGroupById(id) != null) {
            groupDao.deleteById(id);
        }

    }

    public Group getGroupById(Long id) {
        return groupDao.findById(id).get();

    }

    @Override
    public Group getGroupByTitle(String title) {
        return groupDao.getGroupByTitle(title);
    }

    @Override
    public void addProfessorsToGroup(Long groupId, Set<Long> professorIds) {
        Group group = getGroupById(groupId);
        if (group != null) {
            for (Long professorId : professorIds) {
                Optional<Person> personOptional = personDao.findById(professorId);
                if (personOptional.isPresent() && "Professor".equals(personOptional.get().getType())) {
                    Professor professor = (Professor) personOptional.get();
                    group.getProfessors().add(professor);
                } else {
                    throw new IllegalArgumentException("Professor with id " + professorId + " does not exist or is not a Professor");
                }
            }
            groupDao.save(group);
        } else {
            throw new IllegalArgumentException("Group with id " + groupId + " does not exist");
        }
    }


}

