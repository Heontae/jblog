<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${session.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${session.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${session.id }/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      		
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

//화면 키면실행
$(document).ready(function(){
	
	fetchList();
	
});

$("#cateList").on("click","img",function(){
	console.log("삭제버튼클릭");
	
	var delNo = $(this).data("delno");
	
	if($(this).data("count")==0){
		$.ajax({
			
			url : "${pageContext.request.contextPath }/${session.id}/admin/delete",		
			type : "post",
			//contentType : "application/json",
			data : {cateNo: 	 delNo},

			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				
				$("#cateList tr[data-delNo='"+ delNo +"']" ).remove();
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	else{
		alert("삭제할 수 없습니다.");
	}
	
	
})

//카테고리 추가버튼 누르면 추가
$("#btnAddCate").on("click",function(){
	//이벤트체크
	console.log("카테고리추가버튼 클릭");
	event.preventDefault();
	
	//데이터 수집
	var name = $("[name=name]").val();
	var desc = $("[name=desc]").val();
	//데이터전송
	$.ajax({
		
		url : "${pageContext.request.contextPath }/${session.id}/admin/cateListAdd",		
		type : "post",
		//contentType : "application/json",
		data : {
			cateName: 	 name,
			description: desc
		},

		dataType : "json",
		success : function(cateVo){
			/*성공시 처리해야될 코드 작성*/
			render(cateVo, "up")
			$("[name=name]").val("");
			$("[name=desc]").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
})

//전체리스트 불러오기
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/${session.id}/admin/cateList",		
		type : "post",
		//contentType : "application/json",
		//data : {name: "홍길동"},

		dataType : "json",
		success : function(cateList){
			/*성공시 처리해야될 코드 작성*/
			for(var i=0; i<cateList.length; i++){
				render(cateList[i],"down");				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

//리스트 그리기(1개씩)
function render(cateList, direction){
	var count;
	console.log(cateList.postCount);
	cateList.postCount == null? count = 0 : count = cateList.postCount;
	
	var str = "";
	str += "<tr data-delNo='"+cateList.cateNo+"'>";
	str += "	<td>"+cateList.cateNo+"</td>";
	str += "	<td>"+cateList.cateName+"</td>";
	str += "	<td>"+count+"</td>";
	str += "	<td>"+cateList.description+"</td>";
	str += "	<td class='text-center'>";
	str += "	<img class='btnCateDel' data-count='"+ count +"' data-delNo='" +cateList.cateNo+ "' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>";
	str += "	</td>";
	str += "</tr>";


	if(direction == "up"){
		$("#cateList").prepend(str);
	}
	else if(direction =="down"){
		$("#cateList").append(str);	
	}else{
		console.log("direction 오류");
	}
		
};

</script>


</html>