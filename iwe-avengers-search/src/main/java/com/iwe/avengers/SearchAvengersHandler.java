package com.iwe.avengers;

import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengersDAO;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class SearchAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengersDAO dao = new AvengersDAO();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		final String id = avenger.getId();
		
		context.getLogger().log("[#] - Searching Avenger by Id: " + id);
		
		final Optional<Avenger> avengerRetrieved = dao.search(id);
		
		if(avengerRetrieved.isPresent()) {
			
			context.getLogger().log("[#] - Avenger found " + avengerRetrieved.get().getName());
			
			return HandlerResponse.builder().setObjectBody(avengerRetrieved.get()).build();
		}
		
		
		throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId() + " not found");
	}
}
