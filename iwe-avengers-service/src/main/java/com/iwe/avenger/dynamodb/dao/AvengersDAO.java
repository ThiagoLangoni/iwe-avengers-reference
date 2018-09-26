package com.iwe.avenger.dynamodb.dao;

import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengersDAO {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Optional<Avenger> search(final String id) {
		final Avenger avenger = mapper.load(Avenger.class, id);
		return Optional.ofNullable(avenger);
	}

	public Avenger save(final Avenger avenger) {
		mapper.save(avenger);
		return avenger;
	}

	public void delete(Avenger input) {
		mapper.delete(input);
	}

}