package com.ensah.examenplanapp;


import com.ensah.examenplanapp.bo.*;
import com.ensah.examenplanapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude= { SecurityAutoConfiguration.class }) // Exclude SecurityAutoConfiguration
public class ExamenPlanAppApplication {

    @Autowired
    private IEnseignantRepositoryDao enseignantRepositoryDao;

    @Autowired
    private IGroupeRepositoryDao groupeRepositoryDao;

    @Autowired
    private IFiliereRepositoryDao filiereRepositoryDao;

    @Autowired
    private IDepartementRepositoryDao departementRepositoryDao;

    @Autowired
    private ICompteRepository compteRepositoryDao;

    @Autowired
    private IRepositoryDao roleRepositoryDao;
    @Autowired
    private ISalleRepositoryDao salleRepositoryDao;

    @Autowired
    private ISessionRepositoryDao sessionRepositoryDao;

    @Autowired
    private IExamenRepositoryDao examenRepositoryDao;

    @Autowired
    private ISurveillanceRepositoryDao surveillanceRepositoryDao;

    @Autowired
    private INiveauRepositoryDao niveauRepositoryDao;

    @Autowired
    private ITypeElementRepositoryDao typeElementRepositoryDao;

    @Autowired
    private ISemestreRepositoryDao semestreRepositoryDao;
    @Autowired
    private ITypeExamenRepositoryDao typeExamenRepositoryDao;
    @Autowired
    private IElementRepositoryDao elementPedagogiqueRepositoryDao;


    @Autowired
    private IAdministrateurRepositoryDao cadreAdministrateurRepositoryDao;


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ExamenPlanAppApplication.class, args);
        ExamenPlanAppApplication app = context.getBean(ExamenPlanAppApplication.class);
        app.saveNewEntities();
    }

    public void saveNewEntities() {
        // Créer un groupe
        Groupe groupe = new Groupe();
        groupe.setNomGroupe("Groupe A");

        // Créer une filière
        Filiere filiere = new Filiere();
        filiere.setNom("Informatique");
        filiere.setNombreEtudiants(100);

        // Créer un département
        Departement departement = new Departement();
        departement.setNomDep("Département Informatique");

        // Créer un compte
        Compte compte = new Compte();
        compte.setLogin("johndoe");
        compte.setPassword("password123");

        // Créer un rôle
        Role role = new Role();
        role.setNomRole("Enseignant");

        // Sauvegarder les entités dans la base de données
        groupeRepositoryDao.save(groupe);
        filiereRepositoryDao.save(filiere);
        departementRepositoryDao.save(departement);
        roleRepositoryDao.save(role);

        // Créer et configurer l'enseignant
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("Doe");
        enseignant.setPrenom("John");
        enseignant.setCin("CIN12345");
        enseignant.setEmail("john.doe@example.com");
        enseignant.setTelephone("0612345678");
        enseignant.setNomArabe("دو");
        enseignant.setPrenomArabe("جون");
        enseignant.setPhoto("https://cdn.pixabay.com/photo/2016/03/26/20/35/young-man-1281282_960_720.jpg");
        enseignant.setGrade("Docteur");
        enseignant.setHeuresEnseigneesParSemaine(20);
        enseignant.setBureau("A101");

        // Assigner les relations
        enseignant.setGroupe(groupe);
        enseignant.setFiliere(filiere);
        enseignant.setDepartement(departement);
        enseignantRepositoryDao.save(enseignant);

        // Mettre à jour le compte avec l'enseignant
        compte.setProprietaire(enseignant);
        compte.setRole(role);
        compteRepositoryDao.save(compte);

        // Créer une session
        Session session = new Session();
        session.setName("Session normale");
        sessionRepositoryDao.save(session);

        // Créer une surveillance




        // Créer un semestre
        Semestre semestre = new Semestre();
        semestre.setName("S1");
        semestreRepositoryDao.save(semestre);

        // Créer un type d'examen
        Type_Examen typeExamen = new Type_Examen();
        typeExamen.setType("devoir"); // Devoir surveillé
        typeExamenRepositoryDao.save(typeExamen);



        // Créer un type d'élément
        Type_Element typeElement = new Type_Element();
        typeElement.setTypeElement("module");
        typeElementRepositoryDao.save(typeElement);

        // Créer un niveau
        Niveau niveau = new Niveau();
        niveau.setName("Niveau 1");
        niveauRepositoryDao.save(niveau);

        // Créer une salle
        Salle salle = new Salle();
        salle.setCapacite(50);
        salle.setNom("Salle 101");
        salleRepositoryDao.save(salle);





        // Créer un élément pédagogique
        Element elementPedagogique = new Element();
        elementPedagogique.setTitle("Cours d'Algorithmique");
        elementPedagogique.setNiveau(niveau);
        elementPedagogique.setType_element(typeElement);


        elementPedagogiqueRepositoryDao.save(elementPedagogique);

        // Créer un cadre administrateur
        CadreAdministrateur cadreAdmin = new CadreAdministrateur();
        cadreAdmin.setGradeAdmin("Directeur");
        cadreAdministrateurRepositoryDao.save(cadreAdmin);


        // Créer un examen
        Examen examen = new Examen();
        examen.setDate("2024-05-20");
        examen.setHeureDebut("08:00");
        examen.setDureePrev(120);
        examen.setDureeReelle(120);
        examen.setEpreuve("Examen final");
        examen.setPv("Rien à signaler");
        examen.setRapportTextuel("Rien à signaler");

        examen.setSemestre(semestre);
        examen.setSession(session);

        examen.setTypeExamen(typeExamen);

        examen.setElement(elementPedagogique);


        examenRepositoryDao.save(examen);

        surveillance surveillance = new surveillance();
        surveillance.setNombreSurveillant(2);
        surveillance.setSalle(salle);
        surveillance.setAdmin(cadreAdmin);
        surveillance.setExamen(examen);


        surveillanceRepositoryDao.save(surveillance);
    }


}

