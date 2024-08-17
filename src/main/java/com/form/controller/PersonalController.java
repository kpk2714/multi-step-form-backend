package com.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Personal;
import com.form.service.PersonalService;
import com.form.service.TokenService;


@RestController
@CrossOrigin("http://localhost:4200")
public class PersonalController {

	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private TokenService tokenService;
	
	@PutMapping("/updatePersonal")
	public Personal updatePersonal(@RequestBody Personal personal , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(personal.getUserId(), tokenId);
		
		if(personal.getUserId()!=null && tokenId!=null) {
			Personal oldPersonal = personalService.getPersonalByUserId(personal.getUserId());
			if(oldPersonal!=null) {

				oldPersonal.setTitle(personal.getTitle());
				oldPersonal.setFirstname(personal.getFirstname());
				oldPersonal.setMiddlename(personal.getMiddlename());
				oldPersonal.setLastname(personal.getLastname());
				oldPersonal.setGender(personal.getGender());
				oldPersonal.setDob(personal.getDob());
				oldPersonal.setEmail(personal.getEmail());
				oldPersonal.setRelation(personal.getRelation());
				oldPersonal.setAlteremail(personal.getAlteremail());
				oldPersonal.setMobile(personal.getMobile());
				oldPersonal.setAltermobile(personal.getAltermobile());
				oldPersonal.setRelationpersonname(personal.getRelationpersonname());
				oldPersonal.setRelationpersonmobile(personal.getRelationpersonmobile());
				oldPersonal.setNationality(personal.getNationality());
				oldPersonal.setCitizen(personal.getCitizen());
				oldPersonal.setReligion(personal.getReligion());
				oldPersonal.setState(personal.getState());
				oldPersonal.setDistrict(personal.getDistrict());
				oldPersonal.setBlood(personal.getBlood());
				oldPersonal.setReservation(personal.getReservation());
				oldPersonal.setAadhar(personal.getAadhar());
				oldPersonal.setMarital(personal.getMarital());
				oldPersonal.setDrivinglicense(personal.getDrivinglicense());
				oldPersonal.setDrivinglicensenumber(personal.getDrivinglicensenumber());
				oldPersonal.setDrivinglicensename(personal.getDrivinglicensename());
				oldPersonal.setDrivinglicenseplace(personal.getDrivinglicenseplace());
				oldPersonal.setPancard(personal.getPancard());
				oldPersonal.setPancardnumber(personal.getPancardnumber());
				oldPersonal.setPancardname(personal.getPancardname());
				oldPersonal.setPancardplace(personal.getPancardplace());
				oldPersonal.setVotercard(personal.getVotercard());
				oldPersonal.setVotercardnumber(personal.getVotercardnumber());
				oldPersonal.setVotercardname(personal.getVotercardname());
				oldPersonal.setVotercardplace(personal.getVotercardplace());
				oldPersonal.setPassport(personal.getPassport());
				oldPersonal.setPassportnumber(personal.getPassportnumber());
				oldPersonal.setPassportname(personal.getPassportname());
				oldPersonal.setPassportplace(personal.getPassportplace());
				oldPersonal.setAddress1(personal.getAddress1());
				oldPersonal.setAddress2(personal.getAddress2());
				oldPersonal.setLandmark(personal.getLandmark());
				oldPersonal.setCountry(personal.getCountry());
				oldPersonal.setMailingstate(personal.getMailingstate());
				oldPersonal.setMailingdistrict(personal.getMailingdistrict());
				oldPersonal.setCity(personal.getCity());
				oldPersonal.setPostalcode(personal.getPostalcode());
				
				oldPersonal = personalService.savePersonal(oldPersonal);
				return oldPersonal;
			}
			else {
				throw new Exception("Personal Data is not saved . First save the data !!!");
			}
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		
	}
	
	@PostMapping("/savePersonal")
	public Personal savePersonal(@RequestBody Personal personal , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(personal.getUserId(), tokenId);
		
		Personal newPersonal = null;
		if(personal.getUserId()!=null && tokenId!=null) {
			newPersonal = personalService.savePersonal(personal);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		return newPersonal;
	}
	
	
	@GetMapping("/getPersonal/id={userId}")
	public Personal getPersonal(@PathVariable String userId ,  @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(userId, tokenId);
		
		Personal personal = null;
		if(userId!=null && tokenId!=null) {
			personal = personalService.getPersonalByUserId(userId);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return personal;
	}
}
