<template>

  <div>
    
    <template v-if="this.show === 1">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <h1 class="display-5">MeetEat</h1>
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

    <template v-if="this.show === 2">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
          <button type="button" class="btn btn-primary" @click="goHome()">
            <img src="../../assets/arrow-left.png" alt="left-arrow" width="40px">
            <!-- <a href="https://www.flaticon.com/free-icons/back" title="back icons">Back icons created by Roundicons - Flaticon</a> -->
          </button>
          <button type="button" class="btn btn-primary">
            <img src="../../assets/home.png" alt="home" width="35px">
          </button>
          <h1 class="display-5">MeetEat</h1>
          <img src="../../assets/MeetEat_logo.jpg" width="35px" @click="goHome()">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          </ul>
          <div class="form-inline my-2 my-lg-0">
            <button type="button" class="btn2 btn-primary">
              <img src="../../assets/mailbox.png" alt="basket" width="40px">
            </button>
            <button type="button" class="btn2 btn-primary">
              <img src="../../assets/basket.png" alt="basket" width="40px">
            </button>
            <div class="dropdown">
              <button type="button" class="btn2 btn-primary"> <!-- menuDeroulant -->
                <img src="../../assets/user.png" alt="user" width="40px">
              </button>
              <div class="dropdown-child">
                <a @click="becomeCook()">BECOME A COOK</a>
                <a @click="modifyAccount()">MODIFY ACCOUNT</a>
                <a @click="editPreferences()">EDIT MY PREFERENCES</a>
                <a @click="orders()">MY ORDERS</a>
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
export default {
  name: "NavbarReturnHome",
  data() {
    return {
      show:1,
      user : JSON.parse(sessionStorage.getItem("user")),
    }
  },
  methods: {
    goHome : function(){
      router.replace('/');
    },
    becomeCook : function(){
      router.replace('/becomeCook');
    },
    modifyAccount : function(){
      router.replace('/modificationAccount');
    },
    editPreferences : function(){
      router.replace('/selectpreferences');
    },
    orders : function(){
      router.replace('/historic');
    },
    logOut : function(){
      sessionStorage.setItem("user", null);
      sessionStorage.setItem("show",1);
      if(this.$route.name !== '/'){
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
  },
  async mounted() {
    this.user = sessionStorage.getItem("user");
    if (this.user !== null) {
      this.show = 2;
    }
    console.log(sessionStorage.getItem("show"));
    console.log(sessionStorage.getItem("user"));
  }
}
</script>


<style scoped>

.btn, .btn2{
    background-color: whitesmoke;
    border: 0;
    padding: 0;
    transition: 0s;
}

.btn :hover, .btn2 :hover {
    background: #2ecc71;
}

.btn2{
    margin-left: 10px;
}

.display-5{
    margin-left: 10px;
}

a{
  margin-left: 15px;
  margin-bottom: 10px;
}

img{
  cursor: pointer;
}

.dropdown {
position: relative;
display: inline-block;
}
.dropdown-child {
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
.dropdown:hover .dropdown-child {
display: block;
}

</style>