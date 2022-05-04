<template>
<div style="padding-right: 40px;padding-left: 40px; padding-top: 20px; padding-bottom: 20px;">
  <h1>
    Become cook
  </h1>

  <hr class="my-4"/>
  <br/>

  <div class="row">
    <div class="col-sm"  style="text-align: left">
      <h2>Take pictures of your kitchen and your kitchen</h2>
      <p>Please send us pictures of your kitchen and your equipment
        to chack that you will work in a clean and healthy space,
        and that your equipment is in good conditions.
      </p>
    </div>

    <div class="col-sm">
      <h2>Upload pictures</h2>
      <div class="input-group">
        <div class="custom-file">
          <input type="file" class="custom-file-input" id="kitchenPhotos"
                 accept=".jpg">
          <label class="custom-file-label" for="kitchenPhotos">Choose file</label>
          <p id="fileDisplayArea1"></p>

        </div>
      </div>
    </div>
  </div>

  <br/>


  <div class="row">
    <div class="col-sm"  style="text-align: left">
      <h2>Identity card</h2>
      <p>Upload a picture of your identity card.
      </p>
    </div>

    <div class="col-sm">
      <h2>Upload pictures</h2>
      <div class="input-group">
        <div class="custom-file">
          <input type="file" class="custom-file-input" id="identityPhotos"
                 aria-describedby="inputGroupFileAddon02" accept=".jpg">
          <label class="custom-file-label" for="identityPhotos">Choose file</label>
          <p id="fileDisplayArea2"></p>

        </div>
      </div>
    </div>
  </div>

  <br/>

  <div class="row">
    <div class="col-sm"  style="text-align: left">
      <h2>Chef certification (optional)</h2>
      <p>If you have any chef certification, upload it in png format to be certified on MeetEat.
      </p>
    </div>

    <div class="col-sm">
      <h2>Upload pictures</h2>
      <div class="input-group">
        <div class="custom-file">
          <input type="file" class="custom-file-input" id="chefCertificationPhoto"
                 aria-describedby="inputGroupFileAddon03" accept=".jpg">
          <label class="custom-file-label" for="chefCertificationPhoto">Choose file</label>
          <p id="fileDisplayArea3"></p>

        </div>
      </div>
    </div>
  </div>

  <br/>

  <div class="row">
    <div>
      <p>Read general conditions and sanitary conditions <a>here</a>
      </p>
      <div class="form-check">
        <input type="checkbox"  id="conditionsCheckbox">
        <label class="form-check-label" for="conditionsCheckbox" style="padding-left: 5px;">
          I have read and I accept general and sanitary conditions
        </label>
      </div>
    </div>


  </div>

  <button class="btn btn-success" @click="readFile()">Submit</button>

</div>
</template>

<script>
import router from "@/router";

export default {
  name: "VerificationCook",

  methods : {
    submitData : function() {
      console.log('submit');
      router.replace('/orderPage')
    },

    readFile : function () {
      var textType = /.jpg/;
      var doc1 = document.getElementById("kitchenPhotos").files[0];
      var doc2 = document.getElementById("identityPhotos").files[0];
      var doc3 = document.getElementById("chefCertificationPhoto").files[0];

      if (doc1.type.match(textType) && doc2.type.match(textType) && doc3.type.match(textType)) {

        //todo=approveCook
        //axios.put(urlAPI + 'insertTeamInfo')
          //        .then();
        console.log('send certification');

      }else{
        var fileDisplayArea1 = document.getElementById('fileDisplayArea1');
        var fileDisplayArea2 = document.getElementById('fileDisplayArea2');
        var fileDisplayArea3 = document.getElementById('fileDisplayArea3');

        fileDisplayArea1.innerText = 'File not supported';
        fileDisplayArea2.innerText = 'File not supported';
        fileDisplayArea3.innerText = 'File not supported';

      }

    },
  }
}
</script>

<style scoped>

</style>