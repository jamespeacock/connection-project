package com.gwidgets.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwidgets.client.placesAndactivities.LoginPlace;

public class MVPexample implements EntryPoint {
	private LoginPlace welcomePlace = new LoginPlace("login");
	private SimplePanel appWidget = new SimplePanel();
	
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		PlaceController controller = clientFactory.getPlaceController();
		
		EventBus bus = clientFactory.getEventBus();
        ActivityMapper activityMapper = new MyActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, bus);
        activityManager.setDisplay(appWidget);

        MyHistoryMapper historyMapper= GWT.create(MyHistoryMapper.class);
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(controller, bus, welcomePlace);
        
        RootPanel.get().add(appWidget);
        historyHandler.handleCurrentHistory();
	}
}
