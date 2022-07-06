<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css">
</head>

<body>
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
	<div id="conts">
	
	<div>
		<select id="searchCondition" style="width:70px; text-align:center; display:inline-block; margin-left : 350px;">
			<option value="ip" style="width:70px; text-align:center;">IP</option>
			<option value="date" style="width:70px; text-align:center;">날짜</option>
			<option value="time" style="width:70px; text-align:center;">시간</option>
		</select> 
		<div id="ipDiv" style="display:inline-block;"><input type="text" id="ipAdress" placeholder="ip검색"></div>
		<div id="dateDiv" style="display:inline-block;"><input type="text" size="3" placeholder="0000" id="year">년<input type="text" size="1"placeholder="00" id="month">월 <input type="text" size="1"placeholder="00" id="date">일</div>
		<div id="timeDiv" style="display:inline-block;"><input type="text" size="1" placeholder="00" id="hour">시<input type="text" size="1" placeholder="00" id="minute">분 <input type="text" size="1" placeholder="00" id="second">초</div>
		<button type="submit" onclick="searchremoteApprList();">검색</button>
	</div>
		<table id="remoteApprTable" class="table--basic">
			<thead>
				<tr>
					<td>접속no.</td>
					<td>접속한 IP</td>
					<td>접속 날짜</td>
					<td>접속 시간</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<script>
	$(document).ready(
			function() {
				remoteApprList();
			});
	
	function remoteApprList(){
		var ipAdress = $("#ipAdress").val();
		var year = $("#year").val();
		var month = $("#month").val();
		var date = $("#date").val();
		var hour = $("#hour").val();
		var minute = $("#minute").val();
		var second = $("#second").val();
		$.ajax({
			url : "http://127.0.0.1:8888/remoteAppr/list.hirp",
			type : "get",
			jsonp: "callback",
			dataType : "jsonp",
			data : {"ipAdress" : ipAdress, "year":year, "month":month, "date":date, "hour":hour, "minute":minute, "second":second},
			success : function(data) {
				var $remoteApprTable = $("#remoteApprTable tbody");
				var $tr = $("<tr>");
				for (var i = 0; i < data.length; i++) {
					var addrNo = data[i].addrNo;
					var addr = data[i].addr;
					var addrDate = data[i].addrDate;
					var addrTime = data[i].addrTime;
					var remoteApprData = "<tr>" + "<td>" + addrNo + "</td>" + "<td>" + addr + "</td>" + "<td>" + addrDate + "</td>" + "<td>" + addrTime + "</td>" + "</tr>" 
				
					$remoteApprTable.append(remoteApprData);
				}
			},
			error : function(){
				alert("Ajax 통신 실패");
			}
		})
	}
	
	//검색
	function searchremoteApprList(){
		var $remoteApprTableBody = $("#remoteApprTable tbody").children();
		$remoteApprTableBody.remove(); 
		remoteApprList();
	};
	

	//검색 select change시 다른 input보이게
	$("#ipDiv").show();
	$("#dateDiv").hide();
	$("#timeDiv").hide();
	
	$("#searchCondition").change(function(){
		var result = $("#searchCondition option:selected").val();
		if(result == 'ip'){
			$("#ipDiv").show();
			$("#dateDiv").hide();
			$("#timeDiv").hide();
		}else if(result == 'date'){
			$("#ipDiv").hide();
			$("#dateDiv").show();
			$("#timeDiv").hide();
		}else if(result == 'time'){
			$("#ipDiv").hide();
			$("#dateDiv").hide();
			$("#timeDiv").show();
		}
	});
	
	
	
	</script>
</body>
</html>