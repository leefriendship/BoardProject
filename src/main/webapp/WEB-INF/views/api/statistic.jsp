<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
	
	<div id="conts">
	<div id="startDateDiv">시작 날짜  <input type="date"id="startDate"></div>
	<div id="endDateDiv">마지막 날짜  <input type="date" id="endDate"></div>
	<button type="button" id="statisticBtn" onclick="searchStatistic();">조회</button>
		<table id="statisticTable" class="table--basic">
			<thead>
				<tr>
					<td>작성자ID</td>
					<td>작성한 게시물 개수</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<script>
		$(document).ready(
				function() {
					statistic();
				});
		
		
		//테이블에 데이터 넣기		
		function statistic(){
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			$.ajax({
				//url : "http://192.168.0.76:8888/statistics/count.hirp",
				url : "http://127.0.0.1:8888/board/statistic.hirp",
				type : "get",
				jsonp : "callback",
				dataType : "jsonp",
				data: {"startDate" : encodeURI(startDate), "endDate" : encodeURI(endDate)},
				success : function(data) {
					var $statisticTable = $("#statisticTable tbody");
					for (var i = 0; i < data.length; i++) {
						//내 controller 연결
						var emplId = data[i].emplId;
						var statisticCount = data[i].statisticCount;
						var statisticData = "<tr>" + "<td>" + emplId + "</td>" + "<td>" + statisticCount + "</td>" + "</tr>" 
						
						//민수controller 연결
						//var projectManager = data[i].projectManager;
						//var projectManagerCount = data[i].projectManagerCount;
						//var statisticData = "<tr>" + "<td>" + projectManager + "</td>" + "<td>" + projectManagerCount + "</td>" + "</tr>" 
						
						
						$statisticTable.append(statisticData);
					} 
				},
				error : function() {
					alert("Ajax 통신 실패");
				}
			})
		}
		
		//테이블 초기화 후 다시 생성
		function searchStatistic(){
			var statisticTableBody = $("#statisticTable tbody").children();
			statisticTableBody.remove();
			statistic();
		};
		
		//시작날짜 기본값
		document.getElementById("startDate").value = '2022-01-01';
		//마지막날짜 기본값
		document.getElementById("endDate").value = new Date().toISOString().substring(0, 10);
	</script>
</body>
</html>