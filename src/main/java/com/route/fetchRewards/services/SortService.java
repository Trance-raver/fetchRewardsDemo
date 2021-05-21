package com.route.fetchRewards.services;

import com.route.fetchRewards.dto.Transcation_DTO;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

@Service
public class SortService {

    public ArrayList<Transcation_DTO> sort(ArrayList<Transcation_DTO> data)
    {
        Collections.sort(data, new Comparator<Transcation_DTO>() {
            DateFormat f = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            @Override
            public int compare(Transcation_DTO a,Transcation_DTO b) {

                return a.getTimestamp().compareTo(b.getTimestamp());
            }
        });
        return data;
    }
}
