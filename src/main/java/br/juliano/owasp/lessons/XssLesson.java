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

import br.juliano.owasp.models.QueryParamXSSModel;

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
		
		QueryParamXSSModel vuln = new QueryParamXSSModel(
				body_input_param,
				img_src_input_param,
				a_href_input_param,
				onmouseover_input_param,
				js_input_param,
				json_input_param,
				style_input_param,
				url_param_input_param,
				broken_tag_input_param,
				comment_input_param,
				tag_input_param,
				atribute_name_input_param
		);
		
		// what matters is here 
		QueryParamXSSModel safe = makeMeSafe(vuln);
		
		safeModel(safe, model);
        return "noxssview";
        
    }

	private QueryParamXSSModel makeMeSafe(QueryParamXSSModel vuln) {
		
		QueryParamXSSModel safe = new QueryParamXSSModel();
		
		// sample, encode for HTML
		safe.setBody_input_param((ESAPI.encoder().encodeForHTML(vuln.getBody_input_param())));
		safe.setComment_input_param((ESAPI.encoder().encodeForHTML(vuln.getComment_input_param())));
		safe.setTag_input_param((ESAPI.encoder().encodeForHTML(vuln.getTag_input_param())));
		safe.setAtribute_name_input_param((ESAPI.encoder().encodeForHTML(vuln.getAtribute_name_input_param())));
		
		// sample, check for valid input and encode for HTML attribute
		if (vuln.getImg_src_input_param() != null){
			boolean isValidSrcURL = ESAPI.validator().isValidInput("URLContext", vuln.getImg_src_input_param(), "URL", 255, false); 
			if (isValidSrcURL) 
				safe.setImg_src_input_param((ESAPI.encoder().encodeForHTMLAttribute(vuln.getImg_src_input_param())));
		}
		if (vuln.getA_href_input_param() != null) {
			boolean isValidHrefURL = ESAPI.validator().isValidInput("URLContext", vuln.getA_href_input_param(), "URL", 255, false); 
			if (isValidHrefURL)
				safe.setA_href_input_param((ESAPI.encoder().encodeForHTMLAttribute(vuln.getA_href_input_param())));
		}
		
		// sample, encode for JavaScript
		safe.setOnmouseover_input_param((ESAPI.encoder().encodeForJavaScript(vuln.getOnmouseover_input_param())));
		safe.setJs_input_param((ESAPI.encoder().encodeForJavaScript(vuln.getJs_input_param())));
		
		// sample, escaping JSON
		if (vuln.getJson_input_param() != null) {
			safe.setJson_input_param((vuln.getJson_input_param().replaceAll("<", "\\"+CharUtils.unicodeEscaped('<')).replaceAll(">", "\\"+CharUtils.unicodeEscaped('>'))));
		}
		
		// sample, encode for CSS
		safe.setStyle_input_param((ESAPI.encoder().encodeForCSS(vuln.getStyle_input_param())));
		
		// sample, encode for URL
		try {
			safe.setUrl_param_input_param((ESAPI.encoder().encodeForURL(vuln.getUrl_param_input_param())));
		} catch (EncodingException e) {
			e.printStackTrace();
		}
		
		// sample, dealing with unclosed HTML tags
		safe.setBroken_tag_input_param((Sanitizers.FORMATTING.and(Sanitizers.BLOCKS).sanitize(vuln.getBroken_tag_input_param())));
		
		return safe;
		
	}

	private void safeModel(QueryParamXSSModel safe, Model model) {
		
		model.addAttribute("body_input_param", safe.getBody_input_param());
		model.addAttribute("img_src_input_param", safe.getImg_src_input_param());
		model.addAttribute("a_href_input_param", safe.getA_href_input_param());
		model.addAttribute("onmouseover_input_param", safe.getOnmouseover_input_param());
		model.addAttribute("js_input_param", safe.getJs_input_param());
		model.addAttribute("json_input_param", safe.getJson_input_param());
		model.addAttribute("style_input_param", safe.getStyle_input_param());
		model.addAttribute("url_param_input_param", safe.getUrl_param_input_param());
		model.addAttribute("broken_tag_input_param", safe.getBroken_tag_input_param());
		model.addAttribute("comment_input_param", safe.getComment_input_param());
		model.addAttribute("tag_input_param", safe.getTag_input_param());
		model.addAttribute("atribute_name_input_param", safe.getAtribute_name_input_param());
		
	}

}
