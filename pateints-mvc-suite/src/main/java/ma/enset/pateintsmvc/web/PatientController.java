package ma.enset.pateintsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.pateintsmvc.entities.Patient;
import ma.enset.pateintsmvc.repositories.PatientRepository;
import net.bytebuddy.implementation.bytecode.Throw;
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

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String patients(Model model,@RequestParam(name ="page",defaultValue = "0") int page,@RequestParam(name ="size",defaultValue = "5") int size,@RequestParam(name ="keyWord",defaultValue = "") String keyWord){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyWord,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        int nbrPage;
        if(pagePatients.getTotalPages()>10)nbrPage=10;
        else nbrPage=pagePatients.getTotalPages();
        int pages[]=new int[nbrPage];
        if(page<7){
          for(int i=0;i<nbrPage;i++){
              pages[i]=i;
            }
        }
        else if(page>=7 && page<pagePatients.getTotalPages()-5){
            for(int j=0;j<nbrPage;j++){
                pages[j]=page-5+j;
            }

        }
        else if(page>=pagePatients.getTotalPages()-5){
            for(int k=0;k<nbrPage;k++){
                pages[k]=pagePatients.getTotalPages()-10+k;
            }
        }
        model.addAttribute("pages",pages);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyWord",keyWord);
        model.addAttribute("totalPages",pagePatients.getTotalPages());
        return "patients";
    }
    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid  Patient patient, BindingResult bindingResult,@RequestParam(name ="page",defaultValue = "0") int page,@RequestParam(name ="keyWord",defaultValue = " ") String keyWord){
        if(bindingResult.hasErrors())  return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyWord="+keyWord;
    }
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id,int page,String keyWord){
        Patient patient=patientRepository.findById(id).orElse(null);
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyWord",keyWord);
    if(patient==null) throw new RuntimeException("patient introuvable!");
        return "editFormPatients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id,int page,String keyWord){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyWord="+keyWord;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
}
