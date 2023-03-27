package fjs.com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fjs.com.Dao.T002Dao;
import fjs.com.Dto.T002Dto;

public class T002 extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		getDataForPagination(request,mapping);
		deleteData(request, mapping);
		T002Dto searchBean = (T002Dto)form;
		String name = searchBean.getCUSTOMER_NAME();
		String sex = searchBean.getSEX();
		String birthdayFrom = request.getParameter("txtBirthdayFromName");
		String birthdayTo = request.getParameter("txtBirthdayToName");
		
		String page2 = request.getParameter("pagepage");
        HttpSession session = request.getSession();
        try {
        	String action = searchBean.getACTION();
            if (action == null) {
            	action = "";
            }
            if (action.equals("Search")) {
            	if (page2 != null && !page2.isEmpty()) {
    	            String sex1 = (String) session.getAttribute("sex");
    	            String birthdayFrom1 = (String) session.getAttribute("birthdayFrom");
    	            String birthdayTo1 = (String) session.getAttribute("birthdayTo");
    	            processSearch(request, response, name, sex1, birthdayFrom1, birthdayTo1, page2);
    	          
    	        } else if(name != null && name != ""  || sex != null && sex != "" || birthdayFrom != null && birthdayFrom != "" || birthdayTo != null && birthdayTo != "") {
    	        	page2 = "1";
    	            request.setAttribute("nameSearch", name);
    	            request.setAttribute("sexSearch", sex);
    	            request.setAttribute("birthdayFromSearch", birthdayFrom);
    	            request.setAttribute("birthdayToSearch", birthdayTo);
    	            processSearch(request, response, name, sex, birthdayFrom, birthdayTo, page2);
    	            session.setAttribute("sex", sex);
    	            session.setAttribute("birthdayFrom", birthdayFrom);
    	            session.setAttribute("birthdayTo", birthdayTo);
    	        }
            }
        }catch (Exception e) {
			e.printStackTrace();	
		}
        return (mapping.findForward("T002"));
	}
	
	/**
	 * Lấy dữ liệu và thiết lập trang phân trang cho danh sách T002.
	 * @param request đối tượng HttpServletRequest chứa các tham số của request.
	 * @param mapping đối tượng ActionMapping đại diện cho bản đồ ánh xạ tương ứng.
	 * @return ActionForward đại diện cho trang web cần chuyển đến.
	 */
	private ActionForward getDataForPagination(HttpServletRequest request, ActionMapping mapping) {
		// Lấy chỉ số trang hiện tại được yêu cầu từ request hoặc mặc định là trang đầu tiên.
		String indexPage = request.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		
		// Lấy số trang cần hiển thị từ request hoặc mặc định là một trang.
		String pages = request.getParameter("page");
		if (pages == null) {
			pages= "1";
		}
		
		// Chuyển đổi index và page từ chuỗi sang số nguyên.
		int page = Integer.parseInt(pages);
		int index = Integer.parseInt(indexPage);
		
		// Thiết lập thuộc tính tag của request với giá trị là chỉ số trang hiện tại.
		request.setAttribute("tag", index);
		
		// Tạo đối tượng T002Dao để lấy dữ liệu.
		T002Dao dao = new T002Dao();
		
		// Lấy tổng số bản ghi từ cơ sở dữ liệu.
		int count = dao.getDataPage();
		
		// Tính toán số trang cuối cùng.
		int endpage = count/5;
		if (count % 5 != 0) {
			endpage++;
		}
		
		// Lấy danh sách bản ghi cho trang hiện tại và thiết lập thuộc tính ListData của request với danh sách đó.
		List<T002Dto> listPage = dao.pagingData(index);
		request.setAttribute("ListData", listPage);
		
		// Thiết lập thuộc tính endP của request với giá trị là số trang cuối cùng.
		request.setAttribute("endP", endpage);
		
		// Chuyển hướng đến trang T002.
		return mapping.findForward("T002");
	}

	/**
		Xử lý tìm kiếm.
		@param request Đối tượng HttpServletRequest để lấy dữ liệu từ request.
		@param response Đối tượng HttpServletResponse để trả về kết quả.
		@param name Tên người dùng cần tìm kiếm.
		@param sex Giới tính của người dùng cần tìm kiếm.
		@param birthdayFrom Ngày sinh bắt đầu của người dùng cần tìm kiếm.
		@param birthdayTo Ngày sinh kết thúc của người dùng cần tìm kiếm.
		@param page Số trang hiện tại của kết quả tìm kiếm.
	*/
	private void processSearch(HttpServletRequest request, HttpServletResponse response, String name, String sex, String birthdayFrom, String birthdayTo, String page) {
	    T002Dao daoSearch = new T002Dao();
	    List<T002Dto> searchResults = daoSearch.getDataSearch(name, sex, birthdayFrom, birthdayTo);
	    int recordsPerPage = 5;
	    int currentPage = 1;
	    int startIndex = 0;
	    int endIndex = 0;
	    int totalRecords = searchResults.size();
	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
	    
	    try {
	        currentPage = Integer.parseInt(page);
	    } catch (NumberFormatException e) {
	        // Xử lý ngoại lệ khi giá trị của biến page không hợp lệ
	    }
	    
	    if (currentPage <= 0 || currentPage > totalPages) {
	        // Xử lý trường hợp giá trị của biến page không hợp lệ
	        currentPage = 1;
	    }
	    
	    startIndex = (currentPage - 1) * recordsPerPage;
	    endIndex = Math.min(startIndex + recordsPerPage, totalRecords);
	    
	    List<T002Dto> paginatedResults = searchResults.subList(startIndex, endIndex);
	    
	    if (paginatedResults.size() == 0) {
	        request.setAttribute("buttonDelete", paginatedResults);
	    }
	    
	    request.setAttribute("ListSearch", paginatedResults);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("recordsPerPage", recordsPerPage);
	    request.setAttribute("totalRecords", totalRecords);
	    request.setAttribute("totalPages", totalPages);
	}

	
	
	/**
		Xóa dữ liệu khỏi cơ sở dữ liệu dựa trên các ID được chọn trong yêu cầu và trả về danh sách lỗi (nếu có)
		@param request Yêu cầu HTTP được gửi đến servlet
		@param mapping Ánh xạ hành động được sử dụng để ánh xạ yêu cầu này
		@return Danh sách lỗi nếu có lỗi xảy ra, ngược lại trả về null
	*/
	
	private ActionErrors deleteData(HttpServletRequest request, ActionMapping mapping) {
	    T002Dao daoSearch = new T002Dao();
	    ActionErrors errors = new ActionErrors();
	    String[] ids = request.getParameterValues("selectedValues");
	    if (ids == null) {
	    	
	    	errors.add("logerError", new ActionMessage("MESSAGE_CHECKOFF"));
	    	return errors;
	    }
	    if (ids != null && ids.length > 0) {
	        String idList = String.join(",", ids);
	        idList = idList.replace("[\"", "").replace("\"]", "");
	        String result = idList.replaceAll("[^\\d,]", "").replace("\"", "");
	        String[] result2 = result.split(",");
	        daoSearch.deleteData(result2);
	    }
	    return errors;
	}
}
