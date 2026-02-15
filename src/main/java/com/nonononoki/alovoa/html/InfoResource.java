package com.nonononoki.alovoa.html;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nonononoki.alovoa.Tools;
import com.nonononoki.alovoa.model.InfoDto;
import com.nonononoki.alovoa.repo.ConversationRepository;
import com.nonononoki.alovoa.repo.UserLikeRepository;
import com.nonononoki.alovoa.repo.UserRepository;

@RestController
@AllArgsConstructor
public class InfoResource {

    private UserRepository userRepo;

	private ConversationRepository conversationRepo;

	private UserLikeRepository userLikeRepo;

    @GetMapping(path = "/info", produces= MediaType.APPLICATION_JSON_VALUE)
	public InfoDto imprint() {
		return InfoDto.builder().numConfirmedUsers(userRepo.countByConfirmed(true))
						.numFemaleUser(userRepo.countByConfirmedAndGenderId(true, Tools.GENDER_FEMALE_ID))
						.numMaleUsers(userRepo.countByConfirmedAndGenderId(true, Tools.GENDER_MALE_ID))
						.numLikes(userLikeRepo.count()).numMatches(conversationRepo.count()).build();
	}
}
