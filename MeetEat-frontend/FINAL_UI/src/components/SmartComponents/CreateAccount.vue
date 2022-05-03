<template>
  <div class="" align="center">
                  <form class="box" id="needs-validation" novalidate>
                    <h1>Sign up</h1>
                    <input type="text" name="" placeholder="Family name" class="form-control"  v-model="lastName" required>
                    <input type="text" name="" placeholder="First name" class="form-control"   v-model="firstName" required>
                    <input type="text" name="" placeholder="Address" class="form-control"  v-model="address" required>
                    <div class="column">
                        <input type="text" name="" placeholder="Zip code" class="form-control"  v-model="zipCode" required>
                        <input type="text" name="" placeholder="City" class="form-control"  v-model="city" required>
                    </div>
                    <input type="text" name="" placeholder="Phone number" class="form-control"   v-model="tel" required>
                    <input type="email" name="" placeholder="Email address" class="form-control"  v-model="email" required>
                    <input type="password" name="" placeholder="Password" class="form-control"  v-model="password" required>
                    <button type="button" class="btn btn-success" v-on:click="createUser()">Sign in</button>

                  </form>
  </div>
</template>

<script>
import axios from "axios";
import {urlAPI} from "@/variables";
import router from "@/router";

export default {
  name: "CreateAccount",
  data() {
    return {
      firstName : "",
      lastName : "",
      email : "",
      address : "",
      city : "",
      zipCode : "",
      tel : "",
      password : "",
      effective : null,
    }
  },
  methods : {
    createUser : async function () {
      var form = document.getElementById('needs-validation');
      if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
        console.log("PAS OK");

      } else {

        console.log("OK");

        axios.get(urlAPI + 'todo=createAccount&firstName=' + this.firstName + '&lastName=' + this.lastName
            + '&mail=' + this.email + '&address=' + this.address + '&city=' + this.city + '&zipCode=' + this.zipCode
            + '&noTelephone=' + this.tel + '&password=' + this.password)
            .then(response => (this.effective = response))

        console.log('Effectif : ' + this.effective);
        await router.replace('/orderPage');


      }


      form.classList.add('was-validated');
    },

  }
}
</script>

<style scoped>


.box {
    width: 500px;
    padding: 20px;
    background: #191919;
    text-align: center;
    transition: 0.25s;
    margin-top: 10px;
}

.box input[type="text"],
.box input[type="email"],
.box input[type="password"] {
    border: 0;
    display: block;
    margin: 20px auto;
    text-align: left;
    padding: 10px 10px;
    width: 400px;
    outline: none;
    color: black;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-transform: none;
    text-align: left;
    font-weight: 500
}

.btn {
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
.column input[type="email"],
.column input[type="text"]{
    float: left;
    display: inline-flexbox;
    margin-left: 30px;
    margin-right: 0px;
    margin-top: 0;
    margin-bottom: 20px;
    width: 185px;
}

.text-muted {
    margin-left : 30px;
    text-decoration: underline;
}

</style>