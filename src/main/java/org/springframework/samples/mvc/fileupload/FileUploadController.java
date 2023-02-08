package org.springframework.samples.mvc.fileupload;

import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		System.out.println("modelajax");
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@GetMapping
	public void fileUploadForm() {
		System.out.println("fileUploadForm");
	}

	@PostMapping
	public void processUpload(@RequestParam MultipartFile file, Model model) {
		System.out.println("post---");
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}

}
