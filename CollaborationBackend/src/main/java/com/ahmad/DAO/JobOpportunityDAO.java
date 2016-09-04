package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.JobOpportunity;

public interface JobOpportunityDAO {

	void saveOrUpdateJobOpportunity(JobOpportunity jobOpportunity);
	
	void deleteJobOpportunity(String jobOpportunityId);
	
	JobOpportunity getJobOpportunity(String jobOpportunityId);
	
	List<JobOpportunity> listJobOpportunities();
}
