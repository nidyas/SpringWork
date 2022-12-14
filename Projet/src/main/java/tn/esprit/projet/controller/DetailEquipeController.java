package tn.esprit.projet.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.IDetailEquipeService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DetailEquipeController {



    IDetailEquipeService detailEquipeService;

    @GetMapping("/getDetEquipe")
    public List<DetailEquipe> GetDetEqp(){

        return  detailEquipeService.getAlldeqp();
    }

    @PostMapping("/addDetEquipe")
    public void  addDetEqp(@RequestBody DetailEquipe D){
      detailEquipeService.adddeqp(D);
    }


    @PutMapping("/putDetEquipe/{IdEqp}")
    public void updateDetEqp(@PathVariable("IdEqp") Long id, @RequestBody DetailEquipe C){

        C.setIdEqp(id);
        detailEquipeService.updatedeqp(C);
    }
    @DeleteMapping("/delDetEquipe/{IdEqp}")
    public  void deleteDetEqp(@PathVariable("IdEqp") Long id){

       detailEquipeService.deletedeqp(id);
    }


    @GetMapping("/getDetbyid/{IdEquipe}")
    public DetailEquipe findbyid(@PathVariable("IdEqp") Long  id){
        return  detailEquipeService.getdeqpbyid(id);
    }
    @GetMapping("/getth/{thematique}")
    public List<DetailEquipe>findbyThem(@PathVariable("thematique") String Theme){
        return  detailEquipeService.findByThematiqueLike(Theme);
    }

    @GetMapping("/getNB/{thematique}")
    Map<String, Long> countByThematique(@PathVariable("thematique") String Theme ){
        return  detailEquipeService.countByThematique(Theme);
    }


}
