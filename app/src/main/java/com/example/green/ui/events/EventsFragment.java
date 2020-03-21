package com.example.green.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.green.Event;
import com.example.green.EventAdapter;
import com.example.green.R;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private EventViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.events);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // get sorted list of events
        Event event = new Event("14th December, 2019", "4:30-6:30", "Plant Trees", "Forest Bimbo");
        Event event1 = new Event("5th January, 2020", "3:00-5:00", "Beach Cleaning", "Sunnyside Beach");
        Event event2 = new Event("20th January, 2020", "1:00-2:00", "Environmental Technologies Talk by Professor Sam Lee", "Isabel Beta Theta");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event1);
        events.add(event2);
        EventAdapter eventAdapter = new EventAdapter(events);
        recyclerView.setAdapter(eventAdapter);
        return root;
    }
}