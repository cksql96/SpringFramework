<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- header --%>
<%@include file="../includes/header.jsp" %>

	<script>
		
	$(document).ready(function() {	
		
		$("#regNewBtn").on('click',function() {
			self.location = "/board/register";
		});	//.on
		//--------------------------------------------------------//
		
		var result = '<c:out value="${result}" />';
		console.log('>>>result in list.jsp: ' + result);
		console.log('>>>1.history.state: ', history.state);
		
		checkModal(result);
		
		//History(Stack)에 /board/list가 쌓일때, 모달창이 필요하지 않음을 표시하는 코드
		history.replaceState( {}, null, null);
		console.log('>>>2.history.state: ', history.state);
		//----------------------------------------------------------//		
		
		function checkModal(result) {			
			//새로운 등록 이후에는, history.state 에 {} (Object)가 들어있게 됨
			//-> 모달창을 띄우지 말란 신호가 됨.
			if(result === '' || history.state) {
				return;
			}	//if
			
			if(parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
			}	//if
			
			$("#myModal").modal("show");
		}	//cheockModal
		
	});	//.ready		JQuery	
	
	</script>
	<%-- End script --%>
	

	<%-- card-header --%>
	<div class="card-header py-3">
		<span class="m-0 font-weight-bold text-primary">Board List Page</span>
		
		<%-- 게시물 등록 버튼 추가 --%>
		<span class="btn-light">
			<button id="regNewBtn" class="btn btn-danger">Register New Board</button>
		</span>
		
	</div>
	<%-- End of card-header --%>
	
	<%-- card-body --%>
	<div class="card-body">
	
		<div class="table-responsive">
			<table 
				class="table table-boardered"
				id="dataTable"
				width="100%"
				cellspacing="0">
				<%-- ================================================================== --%>
				
				<thead>
					<tr>
						<th>bno</th>
						<th>title</th>
						<th>writer</th>
						<th>insert_ts</th>
						<th>update_ts</th>
					</tr>
				</thead>
				<%-- ================================================================== --%>
				
				<tbody>
				
					<c:forEach items="${list}" var="board">
					
						<tr>
						
							<td>
								<c:out value="${board.bno}" />
							</td>
							<%-- /////////////////////////////////////// --%>
							
							<td>
								<%-- <a 
									class = "move" 
									href = '<c:out value="${board.bno}" />'  
									>	 --%>							
									
								<a 
									href='/board/get?bno=<c:out value="${board.bno}" />' 
									>
								
									<c:out value = "${board.title}" />
								</a>
							</td>
							<%-- /////////////////////////////////////// --%>
							
							<td>
								<c:out value = "${board.writer}" />
							</td>
							<%-- /////////////////////////////////////// --%>
							
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value = "${board.insert_ts}" />
							</td>
							<%-- /////////////////////////////////////// --%>
							
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value = "${board.update_ts}" />
							</td>
							
						</tr>
						
					</c:forEach>
					
				</tbody>
				<%-- ================================================================== --%>
				
				<tfoot/>
				<%-- ================================================================== --%>
				
			</table>	<%--End table table-boardered --%>
		</div>	<%-- End of table-responsive --%>
		
		
	</div>	<%-- End of card-body --%>
	
	
	<%-- ========================================================================== --%>
	<%-- ========================================================================== --%>
	
	<%-- Modal Window --%>
	<div
		class="modal fade"
		id="myModal"
		tabindex="-1"
		role="dialog"
		aria-labelledby="myModalLabel"
		aria-hidden="true">
		
		<div class="modal-dialog">
		
			<div class="modal-content">
			
				<div class="modal-header">
					<button 
						type="button" 
						class="close" 
						data-dismiss="modal"
						aria-hidden="true"> &times; </button>
				</div>	<%-- End modal header --%>
				<%-- /////////////////////////////////////// --%>
				
				<div class="modal-body">
					처리가 완료되었습니다.
				</div>	<%-- End modal body --%>
				<%-- /////////////////////////////////////// --%>
				
				<div class="modal-footer">
					<button
						type="button"
						class="btn btn-default"
						data-dismiss="modal"> Close </button>
				</div>	<%-- End modal footer --%>
			
			
			</div>	<%-- End modal content --%>		
			
		</div>	<%-- End Modal dialog --%>
		
	</div>	<%-- End Modal fade --%>
	



<%-- footer --%>
<%@include file="../includes/footer.jsp" %>
