<template>
<div>
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
          <input type="file" class="custom-file-input" id="inputGroupFile01"
                 aria-describedby="inputGroupFileAddon01">
          <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
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
          <input type="file" class="custom-file-input" id="inputGroupFile02"
                 aria-describedby="inputGroupFileAddon01">
          <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
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
          <input type="file" class="custom-file-input" id="inputGroupFile03"
                 aria-describedby="inputGroupFileAddon01">
          <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
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

  <button class="btn btn-success" @click="submitData()">Submit</button>

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
    }
  }
}
</script>

<style scoped>

</style>