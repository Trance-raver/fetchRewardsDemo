package com.route.fetchRewards.services;

import com.google.gson.Gson;
import com.route.fetchRewards.dto.SpendResponse;
import com.route.fetchRewards.dto.Transcation_DTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpendingService {
    ArrayList<SpendResponse> tempResponse = new ArrayList<>();

    public ArrayList<SpendResponse> spendPoints(ArrayList<Transcation_DTO> data, HashMap<String, Integer> tempData, int points) {
        int len = data.size();
        Gson gson = new Gson();
        for (int i = 0; i < len; i++) {
            if (points <= 0)
                break;
            if ((data.get(i).getPoints()) - points <= 0) {
                tempData.put(data.get(i).getPayer(), tempData.get(data.get(i).getPayer()) - (data.get(i).getPoints()));
                points -= (data.get(i).getPoints());
                data.get(i).setPoints(0);
            } else {
                int temp = (data.get(i).getPoints()) - points;
                tempData.put(data.get(i).getPayer(), temp - data.get(i).getPoints());
                points = 0;
                data.get(i).setPoints(temp);
            }

        }
        for (Map.Entry mapElement : tempData.entrySet()) {

            SpendResponse te = new SpendResponse();
            te.setPayer((String) mapElement.getKey());
            te.setPoints((int) mapElement.getValue());
            tempResponse.add(te);
        }

        return (tempResponse);
    }

    public HashMap<String, Integer> calPoints(HashMap<String, Integer> tempData, HashMap<String, Integer> mainData) {
        for (Map.Entry mapElement : tempData.entrySet()) {
            String key = (String) mapElement.getKey();
            mainData.put(key, mainData.get(key) + (int) mapElement.getValue());
        }
        return mainData;
    }
}
