<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <footer class="footer footer-black  footer-white ">
        <div class="container-fluid">
          <div class="row">
            <nav class="footer-nav">
            </nav>
            <div class="credits ml-auto">
              <span class="copyright">
                © <script>
                  document.write(new Date().getFullYear())
                </script>, made with <i class="fa fa-heart heart"></i> by Creative Như Hoàng
              </span>
            </div>
          </div>
        </div>
      </footer>
    </div>
  </div>
  <!--   Core JS Files   -->
  
 <!-- JQUERY -->
	
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/core/popper.min.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/core/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Chart JS -->
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/plugins/chartjs.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="<%=request.getContextPath()%>/templates/admin/assets/js/paper-dashboard.min.js?v=2.0.1" type="text/javascript"></script><!-- Paper Dashboard DEMO methods, don't include it in your project! -->
  <script src="<%=request.getContextPath()%>/templates/admin/assets/demo/demo.js"></script>
</body>

</html>