package tn.esprit.projet.services;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.EtudiantRepository;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ContratServiceIMPL implements  IContratService{


    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> getAllContrat() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat addContrat(Contrat E) {
        return contratRepository.save(E);
    }

    @Override
    public Contrat updateContrat(Contrat E) {
        return contratRepository.save(E);
    }

    @Override
    public void deleteContrat(long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Contrat getContratbyid(long id) {

        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String prenomE) {
        //Contrat contrat = this.contratRepository.findById(ce.getIdContrat()).orElse(null);
        Etudiant etudiant=etudiantRepository.findEtudiantByprenom(prenomE);
        if (etudiant.getContrats().size()<5) {
            ce.setEtudiant(etudiant);
            contratRepository.save(ce);
        }
        else{
            System.out.println("Cannot Affect anymore");
        }

        return ce;
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        int j=0 ;

       List< Contrat> contrat= contratRepository.findAll();

       for(int i=0;i<contrat.size();i++){
           Contrat ct=contrat.get(i);


               if(ct.isArchive()==false){
                   j++;
                   System.out.println("famaa "+j+"contrat dispo");

               }

       }
       return j;
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        float CA=0;
        int nbOfMonths=1;

        List< Contrat> contrat= contratRepository.findAll();
        for(int i=0;i<contrat.size();i++){
        Contrat ct=contrat.get(i);
        int dd=Integer.parseInt(ct.getDateDebutContrat().toString().substring(5,7));
        int df=Integer.parseInt(ct.getDateFinContrat().toString().substring(5,7));

           if((nbOfMonths*=(df-dd))==0){
                    nbOfMonths=1;
                }else{
                    nbOfMonths=(df-dd);
                }
                    if(ct.isArchive()==false){

                        System.out.println("*******"+nbOfMonths);

                        if(ct.getSpecialite().toString()=="IA"){
                            CA+=nbOfMonths*300;

                        }
                        else if(ct.getSpecialite().toString()=="RESEAUX"){
                            CA+=nbOfMonths*350;
                        }
                        else if(ct.getSpecialite().toString()=="CLOUD"){
                            CA+=nbOfMonths*400;
                        }
                        else if(ct.getSpecialite().toString()=="SECURITE"){
                            CA+=nbOfMonths*450;
                        }

                    }

        }
        return CA;
    }

    @Scheduled( cron = "0 0 13 * * *")
    public  void retrieveAndUpdateStatusContrat(){
        List< Contrat> contrat= contratRepository.findAll();
        for(int i=0;i<contrat.size();i++) {
            Contrat ct = contrat.get(i);
            long daysBetween = ct.getDateFinContrat().getTime()-ct.getDateDebutContrat().getTime() ;
            long nbOfDays=TimeUnit.DAYS.convert(daysBetween,TimeUnit.MILLISECONDS);
               if(nbOfDays==15){
                    ct.setArchive(true);
                    System.out.println("dateFin:"+ct.getDateFinContrat());
                    System.out.println("Specialite:"+ct.getSpecialite());
                    System.out.println("Etudiant concerné:"+ct.getEtudiant().getNom());
            }
        }
    }
}
