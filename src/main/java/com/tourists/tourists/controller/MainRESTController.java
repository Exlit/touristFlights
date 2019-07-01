package com.tourists.tourists.controller;


import com.tourists.tourists.dao.TouristDaoJDBC;
import com.tourists.tourists.model.Flight;
import com.tourists.tourists.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRESTController {
    @Autowired
    private TouristDaoJDBC touristDao;

    @RequestMapping(value = "/tourists", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Tourist> getTourists() {
        List<Tourist> list = touristDao.getAllTourists();
        return list;
    }

    @RequestMapping(value = "/tourist/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Tourist getTourists(@PathVariable("Id") int id) {
        return touristDao.getTourists(id);
    }

    @RequestMapping(value = "/tourist", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Tourist addTourist(@RequestBody Tourist tourist) {
        System.out.println("(Service Side) Creating tourist with id: " + tourist.getId() + tourist.getName());

        return touristDao.addTourists(tourist);
    }

    @RequestMapping(value = "/tourist", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Tourist updateTourist(@RequestBody Tourist touristForm) {

        System.out.println("(Service Side) Creating tourist with id: " + touristForm.getId());

        return touristDao.updateTourist(touristForm);
    }


    @RequestMapping(value = "/tourist/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteTourist(@PathVariable("id") int id) {

        System.out.println("(Service Side) Deleting tourist with Id: " + id);

        touristDao.deleteTourist(id);
    }

}
