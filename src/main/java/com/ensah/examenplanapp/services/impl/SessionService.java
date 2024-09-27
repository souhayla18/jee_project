package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Session;
import com.ensah.examenplanapp.dao.ISessionRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final ISessionRepositoryDao sessionRepositoryDao;

    @Autowired
    public SessionService(ISessionRepositoryDao sessionRepositoryDao) {
        this.sessionRepositoryDao = sessionRepositoryDao;
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepositoryDao.findById(id);
    }

    public Optional<Session> getSessionByName(String name) {
        return Optional.ofNullable(sessionRepositoryDao.findByName(name));
    }

    public List<Session> getAllSessions() {
        return sessionRepositoryDao.findAll();
    }

    public Session saveSession(Session session) {
        return sessionRepositoryDao.save(session);
    }

    public void deleteSessionById(Long id) {
        sessionRepositoryDao.deleteById(id);
    }
    public void deleteSessionByName(String name) {
        Session session = sessionRepositoryDao.findByName(name);
        if (session != null) {
            sessionRepositoryDao.delete(session);
        } else {
            throw new EntityNotFoundException("Session not found with name " + name);
        }
    }

    public Session updateSession(Long id, Session session) {
        Optional<Session> existingSessionOptional = sessionRepositoryDao.findById(id);
        if (existingSessionOptional.isPresent()) {
            Session existingSession = existingSessionOptional.get();
            // Mettre à jour les champs nécessaires
            existingSession.setName(session.getName());
            // Mettre à jour d'autres champs si nécessaire

            return sessionRepositoryDao.save(existingSession);
        } else {
            // Gérer le cas où la session avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
