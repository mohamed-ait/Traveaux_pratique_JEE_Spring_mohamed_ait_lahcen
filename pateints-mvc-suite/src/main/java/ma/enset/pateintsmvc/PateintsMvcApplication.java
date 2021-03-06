package ma.enset.pateintsmvc;

import ma.enset.pateintsmvc.entities.Patient;
import ma.enset.pateintsmvc.repositories.PatientRepository;
import ma.enset.pateintsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PateintsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PateintsMvcApplication.class, args);
    }
@Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args->{
           /* patientRepository.save(new Patient(null,"patient1",new Date(),false,120));
            patientRepository.save(new Patient(null,"patient2",new Date(),false,160));
            patientRepository.save(new Patient(null,"patient3",new Date(),true,220));
            patientRepository.save(new Patient(null,"patient4",new Date(),true,320));
           patientRepository.findAll().forEach(p->{
               System.out.println(p.getNom());
           });*/
        };

}
@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
       return args->{
           /*securityService.saveNewUser("med","med123","med123");
           securityService.saveNewUser("moha","med123","med123");
           securityService.saveNewUser("medo","med123","med123");
           securityService.saveNewRole("ADMIN","admin role description");
           securityService.saveNewRole("USER","user role description");
           securityService.addRoleToUser("med","ADMIN");
           securityService.addRoleToUser("med","USER");
           securityService.addRoleToUser("medo","USER");
           securityService.addRoleToUser("moha","USER");*/
       };
}
}
