package br.juliano.owasp.lessons;

import org.apache.commons.lang.CharUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class XssLesson {
	
	public XssLesson() {
		
	}
   
	@RequestMapping(value = "/xss/possible", method = RequestMethod.GET)
    public String sqlinjectionPossible(@RequestParam(value="input_param", required=false, defaultValue="NEVER_PUT_UNTRUSTED_DATA_HERE") String input_param, Model model) {
		
		model.addAttribute("input_param", input_param);
        return "xssview";
        
    }
	
	@RequestMapping(value = "/xss/impossible", method = RequestMethod.GET)
    public String sqlinjectionImpossible(
    		@RequestParam(value="body_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String body_input_param,
    		@RequestParam(value="img_src_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String img_src_input_param,
    		@RequestParam(value="a_href_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String a_href_input_param,
    		@RequestParam(value="onmouseover_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String onmouseover_input_param,
    		@RequestParam(value="js_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String js_input_param,
    		@RequestParam(value="json_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String json_input_param,
    		@RequestParam(value="style_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String style_input_param,
    		@RequestParam(value="url_param_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String url_param_input_param,
    		@RequestParam(value="broken_tag_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String broken_tag_input_param,
    		@RequestParam(value="comment_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String comment_input_param,
    		@RequestParam(value="tag_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String tag_input_param,
    		@RequestParam(value="atribute_name_input_param", required=false, defaultValue="TRY_PUT_UNTRUSTED_DATA_HERE") String atribute_name_input_param,
    		Model model) {
		
		String safe_body_input_param = ESAPI.encoder().encodeForHTML(body_input_param);
		String safe_img_src_input_param = "";
		if (img_src_input_param != null){
			boolean isValidSrcURL = ESAPI.validator().isValidInput("URLContext", img_src_input_param, "URL", 255, false); 
			if (isValidSrcURL) 
				safe_img_src_input_param = ESAPI.encoder().encodeForHTMLAttribute(img_src_input_param);
		}
		String safe_a_href_input_param = "";
		if (a_href_input_param != null) {
			boolean isValidHrefURL = ESAPI.validator().isValidInput("URLContext", a_href_input_param, "URL", 255, false); 
			if (isValidHrefURL)
				safe_a_href_input_param = ESAPI.encoder().encodeForHTMLAttribute(a_href_input_param);
		}
		String safe_onmouseover_input_param = ESAPI.encoder().encodeForJavaScript(onmouseover_input_param);
		String safe_js_input_param = ESAPI.encoder().encodeForJavaScript(js_input_param);
		String safe_json_input_param = "";
		if (json_input_param != null) {
			safe_json_input_param = json_input_param.replaceAll("<", "\\"+CharUtils.unicodeEscaped('<')).replaceAll(">", "\\"+CharUtils.unicodeEscaped('>'));
		}
		String safe_style_input_param = ESAPI.encoder().encodeForCSS(style_input_param);
		String safe_url_param_input_param = "";
		try {
			safe_url_param_input_param = ESAPI.encoder().encodeForURL(url_param_input_param);
		} catch (EncodingException e) {
			e.printStackTrace();
		}
		String safe_broken_tag_input_param = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS).sanitize(broken_tag_input_param);	
		String safe_comment_input_param = ESAPI.encoder().encodeForHTML(comment_input_param);
		String safe_tag_input_param = ESAPI.encoder().encodeForHTML(tag_input_param);
		String safe_atribute_name_input_param = ESAPI.encoder().encodeForHTML(atribute_name_input_param);
		
		model.addAttribute("body_input_param", safe_body_input_param);
		model.addAttribute("img_src_input_param", safe_img_src_input_param);
		model.addAttribute("a_href_input_param", safe_a_href_input_param);
		model.addAttribute("onmouseover_input_param", safe_onmouseover_input_param);
		model.addAttribute("js_input_param", safe_js_input_param);
		model.addAttribute("json_input_param", safe_json_input_param);
		model.addAttribute("style_input_param", safe_style_input_param);
		model.addAttribute("url_param_input_param", safe_url_param_input_param);
		model.addAttribute("broken_tag_input_param", safe_broken_tag_input_param);
		model.addAttribute("comment_input_param", safe_comment_input_param);
		model.addAttribute("tag_input_param", safe_tag_input_param);
		model.addAttribute("atribute_name_input_param", safe_atribute_name_input_param);
		
        return "noxssview";
        
    }

}
