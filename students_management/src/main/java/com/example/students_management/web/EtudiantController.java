package com.example.students_management.web;

import com.example.students_management.entities.Etudiant;
import com.example.students_management.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class EtudiantController {
    private EtudiantRepository etudiantRepository;
    @GetMapping("/index")
    public String etudiants(Model model, @RequestParam(name ="page",defaultValue = "0") int page, @RequestParam(name ="size",defaultValue = "5") int size, @RequestParam(name ="keyWord",defaultValue = "") String keyWord){
        Page<Etudiant> pageEtudiants = etudiantRepository.findByNomContains(keyWord, PageRequest.of(page,size));
        model.addAttribute("listEtudiants",pageEtudiants.getContent());
        int nbrPage;
        if(pageEtudiants.getTotalPages()>10)nbrPage=10;
        else nbrPage=pageEtudiants.getTotalPages();
        int pages[]=new int[nbrPage];
        if(page<7){
            for(int i=0;i<nbrPage;i++){
                pages[i]=i;
            }
        }
        else if(page>=7 && page<pageEtudiants.getTotalPages()-5){
            for(int j=0;j<nbrPage;j++){
                pages[j]=page-5+j;
            }

        }
        else if(page>=pageEtudiants.getTotalPages()-5){
            for(int k=0;k<nbrPage;k++){
                pages[k]=pageEtudiants.getTotalPages()-10+k;
            }
        }
        model.addAttribute("pages",pages);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyWord",keyWord);
        model.addAttribute("totalPages",pageEtudiants.getTotalPages());
        return "etudiants";
    }
    //methode to save student
    @PostMapping("/save")
    public String save(Model model, @Valid  Etudiant etudiant, BindingResult bindingResult,@RequestParam(name ="page",defaultValue = "0") int page,@RequestParam(name ="keyWord",defaultValue = " ") String keyWord){
        if(bindingResult.hasErrors())  return "addFormEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/index?page="+page+"&keyWord="+keyWord;
    }
    //methode to get addFormEtudiant view :
    @GetMapping("/addEtudiant")
    public String addFormEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "addFormEtudiant";
    }
    //edit function :
    @GetMapping("/editEtudiant")
    public String editEtudiant(Model model,Long id,int page,String keyWord){
        Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyWord",keyWord);
        if(etudiant==null) throw new RuntimeException("etudiant introuvable!");
        return "editFormPage";
    }
    //methode to delete a student :
    @GetMapping("/delete")
    public String delete(Long id,int page,String keyWord){
        etudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyWord="+keyWord;}
    }

