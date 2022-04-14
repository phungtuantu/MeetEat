/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meeteat.service;

import com.meeteat.dao.CookDao;
import com.meeteat.dao.JpaTool;
import com.meeteat.dao.OfferDao;
import com.meeteat.dao.PreferenceTagDao;
import com.meeteat.dao.ReservationDao;
import com.meeteat.dao.UserDao;
import com.meeteat.model.Offer.Offer;
import com.meeteat.model.Offer.Reservation;
import com.meeteat.model.Offer.ReservationState;
import com.meeteat.model.Preference.Cuisine;
import com.meeteat.model.User.Cook;
import com.meeteat.model.User.User;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gvnge
 */
public class Service {
    
    protected UserDao userDao = new UserDao();
    protected CookDao cookDao = new CookDao();
    protected OfferDao offerDao = new OfferDao();
    protected PreferenceTagDao preferenceTagDao = new PreferenceTagDao();
    protected ReservationDao reservationDao = new ReservationDao();

    public Long createCuisine(Cuisine cuisine){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            preferenceTagDao.create(cuisine);
            JpaTool.validateTransaction();
            result = cuisine.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createCuisine", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long createAccount(User user){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            userDao.create(user);
            JpaTool.validateTransaction();
            result = user.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling createAccount", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long makeOffer(Offer offer){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            offerDao.create(offer);
            JpaTool.validateTransaction();
            result = offer.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling makeOffer", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Long approveCook(Cook cook){
        Long result = null;
        JpaTool.createPersistenceContext();
        try{
            JpaTool.openTransaction();
            cookDao.create(cook);
            JpaTool.validateTransaction();
            result = cook.getId();
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling approveCook", ex);
            JpaTool.cancelTransaction();
            result = null;
        } finally{
            JpaTool.closePersistenceContext();
        }
        return result;
    }
    
