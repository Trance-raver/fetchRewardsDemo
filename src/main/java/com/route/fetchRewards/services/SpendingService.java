package com.route.fetchRewards.services;

import com.route.fetchRewards.dto.SpendResponse;
import com.route.fetchRewards.dto.Transcation_DTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpendingService {


    public ArrayList<SpendResponse> spendPoints(ArrayList<Transcation_DTO> data, HashMap<String, Integer> tempData, int points, HashMap<String,Integer> mainData) {
        ArrayList<SpendResponse> tempResponse = new ArrayList<>();
        int len = data.size();
        int totalPoints=0;
       //Calculate total points of user
        for(int i=0;i<data.size();i++)
       {
           totalPoints+=data.get(i).getPoints();
       }
        //Return empty if user's current points are less than the points he wants to spend
       if(totalPoints<points) {
           System.out.println("returned for 0");
           for (Map.Entry mapElement : tempData.entrySet()) {
              mapElement.setValue(0);
           }
           return new ArrayList<SpendResponse>();
       }

       //calculate the current points after spending and return total spend points
        for (int i = 0; i < len; i++) {
            if (points <= 0)
                break;
            if ((data.get(i).getPoints()) - points <= 0) {
                tempData.put(data.get(i).getPayer(), tempData.get(data.get(i).getPayer()) - (data.get(i).getPoints()));
                points -= (data.get(i).getPoints());
                System.out.println("payer"+data.get(i).getPayer()+"points spend"+tempData.get(data.get(i).getPayer())+"current points to spend"+points);

                data.get(i).setPoints(0);
            } else {
                int temp = (data.get(i).getPoints()) - points;
                tempData.put(data.get(i).getPayer(), tempData.get(data.get(i).getPayer()) - points);
                points = 0;
                System.out.println("2ndpayer"+data.get(i).getPayer()+"points spend"+tempData.get(data.get(i).getPayer())+"current points to spend"+points);

                data.get(i).setPoints(temp);
            }

        }
        for (Map.Entry mapElement : tempData.entrySet()) {

            SpendResponse te = new SpendResponse();
            te.setPayer((String) mapElement.getKey());
            te.setPoints((int) mapElement.getValue());
            tempResponse.add(te);
        }
        for (Map.Entry mapElement : tempData.entrySet()) {
            String key = (String) mapElement.getKey();
            mainData.put(key, mainData.get(key) + (int) mapElement.getValue());
        }

        return (tempResponse);
    }

}
