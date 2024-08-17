package com.form.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Query;
import com.form.entities.Reply;
import com.form.service.EmailService;
import com.form.service.PersonalService;
import com.form.service.QueryService;
import com.form.service.TokenService;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin("http://localhost:4200")
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private ReplyController replyController;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/registerquery")
	public Query saveQuery(@RequestBody Query query , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Query queryObj = null;
		
		String id = null;
		
		tokenService.validateToken(query.getUserId(), tokenId);
		
		if(tokenId!=null && query.getUserId()!=null) {
			
			if(query.getCategory()!=null && query.getDescription()!=null) {
				
				String random = new DecimalFormat("000000").format(new Random().nextInt(999999));
				id = "REQ0002024000" + random;
				
				query.setId(id);
				query.setStatus("Open");
				
				String queryDate = getDate();
				
				Date date = new Date();
		        query.setQueryDate(queryDate);
		        query.setQueryTime(date);
				query.setAction("New");
				
				queryObj = queryService.saveQuery(query);
			}
			else {
				throw new NullPointerException("Query category and description can't be empty !!!");
			}
			
			String subject = "SYN Portal: Your query has been submitted successfully!";
			
			String firstName = personalService.getPersonalByUserId(query.getUserId()).getFirstname();
			String middleName = personalService.getPersonalByUserId(query.getUserId()).getMiddlename();
			String lastName = personalService.getPersonalByUserId(query.getUserId()).getLastname();
			
			String emailId = personalService.getPersonalByUserId(query.getUserId()).getEmail();
			
			String body = "Dear "+firstName+" "+middleName+" "+lastName+" "+",\n\n"
					+ 		"Greetings from SYN Portal!\n\n"
					+ 		"Your query has been submitted successfully with Request ID "+id+" .\n\n"
					+ 		"For more details, please navigate to the Trainee Help Desk >> View Request tab on the SYN Portal.\n\n\n"
					+ 		"Warm Regards,\n"
					+ 		"SYN Portal HM Team";
			
			emailService.sendEmail(emailId, subject , body);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return queryObj;
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
	
	@GetMapping("/getAllQuery/userId={id}")
	public List<Query> getAllQueryData(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception{
		
		tokenService.validateToken(id, tokenId);
		
		List<Query> list = null;
		
		if(tokenId!=null && id!=null) {
			
			list = queryService.getAllQueryByUserId(id);
			
			for(Query q : list) {
				if(q.getAction().equals("New")) {
					changeAction(q.getId(),q.getQueryTime());
				}
				
				if(q.getAction().equals("In-Progress")) {
					closeAction(q.getId(),id , q.getQueryTime());
				}
				
				if(q.getAction().equals("Closed")) {
					Reply reply = new Reply();
					reply.setRequestId(q.getId());
					replyController.saveReply(reply,id);
					
					Query query = queryService.getQuerByReqId(q.getId());
					query.setAction("Replied");
					queryService.saveQuery(query);
				}
				
			}
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return list;
	}
	
	public void changeAction(String reqId,Date queryTime) {
		
		Date currentTime = new Date();
		
		long timeDifference = currentTime.getTime() - queryTime.getTime();
		if(timeDifference/(60 * 1000)>=30) {
			Query query = queryService.getQuerByReqId(reqId);
			query.setAction("In-Progress");
			queryService.saveQuery(query);
		}
	}
	
	public void closeAction(String reqId,String userId,Date queryTime) throws MessagingException {
		
		Date currentTime = new Date();
		long timeDifference = currentTime.getTime() - queryTime.getTime();
		
		String firstName = personalService.getPersonalByUserId(userId).getFirstname();
		String middleName = personalService.getPersonalByUserId(userId).getMiddlename();
		String lastName = personalService.getPersonalByUserId(userId).getLastname();
		
		String emailId = personalService.getPersonalByUserId(userId).getEmail();
		
		if(timeDifference/(60 * 60 * 1000)>=24) {
			Query query = queryService.getQuerByReqId(reqId);
			query.setAction("Closed");
			query.setStatus("Close");
			queryService.saveQuery(query);
			
			String subject = "SYN Portal: Update regarding your CampBuzz query";
			
			String body = "Dear "+firstName+" "+middleName+" "+lastName+" "+",\n\n"
					+ 		"Greetings from SYN Portal!\n\n"
					+ 		"Your Request ID "+reqId+" has been updated as Closed.\n\n"
					+		"You can view the details by navigating to the following screen on the portal:\n\n\n"
					+ 		"Home Page >> Trainee helpdesk >> View Request\n\n\n"
					+ 		"Warm Regards,\n"
					+ 		"SYN Portal HM Team";
			
			emailService.sendEmail(emailId, subject , body);
		}
	}
}
