<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Brewery Name</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                        <th>Open</th>
                        <th>Close</th>
                        <th>Website</th>
                        <th>History</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Brewery Name</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                        <th>Open</th>
                        <th>Close</th>
                        <th>Website</th>
                        <th>History</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${breweries}" var="brewery">
                    <tr>
                        <td>${brewery.name}</td>
                        <td>${brewery.address}</td>
                        <td>${brewery.email}</td>
                        <td>${brewery.phoneNumber}</td>
                        <td>${brewery.openFrom}</td>
                        <td>${brewery.openTo}</td>
                        <td>${brewery.website}</td>
                        <td>${brewery.history}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->
<!-- End of Page Content -->

<!-- Page level plugins -->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="/js/basictable.js"></script>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
