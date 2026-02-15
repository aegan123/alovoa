package com.nonononoki.alovoa.html;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nonononoki.alovoa.service.PublicService;

@Controller
@RequiredArgsConstructor
public class TosResource {

	@NonNull
	private PublicService publicService;

	@Value("${app.company.name}")
	private String companyName;

	@Value("${app.tos.update-date}")
	private String tosUpdateDate;

	@Value("${app.age.min}")
	private String minAge;

	private static final String COMPANY_NAME = "COMPANY_NAME";
	private static final String TOS_UPDATE_DATE = "TOS_UPDATE_DATE";
	private static final String MIN_AGE = "MIN_AGE";

	@GetMapping("/tos")
	public ModelAndView tosConditions() {
		ModelAndView mav = new ModelAndView("tos");
		String content = publicService.text("backend.tos");
		content = content.replace(COMPANY_NAME, companyName);
		content = content.replace(TOS_UPDATE_DATE, tosUpdateDate);
		content = content.replace(MIN_AGE, minAge);
		mav.addObject("content", content);
		return mav;
	}
}
