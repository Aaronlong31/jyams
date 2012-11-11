<%
    String loginType = (String) session.getAttribute("loginType");
    if ("admin".equals(loginType)) {
        response.sendRedirect("../admin/login.action?error=true");
    } else {
        response.sendRedirect("../welcome.action?error=true");
    }
%>