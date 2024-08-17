package com.form.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Query;
import com.form.entities.Reply;
import com.form.service.PersonalService;
import com.form.service.QueryService;
import com.form.service.ReplyService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private TokenService tokenService;
	
	
	public Reply saveReply(Reply reply , String userId) {
		Reply replyObj = null;
		
		String id = null;
		
		Query query = null;
		
		if(reply.getRequestId()!=null) {
			
			String random = new DecimalFormat("000000").format(new Random().nextInt(999999));
			id = "REP0002024000" + random;
			
			reply.setReplyId(id);
			
			query = queryService.getQuerByReqId(reply.getRequestId());
			
			reply.setDate(getDate());
			
			String description = null;
			
			String firstName = personalService.getPersonalByUserId(query.getUserId()).getFirstname();
			String middleName = personalService.getPersonalByUserId(query.getUserId()).getMiddlename();
			String lastName = personalService.getPersonalByUserId(query.getUserId()).getLastname();
			
			if(query.getAction().equals("Closed") && query.getCategory().equals("Accomodation/Transportation Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "As we already metioned in the offer letter , Candidates are required to manage the travel expenses on their own . \n"
						+ "Residential accomodation will be provided in the campus and amount of Rs. 6000 for accomodation & Rs. 950 for "
						+ "laundry per month will be deducted from your stipend during training program. \n"
						+ "Food expenses to be borne by the candidates.";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Document Upload Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. \n"
						+ "We have started verifing document of candidates in batches on the business demand. "
						+ "You will be notified once your document will verify.";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Eligibility Criteria Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. "
						+ "As we already metioned in the offer letter , Candidate's age should be less than 24 years .\n"
						+ "We will share the onboarding communication closer to your actual date of joining.";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Joining Date Postponement Request")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "As we already metioned in the offer letter , Candidates have to reach the location within the given time frame .\n "
						+ "Either Your offer will be revoked and you will not be a part of the organozation . \n"
						+ "We have started new passout batch onboarding in batches on the business demand. "
						+ "We will share the onboarding communication closer to your actual date of joining. ";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Joining Date Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. "
						+ "We have started new passout batch onboarding in batches on the business demand. "
						+ "We will share the onboarding communication closer to your actual date of joining.";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Marksheet Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. \n"
						+ "As we already metioned in the offer letter , Candidates have to take their original document in the location . \n"
						+ "We have started new passout batch onboarding in batches on the business demand. "
						+ "We will share the onboarding communication closer to your actual date of joining. ";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Passport/PAN Card/Driving License Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. \n"
						+ "As we already metioned in the offer letter , Candidates have to take their original document in the location . \n"
						+ "We have started new passout batch onboarding in batches on the business demand. "
						+ "We will share the onboarding communication closer to your actual date of joining. ";
			}
			else if(query.getAction().equals("Closed") && query.getCategory().equals("Training Related")) {
				description = "Dear "+firstName+" "+middleName+" "+lastName+", \n"
						+ "Thank you for your interest towards SYN. Please be assured that your offer is active. "
						+ "We understand that you have completed your exams and are eagerly waiting to join us. \n"
						+ "As we already metioned in the offer letter , there will be three months of training . "
						+ "During this three months , Candidates have to undergo in different exams , technical and non-technical trainings. \n "
						+ "We have started new passout batch onboarding in batches on the business demand. "
						+ "We will share the onboarding communication closer to your actual date of joining. ";
			}
			
			
			reply.setDescription(description);
			
			replyObj = replyService.saveReply(reply);
		}
		else {
			throw new NullPointerException("Reply's requestId can't be empty !!!");
		}
		
		return replyObj;
	}
	
	@SuppressWarnings("deprecation")
	public String getDate() {
		Date date = new Date();
		
		int cdate = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
		
		String cmonth = "";
		
		switch(month) {
			case 1 : 	cmonth = "Jan";
						break;
						
			case 2 :	cmonth = "Feb";
						break;
						
			case 3 :	cmonth = "Mar";
						break;
						
			case 4 :	cmonth = "Apr";
						break;
						
			case 5 :	cmonth = "May";
						break;
						
			case 6 :	cmonth = "June";
						break;
						
			case 7 :	cmonth = "July";
						break;
						
			case 8 :	cmonth = "Aug";
						break;
						
			case 9 :	cmonth = "Sept";
						break;
						
			case 10 :	cmonth = "Oct";
						break;
						
			case 11 :	cmonth = "Nov";
						break;
						
			case 12 :	cmonth = "Dec";
						break;
		}
		
		String queryDate = cdate + " " + cmonth + " " + year;
		
		return queryDate;
	}
	
	
	@GetMapping("/getReply/requestId={id}")
	public Reply getReply(@PathVariable String id , @RequestParam("userId") String userId , @RequestHeader("Authorization") String tokenId ) throws Exception {
		
		tokenService.validateToken(userId, tokenId);
		
		Reply reply = null;
		
		if(id!=null && tokenId!=null) {
			reply = replyService.getSpecificReply(id);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return reply;
	}
	
}
