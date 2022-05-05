<template>

  <div>
    
    <template v-if="user === null">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <h1 class="display-5"> MeetEat </h1>
          <a><img src="../../assets/MeetEat_logo.jpg" width="35px" @click="goHome()"></a>
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          </ul>
          <div class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="button" @click="login()">Sign In</button>
            <button class="btn btn-dark my-2 my-sm-0" type="button" @click="signup()">Sign Up</button>
          </div>
        </div>
      </nav>
    </template>

    <template v-if="user !== null">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
          <!--<button type="button" class="btn btn-nav" @click="goHome()">
            <img src="../../assets/arrow-left.png" alt="left-arrow" width="40px">
             
          </button>-->
          <button type="button" class="btn btn-nav" @click="goHome()">
            <img src="../../assets/home.png" alt="home" width="35px">
          </button>
          <h1 class="display-5">MeetEat</h1>
          <img src="../../assets/MeetEat_logo.jpg" width="35px" @click="goHome()">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          </ul>
          <div class="form-inline my-2 my-lg-0">
            <!--<button type="button" class="btn2 btn-nav">
              <img src="../../assets/mailbox.png" alt="mail" width="40px"  >
            </button>-->
            <button type="button" class="btn2 btn-nav">
              <img src="../../assets/basket.png" alt="basket" width="40px" @click="basket()">
            </button>
            <div class="dropdown">
              <button type="button"  class="btn2 btn-nav"> <!-- menuDeroulant -->
                <img src="../../assets/menu.png" alt="user" width="40px" @click="showMenu()">
              </button>
              <div id="userButton" class="dropdown-child">
                <template v-if="user.isCook !== true ">
                  <a @click="becomeCook()">BECOME A COOK</a>
                </template>
                <template v-if="user.isCook === true ">
                  <a @click="createOffer()">CREATE OFFER</a>
                  <a @click="consultCookOffers()">MY OFFERS</a>
                  <a @click="pendingRequests()">PENDING REQUESTS</a>
                </template>
                <a @click="modifyAccount()">MODIFY ACCOUNT</a>
                <a @click="editPreferences()">EDIT MY PREFERENCES</a>
                <a @click="orders()">MY ORDERS</a>
                <a @click="viewReviews()">VIEW REVIEWS</a>
                <a @click="logOut()">LOG OUT</a>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </template>
  </div>

</template>

<script>
import router from "@/router";
import axios from "axios";
import {urlAPI} from "@/variables";
export default {
  name: "NavbarReturnHome",
  data() {
    return {
      user : null,
      menuIsShowing : false,
    }
  },
  methods: {
    goHome : function(){
      router.replace('/');
    },
    basket : function(){
      router.replace('/myBasket');
    },
    becomeCook : function(){
      router.replace('/becomeCook');
    },
    createOffer : function(){
      router.replace('/createOffer');
    },
    pendingRequests : function(){
      router.replace('/pendingRequests');
    },
    consultCookOffers : function(){
      router.replace('/cookOfferHistory');
    },
    modifyAccount : function(){
      router.replace('/modificationAccount');
    },
    editPreferences : function(){
      router.replace('/selectpreferences');
    },
    viewReviews : function(){
      router.replace('/viewreviews');
    },
    orders : function(){
      router.replace('/history');
    },
    logOut : async function () {
      sessionStorage.setItem("user", null);
      await axios.get(urlAPI + "todo=disconnect");
      if (this.$route.name !== '/') {
        router.replace('/');
      }
      location.reload();
    },
    login : function(){
      router.replace('/login');
    },
    signup : function(){
      router.replace('/signup');
    },
    showMenu : function(){
      if(this.menuIsShowing === false){
        document.getElementById("userButton").style.display = "block";
        this.menuIsShowing = true;
      }else{
        document.getElementById("userButton").style.display = "none";
        this.menuIsShowing = false;
      }
    }
  },
  async mounted() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    console.log(sessionStorage.getItem("show"));
    console.log("user is "+sessionStorage.getItem("user"));
  }
}
</script>


<style scoped>

.btn-outline-secondary{
  margin-right: 15px;
}

a{
  margin-left: 15px;
  margin-bottom: 10px;
  cursor: pointer;
}

.btn-nav {
    color: whitesmoke;
    border: 0;
    padding: 0;
    transition: 0s;
}

.btn-nav :hover{
    background: #2ecc71;
}

.btn2{
    margin-left: 15px;
}

.btn .btn-dark{
  margin-left: 30px;
}

.display-5{
    margin-left: 10px;
}

img{
  cursor: pointer;
}

.dropdown {
position: relative;
display: inline-block;
}
.dropdown-child {
position:  absolute; 
z-index: 2;
right:0px;
top  :42px;
display: none;
background-color: gray;
color: white;
min-width: 50px;
cursor: pointer;
padding: 5px;
}
.dropdown-child a {
color: white;
padding: 5px;
text-decoration: none;
display: block;
background-color: black;
/*border: thick double green ;*/
margin: 5px;
}

h1{
 margin-right: 10px;
 color: #2ecc71;
}
/*.dropdown:hover .dropdown-child {
display: block;
}*/

</style>