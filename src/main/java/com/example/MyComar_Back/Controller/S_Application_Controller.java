package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Job_Opening;
import com.example.MyComar_Back.Entities.Spontaneous_application;
import com.example.MyComar_Back.SpringEmail.EmailInterface;
import com.example.MyComar_Back.reposotoryInterface.S_Application_Repo_Interface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping(value="/User")
public class S_Application_Controller {
    @Autowired
    private S_Application_Repo_Interface s_application_repo_interface;
    private final EmailInterface email;

    @GetMapping(value = "/Find_S_Job_Application/{id}")
    public Optional<Spontaneous_application> Find_S_Application(@PathVariable(value = "id") Long id) {
        return s_application_repo_interface.Find_S_Application(id);
    }

    @GetMapping(value = "/searchSponapp/{vacancy}")
    public String  Searchsponapp (@PathVariable(value = "vacancy") Job_Opening vacancy) {
        List<Spontaneous_application> allsapp= s_application_repo_interface.List_S_Application();
        for (Spontaneous_application sapp : allsapp) {
            if ((sapp.getJob_Type().equals(vacancy.getJob_type()))&&
                    (sapp.getRelevant_Education_Level()>=vacancy.getRelevant_Education_Level())&&
                    (sapp.getRelevant_Experience_Level()>=vacancy.getRelevant_Experience_Level()))
                this.email.SendEmail(sapp.getId_User().getId_account().getEmail(),"Comar Vacancy recommendation",
                        "According to a spontaneous application you have sent to our platform we recommend you check out this " +
                                "new vacancy that just opened up for " + vacancy.getJob_type() + " in the department of " + vacancy.getDepartment()+
                        " that consists of " +
                        vacancy.getDescription());
        }
        return "Recommendations complete";    }

    @PostMapping("/Add_S_Application")
    public Spontaneous_application Create_S_Application(@Valid @RequestBody Spontaneous_application spontaneous_application) {
        s_application_repo_interface.save_S_Application(spontaneous_application);
        return spontaneous_application;
    }

    @PutMapping("/Update_S_Application")
    public String Update_S_Application(@PathVariable @RequestBody Spontaneous_application spontaneous_application) {
        s_application_repo_interface.update_S_Application(spontaneous_application);
        return "success:Update Done!";
    }

    @GetMapping("/GetALL_S_Application")
    public List<Spontaneous_application> Get_All_S_Application() {
        return s_application_repo_interface.List_S_Application();
    }

    @DeleteMapping(value = "/Delete_S_Application/{id}")
    public String Delete_S_Application(@PathVariable String id) {
        s_application_repo_interface.remove_S_Application(Long.parseLong(id));
        return "Spontaneous Application Deleted!";
    }
}