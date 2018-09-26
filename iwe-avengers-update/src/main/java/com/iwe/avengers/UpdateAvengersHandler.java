package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengersDAO;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengersDAO dao = new AvengersDAO();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		final String id = avenger.getId();

		context.getLogger().log("[#] - Searching Avenger by Id: " + id);

		if (dao.search(id).isPresent()) {

			context.getLogger().log("[#] - Avenger found " + avenger.getName() + " updating...");
			
			final Avenger updatedAvenger = dao.save(avenger);

			return HandlerResponse.builder().setObjectBody(updatedAvenger).build();
		}

		throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId() + " not found");
		
		
	}
}
