<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!-- Begin Page Content -->
<div class="container-fluid">
  <div class="col-lg-12">
    <div class="container">
      <div class="row">
        <section class="panel panel-default">
          <div class="panel-body">

            <div class="row">
              <div class="col-lg-9">
                <h1>Employees</h1>
              </div>

              <div class="col-lg-9">
                <div class="input-group">
                        <span class="input-group-btn">
                          <button class="btn btn-default" type="button">
                            <i class="fa fa-search" aria-hidden="true"></i>
                          </button>
                        </span>
                  <input type="search" id="accordion_search_bar" class="form-control"
                         placeholder="Typing in a word to search">
                </div>
                <!-- /input-group -->
              </div>
              <!-- /.col-lg-6 -->
            </div>
            <!-- row -->

            <div class="row">
              <div class="col-lg-12 col-xs-12">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                  <c:set var="counter" value="0"/>
                  <c:forEach var="employee" items="${employees}">
                    <c:set var="counter" value="${counter + 1}"/>
                    <div class="panel panel-default" id="collapse${counter}_container">
                      <div class="panel-heading" role="tab" id="heading${counter}">
                        <h4 class="panel-title">
                          <a role="button"
                             data-toggle="collapse"
                             data-parent="#accordion"
                             href="#collapse${counter}"
                             aria-expanded="true"
                             aria-controls="collapse${counter}">
                            <i class="fa fa-paw fa-fw" aria-hidden="true"></i>${employee.firstName} ${employee.lastName}
                          </a>
                        </h4>
                      </div>
                      <div id="collapse${counter}" class="panel-collapse collapse in" role="tabpanel"
                           aria-labelledby="heading${counter}">
                        <div class="panel-body">
                          <ul>
                            <li>${employee.position}</li>
                            <li>${employee.office}</li>
                            <li>${employee.startDate}</li>
                            <li>${employee.salary}</li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
            <!-- row -->

          </div>
          <!-- panel-body -->
        </section>
      </div>
      <!-- row -->
    </div>
    <!-- Container -->
  </div>
  <!-- col-lg-12 -->
</div>
<!-- /.container-fluid -->
<!-- End of Page Content -->

<!-- Page level plugins -->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="/js/accordion.js"></script>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />