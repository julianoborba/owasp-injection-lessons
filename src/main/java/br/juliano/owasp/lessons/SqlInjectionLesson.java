package br.juliano.owasp.lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.OracleCodec;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.julano.owasp.models.LoginModel;

@Controller
public class SqlInjectionLesson {
	
	private SessionFactory sessionFactory;
	
	public SqlInjectionLesson() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		LoginModel login = new LoginModel("email1@email.com", "password1");
		session.save(login);
		login = new LoginModel("email2@email.com", "password2");
		session.save(login);
		session.getTransaction().commit();
		session.close();
		
	}
   
	@RequestMapping(value = "/sqlinjection/possible", method = RequestMethod.GET)
    public String sqlinjectionPossible(@RequestParam(value="query_param", required=false, defaultValue="'or'1'='1") String query_param, Model model) {
        
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		List logins = session
				.createQuery("from LoginModel L where L.email = '" + query_param + "'")
		        .getResultList();
		session.getTransaction().commit();
		session.close();
		model.addAttribute("query", "from LoginModel L where L.email = '" + query_param + "'");
		model.addAttribute("logins", logins);
        return "sqlinjectionview";
        
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sqlinjection/impossible/parameterized_queries", method = RequestMethod.GET)
    public String sqlinjectionImpossibleWithParameterizedQueries(@RequestParam(value="query_param", required=false, defaultValue="'or'1'='1") String query_param, Model model) {
        
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<LoginModel> logins = session
				.createQuery("from LoginModel L where L.email = :email")
		        .setParameter("email", query_param)
		        .getResultList();
		session.getTransaction().commit();
		session.close();
		model.addAttribute("query", "from LoginModel L where L.email = :email");
		model.addAttribute("logins", logins);
        return "sqlinjectionview";
        
    }
	
	@RequestMapping(value = "/sqlinjection/impossible/escaped_inputs", method = RequestMethod.GET)
    public String sqlinjectionImpossibleWithEscapedInputs(@RequestParam(value="query_param", required=false, defaultValue="'or'1'='1") String query_param, Model model) {
        
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		List logins = new ArrayList<>();
		try {
			logins = session
				.createQuery("from LoginModel L where L.email = '" + ESAPI.encoder().encodeForSQL(new OracleCodec(), query_param) + "'")
		        .getResultList();
		} catch (IllegalArgumentException|QuerySyntaxException e) {
			System.out.println(e.getMessage());
		}
		session.getTransaction().commit();
		session.close();
		model.addAttribute("query", "from LoginModel L where L.email = '" + ESAPI.encoder().encodeForSQL(new OracleCodec(), query_param) + "'");
		model.addAttribute("logins", logins);
        return "sqlinjectionview";
        
    }
	
	@RequestMapping(value = "/sqlinjection/impossible/whitelisted_inputs", method = RequestMethod.GET)
    public String sqlinjectionImpossibleWithWhitelistedInputs(@RequestParam(value="query_param", required=false, defaultValue="'or'1'='1") String query_param, Model model) {
        
		String trollRegex = "([A-Za-z0-9\\._-])+(@)([A-Za-z\\.])+";
	    Matcher matcher = Pattern.compile(trollRegex).matcher(query_param);
		boolean found = matcher.find();
        
        Session session = sessionFactory.openSession();
		session.beginTransaction();
		List logins = !found ? new ArrayList<>() : session
				.createQuery("from LoginModel L where L.email = '" + matcher.group(0) + "'")
		        .getResultList();
		session.getTransaction().commit();
		session.close();
		model.addAttribute("query", "from LoginModel L where L.email = '" + (found ? matcher.group(0) : "") + "'");
		model.addAttribute("logins", logins);
        return "sqlinjectionview";
        
    }

}
