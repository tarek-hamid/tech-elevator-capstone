<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <div class="container">
            <div class="row">
              <section class="panel panel-default">
                <div class="panel-body">


                  <div class="row">
                    <div class="col-lg-3">
                      <h1>Animal Files</h1>
                    </div>

                    <div class="col-lg-9">
                      <div class="input-group">
                        <span class="input-group-btn">
                          <button class="btn btn-default" type="button">
                            <i class="fa fa-search" aria-hidden="true"></i>
                          </button>
                        </span>
                        <input type="search" id="accordion_search_bar" class="form-control"
                               placeholder="Typing in a word like 'legless' will make snake the only accordion row available.">
                      </div>
                      <!-- /input-group -->
                    </div>
                    <!-- /.col-lg-6 -->
                  </div>
                  <!-- Row -->

                  <div class="row">
                    <div class="col-lg-12 col-xs-12">


                      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default" id="collapseOne_container">
                          <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                              <a role="button"
                                 data-toggle="collapse"
                                 data-parent="#accordion"
                                 href="#collapseOne"
                                 aria-expanded="true"
                                 aria-controls="collapseOne">
                                <i class="fa fa-paw fa-fw" aria-hidden="true"></i>Lion
                              </a>
                            </h4>
                          </div>
                          <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                               aria-labelledby="headingOne">
                            <div class="panel-body">
                              <p>The lion (Panthera leo) is one of the big cats in the genus Panthera and a member of
                                the family Felidae. The commonly used term African lion collectively denotes the several
                                subspecies in Africa. With some males exceeding 250 kg
                                (550 lb) in weight, it is the second-largest living cat after the tiger. Wild lions
                                currently exist in sub-Saharan Africa and in India (where an endangered remnant
                                population resides in Gir Forest National Park). In ancient historic
                                times, their range was in most of Africa, including North Africa, and across Eurasia
                                from Greece and southeastern Europe to India. In the late Pleistocene, about 10,000
                                years ago, the lion was the most widespread large land mammal
                                after humans: Panthera leo spelaea lived in northern and western Europe and Panthera leo
                                atrox lived in the Americas from the Yukon to Peru.</p>
                            </div>
                          </div>
                        </div>
                      </div>
                      <p>Information is provided by Wikipedia</p>
                    </div>


                  </div>
                  <!-- Row -->
                </div>
                <!-- panel-body -->
              </section>
            </div>
            <!-- Row -->
          </div>
            <!-- Container -->


        </div>
        <!-- /.container-fluid -->
        <!-- End of Page Content -->

<!-- Page level plugins -->
<script src="/js/accordion.js"></script>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />