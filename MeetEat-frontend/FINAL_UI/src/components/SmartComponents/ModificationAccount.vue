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
                <input type="text" name="" placeholder="Ithan">
                <label>Address</label><br>
                <input type="text" name="" placeholder="20 avenue des arts, 69100 Villeurbanne">
                <label>Phone number</label><br>
                <input type="text" name="" placeholder="06.46.46.46.46">
            </div>
        </div>
        <div class="col-3">
            <div class="column">
                <label>Family name</label>
                <input type="text" name="" placeholder="Velarde">
                <label>Email address</label><br>
                <input type="text" name="" placeholder="ithan.velarde@insa-lyon.fr">
                <label>Certification</label><br>
                <input type="text" name="" placeholder="Professional">
                <button class="btn btn-success send btn-sm">
                    SUBMIT
                </button> 
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
  name: "ModificationAccount"
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