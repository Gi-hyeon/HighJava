<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.row {
	  display: flex;
	  justify-content: center;
	}
	.btn-primary {
	    width: 40%;
	}
	select.form-control {
	    width: 40%;
	}
	#registerCancle, #registerSubmit{
	    width: 20%;
	    margin: 0 auto;
	}
	#search{
		margin-right: 15%;
		margin-top: 3%;
	}
	#uploadImages{
		width: 170px;
		height: 140px;
	}
	.phrase{
		display: inline-block;
		margin: 1% 1% 1% 1%;
	}
	.form-group{
		margin: 5%;
	}
</style>
<title>ProdBoard Main</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/jquery-3.6.4.min.js" type="text/javascript"></script>
<script src="../js/jquery.serializejson.min.js" type="text/javascript"></script>

<!-- Kakao API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  
<script type="text/javascript">
	function PostCode() {
		  new daum.Postcode({
		    oncomplete: function(data) {
		      var fullAddress = data.jibunAddress; // 지번 주소로 변경
		      var extraAddress = '';
	
		      // 도로명 주소일 경우, 지번 주소로 변경
		      if (data.userSelectedType === 'R') {
		        fullAddress = data.jibunAddress;
		      }
	
		      // 주소 정보에서 지역 정보와 동 정보를 추출
		      var region = fullAddress.split(' ')[0] + ' ' + fullAddress.split(' ')[1]; // 지역 정보 (시/도, 시/군/구)
		      var dong = fullAddress.split(' ')[2]; // 동 정보
	
		      $('#tradingArea').val(region + ' ' + dong);
		    }
		  }).open();
	}
	
	//image 
	function setPreview(event) {
		for (var image of event.target.files) {
		    var reader = new FileReader();
	
		    reader.onload = function(event) {
		    	var img = document.createElement("img");
			    img.setAttribute("src", event.target.result);
			    img.style.width = "170px";
			    img.style.height = "140px";
			    img.style.marginLeft = "10px";
			    img.style.marginBottom = "10px";
			    document.querySelector("#imageContainer").appendChild(img);
		    };
			
		    console.log(image);
		    reader.readAsDataURL(image);
	    }
	} 
	
	$(function() {
		// 문자가 들어오면 올바른 숫자 입력하라는 알림
		$("#productPrice").on("input", function(){
		    var input = $(this).val();
		    if(!$.isNumeric(input)){
		      alert("올바른 숫자를 입력해주세요.");
		      $(this).val("");
		    }
		  });		
		
		$('.registerCancle').on('click', function() {
			location.href = '../ProdBoard/ProdBoard.jsp';
		});
		
		$('#defaultImages').on('click', function() {
			alert("사진 추가");
		});
		
		$('#modalAreaSel').on('click', function(){
			PostCode();
        });
		
		$('#fileInput').on('change', function() {
			//사진 미리보기
			setPreview(event);
		});
		
		$("#checkAgreement").on("change", function() {
	      if ($(this).is(":checked")) {
	        $("#registerSubmit").prop("disabled", false);
	      } else {
	        $("#registerSubmit").prop("disabled", true);
	      }
	    });
		
		//저장될 전역변수
		var value; 
		var $iform = $('#iform');
		
		$('#registerSubmit').on('click', function() {
			rdata = $('#rform').serializeJSON();
			//console.log(rdata);
			
			// ajax가 끝나기 전에 실행되므로 콜백 함수를 등록하고 그 안에서 2번째 ajax를 실행함
			$.ajax({
				url: `<%=request.getContextPath()%>/prodInsert.do`,
				type: 'post',
				data: rdata,
				success: function(res) {
					//alert(res.flag);
					
					$.ajax({
						url: `<%=request.getContextPath()%>/prodGetId.do`,
						type: 'post',
						success: function(res) {
							value = res.flag;
							
							//alert(value);
							
							// 2번째 ajax 요청 완료 후, "iform" 제출하는 코드 추가
		                    if ($iform.length > 0) {
		                    	$iform.attr('action', '<%=request.getContextPath()%>/prodFileUpload.do?prodId=' + value);
		                        $iform.submit();
		                    }
						},
						error: function(xhr) {
							alert("상태 : " + xhr.status)
						},
						dataType : 'json'
					});
					
				},
				error: function(xhr) {
					alert("상태 : " + xhr.status)
				},
				dataType : 'json'
			});
		})
	})
</script>
</head>
<body>

	<div class="text-center">
		<h2 style="text-align: center; color: orange;">물품 등록</h2>
	</div>
	<hr>
	<div class="container">
		<form id="rform" method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group row">
						<label for="productName" class="col-sm-2 col-form-label" class="">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="productName" placeholder="상품 제목을 입력하세요." name="productName">
						</div>
					</div>
					<div class="form-group row">
						<label for="productPrice" class="col-sm-2 col-form-label">판매 가격</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="productPrice" placeholder="가격을 입력하세요." name="productPrice">
						</div>
					</div>
					<div class="form-group row">
						<label for="tradingArea" class="col-sm-2 col-form-label">거래 지역</label>
						<div class="col-sm-10">
						    <input type="search" class="form-control" id="tradingArea" placeholder="거래 지역" name="tradingArea" disabled="disabled"><br>
						    <input type="button" id="modalAreaSel" value=" 지역 검색 " class="btn btn-primary" style="width: 15%;">
						</div>
					</div>
					<div class="form-group row"> 
						<label for="category" class="col-sm-2 col-form-label">카테고리</label>
						<div class="col-sm-10">
							<select class="form-control" id="category" name="category">
							  <option value="" selected disabled>카테고리</option>
							  <option value="전자제품">전자제품</option>
							  <option value="의류/잡화">의류/잡화</option>
							  <option value="가구/인테리어">가구/인테리어</option>
							  <option value="스포츠/레저용품">스포츠/레저용품</option>
							  <option value="자동차/오토바이">자동차/오토바이</option>
							  <option value="생활용품/가전제품">생활용품/가전제품</option>
							  <option value="도서/음반/DVD">도서/음반/DVD</option>
							  <option value="반려동물용품">반려동물용품</option>
							  <option value="기타 중고물품">기타 중고물품</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label for="productStatus" class="col-sm-2 col-form-label">상품 상태</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="productStatus" placeholder="상태을 입력하세요." name="productStatus">
						</div>
					</div>
					<div class="form-group row">
						<label for="productDetail" class="col-sm-2 col-form-label">상품 상태</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="productDetail" rows="6" placeholder="상세 정보를 입력하세요." name="productDetail"></textarea>
						</div>
					</div>
				</div>
			</div>
		</form>
			<form id="iform" name="prodId"  method="post" enctype="multipart/form-data">
				<div class="form-group row">
					<label for="defaultImages" class="col-sm-2 col-form-label">상품 이미지</label>
					<div class="col-sm-10">
						<label for="fileInput"> 
							<img src="../images/upload3.png" alt="Image" id="uploadImages">
						</label> 
						<input type="file" id="fileInput" name="fileInput" accept="image/*" style="display: none;" multiple="multiple">
						<div id="imageContainer" style="margin-top: 10px; display: inline-block;"></div>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-md-12" id="buttonBox">
					<h3 class="phrase">판매자 정보 제공 동의</h3>
					<p class="phrase">
					<input type="checkbox" id="checkAgreement">
					※해당정보는 구매자에게 제공됩니다.※
					</p>
					<button type="button" class="registerCancle btn btn-primary" id="registerCancle">취소</button>
					<button type="button" class="btn btn-primary" id="registerSubmit" disabled>등록</button>
				</div>
			</div>
	</div>
</body>
</html>