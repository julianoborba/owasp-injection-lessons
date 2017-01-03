package br.juliano.owasp.models;

import org.springframework.ui.Model;

public class QueryParamXSSModel {

	private String body_input_param;
	private String img_src_input_param;
	private String a_href_input_param;
	private String onmouseover_input_param;
	private String js_input_param;
	private String json_input_param;
	private String style_input_param;
	private String url_param_input_param;
	private String broken_tag_input_param;
	private String comment_input_param;
	private String tag_input_param;
	private String atribute_name_input_param;

	public QueryParamXSSModel() {
	}

	public QueryParamXSSModel(String body_input_param, String img_src_input_param, String a_href_input_param,
			String onmouseover_input_param, String js_input_param, String json_input_param, String style_input_param,
			String url_param_input_param, String broken_tag_input_param, String comment_input_param,
			String tag_input_param, String atribute_name_input_param) {
		this.body_input_param = body_input_param;
		this.img_src_input_param = img_src_input_param;
		this.a_href_input_param = a_href_input_param;
		this.onmouseover_input_param = onmouseover_input_param;
		this.js_input_param = js_input_param;
		this.json_input_param = json_input_param;
		this.style_input_param = style_input_param;
		this.url_param_input_param = url_param_input_param;
		this.broken_tag_input_param = broken_tag_input_param;
		this.comment_input_param = comment_input_param;
		this.tag_input_param = tag_input_param;
		this.atribute_name_input_param = atribute_name_input_param;
	}

	public String getBody_input_param() {
		return body_input_param;
	}

	public void setBody_input_param(String body_input_param) {
		this.body_input_param = body_input_param;
	}

	public String getImg_src_input_param() {
		return img_src_input_param;
	}

	public void setImg_src_input_param(String img_src_input_param) {
		this.img_src_input_param = img_src_input_param;
	}

	public String getA_href_input_param() {
		return a_href_input_param;
	}

	public void setA_href_input_param(String a_href_input_param) {
		this.a_href_input_param = a_href_input_param;
	}

	public String getOnmouseover_input_param() {
		return onmouseover_input_param;
	}

	public void setOnmouseover_input_param(String onmouseover_input_param) {
		this.onmouseover_input_param = onmouseover_input_param;
	}

	public String getJs_input_param() {
		return js_input_param;
	}

	public void setJs_input_param(String js_input_param) {
		this.js_input_param = js_input_param;
	}

	public String getJson_input_param() {
		return json_input_param;
	}

	public void setJson_input_param(String json_input_param) {
		this.json_input_param = json_input_param;
	}

	public String getStyle_input_param() {
		return style_input_param;
	}

	public void setStyle_input_param(String style_input_param) {
		this.style_input_param = style_input_param;
	}

	public String getUrl_param_input_param() {
		return url_param_input_param;
	}

	public void setUrl_param_input_param(String url_param_input_param) {
		this.url_param_input_param = url_param_input_param;
	}

	public String getBroken_tag_input_param() {
		return broken_tag_input_param;
	}

	public void setBroken_tag_input_param(String broken_tag_input_param) {
		this.broken_tag_input_param = broken_tag_input_param;
	}

	public String getComment_input_param() {
		return comment_input_param;
	}

	public void setComment_input_param(String comment_input_param) {
		this.comment_input_param = comment_input_param;
	}

	public String getTag_input_param() {
		return tag_input_param;
	}

	public void setTag_input_param(String tag_input_param) {
		this.tag_input_param = tag_input_param;
	}

	public String getAtribute_name_input_param() {
		return atribute_name_input_param;
	}

	public void setAtribute_name_input_param(String atribute_name_input_param) {
		this.atribute_name_input_param = atribute_name_input_param;
	}

}
