package com.sparkhunter.res;

public class SparkCreator implements EntityCreator {
	private static final String type = "SPARK";

	@Override
	public Entity createEntity() {
		return new Spark();
	}

	@Override
	public String getType() {
		return type;
	}

}
