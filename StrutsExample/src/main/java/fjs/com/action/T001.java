package fjs.com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.tomcat.util.http.parser.AcceptLanguage;

import fjs.com.Dao.T001Dao;
import fjs.com.Dto.T001Dto;

public class T001 extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			T001Dto t001dto = (T001Dto)form;
			String user = t001dto.getUSERID();
			String pass = t001dto.getPASSWORD();
			T001Dao t001Dao = new T001Dao();
			T001Dto result = t001Dao.checkLogin(user, pass);
			if(result != null) {
				return mapping.findForward("success");
			}else {
				ActionErrors actionErrors = new ActionErrors();
				actionErrors.add("logerError", new ActionMessage("Đăng nhập không thành công!!!"));
				return mapping.findForward("fail");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
 }
