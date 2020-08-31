<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Form example complete...thank you!</h1>
          <ul>
              <li>${formExample.name}</li>
              <li>${formExample.experience}</li>
              <li>${formExample.knowHtml}</li>
              <li>${formExample.knowCss}</li>
              <li>${formExample.knowJavascript}</li>
              <li>${formExample.skills}</li>
              <li>${formExample.startDate}</li>
          </ul>

        </div>
        <!-- /.container-fluid -->
        <!-- End of Main Content -->


<c:import url="/WEB-INF/jsp/common/footer.jsp" />