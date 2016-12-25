# owasp-injection-lessons
To mess arround with OWASP top 10 risks, this will be for injection lessons. I'm seeking a very simle way to setup the risk, find it with some tools, fix it and see that we now are a bit more aware about injections common flaws... whatever they are!

Just clone this repo and inside de project folder run mvn jetty:run.

## SQL Injections

	SQL Injections can be possible in http://localhost:8080/sqlinjection/possible
	Use the param query_param (e.g. http://localhost:8080/sqlinjection/possible?query_param=foo)
	
	SQL Injections should be impossible in http://localhost:8080/sqlinjection/impossible
	Use the param query_param (e.g. http://localhost:8080/sqlinjection/impossible?query_param=foo)

## XSS

	XSS can be possible in http://localhost:8080/xss/possible
	Use the param Use input_param (e.g. http://localhost:8080/xss/possible?input_param=foo)
	
	XSS should be impossible in http://localhost:8080/xss/impossible
	Use the params:
		body_input_param
		img_src_input_param
		a_href_input_param
		onmouseover_input_param
		js_input_param
		json_input_param
		style_input_param
		url_param_input_param
		broken_tag_input_param
		comment_input_param
		tag_input_param
		atribute_name_input_param
		