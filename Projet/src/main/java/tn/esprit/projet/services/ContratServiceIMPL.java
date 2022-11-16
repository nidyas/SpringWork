package tn.esprit.projet.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.EtudiantRepository;

import java.util.List;

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

        ce.setEtudiant(etudiant);
        contratRepository.save(ce);
        return ce;
    }
}
