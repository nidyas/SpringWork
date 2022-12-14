package tn.esprit.projet.services;

import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.entities.Equipe;

import java.util.List;
import java.util.Map;

public interface IDetailEquipeService {

    List<DetailEquipe> getAlldeqp();
    DetailEquipe adddeqp(DetailEquipe E);
    DetailEquipe updatedeqp(DetailEquipe E);
    void deletedeqp(long id);
    DetailEquipe getdeqpbyid(long id);
    List<DetailEquipe> findByThematiqueLike (String thematique);
    public Map<String, Long> countByThematique(String dept) ;



}
