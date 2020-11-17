package com.zerock.myweb01.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerock.myweb01.domain.EmpVO;
import com.zerock.myweb01.exception.DAOException;
import com.zerock.myweb01.exception.ServiceException;
import com.zerock.myweb01.persistence.EmpInfoDAO;
import com.zerock.myweb01.persistence.EmpInfoDAOImpl;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public class ListService implements Service {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServiceException {
		
		log.info("==================================================");
		log.info("List - execute(request, response) invoked...");		
		
		//***********************
		//	비지니스 로직 수행		*
		//***********************
		//필요시, 뒤에있는 DAO 객체를 이용해서 DB처리를 수행해야함		
		EmpInfoDAO dao = new EmpInfoDAOImpl();
		
		
		//비지니스 로직 처리결과(Model)
		try {
			List<EmpVO> employees = dao.list();
			
			//Request Scope에 Model 바인딩
			request.setAttribute("_RESULT_",  employees);
			
		} catch(DAOException e) {
			throw new ServiceException(e);
		}	//try-catch
		
		
		return "list";
	}	//execute

}	//end class
