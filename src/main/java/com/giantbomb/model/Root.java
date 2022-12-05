package com.giantbomb.model;

import java.util.ArrayList;

public class Root{
    public String error;
    public int limit;
    public int offset;
    public int number_of_page_results;
    public int number_of_total_results;
    public int status_code;
    public ArrayList<Result> results;
    public String version;
	@Override
	public String toString() {
		return "Root [error=" + error + ", limit=" + limit + ", offset=" + offset + ", number_of_page_results="
				+ number_of_page_results + ", number_of_total_results=" + number_of_total_results + ", status_code="
				+ status_code + ", results=" + results + ", version=" + version + "]";
	}
    
    
}

