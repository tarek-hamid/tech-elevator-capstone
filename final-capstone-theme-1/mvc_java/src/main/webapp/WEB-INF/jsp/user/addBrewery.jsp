<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!-- Begin Page Content -->
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <!--<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>-->
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Add Brewery</h1>
                                </div>
                                <form class="user" method="POST" action="/addBrewery">
                                    <input type="hidden" name="destination" value="${param.destination}"/>
                                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                                    <div class="form-group">
                                        <input type="name" class="form-control form-control-user"
                                               name="name" id="name" aria-describedby="nameHelp" placeholder="Enter Name Of Brewery...">
                                    </div>
                                    <div class="form-group">
                                        <input type="phoneNumber" class="form-control form-control-user"
                                               name="phoneNumber" id="phoneNumber" placeholder="Phone Number">
                                    </div>
                                    <div class="form-group">
                                        <input type="website" class="form-control form-control-user"
                                               name="website" id="website" placeholder="Website">
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user"
                                               name="email" id="email" placeholder="Email">
                                    </div>
                                    <div class="form-group">
                                        <input type="address" class="form-control form-control-user"
                                               name="address" id="address" placeholder="Address">
                                    </div>
                                    <div class="form-group">
                                        <input type="history" class="form-control form-control-user"
                                               name="history" id="history" placeholder="History">
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input type="checkbox" class="custom-control-input" value="true" id="active">
                                            <label class="custom-control-label" for="active">Is Active</label>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Login
                                    </button>
                                </form>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<!-- End of Page Content -->

<c:import url="/WEB-INF/jsp/common/footer.jsp" />