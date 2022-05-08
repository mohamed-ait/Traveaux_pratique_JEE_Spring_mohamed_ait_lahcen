package ma.enset.jpaenset;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.service.UserService;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService, RoleRepository roleRepository){
        return args->{
            //create the first user
         User user1= new User();
         user1.setUserName("user");
         user1.setPassword("12345");
         userService.addNewUser(user1);
         //create the second user :
            User user2= new User();
            user2.setUserName("admin");
            user2.setPassword("12345");
            userService.addNewUser(user2);
            //create roles :
            Stream.of("USER","STUDENT","ADMIN").forEach(name->{
                Role role1=new Role();
                role1.setRoleName(name);
                role1.setDesc(name+"_desc");
                userService.addNewRole(role1);
            });
            //adding roles to users :
            try{
                userService.addRoleToUser("admin","ADMIN");
            }catch(Exception e){
                e.printStackTrace();
            }
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("user","USER");
            userService.addRoleToUser("user","STUDENT");
            try{
               User user= userService.authenticate("admin","1234");
                System.out.println("userId:"+user.getUserId());
                System.out.println("username:"+user.getUserName());
                System.out.println("roles=>");
                user.getRoles().forEach(r->{
                    System.out.println("role=>"+r.getRoleName());
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        };
}
}
