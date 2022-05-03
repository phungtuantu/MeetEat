<template>
<div class="container">
    <div class="row">
        <div class="col-6">
            <img src="../../assets/ithan.jpg" width="500px">
            <button class="btn btn-success send btn-sm">
                EDIT PHOTO
            </button> 
        </div>
        <div class="col-3">
            <div class="column">
                <label>First name</label>
                <input type="text" name="" v-model="firstName">
                <label>Address</label><br>
                <input type="text" name="" v-model="address">
                <label>Phone number</label><br>
                <input type="text" name="" v-model="tel">
                <label>Current password</label><br>
                <input type="password" name="" v-model="password">
            </div>
        </div>
        <div class="col-3">
            <div class="column">
                <label>Family name</label>
                <input type="text" name="" v-model="lastName">
                <label>Zip Code</label><br>
                <input type="text" name="" v-model="zipCode">
              <br/>
                <label>City</label><br>
                <input type="text" name="" v-model="city">
                <label>New password</label><br>
                <input type="password" name="" v-model="newPassword">

              <button class="btn btn-success send btn-sm" type="button" @click="modifyAccount()">
                    SUBMIT
                </button> 
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import {urlAPI} from "@/variables";

export default {
  name: "ModificationAccount",
  data () {
    return {
      id : null,
      firstName : '',
      lastName : '',
      address : '',
      city : '',
      zipCode : '',
      tel : '',
      email : '',
      password : '',
      newPassword : '',
      user : JSON.parse(sessionStorage.getItem("user")),
      effective : null,

    }
  },
  methods :{
    //modifyAccount
    modifyAccount : async function () {
      /**
       * Long userId = Long.parseLong(request.getParameter("userId")); // a remplacer par session ?
       *         String firstName = request.getParameter("firstName");
       *         String lastName = request.getParameter("lastName");
       *         String address = request.getParameter("address");
       *         String city = request.getParameter("city");
       *         String zipCode = request.getParameter("zipCode");
       *         String noTelephone = request.getParameter("noTelephone");
       *         Boolean passwordUpdated = Boolean.parseBoolean(request.getParameter("passwordUpdated"));
       *         String password = request.getParameter("password");
       *         String profilePhotoPath = request.getParameter("profilePhotoPath");
       */
      await axios.get(urlAPI + 'todo=authenticate&mail=' + this.email + '&password=' + this.password)
          .then(response => (this.user = response.data));
      if (this.user !== null) {
        if (this.newPassword !== '') {
          this.newPassword = true;
        } else {
          this.newPassword = false;
        }
        await axios.get(urlAPI + 'todo=modifyAccount&firstName=' + this.firstName + '&lastName=' + this.lastName +
            '&address=' + this.address + '&city=' + this.city + '&zipCode=' + this.zipCode
            + '&noTelephone=' + this.tel + '&password=' + this.password + '&userId=' + this.id + '&passwordUpdated=' + this.newPassword)
            .then(response => (this.effective = response))
        this.newPassword = '';

        this.user.firstName = this.firstName ;
        this.user.lastName = this.lastName;
        this.user.city = this.city;
        this.user.noTelephone = this.tel;
        this.user.zipCode = this.zipCode ;
        this.user.address = this.address ;

        sessionStorage.setItem("user", JSON.stringify(this.user));



      }

    }
  },
  mounted() {
    this.firstName = this.user.firstName;
    this.lastName = this.user.lastName;
    this.city = this.user.city;
    this.email = this.user.mail;
    this.tel = this.user.noTelephone;
    this.zipCode = this.user.zipCode;
    this.address = this.user.address;
    this.id = this.user.id;

  }
}
</script>

<style scoped>
.container {
    width: 100%;
}

.col-6 img, .col-3 {
    margin-top: 60px;
    margin-bottom: 20px;
}

.col-3 .btn{
    margin-top: 105px;
    margin-bottom: 20px;
    width: 200px;
}

.column input{
    margin-bottom: 20px;
    background-color: #e0dfdf;
    border-radius: 5px;
}

.column{
    text-align: left;
}

</style>