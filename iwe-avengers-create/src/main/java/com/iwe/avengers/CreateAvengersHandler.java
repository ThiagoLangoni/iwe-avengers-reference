package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengersDAO;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengersDAO dao = new AvengersDAO();
	
	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {

		context.getLogger().log("[#] - Creating a new Avenger");

		final Avenger savedAvenger = dao.save(newAvenger);
	
		return HandlerResponse.builder().setObjectBody(savedAvenger).build();

	}
}
