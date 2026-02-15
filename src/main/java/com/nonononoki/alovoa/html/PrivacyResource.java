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
public class PrivacyResource {

	@NonNull
	private PublicService publicService;

	@Value("${app.company.name}")
	private String companyName;

	@Value("${app.privacy.update-date}")
	private String privacyUpdateDate;

	private static final String COMPANY_NAME = "COMPANY_NAME";
	private static final String PRIVACY_UPDATE_DATE = "PRIVACY_UPDATE_DATE";

	@GetMapping("/privacy")
	public ModelAndView privacy() {
		ModelAndView mav = new ModelAndView("privacy");
		String content = publicService.text("backend.privacy");
		content = content.replace(COMPANY_NAME, companyName);
		content = content.replace(PRIVACY_UPDATE_DATE, privacyUpdateDate);
		mav.addObject("content", content);
		return mav;
	}
}
