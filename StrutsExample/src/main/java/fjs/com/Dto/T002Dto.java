package fjs.com.Dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class T002Dto extends ActionForm {
public String CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, ADDRESS, ACTION;
	
	public T002Dto() {
		
	}

	public T002Dto(String cUSTOMER_ID, String cUSTOMER_NAME, String sEX, String bIRTHDAY, String aDDRESS,
			String aCTION) {
		super();
		CUSTOMER_ID = cUSTOMER_ID;
		CUSTOMER_NAME = cUSTOMER_NAME;
		SEX = sEX;
		BIRTHDAY = bIRTHDAY;
		ADDRESS = aDDRESS;
		ACTION = aCTION;
	}
	
	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}

	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	public String getSEX() {
		return SEX;
	}

	public void setSEX(String sEX) {
		SEX = sEX;
	}

	public String getBIRTHDAY() {
		return BIRTHDAY;
	}

	public void setBIRTHDAY(String bIRTHDAY) {
		BIRTHDAY = bIRTHDAY;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getACTION() {
		return ACTION;
	}

	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		 ActionErrors errors = new ActionErrors();
		 String birthdayFrom = request.getParameter("txtBirthdayFromName");
		 String birthdayTo = request.getParameter("txtBirthdayToName");
			if (birthdayFrom != null && !birthdayFrom.isEmpty() && !isValidDate(birthdayFrom)) {
				errors.add("loginError", new ActionMessage("MESSAGE_ERROR_FROM"));
		        request.setAttribute("dataFrom", birthdayFrom);
		    }
		    if (birthdayTo != null && !birthdayTo.isEmpty() && !isValidDate(birthdayTo)) {
		    	errors.add("loginError", new ActionMessage("MESSAGE_ERROR_TO"));
		        request.setAttribute("dataTo", birthdayTo);
		    }
		    if (birthdayFrom != null && birthdayTo != null && !birthdayFrom.isEmpty() && !birthdayTo.isEmpty()) {   
		        if (isValidDate(birthdayFrom) && isValidDate(birthdayTo)) {
		            String dayFrom = birthdayFrom.replaceAll("/", "");
		            String dayTo = birthdayTo.replaceAll("/", "");
		            if (Integer.parseInt(dayTo) < Integer.parseInt(dayFrom)) {
		            	errors.add("loginError", new ActionMessage("MESSAGE_ERROR_FROM_BIGGER_TO"));
		            	request.setAttribute("birthDayFromFrom", birthdayFrom);
		    	        request.setAttribute("birthDayToTo", birthdayTo);
		            }
		        }
		    }
			return errors;
		
	}
	
	private boolean isValidDate(String txtDate) {
	    String rxDatePattern = "^(\\d{4})(\\/|-)(\\d{1,2})(\\/|-)(\\d{1,2})$";
	    Pattern pattern = Pattern.compile(rxDatePattern);
	    Matcher matcher = pattern.matcher(txtDate);
	    if (!matcher.matches()) {
	        return false;
	    }
	    int dtMonth = Integer.parseInt(matcher.group(3));
	    int dtDay = Integer.parseInt(matcher.group(5));
	    int dtYear = Integer.parseInt(matcher.group(1));
	    if (dtMonth < 1 || dtMonth > 12) {
	        return false;
	    } else if (dtDay < 1 || dtDay > 31) {
	        return false;
	    } else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31) {
	        return false;
	    } else if (dtMonth == 2) {
	        boolean isLeap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	        if (dtDay > 29 || (dtDay == 29 && !isLeap)) {
	            return false;
	        }
	    }
	    return true;
	}	
	
}
