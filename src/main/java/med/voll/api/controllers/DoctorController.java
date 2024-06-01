package med.voll.api.controllers;

import med.voll.api.records.doctor.DoctorsData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @PostMapping
    public void doctorsRegister(@RequestBody DoctorsData doctorsData){
        System.out.println("\n"+doctorsData+"\n");
    }

}
