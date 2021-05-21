package com.route.fetchRewards.controller;

import com.route.fetchRewards.dto.SpendResponse;
import com.route.fetchRewards.dto.Spending;
import com.route.fetchRewards.dto.Transcation_DTO;
import com.route.fetchRewards.services.SortService;
import com.route.fetchRewards.services.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/fetch")
public class mainController {

    @Autowired
    public SortService sortService;
    @Autowired
    public SpendingService spendingService;
    ArrayList<Transcation_DTO> data = new ArrayList<>();
    HashMap<String, Integer> mainData = new HashMap<>();
    HashMap<String, Integer> tempData = new HashMap<>();

    @PostMapping("/add")
    public void add(@RequestBody Transcation_DTO transcations) {

        data.add(transcations);
        if (mainData.containsKey(transcations.getPayer())) {
            mainData.put(transcations.getPayer(), mainData.get(transcations.getPayer()) + (transcations.getPoints()));
        } else
            mainData.put(transcations.getPayer(), transcations.getPoints());

        tempData.put(transcations.getPayer(), 0);
        sortService.sort(data);

    }

    @RequestMapping(value = "/spend", method = RequestMethod.GET, produces = "application/JSON")
    public ArrayList<SpendResponse> spend(@RequestBody Spending spend) {
        int points = spend.getPoints();

        return (spendingService.spendPoints(data, tempData, points,mainData));
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = "application/JSON")
    public HashMap<String, Integer> show() {
        return mainData;
    }
}
