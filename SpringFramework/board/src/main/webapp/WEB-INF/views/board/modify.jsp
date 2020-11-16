<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- header -->
<%@include file="../includes/header.jsp" %>

	<script>
				
		$(function() {
			
			console.log(">>>jquery processing started...");
			
			var formObj = $("form");		//tag selector
			console.log(">>>modify.jsp::formObj: ", formObj);
			
			$('button').on('click', function(e){
				console.log(">>>e: ", e);
		
				e.preventDefault();
				
				var oper = $(this).data("oper");		//button들의 data-oper
				console.log(">>>selected operation: ", oper);
				
				switch(oper) {
					case "modify":
						formObj.attr("action", "/board/modify");
						break;
						
					case "remove":
						formObj.attr("action", "/board/remove");
						
						//필요없는 전달 파라미터는 삭제하고 전송
						formObj.find("input[name='title']").remove();
						formObj.find("textarea[name='content']").remove();
						formObj.find("input[name='writer']").remove();
						break;
						
					case "list":
						formObj.attr("action", "/board/list");
						formObj.attr("method", "get");
						
						//1st method
						formObj.find("input[name='title']").remove();
						formObj.find("textarea[name='content']").remove();
						formObj.find("input[name='writer']").remove();					
						break;
					default:
						alert("Unknown button clicked.\nPlease confirm!!");
						return;
				}	//switch(oper)
				
				formObj.submit();		//form submitting in direct.
				console.log(">>>formObj submitted.");
			
			});	//.on
			
			console.log(">>>jquery processing ended.");
		});	//				jQuery
		
	</script>
	
	<%-- card-header --%>
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Board Modify</h6>
	</div>
	<%-- End of card-header --%>
	
	<%-- card-body --%>
	<div class="card-body">
	
		<div class="table-responsive">
		
			<form role="form" action="#" method="post">
				
				<div class="form-group">
					<label>Bno</label>
					<input 
						class="form-control" 
						name="bno"
						value='<c:out value="${board.bno}" /> '
						readonly="readonly">
						
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Title</label>
					<input 
						class="form-control" 
						name="title"
						value='<c:out value="${board.title}" /> '
						>
						
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Content</label>
					<textarea 
						class="form-control" 
						name="content" 
						rows="3"
						>
						
						<c:out value="${board.content}" />	
					</textarea>
						
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Writer</label>
					<input 
						class="form-control" 
						name="writer"
						value='<c:out value="${board.writer}" /> '
						readonly="readonly">
					
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<button 
						data-oper="modify" 
						class="btn btn-warning"> Modify </button>
						
					<button 
						data-oper="remove" 
						class="btn btn-danger"> Remove </button>
						
					<button 
						data-oper="list" 
						class="btn btn-info"> List </button>
					
				</div>
				
			</form>
				
		</div>
		<!-- End of table-responsive -->
	</div>
	<%-- End of card-body --%>


<!-- footer -->
<%@include file="../includes/footer.jsp" %>