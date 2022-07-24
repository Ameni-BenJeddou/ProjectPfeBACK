package com.example.MyComar_Back.SpringEmail;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/User/Email")
public class EmailController {
    @Autowired
    private  EmailInterface email;


    @PostMapping("/sendemail")
    public String SendEmail(@RequestBody String[] message){

        email.SendEmail(message[0],message[1],message[2]);
        return "sent successfully";
    }

}
