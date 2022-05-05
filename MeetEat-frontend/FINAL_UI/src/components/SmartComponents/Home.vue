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
        <!-- Single item src="https://mdbcdn.b-cdn.net/img/Photos/Slides/img%20(15).webp" -->
        <div class="carousel-item active">
          <img src="../../assets/food.jpg" class="d-block w-100" alt="food"/>
          <div class="carousel-caption">
            <div class="input-group input-group-lg mb-3">
              <input type="text" class="form-control" placeholder="Type you address" aria-label="Recipient's username" aria-describedby="basic-addon2" v-model="city">
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" @click="seeOffers()">View offers</button>
              </div>
            </div>
            <!--<h3>Sign in to see your last researches</h3>-->
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
                      src="../../assets/homemade.jpg"
                      class="card-img-top"
                      alt="homemade"
                  />
                  <div class="card-body">
                    <h5 class="card-title">Homemade Dishes</h5>
                    <p class="card-text">
                    </p>
                  </div>
                </div>
              </div>

              <div class="col-lg-4 d-none d-lg-block">
                <div class="card">
                  <img
                      src="../../assets/moneysave.jpg"
                      class="card-img-top"
                      alt="moneysave"
                  />
                  <div class="card-body">
                    <h5 class="card-title">An Economic Option</h5>
                    <p class="card-text">
                    </p>
                  </div>
                </div>
              </div>

              <div class="col-lg-4 d-none d-lg-block">
                <div class="card">
                  <img
                      src="../../assets/reduce-food-waste.jpg"
                      class="card-img-top"
                      alt="reduce-food-waste"
                  />
                  <div class="card-body">
                    <h5 class="card-title">Stop waste</h5>
                    <p class="card-text">
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
        <h5 class="card-title">Everywhere in the world</h5>
        <p class="card-text">Join MeetEat on all your travels around the world to discover the culinary traditions.</p>
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
            <li class="list-group-item">Berlin</li>
            <li class="list-group-item">Göteborg</li>
            <li class="list-group-item">Lyon</li>
            <li class="list-group-item">Paris</li>
          </ul>
        </div>
        <div class="col-sm">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Bac ninh</li>
            <li class="list-group-item">Séoul</li>
            <li class="list-group-item">Shanghai</li>
            <li class="list-group-item">Tokyo</li>
          </ul>
        </div>
        <div class="col-sm">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Agadir</li>
            <li class="list-group-item">Dakar</li>
            <li class="list-group-item">Mexico</li>
            <li class="list-group-item">Santa Cruz</li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "HomePage",
  data() {
    return {
      city : ""
    }
  },
  methods : {
    seeOffers : function (){
      sessionStorage.setItem("city", this.city)
      router.replace('/orderPage');
    }
  }
}
</script>

<style scoped>

.btn-outline-secondary{
 background-color: green;
 color: white;
}

.carousel-caption{
  top: 50%;
  transform: translateY(-50%);
  bottom: initial;
}

</style>