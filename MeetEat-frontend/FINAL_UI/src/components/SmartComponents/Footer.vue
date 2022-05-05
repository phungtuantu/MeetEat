<template>
<div>

  <template v-if="user === null">
  <section class="">
    <!-- Footer -->
    <footer class="text-center text-white" style="background-color: black;">
      <!-- Grid container -->
      <div class="container p-4 pb-0">
        <!-- Section: CTA -->
        <section class="">
          <p class="d-flex justify-content-center align-items-center">
            <span class="me-3">Register for free : </span>
            <button type="button" class="btn btn-outline-light btn-rounded" @click="signup()">
              Sign Up
            </button>
          </p>
          <p class="d-flex justify-content-center align-items-center" @click="signin()">
            <span class="me-3">Already registered ? </span>
            <button type="button" class="btn btn-outline-light btn-rounded">
              Sign In
            </button>
          </p>
        </section>
        <!-- Section: CTA -->
      </div>
      <!-- Grid container -->

      <!-- Copyright -->
      <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2022 Copyright:
        <a class="text-white" @click="goHome()">MeetEat.fr</a>
      </div>
      <p>
        MeetEat is the world's first peer-to-peer food exchange network. 
        Wherever you go, find the perfect meal from our wide range of low-cost dishes.
      </p>
      <!-- Copyright -->
    </footer>
    <!-- Footer -->
  </section>
  </template>

    
  <template v-if="user !== null">

    <!-- Remove the container if you want to extend the Footer to full width. -->
    <section class="">
      <!-- Footer -->
      <footer class="text-center text-lg-start text-dark" style="background-color: #ECEFF1">
        <br>
        <!-- Section: Links  -->
        <section class="">
          <div class="footer mt-2">
            <!-- Grid row -->
            <div class="row">
              <!-- Grid column -->
              <div class="col-4">
                <!-- Content -->
                <h6 class="text-uppercase fw-bold">MeetEat</h6>
                <hr class="mb-4 mt-0 d-inline-block mx-auto" style="width: 60px; background-color: #7c4dff; height: 2px"/>
                <p>
                  MeetEat is the world's first peer-to-peer food exchange network. 
                  Wherever you go, find the perfect meal from our wide range of low-cost dishes.
                </p>
              </div>
              <!-- Grid column -->

              <!-- Grid column -->
              <div class="col-3">
                <!-- Links -->
                <h6 class="text-uppercase fw-bold">Useful links</h6>
                <hr class="mb-4 mt-0 d-inline-block mx-auto" style="width: 60px; background-color: #7c4dff; height: 2px"/>
                <p>
                  <a class="text-dark" id="modifyAccount" href="https://github.com/GenT322/MeetEat/projects/1">
                  Follow us on gitHub
                  </a>
                </p>
                <p>
                  <a class="text-dark" id="becomeCook" href="https://www.facebook.com/SKILjudo">
                  Follow us on facebook
                  </a>
                </p>
                <p>
                  <a class="text-dark" id="editPreferences" href="https://www.insa-lyon.fr/fr/formation/informatique">
                    Follow us on insagram
                  </a>
                </p>
              </div>
              <!-- Grid column -->

              <!-- Grid column -->
              <div class="col-4">
                <!-- Links -->
                <h6 class="text-uppercase fw-bold">Contact</h6>
                <hr
                    class="mb-4 mt-0 d-inline-block mx-auto"
                    style="width: 60px; background-color: #7c4dff; height: 2px"
                    />
                <p><i class="fas fa-home mr-3"></i> 2 La Garde, 03320, Limoise FRANCE</p>
                <p><i class="fas fa-envelope mr-3"></i> meeteat@gmail.com</p>
                <p><i class="fas fa-phone mr-3"></i> +337 88 52 32 79</p>
              </div>
              <!-- Grid column -->
            </div>
            <!-- Grid row -->
          </div>
        </section>
        <!-- Section: Links  -->

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)" >
          © 2022 Copyright:
          <a class="text-black" @click="goHome()">MeetEat.fr</a>
        </div>
        <!-- Copyright -->
      </footer>
      <!-- Footer -->
    </section>
    <!-- End of .container -->

  </template>

</div>
</template>

<script>
import router from "@/router";
export default {
  name: "Footer",
  data() {
    return {
      user : null,
    }
  },
  methods: {
    goHome : function(){
      router.replace('/');
    },
    facebook : function(){
      router.replace('/');
    },
    github : function(){
      router.replace('github.com/GenT322/MeetEat/projects/1');
    },
    insagram : function(){
      router.replace('github.com/GenT322/MeetEat/projects/1');
    },
    
  },
  async mounted() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    console.log(sessionStorage.getItem("show"));
    console.log(sessionStorage.getItem("user"));
  }
}
</script>

<style scoped>

.btn{
    margin-left: 20px;
}

.col-4{
  margin-left: 25px;
}

a{
  cursor: pointer;
}

.row{
  padding: 0;
}

</style>