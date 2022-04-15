/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.console;

import com.meeteat.dao.JpaTool;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.Preference.Ingredient;
import com.meeteat.model.Preference.PreferenceTag;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import com.meeteat.service.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.meeteat.DBpopulation.DBpopulation;

/**
 *
 * @author gvnge
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBPopulation dpb = new DBPopulation();
    }
    
    public static void testCreatePref(){
        Service service = new Service();
        Cuisine cuisine = new Cuisine ("Asian shit");
        service.createCuisine(cuisine);
    }
    
    public static void testCreateAccount(){
        Service service = new Service();
        System.out.println("create an account");
        User user = new User("Bob", "Smith","here", "this city", "1010","0611","bobsmith@here.com");
        service.createAccount(user);
    }
    
    public static void testApproveCook(){
        Service service = new Service();
        System.out.println("create an account then make a cook");
        User user = new User("Bob the Second", "Smith","here","this city", "1010", "0611","bobthesecondsmith@here.com");
        service.createAccount(user);
        System.out.println("make him a cook");
        Cook cook = new Cook(user, new Date(), 0, "", "none");
        System.out.println(user);
        System.out.println(cook);
        service.approveCook(cook);
        System.out.println(cook);
    }
    
    public static void testMakeOffer(){
        Service service = new Service();
        System.out.println("make a few offers");
        long cookId = 2;
        Cook cook = service.findCookById(cookId);
        List<PreferenceTag> classifications = new LinkedList<>();
        List<Ingredient> ingredients = new LinkedList<>();
        Offer offer = new Offer(cook, new Date(), "test",5.5, 10, "bery good food", classifications, ingredients, "none", "there", "that city", "1011");
        service.makeOffer(offer);
    }
    
    public static void testSpecifyPreferences(){
        Service service = new Service();
        System.out.println("specify preferences");
        User user = new User("Bob the Second", "Smith","here","this city", "1010","0611","bobthesecondsmith@here.com");
        PreferenceTag pref1 = new PreferenceTag("viande");
        PreferenceTag pref2 = new PreferenceTag("beurre");
        PreferenceTag pref3 = new PreferenceTag("riz");
        List<PreferenceTag> prefs = new LinkedList<>();
        prefs.add(pref3);
        List<PreferenceTag> prefsChange = new LinkedList<>();
        prefsChange.add(pref1);
        prefsChange.add(pref2);
        user.setPreferences(prefs);
        System.out.println(user.getPreferences().get(0).getName());
        user = service.specifyPreferences(prefsChange, user);
        System.out.println(user.getPreferences().get(0).getName());
        System.out.println(user.getPreferences().get(1).getName());
    }
    
//    public static void initialiserClients() {
//        
//        Service service = new Service();
//        System.out.println("initialiser quelques Clients pour tester");
//        
//        Client raph = new Client("BORROTI MATIAS DANTAS", "Raphaël", "8 Rue Arago, Villeurbanne","M.","0328178508", "rborrotimatiasdantas4171@free.fr", "Raph1007","1976-07-10");
//        Client nor = new Client("OLMEADA MARAIS", "Nor", "5 Rue Léon Fabre, Villeurbanne","Mme","0418932546", "nolmeadamarais1551@gmail.com", "Nor0912","1983-12-09");
//        Client olena = new Client("RAYES GEMEZ", "Olena", "12 Rue de la Prevoyance, Villeurbanne","Mme","0532731620", "orayesgemez5313@outlook.com", "Olena2808","1992-08-28");
//        Client ainhoa = new Client("SING", "Ainhoa", "4 Rue Phelypeaux, Villeurbanne","Mme","0705224200", "asing8183@free.fr", "Ainhoa0911","1982-11-09");
//        Client david = new Client("ABDIULLINA", "David Alexander", "8 Rue Wilhelmine, Villeurbanne","M.","0590232772", "david-alexander.abdiullina@laposte.net", "David0701","1975-01-07");
//        Client moez = new Client("WOAGNER", "Moez", "6 Rue Camille Koechlin, Villeurbanne","M.","0832205629", "moez.woagner@laposte.net", "Moez1608","1984-08-16");
//        
//        service.inscrireClient(raph);
//        service.inscrireClient(nor);
//        service.inscrireClient(olena);
//        service.inscrireClient(ainhoa);
//        service.inscrireClient(david);
//        service.inscrireClient(moez);
//
//    }
//    
//    public static void initialiserMediums() {
//        System.out.println("initialiser quelques Mediums");
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP-DASI-PU");
//        EntityManager em = emf.createEntityManager();
//
//        Spirite gwen = new Spirite ("Gwenaëlle","F","Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
//        gwen.addSupport("Boule de cristal");
//        Spirite tran = new Spirite ("Professeur Tran","H","Votre avenir est devant vous : regardons-le ensemble !");
//        tran.addSupport("Marc de café");
//        tran.addSupport("Boule de cristal");
//        tran.addSupport("Oreilles de lapin");
//        
//        Cartomacien irma = new Cartomacien ("Mme Irma","F","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
//        Cartomacien endora = new Cartomacien ("Endora","F","Mes cartes répondront à toutes vos questions personnelles.");
//        
//        Astrologue serena = new Astrologue ("Serena","F","Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","École Normale Supérieure d’Astrologie (ENS-Astro)","2006");
//        Astrologue mrm = new Astrologue ("Mr M","H","Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!","Institut des Nouveaux Savoirs Astrologiques","2010");
//
//        try {
//            em.getTransaction().begin();
//            em.persist(gwen);
//            em.persist(tran);
//            em.persist(irma);
//            em.persist(endora);
//            em.persist(serena);
//            em.persist(mrm);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
//            try {
//                em.getTransaction().rollback();
//            }
//            catch (IllegalStateException ex2) {
//            }
//        } finally {
//            em.close();
//        }
//    }
//    
//    public static void initialiserEmployes() {
//        System.out.println("initialiser les Employes");
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP-DASI-PU");
//        EntityManager em = emf.createEntityManager();
//
//        Employe gaetan = new Employe("DUSIRT", "Gaëtan","H", "1 Rue Jean Zuber, Villeurbanne","0786092776", "gdusirt5774@predictif.com", "Gaetan2602","1984-02-26");
//        Employe paul = new Employe("BUISSIN", "Paul","H", "16 Rue Pascal, Villeurbanne","0677812557", "paul.buissin@predictif.com", "Paul0104","1977-04-01");
//        Employe harshawardhan = new Employe("DA PISQUALI", "Harshawardhan","H", "12 Rue Billon, Villeurbanne","0807265795", "hdapisquali5657@predictif.com", "Harshawardhan2604","1987-04-26");
//        Employe zouhair = new Employe("GIREUX", "Zouhair","H", "16 Rue du Docteur Ollier, Villeurbanne","0431819553", "zouhair.gireux@predictif.com", "Zouhair1608","1976-08-16");
//        Employe anthony = new Employe("FERRAER", "Anthony","H", "17 Rue Pelisson, Villeurbanne","0378987725", "anthony.ferraer@predictif.com", "Anthony2205","1974-05-22");
//        Employe alisson = new Employe("MEUGOL", "Alisson","F", "8 Rue du Docteur Rollet, Villeurbanne","0268863526", "alisson.meugol@predictif.com", "Alisson3010","1988-10-30");
//        Employe li = new Employe("OICHI", "Li","F", "15 Avenue Piaton, Villeurbanne","0950656137", "loichi3109@predictif.com", "Li0105","1995-05-01");
//        Employe olena = new Employe("PAMITESCU", "Olena","F", "6 Rue des Acacias, Villeurbanne","0368283469", "olena.pamitescu@predictif.com", "Olena0605","1983-05-06");
//        Employe nathalie = new Employe("YISHOA KUSSUDA", "Nathalie","F", "1 Impasse Richelieu, Villeurbanne","0138061582", "nyishoakussuda6419@predictif.com", "Nathalie2711","1985-11-27");
//        Employe sahra = new Employe("ERNAUD", "Sahra","F", "12 Rue Flachet, Villeurbanne","0691165809", "sernaud@predictif.com", "Sahra2005","1984-05-20");
//        
//        try {
//            em.getTransaction().begin();
//            em.persist(gaetan);
//            em.persist(paul);
//            em.persist(harshawardhan);
//            em.persist(zouhair);
//            em.persist(anthony);
//            em.persist(alisson);
//            em.persist(li);
//            em.persist(olena);
//            em.persist(nathalie);
//            em.persist(sahra);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
//            try {
//                em.getTransaction().rollback();
//            }
//            catch (IllegalStateException ex2) {
//            }
//        } finally {
//            em.close();
//        }
//
//    }
//    
//    public static void testerConsultationx1(long clientId, long mediumId){ //tester une seule consultation isolée
//        System.out.println("Tester le service de demande de consultation une seule fois: ");
//        try {
//            Service service = new Service();
//            //simuler un client demande une consultation après avoir choisit dans la liste
//            service.clientDemandeConsultation(clientId, mediumId);
//            Employe employe = service.rechercherClientParId(clientId).getHistoire().getLast().getEmploye();
//            Long employeId = employe.getId();
//            Thread.sleep(1000);
//            //simuler l'employé accepte la consultation
//            service.consultationAccepte(employeId);
//            Thread.sleep(1000);
//            //simuler le début de la consultation (l'employé clique sur le bouton start)
//            service.consultationDebut(employeId);
//            Thread.sleep(1000);
//            //simuler la fin de la consultation (l'employé clique sur le bouton end)
//            service.consultationFin(employeId, "pas de commentaire");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//    public static void testerConsultationMultiple(){
//        //simuler le cas ou 6 clients demande 6 fois les médiums de même genre (ex: 3 fois médium 2 et 3 fois médium 6)
//        System.out.println("Tester les demandes de consultation plusieurs fois en même temp: ");
//        try {
//            long mediumId1 = 2;
//            long mediumId2 = 5;
//            long clientId1 = 1;
//            long clientId2 = 2;
//            long clientId3 = 3;
//            long clientId4 = 4;
//            long clientId5 = 5;
//            long clientId6 = 6;
//            Service service = new Service();
//            //le client 1 demande le médium 2, qui sera attribué à l'employé 1, qui accepte immédiatement
//            service.clientDemandeConsultation(clientId1, mediumId1);
//            Employe employe1 = service.rechercherClientParId(clientId1).getHistoire().getLast().getEmploye();
//            Long employeId1 = employe1.getId();
//            service.consultationAccepte(employeId1);
//            //le client 2 demande le médium 2, qui sera attribué à l'employé 2, qui commence immédiatement
//            service.clientDemandeConsultation(clientId2, mediumId1);
//            Employe employe2 = service.rechercherClientParId(clientId2).getHistoire().getLast().getEmploye();
//            Long employeId2 = employe2.getId();
//            service.consultationAccepte(employeId2);
//            service.consultationDebut(employeId2);
//            //le client 3 demande le médium 2, qui sera attribué à l'employé 3
//            service.clientDemandeConsultation(clientId3, mediumId1);
//            Employe employe3 = service.rechercherClientParId(clientId3).getHistoire().getLast().getEmploye();
//            Long employeId3 = employe3.getId();
//            //le client 4 demande le médium 6, qui sera attribué à l'employé 4
//            service.clientDemandeConsultation(clientId4, mediumId2);
//            Employe employe4 = service.rechercherClientParId(clientId4).getHistoire().getLast().getEmploye();
//            Long employeId4 = employe4.getId();
//            //le client 5 demande le médium 6, qui sera attribué à l'employé 5
//            service.clientDemandeConsultation(clientId5, mediumId2);
//            Employe employe5 = service.rechercherClientParId(clientId5).getHistoire().getLast().getEmploye();
//            Long employeId5 = employe5.getId();
//            //le client 6 demande le médium 6, qui sera échoué
//            service.clientDemandeConsultation(clientId6, mediumId2);
//            Employe employe6 = service.rechercherClientParId(clientId6).getHistoire().getLast().getEmploye();
//            Thread.sleep(1000);
//            //les autres employés acceptent leurs consultations
//            service.consultationAccepte(employeId3);
//            service.consultationAccepte(employeId4);
//            service.consultationAccepte(employeId5);
//            Thread.sleep(1000);
//            //simuler le début de la consultation (l'employé clique sur le bouton start)
//            service.consultationDebut(employeId1);
//            service.consultationDebut(employeId3);
//            service.consultationDebut(employeId4);
//            service.consultationDebut(employeId5);
//            Thread.sleep(1000);
//            //simuler la fin de la consultation (l'employé clique sur le bouton end)
//            service.consultationFin(employeId1, "pas de commentaire");
//            //le client 6 demande au nouveau une consultation avec le médium 6, qui sera normalement attribué à l'employé 1
//            service.clientDemandeConsultation(clientId6, mediumId2);
//            employe6 = service.rechercherClientParId(clientId6).getHistoire().getLast().getEmploye();
//            afficherEmploye(employe1);
//            afficherEmploye(employe6);
//            Long employeId6 = employe6.getId();
//            service.consultationAccepte(employeId6);
//            service.consultationDebut(employeId6);
//            service.consultationFin(employeId2, "pas de commentaire");
//            service.consultationFin(employeId3, "pas de commentaire");
//            service.consultationFin(employeId4, "pas de commentaire");
//            service.consultationFin(employeId5, "pas de commentaire");
//            service.consultationFin(employeId6, "pas de commentaire");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public static void testerGetConsultationCourante(long employeId, long clientId, long mediumId){
//        Service service = new Service();
//        System.out.println("Tester le service getConsultationCourante: ");
//        Consultation consultation = service.getConsultationCourante(employeId);     //tester si l'employé n'a pas de consultation courante
//        System.out.println("Si l'employé n'est pas dans une consultation courante: "+consultation);
//        System.out.println();
//        service.clientDemandeConsultation(clientId, mediumId);
//        Employe employe = service.rechercherClientParId(clientId).getHistoire().getLast().getEmploye();
//        Long employeId2 = employe.getId();
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si l'employé a une consultation pas encore acceptée: "+consultation);
//        service.consultationAccepte(employeId2);
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si l'employé a une consultation acceptée: "+consultation);
//        service.consultationDebut(employeId2);
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si l'employé a une consultation en cours: "+consultation);
//        service.consultationFin(employeId2, "pas de commentaire");
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si la consultation est terminée: "+consultation);
//        System.out.println();
//        
//        service.clientDemandeConsultation(clientId, mediumId);
//        employe = service.rechercherClientParId(clientId).getHistoire().getLast().getEmploye();
//        employeId2 = employe.getId();
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si l'employé a une consultation pas encore acceptée: "+consultation);
//        service.consultationRejete(employeId2);
//        consultation = service.getConsultationCourante(employeId2);
//        System.out.println("Si la consultation a été rejetée: "+consultation);
//    }
//    
//    public static void testerEmployeDemandeAide(long clientId, long mediumId,int nAmour, int nSante, int nTravail){
//        Service service = new Service();
//        System.out.println("Tester le service employeDemandeAide: ");
//        //le client demande une consultation
//        service.clientDemandeConsultation(clientId, mediumId);
//        Employe employe = service.rechercherClientParId(clientId).getHistoire().getLast().getEmploye();
//        Long employeId = employe.getId();
//        //l'employé accepte la consultation
//        service.consultationAccepte(employeId);
//        //la consultation commence
//        service.consultationDebut(employeId);
//        //l'employé demande d'aide
//        List<String> aAfficher = service.employeDemandeAide(employeId, nAmour, nSante, nTravail);
//        aAfficher.forEach(s -> {
//            System.out.println(s);
//        });
//        service.consultationFin(employeId,"Aide utile");
//    }
//    
//    public static void testerAffichageInfoClientPendantConsultation(long clientId, long mediumId){
//        Service service = new Service();
//        System.out.println("Tester le service d'affichage les informations du client pendant une consultation: ");
//        //le client demande une consultation
//        service.clientDemandeConsultation(clientId, mediumId);
//        Employe employe = service.rechercherClientParId(clientId).getHistoire().getLast().getEmploye();
//        Long employeId = employe.getId();
//        List<String> aAfficher = service.consultationGetClientProfil(employeId);
//        aAfficher.forEach(s -> {
//            System.out.println(s);
//        });
//        //l'employé accepte la consultation
//        service.consultationAccepte(employeId);
//        //la consultation commence
//        service.consultationDebut(employeId);
//        service.consultationFin(employeId,"Infos correctement affichées");
//    }
//    
//    public static void testerSyntheses(){
//        System.out.println("Tester les services de synthèses: ");
//        Service service = new Service();
//        List<Employe> listE = service.listerEmployes();
//        List<Medium> listM = service.listerMediumsPopulaire(5);
//        System.out.println("Tous les employés, triées par son nombre de consultations: ");
//        listE.forEach(e -> {
//            System.out.println(e);
//            System.out.println();
//        });
//        System.out.println("Les 5 médiums les plus consultés: ");
//        listM.forEach(m->{
//            System.out.println(m);
//            System.out.println("Nombre de consultations: "+m.getCount());
//            System.out.println();
//        });
//    }
//    
//    public static void saisirInscriptionClient() {
//        Service service = new Service();
//
//        System.out.println();
//        System.out.println("Appuyer sur Entrée pour passer la pause...");
//        Saisie.pause();
//
//        System.out.println();
//        System.out.println("**************************");
//        System.out.println("** NOUVELLE INSCRIPTION **");
//        System.out.println("**************************");
//        System.out.println();
//
//        String nom = Saisie.lireChaine("Votre nom de famille: ");
//        String prenom = Saisie.lireChaine("Votre Prénom: ");
//        String addresse = Saisie.lireChaine("Votre addresse: ");
//        String civilite = Saisie.lireChaine("Votre civilite: ");
//        String noTelephone = Saisie.lireChaine("Votre numero de telephone: ");
//        String mail = Saisie.lireChaine("Votre addresse mail: ");
//        String motDePasse = Saisie.lireChaine("Votre mot de passe: ");
//        String dob = Saisie.lireChaine("Votre date de naissance ? (format: aaaa-mm-dd ");
//
//        Client client = new Client(nom, prenom, addresse, civilite, noTelephone, mail, motDePasse,dob);
//        
//        service.inscrireClient(client);
//        
//        afficherClient(client);
//        System.out.println();
//
//    }
//    
//    public static void login(){
//        Service service = new Service();
//        
//        System.out.println();
//        System.out.println("Appuyer sur Entrée pour passer la pause...");
//        Saisie.pause();
//
//        System.out.println();
//        System.out.println("**************************");
//        System.out.println("** LOGIN **");
//        System.out.println("**************************");
//        System.out.println();
//
//        String mail = Saisie.lireChaine("Votre mail ? ");
//        String motDePasse = Saisie.lireChaine("Votre mot de passe ? ");
//        
//        try{
//            Loginable login = service.authentifierUtilisateur(mail, motDePasse);
//            System.out.println(login);
//        } catch (Exception ex){
//            
//        }
//        
//    }
    
}
