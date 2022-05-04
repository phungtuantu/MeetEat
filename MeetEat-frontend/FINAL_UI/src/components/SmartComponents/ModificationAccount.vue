<template>
<div class="container" @load="load()">
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
                <input type="text" name="" placeholder="Ithan" class="enableInput">
                <label>Address</label><br>
                <input type="text" name="" placeholder="20 avenue des arts" class="enableInput"><br>
                <label>City</label><br>
                <input type="text" name="" placeholder="Villeurbanne" class="enableInput">
                <label>Zip code</label><br>
                <input type="text" name="" placeholder="69100" class="enableInput">
                <label>Password</label><br>
                <input type="password" id="pass" name="password" class="enableInput">
            </div>
        </div>
        <div class="col-3">
            <div class="column">
                <label>Family name</label>
                <input type="text" name="" placeholder="Velarde" class="enableInput">
                <label>Email address</label><br>
                <input type="text" name="" placeholder="ithan.velarde@insa-lyon." class="disableInput" disabled><br>
                <label>Certification</label><br>
                <input type="text" name="" placeholder="Professional" class="disableInput" disabled><br>
                <label>Phone number</label><br>
                <input type="text" name="" placeholder="06.46.46.46.46" class="enableInput">
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
    name: "ModificationAccount",
    methods: {
        load : function () {
            console.log('load');
            document.getElementById("pass").defaultValue = "hello123456";
        },
    },
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
    margin-top: 100px;
    margin-bottom: 20px;
    width: 200px;
    margin-left: -50px;
}

input[type="text"], input[type="password"]{
    margin-bottom: 20px;
    border-radius: 5px;
}

.enableInput{
    background-color: #f8f8f8;
}

.disableInput{
    background-color: #e8e8e8;
}

.column{
    text-align: left;
}

</style>