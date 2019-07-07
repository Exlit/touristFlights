package com.tourists.tourists.controller;

import com.tourists.tourists.dao.FlightDaoJDBC;
import com.tourists.tourists.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class MainFlightRESTController {
    @Autowired
    private FlightDaoJDBC flightDao;

    @RequestMapping(value = "/flights", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Flight> getFlights() {
        List<Flight> list = flightDao.getAllFlights();
        return list;
    }

    @RequestMapping(value = "/flight/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Flight getFlights(@PathVariable("Id") int id) {
        return flightDao.getFlights(id);
    }

    @RequestMapping(value = "/tourist", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Flight addFlight(@RequestBody Flight flight) {
        System.out.println("(Service Side) Creating tourist with id: " + flight.getId() + flight.getName());

        return flightDao.addFlights(flight);
    }

    @RequestMapping(value = "/flight", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Flight updateFlight(@RequestBody Flight flightForm) {

        System.out.println("(Service Side) Creating tourist with id: " + flightForm.getId());

        return flightDao.updateFlight(flightForm);
    }


    @RequestMapping(value = "/flight/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteFlight(@PathVariable("id") int id) {

        System.out.println("(Service Side) Deleting tourist with Id: " + id);

        flightDao.deleteFlight(id);
    }

}

