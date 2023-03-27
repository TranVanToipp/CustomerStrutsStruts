package fjs.com.Dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class T003Dto extends ActionForm {
	public int CUSTOMER_ID;
	public String  CUSTOMER_NAME, SEX, EMAIL, BIRTHDAY, ADDRESS;
	
	public T003Dto() {
		
	}
	
	public T003Dto(int cUSTOMER_ID, String cUSTOMER_NAME, String sEX, String eMAIL, String bIRTHDAY, String aDDRESS) {
		CUSTOMER_ID = cUSTOMER_ID;
		CUSTOMER_NAME = cUSTOMER_NAME;
		SEX = sEX;
		EMAIL = eMAIL;
		BIRTHDAY = bIRTHDAY;
		ADDRESS = aDDRESS;
	}
	public int getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}
	public void setCUSTOMER_ID(int cUSTOMER_ID) {
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
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
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
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req) {
ActionErrors errors = new ActionErrors();
		if (EMAIL != null) {
		boolean isValidEmail = checkGmailEmail(EMAIL);
		
			if (!isValidDate(BIRTHDAY)) {
				errors.add("editerror", new ActionMessage("MESSAGE_ERROR_BIRTHDAY"));
				req.setAttribute("idUser", CUSTOMER_ID);
				req.setAttribute("nameUserEdit", CUSTOMER_NAME);
				req.setAttribute("sexUserEdit", SEX);
				req.setAttribute("birthdayUserEdit", BIRTHDAY);
				req.setAttribute("emailUserEdit", EMAIL);
				req.setAttribute("addressUserEdit", ADDRESS);
				
			}else if (!isValidEmail) {
				errors.add("editerror", new ActionMessage("MESSAGE_ERROR_FORMAT_EMAIL"));
				req.setAttribute("idUser", CUSTOMER_ID);
				req.setAttribute("nameUserEdit", CUSTOMER_NAME);
				req.setAttribute("sexUserEdit", SEX);
				req.setAttribute("birthdayUserEdit", BIRTHDAY);
				req.setAttribute("emailUserEdit", EMAIL);
				req.setAttribute("addressUserEdit", ADDRESS);
			}
		}
	return errors;
		
	}
	
	private boolean checkGmailEmail(String txtEmail) {
	    String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
	    Pattern pattern = Pattern.compile(gmailRegex);
	    Matcher matcher = pattern.matcher(txtEmail);
	    return matcher.matches();
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
