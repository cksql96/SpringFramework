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
			$("#cancelBtn").on("click",function(){
				self.location = "/board/list";
				alert("취소합니다");
			});	//.on
			
		});	//				jQuery
	</script>
	
	<%-- card-header --%>
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Board Register</h6>
	</div>
	<%-- End of card-header --%>
	
	<%-- card-body --%>
	<div class="card-body">
	
		<div class="table-responsive">
			<form role="form" action="/board/register" method="post">
				
				<div class="form-group">
					<label>Title</label>
					<input class="form-control" name="title">
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Content</label>
					<textarea 
						class="form-control" 
						name="content" 
						rows="4"></textarea>
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<label>Writer</label>
					<input class="form-control" name="writer">
				</div>
				<!-- ///////////////////////////////////////////// -->
				
				<div class="form-group">
					<button type="submit" class="btn btn-success">Register</button>
					<button type="reset" id="cancelBtn" class="btn btn-danger">Cancel</button>
				</div>
			</form>
		</div>
		<!-- End of table-responsive -->
	</div>
	<%-- End of card-body --%>


<!-- footer -->
<%@include file="../includes/footer.jsp" %>