    public Offer setPrice(Long offerId, double price){
        JpaTool.createPersistenceContext();
        Offer offer = null;
        try{
            JpaTool.openTransaction();
            offer = offerDao.searchById(offerId);
            JpaTool.validateTransaction();
            offer.setPrice(price);
        } catch (Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception in calling setPrice", ex);
            JpaTool.cancelTransaction();
        } finally{
            JpaTool.closePersistenceContext();
        }
        return offer;
    }
    public User getUserFromId(Long id){
        //Might not be needed in the future, currently used for testing
        return userDao.searchById(id);
    }
    public Offer getOfferFromId(Long id){
        //Might not be needed in the future, currently used for testing
        return offerDao.searchById(id);
    }
//
//    public Long inscrireClient(Client client) {
//        Long resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            if (client.getMail().endsWith("@predictif.com")){
//                throw new Exception ("Client possede un mail de domaine predictif.com");
//            }
//            AstroNetApi astro = new AstroNetApi();
//            List<String> profil = astro.getProfil(client.getPrenom(), client.getDOB());
//            client.setProfile(new ProfileAstral(profil.get(0),profil.get(1),profil.get(2),profil.get(3)));
//            JpaUtil.ouvrirTransaction();
//            clientDao.creer(client);
//            JpaUtil.validerTransaction();
//            resultat = client.getId();
//            messageSuccesInscription(client.getPrenom(), client.getMail());
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client) ", ex);
//            JpaUtil.annulerTransaction();
//            resultat = null;
//            messageEchecInscription(client.getPrenom(), client.getMail());
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    private void messageSuccesInscription(String prenom, String mail){
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        mailWriter.println("Bonjour "+prenom+", nous vous confirmons votre inscription au service PREDICT’IF. Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums");
//
//        Message.envoyerMail(
//                "contact@predict.if",
//                mail,
//                "Bienvenue chez PREDICT’IF",
//                corps.toString()
//            );
//    }
//    
//    private void messageEchecInscription(String prenom, String mail){
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        mailWriter.println("Bonjour "+prenom+", votre inscription au service PREDICT'IF a malencontreusement échoué...\nMerci de recommencer ultérieurement");
//
//        Message.envoyerMail(
//                "contact@predict.if",
//                mail,
//                "Echec de l'inscription chez PREDICT'IF",
//                corps.toString()
//            );
//    }
//
//    private void messageNotificationEmploye(String employePrenom, String employeNom, String employeNoTelephone, String clientCivilite, String clientPrenom, String clientNom, String mediumDenom){
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        mailWriter.println("Bonjour "+employePrenom+". Consultation requise pour "+clientCivilite+" "+clientPrenom+" "+clientNom+". Médium à incarner : "+mediumDenom);
//
//        Message.envoyerNotification(
//            employePrenom,
//            employeNom,
//            employeNoTelephone,
//            corps.toString()
//        );
//        
//    }
//    
//    private void messageNotificationClient(String clientPrenom, String clientNom, String clientNoTelephone, String employeNoTelephone, String mediumDenom, Date dateEnvoiDemande){
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        mailWriter.println("Bonjour "+clientPrenom+". J’ai bien reçu votre demande de consultation du "+Message.typeDate(dateEnvoiDemande)+". Vous pouvez dès à présent me contacter au "+employeNoTelephone+". A tout de suite ! Médiumiquement vôtre, "+mediumDenom);
//
//        Message.envoyerNotification(
//            clientPrenom,
//            clientNom,
//            clientNoTelephone,
//            corps.toString()
//        );
//    }
//    
//    private void messageDemandeRejete(String clientPrenom, String clientNom, String clientNoTelephone, String mediumDenom, Date dateEnvoiDemande){
//        StringWriter corps = new StringWriter();
//        PrintWriter mailWriter = new PrintWriter(corps);
//        mailWriter.println("Bonjour "+clientPrenom+". Suite aux difficultés techniques, je ne pourrai pas suivre votre demande de consultation du "+Message.typeDate(dateEnvoiDemande)+". Veilleuz faire une nouvelle demande ultérieurement. Merci pour votre compréhension ! Médiumiquement vôtre, "+mediumDenom);
//
//        Message.envoyerNotification(
//            clientPrenom,
//            clientNom,
//            clientNoTelephone,
//            corps.toString()
//        );
//    }
//    
//    public void clientDemandeConsultation(Long clientId, Long mediumId){
//        JpaUtil.creerContextePersistance();
//        Client client = clientDao.chercherParId(clientId);
//        Medium medium = mediumDao.chercherParId(mediumId);
//        try {
//            Employe employe = rechercheEmployeConsultation(medium);
//            Consultation consultation = new Consultation (client, medium, employe,new Date());
//            JpaUtil.ouvrirTransaction();
//            employe.setDisponible(false);
//            employe.addConsultation(consultation);
//            client.addConsultation(consultation);
//            medium.addConsultation(consultation);
//            consultationDao.creer(consultation);
//            employeDao.merge(employe);
//            JpaUtil.validerTransaction();
//            messageNotificationEmploye(employe.getPrenom(),employe.getNom(),employe.getNoTelephone(),client.getCivilite(),client.getPrenom(),client.getNom(),medium.getDenom());
//        } catch (Exception ex){
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service clientDemandeConsultation", ex);
//            JpaUtil.annulerTransaction();
//            messageDemandeRejete(client.getPrenom(),client.getNom(),client.getNoTelephone(),medium.getDenom(),new Date());
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//    }
//    
//    public void consultationAccepte(Long employeId){
//        JpaUtil.creerContextePersistance();
//        try {
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            Client client = consultation.getClient();
//            Medium medium = consultation.getMedium();
//            JpaUtil.ouvrirTransaction();
//            consultation.setAccepte(1);
//            consultationDao.merge(consultation);
//            JpaUtil.validerTransaction();
//            messageNotificationClient(client.getPrenom(),client.getNom(),client.getNoTelephone(),employe.getNoTelephone(),medium.getDenom(),consultation.getDemande());
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consultationAccept", ex);
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//    }
//    
//    public void consultationRejete(Long employeId){
//        JpaUtil.creerContextePersistance();
//        try {
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            Client client = consultation.getClient();
//            Medium medium = consultation.getMedium();
//            JpaUtil.ouvrirTransaction();
//            employe.removeConsultation();
//            employe.setDisponible(true);
//            client.removeConsultation();
//            medium.removeConsultation();
//            employeDao.merge(employe);
//            clientDao.merge(client);
//            mediumDao.merge(medium);
//            consultationDao.remove(consultation);
//            JpaUtil.validerTransaction();
//            messageDemandeRejete(client.getPrenom(),client.getNom(),client.getNoTelephone(),medium.getDenom(),consultation.getDemande());
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consultationAccept", ex);
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//    }
//    
//    public void consultationDebut(Long employeId){
//        JpaUtil.creerContextePersistance();
//        try {
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            Medium medium = consultation.getMedium();
//            Client client = consultation.getClient();
//            JpaUtil.ouvrirTransaction();
//            consultation.setDebut(new Date());
//            clientDao.merge(client);
//            mediumDao.merge(medium);
//            employeDao.merge(employe);
//            consultationDao.merge(consultation);
//            JpaUtil.validerTransaction();
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consultationBegin", ex);
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//    }
//    
//    public List<String> employeDemandeAide (Long employeId, int nAmour, int nSante, int nTravail){
//        List<String> predictions;
//        JpaUtil.creerContextePersistance();
//        try {
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            Client client = consultation.getClient();
//            AstroNetApi astro = new AstroNetApi();
//            predictions = astro.getPredictions(client.getProfile().getCouleur(), client.getProfile().getAnimal(), nAmour, nSante, nTravail);
//        } catch (IOException ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service employeDemandeAide", ex);
//            predictions = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return predictions;
//    }
//    
//    public void consultationFin(Long employeId, String commentaire){
//        JpaUtil.creerContextePersistance();
//        try{
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            JpaUtil.ouvrirTransaction();
//            consultation.setFin(new Date());
//            consultation.setCommentaire(commentaire);
//            employe.setDisponible(true);
//            consultationDao.merge(consultation);
//            employeDao.merge(employe);
//            JpaUtil.validerTransaction();
//        } catch (Exception ex){
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consulterEnd", ex);
//            JpaUtil.annulerTransaction();
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//    }
//    
//    private Employe rechercheEmployeConsultation(Medium medium){
//        Employe resultat;
//        try {
//            String genre = medium.getGenre();
//            resultat = employeDao.employeSouhaite(genre);
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service employeSouhaite(genre)", ex);
//            resultat = null;
//        }
//        return resultat;
//    }
//    
//    public Loginable authentifierUtilisateur(String mail, String motDePasse) {
//        Loginable resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            if (mail.endsWith("@predictif.com")){
//                Employe employe = employeDao.chercherParMail(mail);
//                if (employe != null) {
//                    if (employe.getMotDePasse().equals(motDePasse)) {
//                        resultat = employe;
//                    }
//                }
//            } else{
//                Client client = clientDao.chercherParMail(mail);
//                if (client != null) {
//                    if (client.getMotDePasse().equals(motDePasse)) {
//                        resultat = client;
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public ProfileAstral getProfilAstralClient(Long clientId){
//        ProfileAstral resultat;
//        JpaUtil.creerContextePersistance();
//        try{
//            Client client = clientDao.chercherParId(clientId);
//            resultat = client.getProfile();
//        } catch (Exception ex){
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getProfilAstralClient", ex);
//            resultat = null;
//        } finally{
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public List<String> consultationGetClientProfil(Long employeId){
//        List<String> resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            resultat = new ArrayList<>();
//            Employe employe = employeDao.chercherParId(employeId);
//            Consultation consultation = employe.getCurrentConsultation();
//            Client client = consultation.getClient();
//            resultat.add(client.getCivilite()+" "+client.getPrenom()+" "+client.getNom());
//            resultat.add(client.getNoTelephone());
//            resultat.add(client.getProfile().toString());
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consultationAccept", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public Consultation getConsultationCourante(Long employeId){
//        Consultation resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            Employe employe = employeDao.chercherParId(employeId);
//            resultat = employe.getCurrentConsultation();
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getConsultationCourante", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public List<Employe> listerEmployes(){
//        List<Employe> resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            resultat = employeDao.listerEmployes();
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public List<Medium> listerMediumsPopulaire(int taille){
//        List<Medium> resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            List<Medium> allMediums = mediumDao.listerMediums();
//            resultat = new ArrayList();
//            for (int i=0; i<taille; i++){
//                resultat.add(allMediums.get(i));
//            }
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public Client rechercherClientParId(Long id) {
//        Client resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            resultat = clientDao.chercherParId(id);
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//
//    public Medium rechercherMediumParId(Long id) {
//        Medium resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            resultat = mediumDao.chercherParId(id);
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParId(id)", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
//    
//    public Employe rechercherEmployeParId(Long id) {
//        Employe resultat = null;
//        JpaUtil.creerContextePersistance();
//        try {
//            resultat = employeDao.chercherParId(id);
//        } catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
//            resultat = null;
//        } finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return resultat;
//    }
    
}
