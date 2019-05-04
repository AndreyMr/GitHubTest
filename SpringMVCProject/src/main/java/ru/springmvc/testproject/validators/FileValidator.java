package ru.springmvc.testproject.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object multiPartFile, Errors errors) {

		MultipartFile multiFile = (MultipartFile) multiPartFile;
		if (multiFile.getSize() == 0) {
			errors.rejectValue("file", "loginresult.salectFile", "Please select a file!");
		}

		// TODO Auto-generated method stub

	}

}
