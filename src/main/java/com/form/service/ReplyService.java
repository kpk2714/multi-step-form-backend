package com.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.entities.Reply;
import com.form.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	public Reply saveReply(Reply reply) {
		return replyRepository.save(reply);
	}
	
	public Reply getSpecificReply(String id) {
		return replyRepository.getReplyByRequestId(id);
	}
}
