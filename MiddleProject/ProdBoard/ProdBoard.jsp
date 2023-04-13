<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.row {
	  display: flex;
	  justify-content: flex-end;
	}
	.btn-primary {
	    width: 40%;
	}
	select.form-control {
	    width: 40%;
	}
	.col-md-4 button.btn-primary {
	    width: 40%;
	    margin: 0 auto;
	}
	
	#search{
		margin-right: 15%;
		margin-top: 3%;
	}
	
</style>
<title>ProdBoard Main</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="../js/jquery-3.6.4.min.js" type="text/javascript"></script>
<script src="../js/jquery.serializejson.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		
		$('#pdRegister').on('click', function() {
			location.href = location.href = '../ProdBoard/ProdRegisterPage.jsp';
		});
		
	})
</script>
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg navbar-white bg-white">
		<div class="container">
			<a class="navbar-brand mr-auto" href="#">
				<img src="../images/dditmarket.jpg" alt="#" style="width: 50px; height: 50px;">
			</a>
			<div class="text-center w-100">
				<h2 style="text-align: center; color: orange;">물품게시판</h2>
			</div>
		</div>
	</nav>
	<div class="d-flex justify-content-end" id="search">
	  <form class="form-inline my-2 my-lg-0">
	    <input class="form-control mr-sm-2" type="search" placeholder="검색어 입력" aria-label="Search">
	    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
	  </form>
	</div>
</header>


<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1>
					<pre>
					
				test
				test
				test
				test
				test
				test
				test
				test
				test
				test
					
					</pre>
				</h1>
			</div>
		</div>
	</div>
</main>
<footer>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4">
				<button class="btn btn-primary btn-block" data-target="#mregister" id="pdRegister">물품등록</button>
			</div>
			<div class="col-md-4">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled">
							<a class="page-link" href="#" tabindex="-1">이전</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item">
							<a class="page-link" href="#">다음</a>
						</li>
					</ul>
				</nav>
			</div>
			<div class="col-md-4">
				<select class="form-control">
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
	</div>
</footer>
</body>
</html>