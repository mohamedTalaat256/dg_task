package com.example.dg_task.controller;

import com.example.dg_task.DTO.Pair;
import com.example.dg_task.enumeration.CommunicationType;
import com.example.dg_task.enumeration.ContactType;
import com.example.dg_task.enumeration.CountryCode;
import com.example.dg_task.enumeration.Gender;
import com.example.dg_task.serviceImpl.EntityService;
import com.example.dg_task.utilis.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")

public class SelectOptionsController {


    @GetMapping("/select_options/contact_types")
    public ResponseEntity<Object> getContactType(){


        List<Pair> EXPECTED_LIST = new ArrayList<>();

        for(ContactType item : ContactType.values())
        {
            EXPECTED_LIST.add(new Pair( item.name() , item.getDescribtion()));
        }

        return AppResponse.generateResponse("data", HttpStatus.OK, EXPECTED_LIST, true);
    }


    @GetMapping("/select_options/Communication_types")
    public ResponseEntity<Object> getCommunicationType(){


        List<Pair> EXPECTED_LIST = new ArrayList<>();

        for(CommunicationType item : CommunicationType.values())
        {
            EXPECTED_LIST.add(new Pair( item.name() , item.getDescribtion()));
        }

        return AppResponse.generateResponse("success", HttpStatus.OK, EXPECTED_LIST, true);
    }

    @GetMapping("/select_options/genders")
    public ResponseEntity<Object> getGenders(){


        List<Pair> EXPECTED_LIST = new ArrayList<>();

        for(Gender item : Gender.values())
        {
            EXPECTED_LIST.add(new Pair( item.name() , item.getDescribtion()));
        }

        return AppResponse.generateResponse("success", HttpStatus.OK, EXPECTED_LIST, true);
    }


    @GetMapping("/select_options/countries_codes")
    public ResponseEntity<Object> getCountriesCodes(){


        List<Pair> EXPECTED_LIST = new ArrayList<>();

        for(CountryCode item : CountryCode.values())
        {
            EXPECTED_LIST.add(new Pair( item.name() , item.getDescribtion()));
        }

        return AppResponse.generateResponse("success", HttpStatus.OK, EXPECTED_LIST, true);
    }
}
