<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="<%=request.getContextPath()%>/templates/login/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/templates/login/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/templates/login/assets/css/style.css" rel="stylesheet">

    <title>Login</title>
  </head>
  <body>
    <section class="form-02-main">
    <form  action="<%=request.getContextPath()%>/login" method="post">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="_lk_de">
              <div class="form-03-main">
                <div class="logo">
                  <img src="<%=request.getContextPath()%>/templates/login/assets/images/user.png">
                </div>
                <div>
                	<%String err = request.getParameter("err");
		            if("1".equals(err)){
		    	   		out.print("<span style=\" color: red; font-weight: bold; padding: 5px; \">Sai tên đăng nhập hoặc mật khẩu!</span>");
		    	   	}
		            %>
                </div>
                <div class="form-group">
                  <input type="text" name="username" class="form-control _ge_de_ol" placeholder="Tên đăng nhập" required="required" aria-required="true">
                </div>

                <div class="form-group">
                  <input type="password" name="password" class="form-control _ge_de_ol" placeholder="Mật khẩu" required="required" aria-required="true">
                </div>

                <div class="form-group">
                  <div class="_btn_04">
                    <input type="submit" name="submit" id ="submit" value="Đăng nhập"/>
                  </div>
                </div>

                <div class="form-group nm_lk"><p>Or Login With</p></div>

                <div class="form-group pt-0">
                  <div class="_social_04">
                    <ol>
                      <li><i class="fa fa-facebook"></i></li>
                      <li><i class="fa fa-twitter"></i></li>
                      <li><i class="fa fa-google-plus"></i></li>
                    </ol>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </form>
    </section>
  </body>
</html>