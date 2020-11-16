<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- header -->
<%@include file="../includes/header.jsp" %>

	<script>
		//만일 Model로 전달된 값이 없으면, empty string('')이 넘어온다.
		var board ='<c:out value="${board}"/>';
		console.log("++++get.jsp::board: ", board);
		//---------------------------------------------//
		
		$(function() {
			
			//list
			$("#listBtn").on("click",function(e){
				console.log("++++get.jsp::onclick::e: ", e)
				self.location = "/board/list";
				alert("리스트로 돌아갑니다.");
			});	//.on
			//----------------------------------------------------------//
			
			//modify
			if(board === '' ||board ==null) {	//No board found
				alert("No board found.");
				
				$("#modifyBtn").disable = true;		//Modify 버튼 기능을 비활성화
				$("#modifyBtn").hide();				//Modify 버튼을 감춤
			}else{
				$("#modifyBtn").on('click', function (e) {
					console.log("++++get.jsp::onclick::e: ", e);
					self.location = '/board/modify?bno=<c:out value="${board.bno}" />';
				});	//.on				
			}	//if-else
			
		});	//				jQuery
		
	</script>
	
	<%-- card-header --%>
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Board Details</h6>
	</div>
	<%-- End of card-header --%>
	
	<%-- card-body --%>
	<div class="card-body">
	
		<div class="table-responsive">
		
		<%-- 	<form role="form" action="/board/register" method="post"> --%>
				
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
						readonly="readonly">
						
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Content</label>
					<textarea 
						class="form-control" 
						name="content" 
						rows="3"
						readonly="readonly">
						
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
						id="modifyBtn" 
						data-oper="modify" 
						class="btn btn-warning"> Modify </button>
						
					<button 
						id="listBtn" 
						data-oper="list" 
						class="btn btn-info"> List </button>
					
				</div>
				
		</div>
		<!-- End of table-responsive -->
	</div>
	<%-- End of card-body --%>


<!-- footer -->
<%@include file="../includes/footer.jsp" %>