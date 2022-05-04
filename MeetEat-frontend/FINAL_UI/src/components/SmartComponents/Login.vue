<template>
  <div class="container">
      <div class="row">
          <div class="col-md-6">
              <div class="card">
                  <form onsubmit="event.preventDefault()" class="box">
                      <h1>Login</h1>
                      <input type="text" name="" placeholder="Email address" v-model="email">
                      <input type="password" name="" placeholder="Password" v-model="password">
                      <button type="button" class="btn btn-success" style="width: 90%; margin-bottom: 20px;" @click="login()">Login</button>
                      <p id="errorMessage"></p>
                      <div style="text-align: right; padding-right: 30px;">
                        <button class="btn btn-success" type="button" @click="createAccount()">Create an account</button>
                        </div>
                  </form>
              </div>
          </div>
      </div>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import {urlAPI} from "@/variables";
export default {
  name: "Login",

  data () {
    return{
      email : "",
      password : "",
      user : null,
    }
  },
   methods: {
    createAccount : function(){
      router.replace('/signup');
    },

     login : async function() {

       await axios.get(urlAPI + 'todo=authenticate&mail=' + this.email+'&password='+this.password)
           .then(response => (this.user = response.data));
        if(this.user !== null){
          console.log(this.user);
          sessionStorage.setItem("user", JSON.stringify(this.user));
          router.replace('/orderPage');
        }else{
          document.getElementById("errorMessage").innerText= "Login or password incorrect";
        }



     }
  },
}
</script>

<style scoped>
#errorMessage {
  color: red;
}
.card {
    margin-bottom: 20px;
    border: none
}

.box {
    width: 500px;
    padding: 20px;
    position: absolute;
    top: 50%;
    left: 50%;
    background: #191919;
    transition: 0.25s;
    margin-top: 100px
}

.box input[type="text"],
.box input[type="password"] {
    border: 0;
    background: white;
    display: block;
    margin: 20px auto;
    text-align: left;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 400px;
    outline: none;
    color: black;
    border-radius: 5px;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-transform: none;
    text-align: left;
    font-weight: 500
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
    width: 100%;
    border-color: #2ecc71
}

.box input[type="submit"] {
    border: 0;
    width: 400px;
    background: green;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #2ecc71;
    padding: 14px 40px;
    outline: none;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
    transition: 0.25s;
    cursor: pointer
}

.box input[type="submit"]:hover {
    background: #2ecc71;
    width: 100%;
}

.column{
  text-align: left;
}

.text-muted {
    margin-left : 30px;
    text-decoration: underline;
}



</style>