<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<link rel="stylesheet" href="../../resources/css/employee.css">
</head>
<body class="page--employee">
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>

<form action="/register/member.eansoft" method="post" id="registerForm" enctype="multipart/form-data">
<div>아이디 <input type="text" name="memberId"></div>
<div>비밀번호<input type="password" class="pwd" name="memberPwd" id="memberPwd"></div>
<div>비밀번호 확인 <input type="password"  class="pwd" id="pwdCheck">
<span id="eqPwd" class="pwdCheckAlert">비밀번호가 일치합니다.</span>
<span id="nePwd" class="pwdCheckAlert">비밀번호가 일치하지 않습니다.</span></div>
<div>이름<input type="text" name="memberName"></div>
<div>프로필 사진
			<p>
				<div class="profile-wrap">
					<figure>
						<img src="../../../resources/img/img_no_profile.png" alt="image preview">
					</figure>
				</div>
				<div class="profile-upload mt-20">
					<input type="file" id="profilePhoto" name="profileImg" value="">
				</div>
			</p>
			
		</div>
<button type="submit">회원가입</button>
	</form>

<script>

//프로필 사진 변경
var inputImg = document.getElementById("profilePhoto");
inputImg.addEventListener("change",function(e){
	return readImg(e.target);
})

//변경된 프로필 사진 미리보기
function readImg(input) {
            if(input.files && input.files[0]) {
                var reader = new FileReader()
                reader.onload = function(e){
                    var previewImage = document.querySelector(".profile-wrap>figure>img");
                    previewImage.src = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        

$('.pwd').focusout(function () {
	   var pwd1 = $("#memberPwd").val();
        var pwd2 = $("#pwdCheck").val();
  
        if ( pwd1 != '' && pwd2 == '' || pwd1 == '' && pwd2 == '') {
        	$("#eqPwd").css('display', 'none');
            $("#nePwd").css('display', 'none');
        } else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                $("#eqPwd").css('display', 'inline-block');
                $("#nePwd").css('display', 'none');
            } else {
                $("#eqPwd").css('display', 'none');
                $("#nePwd").css('display', 'inline-block');
            }
        }
});  

        
</script>

</body>
</html>