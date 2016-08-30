package com.leakhawk.sensor;

import com.leakhawk.aggregator.Aggregator;

public abstract class SensorJob extends Thread {

	protected int feedEntriesPerHit;
	
	protected String connectingUrl;
	
	protected Aggregator aggregator;
	
	public Aggregator getAggregator() {
		return aggregator;
	}

	public void setAggregator(Aggregator aggregator) {
		this.aggregator = aggregator;
	}

	protected abstract void manageDownloadProcess();
}
