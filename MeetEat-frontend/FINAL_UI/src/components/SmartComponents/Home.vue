<template>
  <div>
    
    <!-- Carousel wrapper -->
    <div
        id="carouselBasicExample"
        class="carousel slide carousel-fade"
        data-mdb-ride="carousel"
    >
      <!-- Inner -->
      <div class="carousel-inner">
        <!-- Single item -->
        <div class="carousel-item active">
          <img
              src="https://mdbcdn.b-cdn.net/img/Photos/Slides/img%20(15).webp"
              class="d-block w-100"
              alt="Sunset Over the City"
          />
          <div class="carousel-caption d-none d-md-block">
            <div class="input-group input-group-lg mb-3">
              <input type="text" class="form-control" placeholder="Type you address" aria-label="Recipient's username" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button">View offers</button>
              </div>
            </div>
            <h3>Sign in to see your last researches</h3>

          </div>
        </div>

      </div>
      <!-- Inner -->

    </div>
    <!-- Carousel wrapper -->









    <br/>




    <!-- Carousel wrapper -->
    <div
        id="carouselMultiItemExample"
        class="carousel slide carousel-dark text-center"
        data-mdb-ride="carousel"
    >

      <!-- Inner -->
      <div class="carousel-inner py-4">
        <!-- Single item -->
        <div class="carousel-item active">
          <div class="container">
            <div class="row">
              <div class="col-lg-4">
                <div class="card">
                  <img
                      src="https://mdbcdn.b-cdn.net/img/new/standard/nature/181.webp"
                      class="card-img-top"
                      alt="Waterfall"
                  />
                  <div class="card-body">
                    <h5 class="card-title">Homemade Dishes</h5>
                    <p class="card-text">
                      Some quick example text to build on the card title and make up the bulk
                      of the card's content.
                    </p>
                  </div>
                </div>
              </div>

              <div class="col-lg-4 d-none d-lg-block">
                <div class="card">
                  <img
                      src="https://mdbcdn.b-cdn.net/img/new/standard/nature/182.webp"
                      class="card-img-top"
                      alt="Sunset Over the Sea"
                  />
                  <div class="card-body">
                    <h5 class="card-title">An Economic Option</h5>
                    <p class="card-text">
                      Some quick example text to build on the card title and make up the bulk
                      of the card's content.
                    </p>
                  </div>
                </div>
              </div>

              <div class="col-lg-4 d-none d-lg-block">
                <div class="card">
                  <img
                      src="https://mdbcdn.b-cdn.net/img/new/standard/nature/183.webp"
                      class="card-img-top"
                      alt="Sunset over the Sea"
                  />
                  <div class="card-body">
                    <h5 class="card-title">Stop waste</h5>
                    <p class="card-text">
                      Some quick example text to build on the card title and make up the bulk
                      of the card's content.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      <!-- Inner -->
    </div>
    <!-- Carousel wrapper -->
    </div>


    <br/>




    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Everywhere in France</h5>
        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      </div>
      <div style="padding-left:20%; padding-right:20%; margin-bottom: 30px; ">
        <img src="../../assets/maps.jpg" class="card-img-top rounded" height="500px;" alt="">
      </div>
    </div>




    <br/>



    <div class="container">
      <div class="row">
        <div class="col-sm">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Amiens</li>
            <li class="list-group-item">Angers</li>
            <li class="list-group-item">Bordeaux</li>
            <li class="list-group-item">Dijon</li>
          </ul>
        </div>
        <div class="col-sm">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">La Rochelle</li>
            <li class="list-group-item">Lille</li>
            <li class="list-group-item">Lyon</li>
            <li class="list-group-item">Marseille</li>
          </ul>
        </div>
        <div class="col-sm">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Nantes</li>
            <li class="list-group-item">Nice</li>
            <li class="list-group-item">Paris</li>
            <li class="list-group-item">Perpignan</li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "HomePage"
}
</script>

<style scoped>

</style>